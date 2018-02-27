package com.bdms.spark.dzwl

import com.bdms.spark.util.SparkConfigReadUtil
import com.bdms.spark.util.SparkConfigProperty
import org.apache.commons.lang.StringUtils
import org.apache.spark.Logging
import com.bdms.spark.util.HBaseUtil

object DZWLHistoryDataStoreRun extends Serializable with Logging {
  
  def main(args: Array[String]) {
    
    //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
	val host = config2.getString(SparkConfigProperty.DZWLSocket.getName())
	val port = config2.getString(SparkConfigProperty.DZWLPort.getName())
	//val ttl = config2.getString("streaming.dzwl.table.ttl")
	//val inmemory:Boolean = config2.getBoolean("streaming.dzwl.table.inmemory", false);
	
	if(StringUtils.isBlank(host) || StringUtils.isBlank( port)  ){
	  
	  logError("电子围栏数据的socket的host或port不能为空!")
	  
	}else{
    
		logInfo(s"电子围栏数据   socket 主机 IP 为 $host, 端口为: $port .")
	    
	    val tableName = "history_predicte_dzwl"
	      
	   
	     logInfo("电子数据 对应的hbase的表中的内容 永远不会过期.")
	     HBaseUtil.checkOrCreateTable(tableName,"dzwl")
	    
	    
	    val dds = new DZWLHistoryDataStore   
       
	    dds.startApp("DZWLHistoryDataStore", tableName,host,port)
	}
 }

}