package com.bdms.spark.csdata

import com.bdms.spark.util.SparkUtil
import org.apache.spark.storage.StorageLevel
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import com.bdms.spark.util.HBaseUtil
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import scala.collection.mutable.ArrayBuffer
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import scala.reflect.ClassTag
import com.bdms.spark.util.SortNumberKey

class CSDataAnalyzer extends Serializable{

  
  val format = new SortNumberKey(9);
 
  def convert(triple:  Iterator[(String, String, String, String, String, String, String, String,String)]):Iterator[(ImmutableBytesWritable,Put)] = {

	  var p:Put = null
	  triple.map( z => {
	      p = new Put(Bytes.toBytes(z._2+"-"+z._1))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("time"),Bytes.toBytes(z._1)) 
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("area"),Bytes.toBytes(z._2))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("flowIn"),Bytes.toBytes(z._3))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("current"),Bytes.toBytes(z._4))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("flowOut"),Bytes.toBytes(z._5))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("topLeftPoint"),Bytes.toBytes(z._9))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("topRightPoint"),Bytes.toBytes(z._8))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("bottomRightPoint"),Bytes.toBytes(z._7))
		  p.add(Bytes.toBytes("cs"), Bytes.toBytes("bottomLeftPoint"),Bytes.toBytes(z._6))
			(new ImmutableBytesWritable, p)
	          })
	  }
	  
	 //存储Hbase
  def storeRegionToHbase(row: RDD[(String, String, String, String, String, String, String, String,String)],tableName:String){
	    
		    val hConf = HBaseUtil.getHConf
			
			//HBaseUtil.checkTableExist(hConf,tableName)
			
			//指定输出格式和输出表名
			val jobConf = new JobConf(hConf,this.getClass)
			jobConf.setOutputFormat(classOf[TableOutputFormat])
			jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
			jobConf.setNumMapTasks(60)
			jobConf.setNumReduceTasks(60)
			
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)

	  }
  
   /* 
	 * @Title: startApp  
	 * @Description: TODO   启动任务
	 * @param @param appName
	 * @param @param filePath    
	 * @return void    
	 * @throws 
	 * @Author  chenfeng
	*/
   def startApp(appName:String = this.getClass().getName(),filePath:String,tableName:String) {
      
       val sparkConf = SparkUtil.getSparkConf(appName)
	   val sc =  new SparkContext(sparkConf)
		  	  
       //获取 spark Dstream  
       val meta_data = sc.textFile(filePath)
              	
       val meta_sc_data = meta_data.map( _.split(";")).filter(_.length == 9)
                           .map(x => (x(0),x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))  
	
	   //存储到hbase
       storeRegionToHbase(meta_sc_data,tableName);
                        
	   sc.stop;
    }
}