package com.bdms.ftp.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPFileTransmit {

	private final Logger LOG = LoggerFactory
			.getLogger(FTPFileTransmit.class);
	
    private static final String FTPWORKINGPATH = "dsftp/" ;  
    private static final String FTPUSERNAME = "dswh";  
    private static final String FTPPWD = "1234567";  
    private static final String FTPHOSTNAME = "dswhhadoop-30";  
    private static final int FTPPORT = 2222;  
    
    
    private FTPClient ftpClient;

    public FTPFileTransmit() throws SocketException, IOException { 
    	
    	 ftpClient = new FTPClient();
    	 
    	 ftpClient.connect(FTPHOSTNAME, FTPPORT);
    	 /*
    	 boolean flag = false;
    	 if(ftpClient.login(FTPUSERNAME,FTPPWD)){
    		 flag =  ftpClient.changeWorkingDirectory(FTPWORKINGPATH);
    	 }else{
    		 LOG.error("登录失败。。。");
    		 System.exit(-1);
    	 }
    	 if(!flag){
    		 LOG.error("工作空间切换失败。。。");
    		 System.exit(-1);
    	 }*/
    	 
    	 if(!ftpClient.login(FTPUSERNAME,FTPPWD)){
    		 LOG.error("登录失败。。。");
    		 System.exit(-1);
    	 }
    	 
    	 
    	 // 设置缓冲   
         ftpClient.setBufferSize(10240);  
    	// ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
    }  
    
    public void saveInFTP (List<File> files) {  
    	
    	String fileName = null;
    	FileInputStream local = null;
        try {  
           
        	for(File file : files){
        		  fileName =  file.getName();
                  local = new FileInputStream(file);
                   
				if(ftpClient.storeFile(fileName,local)){
                	   LOG.info(fileName +" 上传成功。");
                   }else{
                	   LOG.error(fileName +" 上传失败。");
                   }
				
				local = null;
                        
        	}
              
        } catch (IOException e) {  
        		LOG.error("io异常", e);
        }  finally {  
               IOUtils.closeQuietly(local);  
        }  

        
    }  
}
