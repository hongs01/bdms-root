package com.dw.ftp.test;

import com.dw.ftp.json.DataFormat;
import com.dw.ftp.json.Employee;
import com.dw.ftp.json.JsonHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask{

		
	protected static String fileName ="D:/test3.log";
	

	
	public MyTimeTask(){		
	}

	private String produceStr(int lenth){
		
		String result = null;
		ArrayList list = new ArrayList();
		for (char c = 'a'; c <= 'z'; c++) {
			          list.add(c);
			}
		for (int i = 0; i < lenth; i++) {
			 int num = (int) (Math.random() * 26);
			 result = result + list.get(num);
		}
		return result;
	}
	
	
	
	@Override
	public void run() {
		
		DataFormat df = new DataFormat();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//产生一个随机的品牌
		String[] brands = {"huawei","xiaomi","sanxing","lianxiang","apple","nokia"};	
		//产生一个随机的分辨率
		String[] resolutions = {"240*400","480*800","480*854","1136*640","1280*768","1280*800","1024*600"};
		//产生一个随机的操作系类型统
		String[] osTypes = {"macOS","ubantu","saiban","android"};
		//产生一个随机的操作系类型统
		String[] osVersions = {"1.4.21","2.56.79","33.2.0","1.2.3"};
		String[] currentPages = {"page1","page2","page3","page4","page5","page6","page7"};
		
		String [] contentIds = {"1","4","123","654","926"};
		String [] contentTypes = {"text","html","video","photo"};
					
		String userId = produceStr(12);
		df.setUserId(userId);
		String imei = produceStr(6);
		df.setImei(imei);
		String ip = "127.0.0.1";
		df.setIp(ip);
		String longitude = "120.333";
		df.setLongitude(longitude);
		String latitude = "50.123";
		df.setLatitude(latitude);
		String hsOsVersion = "v3.0";
		df.setOsVersion(hsOsVersion);
		String brand = brands[(int)(Math.random()*brands.length)];
		df.setBrand(brand);
		String resolution = resolutions[(int)(Math.random()*resolutions.length)]; 
		df.setResolution(resolution);
		String osType = osTypes[(int)(Math.random()*osTypes.length)];
		df.setOsType(osType);
		String osVersion = osVersions[(int)(Math.random()*osVersions.length)];
		df.setOsVersion(osVersion);
		String currentPage = currentPages[(int)(Math.random()*currentPages.length)];
		df.setCurrentPage(currentPage);
		String enterTime = sdf.format(new Date());
		String leaveTime = sdf.format(new Date());
		
		df.setEnterTime(enterTime);
		
		df.setLeaveTime(leaveTime);
		
		String contentId = contentIds[(int)(Math.random()*contentIds.length)];
		df.setContentId(contentId);
		
		String operationType = "click";
		String contentType = contentTypes[(int)(Math.random()*contentTypes.length)];
		String status = "on";
		String log = produceStr(100);
		
		df.setOperationType(operationType);
		df.setContentType(contentType);
		df.setStatus(status);
		df.setLog(log);
		
		
			
		String content = new JsonHelper().toJSON(df).toString();
			
		new FileUtils().write2File(fileName, content);
		
	}
	
}
