package com.bdms.spark.wifi

import org.apache.spark.Logging
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
import org.apache.spark.streaming.Minutes
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.dstream.DStream
import java.text.SimpleDateFormat
import java.util.Date
import org.apache.hadoop.hbase.client.Result
import com.bdms.hbse.enums.Wifi2Meta

class WIFIDataStore extends Serializable with Logging {
  
   //数据格式转换  
//   def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
//     
//         val cf = Bytes.toBytes("info");
//	     var p:Put = null
//	     var countRow:ArrayBuffer[(ImmutableBytesWritable,Put)] = new ArrayBuffer[(ImmutableBytesWritable,Put)]()
//	     triple.foreach( z => {
//	    	 	  
//	              p = new Put(Bytes.toBytes(z(2) + "-" + z(0)))
//				  p.add(cf, Bytes.toBytes("time"),Bytes.toBytes(z(0))) 
//				  p.add(cf, Bytes.toBytes("count"),Bytes.toBytes(z(1))) 
//				  p.add(cf, Bytes.toBytes("sid"),Bytes.toBytes(z(2))) 
//				 
//				 countRow += ( (new ImmutableBytesWritable, p) )
//	            })
//	            
//	            countRow.toIterator
//	            
//	  }
   
   
    //数据格式转换  
   def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
     
	   	 var countRow:ArrayBuffer[(ImmutableBytesWritable,Put)] = new ArrayBuffer[(ImmutableBytesWritable,Put)]()
         val cf = Bytes.toBytes("info")
	     var p:Put = null
	     var map = Map[String,(Int,Int)]()
	     var key = ""
	     var oldrec = null
	   	 var row:Result = null
	   
	     triple.foreach(x=>{
	       
	       key = x(2)+"-"+checkDate(x(0))
	       
	       if((0,0) == map.getOrElse(key,(0,0)))
	       {
	         map += (key->(x(1).toInt,1))
	       }
	       else
	       {
	         map += (key->(map.get(key).get._1+x(1).toInt,map.get(key).get._2+1))
	       }
	     })
	     
	     for((k,v)<- map)
	     {
	       p = new Put(Bytes.toBytes(k))
	       p.add(cf, Bytes.toBytes("time"),Bytes.toBytes(k.split("-")(1))) 
	       p.add(cf, Bytes.toBytes("sid"),Bytes.toBytes(k.split("-")(0)))
	       row = WifiHbaseUtil.getByRowKey(k, "wifi2","info")
	       if(0==row.size())
	       {
	         p.add(cf, Bytes.toBytes("count"),Bytes.toBytes(v._1.toString)) 
	         p.add(cf, Bytes.toBytes("num"),Bytes.toBytes(v._2.toString)) 
	        
	       }
	       else
	       {
	           p.add(cf, Bytes.toBytes("count"),Bytes.toBytes((v._1+new String(row.list().get(0).getValue()).toLong).toString)) 
	           p.add(cf, Bytes.toBytes("num"),Bytes.toBytes((v._2+new String(row.list().get(1).getValue()).toLong).toString)) 
	       }
	        countRow += ( (new ImmutableBytesWritable, p))
	    }
	     countRow.toIterator
	            
	  }
   
   def checkDate(str:String):String=
   {
     var s = "";
     val dnm:Long = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
     val getFofS = dnm%3600
     val getMofS = getFofS%300
     val getMs = getFofS/300
     if(getMofS ==0) 
       s=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(dnm))
     else 
       s=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(dnm+300-getFofS%300))
     s
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
//    val ssc = new StreamingContext(conf,Seconds(10))
    //A batch of 5 minutes
    val ssc = new StreamingContext(conf,Minutes(5));
    
    //读取 wifi信息的原始数据
    val  fileData =  ssc.socketTextStream(socketHost, socketPort.toInt)
    
    fileData.print
  
    //过滤标错误数据
    val metaData = fileData.map( _.split(",")).filter(_.length == 3 )
    
    //存储到hbase
    metaData.foreachRDD( rdd => {
      
      storeRegionToHbase(rdd,tableName);
      
    })
    
    
    
    ssc.start
    ssc.awaitTermination
    
  }

}