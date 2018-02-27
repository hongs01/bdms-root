package com.bdms.ftp.simulate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
//import org.apache.oro.text.regex.MalformedPatternException;

public class FtpClientUtil {
	
////	ftp参数
//	public final static String FTP_URL="dswhhadoop-30";
//	public final static int FTP_PORT=2222;
//	public final static String FTP_UN="dswh";
//	public final static String FTP_PS="1234567";
	
	public static FTPClient getFtp() {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(Config.FTP_URL, Config.FTP_PORT);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(Config.FTP_UN, Config.FTP_PS);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return ftp;
	}
	
	/**
	* Description: 向FTP服务器上传文件
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目录
	* @param filename 上传到FTP服务器上的文件名
	* @param content 输入字符串
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean uploadStrAsFile(String filename, String content) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		InputStream input = null;
		try {
			int reply;
			ftp.connect(Config.FTP_URL, Config.FTP_PORT);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(Config.FTP_UN, Config.FTP_PS);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(Config.FTP_PATH);
			input = new ByteArrayInputStream(content.getBytes("utf-8"));
			ftp.storeFile(filename, input);		
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					if(input!=null){
						input.close();
					}
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	/**
	* Description: 从FTP服务器读取文件 内容
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要读取的文件名
	* @return 返回文件内容字符串
	*/ 
	public static String getFileStr(String fileName) { 
		StringBuffer sb=new StringBuffer();
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(Config.FTP_URL, Config.FTP_PORT); 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(Config.FTP_UN, Config.FTP_PS);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return ""; 
	        } 
	        ftp.changeWorkingDirectory(Config.FTP_PATH);//转移到FTP服务器目录 
	        FTPFile[] fs = ftp.listFiles(); 
	        for(FTPFile ff:fs){ 
	            if(ff.getName().equals(fileName)){     
//	              InputStreamReader ir=new InputStreamReader(ftp.retrieveFileStream(ff.getName()));
	              BufferedReader br=null;
	              try{
	      	        br=new BufferedReader(new InputStreamReader(ftp.retrieveFileStream(ff.getName())));
	      	        String temp=null;
	      	        temp=br.readLine();
	      	        while(temp!=null){
	      	            sb.append(temp+"\r\n");
	      	            temp=br.readLine();
	      	        }
	              }catch(Exception ex){
	              	ex.printStackTrace();
	              }finally{
	              	br.close();
	              }   
	            } 
	        }          
	        ftp.logout(); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return sb.toString();
	}
	
	/**
	* Description: 从FTP服务器读取多个文件 内容（字符串）
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要读取文件名必须包含的字符串
	* @return 返回多个符合条件的文件内容组成的字符串
	*/ 
	public static String getFilesStr(String fileName) { 
		StringBuffer sb=new StringBuffer();
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(Config.FTP_URL, Config.FTP_PORT); 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(Config.FTP_UN, Config.FTP_PS);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return ""; 
	        } 
	        ftp.changeWorkingDirectory(Config.FTP_PATH);//转移到FTP服务器目录 
	        FTPFile[] fs = ftp.listFiles(); 
            
	        for(FTPFile ff:fs){ 
	            if(ff.getName().contains(fileName)){  
	            	sb.append(FtpClientUtil.getFileStr(ff.getName()));
	            } 
	        }
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return sb.toString();
	}
	
	/**
	* Description: 文件append字符串
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要读取的文件
	* @param appendContent 文件末尾添加的内容字符串
	*/
	public static void appendToFile(String filename, String appendContent){
		String cont=FtpClientUtil.getFileStr(filename);
		FtpClientUtil.uploadStrAsFile(filename, cont+appendContent);
	}
	
	/**
	* Description: 从文件中删除某行记录（包含字符串）
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要读取的文件
	* @param record 文件中包含record的行将被删除
	*/
	public static void removeRecord(String fileName,String record) { 
//		System.out.println(fileName+"-----"+record);
		StringBuffer sb=new StringBuffer();
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(Config.FTP_URL, Config.FTP_PORT); 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(Config.FTP_UN, Config.FTP_PS);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return; 
	        } 
	        ftp.changeWorkingDirectory(Config.FTP_PATH);//转移到FTP服务器目录 
	        FTPFile[] fs = ftp.listFiles(); 
	        for(FTPFile ff:fs){ 
	            if(ff.getName().equals(fileName)){     
//	              InputStreamReader ir=new InputStreamReader(ftp.retrieveFileStream(ff.getName()));
	              BufferedReader br=null;
	              try{
	      	        br=new BufferedReader(new InputStreamReader(ftp.retrieveFileStream(ff.getName())));
	      	        String temp=null;
	      	        temp=br.readLine();
	      	        while(temp!=null){
//	      	        	System.out.println(temp);
	      	        	if(!temp.contains(record)){
	    	        		sb.append(temp+"\r\n");
	    	        	}
	      	            temp=br.readLine();
	      	        }
	              }catch(Exception ex){
	              	ex.printStackTrace();
	              }finally{
	              	br.close();
	              }   
	            } 
	        }          
	        ftp.logout(); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    uploadStrAsFile(fileName,sb.toString());
	}
	
	public static long rowNums(String str,String regex){
		return str.split("\r\n").length;
	}
	
	
	public static void main(String[] args){
		try {
			String content="this is a test";
			boolean flag = uploadStrAsFile("test.txt", content);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String str=getFileStr("test.txt");
		System.out.println(str);
	}
}
