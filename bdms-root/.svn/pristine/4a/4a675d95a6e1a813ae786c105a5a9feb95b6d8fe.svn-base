package com.bdms.spark.gj

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.rdd.RDD
import java.io.IOException
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configuration
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import scala.io.Source
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericXmlApplicationContext
import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkUtil
import scala.collection.mutable.ArrayBuffer
import com.bdms.spark.util.DataBaseUtil
import java.text.SimpleDateFormat
import org.apache.spark.Accumulator
import scala.math.Ordering
import com.bdms.spark.util.LatestRemember


/**
  * Description:
  * 		这是一个例子。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-7-28下午5:32:53            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class GuiJStreamingDay extends Serializable {
  
  //获取所有换乘站信息
   val hcMSG  : java.util.Map[String,String] = DataBaseUtil.findAllTrans
    
    /* 
     * @Title: calcuteData  
     * @Description: TODO    对原始数据 进行解析   将站点 归类到  换乘站 和 线路  以及 所有换成站  所有站点 四类  以便  统计
     * @param @param iter 
     * @param @return    
     * @return scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.String>>    
     * @throws 
     * @Author  Lixc
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
	 * @Description: TODO   启动  任务
	 * @param @param appName
	 * @param @param filePath    
	 * @return void    
	 * @throws 
	 * @Author Lixc
	*/
    def startApp(appName:String = this.getClass().getName(),tableName:String,tableName2:String,socketHost:String,socketPort:String) {
      
       val sparkConf = SparkUtil.getSparkStreamingConf(appName)
		  
       
	   val ssc =  new StreamingContext(sparkConf, Seconds(30))
  	  // val broadcast = ssc.sparkContext.broadcast(format)
       
		 // ssc.checkpoint("/tmp/lixc/hbase/checkpoint/")
		  	  
       	 //获取 spark Dstream  并解析数据     station_id start_time in out
         //val streams = ssc.textFileStream(filePath)
       
        //过滤掉正在复制的文件 
      // val streams = ssc.fileStream[LongWritable, Text, TextInputFormat]("hdfs://dswhhadoop-1:8020/ftp/",{path: Path => !path.getName().endsWith("_")},true).map(_._2.toString())
        
        //获取数据
        val streams = ssc.socketTextStream(socketHost, socketPort.toInt)
         streams.print
       
         //每五分钟的实时数据  
        val kl_data =  streams.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(1),x(2),Math.abs(x(4).toInt/100).toString,Math.abs(x(5).toInt/100).toString)))
                        .persist(StorageLevel.MEMORY_AND_DISK_2)
         
         
         //整理需要合并的数据
        val cal_data = kl_data.mapPartitions(calcuteData)
         
         //合并数据
        val res_data =  cal_data.reduceByKey((x,y)=> {
           val xs = x.split(";")
           val ys = y.split(";")
          ( xs(0).toInt+ys(0).toInt) + ";" + ( xs(1).toInt+ys(1).toInt)
         } ).mapPartitions(_.map(x => {
            val rows = x._1.split(";")
            val lines = x._2.split(";")
            (rows(0),rows(1),lines(0),lines(1))
         }))
         
         
         //复制一份到最新的数据 
        /*val latestData = kl_data.mapPartitions(_.map(x => {
        		("latest-"+x._1,x._2,x._3,x._4)
        }))*/
         
        //计算 准 最新时间
      /* val latestData = kl_data.map( x => ("time",broadcast.value.parse(x._2).getTime())).groupByKey(1)
        	   .map( x => {
        		   		
        		   	("000000" ,"000000",x._2.toList.sorted(Ordering.Long.reverse)(0).toString ,"0")	
        	   })
         */
         //数据汇总
         val result =  kl_data.union(res_data)
         
	    //存储数据
		 result.foreachRDD(rdd => {
				HBaseUtil.storeRegionToHbase(rdd,tableName)
				HBaseUtil.storeRegionToHbase(rdd,tableName2)
			})
		 
		  ssc.start()
		  ssc.awaitTermination()
	
  }
  
}