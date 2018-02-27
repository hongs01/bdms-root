package com.bdms.flume.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.bdms.csData.CSData;

public class App 
{
    public static void main( String[] args )
    {
    	MyRpcClientFacade client = new MyRpcClientFacade();
		client.init("dswhhadoop-64", 44444);
		
		Date date = null;
		String dateStr = "2014-01-01 00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		do {			
			try {
				date = sdf.parse(dateStr);				
				client.sendDataToFlume(new CSData().getCSData(dateStr));
				System.out.println( new CSData().getCSData(dateStr));
				
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.MINUTE, 30);
				Date nextTime = calendar.getTime();
				dateStr = sdf.format(nextTime);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} while (!dateStr.equals("2016-02-01 00:00"));
	}
}
