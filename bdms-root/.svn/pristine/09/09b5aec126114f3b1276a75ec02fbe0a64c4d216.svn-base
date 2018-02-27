package com.bdms.spark.wifi

import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.commons.lang.StringUtils
import com.bdms.spark.util.HBaseUtil

/**
  * Description:
  * 		wifi 实时数据 处理的入口。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-16上午10:27:58            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object WIFIDataStoreRun extends Serializable with Logging {
  
    def main(args: Array[String]) {
     
    val config2 = SparkConfigReadUtil.getConfig()
	val host = config2.getString("streaming.wifi.socket.host")
	val port = config2.getString("streaming.wifi.socket.port")
	val ttl = config2.getString("streaming.wifi.table.ttl")
	val inmemory:Boolean = config2.getBoolean("streaming.wifi.table.inmemory", false);
	
	if(StringUtils.isBlank(host) || StringUtils.isBlank( port) ){
	  
	  logError("wifi数据的socket的host或port不能为空!")
	  
	}else{
	    logInfo(s"wifi数据 socket 主机 IP 为 $host, 端口为: $port .")
	    val tableName = "wifi2"
	     
	      if( StringUtils.isBlank(ttl)  ){
	         logInfo("wifi数据 对应的hbase的表中的内容 永远不会过期.")
	         HBaseUtil.checkOrCreateTable(tableName,"metadata",inmemory)
	      }else{
	         logInfo(s"wifi数据 对应的hbase的表中的内容 的过期时间为    $ttl 秒.")
	         HBaseUtil.checkOrCreateTable(tableName,"info",inmemory,ttl.toInt)
	      }
         
	    val wifi = new  WIFIDataStore
        wifi.startApp("WIFIDataStore", tableName,host,port)
	}
    
  
 }

}