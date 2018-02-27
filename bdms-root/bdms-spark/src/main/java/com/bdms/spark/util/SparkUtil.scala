package com.bdms.spark.util

import org.apache.spark.SparkConf


/**
  * Description:
  * 		负责spark 公共配置的初始化操作。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-7-28下午5:47:19            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object SparkUtil extends Serializable {
  
   //初始化sparkConf
   def getSparkConf( appName:String ,master:String = "yarn") : SparkConf = {
     
          val sparkConf = new SparkConf().setAppName(appName)//.setMaster(master)
	  
		  sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
		  //sparkConf.set("spark.akka.timeout","300");
          
         // sparkConf.set("spark.network.timeout","300");
		 
		  sparkConf.set("spark.cleaner.ttl", "3600")
		  
		  sparkConf.set("spark.default.parallelism","8") //shuffle过程中 task的个数  默认为 8
		 // sparkConf.set("spark.rdd.compress", "true") //是否压缩  用时间 换空间
		  sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
		  sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
		  
		  sparkConf.set("spark.task.maxFailures","8");
          
		  sparkConf.set("spark.yarn.submit.file.replication", "1")  //  提交的jar文件  的副本数  默认为 3
		 // sparkConf.set("spark.yarn.containerLauncherMaxThreads", "60")  //  container中的线程数 默认为 25 
		  sparkConf.set("spark.yarn.max.executor.failures","100");
		  
		  //解决yarn-cluster模式下 对处理  permGen space oom异常很有用
		  //sparkConf.set("spark.yarn.am.extraJavaOptions", "-Xms512m -Xmx1024m")
		  //sparkConf.set("spark.driver.extraJavaOptions", "")
		  //sparkConf.set("spark.executor.extraJavaOptions", "-XX:PermSize=512m -XX:MaxPermSize=512m")

		  sparkConf
     
   }
   
   
   def getSparkStreamingConf( appName:String ,master:String = "yarn" ) : SparkConf = {
     
      val sparkConf = getSparkConf(appName,master)
	  
		 // sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
		  sparkConf.set("spark.streaming.concurrentJobs","1") //job的并行度  默认为 1 
		  //sparkConf.set("spark.default.parallelism","60") //shuffle过程中 task的个数  默认为 8
		  // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
		  sparkConf.set("spark.streaming.unpersist", "true")
		//  sparkConf.set("spark.cleaner.ttl", "1200")
		 // sparkConf.set("spark.rdd.compress", "true") //是否压缩  用时间 换空间
		  //sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
		 // sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
		 // sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
		 // sparkConf.set("spark.streaming.blockQueueSize ", "10")  //  默认为10  队列是最多能容纳10个Block
		  sparkConf.set("spark.streaming.receiver.maxRate", "200000")  // 100000 / s  8000
		 // sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "2000")  //  x/s
		 // sparkConf.set("spark.yarn.submit.file.replication", "1")  //  提交的jar文件  的副本数  默认为 3
		 // sparkConf.set("spark.yarn.containerLauncherMaxThreads", "60")  //  container中的线程数 默认为 25 
		  
		  sparkConf
     
   }

}