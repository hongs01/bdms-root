package com.bdms.spark.hbase

import java.text.DecimalFormat

//import org.apache.log4j.Logger

/*
 * val region = new Regions(120.841547,122.112458,12,30.666666,31.883332,10)
 *  12 表示  将 (120.841547,122.112458)的间隔分成12份
 */

class Regions extends Serializable{
	  
	// private final val LOG = Logger.getLogger(classOf[Regions])
	 //步长精度，以及 经纬度精度
	// private final val DF_GAP = new DecimalFormat("###.00");
	 //步长个数精度
	 private final val DF_LEN = new DecimalFormat("###");
	 private final val DF_x = new DecimalFormat("###.000000");
	 private final val DF_y = new DecimalFormat("###.000000");
  
	  private var x1:Double = 0;
	  private var x2:Double = 0;
	  private var x_n:Int = 0;
	  
	  private var y1:Double = 0;
	  private var y2:Double = 0;
	  private var y_n:Int = 0;
	  
	  private var x_gap:Double = 0;
	  private var y_gap:Double = 0;
	  
	  //间隔步长
	  private var x_length:Double = 0;
	  private var y_length:Double = 0;
	  
	  
	  private var pos:String = null;
	  private var result:String = null;
	  
	  
	  //apply(120.85,122.2,100,30.67,31.88,100)  经纬度范围（区分大小顺序 ，从小到大），以及分区数 
	  def this(x1:Double,x2:Double,x_n:Int,y1:Double,y2:Double,y_n:Int){
	    
	     this()
	     this.x1 = x1;
	     this.x2 = x2;
	     this.x_n = x_n;
	     
	     
	     this.y1 = y1;
	     this.y2 = y2;
	     this.y_n = y_n;
	     
	     x_gap = x2 - x1
	     y_gap = y2 - y1
	     
	     if(x_gap <= 0 || y_gap <= 0 ){
	       //error("区分精度有误，或给定范围有误。")
	      // println("区分精度有误，或给定范围有误。")
	       System.exit(-1)
	     }
	     
	     x_length =  x_gap/x_n
	     y_length =  y_gap/y_n
	     
	   }
	  
	   def decideRegion(x:Double,y:Double):String = {
	     
	     // pos = "坐标： (" + x + "," + y + ") "
	      result = null;
	      
	      if( x > x2 || y > y2 || x < x1 || y < y1){
	    	 //warn(pos +",不在给定的范围内。")
	    	// println(pos +",不在给定的范围内。")
	      }else{
	          
	    	  var gap = x - x1
	    	  var n = DF_LEN.format(gap/x_length).toInt
	    	  
	    	  var tmp =  DF_x.format(x1 + n*x_length).toDouble
	    	  
	    	  if(tmp < x2){
	    	    
	    		  result = tmp + ";";
	    		  
	    		  // debug(pos + "与x的最小边界的距离为：" + gap + ", 步长的个数为： " + n )
	    		  // println(pos + "与x的最小边界的距离为：" + gap + ", 步长的个数为： " + n  )
	    		  
	    		  gap = y - y1
	    		  n = DF_LEN.format(gap/y_length).toInt
	    		  tmp = DF_y.format( y1 + n*y_length ).toDouble;
	    		  
	    		  if(tmp < y2){
	    			  result += tmp;
	    		  }else{
	    		     result = null;
	    		  }	  
	    		  // debug(pos + "与y的最小边界的距离为：" + gap + ", 步长的个数为： " + n )
	    		  //println(pos + "与y的最小边界的距离为：" + gap + ", 步长的个数为： " + n )
	    	  }
	    	  
	      }
	    result
	  }
	  
	}