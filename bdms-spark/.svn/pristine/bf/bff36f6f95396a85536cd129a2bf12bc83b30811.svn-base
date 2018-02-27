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
import com.bdms.spark.util.SparkUtil
import com.bdms.dams.station.service.StationService;
import scala.math.Ordering


/* 
 * Description:
 * 		这是一个例子。
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-6-27上午3:28:07            1.0            Created by LiXiaoCong
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class GuiJODDay extends Serializable  {
  
  
object Blaher extends Serializable {
  
	  val  HBASESITE = "hbase/hbase-site.xml"
	  val  HBASEHDFS = "hbase/hdfs-site.xml"
	 
	  def convert(triple:  Iterator[(String, Int)]):Iterator[(ImmutableBytesWritable,Put)] = {
	     var p:Put = null
	     var day:String = null
	     var in:String = null
	     var out:String = null
	    
	     triple.map( z => { 
	    	   day = z._1.split("-")(0)
			   in = z._1.split("-")(1)
			   out = z._1.split("-")(2)
			   
			   p = new Put(Bytes.toBytes(z._1))
			   p.setWriteToWAL(false)
			   p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("DAY"),Bytes.toBytes(day))
			   p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("STATION_IN"),Bytes.toBytes(in)) 
			   p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("STATION_OUT"),Bytes.toBytes(out))
			   p.add(Bytes.toBytes("luxnew"),Bytes.toBytes("NUM"),Bytes.toBytes(z._2.toString))

			  (new ImmutableBytesWritable, p)
	     })
	  }
	  
	 def storeRegionToHbase(row: RDD[(String, Int)]){
	    
		    val hConf = getBaseConf
			
			val tabaleName = "odtop10gj"
			
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
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)
	  }
    
    private def getBaseConf: org.apache.hadoop.conf.Configuration = {
	    
	    val hConf : Configuration = HBaseConfiguration.create()
	    hConf.addResource(HBASESITE)
		hConf.addResource(HBASEHDFS)
	    hConf
	  }
	
}
  
    def startApp(appName:String = this.getClass().getName(),filePath:String) {
     
		val sparkConf = SparkUtil.getSparkStreamingConf(appName, "")
		val sc = new SparkContext(sparkConf); 

       	 //获取 spark Dstream  并解析数据     station_id start_time in out
         val streams = sc.textFile(filePath)
       
         //全天的实时数据
         val kl_data =  streams.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(0),x(3),x(4),Math.abs(x(5).toInt))))
         
         val reduce_data = kl_data.map(x => (x._1+"-"+x._2+"-"+x._3,x._4)).reduceByKey(_+_)
         val sort_data = reduce_data.map(x=>(x._2,x._1)).sortByKey(false)
//         sort_data.saveAsTextFile("hdfs://dswhhadoop-1:8020/tmp/oddataresult")     
         sort_data.top(10).foreach(print)
	    //存储数据
		 Blaher.storeRegionToHbase(sc.parallelize(sort_data.top(10).map(x=>(x._2,x._1))));
		sc.stop
  }

}
