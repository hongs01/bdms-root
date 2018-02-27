package com.bdms.spark.hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HTable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.HColumnDescriptor
import java.io.IOException
import org.apache.log4j.Logger

object HBaseManager  {
  
   private final val LOG = Logger.getLogger(this.getClass());
  private final val hConf = new HBaseConfiguration() 
  private var hTable:HTable = null;
  private var thePut:Put = null;
  
  
  
   def putData(row: Array[String],tableName:String) { 
   
  } 
   
   def getTable():HTable = {
     
     val hConf = HBaseConfiguration.create()
	    hConf.set("hbase.zookeeper.quorum", "dswhhadoop-4,dswhhadoop-3,dswhhadoop-2,dswhhadoop-5,dswhhadoop-6,dswhhadoop-7,dswhhadoop-8,dswhhadoop-1");
		hConf.set("hbase.zookeeper.property.clientPort", "2181");  
		hConf.set("hbase.rootdir","hdfs://dswhhadoop-2:8020/apps/hbase/data");
		
		val admin = new HBaseAdmin(hConf);
		if(!admin.tableExists("GIS")){
		  val htd = new HTableDescriptor(TableName.valueOf("GIS"));
		  htd.addFamily(new HColumnDescriptor("id"));
		  htd.addFamily(new HColumnDescriptor("gis"));
		  try {
				admin.createTable(htd);
			} catch  {
				case e : IOException => e.printStackTrace();
			}
			if(!admin.tableExists("GIS")){
			  LOG.info("创建GIS表失败")
			  println("创建GIS表失败")
			  System.exit(-1)
			}
		}
		 new HTable(hConf,"GIS");
     
   }

}