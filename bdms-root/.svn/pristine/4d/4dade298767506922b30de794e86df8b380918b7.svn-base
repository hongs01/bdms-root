package com.bdms.ftp.simulate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RTFileGenerator {
	
	private static Map<String,String> statInfo=Config.getStations();
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
	
	public static String getContent(){
		String str="";
		Long time=System.currentTimeMillis();
		for(Map.Entry<String, String> entry:statInfo.entrySet()){	
			String[] stations=entry.getValue().split(",");
			for(String stats:stations){	
				str+=entry.getKey()+","+stats.split("-")[0]+","+sdf.format(new Date(time-5*60*1000))+","+sdf.format(new Date(time))+","+Math.round((Math.random()*100+1)*100)+","+Math.round((Math.random()*100+1)*100)+"\r\n";
			}
		}
		return str;
	}
	
	public static void main(String[] args){
		Long time=System.currentTimeMillis();
		String content=getContent();
		String filename="FLUXNEW_SEG_"+sdf.format(new Date(time));
//		String filepath=Config.FILE_PATH+filename;
//		FileUtil.saveFile(getContent(), filepath,false);
		FtpClientUtil.uploadStrAsFile(filename, content);
		
		
		String md5=MD5Util.MD5(content+"SSKL");
		String filename2="MAC_DAY_"+sdf2.format(new Date(System.currentTimeMillis()));
//		String filepath2=Config.FILE_PATH+filename2;
		String content2=MD5Util.MD5Row(filename, FtpClientUtil.rowNums(content, "\r\n"), md5);		
//		FileUtil.saveFile(content2,filepath2,true);	
		FtpClientUtil.appendToFile(filename2, content2);
		
	}
}
