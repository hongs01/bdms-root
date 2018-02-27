/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-5 下午1:38:02
 */
package com.bdms.hive;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.log4j.Logger;
import com.bdms.hive.exceptions.HiveStringException;
import com.bdms.hive.tools.DateTool;


/** 
 * @author 李晓聪
 * @date 2014-12-5 下午1:38:02
 * @Description:  TODO  判断  两个日期   是否  在同一周    
 */
public class SameWeek extends UDF {
	
	private static final Logger LOG = Logger.getLogger(SameWeek.class);
	
	private static final int SAVENDAY = 1000*60*60*24*7;
	
	public boolean evaluate(String date1,String date2){
		
			try {
				return compare( DateTool.str2Date(date1).getTime(),DateTool.str2Date(date2).getTime() );
			
			} catch (HiveStringException e) {
				LOG.error("不支持的日期字符串", e );
				e.printStackTrace();
			} catch (ParseException e) {
				LOG.error("不支持的日期字符串", e );
				e.printStackTrace();
			}
		
		  return false;
	}
	
	private boolean compare(long t1, long t2) {
		
		if( Math.abs(t1 - t2) < SAVENDAY ){
		
			Calendar c1 = Calendar.getInstance();
			c1.setTime( new java.util.Date(t1) );
			Calendar c2 = Calendar.getInstance();
			c2.setTime( new java.util.Date(t2) );
			
			if( (c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR)) ){
				return true;
			}
		}
		return false;
	}

}
