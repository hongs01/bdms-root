package com.bdms.spark.gj

import org.apache.spark.Logging;
import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.commons.lang.StringUtils

object GuiJODOneDayAnalyzerRunner extends Serializable with Logging {

  def main(args: Array[String]): Unit = {}

  
  	 //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
    
	val path = config2.getString("spark.od.hdfs")
	
	
	if(StringUtils.isBlank(path)  ){
	  
	  logError(s"存储历史数据的路径$path 不能为空!")
	  
	}else{
	  
		 logInfo(s" 热门线路  计算的job使用 的hdfs的路径为 $path .")
     
    
	    val tableName = "hot_od_day_gj";
	    
	     HBaseUtil.checkOrCreateTable(tableName,"luxnew");
	     
	     val od = new GuiJODOneDayDataAnalyzer
	     
	     od.startApp("GuiJODOneDayDataAnalyzer", path ,tableName)
	  
	}
  
}