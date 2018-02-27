package com.ds.bdms.bdms_spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import scala.util.parsing.json.JSON

/*
 * updateStateByKey ，
 * 计算每个窗口的平均值，跟上一个窗口的平均值的涨跌幅度，如果波动超过 10%，则输出
 */

object LogStash {

  case class LogStashV1(message:String, path:String, host:String, lineno:Double, timestamp:String)

  case class Status(sum:Double = 0.0, count:Int = 0) {

    val avg = sum / scala.math.max(count, 1)
    var countTrend = 0.0
    var avgTrend = 0.0

    def +(sum:Double, count:Int): Status = {

      val newStatus = Status(sum, count)

      if (this.count > 0 ) {
        newStatus.countTrend = (count - this.count).toDouble / this.count
      }
      if (this.avg > 0 ) {
        
        newStatus.avgTrend = (newStatus.avg - this.avg) / this.avg
      }
      newStatus
    }

    override def toString = {
      s"Trend($count, $sum, $avg, $countTrend, $avgTrend)"
    }
  }

  def updatestatefunc(newValue: Seq[(Double, Int)], oldValue: Option[Status]): Option[Status] = {
    val prev = oldValue.getOrElse(Status())
    var current = prev + ( newValue.map(_._1).sum, newValue.map(_._2).sum )
    Some(current)
  }
  
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("LogStash")
    val sc  = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sc, Seconds(10))
    val lines = ssc.socketTextStream("localhost", 8888)
    val jsonf = lines.map(JSON.parseFull(_)).map(_.get.asInstanceOf[scala.collection.immutable.Map[String, Any]])
    val logs = jsonf.map(data => LogStashV1(data("message").toString, data("path").toString, data("host").toString, data("lineno").toString.toDouble, data("@timestamp").toString))
    val r = logs.filter(l => l.path.equals("/var/log/system.log")).filter(l => l.lineno > 70)
    r.map(l => l.message -> (l.lineno, 1)).reduceByKey((a, b) => {
      (a._1 + b._1, a._2 + b._2)
    }).updateStateByKey(updatestatefunc).filter(t => t._2.avgTrend.abs > 0.1).print()
    ssc.start()
    ssc.awaitTermination()
  }
}