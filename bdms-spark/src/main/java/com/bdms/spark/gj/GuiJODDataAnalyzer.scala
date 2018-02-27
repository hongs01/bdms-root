package com.bdms.spark.gj

import com.bdms.spark.util.SparkUtil
import org.apache.spark.storage.StorageLevel
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import com.bdms.spark.util.HBaseUtil
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import scala.collection.mutable.ArrayBuffer
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import scala.reflect.ClassTag
import com.bdms.spark.util.SortNumberKey

class GuiJODDataAnalyzer extends Serializable {

  
  val format = new SortNumberKey(4);
  
   /**
	  * description: 数据格式重组,适用与统计历史数据中某个时间点的od数据
	  * @param rows
	  * @return  (O-D-(START_TIME.substring(8)),PASS_NUM)
	  * scala.collection.Iterator<scala.Tuple2<java.lang.String,java.lang.Int>>
	  * 2015-8-4 下午1:37:31
	  * by Lixc
	 */
	def covertAllDay(rows: Iterator[(String, String, String, Int)]):Iterator[(String, Int)] = {
	     
      rows.map( x => (x._1.substring(0,8) + "-" + x._2 + "-" + x._3 ,x._4))
     
   }
  
	
	
		 //数据类型转换
	
//	 def convert(triple:  Iterator[List[String]]):Iterator[(ImmutableBytesWritable,Put)] = {
//	   
//	    var countRow:ArrayBuffer[(ImmutableBytesWritable,Put)] = new ArrayBuffer[(ImmutableBytesWritable,Put)]()
//	     var p:Put = null
//	     var value:Array[String] = null;
//	     triple.foreach( z => {
//	         z.foreach( x =>{
//	              value = x.split(";")
//	              p = new Put(Bytes.toBytes(value(1)))
//				  p.setWriteToWAL(false)
//				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("OD"),Bytes.toBytes(value(1).substring(9))) 
//				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("PASS_NUM"),Bytes.toBytes(value(0))) 
//				  countRow += ((new ImmutableBytesWritable, p))
//	            })
//	     })
//	     
//	     countRow.toIterator
//	  }
	
	 def convert(triple:  Iterator[(String, String,String)]):Iterator[(ImmutableBytesWritable,Put)] = {
		 
		 
		 
	     var p:Put = null
	     triple.map( z => {
	              p = new Put(Bytes.toBytes(z._1))
				  p.setWriteToWAL(false)
				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("OD"),Bytes.toBytes(z._2)) 
				  p.add(Bytes.toBytes("luxnew"), Bytes.toBytes("PASS_NUM"),Bytes.toBytes(z._3)) 
				  (new ImmutableBytesWritable, p)
	            })
	  }
	  
	 //存储Hbase
	 def storeRegionToHbase(row: RDD[(String, String,String)],tableName:String){
	    
		    val hConf = HBaseUtil.getHConf
			
			//HBaseUtil.checkTableExist(hConf,tableName)
			
			//指定输出格式和输出表名
			val jobConf = new JobConf(hConf,this.getClass)
			jobConf.setOutputFormat(classOf[TableOutputFormat])
			jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
			jobConf.setNumMapTasks(60)
			jobConf.setNumReduceTasks(60)
			
			row.mapPartitions(convert).saveAsHadoopDataset(jobConf)

	  }
	
	
   /* 
	 * @Title: startApp  
	 * @Description: TODO   启动  任务
	 * @param @param appName
	 * @param @param filePath    
	 * @return void    
	 * @throws 
	 * @Author  Lixc
	*/
    def startApp(appName:String = this.getClass().getName(),filePath:String,tableName:String) {
      
       val sparkConf = SparkUtil.getSparkConf(appName)
	   val sc =  new SparkContext(sparkConf)
		  	  
       //获取 spark Dstream  
       val meta_data = sc.textFile(filePath)
       
        //(START_TIME,O,D,PASS_NUM)
       val meta_od_data =  meta_data.mapPartitions(_.map(_.split(",")).filter(x => x.length == 6).map(x => (x(3)+"-"+x(4),Math.abs(x(5).toInt))))
         					   .persist(StorageLevel.MEMORY_AND_DISK_2)
         	
        //(O-D-(START_TIME.substring(8)),PASS_NUM)	
       
//       val allDay_od_data = meta_od_data.mapPartitions(covertAllDay)
         					   
         					   
//    val sort_data = reduce_data.map(x=>(x._2,x._1)).sortByKey(false)
////         sort_data.saveAsTextFile("hdfs://dswhhadoop-1:8020/tmp/oddataresult")     
//         sort_data.top(10).foreach(print)
//	    //存储数据
//		 Blaher.storeRegionToHbase(sc.parallelize(sort_data.top(10).map(x=>(x._2,x._1))));
         					   
//       val currentDate = new Date();
//       val simpleDateFormat = new SimpleDateFormat("yyyyMMdd")  					   
//       val dateStr = simpleDateFormat.format(currentDate)			   
       
	     val od_data = meta_od_data.reduceByKey(_+_).map(x=>(x._2,x._1)).sortByKey(false).top(1000)
		//val od_data = meta_od_data.reduceByKey(_+_).sortBy(x => x._2)(Ordering.Int.reverse,ClassTag.Int).take(1000)	
		
		val result:ArrayBuffer[(String,String,String)] = new ArrayBuffer[(String,String,String)]()
		
	  	for( i <- 0 to (od_data.length -1) ){
	  	     result += ((format.getKey(i),od_data(i)._2,od_data(i)._1.toString));
	  	}					  
	  	val a = result.toArray
       
	    storeRegionToHbase(sc.parallelize(a),tableName)		
	    
	    sc.stop;
    }
}