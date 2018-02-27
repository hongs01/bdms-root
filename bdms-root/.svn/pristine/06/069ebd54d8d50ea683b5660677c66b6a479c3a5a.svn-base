package com.bdms.ftp.ftplistener;

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
public class MetroLostPathsCheckTask extends TimerTask{
	
	private static final Logger LOG = LoggerFactory.getLogger(MetroLostPathsCheckTask.class);
	

	private MetroLostFilePaths mlfs ;
	
	private  String address;
	private int port;
	
	

	public MetroLostPathsCheckTask(  String address,String port, String storeLostPath){
		
 		this.address = address;
 		this.port = Integer.parseInt(port);
		mlfs = MetroLostFilePaths.getInstance(storeLostPath);
	}
	
	
	public void run() {
		
		/*获取丢失文件列表*/
		List<String> allLostFile = new ArrayList<String>();
		try {
			allLostFile = mlfs.getAllLostFile();
		} catch (IOException e1) {
			LOG.error("丢失文件检测定时器    :   获取丢失文件列表失败.", e1);
		}
		
		/*遍历 丢失列表,找出存在的文件,并解析*/
	    if( !allLostFile.isEmpty() ){
	    	
			LOG.debug("丢失文件检测定时器    :   开始监听丢失文件列表 " + allLostFile );
			LOG.debug("丢失文件检测定时器    :   开始监听丢失文件列表内包含的文件数为:  " +  allLostFile.size());
			
			List<String> exists = new ArrayList<String>();
			
			//遍历丢失文件的mac, 并存在的 文件 存到 existFiles
			FileObject lostFile = null;
			
			for( String mac : allLostFile ){
				
				 try {
					 
					lostFile = VFSUtils.resolveFile(mac);
					
					if(lostFile.exists()){
						
							LOG.debug("丢失文件检测定时器    :   文件 " + lostFile + " 已经存在,开始解析..");
							
							
							if(mac.contains("FLUXNEW_SEG")){
								if( VFSUtils.parseLatestFLUXNEWFile(address, port, lostFile) ){
									
									exists.add(mac);
									LOG.debug("丢失文件检测定时器    :   解析 " + lostFile + "成功.");
								}else{
									
									LOG.error("丢失文件检测定时器    :   解析 " + lostFile + "失败.");
								}
							}else if(mac.contains("OD_DAY")){
								LOG.error("丢失OD处理中。。。。。。");
								if( VFSUtils.parseLatestODFile(address, port, lostFile) ){
									exists.add(mac);
									LOG.debug("丢失文件检测定时器    :   解析 " + lostFile + "成功.");
								}else{
									LOG.error("丢失文件检测定时器    :   解析 " + lostFile + "失败.");
								}
							}else if(mac.contains("FLUXNEW_DAY")){
								LOG.info("丢失DAY处理中。。。。。。");
								if( VFSUtils.parseLatestDayFile(address, port, lostFile) ){
									exists.add(mac);
									LOG.debug("丢失文件检测定时器    :   解析 " + lostFile + "成功.");
								}else{
									LOG.error("丢失文件检测定时器    :   解析 " + lostFile + "失败.");
								}
							}
						}
						
				} catch (IOException e) {
					LOG.error("丢失文件检测定时器    :   文件解析异常.. ", e);
				}
				
			}
			/*从丢失列表中删除  现已存在的文件列表*/
			if( !exists.isEmpty() ){
				try {
					mlfs.deleteLostFiles(exists);
				} catch (IOException e) {
					LOG.error("丢失文件检测定时器    :   删除丢失文件失败 : " + exists ,e);
				}
			}
	    }
		
		LOG.debug("丢失文件检测定时器    :   本次任务完成....");
	}
	

}
