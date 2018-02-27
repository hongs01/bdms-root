package com.bdms.spark.gj

import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigReadUtil
import com.bdms.common.lang.StringUtils
import com.bdms.spark.util.HBaseUtil

/**
  * Description:
  * 		根据历史数据计算站点的每天的客流的预测数据。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-23上午10:11:24            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object GuiJWeekStatisticsRun extends Serializable with Logging {
  
  def main(args: Array[String]) {
  
      //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
	val path = config2.getString("spark.gjyc.hdfs")
	
	
	if(StringUtils.isBlank(path)  ){
	  
	     logError(s"存储历史数据的路径$path 不能为空!")
	  
	}else{
	  
		 logInfo(s" 轨交历史预测数据 计算的job使用 的hdfs的路径为 $path .")
	
		 val tableName = "history_predicte_gj";
	    
	     HBaseUtil.checkOrCreateTable(tableName,"luxnew");
	    
	     val guiJWeekStatistics = new GuiJWeekStatistics()
	     guiJWeekStatistics.startApp("guiJWeekStatistics", path,tableName)
	}
}

}