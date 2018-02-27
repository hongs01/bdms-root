package com.bdms.spark.gj

import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkConfigReadUtil
import com.bdms.spark.util.SparkConfigProperty
import org.apache.commons.lang.StringUtils
import org.apache.spark.Logging

/**
  * Description:
  * 		实时轨交数据。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-23上午10:27:11            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object GuiJStreamingDayRun extends Serializable with Logging {

  def main(args: Array[String]) {
    //日志开启debug模式
    //Logger.getLogger("org.apache.spark.streaming").setLevel(Level.DEBUG);
   // Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF);
    
    
     //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
	val host = config2.getString(SparkConfigProperty.GJSocket.getName())
	val port = config2.getString(SparkConfigProperty.GJPort.getName())
	val ttl = config2.getString("streaming.gj.table.ttl")
	val inmemory:Boolean = config2.getBoolean("streaming.gj.table.inmemory", false);
	
	if(StringUtils.isBlank(host) || StringUtils.isBlank( port) ){
	  
	  logError("轨交数据的socket的host或port不能为空!")
	  
	}else{
	  
		 logInfo(s"轨交数据 socket 主机 IP 为 $host, 端口为: $port .")
	     val tableName = "streaming_gj"
	     
	      if( StringUtils.isBlank(ttl) ){
	          logInfo("轨交数据 对应的hbase的表中的内容 永远不会过期.")
	          HBaseUtil.checkOrCreateTable(tableName,"luxnew",inmemory)
	          
	      }else{
	         logInfo(s"轨交数据 对应的hbase的表中的内容 的过期时间为    $ttl 秒.")
	         HBaseUtil.checkOrCreateTable(tableName,"luxnew",inmemory,ttl.toInt)
	      }
		 val tableName2 = "streaming_gj_history"
		 HBaseUtil.checkOrCreateTable(tableName2,"luxnew",inmemory)
	     val day = new GuiJStreamingDay()
	     day.startApp("GuiJStreamingDay",tableName,tableName2,host,port)
	 
    
	}
}
}