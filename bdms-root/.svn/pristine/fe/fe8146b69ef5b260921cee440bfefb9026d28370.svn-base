package com.bdms.hbase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
  * Description:
  * 		hbase解析数据时 可能用到的时间解析工具。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24下午3:01:45            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class DSHbaseDateUtil {

	private static final SimpleDateFormat GJHTIMEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat DZWLHTIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/**
	  * description:
	  * @param dateTime
	  * @return   yyyyMMddHHmmss
	  * String
	  * 2015-8-24 下午3:03:49
	  * by Lixc
	 */
	public static String getHtimeStreing(long dateTime){
		
		return GJHTIMEFORMAT.format(new Date(dateTime));
	}
	
	
	public static long parseDZWLTimeStrToLong(String str) throws ParseException{
		
		return DZWLHTIMEFORMAT.parse(str).getTime();
	}

}
