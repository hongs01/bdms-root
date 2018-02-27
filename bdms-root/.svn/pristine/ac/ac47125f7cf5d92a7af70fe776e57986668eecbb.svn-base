package com.bdms.spark.mlib

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import scala.collection.immutable.Vector
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}

/* 
 * Description:
 * 		用于mlib测试
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-29下午3:12:36            1.0            Created by YuXiaolin
 * 
 *   import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}

val observations: RDD[Vector] = ... // an RDD of Vectors

// Compute column summary statistics.
val summary: MultivariateStatisticalSummary = Statistics.colStats(observations)
println(summary.mean) // a dense vector containing the mean value for each column
println(summary.variance) // column-wise variance
println(summary.numNonzeros) // number of nonzeros in each column
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
object mlibS {

  def main(args: Array[String]): Unit = {
    
     val conf=new SparkConf().setAppName("spark-w"); //spark配置
     val sc = new SparkContext(conf);
     val line=sc.textFile("hdfs://dswhhadoop-1:8020/tmp/yuxl/data.txt").cache();
     val observations=line.map(s=>{
    	 val dis=s.split(" ").map(f=>f.toDouble);
    	 Vectors.dense(dis);
     	});
     	val summary:MultivariateStatisticalSummary=Statistics.colStats(observations);
     	
     	val mmean=summary.mean
     	
     	
     	println(summary.mean);
     	
     	sc.stop();
     	
     	
     }

}