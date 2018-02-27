package com.bdms.ftp.ftplistener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.vfs2.FileObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * Description:
  * 		计时器  , 对 轨交数据 中 校验文件中有记录,却实际不存在的 文件进行定时扫描 。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-14下午2:31:11            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class MetroLostFilesCheckTask extends TimerTask{
	
	private static final Logger LOG = LoggerFactory.getLogger(MetroLostFilesCheckTask.class);
	

	private MetroLostFiles mlfs ;
	
	
	
 	public MetroLostFilesCheckTask() {
 		
 		mlfs = MetroLostFiles.getInstance();
 	}
	
	public void run() {
		
		
		List<MetroLostFile> allLostFile = mlfs.getAllLostFile();
		LOG.debug("丢失文件检测定时器    :   开始监听丢失文件列表 " + allLostFile );
		LOG.debug("丢失文件检测定时器    :   开始监听丢失文件列表内包含的文件数为:  " +  allLostFile.size());
		
		List<MetroLostFile> exists = new ArrayList<MetroLostFile>();
		
		String currentMacFileName = MetroMacFileLineAnalyer.getInstance().getCurrentMacFileName();
		
		//遍历丢失文件的mac, 并存在的 文件 存到 existFiles
		FileObject lostFile = null;
		String localMacFilePath = null;
		
		for( MetroLostFile mac : allLostFile ){
			
			 try {
				 
				lostFile = mac.getLostFile();
				
				if(lostFile.exists()){
						
					LOG.debug("丢失文件检测定时器    :   文件 " + lostFile + " 已经存在,开始拷贝到  " + mac.getDestDir());
					
					localMacFilePath = mac.getDestDir() + File.separator + currentMacFileName;
					
					if(VFSUtils.copyLatestFLUXNEWFile(lostFile, mac.getDestDir())){
						
						VFSUtils.updateLocalMetroMacFile( localMacFilePath , mac.getMac());
						exists.add(mac);
						LOG.debug("丢失文件检测定时器    :   拷贝成功");
						
					}else{
						LOG.error("丢失文件检测定时器    :   拷贝失败");
					}
					
				 }
				
			} catch (IOException e) {
				LOG.error("丢失文件检测定时器    :   文件拷贝异常.. ", e);
			}
			
		}
		if( !exists.isEmpty() ){
			mlfs.deleteLostFiles(exists);
		}
		
		LOG.debug("丢失文件检测定时器    :   本次任务完成....");
	}

}
