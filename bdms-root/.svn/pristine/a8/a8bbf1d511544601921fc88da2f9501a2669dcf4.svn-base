package com.bdms.ftp.ftplistener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * Description:
  * 		这是个单例  , 用于存储 轨交校验文件中有记录,却不存在的文件 。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-14下午2:16:09            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class MetroLostFiles {
	
	private static final Logger LOG = LoggerFactory.getLogger(MetroLostFiles.class);
	
	private static MetroLostFiles mlfs;
	
	private List<MetroLostFile> lostFiles = new ArrayList<MetroLostFile>();
	
	private MetroLostFiles(){}
	
	public static MetroLostFiles getInstance(){
		
		if( mlfs == null ){
			
			synchronized (LOG) {
				
				if( mlfs == null ){
					
					mlfs = new MetroLostFiles();
				}
			}
		}
		
		return mlfs;
	}


	/* 
	 * @Title: addLostFile   
	 * @Description: TODO 添加  丢失文件
	 * @param @param macString    轨交校验文件中的   关于丢失文件的  那一行字符串  
	 * @return void    
	 * @throws 
	 * @Author  Lixc
	*/
	public void addLostFile( MetroLostFile macfile ){
		
		synchronized (LOG) {
			LOG.info("新增 丢失文件Mac " + macfile );
			lostFiles.add(macfile);
		}
		
	}
	
	public void deleteLostFiles( List<MetroLostFile> macfiles ){
		
		synchronized (LOG) {
			LOG.info("删除 丢失文件Mac集合 " + macfiles );
			lostFiles.removeAll(macfiles);
		}
	}
	
	public void deleteLostFile( MetroLostFile macfile ){
		
		synchronized (LOG) {
			LOG.info("删除 丢失文件Mac " + macfile );
			lostFiles.remove(macfile);
		}
	}
	
	
	/* 
	 * @Title: getAllLostFile 
	 * @Description: TODO  获取所有的 丢失文件
	 * @param @return    
	 * @return List<String>    
	 * @throws 
	 * @Author  Lixc
	*/
	public List<MetroLostFile> getAllLostFile(){
		
		synchronized (LOG) {
			
			return lostFiles;
		}
	}

	

	public String toString() {
		return "MetroLostFiles [lostFiles=" + lostFiles + "]";
	}

	
	
}
