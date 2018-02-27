package com.bdms.ftp.listener;

import java.io.IOException;
import java.util.TimerTask;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.ftp.ftplistener.VFSUtils;

/**
  * Description:
  * 		ftp上实时轨交数据 校验文件监听
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-8下午3:44:47            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class FTPMetroMACFileTask extends TimerTask {
	
	private static final Logger LOG = LoggerFactory.getLogger(FTPMetroMACFileTask.class);
	
	private String listenFilePath;
    private FileObject 	listenFile;
	
	private FileSystemManager fsManager;
	
	//最近一次的 行号的最大值
	private int lineNum;
	
	private long size;
	
	
	public FTPMetroMACFileTask( String listenFilePath ){
		
		init(listenFilePath);
	}

	/* 
	 * @Title: init 
	 * @Description: TODO   实现监听操作的初始化   .
	 * @param  listenFilePath    
	 * @return void    
	 * @Author  Lixc
	*/
	public void init(String listenFilePath) {
		
		this.listenFilePath = listenFilePath;
		lineNum = 0;
		try {
			fsManager = VFS.getManager();
			listenFile = fsManager.resolveFile(listenFilePath);
		} catch (FileSystemException e) {
			LOG.error("VFS虚拟文件系统获取实例失败...", e);
		}
		
	}
	
	
	public void run() {
		
		//VFSUtils.gjMD5File2(address, port, listenFile, lineNum);
		
		
	}

	
	
	
}
