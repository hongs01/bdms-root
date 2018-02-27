package com.bdms.csData;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
  * Description:
  * 		超算地图坐标数据模拟
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2016-1-10下午1:54:15            1.0            Created by hongshuai
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class CSData {

	 
	private static String time ;
	 
	
	
/**********************************************************************/	
	
	
	/**
	  * description:获取字符串数据
	  * @return
	  * String
	  * 2016-1-11 下午5:21:15
	  * by hongshuai
	 */
	public String getCSData(String str){
		StringBuffer SB=new StringBuffer();
		SB.append(str);
		SB.append(";");
		SB.append(getRandomEnNum()+" area");
		SB.append(";");
		SB.append(((int)(Math.random()*3900)+100));
		SB.append(";");
		SB.append(((int)(Math.random()*3900)+100));
		SB.append(";");
		SB.append(((int)(Math.random()*3900)+100));
		SB.append(";");
		SB.append(getFourPos());
		 
		return SB.toString();
	}
	
	
	
	

	/**
	  * description:获取4个点的坐标
	  * @return
	  * String
	  * 2016-1-21 下午4:54:35
	  * by Hongs
	 */
	private String getFourPos(){
		final DecimalFormat DF = new DecimalFormat("###.000000");
	       GISRandomUtil gis = new GISRandomUtil();
		    StringBuffer SB=new StringBuffer();
			String[] position = gis.getGISData();
			double length= 0.006612;
			double width=0.005311;
			//SB.append("(");
			SB.append(DF.format(Double.parseDouble(position[0])));
			SB.append(",");
			SB.append(DF.format(Double.parseDouble(position[1])));
			//SB.append(")");
			SB.append(";");
			
			//SB.append("(");
			SB.append(DF.format(Double.parseDouble(position[0])+length));
			SB.append(",");
			SB.append(DF.format(Double.parseDouble(position[1])));
			//SB.append(")");
			SB.append(";");
			
			//SB.append("(");
			SB.append(DF.format(Double.parseDouble(position[0])+length));
			SB.append(",");
			SB.append(DF.format(Double.parseDouble(position[1])-width));
			//SB.append(")");
			SB.append(";");
			
			//SB.append("(");
			SB.append(DF.format(Double.parseDouble(position[0])));
			SB.append(",");
			SB.append(DF.format(Double.parseDouble(position[1])-width));
		//	SB.append(")");
			
			
			
			
			return SB.toString();
		}
	
	/**********************************************************************************/
	/**
	  * description:获取随机时间点
	  * @return
	  * String
	  * 2016-1-12 上午9:19:27
	  * by hongshuai
	 */
	private String getRandomTime(){
		String s[]="00:00,00:30,01:00,01:30,02:00,02:30,03:00,03:30,04:00,04:30,05:00,05:30,06:00,06:30,07:00,07:30,08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30,12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30,19:00,19:30,20:00,20:30,21:00,21:30,22:00,22:30,23:00,23:30".split(","); 
		
		return null;
	}
	
	
	/**
	  * description:获得递增日期
	  * @return
	  * String
	  * 2016-1-21 下午3:35:22
	  * by Hongs
	 */
	public static String getIncreaseDate(){
		System.out.println(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(calendar.MINUTE, 30);
		Date todate=calendar.getTime();
		time=sdf.format(todate);
		return time;
	}
	
	/**
	  * description:获得随机的区域id
	  * @return
	  * String
	  * 2016-1-21 下午3:33:31
	  * by Hongs
	 */
	private String getRandomEnNum(){
		String s[]="first,second,third,forth,fifth,sexth,seventh,eighth,ninth,tenth".split(",");
		int i=(int)(Math.random()*10);
		return s[i];
	}
	
	
	/*public Map<String,Map<String,Map<String,String>>> getCSData(int n){
		Map<String,String> map=new HashMap<String,String>();
		Map<String,String> pos=new HashMap<String,String>();
		Map<String,Map<String,String>> mmp=new HashMap<String,Map<String,String>>();
		Map<String,Map<String,Map<String,String>>> mmmp= new HashMap<String,Map<String,Map<String,String>>>();
		for(int i=1;i<=n;i++){
			pos=getPosition();
			map.put("流入",  (int)(Math.random()*3900)+100+"");
			map.put("当前",  (int)(Math.random()*3900)+100+"");
			map.put("流出",  (int)(Math.random()*3900)+100+"");
			map.put("position",pos.toString());
			mmp.put(i+"区域", map);
			
		}
		mmmp.put("0:0~0:30", mmp);
		return mmmp;
	}*/
 
	
	
	/*public Map<String,String> getPosition(){
		Map<String,String> map=new HashMap<String,String>();
		GISRandomUtil gis = new GISRandomUtil();
		
		for(int i=1;i<=4;i++){
			StringBuffer SB=new StringBuffer();
		String[] position = gis.getGISData();
		SB.append("(");
		SB.append(position[0]);
		SB.append(",");
		SB.append(position[1]);
		SB.append(")");
		map.put("pos"+i+"", SB.toString());
		}
		return map;
	}
	*/

	public static void main(String[] args){
		 
	  System.out.println(new CSData().getCSData("2015-01-01"));
	  
	}
}
