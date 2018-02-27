package com.bdms.spark.gj

import com.bdms.spark.util.SparkUtil
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel


class GuiJODStreamingDataAnalyzer extends Serializable {

  
   def covert(rows: Iterator[(String, String, String, String)]):Iterator[(String, String, String, String)] = {
     
     rows
   }
  
   /* 
	 * @Title: startApp  
	 * @Description: TODO   启动  任务
	 * @param @param appName
	 * @param @param filePath    
	 * @return void    
	 * @throws 
	 * @Author  Lixc
	*/
    def startApp(appName:String = this.getClass().getName(),filePath:String) {
      
       val sparkConf = SparkUtil.getSparkStreamingConf(appName, "")
	   val ssc =  new StreamingContext(sparkConf, Seconds(300))
		  	  
       //获取 spark Dstream  
       val meta_data = ssc.textFileStream(filePath)
       
        //(START_TIME,O,D,PASS_NUM)
       val od_data =  meta_data.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(1),x(3),x(4),Math.abs(x(5).toInt).toString)))
         					   .persist(StorageLevel.MEMORY_AND_DISK_2)
         				
        od_data.mapPartitions(covert)
    }
  
}