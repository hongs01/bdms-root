package com.bdms.ftp.simulate;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ODFileGenerator {
	
	private static Map<String,String> statInfo=Config.getStations();
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
	
	public static List<String> getList(){
		List<String> list=new ArrayList<String>();
		for(Map.Entry<String, String> entry:statInfo.entrySet()){	
			String[] stations=entry.getValue().split(",");
			for(String stats:stations){	
				list.add(stats.split("-")[0]);
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws IOException{
	
		Long time=System.currentTimeMillis();
		List<String> list=getList();
		String content="";		
		long timeStart=0L;
		try {
			timeStart = sdf2.parse(sdf2.format(new Date(time-24*60*60*1000))).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String filename="OD_DAY_"+sdf2.format(new Date(timeStart));
//	String filepath=Config.FILE_PATH+filename;
		
	    FTPClient ftp = new FTPClient(); 
	    int reply; 
	    try {
			ftp.connect(Config.FTP_URL, Config.FTP_PORT); 
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
			ftp.login(Config.FTP_UN, Config.FTP_PS);//登录 
			reply = ftp.getReplyCode(); 
			if (!FTPReply.isPositiveCompletion(reply)) { 
			    ftp.disconnect(); 
			} 
			ftp.changeWorkingDirectory(Config.FTP_PATH);//转移到FTP服务器目录
			
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    ftp.setBufferSize(1024*10);
	    
		
	    try {
			ftp.storeFile(filename, new ByteArrayInputStream(content.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    
		long num=0L;
		System.out.println(new Date(timeStart));
//		OutputStream out=ftp.appendFileStream(filename);
		

		BufferedWriter bufw1=new BufferedWriter(new OutputStreamWriter(ftp.appendFileStream(filename)));
		
		
		
		
		for(String str:list){
			System.out.println(num++);	
			for(String str2:list){
				if(!str.equals(str2)){
					long timeL=timeStart+5*60*1000;
					while(timeL<(timeStart+24*60*60*1000)){
						content+=sdf2.format(new Date(time-24*60*60*1000))+","+sdf.format(new Date(timeL))
								+","+sdf.format(new Date(timeL))+","+str+","+str2+","+Math.round(Math.random()*100)+"\r\n";
						timeL=timeL+5*60*1000;
					}
//				out.write(content.getBytes());
				bufw1.write(content);
				content="";	
				}
			}
		}
//		out.flush();
//		out.close();
		bufw1.flush();
		bufw1.close();
			
////		System.out.println(content);		
//		String filename="OD_DAY_"+sdf2.format(new Date(timeStart));
////		String filepath=Config.FILE_PATH+filename;
////		FileUtil.saveFile(content, filepath,false);
//		FtpClientUtil.uploadStrAsFile(filename, content);
//		
//		String md5=MD5Util.MD5(content+"SSKL");
//		String filename2="MAC_DAY_"+sdf2.format(new Date(System.currentTimeMillis()));
////		String filepath2=Config.FILE_PATH+filename2;
//		String content2=MD5Util.MD5Row(filename, FtpClientUtil.rowNums(content, "\r\n"), md5);		
////		FileUtil.saveFile(content2,filepath2,true);	
//		FtpClientUtil.appendToFile(filename2, content2);
		
	}
	
}
