package com.bdms.spark.gj

import com.bdms.spark.util.HBaseUtil
import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.commons.lang.StringUtils

/**
  * Description:
  * 		根据历史。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-23上午10:14:08            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object GuiJODDataAnalyzerRun extends Serializable with Logging {
  
  def main(args: Array[String]) {
  
    
     //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
    
	val path = config2.getString("spark.od.hdfs")
	
	
	if(StringUtils.isBlank(path)  ){
	  
	  logError(s"存储历史数据的路径$path 不能为空!")
	  
	}else{
	  
		 logInfo(s" 热门线路  计算的job使用 的hdfs的路径为 $path .")
     
    
	    val tableName = "hot_od_gj";
	    
	     HBaseUtil.checkOrCreateTable(tableName,"luxnew");
	     val od = new GuiJODDataAnalyzer()
	     od.startApp("GuiJODDataAnalyzer", path ,tableName)
	    // od.startApp("GuiJODDataAnalyzer", "hdfs://dswhhadoop-1:8020/dams/ODData/*/*/*",tableName)
	}
}

}