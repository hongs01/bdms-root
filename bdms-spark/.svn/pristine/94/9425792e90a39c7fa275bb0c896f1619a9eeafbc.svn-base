package com.bdms.spark.csdata

import com.bdms.spark.util.HBaseUtil
import com.bdms.spark.util.SparkConfigReadUtil
import org.apache.spark.Logging
import com.bdms.spark.util.SparkConfigProperty
import org.apache.commons.lang.StringUtils


object CSDataAnalyzerRun extends Serializable with Logging{

  def main(args: Array[String]): Unit = {
     //读取相关的配置
    val config2 = SparkConfigReadUtil.getConfig()
    
	val path = config2.getString("spark.csData.hdfs")
	
	
	if(StringUtils.isBlank(path)){
	  
	  logError(s"存储历史数据的路径$path 不能为空!")
	  
	}else{
	  
		logInfo(s" csData数据计算的job使用 的hdfs的路径为 $path .")

	    val tableName = "superCalculate_csData";
	    
	    HBaseUtil.checkOrCreateTable(tableName,"cs");
	    val cs = new CSDataAnalyzer
	    cs.startApp("CSDataAnalyzer", path ,tableName)
	  
	}
    
  }

}