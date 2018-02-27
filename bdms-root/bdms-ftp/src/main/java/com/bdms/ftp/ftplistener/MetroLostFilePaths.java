package com.bdms.ftp.ftplistener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.common.lang.StringUtils;

/**
  * Description:
  * 		存储丢失文件的路径.
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-21上午10:23:55            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class MetroLostFilePaths {
	
	private static final Logger LOG = LoggerFactory.getLogger(MetroLostFilePaths.class);
	
	private static final String NAME = "metro_lost_path.txt";
	
	private static MetroLostFilePaths mlfs;
	
	//private List<MetroLostFile> lostFiles = new ArrayList<MetroLostFile>();
	
	private File lostPath;
	
	private MetroLostFilePaths(String path){
		
		if( StringUtils.isBlank(path) ){
			LOG.warn("存储丢失文件的路径为空,采用相对路径");
			path = "";
		}
		
		lostPath = new File(path + File.separator + NAME );
		if(!lostPath.exists()){
			try {
				lostPath.createNewFile();
				LOG.info("丢失文件的存储文件为 : " + lostPath.getAbsolutePath());
			} catch (IOException e) {
				LOG.error("创建文件" + lostPath.getPath() + "失败.", e);
			}
		}
		
	}
	
	public static MetroLostFilePaths getInstance(String path){
		
		if( mlfs == null ){
			
			synchronized (LOG) {
				
				if( mlfs == null ){
					
					mlfs = new MetroLostFilePaths(path);
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
	public void addLostFile( String filePath ) {
		
		synchronized (LOG) {
			LOG.info("新增 丢失文件地址 " + filePath );
			try {
				FileUtils.writeLines(lostPath,Arrays.asList(filePath) ,true);
			} catch (IOException e) {
				LOG.error("添加丢失文件路径 "+ filePath + " 失败.", e);
			}
		}
		
	}
	
	/**
	  * description: 删除已经存在的  丢失文件
	  * @param paths
	  * @throws IOException
	  * void
	  * 2015-9-21 上午11:04:04
	  * by Lixc
	 */
	@SuppressWarnings("unchecked")
	public void deleteLostFiles( List<String> paths ) throws IOException{
		
		synchronized (LOG) {
			LOG.info("删除 存在的文件地址集合 " + paths );
			List<String> readLines = FileUtils.readLines(lostPath);
			List<String> leavePaths = (List<String>) CollectionUtils.subtract(readLines, paths);
			FileUtils.writeLines(lostPath, leavePaths);
		}
	}
	
	public void deleteLostFile( String path ) throws IOException{
		
			deleteLostFiles(Arrays.asList(path));
	}
	
	
	/* 
	 * @Title: getAllLostFile 
	 * @Description: TODO  获取所有的 丢失文件
	 * @param @return    
	 * @return List<String>    
	 * @throws 
	 * @Author  Lixc
	*/
	public List<String> getAllLostFile() throws IOException{
		
		synchronized (LOG) {
			
			return FileUtils.readLines(lostPath);
		}
	}

	

	public String toString() {
		return "MetroLostFiles [lostFiles=" + lostPath.getPath() + "]";
	}
	
}
