/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-11-18 下午2:51:39
 */
package com.bdms.hive.udf.test;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.hadoop.hive.ql.exec.UDF;


/** 
 * @author 李晓聪
 * @date 2014-11-18 下午2:51:39
 * @Description:  TODO
 */

/**  UDF  Description:
 *  
 * A User-defined function (UDF) for the use with Hive.
 *
 * New UDF classes need to inherit from this UDF class.
 *
 * Required for all UDF classes: 1. Implement one or more methods named
 * "evaluate" which will be called by Hive. The following are some examples:
 * public int evaluate(); public int evaluate(int a); public double evaluate(int
 * a, double b); public String evaluate(String a, int b, String c);
 *
 * "evaluate" should never be a void method. However it can return "null" if
 * needed.
 * 
 */
public class TestUDF extends UDF {
	
	private static final TimeZone GMT = TimeZone.getTimeZone("GMT-8");
	
	public boolean evaluate( Date startDate,Date endDate ) throws Exception{
		
		if( startDate == null || endDate == null) return false;
		long t1 = startDate.getTime();
		long t2 = endDate.getTime();
		return compare(t1, t2);
	}
	
	public boolean evaluate( String startDate,String endDate ) {
		
		if( startDate == null || endDate == null) return false;
	
		long t1 = DateUtil.Str2Date(startDate).getTime();
		long t2 =DateUtil.Str2Date(endDate).getTime();
		
		return compare( t1,t2 );
	}
	

	private boolean compare(long t1, long t2) {
	
		Calendar c1 = Calendar.getInstance(GMT);
		c1.setTime(new java.util.Date(t1));
		Calendar c2 = Calendar.getInstance(GMT);
		c2.setTime(new java.util.Date(t2));
		
		if( (Math.abs( c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR) ) < 7) && (c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR)) ){
			return true;
		}
		return false;
	}

}
