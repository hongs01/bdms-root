package com.bdms.spark.gj

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
import org.apache.spark.Logging
import java.nio.file._

class GuiJODOneDayDataAnalyzer extends Serializable with Logging{

  
  val format = new SortNumberKey(4);
  
  def convert(triple:  Iterator[(String, String,String)]):Iterator[(ImmutableBytesWritable,Put)] = {
		 
		 
		 
	     var p:Put = null
	     triple.map( z => {
	              p = new Put(Bytes.toBytes((z._2.substring(0,8))+z._1))
				  p.setWriteToWAL(false)
				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("OD"),Bytes.toBytes(z._2.substring(9)))  
				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("PASS_NUM"),Bytes.toBytes(z._3)) 
				  (new ImmutableBytesWritable, p)
	            })
	  }
	  
  
   //存储Hbase
	 def storeRegionToHbase(row: RDD[(String, String,String)],tableName:String){
	    
		    val hConf = HBaseUtil.getHConf
			
			//指定输出格式和输出表名
			val jobConf = new JobConf(hConf,this.getClass)
			jobConf.setOutputFormat(classOf[TableOutputFormat])
			jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
			jobConf.setNumMapTasks(60)
			jobConf.setNumReduceTasks(60)
			
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)

	  }
	
  def startApp(appName:String = this.getClass().getName(),filePath:String,tableName:String) {
     
      val sparkConf = SparkUtil.getSparkConf(appName)
	  val sc =  new SparkContext(sparkConf)
      val sdf = new SimpleDateFormat("yyyyMMdd");
      val fileName = "OD_DAY_" + sdf.format(new Date())
       //获取 spark Dstream 
     // val filePath1 = filePath+"OD_DAY_20150707"
      val meta_data = sc.textFile(filePath + fileName)
      
      val meta_od_data =  meta_data.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(0)+"-"+x(3)+"-"+x(4),Math.abs(x(5).toInt))))
         					   .persist(StorageLevel.MEMORY_AND_DISK_2)
      
      val od_data = meta_od_data.reduceByKey(_+_).map(x=>(x._2,x._1)).sortByKey(false).top(1000)
	
		
	  val result:ArrayBuffer[(String,String,String)] = new ArrayBuffer[(String,String,String)]()
		
	  for( i <- 0 to (od_data.length -1) ){
	  	   result += ((format.getKey(i),od_data(i)._2,od_data(i)._1.toString));
	  	   
	  }					  
	  val a = result.toArray
	  storeRegionToHbase(sc.parallelize(a),tableName)
	  sc.stop;
    }
 
}