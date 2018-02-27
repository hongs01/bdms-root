/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-3-17 下午2:46:19
 */
package com.bdms.hive.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.bdms.hive.exceptions.HiveDateException;
import com.bdms.hive.exceptions.HiveStringException;


/**
 * 
 * @author 李晓聪
 * @date 2014-3-17 下午2:46:19
 * @Description: TODO 日期转换
 */
public class DateTool {
	
	
	private static final String[] pattens = new String[]{"yyyyMMddHHmmss","yyyyMMddHHmm","yyyyMMddHH",
		"yyyyMMdd","yyyyMM","yyyy"};
	
	private DateTool(){};
	
	/**
	 * @param time 时间的字串
	 * @return Date  time转换后的Date 对象
	 * @throws HiveStringException 
	 * @throws ParseException   <a>strToDate</a> instead
	 */
	public static Date str2Date(String time) throws HiveStringException, ParseException{
		
		StoreMSG transform = transform(time);
    	int n = transform.getLength();
    	String str = transform.getValue();
			
			switch (n) {
				case 1: return covert(str.toString(),"yyyy");
				case 2: return covert(str.toString(),"yyyyMM");
				case 3: return covert(str.toString(),"yyyyMMdd");
				case 4: return covert(str.toString(),"yyyyMMddHH");
				case 5: return covert(str.toString(),"yyyyMMddHHmm");
				case 6: return covert(str.toString(),"yyyyMMddHHmmss");
			default:
				throw new HiveStringException( time +  " : 无法识别的字符串。");
			}
		
	}
	
	@Deprecated
    public static Date strToDate(String time) throws HiveStringException, ParseException{
		
    	StoreMSG transform = transform(time);
    	String str = transform.getValue();
			
		return DateUtils.parseDate(str, pattens);
		
			
		
	}
	
    private static StoreMSG transform(String date_str) throws HiveStringException{
    	
    	if(StringUtils.isNotBlank(date_str)){
    		
    		StoreMSG  sm = new StoreMSG();
    		
			StringBuffer str =  new StringBuffer();
			String[] temp = date_str.split("[\\D]");

			int n = 0;
			for(String s : temp){
				if( n < 6 &&  StringUtils.isNotBlank(s)  ){
					str.append(s);
					n+=1;
				}
			}
			
			sm.setLength(n);
			sm.setValue(str.toString());
			
			return sm;
			
    	}else{
			throw new HiveStringException("传入的时间字串为空!!");
		}
    }
   
    
 	public static Date covert( String time, String format ) throws ParseException{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(time);
	}

    public static long dateDiff( String mainDate , String diffDate,DATE_DIFFTYPE diffType ) throws HiveDateException, HiveStringException, ParseException{
		
			return dateDiff(str2Date(mainDate),str2Date(diffDate),diffType);
		
	}
	
	public static long dateDiff( Date mainDate , Date diffDate,DATE_DIFFTYPE diffType ) throws HiveDateException{
		
		if( mainDate != null && diffDate != null){
			
			GregorianCalendar c1 = new GregorianCalendar();
			c1.setTime( mainDate );
			GregorianCalendar c2 = new GregorianCalendar();
			c2.setTime( diffDate );
			
			switch(diffType){
			
			case DAY:
				return dateDiffDAY(c1,c2);
			case HOUR:
				return dateDiffHOUR(c1,c2);
			case MINUTE:
				return dateDiffMINUTE(c1,c2);
			case MONTH:
				return dateDiffMONTH(c1,c2);
			case SECOND:
				return ( c1.getTimeInMillis() - c2.getTimeInMillis() ) / 1000;
			case WEEK:
				return dateDiffWEEK(c1,c2);
			case YAER:
				return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
			default:
				throw new HiveDateException("不能识别的DATE_DIFFTYPE。");
			}
		}else{
			throw new HiveDateException("参与运算的日期不能为空。");
		}
	}
	
	private static long dateDiffWEEK(GregorianCalendar c1, GregorianCalendar c2) {
		
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		
		c1 = move2WeekEnd(c1);
		c2 = move2WeekEnd(c2);
		
		
		return ( c1.getTimeInMillis() - c2.getTimeInMillis() )/(1000*60*60*24*7);
	}
	
	private static long dateDiffMONTH(GregorianCalendar c1, GregorianCalendar c2) {
		
		int yearGap = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
	    return   12*yearGap + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
	
	}
	
	private static long dateDiffDAY( GregorianCalendar c1, GregorianCalendar c2 ) {
		
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		
		return ( c1.getTimeInMillis() - c2.getTimeInMillis() )/(1000*60*60*24);
	}
	
	private static long dateDiffHOUR(GregorianCalendar c1, GregorianCalendar c2) {
		
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		
		return ( c1.getTimeInMillis() - c2.getTimeInMillis() )/(1000*60*60);
	}
	
	private static long dateDiffMINUTE(GregorianCalendar c1,GregorianCalendar c2) {
		
		c1.set(Calendar.SECOND, 0);
		c2.set(Calendar.SECOND, 0);
		return ( c1.getTimeInMillis() - c2.getTimeInMillis() )/(1000*60);
	}
	
    private static GregorianCalendar move2WeekEnd(GregorianCalendar c) {
		
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		switch(day_of_week){
			case 1 : c.add(Calendar.DAY_OF_YEAR,6);break;
			case 2 : c.add(Calendar.DAY_OF_YEAR,5);break;
			case 3 : c.add(Calendar.DAY_OF_YEAR,4);break;
			case 4 : c.add(Calendar.DAY_OF_YEAR,3);break;
			case 5 : c.add(Calendar.DAY_OF_YEAR,2);break;
			case 6 : c.add(Calendar.DAY_OF_YEAR,1);break;
			case 7 : break;
		}
		
		return c;
	}

	public static enum DATE_DIFFTYPE{
		SECOND,MINUTE,HOUR,DAY,MONTH,YAER,WEEK
	}

	public static class StoreMSG{
		
		private int length;
		private String value;
		private String name;
		
		public StoreMSG() {
		}

		public StoreMSG(int length, String value, String name) {
			this.length = length;
			this.value = value;
			this.name = name;
		}
		
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		
	}
	
}
