package com.bdms.spark.dzwl

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
import com.bdms.spark.util.SparkUtil
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.Seconds
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import com.bdms.spark.util.HBaseUtil
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.mapreduce.OutputFormat
import org.apache.hadoop.hbase.client.Mutation
import com.bdms.spark.util.DataBaseUtil
import org.apache.spark.Logging

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
class DZWLWeekStatistics extends Serializable with Logging {
  
    val LOG = LoggerFactory.getLogger(classOf[DZWLWeekStatistics]);
    
    val  HBASESITE = "hbase/hbase-site.xml"
	val  HBASEHDFS = "hbase/hdfs-site.xml"
  
    val calendar = Calendar.getInstance()
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    val qyms  : java.util.Map[String,String] = com.bdms.spark.util.DataBaseUtil.findAllQYM()
    
    
    //数据格式转换  任务名,区域名,时间 ,用户数,用户数5天同比,漫入数,漫入数5天同比,漫出数,漫出数5天同比
//   def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
   def convert(triple:  Iterator[(String, String, String, String, String, String, String, String)]):Iterator[(ImmutableBytesWritable,Put)] = {
     
         val cf = Bytes.toBytes("dzwl");
	     var p:Put = null
	     
	     triple.map( z => {
	              p = new Put(Bytes.toBytes(z._1 + "-" + z._2.replace(" ", "-")))
				 // p.add(cf, Bytes.toBytes("rwm"),Bytes.toBytes(z(0))) 
				  p.add(cf, Bytes.toBytes("qym"),Bytes.toBytes(z._1)) 
				  p.add(cf, Bytes.toBytes("sj"),Bytes.toBytes(z._2)) 
				  p.add(cf, Bytes.toBytes("yhs"),Bytes.toBytes(z._3)) 
				  p.add(cf, Bytes.toBytes("yhstb"),Bytes.toBytes(z._4)) 
				  p.add(cf, Bytes.toBytes("mrs"),Bytes.toBytes(z._5)) 
				  p.add(cf, Bytes.toBytes("mrstb"),Bytes.toBytes(z._6)) 
				  p.add(cf, Bytes.toBytes("mcs"),Bytes.toBytes(z._7)) 
				  p.add(cf, Bytes.toBytes("mcstb"),Bytes.toBytes(z._8)) 
				  (new ImmutableBytesWritable, p)
	            })
	            
	  }
	  
	 //存储Hbase
	 def storeRegionToHbase(row: RDD[(String, String, String, String, String, String, String, String)],tableName:String){
	    
		    val hConf = HBaseUtil.getHConf
			
			//指定输出格式和输出表名
			hConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
		    hConf.setClass("mapreduce.job.outputformat.class", classOf[TableOutputFormat[String]], classOf[OutputFormat[String,Mutation]])
			
			row.mapPartitions(convert).saveAsNewAPIHadoopDataset(hConf)

	  }
    
  
	 //将 记录中的 区域名 数据库中的code
	def changeNameByCode( rows :  Iterator[Array[String]] ) : Iterator[Array[String]] = {
	  val res : ArrayBuffer[Array[String]] = new ArrayBuffer[Array[String]]();
	  rows.foreach( row => {
	        var code:String = qyms.get(row(1))
	        //logError( "区域名  " + row(1) + " 在数据库中没有对应的code,该条记录将忽略..."+code)
            if(code == null){
              //code = "-1"
              logError( "区域名  " + row(1) + " 在数据库中没有对应的code,该条记录将忽略...")   
              res += (Array(row(0),"xxxxxxxx",row(2),row(3),row(4),row(5),row(6),row(7),row(8)))
            }else{
              res += (Array(row(0),code,row(2),row(3),row(4),row(5),row(6),row(7),row(8)))
            }
	  })
	  res.toIterator
	}
	
	
	
	
	
	
	
    
    /**
      * description:  将整理过的原始数据进行数据格式转换 ， 将日期转换层 工作日 与 休息日两大类
      * @param rows
      * @return
      * scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.String>>
      * 2015-8-3 下午12:52:30
      * by Lixc
     */
    def dataConvert( rows : Iterator[(String, String, String, String,String, String, String, String,String)]): Iterator[(String,String)] = {
      
    	   var day_of_week:Int = -1
           var timeType:String = null
      
	       var countRow:ArrayBuffer[(String, String)] = new ArrayBuffer[(String, String)]()
	       
	       rows.foreach( x => {
	         
		      try{
	        	  calendar.setTime(simpleDateFormat.parse(x._3))
	          } catch{
	            case e: ParseException => LOG.error("数据解析过程中，时间格式转换失败。",e);
	          }
	          day_of_week = calendar.get(Calendar.DAY_OF_WEEK)
	          if( day_of_week > 1 && day_of_week < 7 ){
	            timeType = "11111111" + x._3.substring(10)
	          }else{
	            timeType = "00000000" + x._3.substring(10)
	          }
	         countRow += (( x._2 + ";" + timeType,x._4 + ";" + x._5 + ";" + x._6 + ";" + x._7 + ";" + x._8 + ";" + x._9 ))
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
	 * @Author  Yangbo
	*/
	def startApp(appName:String = this.getClass().getName(),filePath:String,tableName:String){
	    
	     val sparkConf = SparkUtil.getSparkConf(appName)
	     val sc = new SparkContext(sparkConf)
	    
	     val qym = sc.broadcast(qyms)
	     LOG.info("qymis:"+qym)
                        
        //过滤标题栏                                                                                                                                                                                                                          
	     val metaData = sc.textFile(filePath)
	     				.map( _.split(",")).filter(_.length == 9 ).filter( _(2).length() > 6)
//                        .mapPartitions(changeNameByCode)
	     				
	     				
	     				.map(x => {
                                var code:String = qym.value.get(x(1))
                                if(code == null ){
                                    code = "-1"
                                }
                                Array(x(0),code,x(2),x(3),x(4),x(5),x(6),x(7),x(8))
                           })
                           .filter( x => !"-1".equals(x(1)))
                           
                        .map(x => (x(0),x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))  
                        .persist(StorageLevel.MEMORY_AND_DISK_2)
	             
         LOG.info("metadata'ssizeis"+metaData.count)
         LOG.info("metadata'ssizeis"+qyms.size()+"---"+qyms.keySet()+"---"+qyms.get("陆家嘴")+"---"+qym.value.get("核心外滩"))
         
         
         //时间进行转换，每天 => 每周
	     val weekData =  metaData.mapPartitions(dataConvert)
	     
	     
	     //分组统计  求平均值
	     val resData = weekData.groupByKey.map(x => {
	       
	    	val len  =  x._2.size
	    	val ee   =  x._2.map(z => z.split(";")).map(z => (z(0).toInt,z(1).toInt,z(2).toInt,z(3).toInt,z(4).toInt,z(5).toInt))
					      .reduce[(Int, Int,Int,Int,Int,Int)]( (m:(Int,Int,Int,Int,Int,Int),n:(Int,Int,Int,Int,Int,Int)) =>{
					    	  ( m._1  + n._1, m._2 + n._2,  m._3 + n._3,  m._4 + n._4, m._5 + n._5, m._6 + n._6 )
					       })
		   val rows = x._1.split(";")
		   (rows(0),rows(1),Math.floor(ee._1/len).toInt.toString ,Math.floor(ee._2/len).toInt.toString,Math.floor(ee._3/len).toInt.toString,Math.floor(ee._4/len).toInt.toString,Math.floor(ee._5/len).toInt.toString,Math.floor(ee._6/len).toInt.toString)
	     })
	     
	     LOG.info("resdata'ssizeis:"+resData.count)
	     //存储数据
	     storeRegionToHbase(resData,tableName)  	
        sc.stop          
	  }

}