package com.bdms.dams.metro;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.common.lang.StringUtils;
import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.station.service.StationService;
import com.bdms.dams.util.AxisUtil;
import com.bdms.dams.util.DaySplit;
import com.bdms.dams.util.StationGISContainer;
import com.bdms.entity.dams.Criterion;
import com.bdms.entity.dams.Station;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.ResultType;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingGJ;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;

@Service(value="dayACCService")
public class DayACCServiceImpl implements DayACCService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DayACCServiceImpl.class);
	
	@Autowired
	private  HbaseService hbaseService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private CriterionService criterionService;
	
	private StationGISContainer sgc = StationGISContainer.getInstance();
	
	
	
	public void checkGISHasExists(){
		
		if(sgc.gisIsNotExist()){
			//System.err.println(stationService.findAllGis());
			sgc.setGis(stationService.findAllStas());
		}
	}
	
	
	/**
	 * description:
	 * @param dayStr 日期字串   
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public List<Map<String, String>> axisDayData(String dayStr, String station_id,ResultType type) {
		
		List<Map<String, String>> stationDayData=null;
		try {
			stationDayData = hbaseService.getStationDayData(dayStr, station_id,type);
		} catch (IOException e) {
			LOG.error("获取在时间   "  + dayStr + " 下 id为  " + station_id + " 的站点的 实时数据失败。", e);
		}
		
		
		return stationDayData;
	}
	
   /**
	  * description:  获取 预测数据
	  * @param dayStr  日期字串  
	  * @param station_id
	  * @param type   
	  * @return
	  * List<Map<String,String>>
	  * 2015-8-4 上午11:10:31
	  * by Lixc
	 */
	public List<Map<String, String>> axisDayPredicteData(String dayStr, String station_id,ResultType type) {
		
		List<Map<String, String>> stationDayData=null;
		try {
			stationDayData = hbaseService.getStationDayPredicteData(dayStr, station_id,type);
		} catch (IOException e) {
			LOG.error("获取在时间   "  + dayStr + " 下 id为  " + station_id + " 的站点的 预测数据失败。", e);
		}
		
		
		return stationDayData;
	}
	
	
	/**
	  * description:    获取最新的 人数最多的 前n个站点
	  * @param sortKey
	  * @param offset 
	  * @param n
	  * @return
	  * List<Map<String,Object>>
	  * 2015-8-6 上午10:50:56
	  * by Lixc
	 */
	public String getStationDayLatestDataTopN(SortKey sortKey,int offset,int n) {
		
		checkGISHasExists();
		
		//List<Map<String, Object>> stationDayLatestDataTopN = new ArrayList<Map<String, Object>>();
		
		//List<String> res = new ArrayList<String>();
		
		
		String[] heatData = null;
		
		
		StringBuffer sb=new StringBuffer();
		
		// [ [xx,yy,zz],[xx,xx,xx], ]
		sb.append("[");
		
		
		//Map<String,Object> tmp = null;
		
		//double[] ogis = new double[2];
		
		String id = null;
		Station gis = null;
		
		
			for( Map<String, Object> top : getLatestStreamingGJData(sortKey,offset, n)){
				
				sb.append("[");
				  
				
				heatData = new String[3];
				
				
				//tmp  = new HashMap<String,Object>();
				
				id = (String)top.get("STATION_ID");
				gis = sgc.getGis(id);
				/*ogis[0] = gis.getX();
				ogis[1] = gis.getY();*/
				
			//	heatData[0] = String.valueOf(gis.getX());
			//	heatData[1] = String.valueOf(gis.getY());
				
				sb.append(gis.getX());
				sb.append(",");
				sb.append(gis.getY());
				sb.append(",");
				
				
				
				//tmp.put("id", id);
			//	tmp.put("name", gis.getName());
				//tmp.put("gis", ogis);
			 
				switch (sortKey) {
					case ENTER_TIMES:
						//tmp.put("enter", top.get("ENTER_TIMES"));
						heatData[2] = String.valueOf(top.get("ENTER_TIMES"));
						sb.append(top.get("ENTER_TIMES"));
						sb.append("],");
						break;
					case EXIT_TIMES:
						//tmp.put("exit", top.get("EXIT_TIMES"));
						heatData[2] = String.valueOf(top.get("EXIT_TIMES"));
						sb.append(top.get("EXIT_TIMES"));
						sb.append("],");
						break;
					case ENTER_EXIT_SUB:
						//tmp.put("enter_exit_sub", top.get("ENTER_EXIT_SUB"));
						heatData[2] = String.valueOf(top.get("ENTER_EXIT_SUB"));
						sb.append(top.get("ENTER_EXIT_SUB"));
						sb.append("],");
						break;
					case ENTER_EXIT_SUM:
						//tmp.put("enter_exit_sum", top.get("ENTER_EXIT_SUM"));
						heatData[2] = String.valueOf(top.get("ENTER_EXIT_SUM"));
						sb.append(((int)top.get("ENTER_EXIT_SUM"))*100);
						sb.append("],");
						break;
				default:
					break;
				}
				 
				
				
			//	stationDayLatestDataTopN.add(tmp);
				//res.add(heatData);
				
			}
			//sb=sb.substring(0,sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			
		
		
		
		return sb.toString();
		//return sb;
	}
	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterDataTopN(int, int)
	 */
	public String getStationDayLatestEnterDataTopN(int offset,int n){
		return getStationDayLatestDataTopN(SortKey.ENTER_TIMES,offset,n);
	}
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestExistDataTopN(int, int)
	 */
	public String getStationDayLatestExistDataTopN(int offset,int n){
		return getStationDayLatestDataTopN(SortKey.EXIT_TIMES,offset,n);
	}
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterExistSubDataTopN(int, int)
	 */
	public String getStationDayLatestEnterExistSubDataTopN(int offset,int n){
		return getStationDayLatestDataTopN(SortKey.ENTER_EXIT_SUB,offset,n);
	}
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterExistSumDataTopN(int, int)
	 */
	public String getStationDayLatestEnterExistSumDataTopN(int offset,int n){
		return getStationDayLatestDataTopN(SortKey.ENTER_EXIT_SUM,offset,n);
	}
	
	/**
	 * description:
	 * @param dateTime 日期毫秒数
	 * @param station_id  站点名字/换乘站ID/count(总计人数)
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayDataforHighCharts( long dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		
		 List<Map<String, String>> stationDayData = axisDayData( simpleDateFormat.format(new Date(dateTime)).substring(0,8), sid,ResultType.DAY_ALL);
		 
		 
		 if( stationDayData != null ){
			 
			 List<long[]> inps = new ArrayList<long[]>();
			 List<long[]> outps = new ArrayList<long[]>();
			 
			 long[] ips = null;
			 long[] ops = null;
			 
			 String start = null;
			 String enter = null;
			 String exit = null;
			 
			 long time = 0;
			 
			 for( Map<String, String> res : stationDayData ){
				 
				ips = new long[2];
				ops = new long[2];
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				exit = res.get("EXIT_TIMES");
				
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				
				ips[0] = time;
				ips[1] = Long.parseLong(enter); //+ (long)(Math.random() * 10) ;
				
				inps.add(ips);
				
				ops[0] = time;
				ops[1] = Long.parseLong(exit); //+ (long)(Math.random() * 100);
				outps.add(ops);
				 
			 }
			 
			 data.put("in", inps);
			 data.put("out", outps);
			
		 }
		 
		return data;
	}
	
	/**
	 * description: 获取  实时进站 数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id  
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterDataforHighCharts( String dateTime, String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String substring = "";
		 if(dateTime==null || dateTime.equals("")){
			 substring = hbaseService.getStreamingGJLatestTimeStr().substring(0,8)+"-rt";
		 }else{
			 substring=dateTime+"-his";
		 }
		 LOG.info("输入的时间为："+substring);
		 //substring = "20150708";
		 List<Map<String, String>> stationDayData = axisDayData( substring, sid,ResultType.DAY_ENTER);
		 
		 
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			 //DaySplit instance = DaySplit.getInstance();
			 //List<Long> splits = instance.getSplits(dateTime);
			 
			 //获取当天的 早晚高峰时间段
			 //long[] beforeTime = instance.getBeforeTime();
			 //long[] afterTime = instance.getAfterTime();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			
			 int pNow = 0;
			 int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 String pDate = null;
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String enter = null;
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( substring + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row[1] = Long.parseLong(enter); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				pCount += row[1];
				
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h < DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				
				times.put(time, row[1]);
				list.add(row);
				 
			 }
			 
			 data.put("pointSize", list.size());
			 
			 Date latestDate = new Date();
			 LOG.info("更新时间是："+latestDate);
			 //获取当前最近时刻的人数
			 if( !list.isEmpty()&& list.size()>0 ){
				 int index = list.size() - 1;
				 pNow = (int) list.get(index )[1];
				 latestDate =  new Date(list.get(index)[0] + 5*60*1000 );
				 pDate = simpleDateFormat2.format(latestDate);
				 latestDate =  new Date(list.get(index)[0] + 1000 );
			 }else{
				 pNow = 0;
				 //pDate = simpleDateFormat2.format( latestDate);
				 pDate = "";
			 }
			 
			 //补点
			/*List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ips = new long[2];
				 ips[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null) left = 0l;
				 if(right == null) right = 0l;
				 
				 ips[1] = (left + right)/2;
				 inps.add(ips);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 Collections.sort(inps, new Comparator<long[]>(){

					@Override
					public int compare(long[] o1, long[] o2) {
						
						return (int) (o1[0] - o2[0]);
						
					}
					
				});*/
			 
			 data.put("data", list);
			 data.put("pNow", pNow);
			 data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			 data.put("pDate", pDate);
			 
			 setPridectDate( data,simpleDateFormat.format(latestDate), sid, ResultType.DAY_ENTER);
			
		 }
		 
		return data;
	}
	
	
	/**
	 * description: 获取 实时出站数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayExitDataforHighCharts( String dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		 
		 String substring = "";
		 if(dateTime==null || dateTime.equals("")){
			 substring = hbaseService.getStreamingGJLatestTimeStr().substring(0,8)+"-rt";
		 }else{
			 substring=dateTime+"-his";
		 }
		 LOG.info("输入的时间为："+substring);
		 
		 List<Map<String, String>> stationDayData = axisDayData( substring, sid,ResultType.DAY_EXIT);
		
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			// List<Long> splits = instance.getSplits(dateTime);
			 //获取当天的 早晚高峰时间段
			 //long[] beforeTime = instance.getBeforeTime();
			 //long[] afterTime = instance.getAfterTime();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			 int pNow = 0;
			 int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 String pDate = null;
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String exit = null;
			
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( substring + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				
				row[0] = time;
				row[1] = Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				

				//统计所有人数
				pCount += row[1];
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h < DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				times.put(time,row[1]);
				list.add(row);
				 
			 }
			 
			 data.put("pointSize", list.size());
			 
			//获取当前最近时刻的人数
			 Date latestDate = new Date();
			 if( !list.isEmpty() ){
				 int index = list.size() - 1;
				 pNow = (int) list.get(index )[1];
				 latestDate = new Date(list.get(index)[0] + 5*60*1000);
				 pDate = simpleDateFormat2.format( latestDate );
				 latestDate =  new Date(list.get(index)[0] + 1000 );
				 
			 }else{
				 pNow = 0;
//				 pDate = simpleDateFormat.format(latestDate).substring(8);
				 pDate = "";
			 }
			
			 /*List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ops = new long[2];
				 ops[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null || right == null) {
					 ops[1] = 0l ;
				 }else{
					 ops[1] = (left + right)/2;
				 }
				
				 outps.add(ops);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 
			Collections.sort(outps, new Comparator<long[]>(){

				@Override
				public int compare(long[] o1, long[] o2) {
					
					return (int) (o1[0] - o2[0]);
					
				}
				
			});*/
			 
			 data.put("data", list);
			 data.put("pNow", pNow);
			 data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			 data.put("pDate", pDate);
			 
			 setPridectDate( data,simpleDateFormat.format(latestDate), sid, ResultType.DAY_EXIT);
			
		 }
		 
	
		 
		return data;
	}
	
	
	/**
	 * description: 获取实时进站数据差 
	 * @param dateTime 时间的毫秒数
	 * @param station_id  
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterOutDiffDataforHighCharts( String dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String substring = "";
		 if(dateTime==null || dateTime.equals("")){
			 substring = hbaseService.getStreamingGJLatestTimeStr().substring(0,8)+"-rt";
		 }else{
			 substring=dateTime+"-his";
		 }
		List<Map<String, String>> stationDayData = axisDayData( substring, sid,ResultType.DAY_ALL);
		 
		 
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			// List<Long> splits = instance.getSplits(dateTime);
			 //获取当天的 早晚高峰时间段
			// long[] beforeTime = instance.getBeforeTime();
			 //long[] afterTime = instance.getAfterTime();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			
			 int pNow = 0;
			 int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 String pDate = null;
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String enter = null;
			 String exit  = null;
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( substring + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row[1] = Long.parseLong(enter) - Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				pCount += row[1];
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h <  DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				times.put(time,row[1]);
				list.add(row);
				 
			 }
			 data.put("pointSize", list.size());
			 Date latestDate = new Date();
			 if( !list.isEmpty() ){
				 int index = list.size() - 1;
				 pNow = (int) list.get(index )[1];
				 latestDate =  new Date(list.get(index)[0] + 5*60*1000);
				 pDate = simpleDateFormat2.format( latestDate );
				 latestDate =  new Date(list.get(index)[0] + 1000 );
				 
			 }else{
				 pNow = 0;
//				 pDate = simpleDateFormat.format( latestDate).substring(8);
				 pDate = "";
			 }
			 
		/*	 List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ips = new long[2];
				 ips[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null) left = 0l;
				 if(right == null) right = 0l;
				 
				 ips[1] = (left + right)/2;
				 inps.add(ips);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 Collections.sort(inps, new Comparator<long[]>(){

					@Override
					public int compare(long[] o1, long[] o2) {
						
						return (int) (o1[0] - o2[0]);
						
					}
					
				});*/
			 
			 data.put("data", list);
			 data.put("pNow", pNow);
			 data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			 data.put("pDate", pDate);
			 
			 setPridectDate( data,simpleDateFormat.format(latestDate), sid, ResultType.DAY_SUB);
			
		 }
		 
		return data;
	}
	
	
	/**
	 * description: 获取实时进站数据差统计
	 * @param dateTime 时间的毫秒数
	 * @param station_id  
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterOutDiffDataStaticforHighCharts(String dateTime, String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		 
		 String substring = "";
		 if(dateTime==null || dateTime.equals("")){
			 substring = hbaseService.getStreamingGJLatestTimeStr().substring(0,8)+"-rt";
		 }else{
			 substring=dateTime+"-his";
		 }
		 
		 List<Map<String, String>> stationDayData = axisDayData( substring, sid,ResultType.DAY_ALL);
		 
		 
		 if( stationDayData != null ){			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			 List<long[]> list2 = new ArrayList<long[]>();
			
			 int pNow = 0;
			 int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 String pDate = null;
			 
			 long[] row = null;
			 long[] row2 = null;
			 int pCountTotal=0;
			 int pCountMax=0;
			
			 
			 String start = null;
			 String enter = null;
			 String exit  = null;
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				row2 = new long[2];
				
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row2[0] = time;
				row[1] = Long.parseLong(enter) - Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				pCount += row[1];
				pCountTotal+=pCount;
				if(pCount>pCountMax){
					pCountMax=pCount;
				}
				row2[1] = pCount;
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h <  DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				times.put(time,row[1]);
				list.add(row);
				list2.add(row2);
				 
			 }
			 
			 data.put("pointSize", list.size());
			 Date latestDate = new Date();
			 
			 if( !list.isEmpty() ){
				 int index = list.size() - 1;
				 pNow = (int) list.get(index )[1];
				 latestDate =  new Date(list.get(index)[0] + 5*60*1000);
				 pDate = simpleDateFormat2.format( latestDate );
				 latestDate =  new Date(list.get(index)[0] + 1000 );
				 
			 }else{
				 pNow = 0;
				 //pDate = simpleDateFormat.format( latestDate).substring(8);
				 pDate = "";
			 }
			 
			 int size = stationDayData.size();
			 
			 int avg = 0;
			 
			 if( size != 0 ){
				 Math.round( pCountTotal / size );
			 }
			 
			 data.put("data", list2);
			 data.put("pNow", pNow);
			 data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			 data.put("pDate", pDate);
			 data.put("pCountTotal", pCountTotal);
			 data.put("pCountAvg", avg );
			 data.put("pCountMax",pCountMax);
			 data.put("yAxis","1,1000000,10000000");
			 
			 setPridectDate( data,simpleDateFormat.format(latestDate), sid, ResultType.DAY_SUB);
			
		 }
		 
		 setYAxis(data,sid, ResultType.DAY_INSIDEALL); 
		 
		return data;
	}
	
	
	/**
	 * description: 获取实时进站数据和
	 * @param dateTime 时间的毫秒数
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayExitEnterSumDataforHighCharts( String dateTime,String sid  ) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String substring = "";
		 if(dateTime==null || dateTime.equals("")){
			 substring = hbaseService.getStreamingGJLatestTimeStr().substring(0,8)+"-rt";
		 }else{
			 substring=dateTime+"-his";
		 }
		 
		List<Map<String, String>> stationDayData = axisDayData( substring, sid,ResultType.DAY_ALL);
		
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			// List<Long> splits = instance.getSplits(dateTime);
			 //获取当天的 早晚高峰时间段
			// long[] beforeTime = instance.getBeforeTime();
			// long[] afterTime = instance.getAfterTime();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			 int pNow = 0;
			 int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String exit = null;
			 String enter = null;
			 String pDate = null;
			
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			 /*start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( substring + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				exit  = res.get("EXIT_TIMES");
				enter = res.get("ENTER_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(exit) || StringUtils.isBlank(enter)  ){
					continue;
				}
				
				try {
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				
				row[0] = time;
				row[1] = Long.parseLong(exit) + Long.parseLong(enter); //+ (long)(Math.random() * 10) ;
				

				//统计所有人数
				pCount += row[1];
				
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h < DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h <  DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				times.put(time,row[1]);
				list.add(row);
				 
			 }
			 data.put("pointSize", list.size());
			//获取当前最近时刻的人数
			 Date latestDate = new Date();
			 if( !list.isEmpty() ){
				 int index = list.size() - 1;
				 pNow = (int) list.get(index )[1];
				 latestDate = new Date(list.get(index)[0] + 5*60*1000 );
				 pDate = simpleDateFormat2.format( latestDate );
				 latestDate =  new Date(list.get(index)[0]+1000);
			 }else{
				 pNow = 0;
//				 pDate = simpleDateFormat.format( latestDate).substring(8);
				 pDate = "";
			 }
			
	/*		 List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ops = new long[2];
				 ops[0] = lost;
				 
				 left =  times.get( lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null || right == null) {
					 ops[1] = 0l ;
				 }else{
					 ops[1] = (left + right)/2;
				 }
				
				 outps.add(ops);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 
			Collections.sort(outps, new Comparator<long[]>(){

				@Override
				public int compare(long[] o1, long[] o2) {
					
					return (int) (o1[0] - o2[0]);
					
				}
				
			});*/
			 
			 data.put("data", list);
			 data.put("pNow", pNow);
			 data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			 data.put("pDate", pDate);
			 
			 setPridectDate( data,simpleDateFormat.format(latestDate), sid, ResultType.DAY_SUM);
			
		 }
	
		 
		return data;
	}
	
	
	
	/**
	 * description: 获取进站的预测数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterPredicteDataforHighCharts(String dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		 
		 String dayStr = "";
		 LOG.info("dateTime is==========="+dayStr);
		 if(dateTime==null || dateTime.equals("")){
			 dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 }else{
			 dayStr=dateTime;
		 }
		 LOG.info("dayStr is==========="+dayStr);
		 
		 List<Map<String, String>> stationDayData = axisDayPredicteData( dayStr, sid,ResultType.DAY_ENTER);
		 
		 
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			 //DaySplit instance = DaySplit.getInstance();
			// List<Long> splits = instance.getSplits(dateTime);
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
				 
			 long[] row = null;
			 
			 //int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			
			 
			 String start = null;
			 String enter = null;
			
			 
			 long time = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			 /*start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( dayStr + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter)  ){
					continue;
				}
				
				try {
					start = dayStr + start.substring(8);
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row[1] = Long.parseLong(enter); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				//pCount += ips[1];
				
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h < DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				times.put(time, row[1]);
				list.add(row);
				 
			 }
			 
			/*List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ips = new long[2];
				 ips[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null) left = 0l;
				 if(right == null) right = 0l;
				 
				 ips[1] = (left + right)/2;
				 inps.add(ips);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 Collections.sort(inps, new Comparator<long[]>(){

					@Override
					public int compare(long[] o1, long[] o2) {
						
						return (int) (o1[0] - o2[0]);
						
					}
					
				});*/
			 
			 data.put("data", list);
			// data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			
		 }
		 
		 setYAxis(data,sid, ResultType.DAY_ENTER);
		 
		return data;
	}
	
	
	/**
	 * description: 获取出站的预测数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayExitPredicteDataforHighCharts( String dateTime,String sid  ) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String dayStr = "";
		 if(dateTime==null || dateTime.equals("")){
			 dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 }else{
			 dayStr=dateTime;
		 }
		 
		List<Map<String, String>> stationDayData = axisDayPredicteData( dayStr, sid,ResultType.DAY_EXIT);
		
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			 //DaySplit instance = DaySplit.getInstance();
			 //List<Long> splits = instance.getSplits(dateTime);
			
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String exit = null;
			
			 
			 long time = 0;
			 
			 //int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			 /*start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( dayStr + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				start = res.get("START_TIME");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					start = dayStr + start.substring(8);
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				
				row[0] = time;
				row[1] = Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				//pCount += ops[1];
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h < DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				
				times.put(time,row[1]);
				list.add(row);
				 
			 }
			 
			/*
			 List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ops = new long[2];
				 ops[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null || right == null) {
					 ops[1] = 0l ;
				 }else{
					 ops[1] = (left + right)/2;
				 }
				
				 outps.add(ops);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 
			Collections.sort(outps, new Comparator<long[]>(){

				@Override
				public int compare(long[] o1, long[] o2) {
					
					return (int) (o1[0] - o2[0]);
					
				}
				
			});*/
			 
			 data.put("data", list);
			// data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);

		 }
		 
		 setYAxis(data,sid, ResultType.DAY_ENTER);
		 
		return data;
	}
	
	
	/**
	 * description: 获取 进出站人数差的预测数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id  
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterOutDiffPredicteDataforHighCharts( String dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String dayStr = "";
		 if(dateTime==null || dateTime.equals("")){
			 dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 }else{
			 dayStr=dateTime;
		 }
		 
		List<Map<String, String>> stationDayData = axisDayPredicteData( dayStr, sid,ResultType.DAY_ALL);
		 
		 
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			
			 long[] row = null;
			
			 
			 String start = null;
			 String enter = null;
			 String exit  = null;
			 
			 long time = 0;
			 
			// int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( dayStr + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					start = dayStr + start.substring(8);
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row[1] = Long.parseLong(enter) - Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				//pCount += ips[1];
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h <  DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				
				times.put(time,row[1]);
				list.add(row);
				 
			 }
			
			 
			/* List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ips = new long[2];
				 ips[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null) left = 0l;
				 if(right == null) right = 0l;
				 
				 ips[1] = (left + right)/2;
				 inps.add(ips);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 Collections.sort(inps, new Comparator<long[]>(){

					@Override
					public int compare(long[] o1, long[] o2) {
						
						return (int) (o1[0] - o2[0]);
						
					}
					
				});*/
			 
			 data.put("data", list);
			// data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			
		 }
		 
		 setYAxis(data,sid, ResultType.DAY_SUB);
		 
		return data;
	}
	
	/**
	 * description: 获取 进出站人数差的预测统计数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id  
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayEnterOutDiffPredicteDataStaticforHighCharts( String dateTime,String sid  ) {
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 @SuppressWarnings("unused")
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String dayStr = "";
		 if(dateTime==null || dateTime.equals("")){
			 dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 }else{
			 dayStr=dateTime;
		 }
		 
		 //dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 List<Map<String, String>> stationDayData = axisDayPredicteData( dayStr, sid,ResultType.DAY_ALL);
		 
		 
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 List<long[]> list2 = new ArrayList<long[]>();
			
			 long[] row = null;
			 long[] row2 = null;
			 
			 String start = null;
			 String enter = null;
			 String exit  = null;
			 
			 long time = 0;
			 
			 int pCount = 0;
			 @SuppressWarnings("unused")
			int pCountTotal=0;
			 int pCountMax=0;
			 
			 int pBefore = 0;
			 int pAfter = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( dayStr + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				row2 = new long[2];
				
				start = res.get("START_TIME");
				enter = res.get("ENTER_TIMES");
				exit  = res.get("EXIT_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(enter) || StringUtils.isBlank(exit)  ){
					continue;
				}
				
				try {
					start = dayStr + start.substring(8);
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				row[0] = time;
				row2[0] = time;
				row[1] = Long.parseLong(enter) - Long.parseLong(exit); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				pCount += row[1];
				pCountTotal+=pCount;
				if(pCount>pCountMax){
					pCountMax=pCount;
				}
				row2[1] = pCount;
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h <  DaySplit.endAfterHour ){
					pAfter += row[1];
				}
				
				
				times.put(time,row[1]);
				list.add(row);
				list2.add(row2);
				 
			 }
			
			 
			/* List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ips = new long[2];
				 ips[0] = lost;
				 
				 left =  times.get(lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null) left = 0l;
				 if(right == null) right = 0l;
				 
				 ips[1] = (left + right)/2;
				 inps.add(ips);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 Collections.sort(inps, new Comparator<long[]>(){

					@Override
					public int compare(long[] o1, long[] o2) {
						
						return (int) (o1[0] - o2[0]);
						
					}
					
				});*/
			 
			 data.put("data", list2);
			// data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			
		 }
		 
		 setYAxis(data,sid, ResultType.DAY_SUB);
		 
		return data;
	}	
	
	/**
	 * description: 获取 进出站人数和的预测数据
	 * @param dateTime 时间的毫秒数
	 * @param station_id 
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public Map<String, Object> axisDayExitEnterSumPredicteDataforHighCharts(String dateTime,String sid  ) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 
		 Map<String, Object> data = new HashMap<String,Object>();
		
		 String dayStr = "";
		 if(dateTime==null || dateTime.equals("")){
			 dayStr = hbaseService.getStreamingGJLatestTimeStr().substring(0,8);
		 }else{
			 dayStr=dateTime;
		 }
		 
		List<Map<String, String>> stationDayData = axisDayPredicteData( dayStr, sid,ResultType.DAY_ALL);
		
		 if( stationDayData != null ){
			 
			 //获取当天所有点的 时间
			// DaySplit instance = DaySplit.getInstance();
			 
			 //存储有数据的时间点
			 Map<Long,Long>  times = new HashMap<Long,Long>();
			 
			 List<long[]> list = new ArrayList<long[]>();
			 
			 long[] row = null;
			
			 
			 String start = null;
			 String exit = null;
			 String enter = null;
			
			 
			 long time = 0;
			 
			 //int pCount = 0;
			 int pBefore = 0;
			 int pAfter = 0;
			 
			 String hour = null;
			 int h = 0;
			 
			/* start = stationDayData.get(0).get("START_TIME");
			 if(!start.endsWith("000000")){
				 try {
					 time = simpleDateFormat.parse( dayStr + "000000" ).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
				}
				row = new long[2];
				row[0] = time;
				row[1] = 0;
				
				list.add(row);
			 }*/
			 
			 for( Map<String, String> res : stationDayData ){
				 
				row = new long[2];
				
				
				start = res.get("START_TIME");
				exit  = res.get("EXIT_TIMES");
				enter = res.get("ENTER_TIMES");
				
				if( StringUtils.isBlank(start) || StringUtils.isBlank(exit) || StringUtils.isBlank(enter)  ){
					continue;
				}
				
				try {
					start = dayStr + start.substring(8);
					time = simpleDateFormat.parse(start).getTime();
				} catch (ParseException e) {
					LOG.error("时间转换失败。",e);
					//System.err.println( "错误数据 ：  " +  res  );
				}
				
				row[0] = time;
				row[1] = Long.parseLong(exit) + Long.parseLong(enter); //+ (long)(Math.random() * 10) ;
				
				//统计所有人数
				//pCount += ops[1];
				
				hour = start.substring(8,10);
				if(hour.startsWith("0")){
					hour.substring(1);
				}
				
				h = Integer.parseInt(hour);
				
				//统计 早高峰人数
				if( h >= DaySplit.startBeforeHour && h <  DaySplit.endBeforeHour ){
					pBefore += row[1];
				}
				
				//统计 晚峰人数
				if( h >= DaySplit.startAfterHour && h < DaySplit.endAfterHour ){
					pAfter += row[1];
				}

				times.put(time,row[1]);
				list.add(row);
				 
			 }
			 
			
			/* List<Long> losts = (List<Long>) CollectionUtils.subtract(splits,times.keySet()); 
			
			 Long left = 0l ;
			 Long right = 0l;
			 
			 for( long lost : losts  ){
				 
				 ops = new long[2];
				 ops[0] = lost;
				 
				 left =  times.get( lost - DaySplit.gap );
				 right = times.get( lost + DaySplit.gap );
				 
				 if(left == null || right == null) {
					 ops[1] = 0l ;
				 }else{
					 ops[1] = (left + right)/2;
				 }
				
				 outps.add(ops);
				 
			 }
			 
			 times = null;
			 losts = null;
			 
			 
			Collections.sort(outps, new Comparator<long[]>(){

				@Override
				public int compare(long[] o1, long[] o2) {
					
					return (int) (o1[0] - o2[0]);
					
				}
				
			});
*/			 
			 data.put("data", list);
			// data.put("pCount", pCount);
			 data.put("pBefore", pBefore);
			 data.put("pAfter", pAfter);
			
		 }
	
		 setYAxis(data,sid, ResultType.DAY_SUM);
		 
		 return data;
	}
	
	
	
	
	
	/**
	 * description:
	 * @param dayStr 日期字串    格式为 ：  yyyMM
	 * @param station_id  站点名字
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	public List<Map<String, String>> axisMonthData(String monthStr, String station_id) {
		
		List<Map<String, String>> stationDayData=null;
		try {
			stationDayData = hbaseService.getStationMonthata( monthStr, station_id);
		} catch (IOException e) {
			LOG.error("获取在时间   "  + monthStr + " 下 id为  " + station_id + " 的站点的 数据失败。", e);
		}
		
		
		return stationDayData;
	}

	
	public Axis<String> DayACCAxis(List<String> axisData) {
		// TODO Auto-generated method stub
		return null;
	}

	//生成每天的实时图
	public Option SDayDataOption() {
		
		
		List<Map<String, String>> stationDayData=axisDayData("20150626","0242",ResultType.DAY_ALL);
				
		List<Integer> outdataL=new ArrayList<Integer>();
		
		List<Integer> indataL=new ArrayList<Integer>();
		
		List<String> xtimeL=new ArrayList<String>();
		
		for (Map<String, String> data : stationDayData) {
			outdataL.add((Integer.parseInt(data.get("EXIT_TIMES"))));
			indataL.add((Integer.parseInt(data.get("ENTER_TIMES"))));
			xtimeL.add(data.get("START_TIME").substring(8));
		}
		
		Object[] outdata= outdataL.toArray();
		Object[] indata= indataL.toArray();
		Object[] xtime= xtimeL.toArray();
		
		Option option = new Option();
		option.legend("出站");
		option.legend("进站");

		option.toolbox()
				.show(true)
				.feature(Tool.mark, Tool.dataView,
						new MagicType(Magic.line, Magic.bar), Tool.restore,
						Tool.saveAsImage);

		option.calculable(true);
		option.tooltip().trigger(Trigger.axis);
				//.formatter("Temperature : <br/>{b}km : {c}°C");
		//option.tooltip().formatter("{b},{c}{d}");
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.max(2000);
		valueAxis.min(0);
		valueAxis.axisLabel().formatter("{value} 人").interval(30);
		valueAxis.axisLabel();
		//valueAxis.data(01,02,03,04,05,06,07,10,11,12);
		option.yAxis(valueAxis);
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.axisLine().onZero(false);
		categoryAxis.axisLabel().formatter("{value}").interval(5);
		categoryAxis.axisLabel().rotate(30);
		categoryAxis.boundaryGap(false);
		categoryAxis.data(xtime);
		option.xAxis(categoryAxis);

		Line line1 = new Line();
		Line line2 = new Line();
		
		line1.smooth(true).name("出站")
			 	.data(outdata)
				.itemStyle().normal().lineStyle()
				.shadowColor("rgba(0,0,0,0.4)");
		line2.smooth(true).name("进站")
		.data(indata)
		.itemStyle().normal().lineStyle()
		.shadowColor("rgba(255,0,0,0.4)");
		
		
		option.series(line1);
		option.series(line2);
		return option;
	}
	
	//生成每月的统计图
	public Option SMonthDataOption(){
		
		List<Map<String, String>> stationDayData=axisMonthData("201506","0743");
		
		List<Integer> outdataL=new ArrayList<Integer>();
		
		List<Integer> indataL=new ArrayList<Integer>();
		
		List<String> xtimeL=new ArrayList<String>();
		
		for (Map<String, String> data : stationDayData) {
			outdataL.add((Integer.parseInt(data.get("EXIT_AVG"))));
			indataL.add((Integer.parseInt(data.get("ENTER_AVG"))));
			xtimeL.add(data.get("DAY").substring(6));
		}
		
		Object[] outdata= outdataL.toArray();
		Object[] indata= indataL.toArray();
		Object[] xtime= xtimeL.toArray();
		
		
		Option option = new Option();
		
		option.legend("当月客流量");
		option.legend("上个月客流量");

		option.toolbox()
				.show(true)
				.feature(Tool.mark, Tool.dataView,
						new MagicType(Magic.line, Magic.bar), Tool.restore,
						Tool.saveAsImage);

		option.calculable(true);
		option.tooltip().trigger(Trigger.axis);
		//		.formatter("Temperature : <br/>{b}km : {c}°C");

		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value} 百人");
		
		option.yAxis(valueAxis);

		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.axisLine().onZero(false);
		categoryAxis.axisLabel().formatter("{value} 日");
		categoryAxis.boundaryGap(false);
		categoryAxis.data(xtime);
		option.xAxis(categoryAxis);

		Bar bar1 = new Bar();
		Bar bar2 = new Bar();
		
		bar1.name("当月进站客流量")
				.data(indata)
				.itemStyle().normal().barBorderColor();
		bar2.name("当月出站客流量")
		        .data(outdata)
		        .itemStyle().normal().barBorderColor();

		option.series(bar1);
		option.series(bar2);
		
		return  option;
		 
		
	}

	//获取 当天的 某站点的数据 并生产图片 option
	public void getStreamingDayDataOption(){
		
		/*HighchartsOptionFactory highchartsFactory = new JsoHighchartsOptionFactory();
		
		HighchartsLayoutPanel highchartsLayoutPanel = new HighchartsLayoutPanel();
		
		ChartOptions options = highchartsFactory.createChartOptions();
		
		
		options.chart().type("column");
		options.chart().margin().push(75);
		options.chart().options3d().enabled(true).alpha(15).beta(15).depth(50).viewDistance(25);

		options.title().text("Chart rotation demo");
		options.subtitle().text("Test options by dragging the sliders below");

		options.plotOptions().column().depth(25);

		SeriesColumn series = highchartsFactory.createSeriesColumn();

		ArrayNumber data = series.dataAsArrayNumber();
		data.push(29.9);
		data.push(71.5);
		data.push(106.4);
		data.push(129.2);
		data.push(144.0);
		data.push(176.0);
		data.push(135.6);
		data.push(148.5);
		data.push(216.4);
		data.push(194.1);
		data.push(95.6);
		data.push(54.4);

		//Event callback
		series.addClickHandler(new ClickHandler()
		{
		    @Override
		    public void onClick(ClickEvent clickEvent)
		    {
		        Window.alert("The series has been clicked");
		    }
		});

		//Function callback
		series.tooltip().pointFormatter(new PointFormatterCallback()
		{
		    
		    @Override
		    public String onCallback(Point Point)
		    {
		        String value = "Custom point tooltip, point " + Point.categoryAsString() + ", value: " +  Point.y();
		        return value;
		    }
		});

		options.series().addToEnd(series);

		// To set a function as string if needed (formatter case with no context object)
		String function = "function () " +
		    "{" +
		        "return 'The value for <b>' + this.x +'</b> is <b>' + this.y + '</b>';"+
		    "}";
		    
		options.tooltip().setFunctionAsString("formatter", function);

		//To set a field if no typed setter is available
		options.setFieldAsJsonObject("colorAxis", "{ \"minColor\" : \"#FFFFFF\", \"maxColor\" : \"#7cb5ec\"}");

		highchartsLayoutPanel.renderChart(options);*/
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#dayODtopNData(int)
	 */
	public List<String> dayODtopNData(int n) {
		
		checkGISHasExists();
		
		List<Map<String,Object>> dayODtop100Data= new ArrayList<Map<String,Object>>();
		
		StringBuffer geo   = new StringBuffer();
		geo.append("{");
		StringBuffer name  = new StringBuffer();
		name.append("[");
		StringBuffer od    = new StringBuffer();
		od.append("[");
		
		
		@SuppressWarnings("unused")
		Map<String,Object> tmp = null;
		
		@SuppressWarnings("unused")
		double[] ogis = new double[2];
		@SuppressWarnings("unused")
		double[] dgis = new double[2];
		
		String d_id = null;
		String o_id = null;
		
		Station gis = null;
		
		try {
			int i = 0;
			for(Map<String,Object> map : hbaseService.getDayODtopNData(0,n)){
				
				i += 1;
				
				o_id = (String)map.get("O");
				gis = sgc.getGis(o_id);
				
				geo.append("'");
				geo.append( gis.getName());
				geo.append("':[");
				geo.append( gis.getX());
				geo.append( ",");
				geo.append( gis.getY());
				geo.append("],");
				
				name.append("{");
				name.append("name:'");
				name.append( gis.getName());
				name.append( "'},");
				
				od.append("[");
				od.append("{");
				od.append("name:'");
				od.append( gis.getName());
				od.append( "'},");
				
				
				d_id = (String)map.get("D");
				gis = sgc.getGis(d_id);
				geo.append("'");
				geo.append( gis.getName());
				geo.append("':[");
				geo.append( gis.getX());
				geo.append( ",");
				geo.append( gis.getY());
				geo.append("]");
				
				name.append("{");
				name.append("name:'");
				name.append( gis.getName());
				name.append( "'}");
				
				
				od.append("{");
				od.append("name:'");
				od.append( gis.getName());
				od.append( "'}");
				od.append("]");
				
				
				if( i < n ){
					geo.append(",");
					name.append(",");
					od.append(",");
				}
				
				/*tmp  = new HashMap<String,Object>();
				
				
				ogis[0] = gis.getX();
				ogis[1] = gis.getY();
				tmp.put("o_id", o_id);
				tmp.put("o_name", gis.getName());
				tmp.put("o_gis", ogis);
				
				
				
				gis = sgc.getGis(d_id);
				dgis[0] = gis.getX();
				dgis[1] = gis.getY();
				tmp.put("d_name", gis.getName());
				tmp.put("d_id", d_id);
				tmp.put("d_gis", dgis);
				
				tmp.put("pass_sum", map.get("PASS_NUM"));*/
				
				
				//dayODtop100Data.add(tmp);
			}
			
			geo.append("}");
			name.append("]");
			od.append("]");
			
			
		} catch (IOException e) {
			LOG.error("获取数据失败。", e);
		}
		
		List<String> res = new ArrayList<String>();
		res.add(geo.toString());
		res.add(od.toString());
		
		String string = name.toString();
		res.add(string);
		res.add(string);
		
		return res;
	}

	//加载指定站点 当前时刻的 预测值
	private void setPridectDate( Map<String, Object> data,String dayStr, String station_id,ResultType type){
		
		List<Map<String, Integer>> predicteData = null;
		
		try {
				predicteData = hbaseService.getPredicteData(dayStr,station_id, type);
		} catch (IOException e) {
				LOG.error("获取预测数据失败。", e);
		}
		
		 int pNow = 0;
		 int pCount = 0;
		 //int pBefore = 0;
		// int pAfter = 0;
		 
		// int phour = 0;
		
		 for(Map<String, Integer> map : predicteData ){
			 
			 pNow = map.get("count");
			 pCount = pCount + pNow;

		 }
		 
		 data.put("hNow", pNow);
		 data.put("hCount", pCount);
		// data.put("hBefore", pBefore);
		// data.put("hAfter", pAfter);
		 setYAxis(data, station_id, type);
		 
		 
	}

	//为指定站点加载y轴范围配置
	private void setYAxis( Map<String, Object> data,String station_id,ResultType rt){
		
		String type = null;
		
		boolean isStation = true;
		
		if("0098".equals(station_id)){
			
			isStation = false;
			
			switch (rt) {
				case DAY_ENTER:
					type = "transLineEnterRT";
					break;
				case DAY_EXIT:
					type = "transLineExitRT";
					break;
				case DAY_SUM:
					type = "transLineSumRT";
					break;
				case DAY_SUB:
					type = "transLineSubRT";
					break;
				default:
					type = "transLineEnterRT";
					break;
				}
			
		}
		
	  if("0099".equals(station_id)){
		  
		  isStation = false;
			
		 switch (rt) {
			case DAY_ENTER:
				type = "allEnterRT";
				break;
			case DAY_EXIT:
				type = "allExitRT";
				break;
			case DAY_SUM:
				type = "allSumRT";
				break;
			case DAY_SUB:
				type = "allSubRT";
				break;
			case DAY_INSIDEALL:
				type = "allInsideRT";
				break;
			default:
				type = "allEnterRT";
				break;
			}
		}
		
		if(station_id.endsWith("00")){
			
			isStation = false;
			
			 switch (rt) {
				case DAY_ENTER:
					type = "lineEnterRT";
					break;
				case DAY_EXIT:
					type = "lineExitRT";
					break;
				case DAY_SUM:
					type = "lineSumRT";
					break;
				case DAY_SUB:
					type = "lineSubRT";
					break;
				default:
					type = "lineEnterRT";
					break;
				}
			
		}
		
		if(isStation){
			
			 switch (rt) {
				case DAY_ENTER:
					type = "stationEnterRT";
					break;
				case DAY_EXIT:
					type = "stationExitRT";
					break;
				case DAY_SUM:
					type = "stationSumRT";
					break;
				case DAY_SUB:
					type = "stationSubRT";
					break;
				default:
					type = "stationEnterRT";
					break;
				}
		}
		Criterion criter = criterionService.findByCodeAndType(station_id, type);

		String level = criter.getLevel();
		if(level == null ){
			LOG.error("数据库中 station_id为" + station_id + "的level字段为空");
		}
		String[] split = level.split(",");
		
		
		
//		int begain = 0;
//		if(rt.compareTo(ResultType.DAY_SUB) == 0 ){
//			begain = Integer.MIN_VALUE;
//		}
//		
//		String yAxis = AxisUtil.getYAxis(String.valueOf(begain),split[0],split[0],split[1],split[1],split[2],split[2],String.valueOf(Integer.MAX_VALUE));
		
		
		int begin = 0;
		String yAxis = "";
		//进出差
		if(rt.compareTo(ResultType.DAY_SUB) == 0){
			
			//begin = -Integer.parseInt(split[2]);
			begin = Integer.MIN_VALUE;
			yAxis = AxisUtil.getYAxis(String.valueOf(begin), "-"+split[2],
					"-"+split[2], "-"+split[1], 
					"-"+split[1],"-"+split[0], 
					"-"+split[0], split[0], 
					split[0], split[1], split[1], split[2], split[2], String.valueOf(Integer.MAX_VALUE));

			
		}else {
			
			yAxis = AxisUtil.getYAxis(String.valueOf(begin),split[0],split[0],split[1],split[1],split[2],split[2],String.valueOf(Integer.MAX_VALUE));
		}
		
		data.put("yAxis", yAxis);
		
	}


	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterDataTopNForRight(int)
	 */
	public Map<String,Object> getStationDayLatestEnterDataTopNForRight(
			int n) {
		 
		 return getStationDayLatestEnterDataTopNForAll(n, SortKey.ENTER_TIMES);
		 
	}
	
	
	private Map<String, Object> getStationDayLatestEnterDataTopNForAll(int n, SortKey key){
		
	   
		
		//存储结果
		Map<String, Object> result = new HashMap<String,Object>();
		
		//右侧数据
		List<Map<String, String>> rightData = new ArrayList<Map<String, String>>();
		 
		
		//最新的查询数据
		List<Map<String, Object>> top =  getLatestStreamingGJData(key,0,n);
		
		System.out.println( top );
		
		 if(top.size() > 0 ){
			 //单条右侧数据
			 Map<String, String> rdata = null;
			 //头部数据
			 Map<String, String> tdata = new HashMap<String,String>();
			
			 Map<String, Object> topMap = top.get(0);
			 
			 String  id  = (String)topMap.get("STATION_ID");
			 Station gis = sgc.getGis(id);
			 
			 tdata.put("name", gis.getName());
			 tdata.put("ename", gis.getEname());
			 
			 switch (key) {
			    case ENTER_TIMES:
			    	tdata.put("count", String.valueOf(topMap.get("ENTER_TIMES")));
			    break;
				
			    case EXIT_TIMES : 	 
			    	tdata.put("count", String.valueOf(topMap.get("EXIT_TIMES")));
			    break;
			    case ENTER_EXIT_SUB:	
			    	tdata.put("count", String.valueOf(topMap.get("ENTER_EXIT_SUB")));
			    break;
			    case ENTER_EXIT_SUM :	
			    	tdata.put("count", String.valueOf(topMap.get("ENTER_EXIT_SUM")));
			    break;

			default:
				break;
			}
			 
			 String t = (String)topMap.get("START_TIME");
			 tdata.put("updateTime",t.substring(8,10) + ":" + t.substring(10,12) + ":" + t.substring(12));
			 
			 int totoal = 0;
			 
			 int offset = 0;
			 int enter = 0;
			 for(Map<String, Object> map : top ){
				 
				 switch (key) {
				    case ENTER_TIMES:
				    	 enter = (int)map.get("ENTER_TIMES");
				    break;
					
				    case EXIT_TIMES : 	 
				    	 enter = (int)map.get("EXIT_TIMES");
				    break;
				    case ENTER_EXIT_SUB:	
				    	 enter = (int)map.get("ENTER_EXIT_SUB");
				    break;
				    case ENTER_EXIT_SUM :	
				    	 enter = (int)map.get("ENTER_EXIT_SUM");
				    break;

				default:
					break;
				}
				 
				 if( offset < n ){
					 
					 id = (String)map.get("STATION_ID");
					 gis = sgc.getGis(id);
					 
					 rdata = new HashMap<String,String>();
					 rdata.put("name", gis.getName());
					 rdata.put("line", gis.getLine());
					 rdata.put("count", String.valueOf(enter));
					 rdata.put("level", gis.getLevel());
					 
					 rightData.add(rdata);
				 }
				 
				 totoal += enter;
				 offset += 1;
			 }
			 
			 tdata.put("totoal", String.valueOf(totoal));
			 //添加数据
			 result.put("right", rightData);
			 result.put("top", tdata);
			 
			
		 }
		 
		 return result;
	}
	
	
	
	@Override
	public List<String> dayODtopNStaionPairs(int n) {
		checkGISHasExists();
		List<String> list=new ArrayList<String>();
		try {
			for(Map<String,Object> map : hbaseService.getDayODtopNData(0,n)){
				list.add(map.get("O")+"-"+map.get("D"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String,Object>>  dayODtopNStaionNum(int offset,int n) {
		
		checkGISHasExists();
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> lineData = null;
		
		Station gis = null;
		
		String o_id = null;
		String d_id = null;
		
		try {
			for(Map<String,Object> map : hbaseService.getDayODtopNData(offset,n)){
				
				lineData = new HashMap<String,Object>();
				
				o_id = (String)map.get("O");
				gis = sgc.getGis(o_id);
				lineData.put("ost", gis.getName());
				
				d_id = (String)map.get("D");
				gis = sgc.getGis(d_id);
				lineData.put("dst", gis.getName());
				
				lineData.put("count", map.get("PASS_NUM"));
				
				list.add(lineData);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.dams.metro.DayACCService#getStationDayLatestDataTopNAddAllTimes
	 * (com.bdms.hbse.enums.SortKey, int, int)
	 */
	public String getStationDayLatestDataTopNAddAllTimes(SortKey sortKey,
			int offset, int n) {
		checkGISHasExists();
		List<Map<String, Object>> heatStations = getLatestStreamingGJData(sortKey,offset, n);
		String id = null;
		Station gis = null;
		Double gisx = null;
		Double gisy = null;
		Integer times = null;
		String date=null;
		String newDate=null;

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (Map<String, Object> top : heatStations) {
			id = (String) top.get("STATION_ID");
			gis = sgc.getGis(id);
			gisx = gis.getX();
			gisy = gis.getY();
			times = Integer.parseInt(top.get("ENTER_TIMES").toString());
			
			date=top.get("START_TIME").toString();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss");
			java.util.Date dd=null;
			
			try {
				dd = sdf.parse(date);
				newDate=sdf2.format(new Date(dd.getTime()+5*60*1000));
			} catch (ParseException e) {
				e.printStackTrace();
			}

//			for(int i=0;i<times;i++)
//			{
				sb.append("[\"");
				sb.append(gisx);
				sb.append("\",\"");
				sb.append(gisy);
				sb.append("\",\"");
				sb.append(times);
				sb.append("\",\"");
				sb.append(newDate);
				sb.append("\"],");
//			}

		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");

		return sb.toString();

	}




/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestExitDataTopNForRight(int)
	 */
	public Map<String, Object> getStationDayLatestExitDataTopNForRight(int n) {
		 return getStationDayLatestEnterDataTopNForAll(n, SortKey.EXIT_TIMES);
	}

	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterExitSubDataTopNForRight(int)
	 */
	public Map<String, Object> getStationDayLatestEnterExitSubDataTopNForRight(
			int n) {
		 return getStationDayLatestEnterDataTopNForAll(n, SortKey.ENTER_EXIT_SUB);
	}


	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCService#getStationDayLatestEnterExitSumDataTopNForRight(int)
	 */
	public Map<String, Object> getStationDayLatestEnterExitSumDataTopNForRight(
			int n) {
		 return getStationDayLatestEnterDataTopNForAll(n, SortKey.ENTER_EXIT_SUM);
	}

	
	private List<Map<String, Object>> getLatestStreamingGJData(SortKey key,int offset,int n ){
		
		checkGISHasExists();
	
		List<Map<String, Object>> latestData = hbaseService.getGJLatestDataForACC(sgc.getAllStationId(), key);
		List<Map<String, Object>> topNByLatestData = getTopNByLatestData(latestData, key, offset, n);
		
		latestData = null;
		
		return topNByLatestData;
		
		
	}
	
	
	// 根据人数排序,并截取
	private List<Map<String, Object>> getTopNByLatestData(List<Map<String, Object>> latestData, SortKey sortKey, int offset,int n) {
		
		switch (sortKey) {
		
			case ENTER_TIMES:
				
				Collections.sort(latestData, new Comparator<Map<String, Object>>() {
					
					private String name = StreamingGJ.ENTER_TIMES.getName();
					
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						
						return (int)(o2.get(name))
								- (int)(o1.get(name));
					}
	
				});
				break;
		case EXIT_TIMES:
			
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				private String name = StreamingGJ.EXIT_TIMES.getName();
				
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int)(o2.get(name))
							- (int)(o1.get(name));
				}

			});
			break;
		case ENTER_EXIT_SUM:
			
			
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {
				
				public int compare(Map<String, Object> o1,
						Map<String,Object> o2) {

					return (int)(o2.get("ENTER_EXIT_SUM"))
							- (int)(o1.get("ENTER_EXIT_SUM"));
				}

			});
			break;
		case ENTER_EXIT_SUB:
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int)(o2.get("ENTER_EXIT_SUB"))
							- (int)(o1.get("ENTER_EXIT_SUB"));
				}

			});
			break;

		default:
			LOG.error("未知错误");
			break;
		}
		int size = latestData.size();
		int toIndex = offset + n;
		
		if(size > 0 ){

			if (offset >= size) {
				offset = size - 1;
			}
	
			if (toIndex > size) {
	
				toIndex = size;
			}
		}else{
			return new ArrayList<Map<String, Object>>();
		}

		return latestData.subList(offset, toIndex);

	}


	@Override
	public List<Map<String, Object>> getODtopNStaionNumForOneDay(String date,int offset,
			int n) {
		checkGISHasExists();
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> lineData = null;
		
		Station gis = null;
		
		String o_id = null;
		String d_id = null;
		
		try {
			for(Map<String,Object> map : hbaseService.getODtopNDataForOneDay(date,offset,n)){
				
				lineData = new HashMap<String,Object>();
				
				o_id = (String)map.get("O");
				gis = sgc.getGis(o_id);
				lineData.put("ost", gis.getName());
				
				d_id = (String)map.get("D");
				gis = sgc.getGis(d_id);
				lineData.put("dst", gis.getName());
				
				lineData.put("count", map.get("PASS_NUM"));
				
				list.add(lineData);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<String> getODtopNStaionPairsForOneDay(String dateTime,int n) {
		checkGISHasExists();
		List<String> list=new ArrayList<String>();
		try {
			for(Map<String,Object> map : hbaseService.getODtopNDataForOneDay(dateTime, 0, 10)){
				list.add(map.get("O")+"-"+map.get("D"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}	

}
