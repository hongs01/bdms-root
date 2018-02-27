package com.bdms.spark.sp

import com.bdms.spark.util.SparkUtil
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.Seconds
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import com.bdms.spark.util.HBaseUtil
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.mapreduce.OutputFormat
import org.apache.hadoop.hbase.client.Mutation
import scala.collection.mutable.ArrayBuffer


class ImageMetaDataStore extends Serializable {

  
    //数据格式转换  
   def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
     
         val cf = Bytes.toBytes("metadata");
	     var p:Put = null
	     var time:String = null;
	     var countRow:ArrayBuffer[(ImmutableBytesWritable,Put)] = new ArrayBuffer[(ImmutableBytesWritable,Put)]()
	     
	     triple.foreach( z => {
	    	 	  time = z(0);
	              p = new Put(Bytes.toBytes(z(1) + "-" + z(0)))
				  p.add(cf, Bytes.toBytes("timeStamp"),Bytes.toBytes(z(0))) 
				  p.add(cf, Bytes.toBytes("cameraId"),Bytes.toBytes(z(1))) 
				  p.add(cf, Bytes.toBytes("peopleNum"),Bytes.toBytes(z(2))) 
				  p.add(cf, Bytes.toBytes("densityLevel"),Bytes.toBytes(z(3))) 
				  p.add(cf, Bytes.toBytes("densityImage"),Bytes.toBytes(z(4))) 
				  p.add(cf, Bytes.toBytes("groupNum"),Bytes.toBytes(z(5))) 
				  p.add(cf, Bytes.toBytes("groupImage"),Bytes.toBytes(z(6))) 
				  p.add(cf, Bytes.toBytes("warnLevel"),Bytes.toBytes(z(7))) 
				  p.add(cf, Bytes.toBytes("wrnImage"),Bytes.toBytes(z(8)))
				 // p.add(cf, Bytes.toBytes("reserved"),Bytes.toBytes(z(8))) 
				 countRow += ( (new ImmutableBytesWritable, p) )
	            })
	            
	             p = new Put(Bytes.toBytes("00000000"))
				 p.add(cf, Bytes.toBytes("timeStamp"),Bytes.toBytes(time)) 
				 countRow += ( (new ImmutableBytesWritable, p) )
	            
	             countRow.toIterator
	            
	  }
	  
	 //存储Hbase
	 def storeRegionToHbase(row: RDD[Array[String]],tableName:String){
	    
		    val hConf = HBaseUtil.getHConf
			
			//指定输出格式和输出表名
			hConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
		    hConf.setClass("mapreduce.job.outputformat.class", classOf[TableOutputFormat[String]], classOf[OutputFormat[String,Mutation]])
			
			row.mapPartitions(convert).saveAsNewAPIHadoopDataset(hConf)

	  }
  
  
  def startApp(appName:String = this.getClass().getName(),tableName:String,socketHost:String,socketPort:String){
   
    val conf = SparkUtil.getSparkStreamingConf(appName)
    val ssc = new StreamingContext(conf,Seconds(1))
    
    //读取 图片信息的原始数据
    val  fileData =  ssc.socketTextStream(socketHost, socketPort.toInt)
    
    fileData.print
  
    //过滤标错误数据
    val metaData = fileData.map( _.split(",")).filter(_.length == 9 )
    
    //存储到hbase
    metaData.foreachRDD( rdd => {
      
      storeRegionToHbase(rdd,tableName);
    })
    
    
    
    ssc.start
    ssc.awaitTermination
    
  }
}