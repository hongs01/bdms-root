package com.bdms.spark

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
import scala.tools.nsc.transform.Flatten

/**
 * Consumes messages from one or more topics in Kafka and does wordcount.
 * Usage: KafkaWordCount <zkQuorum> <group> <topics> <numThreads>
 *   <zkQuorum> is a list of one or more zookeeper servers that make quorum
 *   <group> is the name of kafka consumer group
 *   <topics> is a list of one or more kafka topics to consume from
 *   <numThreads> is the number of threads the kafka consumer should use
 *
 * Example:
 *    `$ bin/run-example \
 *      org.apache.spark.examples.streaming.KafkaWordCount zoo01,zoo02,zoo03 \
 *      my-consumer-group topic1,topic2 1`
 */

class Kafka_Spark {
  
  def main(args: Array[String]) {
  
	    /*
	   if (args.length < 5) {
	      System.err.println("Usage: KafkaTest <zkQuorum> <group> <topics> <numThreads> <output>")
	      System.exit(1)
	    }*/
   
   
    val sparkConf = new SparkConf().setAppName("KafkaTest")
    val ssc =  new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")
 
    val topicpMap = Map("test666" -> 6 );
    val lines = KafkaUtils.createStream(ssc, "hadoop-1,hadoop-2,hadoop-3,hadoop-4,hadoop-5,hadoop-6", "test", topicpMap).map(_._2)
    lines.saveAsTextFiles("/tmp/out/")
    ssc.start()
    ssc.awaitTermination()
 
    //.saveAsTextFile(output)
  }

}