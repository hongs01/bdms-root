/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-5 下午5:14:39
 */
package com.bdms.hive;

import java.text.ParseException;

import org.apache.hadoop.hive.ql.exec.UDF;
import com.bdms.hive.exceptions.HiveDateException;
import com.bdms.hive.exceptions.HiveStringException;
import com.bdms.hive.tools.DateTool;
import com.bdms.hive.tools.DateTool.DATE_DIFFTYPE;

/** 
 * @author 李晓聪
 * @date 2014-12-5 下午5:14:39
 * @Description:  TODO  定向日期 相减 
 */
public class DateDiff extends UDF{

	public long evaluate(String date1,String date2,String diffType) throws HiveStringException, ParseException, HiveDateException{
		
		DateTool.DATE_DIFFTYPE type = null;
		
		if("minute".equalsIgnoreCase(diffType)){
			type = DATE_DIFFTYPE.MINUTE;
		}else if("second".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.SECOND;
		}else if("hour".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.HOUR;
		}else if("day".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.DAY;
		}else if("month".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.MONTH;
		}else if("year".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.YAER;
		}else if("week".equalsIgnoreCase(diffType) ) {
			type = DATE_DIFFTYPE.WEEK;
		}else{
			throw new HiveStringException("不能识别的日期运算 类型！(系统定义的运算类型有：( year month week day hour minute second )) ");
		}
		
		return DateTool.dateDiff(date1, date2, type);
		
	}
	
	public long evaluate(String date1,String date2,String diffType,boolean  abs ) throws HiveStringException, ParseException, HiveDateException{
		
		long result = evaluate(date1, date2, diffType);
		
		if(abs){
			result =  Math.abs(result);
		}
		return result;
	}
	
}
