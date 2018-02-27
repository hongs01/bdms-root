package com.bdms.ftp.simulate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DAYFileGenerator {
//	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
	
//	返回包含当天所有5分钟文件内容的字符串
	public static String getContent() throws Exception{
		String str="";	
//		Long time=System.currentTimeMillis();
		Long time=System.currentTimeMillis()-24*60*60*1000;
		String date=sdf2.format(new Date(time));
		String segFileName="FLUXNEW_SEG_"+date;
		str=FtpClientUtil.getFilesStr(segFileName);
		return str;
	}
	
	public static void main(String[] args) throws Exception{
		String content=getContent();
//		System.out.println(content);
		if(content!=null && !content.equals("")){
			String filename="FLUXNEW_DAY_"+sdf2.format(new Date(System.currentTimeMillis()-24*60*60*1000));
//			String filepath=Config.FILE_PATH+filename;
//			FileUtil.saveFile(content, filepath,false);
			FtpClientUtil.uploadStrAsFile(filename, content);
			
			
			String md5=MD5Util.MD5(content+"SSKL");
			String filename2="MAC_DAY_"+sdf2.format(new Date(System.currentTimeMillis()));
//			String filepath2=Config.FILE_PATH+filename2;
			String content2=MD5Util.MD5Row(filename, FtpClientUtil.rowNums(content, "\r\n"), md5);		
//			FileUtil.saveFile(content2,filepath2,true);	
			FtpClientUtil.appendToFile(filename2, content2);
			
		}
		
		
	}
	
}
