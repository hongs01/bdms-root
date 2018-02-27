package com.bdms.spark.gj

import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.commons.lang.StringUtils

/**
  * Description:
  * 		轨交站点图标四色预警值计算。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-23上午10:03:38            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object GuiJBorderForecastRun extends Serializable with Logging {
  
  def main(args: Array[String]) {
  
    //日志开启debug模式
    //Logger.getLogger("org.apache.spark.streaming").setLevel(Level.DEBUG);
   // Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF);
    
      //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
	val path = config2.getString("spark.border.hdfs")
	
	
	if(StringUtils.isBlank(path)  ){
	  
	  logError(s"存储历史数据的路径$path 不能为空!")
	  
	}else{
	  
		 logInfo(s" 轨交历史临界值数据 计算的job使用 的hdfs的路径为 $path .")
    
	    val day = new GuiJBorderForecast()
	    day.startApp("GuiJBorderForecast",path)
	    //day.startApp("GuiJBorderForecast", "hdfs://dswhhadoop-1:8020/dams/DayData/2015/06/success/FLUXNEW_DAY_20150626")
	}
}

}