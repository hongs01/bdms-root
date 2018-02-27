package com.bdms.spark.gj

import com.bdms.spark.util.SparkUtil
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary
import org.apache.spark.mllib.stat.Statistics
import java.util.Calendar
import java.text.SimpleDateFormat
import org.apache.spark.mllib.linalg.Vectors
import java.text.ParseException
import org.slf4j.LoggerFactory
import scala.collection.mutable.ArrayBuffer
import com.bdms.spark.util.DataBaseUtil
import com.bdms.spark.util.HBaseUtil

/**
  * Description:
  * 		历史每周的轨交数据的一个统计 和  预测。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-7-29上午11:29:18            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class GuiJWeekStatistics extends Serializable {
  
    val LOG = LoggerFactory.getLogger(classOf[GuiJWeekStatistics]);
  
    val calendar = Calendar.getInstance()
    val simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
    val hcMSG :  java.util.Map[String,String] = DataBaseUtil.findAllTrans
    
    /**
      * description:  将整理过的原始数据进行数据格式转换 ， 将日期转换层 工作日 与 休息日两大类
      * @param rows
      * @return
      * scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.String>>
      * 2015-8-3 下午12:52:30
      * by Lixc
     */
    def dataConvert( rows : Iterator[(String, String, String, String)]): Iterator[(String,String)] = {
      
    	   var day_of_week:Int = -1
           var timeType:String = null
      
	       var countRow:ArrayBuffer[(String, String)] = new ArrayBuffer[(String, String)]()
	       
	       rows.foreach( x => {
	         
		      try{
	        	  calendar.setTime(simpleDateFormat.parse(x._2))
	          } catch{
	            case e: ParseException => LOG.error("数据解析过程中，时间格式转换失败。",e);
	          }
	          day_of_week = calendar.get(Calendar.DAY_OF_WEEK)
	          if( day_of_week > 1 && day_of_week < 7 ){
	            timeType = "11111111" + x._2.substring(8)
	          }else{
	            timeType = "00000000" + x._2.substring(8)
	          }
	         countRow += (( x._1 + ";" + timeType,x._3 + ";" + x._4 ))
	       })
       
	       countRow.toIterator
    }
    
    
    /**
      * description:  转换统计数据
      * @param iter
      * @return
      * scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.String>>
      * 2015-8-4 上午9:02:57
      * by Lixc
     */
    def  calcuteData( iter : Iterator[(String, String, String, String)] ) : Iterator[(String, String)] = {
     
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
    
    
    /* 
	 * @Title: startApp  
	 * @Description: TODO        启动spark任务 
	 * @param @param appName     任务名称  默认使用  本类的类名
	 * @param @param filePath    历史数据的路径
	 * @return void    
	 * @throws 
	 * @Author  Lixc
	*/
	def startApp(appName:String = this.getClass().getName(),filePath:String,tableName:String){
	    
	     val sparkConf = SparkUtil.getSparkConf(appName)
	     val sc = new SparkContext(sparkConf)
	     
	     //读取原始数据  并清洗数据 和 缓存到内存  ( station_id,start_time , enter_times,exit_times )
	     val metaData =  sc.textFile(filePath).mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(1),x(2),Math.abs(x(4).toInt/100).toString,Math.abs(x(5).toInt/100).toString)))
                        .persist(StorageLevel.MEMORY_AND_DISK_2)
	     
           
         val countData = metaData.mapPartitions(calcuteData)   
         
          //合并数据
         val counted_data =  countData.reduceByKey((x,y)=> {
           val xs = x.split(";")
           val ys = y.split(";")
          ( xs(0).toInt+ys(0).toInt) + ";" + ( xs(1).toInt+ys(1).toInt)
         } ).mapPartitions(_.map(x => {
            val rows = x._1.split(";")
            val lines = x._2.split(";")
            (rows(0),rows(1),lines(0),lines(1))
         }))
             
         
         //原始数据 与统计数据 合并  并时间进行转换，每天 => 每周
	     val weekData =  metaData.union(counted_data).mapPartitions(dataConvert)
	     
	     //分组统计  求平均值
	     val resData = weekData.groupByKey.map(x => {
	       
	    	val len  =  x._2.size
	    	val ee   =  x._2.map(z => z.split(";")).map(z => (z(0).toInt,z(1).toInt))
					      .reduce[(Int, Int)]( (m:(Int,Int),n:(Int,Int)) =>{
					    	  ( m._1  + n._1, m._2 + n._2 )
					       })
		   val rows = x._1.split(";")
		   (rows(0),rows(1),Math.floor(ee._1/len).toInt.toString ,Math.floor(ee._2/len).toInt.toString) 
	     })
	     
	     //存储数据
		HBaseUtil.storeRegionToHbase(resData,tableName)
			  	
        sc.stop          
	     
	  }

}