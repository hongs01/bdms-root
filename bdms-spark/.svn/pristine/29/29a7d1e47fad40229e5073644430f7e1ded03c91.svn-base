package com.bdms.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._


object WordCount {
  
  def main(args: Array[String]) {
	  
    val conf = new SparkConf().setAppName("spark-wordCount");
    val sc = new SparkContext(conf);
   
    val line = sc.textFile("hdfs://dswhhadoop-2:8020/tmp/lixc/SogouQ1.txt");
    line.cache();
    
    line.map(_.split("\t")).filter(_.length > 5).map(x=>(x(2)+"(" + x(5) + ")",1))
    .reduceByKey(_+_).filter(_._2 >= 100).map(x=>(x._2,x._1))
    .sortByKey(false).map(x =>(x._2,x._1)).saveAsTextFile("hdfs://dswhhadoop-2:8020/tmp/lixc/out")
    
    line.unpersist();
    
    sc.stop
}

}