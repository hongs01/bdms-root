/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-3-17 下午2:46:19
 */
package com.bdms.hive.udf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * @author 李晓聪
 * @date 2014-3-17 下午2:46:19
 * @Description: TODO 日期转换
 */
public class DateUtil {
	
	private static final Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * 
	 * @param time 时间的字串
	 * @return Date  time转换后的Date 对象
	 */
	public static Date Str2Date(String time){
		
		if(time == null){
			log.error("传入的时间字串为空，无法转换成日期类型 。");
			return null;
		}
		
		String str =  time.replaceAll("[\\D]", "");
		String[] temp = time.split("[\\D]");
	   
	   int n = 0;
		for(String s : temp){
			if(s.isEmpty())continue;
			n+=1;
		}
		try {
			switch (n) {
				case 1: return new SimpleDateFormat("yyyy").parse(str);
				case 2: return new SimpleDateFormat("yyyyMM").parse(str);
				case 3: return new SimpleDateFormat("yyyyMMdd").parse(str);
				case 4: return new SimpleDateFormat("yyyyMMddHH").parse(str);
				case 5: return new SimpleDateFormat("yyyyMMddHHmm").parse(str);
				case 6: return new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
			default:
				return null;
			}
		} catch (ParseException e) {
			log.error("----日期转换异常。",e);
		}
		return null;
	}
	/**
	 * @Title:Str2Str 
	 * @param time
	 * @Return String    返回格式为 yyyy-MM-dd'T'HH:mm:ss'Z' 的字符串
	 * @Description:TODO  日期字符串的格式转换
	 */
	public static String Str2Str(String time){
		
		SimpleDateFormat simpleDateFormat =	new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date day = Str2Date(time);
		if(day != null){
			return simpleDateFormat.format(day);
		}else{
			return null;
		}
		
	}

}
