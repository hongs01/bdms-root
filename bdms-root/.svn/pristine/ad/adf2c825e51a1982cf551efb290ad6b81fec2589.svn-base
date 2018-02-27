package com.bdms.spark.cassandra

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import java.util.Random
import org.apache.spark.streaming.kafka.KafkaUtils

object Spark_Cassandra extends Serializable {
  
  def main(args: Array[String]) {
    
	    val sparkConf = new SparkConf().setAppName("Spark_Cassandra").setMaster("yarn-cluster")
	    sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
	    sparkConf.set("spark.streaming.concurrentJobs","10") //job的并行度  默认为 1 
	    sparkConf.set("spark.default.parallelism","20") //shuffle过程中 task的个数  默认为 8
	    sparkConf.set("spark.streaming.unpersist", "true")
	    sparkConf.set("spark.cleaner.ttl", "120")
	    sparkConf.set("spark.rdd.compress", "true") //是否压缩
	    sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
	    sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
	    sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
	   // sparkConf.set("spark.streaming.blockQueueSize ", "10")  //  默认为10  队列是最多能容纳10个Block
	    sparkConf.set("spark.streaming.receiver.maxRate", "100000")  // 100000 / s 
	    sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "10000")  //  x/s
	    
	    val ssc =  new StreamingContext(sparkConf, Seconds(1))
		
	    
	    ssc.checkpoint("checkpoint")
	    
	    
	    val inputTopic = "test909"
	   
	    val readParallelism  = 4
	    val streams = (1 to readParallelism ) map { _ =>
	  		KafkaUtils.createStream(ssc, "dswhhadoop-1:2181,dswhhadoop-2:2181,dswhhadoop-3:2181,dswhhadoop-4:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181","spark-streaming-test06", Map(inputTopic -> 20), StorageLevel.MEMORY_AND_DISK_SER)
	    }
		
		  //val sparkProcessingParallelism = 20 // You'd probably pick a higher value than 1 in production.
		 val processingDStream =   ssc.union(streams).repartition(64) //重新分区
		 processingDStream.foreach(_.foreachPartition(putDataToCassendra.doPut))
    		
		
		  ssc.start()
		  ssc.awaitTermination()
		  
		
  }

  object putDataToCassendra extends Serializable{
    
    def doPut(data:Iterator[(String,String)]){
      
       val cqlServer = new CQLServer("DSwhHadoop-2","9160","person")
       val ra = new Random();
       var str:StringBuffer = new StringBuffer()
       str.append("BEGIN BATCH  ")
       
       var n = 0;
       
       data.map(_._2.split(" ")).filter(_.length > 6 ).foreach(z => {
        
			str.append("INSERT INTO persons (id, simId,gis_x,gis_y,name,phoneNum,address,time) VALUES (");
			str.append( "'" + System.currentTimeMillis() + ra.nextInt(Integer.MAX_VALUE) +  "',");
			str.append( "'" + z(0) +  "',");
			str.append( "'" + z(1) +  "',");
			str.append( "'" + z(2) +  "',");
			str.append( "'" + z(6) +  "',");
			str.append( "'" + z(4) +  "',");
			str.append( "'" + z(5) +  "',");
			str.append( "'" + z(3) +  "'");
			str.append(");");
		   
			 n += 1
			 if(n >= 50000){
				 str.append(" APPLY BATCH");
			     cqlServer.executeUpdate(str.toString())
			     str = new StringBuffer()
			     str.append("BEGIN BATCH  ")
			 }
      })
      
       str.append(" APPLY BATCH");
	   cqlServer.executeUpdate(str.toString())
	   str = null;
      
    }
  }
}