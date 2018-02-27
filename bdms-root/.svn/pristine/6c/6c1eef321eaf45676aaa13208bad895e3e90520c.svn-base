package com.bdms.dams.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaySplit {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(DaySplit.class);
	
	private static final  SimpleDateFormat LONG2STR = new SimpleDateFormat("yyyyMMdd");
	private static final  SimpleDateFormat STR2LONG = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final int gap = 5 * 60 * 1000;
	
	public static final int startBeforeHour = 7;
	public static final int endBeforeHour = 9;
	
	public static final int startAfterHour = 17;
	public static final int endAfterHour = 19;
	
	
	private static final int startBefore = 60 * 60 * 1000 * startBeforeHour;
	private static final int endBefore   = 60 * 60 * 1000 * endBeforeHour;
	
	private static final int startAfter = 60 * 60 * 1000 * startAfterHour;
	private static final int endAfter  = 60 * 60 * 1000 *  endAfterHour;
	
	public  long time;
	
	
	
	private static final int steps = 24 * 12;
	
	private static DaySplit day ;
	
	private String dateStr;
	
	private List<Long> splits;
	
	private long[] before = new long[2];
	private long[] after  = new long[2];
	
	private DaySplit(){}
	
	public static DaySplit getInstance(){
		
		if(day == null ){
			
			synchronized (DaySplit.class) {
				
				if( day == null ){
					
					day = new DaySplit();
				}
			}
			
		}
		
		return day;
	}
	
	public List<Long> getSplits( long dateTime ){
		
		String tmp = LONG2STR.format(new Date(dateTime)) + "000000";
		
		if( dateStr == null || !tmp.equals(dateStr) ){
			
			dateStr = tmp ;
			try {
				time = STR2LONG.parse(dateStr).getTime();
			} catch (ParseException e) {
				LOG.error("时间转换失败.", e);
			}
			
			//设定早晚高峰的时间
			before[0] = time + startBefore;
			before[1] = time + endBefore;
			
			after[0] = time + startAfter;
			after[1] = time + endAfter;
			
			splits =  doSplits();
		}
		
		return splits;
	}
	
	private  List<Long> doSplits(){
		
		splits = new ArrayList<Long>();
		
		for( int i = 0 ; i < steps ; i++ ){
			
			splits.add( time + i * gap );
		}
		
		return splits;
		
	}
	
	
	public long[] getBeforeTime(){
		
		return before;
	}
	
	public long[] getAfterTime(){
		
		return after;
	}
	
	
	public static void main(String[] args) throws ParseException {
		
	Map<Long,Long> a = new HashMap<Long,Long>();
	
	}
}
