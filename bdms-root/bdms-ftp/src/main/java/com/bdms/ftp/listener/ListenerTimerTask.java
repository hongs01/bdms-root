package com.bdms.ftp.listener;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.ftp.upload.FTPFileTransmit;

/* 
 * Description:
 * 		这是一个例子。
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-15上午9:49:15            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class ListenerTimerTask extends TimerTask{

	private final Logger LOG = LoggerFactory
			.getLogger(ListenerTimerTask.class);
	
	
	private DirListener dir ;
	private FTPFileTransmit ftp;
	
	private boolean needInit = true;
	private List<File> newFiles;
	
	public ListenerTimerTask(){
		
		dir = new DirListener();
	}
	
	public void run() {
		
		if(needInit){
			
			try {
				ftp = new FTPFileTransmit();
				needInit = false;
				LOG.info("文件夹监听程序初始化成功，开始执行监听任务...");
			} catch (SocketException e) {
				LOG.error("初始化FTPClient失败", e);
				System.exit(-1);
			} catch (IOException e) {
				LOG.error("io异常",e);
				System.exit(-1);
			}
			
		}
		newFiles = dir.startListenAndReturnNewFiles();
		
		if(newFiles.size() > 0 ){
			ftp.saveInFTP(newFiles);
		}else{
			LOG.info("没有检测到新文件加入...");
		}
		
		newFiles = null;
		
	}

}
