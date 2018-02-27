package com.bdms.spark.dzwl

import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigProperty
import org.apache.commons.lang.StringUtils

/**
  * Description:
  * 		电子围栏数据 spark streaming job 的任务入口。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-27上午11:03:12            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object DZWLDataStoreRun extends Serializable with Logging {
  
  def main(args: Array[String]) {
    
    //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
	val host = config2.getString(SparkConfigProperty.DZWLSocket.getName())
	val port = config2.getString(SparkConfigProperty.DZWLPort.getName())
	val ttl = config2.getString("streaming.dzwl.table.ttl")
	val inmemory:Boolean = config2.getBoolean("streaming.dzwl.table.inmemory", false);
	
	if(StringUtils.isBlank(host) || StringUtils.isBlank( port)  ){
	  
	  logError("电子围栏数据的socket的host或port不能为空!")
	  
	}else{
    
		logInfo(s"电子围栏数据   socket 主机 IP 为 $host, 端口为: $port .")
	    
	    val tableName = "streaming_dzwl"
	      
	     if( StringUtils.isBlank( ttl ) ){
	        logInfo("电子围栏数据 对应的hbase的表中的内容 永远不会过期.")
	        HBaseUtil.checkOrCreateTable(tableName,"dzwl",inmemory)
	     }else{
	        logInfo(s"电子围栏数据 对应的hbase的表中的内容 的过期时间为    $ttl 秒.")
	        HBaseUtil.checkOrCreateTable(tableName,"dzwl",inmemory,ttl.toInt)
	     }
		val tableName2 = "streaming_dzwl_history"
		HBaseUtil.checkOrCreateTable(tableName2,"dzwl",inmemory)
	    
	    val dds = new DZWLDataStore   
       
	    dds.startApp("DZWLDataStore", tableName,tableName2,host,port)
	}
 }

}