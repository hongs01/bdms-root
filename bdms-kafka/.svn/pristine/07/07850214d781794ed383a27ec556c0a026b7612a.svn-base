package com.bdms.kafka.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class GISRandomUtil {

	private static final DecimalFormat DF = new DecimalFormat("###.000000");
	private static final Random RANDOM = new Random();
	
	private int first = 120850000;
	private int last = 122200000;
	
	private int gap = 0;
	
	private int first2 = 30666667;
	private int last2 =31883333;
	
	private int gap2 = 0;
	
	private  int gis_x;
	private int gis_y;
	
	
	private String[] res = new String[2];
	
	public GISRandomUtil(){
		
		gap = last - first + 1 ;
		gap2 = last2 - first2 + 1 ;
		
	}
	
	public  String[] getGISData() {
		
		gis_x = RANDOM.nextInt(gap) + first;
		gis_y = RANDOM.nextInt(gap2) + first2;
		
		res[0] = DF.format(gis_x / 1000000.0) + RANDOM.nextInt(10) +  RANDOM.nextInt(10) ;
		res[1] = DF.format(gis_y / 1000000.0) + RANDOM.nextInt(10) +  RANDOM.nextInt(10);
		
		return res;
	}
	
	public static void main(String[] args) {
		
		//System.out.println("(" + 7251.0/60 + "  " + 7332.0/60 + ")");
		//System.out.println("(" + 1840.0/60 + "  " + 1913.0/60 + ")");
		
		GISRandomUtil gis = new GISRandomUtil();
		
		String[] gisData = gis.getGISData();
		System.out.println((gisData[0]+gisData[1]).getBytes().length);
		
		/*System.out.println( (7251.0+ 7332.0)/120 );
		System.out.println( (1840.0+1913.0)/120 );
		System.out.println( (122200000-120850000)/1000000.0  );*/
		
		
		
		
	}
}
