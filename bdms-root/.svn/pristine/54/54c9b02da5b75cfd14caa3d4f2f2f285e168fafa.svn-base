package com.bdms.spark

import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Milliseconds
import java.util.Date

object SparkTest {
  
  private final val LOG = Logger.getLogger(this.getClass());
  private final val _CLIENT =TestMemcache.getInstance();
  private var num = 0 
  private var text:Array[String] = null;
  
  
  /**
   * ./bin/spark-submit  --class com.bdms.spark.SparkTest 
   * 					 --master  yarn-cluster  
   *       				 --jars /tmp/bdms-spark-0.0.1-SNAPSHOT.jar  
   *            		 --num-executors 20 
   *                     --driver-memory 4g  
   *                     --executor-memory 40g 
   *                     --executor-cores 10 /tmp/spark.jar
   */
  
	def main(args: Array[String]) {
	  
		//val system = ActorSystem("PutDataActor")
		//val pd = system.actorOf(Props[PutDataActor], name = "PutData")
    
    
	    val sparkConf = new SparkConf().setAppName("SparkTest").setMaster("yarn-cluster")
	   // sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
       // sparkConf.set("spark.kryo.registrator", "MyKryo");
	    sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
	    sparkConf.set("spark.streaming.concurrentJobs","10") //job的并行度  默认为 1 
	    sparkConf.set("spark.default.parallelism","20") //shuffle过程中 task的个数  默认为 8
	   // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
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
		
	   // sparkConf.set(key, value)
	    
	    ssc.checkpoint("checkpoint")
	    
	  // Set up the input DStream to read from Kafka (in parallel)
		
	   /* val kafkaParams:Map[ String , String ] = Map(
		    "zookeeper.connect" -> "hadoop-1:2181,hadoop-2:2181,hadoop-3:2181,hadoop-4:2181,hadoop-5:2181,hadoop-6:2181",
		    "group.id" -> "spark-streaming-test04",
		    "zookeeper.connection.timeout.ms" -> "1000000")*/
	    
		  val inputTopic = "test906"
		   
		    // 8个 reciver  每个上面配置 10个线程并行读取  
		 
		    val readParallelism  = 4
		   val streams = (1 to readParallelism ) map { _ =>
		  	KafkaUtils.createStream(ssc, "dswhhadoop-1:2181,dswhhadoop-2:2181,dswhhadoop-3:2181,dswhhadoop-4:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181","spark-streaming-test06", Map(inputTopic -> 20), StorageLevel.MEMORY_AND_DISK_SER)
		  	//.map(_._2.split(" ")).filter(_.length > 2).map(x=> "simID:" + x(0)+"#GIS_X:"+x(1)+"#GIS_Y:"+x(2) )
		  }
		
		  //val sparkProcessingParallelism = 20 // You'd probably pick a higher value than 1 in production.
		 val processingDStream =   ssc.union(streams).repartition(64) //重新分区
		
		 // unifiedStream.print
		 /*
		  processingDStream.foreach(rdd => {
		    
    			rdd.map(_._2.split(" "))
			    .filter(_.length > 2)
			    .map(x=> "simID:" + x(0)+"#GIS_X:"+x(1)+"#GIS_Y:"+x(2))
			    .foreachPartition(
			    	_.foreach(z =>{
		    			_CLIENT.add("ipp" + String.valueOf(num),z)
		    			num += 1 
					    if(num >= 20000){
					      num = 0; 
					      println("入库两万");
					      println("ipp1000 -- > " + _CLIENT.get("ipp1000"))
					    }
			    	})
			    )
    		})*/
		 
		 
		 processingDStream.foreach(rdd => {
		    
    			rdd.foreachPartition( 
			    	_.map(_._2.split(" ")).filter(_.length > 2).map(x=> ( x(0), "#GIS_X:"+x(1)+"#GIS_Y:"+x(2) )).foreach(z => _CLIENT.add(z._1,z._2))
			    )
			    rdd.unpersist()
    		})
    		
    		
		
		  // val sparkProcessingParallelism = 20 // You'd probably pick a higher value than 1 in production.
		 // val processingDStream =  unifiedStream.repartition(sparkProcessingParallelism) //重新分区
		  
		 // processingDStream.map(println)
		  
		  
		// 一个 reciver  80个消费线程
		 /*val line = KafkaUtils.createStream(ssc, "dswhhadoop-1:2181,dswhhadoop-2:2181,dswhhadoop-3:2181,dswhhadoop-4:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181","spark-streaming-test05", Map(inputTopic -> 80), StorageLevel.MEMORY_ONLY).map(_._2.split(" "))
		  .filter(_.length > 2).map(x=> "simID:" + x(0)+"#GIS_X"+x(1)+"#GIS_Y"+x(2) )
		  line.saveAsTextFiles("hdfs://dswhhadoop-2:8020/tmp/lixc/out5")*/
		  
		  ssc.start()
		  ssc.awaitTermination()
		  
		
	}
}