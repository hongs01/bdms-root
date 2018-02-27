package com.bdms.spark.dzwl

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
import scala.collection.mutable.ArrayBuffer
import org.apache.spark.Logging

/**
  * Description:
  * 		电子围栏  数据处理。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-27上午11:07:40            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class DZWLDataStore extends Serializable with Logging {
  
    val qyms  : java.util.Map[String,String] = DataBaseUtil.findAllQYM()

    //数据格式转换  任务名,区域名,时间 ,用户数,用户数5天同比,漫入数,漫入数5天同比,漫出数,漫出数5天同比
    def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
     
         val cf = Bytes.toBytes("dzwl");
	     var p:Put = null
	     
	     triple.map( z => {
	              p = new Put(Bytes.toBytes(z(1) + "-" + z(2)))
				 // p.add(cf, Bytes.toBytes("rwm"),Bytes.toBytes(z(0))) 
				  p.add(cf, Bytes.toBytes("qym"),Bytes.toBytes(z(1))) 
				  p.add(cf, Bytes.toBytes("sj"),Bytes.toBytes(z(2))) 
				  p.add(cf, Bytes.toBytes("yhs"),Bytes.toBytes(z(3))) 
				  p.add(cf, Bytes.toBytes("yhstb"),Bytes.toBytes(z(4))) 
				  p.add(cf, Bytes.toBytes("mrs"),Bytes.toBytes(z(5))) 
				  p.add(cf, Bytes.toBytes("mrstb"),Bytes.toBytes(z(6))) 
				  p.add(cf, Bytes.toBytes("mcs"),Bytes.toBytes(z(7))) 
				  p.add(cf, Bytes.toBytes("mcstb"),Bytes.toBytes(z(8))) 
				  (new ImmutableBytesWritable, p)
	            })
	            
	  }
	  
	 //存储Hbase
	def storeRegionToHbase(row: RDD[Array[String]],tableName:String){
	    
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
            if(code == null ){
               // code = "-1"
              logError( "区域名  " + row(1) + " 在数据库中没有对应的code,该条记录将忽略...")
              
            }else{
              
              res += (Array(row(0),code,row(2),row(3),row(4),row(5),row(6),row(7),row(8)))
            }
	  })
	  
	  res.toIterator
	  
	}
	
  
   def startApp(appName:String = this.getClass().getName(),tableName:String,tableName2:String,socketHost:String,socketPort:String){
   
    val conf = SparkUtil.getSparkStreamingConf(appName)
    val ssc = new StreamingContext(conf,Seconds(60))
    
    //val qym = ssc.sparkContext.broadcast(qyms)
    
    //读取原始的电子围栏数据  任务名,区域名,时间 ,用户数,用户数5天同比,漫入数,漫入数5天同比,漫出数,漫出数5天同比
    val  fileData =  ssc.socketTextStream(socketHost, socketPort.toInt)
    
    fileData.print
  
    //过滤标题栏                                                                                                                                                                                                                            
    val metaData = fileData.map( _.split(",")).filter(_.length == 9 ).filter( _(2).length() > 6)
                           .mapPartitions(changeNameByCode)
    
      //存储到hbase
    metaData.foreachRDD( rdd => {
      storeRegionToHbase(rdd,tableName);
      storeRegionToHbase(rdd,tableName2);
    })
    
    ssc.start
    ssc.awaitTermination
    
  }

}