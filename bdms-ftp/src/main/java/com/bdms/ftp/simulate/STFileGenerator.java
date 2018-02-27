package com.bdms.ftp.simulate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class STFileGenerator {
	
	private static Map<String,String> statInfo=Config.getStations();
	
//	组织站点信息字符串
	public static String getContent(String args0){
		String str="";
		for(Map.Entry<String, String> entry:statInfo.entrySet()){
//			str+=args0+","+entry.getKey()+",";
			String[] stations=entry.getValue().split(",");
			for(String stats:stations){
				String[] sta=stats.split("-");
				str+=args0+","+entry.getKey()+","+sta[0]+","+sta[1]+","+sta[2]+"\r\n";
			}
		}
		return str;
	}
	
	public static void main(String args[]) throws IOException{
		if(args.length!=1){
			System.out.println("Please input like :\n xxx.jar [版本号]");
			System.exit(0);			
		}
		
		String content=getContent(args[0]);
		long rownums=FtpClientUtil.rowNums(content,"\r\n");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String filename="STATION_INFO_"+sdf.format(new Date(System.currentTimeMillis()));
//		String filepath=Config.FILE_PATH+filename;
		
//		FileUtil.saveFile(content,filepath,false);	
		FtpClientUtil.uploadStrAsFile(filename, content);
		
		String md5=MD5Util.MD5(content+"SSKL");
		String filename2="MAC_DAY_"+sdf.format(new Date(System.currentTimeMillis()));
//		String filepath2=Config.FILE_PATH+filename2;
		String content2=MD5Util.MD5Row(filename, rownums, md5);
		
		FtpClientUtil.removeRecord(filename2, filename);
		FtpClientUtil.appendToFile(filename2, content2);
				
//		try {
//			FileUtil.removeLine(filepath2,filename2);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		FileUtil.saveFile(content2,filepath2,true);	
		
////		测试MD5	
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//		String filename="STATION_INFO_"+sdf.format(new Date(System.currentTimeMillis()))+".txt";
//		String filepath=Config.FILE_PATH+filename;
//		String tempstr;
//		try {
//			tempstr = FileUtil.readFile(filepath);
//			System.out.println(tempstr);
////			System.out.println(tempstr.equals(content));
////			System.out.println(MD5Util.MD5(tempstr+"SSKL"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
}
