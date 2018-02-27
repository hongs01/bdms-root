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
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configuration
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import scala.io.Source

object GuiJDay {
  
   final val HCMSG_PATH = "/hc.properties"
  
  object Blaher extends Serializable {
	  
	 val zk =  "dswhhadoop-10,dswhhadoop-1,dswhhadoop-11,dswhhadoop-12,dswhhadoop-13,dswhhadoop-14,dswhhadoop-15,dswhhadoop-16," +
				"dswhhadoop-17,dswhhadoop-18,dswhhadoop-19,dswhhadoop-2,dswhhadoop-20,dswhhadoop-21,dswhhadoop-22,dswhhadoop-23," +
				"dswhhadoop-24,dswhhadoop-25,dswhhadoop-26,dswhhadoop-27,dswhhadoop-28,dswhhadoop-29,dswhhadoop-3,dswhhadoop-30," +
				"dswhhadoop-31,dswhhadoop-32,dswhhadoop-33,dswhhadoop-34,dswhhadoop-35,dswhhadoop-36,dswhhadoop-37,dswhhadoop-38," +
				"dswhhadoop-39,dswhhadoop-4,dswhhadoop-40,dswhhadoop-41,dswhhadoop-42,dswhhadoop-43,dswhhadoop-44,dswhhadoop-45," +
				"dswhhadoop-46,dswhhadoop-47,dswhhadoop-48,dswhhadoop-49,dswhhadoop-50,dswhhadoop-51,dswhhadoop-52,dswhhadoop-53," +
				"dswhhadoop-54,dswhhadoop-55,dswhhadoop-56,dswhhadoop-57,dswhhadoop-58,dswhhadoop-59,dswhhadoop-60,dswhhadoop-61," +
				"dswhhadoop-62,dswhhadoop-63,dswhhadoop-64,dswhhadoop-9";
	
	 def convert(triple:  Iterator[(String, String, String, String)]):Iterator[(ImmutableBytesWritable,Put)] = {
	     var p:Put = null
	     triple.map( z => {
			  p = new Put(Bytes.toBytes( z._1 + "-" + z._2 ))
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_ID"),Bytes.toBytes(z._1)) 
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("START_TIME"),Bytes.toBytes(z._2))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("ENTER_TIMES"),Bytes.toBytes(z._3))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("EXIT_TIMES"),Bytes.toBytes(z._4))
			  (new ImmutableBytesWritable, p)
	     })
		 
	  }
	  
	 def storeRegionToHbase(row: RDD[(String, String, String, String)]){
	    
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
				 hcd.setInMemory(true)
				 hcd.setTimeToLive(60*60*30)
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
			
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)

	  }

	
	 
	}
  
  
  def main(args: Array[String]) {
    
      //获取换乘站信息
	   val line = Source.fromInputStream(GuiJDay.getClass().getResourceAsStream(HCMSG_PATH), "UTF-8")
       val hcMSG =  line.getLines.map(_.split("=")).filter(_.length  > 1).map(x => (x(0),x(1))).toMap
       
       
  
       val sparkConf = new SparkConf().setAppName("GuijDay")//.setMaster("yarn-cluster")
	  
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
		
		  
		  //val sc = new SparkContext(sparkConf)
        
       	     
		//  lne.map(_.split(",")).collect.foreach(print)
		 
		  
		  val ssc =  new StreamingContext(sparkConf, Seconds(300))
  		  
		 // ssc.checkpoint("/tmp/lixc/hbase/checkpoint/")
		  	  
		 // val streams = ssc.fileStream[LongWritable, Text, TextInputFormat]("hdfs://dswhhadoop-1:8020/ftp/",{path: Path => path.getName().contains("FLUXNEW_SEG")  },true).map(_._2.toString())
		 // val kl_data =  streams.mapPartitions(_.map(_.split(",")))
       
       	 //获取 spark Dstream  并解析数据     station_id start_time in out
         val streams = ssc.textFileStream("hdfs://dswhhadoop-1:8020/ftp/").persist(StorageLevel.MEMORY_AND_DISK_2)
       
         //每五分钟的实时数据
         val kl_data =  streams.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(1),x(2),Math.abs(x(4).toInt/100).toString,Math.abs(x(5).toInt/100).toString)))
         
         //计算换乘站 数据
         val hc_data_tmp = kl_data.filter( x => !hcMSG.get(x._1).isEmpty ).map(x => (x._2 + "-hcz" + hcMSG.get(x._1).get,x._3+"-"+x._4))
         
         val hc_data =  hc_data_tmp.reduceByKey((x,y)=> {
           val xs = x.split("-")
           val ys = y.split("-")
          ( xs(0).toInt+ys(0).toInt) + "-" + ( xs(1).toInt+ys(1).toInt)
         } ).map(x => {
            val rows = x._1.split("-")
            val lines = x._2.split("-")
            (rows(1),rows(0),lines(0),lines(1))
         })
         
         
         //五分钟之内的计算总人数
         val count_data_tmp = kl_data.mapPartitions(_.map(x => ("count" + "-" + x._2  ,x._3+"-"+x._4)))
         
         val count_data = count_data_tmp.reduceByKey((x,y)=> {
           val xs = x.split("-")
           val ys = y.split("-")
          ( xs(0).toInt+ys(0).toInt) + "-" + ( xs(1).toInt+ys(1).toInt)
         } ).map(x => {
            val rows = x._1.split("-")
            val lines = x._2.split("-")
            (rows(0),rows(1),lines(0),lines(1))
         })
	     
         
         //数据汇总
         val result =  kl_data.union(hc_data).union(count_data)
         
         
         
	    //存储数据
		 result.foreachRDD(rdd => {
				Blaher.storeRegionToHbase(rdd)
			  	//rdd.collect.foreach(print)
			})
		 
		
		  ssc.start()
		  ssc.awaitTermination()
	
  }

}
