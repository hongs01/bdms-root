package com.bdms.spark.hbase

import java.text.DecimalFormat
import scala.collection.mutable.ArrayBuffer

class RegionGIS extends Serializable {
  
     private final val DF_LEN = new DecimalFormat("###");
	 private final val DF_x = new DecimalFormat("###.000000");
	 private final val DF_y = new DecimalFormat("###.000000");
	 
	 var n = 0 //范围坐标的个数
	 
	 var gis:Array[(Double,Double)] = null;
   
	
	 var x_min:Double = 0
	 var x_max:Double = 0
	 
	 var y_min:Double = 0
	 var y_max:Double = 0
	
	  //apply(121.147648,121.980414,100,30.710435,31.421133,100)  经纬度范围（区分大小顺序 ，从小到大），以及分区数 
	  def this(gis:Array[(Double,Double)],partNum:Int){
	   
	    this()
	    this.gis = gis;
	    n  = gis.length;
	    var tmp =  gis.sorted
	    x_min = tmp.head._1
	    x_max = tmp.last._1
	    
	    tmp =  gis.map( x => (x._2,x._1)).sorted
	    y_min = tmp.head._1
	    y_max = tmp.last._1
	    
	    
	   }
	  
	   def decideRegion(x:Double,y:Double){
	     
	     val tmp = new Array[(Double,Double)](2)
	     
	     if( (x >= x_min && x <= x_max) && (y >= y_min && y <= y_max)){
	       
	       gis.toMap
	     }
	    
	  }
	   
	   def main(args: Array[String]) {
	     
		   val a = new ArrayBuffer[(Double,Double)]
		   a += ((121.975814,30.889107))
		   a += ((121.899925,30.857368))
		   a += ((121.541178,30.829587))
		   a += ((121.368703,30.730304))
		   
		   a += ((121.522781,31.393519))
		   a += ((121.299714,31.334317))
		   a += ((121.299714,31.334317))
		   a += ((121.219225,30.893074))
		   
   }

}