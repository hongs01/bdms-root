package com.bdms.spark.hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.HTableDescriptor
import java.io.IOException
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.HTable
import org.apache.spark.SparkConf
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.hadoop.hbase.client.Put
import org.apache.log4j.Logger
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.hadoop.hbase.util.Bytes
import java.util.Random
import org.apache.hadoop.hbase.client.HTablePool
import java.util.ArrayList
import java.util.HashMap
import scala.Array
import scala.collection.mutable.ArrayBuffer
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._
import org.apache.spark.streaming.Duration
import org.apache.spark.rdd.RDD
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.client.HConnectionManager
import org.apache.hadoop.hbase.regionserver.KeyPrefixRegionSplitPolicy
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm
import org.apache.spark.sql.execution.Distinct
import org.apache.zookeeper.ZooKeeper
import java.net.InetAddress
import org.apache.hadoop.hbase.regionserver.BloomType
import java.awt.image.BufferedImage
import java.io.File
import java.awt.Graphics
import java.awt.Color
import java.io.FileOutputStream
import javax.imageio.ImageIO
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import java.text.SimpleDateFormat
import java.util.Date
import org.apache.hadoop.fs.FSDataInputStream


object HbaseTest extends Serializable {

  private final val LOG = Logger.getLogger(this.getClass());
  private var num = 0 
  private var text:Array[String] = null;
  
  /**
   * ./bin/spark-submit  
   * 		--class com.bdms.saprk.hbase.HbaseTest 
   * 		--master  yarn-cluster  
   * 		--jars /tmp/bdms-spark-0.0.1-SNAPSHOT.jar  
   * 		--num-executors 24  
   *		--driver-memory 4g  
   * 		--executor-memory 46g 
   * 		--executor-cores 8 /tmp/spark.jar 
   */
  
  /**
   * (120.85  122.20)
   * (30.67  31.88)
   */
  
	object Blaher extends Serializable {
	  
	  
	  val zk =  "dswhhadoop-10,dswhhadoop-1,dswhhadoop-11,dswhhadoop-12,dswhhadoop-13,dswhhadoop-14,dswhhadoop-15,dswhhadoop-16," +
				"dswhhadoop-17,dswhhadoop-18,dswhhadoop-19,dswhhadoop-2,dswhhadoop-20,dswhhadoop-21,dswhhadoop-22,dswhhadoop-23," +
				"dswhhadoop-24,dswhhadoop-25,dswhhadoop-26,dswhhadoop-27,dswhhadoop-28,dswhhadoop-29,dswhhadoop-3,dswhhadoop-30," +
				"dswhhadoop-31,dswhhadoop-32,dswhhadoop-33,dswhhadoop-34,dswhhadoop-35,dswhhadoop-36,dswhhadoop-37,dswhhadoop-38," +
				"dswhhadoop-39,dswhhadoop-4,dswhhadoop-40,dswhhadoop-41,dswhhadoop-42,dswhhadoop-43,dswhhadoop-44,dswhhadoop-45," +
				"dswhhadoop-46,dswhhadoop-47,dswhhadoop-48,dswhhadoop-49,dswhhadoop-50,dswhhadoop-51,dswhhadoop-52,dswhhadoop-53," +
				"dswhhadoop-54,dswhhadoop-55,dswhhadoop-56,dswhhadoop-57,dswhhadoop-58,dswhhadoop-59,dswhhadoop-60,dswhhadoop-61," +
				"dswhhadoop-62,dswhhadoop-63,dswhhadoop-64,dswhhadoop-9";
	  
	  //不经过坐标分区计算存hbase
	  def blah(row: Iterator[(String,String)]) { 
	    
	  //  println( "blah-----------------------------------------------------------------------")
	    
	    val ra = new Random();
	    val hConf = HBaseConfiguration.create()
	    hConf.set("hbase.zookeeper.quorum", zk)
		hConf.set("hbase.zookeeper.property.clientPort", "2181") 
		hConf.set("hbase.rootdir","hdfs://dswhhadoop-2:8020/apps/hbase/data")
		
		val admin = new HBaseAdmin(hConf);
		if(!admin.tableExists("test910")){
		  val htd = new HTableDescriptor(TableName.valueOf("test910"));
		  htd.addFamily(new HColumnDescriptor("position"));
		  htd.addFamily(new HColumnDescriptor("abstruct"));
		  try {
				admin.createTable(htd);
			} catch  {
				case e : IOException => e.printStackTrace();
			}
		}
		
		val table =new HTable(hConf,"test910");
	    table.setAutoFlush(false)
	    table.setWriteBufferSize(7*1024*1024)
		//val table = HBaseServer.getHBaseServer().getHTable()
		
		if(table == null){
		  
		  println("没有获得HTable实例。");
		  System.exit(-1)
		}
		
	    var list = new ArrayList[Put]();
	    
	    var thePut:Put = null;
	    
	    row.map(_._2.split(" ")).filter(_.length > 6).foreach(x => {
	      
	      thePut = new Put(Bytes.toBytes( System.currentTimeMillis() + ra.nextInt(Integer.MAX_VALUE))) 
	      thePut.setWriteToWAL(false)
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("simId"),Bytes.toBytes(x(0))) 
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("gis_x"), Bytes.toBytes(x(1))) 
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("gis_y"), Bytes.toBytes(x(2))) 
	    
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("time"),Bytes.toBytes(x(3))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("phoneNoPer"), Bytes.toBytes(x(4))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("address"), Bytes.toBytes(x(5))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("name"),Bytes.toBytes(x(6))) 
	      
	      list.add(thePut)
	      if(list.size() > 100000){
	        table.put(list)
	        list = new ArrayList[Put]();
	      }
	      
	    })
	    
	    if(list.size() > 0){
	        table.put(list)
	        list = null;
	    }
		
	  } 
	  
	  def blah2(row: RDD[(String,String)]) { 
		  
		  //  println( "blah-----------------------------------------------------------------------")
		  //val ra = new Random();
		  val hConf = HBaseConfiguration.create()
		  hConf.set("hbase.zookeeper.quorum", zk)
		  hConf.set("hbase.zookeeper.property.clientPort", "2181") 
		  hConf.set("hbase.rootdir","hdfs://dswhhadoop-2:8020/apps/hbase/data")
				  
				  
		/*  val admin = new HBaseAdmin(hConf);
		  if(!admin.tableExists("test910")){
			  val htd = new HTableDescriptor(TableName.valueOf("test910"));
			  htd.addFamily(new HColumnDescriptor("position"));
			  htd.addFamily(new HColumnDescriptor("abstruct"));
			  try {
				  admin.createTable(htd);
			  } catch  {
			  case e : IOException => e.printStackTrace();
			  }
		  }
		  */
		  //指定输出格式和输出表名
			val jobConf = new JobConf(hConf,this.getClass)
			jobConf.setOutputFormat(classOf[TableOutputFormat])
			jobConf.set(TableOutputFormat.OUTPUT_TABLE,"test910")
			
			row.map(_._2.split(" ")).filter(_.length > 6).map(convert2).saveAsHadoopDataset(jobConf)
		  
	  } 
	  
	  //计算分区
	  def regionCalculate(row: Iterator[(String,String)]):Iterator[(String,Long)] = {
	     
	   // val region = new Regions(120.85,122.2,12,30.666667,31.883333,10)
	    //val res: ArrayBuffer[(String,Int)] = new ArrayBuffer[(String,Int)]()
	     var lat:String = null
	     var lng:String = null
	     row.map(_._2.split(" ")).filter(_.length > 6).map(x => {
	       lat = x(1)
	       lng = x(2)
	       (lat.substring(0, lat.length()-2) +";"+ lng.substring(0, lat.length()-2),1L)
	       })
	   
	  }

	 def convert2(x: Array[String]) = {
	  
		 val  thePut = new Put(Bytes.toBytes(x(0))) 
	      thePut.setWriteToWAL(false)
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("simId"),Bytes.toBytes(x(0))) 
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("gis_x"), Bytes.toBytes(x(1))) 
	      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("gis_y"), Bytes.toBytes(x(2))) 
	    
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("time"),Bytes.toBytes(x(3))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("phoneNoPer"), Bytes.toBytes(x(4))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("address"), Bytes.toBytes(x(5))) 
	      thePut.add(Bytes.toBytes("abstruct"), Bytes.toBytes("name"),Bytes.toBytes(x(6)))
	      (new ImmutableBytesWritable, thePut)
	  }
	
	 def convert(partitionIndex:Int,triple:  Iterator[(String,Long)]):Iterator[(ImmutableBytesWritable,Put)] = {
	   
	     var p:Put = null
	     var arr:Array[String] = null
	     var n = 0;
	     triple.map( z => {
	    	  arr = z._1.split(";")
	    	  n = n + 1
			  p = new Put(Bytes.toBytes(partitionIndex + "-" + n ))
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("position"), Bytes.toBytes("lng"),Bytes.toBytes(arr(0))) 
			  p.add(Bytes.toBytes("position"), Bytes.toBytes("lat"),Bytes.toBytes(arr(1))) 
			  p.add(Bytes.toBytes("position"),Bytes.toBytes("count"),Bytes.toBytes(z._2.toString))
			  (new ImmutableBytesWritable, p)
	     })
		 
	  }
	 
	  def convert3(partitionIndex:Int,triple:  Iterator[(String,Long)]):Iterator[(Put)] = {
	   
	     var p:Put = null
	     var arr:Array[String] = null
	     var n = 0;
	     triple.map( z => {
	    	  arr = z._1.split(";")
	    	  n = n + 1
			  p = new Put(Bytes.toBytes(partitionIndex + "-" + n ))
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("position"), Bytes.toBytes("lng"),Bytes.toBytes(arr(0))) 
			  p.add(Bytes.toBytes("position"), Bytes.toBytes("lat"),Bytes.toBytes(arr(1))) 
			  p.add(Bytes.toBytes("position"),Bytes.toBytes("count"),Bytes.toBytes(z._2.toString))
			  (p)
	     })
		 
	  }
	  
	 def storeRegionToHbase(row: RDD[(String,Long)]){
	    
		    val hConf = HBaseConfiguration.create()
		    hConf.set("hbase.zookeeper.quorum", zk)
			hConf.set("hbase.zookeeper.property.clientPort", "2181") 
			hConf.set("hbase.rootdir","hdfs://dswhhadoop-1:8020/apps/hbase/data")
			
			val tabaleName = "testGIS002"
			
			val admin = new HBaseAdmin(hConf);
			if(!admin.tableExists(tabaleName)){
			    val htd = new HTableDescriptor(TableName.valueOf(tabaleName));
			    //htd.setValue(HTableDescriptor.SPLIT_POLICY, classOf[KeyPrefixRegionSplitPolicy].getName())
			    // htd.setValue("KeyPrefixRegionSplitPolicy.prefix_length", "2");
				
			    //添加聚合协助处理器
			   // htd.addCoprocessor("org.apache.hadoop.hbase.client.coprocessor.AggregationClient")
			  
				 
				 val hcd = new HColumnDescriptor("position")
				 hcd.setMaxVersions(1);
				 hcd.setBloomFilterType(BloomType.ROWCOL)
				 hcd.setInMemory(true);
				 
				 hcd.setTimeToLive(1800) // 值的  存活  时间     半小时
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
			jobConf.setNumMapTasks(120)
			jobConf.setNumReduceTasks(120)
			
			//hConf.set(TableOutputFormat.OUTPUT_TABLE,"testGIS")
			//hConf.set(TableOutputFormat.QUORUM_ADDRESS,"dswhhadoop-4,dswhhadoop-3,dswhhadoop-2,dswhhadoop-5,dswhhadoop-6,dswhhadoop-7,dswhhadoop-8,dswhhadoop-1")
			//hConf.set(TableOutputFormat.QUORUM_PORT,"2181")
			row.mapPartitionsWithIndex(convert).saveAsHadoopDataset(jobConf)
		   // row.foreachPartitionAsync(Painting.paint(paintIndex,_))
	  }
	
	 def storeRegionToHbase2(row: Iterator[(Int,String,Long)]){
		  
		   val table = getTable("testGIS002")
		   
			if(table == null){
			  
			  println("没有获得HTable实例。");
			  System.exit(-1)
			}
			
		    var list = new ArrayList[Put]();
		    
		    var thePut:Put = null;
		    var arr:Array[String] = null
		    row.foreach(x => {
		      
		      arr = x._2.split(";")
		      thePut = new Put(Bytes.toBytes(x._1.toString)) 
		      thePut.setWriteToWAL(false)
		      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("lng"),Bytes.toBytes(arr(0))) 
		      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("lat"),Bytes.toBytes(arr(1))) 
		      thePut.add(Bytes.toBytes("position"), Bytes.toBytes("count"), Bytes.toBytes(x._3.toString)) 
		      
		      list.add(thePut)
		      if(list.size() > 10000){
		        table.put(list)
		        list = new ArrayList[Put]();
		      }
		      
		    })
		    
		    if(list.size() > 0){
		        table.put(list)
		        list = null;
		    }
		    
		    table.flushCommits()
		    table.close();
		  
	  }
	 
	 def storeRegionToHbase3(row: Iterator[(Put)]){
	   
	   val table = getTable("testGIS002")
		 
		 if(table == null){
			 
			 println("没有获得HTable实例。");
			 System.exit(-1)
		 }
		 
		 var list = new ArrayList[Put]();
		 row.foreach(x => {
		   
			 list.add(x)
			 if(list.size() > 10000){
				 table.put(list)
				 list = new ArrayList[Put]();
			 }
			 
		 })
				 
		 if(list.size() > 0){
			 table.put(list)
			 list = null;
		 }
		 
		 table.flushCommits()
		 table.close();
		 
	 }
	  
	 private def getTable(name:String,checkExsits:Boolean = true): org.apache.hadoop.hbase.client.HTableInterface = {
	    
	    val hConf = HBaseConfiguration.create()
	    hConf.set("hbase.zookeeper.quorum", zk)
	    hConf.set("hbase.zookeeper.property.clientPort", "2181") 
	    hConf.set("hbase.rootdir","hdfs://dswhhadoop-1:8020/apps/hbase/data")
				 
	    val hconn = HConnectionManager.createConnection(hConf)
	    
	    if(checkExsits){
	    
		    val admin = new HBaseAdmin(hConf);
		    
		    if(!admin.tableExists(name)){
				 val htd = new HTableDescriptor(TableName.valueOf(name));
				 //htd.setValue(HTableDescriptor.SPLIT_POLICY, classOf[KeyPrefixRegionSplitPolicy].getName())
				 htd.setValue("KeyPrefixRegionSplitPolicy.prefix_length", "2");
				 
				 val hcd = new HColumnDescriptor("position")
				 hcd.setMaxVersions(1);
				 hcd.setBloomFilterType(BloomType.ROWCOL)
				 hcd.setInMemory(true);
				 
				 hcd.setTimeToLive(300) // 值的  存活  时间 
				 htd.addFamily( hcd);
				 try {
					 admin.createTable(htd);
				 } catch  {
				 case e : IOException => e.printStackTrace();
				 }
		    }
	    
	    }
	    val table = hconn.getTable(Bytes.toBytes(name));
	    table.setAutoFlush(false)
	    table.setWriteBufferSize(7*1024*1024)
	    table
	  }
	
	 
	}
	
	object Painting extends Serializable{
	  
	  private final val simple = new SimpleDateFormat("yyyyMMddHHmm");
	  
	  def paint(rows : RDD[(String,Long)]){
	    
		    val conf = new Configuration();
			val hdfs = FileSystem.get(conf);
			
			val path = new Path("/tmp/png/" + simple.format(new Date()) + ".png")
		    
		    var image:BufferedImage = new BufferedImage(14000, 4000, BufferedImage.TYPE_INT_ARGB);
			var in:FSDataInputStream = null
			try{
				if (hdfs.exists(path)) {
					  in = hdfs.open(path)
					  image = ImageIO.read(in);
				}
			}catch{
			    case e: IOException => e.printStackTrace();
			}finally {
				if(in != null){
				   in.close()
				   hdfs.delete(path)
				}
            }
	
			val g:Graphics = image.getGraphics();
			
			g.setColor(Color.red);
			
			val rand = new Random();
			/*
			for (i <- 0 to 9) { 
				g.drawOval(rand.nextInt(1000), rand.nextInt(1000), 5, 5);
			}*/
			
			var n:Int = 0 ;
			
			rows.mapPartitions( x =>{
			 
			  x.map(_._1.split(";")).map(y =>(y(0).replace(".","").toInt,y(1).replace(".","").toInt))
			  
			}).collect.foreach(z => {
					//g.drawOval(rand.nextInt(14000),rand.nextInt(4000), 5, 5)
					g.drawOval(7000,2000, 500, 500)
			  })
			
			//val out = new FileOutputStream(path);
			val out = hdfs.create(path)
			ImageIO.write(image, "png", out);
			g.dispose()
			out.flush()
			out.close()
			//hdfs.close()
		  }
	  
	}
	
	object TheMain extends Serializable{
	  
	  //不经过坐标计算
	  def run1(a:Int) {
	    
	     //val server = HBaseServer.getHBaseServer()
	     val sparkConf = new SparkConf().setAppName("SparkTest")//.setMaster("yarn-cluster")
	     
	   // sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
       // sparkConf.set("spark.kryo.registrator", "MyKryo");
	    sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
	    sparkConf.set("spark.streaming.concurrentJobs","8") //job的并行度  默认为 1 
	    sparkConf.set("spark.default.parallelism","40") //shuffle过程中 task的个数  默认为 8
	   // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
	    sparkConf.set("spark.streaming.unpersist", "true")
	    sparkConf.set("spark.cleaner.ttl", "600")
	    sparkConf.set("spark.rdd.compress", "true") //是否压缩
	    sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
	    sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
	    sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
	   // sparkConf.set("spark.streaming.blockQueueSize ", "10")  //  默认为10  队列是最多能容纳10个Block
	    sparkConf.set("spark.streaming.receiver.maxRate", "100000")  // 100000 / s 
	    sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "10000")  //  x/s
	    
	    val ssc =  new StreamingContext(sparkConf, Seconds(1))
		
	   // sparkConf.set(key, value)
	    
	    ssc.checkpoint("/tmp/lixc/hbase/checkpoint/")
	    
	  //  println(ssc + "****************************************************************")
	    
		val inputTopic = "test909"
		   
		    // 8个 reciver  每个上面配置 10个线程并行读取  
		 
		    val readParallelism  = 6
		   val streams = (1 to readParallelism ) map { _ =>
		  	KafkaUtils.createStream(ssc, "dswhhadoop-1:2181,dswhhadoop-2:2181,dswhhadoop-3:2181,dswhhadoop-4:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181","spark-streaming-test06", Map(inputTopic -> 15), StorageLevel.MEMORY_AND_DISK_SER)
		  }
		  //val sparkProcessingParallelism = 20 // You'd probably pick a higher value than 1 in production.
		 
	     if(a == 1){
	    	 val processingDStream =   ssc.union(streams).repartition(80) //重新分区
	    	 processingDStream.foreachRDD(_.foreachPartition(Blaher.blah))
	     }else{
	       
	    	 val processingDStream =   ssc.union(streams).repartition(20) //重新分区
	    	 processingDStream.foreachRDD(rdd => Blaher.blah2(rdd))
	     }
    	   
		  ssc.start()
		  ssc.awaitTermination()
	  } 
	  
	  //经过坐标计算
	 def run2(a: Int) {
	   
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
		  sparkConf.set("spark.streaming.concurrentJobs","2") //job的并行度  默认为 1 
		  sparkConf.set("spark.default.parallelism","120") //shuffle过程中 task的个数  默认为 8
		  // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
		  sparkConf.set("spark.streaming.unpersist", "true")
		  sparkConf.set("spark.cleaner.ttl", "1200")
		 // sparkConf.set("spark.rdd.compress", "true") //是否压缩  用时间 换空间
		  sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
		  sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
		  sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
		  sparkConf.set("spark.streaming.blockQueueSize ", "15")  //  默认为10  队列是最多能容纳10个Block
		  sparkConf.set("spark.streaming.receiver.maxRate", "8000")  // 100000 / s 
		  sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "8000")  //  x/s
		  
		  sparkConf.set("spark.yarn.submit.file.replication", "1")  //  提交的jar文件  的副本数  默认为 3
		  sparkConf.set("spark.yarn.containerLauncherMaxThreads", "60")  //  container中的线程数 默认为 25 
		  sparkConf.set("spark.yarn.driver.memoryOverhead", "1024")  // 堆外内存  默认 384
		  sparkConf.set("spark.yarn.executor.memoryOverhead", "2048")  //  默认  384 
		  
		  sparkConf.set("spark.shuffle.memoryFraction", "0.4")  //  （1.1后默认为0.2）决定了当Shuffle过程中使用的内存达到总内存多少比例的时候开始Spill
		  sparkConf.set("spark.storage.memoryFraction", "0.85")  //  后默认为0.67  即 默认使用 内存的2/3来缓存数据
		 
		  sparkConf.set("spark.executor.extraJavaOptions", "-XX:+UseConcMarkSweepGC")  
		  
		  val ssc =  new StreamingContext(sparkConf, Seconds(60))
  		  
		  ssc.checkpoint("/tmp/lixc/hbase/checkpoint/")
		  
		  //  println(ssc + "****************************************************************")
		  
		  val inputTopic = "test002"
		  
		  // 8个 reciver  每个上面配置 10个线程并行读取  
		  
		  val readParallelism  = 60
		 
		  
		  val streams = (1 to readParallelism ) map { _ =>
			  KafkaUtils.createStream(ssc,ZKC,"spark-streaming-test002", Map(inputTopic -> 2),StorageLevel.MEMORY_AND_DISK_2)
			// a.count.print 
			 .mapPartitions(Blaher.regionCalculate)//.reduceByKey(_+_)
		  }
		  
		   val processingDStream =   ssc.union(streams).repartition(40) //重新分区
		   
		   //var n = 0 
		   if(a == 5){
			  // processingDStream.count.print
			   processingDStream.map(_._1.split(";")).map(x => "[" + x(0)+"," + x(1) + "]").saveAsTextFiles("hdfs://dswhhadoop-1:8020/tmp/train/")
			   //
		   }else{
		     
			   val res =  processingDStream.reduceByKeyAndWindow(
			       (x: Long, y: Long) => x + y,
	               (x: Long, y: Long) => x - y,
	               new Duration(60000), 
	               new Duration(60000),
	               ssc.sparkContext.defaultParallelism,
	              (x: (String, Long)) => x._2 != 0L )
	              
			 //  res.saveAsTextFiles("hdfs://dswhhadoop-2:8020/tmp/lixc/out6")
			  
			    /*
			  res.map(_._2).foreach(z => 
					 println(" -- " + z.collect.sum )
			      )*/
			
			  if(a == 3 ){
			     
				  res.foreachRDD(rdd => {
					  	//rdd.persist(StorageLevel.MEMORY_AND_DISK)
					    Blaher.storeRegionToHbase(rdd)
					   // rdd.unpersist() 
				    })
				    
			  }else{
			    /*
			     res.foreachRDD(rdd => {
			       	 //rdd.persist(StorageLevel.MEMORY_AND_DISK)
			    	 rdd.mapPartitionsWithIndex(Blaher.convert3).foreachPartition(Blaher.storeRegionToHbase3)
			    	 //rdd.unpersist()
			       })*/
			     res.foreachRDD(Painting.paint(_))
			    
			  }
		   
		   }
		   
		   /*
		   if(accumulable.value > 4){
		      accumulable.setValue(0)
		   }
		   accumulable += 1*/
		   
		  ssc.start()
		  ssc.awaitTermination()
	  } 
	
	
	}
	
	def main(args: Array[String]) {
	  
	   var  a = args(0).toInt
	    a match {
           case 1 =>  TheMain.run1(1)
           case 2 =>  TheMain.run1(2)
           case 3 =>  TheMain.run2(3)
           case 4 =>  TheMain.run2(4)
           case 5 =>  TheMain.run2(5)
	    }
	  
	
	  
	  
	//  val region = new Regions(120.841547,122.112458,12,30.666666,31.883332,10)
	  
//	  println(region.decideRegion(121.8587, 30.9857));
	   
	}
	
	
	
	
}





