package com.bdms.spark.util

import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.regionserver.BloomType
import java.io.IOException
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.conf.Configuration
import org.apache.commons.configuration.CompositeConfiguration
import org.slf4j.LoggerFactory
import org.apache.commons.configuration.ConfigurationException
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.mapreduce.OutputFormat
import org.apache.hadoop.hbase.client.Mutation
import org.apache.hadoop.hbase.HConstants
import org.apache.spark.Logging


/**
  * Description:
  * 		Hbase操作相关，主要作用是为spark程序提供  Hbase读写操作方法  
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-7-28下午5:45:12            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object HBaseUtil extends Serializable with Logging{
  
     val LOG = LoggerFactory.getLogger(HBaseUtil.getClass())

	 val  HBASESITE = "hbase/hbase-site.xml"
	 val  HBASEHDFS = "hbase/hdfs-site.xml"
	
	 //数据类型转换
	 def convert(triple:  Iterator[(String, String, String, String)]):Iterator[(ImmutableBytesWritable,Put)] = {
	     var p:Put = null
	     triple.map( z => {
	          if(z._1.contains("latest")){
	              p = new Put(Bytes.toBytes(z._1))
	              p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_ID"),Bytes.toBytes(z._1.split("-")(1))) 
	          }else{
	        	  p = new Put(Bytes.toBytes( z._1 + "-" + z._2 ))
	        	  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_ID"),Bytes.toBytes(z._1)) 
	          }
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("START_TIME"),Bytes.toBytes(z._2))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("ENTER_TIMES"),Bytes.toBytes(z._3))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("EXIT_TIMES"),Bytes.toBytes(z._4))
			  (new ImmutableBytesWritable, p)
	     })
		 
	  }
	  
	 //存储Hbase
	 def storeRegionToHbase(row: RDD[(String, String, String, String)],tableName:String){
	    
		    val hConf = getHConf
			
			//指定输出格式和输出表名
			//val jobConf = new JobConf(hConf,this.getClass)
			//jobConf.setOutputFormat(classOf[TableOutputFormat])
			//jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
			//jobConf.setNumMapTasks(60)
			//jobConf.setNumReduceTasks(60)
		    hConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
		    hConf.setClass("mapreduce.job.outputformat.class", classOf[TableOutputFormat[String]], classOf[OutputFormat[String,Mutation]])
			
			row.mapPartitions(convert).saveAsNewAPIHadoopDataset(hConf);

	  }
	 
	 def getHConf():Configuration = {
	   
	    val hConf = HBaseConfiguration.create()
		hConf.addResource(HBASESITE)
		hConf.addResource(HBASEHDFS)
		
		hConf	
	   
	 }

	 def checkTableExist(hConf:Configuration,tableName:String,cf:String,inMemory:Boolean=false,ttl:Int=HConstants.FOREVER ){
	   
	    val admin = new HBaseAdmin(hConf);
		if(!admin.tableExists(tableName)){
		  
		    val htd = new HTableDescriptor(TableName.valueOf(tableName));
			 
			 val hcd = new HColumnDescriptor(cf)
			 hcd.setMaxVersions(3);
			 hcd.setBloomFilterType(BloomType.ROWCOL)
			 hcd.setInMemory(inMemory)
			 hcd.setTimeToLive(ttl)
			 htd.addFamily( hcd);
			 try {
				 admin.createTable(htd);
			 } catch  {
			 	case e : IOException => logError(s"创建hbase表  $tableName 失败",e)
			 }finally {
				 	admin.close();
			 }
		}
	   
	 }
	 
	 def checkOrCreateTable(tableName:String,cf:String,inMemory:Boolean=false,ttl:Int=HConstants.FOREVER){
	   
	   checkTableExist(getHConf,tableName,cf,inMemory,ttl);
	 }
	

}