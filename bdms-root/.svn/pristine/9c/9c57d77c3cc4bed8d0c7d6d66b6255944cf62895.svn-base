package com.bdms.common.net;

import java.io.IOException;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

public class Ftp {
	    private FTPClient ftpClient;
	    private String strIp;
	    private int intPort;
	    private String user;
	    private String password;
	    
	     private static Logger logger = Logger.getLogger(Ftp.class.getName());  

	     /* * 
	      * Ftp构造函数 
	     */  
	     public Ftp(String strIp, int intPort, String user, String Password) {  
	         this.strIp = strIp;  
	         this.intPort = intPort;  
	         this.user = user;  
	         this.password = Password;  
	         this.ftpClient = new FTPClient();  
	    }  
	     /** 
	    * @return 判断是否登入成功 
	    * */ 
	      public boolean ftpLogin() {  
	         boolean isLogin = false;  
	         FTPClientConfig ftpClientConfig = new FTPClientConfig();  
	        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
	         this.ftpClient.setControlEncoding("GBK");  
	         this.ftpClient.configure(ftpClientConfig);  
	        try {  
	             if (this.intPort > 0) {  
	                this.ftpClient.connect(this.strIp, this.intPort);  
	             } else {  
	                 this.ftpClient.connect(this.strIp);  
	             }  
	            // FTP服务器连接回答  
	            int reply = this.ftpClient.getReplyCode();  
	            if (!FTPReply.isPositiveCompletion(reply)) {  
	                 this.ftpClient.disconnect();  
	                 logger.info("登录FTP服务失败！");  
	                 return isLogin;  
	             }  
	             this.ftpClient.login(this.user, this.password);  
	            // 设置传输协议  
	             this.ftpClient.enterLocalPassiveMode();  
	             this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
	             logger.info("恭喜" + this.user + "成功登陆FTP服务器");  
	             isLogin = true;  
	        } catch (Exception e) {  
	             e.printStackTrace();  
	             logger.info(this.user + "登录FTP服务失败！" + e.getMessage());  
	         }  
	         this.ftpClient.setBufferSize(1024 * 2);  
	         this.ftpClient.setDataTimeout(30 * 1000);  
	         return isLogin;  
	    }  
	      
	       /** 
	      * @退出关闭服务器链接 
	      * */  
	    public void ftpLogOut() {  
	         if (null != this.ftpClient && this.ftpClient.isConnected()) {  
	             try {  
	                 boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
	                 if (reuslt) {  
	                     logger.info("成功退出服务器");  
	                 }  
	            } catch (IOException e) {  
	                 e.printStackTrace();  
	                 logger.warning("退出FTP服务器异常！" + e.getMessage());  
	            } finally {  
	                try {  
	                     this.ftpClient.disconnect();// 关闭FTP服务器的连接  
	                 } catch (IOException e) {  
	                     e.printStackTrace();  
	                     logger.warning("关闭FTP服务器的连接异常！");  
	                 }  
	             }  
	         }  
	         
	     }
	    
	    
	    
	    
}
