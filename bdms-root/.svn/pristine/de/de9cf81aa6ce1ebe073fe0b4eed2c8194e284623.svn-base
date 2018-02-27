package com.bdms.spark.mlib

import java.text.DecimalFormat
import java.util.Random
import scala.collection.mutable.ArrayBuffer

object CentersUtil extends Serializable {
  
  
	private  final val DF = new DecimalFormat("###.000000")
	private final val RANDOM = new Random()
	
	private val first:Int  = 120850000
	private val last:Int  = 122200000
	
	private val gap:Int  = last - first + 1
	
	private val first2:Int = 30666667
	private val last2:Int =31883333
	
	private val gap2:Int =last2 - first2 + 1 
	
	private var gis_x:Int = 0
	private var gis_y: Int = 0
	
	private var res:ArrayBuffer[Array[Double]] = null
	private val gis = new Array[Double](2)
	
	 def getGISData(length:Int):ArrayBuffer[Array[Double]] = {
		
		res = new ArrayBuffer[Array[Double]]()
	    for( i <- 0 to (length-1) ){
	      
	    	gis_x = RANDOM.nextInt(gap) + first;
	    	gis_y = RANDOM.nextInt(gap2) + first2;
	    	
	    	gis(0) = DF.format(gis_x / 1000000.0).toDouble
	    	gis(1) = DF.format(gis_y / 1000000.0).toDouble
	    	
	    	res += gis
	    }
		res.distinct
	}

	
}