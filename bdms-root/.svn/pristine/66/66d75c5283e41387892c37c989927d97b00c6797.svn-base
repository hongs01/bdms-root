package com.bdms.spark.sp

import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkConfigReadUtil
import com.bdms.spark.util.SparkConfigProperty
import com.bdms.common.lang.StringUtils
import org.apache.spark.Logging

/**
  * Description:
  * 		视频数据 spark streaming job 的任务入口。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-27上午11:04:11            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object ImageMetaDataStoreRun extends Serializable with Logging{
  
  def main(args: Array[String]) {
     
    val config2 = SparkConfigReadUtil.getConfig()
	val host = config2.getString(SparkConfigProperty.SPSocket.getName())
	val port = config2.getString(SparkConfigProperty.SPPort.getName())
	val ttl = config2.getString("streaming.sp.table.ttl")
	val inmemory:Boolean = config2.getBoolean("streaming.sp.table.inmemory", false);
	
	if(StringUtils.isBlank(host) || StringUtils.isBlank( port) ){
	  
	  logError("视频数据的socket的host或port不能为空!")
	  
	}else{
	    logInfo(s"视频数据 socket 主机 IP 为 $host, 端口为: $port .")
	    val tableName = "jk_img_meta"
	     
	      if( StringUtils.isBlank(ttl)  ){
	         logInfo("视频数据 对应的hbase的表中的内容 永远不会过期.")
	         HBaseUtil.checkOrCreateTable(tableName,"metadata",inmemory)
	      }else{
	         logInfo(s"视频数据 对应的hbase的表中的内容 的过期时间为    $ttl 秒.")
	         HBaseUtil.checkOrCreateTable(tableName,"metadata",inmemory,ttl.toInt)
	      }
         
	    val img = new  ImageMetaDataStore
        img.startApp("ImageMetaDataStore", tableName,host,port)
	}
    
  
 }

}