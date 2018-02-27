package com.bdms.spark.gj

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.regionserver.BloomType
import java.io.IOException
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._

object GuiJTest {
  
  object Blaher extends Serializable {
	  
	  
	  val zk =  "dswhhadoop-10,dswhhadoop-1,dswhhadoop-11,dswhhadoop-12,dswhhadoop-13,dswhhadoop-14,dswhhadoop-15,dswhhadoop-16," +
				"dswhhadoop-17,dswhhadoop-18,dswhhadoop-19,dswhhadoop-2,dswhhadoop-20,dswhhadoop-21,dswhhadoop-22,dswhhadoop-23," +
				"dswhhadoop-24,dswhhadoop-25,dswhhadoop-26,dswhhadoop-27,dswhhadoop-28,dswhhadoop-29,dswhhadoop-3,dswhhadoop-30," +
				"dswhhadoop-31,dswhhadoop-32,dswhhadoop-33,dswhhadoop-34,dswhhadoop-35,dswhhadoop-36,dswhhadoop-37,dswhhadoop-38," +
				"dswhhadoop-39,dswhhadoop-4,dswhhadoop-40,dswhhadoop-41,dswhhadoop-42,dswhhadoop-43,dswhhadoop-44,dswhhadoop-45," +
				"dswhhadoop-46,dswhhadoop-47,dswhhadoop-48,dswhhadoop-49,dswhhadoop-50,dswhhadoop-51,dswhhadoop-52,dswhhadoop-53," +
				"dswhhadoop-54,dswhhadoop-55,dswhhadoop-56,dswhhadoop-57,dswhhadoop-58,dswhhadoop-59,dswhhadoop-60,dswhhadoop-61," +
				"dswhhadoop-62,dswhhadoop-63,dswhhadoop-64,dswhhadoop-9";
	   
	  
	 def convert(triple:  Iterator[Array[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
	     var p:Put = null
	     triple.map( z => {
			  p = new Put(Bytes.toBytes( z(2) + "-" + z(1) ))
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("LINE_ID"),Bytes.toBytes(z(0))) 
			  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_ID"),Bytes.toBytes(z(1))) 
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("START_TIME"),Bytes.toBytes(z(2)))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("END_TIME"),Bytes.toBytes(z(3)))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("ENTER_TIMES"),Bytes.toBytes((z(4).toInt/100).toString))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("EXIT_TIMES"),Bytes.toBytes((z(5).toInt/100).toString))
			  (new ImmutableBytesWritable, p)
	     })
		 
	  }
	  
	 def storeRegionToHbase(row: RDD[Array[String]]){
	    
		    val hConf = HBaseConfiguration.create()
		    hConf.set("hbase.zookeeper.quorum", zk)
			hConf.set("hbase.zookeeper.property.clientPort", "2181") 
			hConf.set("hbase.rootdir","hdfs://dswhhadoop-1:8020/apps/hbase/data")
			
			val tabaleName = "dsgj"
			
			val admin = new HBaseAdmin(hConf);
			if(!admin.tableExists(tabaleName)){
			    val htd = new HTableDescriptor(TableName.valueOf(tabaleName));
				 
				 val hcd = new HColumnDescriptor("luxnew")
				 hcd.setMaxVersions(1);
				 hcd.setBloomFilterType(BloomType.ROWCOL)
				 htd.addFamily( hcd);
				 try {
					 admin.createTable(htd);
				 } catch  {
				 	case e : IOException => e.printStackTrace();
				 }finally {
					 	admin.close();
				 	}
			}
			
			//指定输出格式和输出表名
			val jobConf = new JobConf(hConf,this.getClass)
			jobConf.setOutputFormat(classOf[TableOutputFormat])
			jobConf.set(TableOutputFormat.OUTPUT_TABLE,tabaleName)
			jobConf.setNumMapTasks(60)
			jobConf.setNumReduceTasks(60)
			
			//hConf.set(TableOutputFormat.OUTPUT_TABLE,"testGIS")
			//hConf.set(TableOutputFormat.QUORUM_ADDRESS,"dswhhadoop-4,dswhhadoop-3,dswhhadoop-2,dswhhadoop-5,dswhhadoop-6,dswhhadoop-7,dswhhadoop-8,dswhhadoop-1")
			//hConf.set(TableOutputFormat.QUORUM_PORT,"2181")
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)
		   // row.foreachPartitionAsync(Painting.paint(paintIndex,_))
	  }
	
	}
  
  
  def main(args: Array[String]) {
  
       val sparkConf = new SparkConf().setAppName("SparkTest")//.setMaster("yarn-cluster")
	   val ZKC =  "dswhhadoop-10:2181,dswhhadoop-1:2181,dswhhadoop-11:2181,dswhhadoop-12:2181,dswhhadoop-13:2181,dswhhadoop-14:2181,dswhhadoop-15:2181,dswhhadoop-16:2181," +
					"dswhhadoop-17:2181,dswhhadoop-18:2181,dswhhadoop-19:2181,dswhhadoop-2:2181,dswhhadoop-20:2181,dswhhadoop-21:2181,dswhhadoop-22:2181,dswhhadoop-23:2181," +
					"dswhhadoop-24:2181,dswhhadoop-25:2181,dswhhadoop-26:2181,dswhhadoop-27:2181,dswhhadoop-28:2181,dswhhadoop-29:2181,dswhhadoop-3:2181,dswhhadoop-30:2181," +
					"dswhhadoop-31:2181,dswhhadoop-32:2181,dswhhadoop-33:2181,dswhhadoop-34:2181,dswhhadoop-35:2181,dswhhadoop-36:2181,dswhhadoop-37:2181,dswhhadoop-38:2181," +
					"dswhhadoop-39:2181,dswhhadoop-4:2181,dswhhadoop-40:2181,dswhhadoop-41:2181,dswhhadoop-42:2181,dswhhadoop-43:2181,dswhhadoop-44:2181,dswhhadoop-45:2181," +
					"dswhhadoop-46:2181,dswhhadoop-47:2181,dswhhadoop-48:2181,dswhhadoop-49:2181,dswhhadoop-50:2181,dswhhadoop-51:2181,dswhhadoop-52:2181,dswhhadoop-53:2181," +
					"dswhhadoop-54:2181,dswhhadoop-55:2181,dswhhadoop-56:2181,dswhhadoop-57:2181,dswhhadoop-58:2181,dswhhadoop-59:2181,dswhhadoop-60:2181,dswhhadoop-61:2181," +
					"dswhhadoop-62:2181,dswhhadoop-63:2181,dswhhadoop-64:2181,dswhhadoop-9:2181";
				  
		  // sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		  // sparkConf.set("spark.kryo.registrator", "MyKryo");
		  sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
		  sparkConf.set("spark.streaming.concurrentJobs","4") //job的并行度  默认为 1 
		  sparkConf.set("spark.default.parallelism","240") //shuffle过程中 task的个数  默认为 8
		  // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
		  sparkConf.set("spark.streaming.unpersist", "true")
		  sparkConf.set("spark.cleaner.ttl", "1200")
		 // sparkConf.set("spark.rdd.compress", "true") //是否压缩  用时间 换空间
		  sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
		  sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
		  //sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
		  sparkConf.set("spark.streaming.blockQueueSize ", "20")  //  默认为10  队列是最多能容纳10个Block
		  sparkConf.set("spark.streaming.receiver.maxRate", "2000")  // 100000 / s  8000
		 // sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "2000")  //  x/s
		  
		  sparkConf.set("spark.yarn.submit.file.replication", "1")  //  提交的jar文件  的副本数  默认为 3
		  sparkConf.set("spark.yarn.containerLauncherMaxThreads", "60")  //  container中的线程数 默认为 25 
		 // sparkConf.set("spark.yarn.driver.memoryOverhead", "1024")  // 堆外内存  默认 384
		 // sparkConf.set("spark.yarn.executor.memoryOverhead", "2048")  //  默认  384 
		  
		  //sparkConf.set("spark.shuffle.memoryFraction", "0.3")  //  （1.1后默认为0.2）决定了当Shuffle过程中使用的内存达到总内存多少比例的时候开始Spill
		//  sparkConf.set("spark.storage.memoryFraction", "0.85")  //  后默认为0.67  即 默认使用 内存的2/3来缓存数据
		 
		 // sparkConf.set("spark.executor.extraJavaOptions", "-XX:+UseConcMarkSweepGC")  
		  
		  val ssc =  new StreamingContext(sparkConf, Seconds(60))
  		  
		 // ssc.checkpoint("/tmp/lixc/hbase/checkpoint/")
		  
		  	  
		  val streams = ssc.textFileStream("hdfs://dswhhadoop-1:8020/dsftp/")
		  val kl_data =  streams.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6 && x(0).length() < 5 ))
		  
		  kl_data.foreachRDD(rdd => {
					    Blaher.storeRegionToHbase(rdd)
				    })
		 
		
		  ssc.start()
		  ssc.awaitTermination()

  }

}