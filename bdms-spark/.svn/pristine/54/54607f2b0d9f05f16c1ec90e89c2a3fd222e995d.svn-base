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
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._
import org.apache.spark.SparkContext
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.RowFilter
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import java.util.ArrayList
import org.apache.hadoop.hbase.filter.Filter
import org.apache.hadoop.hbase.filter.FilterList
import org.apache.hadoop.hbase.filter.FilterList.Operator
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Base64
import org.apache.hadoop.conf.Configuration
import org.apache.spark.storage.StorageLevel
import scala.math.Ordering
import org.apache.hadoop.hbase.mapred.TableOutputFormat

object GuiJMonth extends Serializable {
  
  object Blaher extends Serializable {
	  
	  
	  val zk =  "dswhhadoop-10,dswhhadoop-1,dswhhadoop-11,dswhhadoop-12,dswhhadoop-13,dswhhadoop-14,dswhhadoop-15,dswhhadoop-16," +
				"dswhhadoop-17,dswhhadoop-18,dswhhadoop-19,dswhhadoop-2,dswhhadoop-20,dswhhadoop-21,dswhhadoop-22,dswhhadoop-23," +
				"dswhhadoop-24,dswhhadoop-25,dswhhadoop-26,dswhhadoop-27,dswhhadoop-28,dswhhadoop-29,dswhhadoop-3,dswhhadoop-30," +
				"dswhhadoop-31,dswhhadoop-32,dswhhadoop-33,dswhhadoop-34,dswhhadoop-35,dswhhadoop-36,dswhhadoop-37,dswhhadoop-38," +
				"dswhhadoop-39,dswhhadoop-4,dswhhadoop-40,dswhhadoop-41,dswhhadoop-42,dswhhadoop-43,dswhhadoop-44,dswhhadoop-45," +
				"dswhhadoop-46,dswhhadoop-47,dswhhadoop-48,dswhhadoop-49,dswhhadoop-50,dswhhadoop-51,dswhhadoop-52,dswhhadoop-53," +
				"dswhhadoop-54,dswhhadoop-55,dswhhadoop-56,dswhhadoop-57,dswhhadoop-58,dswhhadoop-59,dswhhadoop-60,dswhhadoop-61," +
				"dswhhadoop-62,dswhhadoop-63,dswhhadoop-64,dswhhadoop-9";
	   
	
	  
	 def getStationMonthDataConf(monthStr:String) : Configuration = {
	  
			val tabaleName = "dsgj"
	        
			val hConf = getBaseConf
			
			val scan = new Scan();
		    val cf = Bytes.toBytes("luxnew")
		    scan.addColumn(cf, Bytes.toBytes("START_TIME"));
		    scan.addColumn(cf, Bytes.toBytes("ENTER_TIMES"));
		    scan.addColumn(cf, Bytes.toBytes("EXIT_TIMES"));
		   
		    val rowFilter = new RowFilter(CompareOp.EQUAL,  new BinaryPrefixComparator(Bytes.toBytes( monthStr )));
		   // val scvf = new SingleColumnValueFilter(Bytes.toBytes("luxnew"),Bytes.toBytes("STATION_ID"),CompareOp.EQUAL,Bytes.toBytes(station_id));
		    
	       // val filters = new ArrayList[Filter]();
			//filters.add(scvf);
			//filters.add(rowFilter);
			
			scan.setFilter(rowFilter)
	      
			val proto = ProtobufUtil.toScan(scan);
            val ScanToString = Base64.encodeBytes(proto.toByteArray())
			
			//指定输出格式和输出表名
			hConf.set(TableInputFormat.INPUT_TABLE,tabaleName)
			hConf.set(TableInputFormat.SCAN, ScanToString)
            
            hConf
            
			
		  
	  }
	
	 
	 
	  def convert(triple:  Iterator[(String, Iterable[String])]):Iterator[(ImmutableBytesWritable,Put)] = {
	     var p:Put = null
	     var tmp:Array[String] = null;
	 	 var ex:Array[String] = null;
	     var e:String = null;
	     var x:String = null;
	    
	     triple.map( z => {
	       
			  p = new Put(Bytes.toBytes(z._1))
			  tmp = z._1.split("-")
			  
			 z._2.foreach(y => {
			   if(y.endsWith("e")){
			      e = y.substring(0, y.length()-1)
			   }
			   if(y.endsWith("x")){
			      x = y.substring(0, y.length()-1)
			   }
			 })
			  
			  p.setWriteToWAL(false)
			  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("LINE_ID"),Bytes.toBytes(tmp(1).substring(0, 2))) 
			  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_ID"),Bytes.toBytes(tmp(1))) 
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("DAY"),Bytes.toBytes(tmp(0)))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("ENTER_AVG"),Bytes.toBytes(e))
			  p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("EXIT_AVG"),Bytes.toBytes(x))
			  (new ImmutableBytesWritable, p)
	     })
		 
	  }
	  
	 def storeRegionToHbase(row: RDD[(String, Iterable[String])]){
	    
		    val hConf = getBaseConf
			
			val tabaleName = "monthgj"
			
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
    
    private def getBaseConf: org.apache.hadoop.conf.Configuration = {
	    
	    val hConf : Configuration = HBaseConfiguration.create()
	    hConf.set("hbase.zookeeper.quorum", zk)
	    hConf.set("hbase.zookeeper.property.clientPort", "2181") 
	    hConf.set("hbase.rootdir","hdfs://dswhhadoop-1:8020/apps/hbase/data")
	    hConf
	  }
	
}
  
	 
  
  
  def main(args: Array[String]) {
  
         val sparkConf = new SparkConf().setAppName("SparkTest")//.setMaster("yarn-cluster")
	  
				  
		  // sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		  // sparkConf.set("spark.kryo.registrator", "MyKryo");
		  sparkConf.set("spark.akka.frameSize","1024") //控制Spark中通信消息的最大容量 （如 task 的输出结果），默认为10M 
		  //sparkConf.set("spark.streaming.concurrentJobs","4") //job的并行度  默认为 1 
		  sparkConf.set("spark.default.parallelism","240") //shuffle过程中 task的个数  默认为 8
		  // sparkConf.set("spark.local.dir","/tmp/run_spark/1,/tmp/run_spark/2,/tmp/run_spark/3,/tmp/run_spark/4,/tmp/run_spark/5,/tmp/run_spark/6,/tmp/run_spark/7,/tmp/run_spark/8") // Spark 运行时的临时目录 默认为tmp
		 // sparkConf.set("spark.streaming.unpersist", "true")
		  sparkConf.set("spark.cleaner.ttl", "1200")
		 // sparkConf.set("spark.rdd.compress", "true") //是否压缩  用时间 换空间
		  sparkConf.set("spark.shuffle.consolidateFiles", "true")  //合并shuffle过程中产生的小文件
		  sparkConf.set("spark.speculation", "true")  //去掉允许缓慢的节点
		  //sparkConf.set("spark.streaming.blockInterval", "100")  // 100 ms 一个bolock 默认为 200ms
		 //  sparkConf.set("spark.streaming.blockQueueSize ", "20")  //  默认为10  队列是最多能容纳10个Block
		  //sparkConf.set("spark.streaming.receiver.maxRate", "2000")  // 100000 / s  8000
		 // sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "2000")  //  x/s
		  
		  sparkConf.set("spark.yarn.submit.file.replication", "1")  //  提交的jar文件  的副本数  默认为 3
		  sparkConf.set("spark.yarn.containerLauncherMaxThreads", "60")  //  container中的线程数 默认为 25 
		 // sparkConf.set("spark.yarn.driver.memoryOverhead", "1024")  // 堆外内存  默认 384
		 // sparkConf.set("spark.yarn.executor.memoryOverhead", "2048")  //  默认  384 
		  
		  //sparkConf.set("spark.shuffle.memoryFraction", "0.3")  //  （1.1后默认为0.2）决定了当Shuffle过程中使用的内存达到总内存多少比例的时候开始Spill
		//  sparkConf.set("spark.storage.memoryFraction", "0.85")  //  后默认为0.67  即 默认使用 内存的2/3来缓存数据
		 
		 // sparkConf.set("spark.executor.extraJavaOptions", "-XX:+UseConcMarkSweepGC")  
		  
		  val sc =  new SparkContext(sparkConf)
  		  
		
		  val usersRDD = sc.newAPIHadoopRDD(Blaher.getStationMonthDataConf("201506"), classOf[TableInputFormat],
			  classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
			  classOf[org.apache.hadoop.hbase.client.Result]).cache
			 
         
			val monthData =  usersRDD.map(_._2).map(result  =>{
				  val cf = Bytes.toBytes("luxnew")
				  val key = Bytes.toString(result.getRow)
				  (
			       key.substring(0,8) + "-"+key.split("-")(1),
			       Bytes.toString(result.getValue(cf,Bytes.toBytes("ENTER_TIMES"))),
			       Bytes.toString(result.getValue(cf, Bytes.toBytes("EXIT_TIMES")))
			    )})
			    
			       
			val monthEnter =   monthData.map( x => (x._1,x._2.toInt/100))
			.groupByKey()
			.map(x => {
			   (x._1, x._2.toList.sorted(Ordering.Int.reverse).slice(0, 10).sum/10+"e")
			})
			
			val monthExit =   monthData.map( x => (x._1,x._3.toInt/100))
			.groupByKey()
			.map(x => {
				(x._1, x._2.toList.sorted(Ordering.Int.reverse).slice(0, 10).sum/10 + "x")
			})
			
			val monthResult = monthEnter.union(monthExit).groupByKey	
			
			Blaher.storeRegionToHbase(monthResult);
			
			sc.stop
		  	  
		

  }

}