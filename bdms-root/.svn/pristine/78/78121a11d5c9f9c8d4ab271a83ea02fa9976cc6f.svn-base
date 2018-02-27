package com.bdms.spark.gj

import com.bdms.spark.util.SparkUtil
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import scala.collection.mutable.ArrayBuffer
import com.bdms.spark.util.DataBaseUtil
import java.util.ArrayList
import com.bdms.entity.dams.Criterion

/**
  * Description:
  * 		通过计算 获取每个站点的初始 临界点预测值
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-17下午9:33:35            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class GuiJBorderForecast extends Serializable{
  
  
   val hcMSG :  java.util.Map[String,String] = DataBaseUtil.findAllTrans
    
    /* 
     * @Title: calcuteData  
     * @Description: TODO    对原始数据 进行解析 
     * @param @param iter 
     * @param @return    
     * @return scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.String>>    
     * @throws 
     * @Author  Lixc
    */
    def  calcuteData( iter : Iterator[(String,String, Int, Int)] ) : Iterator[(String, String)] = {
     
     var countRow:ArrayBuffer[(String, String)] = new ArrayBuffer[(String, String)]()
     
     iter.foreach( x => {
       
       if( hcMSG.containsKey(x._1) ){
         //该站点是换乘站   
         countRow += (( "0099;"+ x._2, x._3+ ";" + x._4 ),( x._1.substring(0, 2) + "00;"+ x._2, x._3+ ";" + x._4 ),
        		 	  (hcMSG.get(x._1) + ";" + x._2, x._3+ ";" + x._4),("0098;" + x._2, x._3+ ";" + x._4))
       }else{
         countRow += (( "0099;"+ x._2, x._3+ ";" + x._4 ),( x._1.substring(0, 2) + "00;"+ x._2, x._3+ ";" + x._4 ))
       }
       
     })
     countRow.toIterator
   }
   
  
  
  def startApp( applicationName:String = this.getClass().getName() , filePath:String){
    
    val conf = SparkUtil.getSparkConf(applicationName, "")
    val sc = new SparkContext(conf)
    
    //读取原始数据
    val OriginalData = sc.textFile(filePath)
    
    //对数据经行最初拆分与过滤
    val calculateData = OriginalData.mapPartitions(_.map(_.split(",")).filter(_.length == 6).map((x => (x(1),x(2),Math.abs(x(4).toInt/100),Math.abs(x(5).toInt/100)))))
    
    //汇总 总人数,换乘人数,线路人数
   val collectData_tmp = calculateData.mapPartitions(calcuteData)
   val collectData = collectData_tmp.reduceByKey((x,y) => {
	   	val num1 = x.split(";")
	   	val num2 = y.split(";")
	   	((num1(0).toInt + num2(0).toInt) + ";" + (num1(1).toInt + num2(1).toInt))
     }).map( x => {
         	  val num = x._2.split(";")
         	  (x._1.split(";")(0),num(0).toInt,num(1).toInt)
     })
     
     //站点数据
   val stationData = calculateData.map(x => (x._1,x._3,x._4))
 
   //合并汇总数据和站点数据
   val allData_tmp = stationData.union(collectData)  
   
   //分离 进站 出站 进出和数据
   val allData_enter = allData_tmp.map(x => (x._1 + ";enter" , x._2))
   val allData_exit  = allData_tmp.map(x => (x._1 + ";exit" , x._3))
   val allData_sum   = allData_tmp.map(x => (x._1 + ";sum" ,x._2 + x._3))
   val allData_sub   = allData_tmp.map(x => (x._1 + ";sub" ,Math.abs(x._2 - x._3)))
   
   //合并 进站 出站 进出和 数据
   val allData = allData_enter.union(allData_exit).union(allData_sum).union(allData_sub)
   
   //计算临界值
   val resData = allData.groupByKey.map( x =>{
			      val max = Math.ceil(x._2.max/0.75).toInt
			     (x._1 , Math.ceil(max*0.5).toInt + "," + Math.ceil(max*0.8).toInt + "," + max)
		   })
		   
		   
	var ct:Criterion  = null
	var key:Array[String] = null
	var isStation:Boolean = true
	
	val list:java.util.ArrayList[Criterion] = new ArrayList[Criterion]()
	
	
	
    val storeData = resData.collect.foreach( x => {
      
    	isStation = true
      
        key = x._1.split(";")
      
    	ct = new Criterion()
    	ct.setCode(key(0))
    	
    	if( "0099".equals(key(0)) ){
    	  
    		if( "enter".equals(key(1)) ){
    		    ct.setType("allEnterRT")
    		}
    		if( "exit".equals(key(1)) ){
    			ct.setType("allExitRT")
    		}
    		
    		if( "sum".equals(key(1)) ){
    			ct.setType("allSumRT")
    		}
    		
    		if( "sub".equals(key(1)) ){
    			ct.setType("allSubRT")
    		}
    		
    		isStation = false
    		
    	}
        
        if( "0098".equals(key(0)) ){
        	
        	if( "enter".equals(key(1)) ){
        		ct.setType("transLineEnterRT")
        	}
        	if( "exit".equals(key(1)) ){
        		ct.setType("transLineExitRT")
        	}
        	
        	if( "sum".equals(key(1)) ){
        		ct.setType("transLineSumRT")
        	}
        	
        	if( "sub".equals(key(1)) ){
        		ct.setType("transLineSubRT")
        	}
        	
        	isStation = false
        	
        }
        
        if( key(0).endsWith("00") ){
        	
        	if( "enter".equals(key(1)) ){
        		ct.setType("lineEnterRT")
        	}
        	if( "exit".equals(key(1)) ){
        		ct.setType("lineExitRT")
        	}
        	
        	if( "sum".equals(key(1)) ){
        		ct.setType("lineSumRT")
        	}
        	
        	if( "sub".equals(key(1)) ){
        		ct.setType("lineSubRT")
        	}
        	
        	isStation = false
        	
        }
        
        if(isStation){
          
            if( "enter".equals(key(1)) ){
        		ct.setType("stationEnterRT")
        	}
        	if( "exit".equals(key(1)) ){
        		ct.setType("stationExitRT")
        	}
        	
        	if( "sum".equals(key(1)) ){
        		ct.setType("stationSumRT")
        	}
        	
        	if( "sub".equals(key(1)) ){
        		ct.setType("stationSubRT")
        	}
          
        }
    	ct.setLevel(x._2)
    	ct.setVersion("1.0")
    	list.add(ct)
    })
    
    DataBaseUtil.insertCriterion(list)
    
    //job完成  ,关闭SparkContext
    sc.stop
    
  }
  
  

}