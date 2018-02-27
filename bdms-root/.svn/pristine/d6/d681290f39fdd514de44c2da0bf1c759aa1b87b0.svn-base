package com.bdms.spark.mlib

import java.util.Random
import org.apache.spark.SparkContext
import SparkContext._
import org.apache.spark.util.Vector  
import org.apache.spark.SparkConf
/**  
 * Created by jack on 2/26/14.  
 */  
class KMeans {  
  
    val N = 1000  
    val R = 1000     //随机数范围  0-1  *  R  
    val D = 10       //点空间纬度  
    val K = 10       //聚类中心个数  
    val rand = new Random(42) //随机种子  
    val convergeDist = 0.01   //迭代收敛条件  
  
    /**  
     * 将p分配到当前所有聚类中心的最短距离的类中  
     * */  
    def closestPoint(p:Vector,centers: Array[Vector]): Int = {  
        var bestIndex = 0  
        var closest = Double.PositiveInfinity  
  
        for (i <- 0 until centers.length) {  
            val tempDist = p.squaredDist(centers(i))  
            if(tempDist < closest) {  
                closest = tempDist  
                bestIndex = i  
            }  
        }  
  
        bestIndex  
    }  
  
/**  
 * 产生N个D维（每一维取值0-1000）随机的点  
 * */  
    def generateData = {  
        def generatePoint(i: Int) = {  
            Vector(D,_ => rand.nextDouble * R)  
        }  
        Array.tabulate(N)(generatePoint)  
    }  
  
    def main(args: Array[String]) {  
      
       val sparkConf = new SparkConf().setAppName("SparkTest")
        val sc = new SparkContext(sparkConf) 
        val data = sc.parallelize(generateData).cache()  
  
        //随机初始化K个聚类中心  
        val kPoints = data.takeSample(false,K,42).toArray  
        var tempDist = 1.0  
  
        while(tempDist > convergeDist) {  
            //closest为（类别，（点，1）），1是用来后续统计各个类中点的数量count  
            val closest = data.map(p => (closestPoint(p,kPoints),(p,1)))  
            //按类别，计算点的坐标和，以及该类别中节点总数 （类别，（点向量和，点数））  
            val pointStats = closest.reduceByKey{  
                case ((x1,y1),(x2,y2)) => (x1+x2,y1+y2)  
            }  
            //生成新的聚类中心的Map（类别，新聚类中心）  
            val newPoints = pointStats.map{  
                pair => (pair._1, pair._2._1 / pair._2._2)  
            }.collectAsMap()  
  
            tempDist = 0.0  
            for (i <- 0 until K) {  
                tempDist += kPoints(i).squaredDist(newPoints(i))  
            }  
            //更新聚类中心到kPoint  
            for (newP <- newPoints) {  
                kPoints(newP._1) = newP._2  
            }  
            println("Finished iteration(delta = "+ tempDist + ")")  
        }  
        println("Final centers:")  
        kPoints.foreach(println)  
        System.exit(0)  
    }  
}  