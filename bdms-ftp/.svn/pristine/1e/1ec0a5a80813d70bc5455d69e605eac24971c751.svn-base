package com.bdms.ftp.ftplistener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Description:
 * 		公安网 地铁数据监听
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-27上午9:47:47            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class GAFTPListener implements FileListener {
	
	private static Logger LOG = LoggerFactory.getLogger(GAFTPListener.class);

	private String destDir;  // 本地   轨交数据的目录

	private MetroMacFileLineAnalyer mmf;
	
	private List<String> oldMacLines;
	private List<String> currentMacLines;
	
	private List<String> macLines;
	
	public GAFTPListener(String destDir) {
		
		this.destDir = destDir + File.separator;
	
		 mmf = MetroMacFileLineAnalyer.getInstance();
		
	}
	

	public void fileChanged(FileChangeEvent event) throws Exception {
		
		FileObject file = event.getFile();
		
		String baseName = file.getName().getBaseName();
		
		if( baseName.contains("MAC_DAY") ){
			
			LOG.info("监控到 轨交检验文件 ：" + baseName + "有更改，开始解析 ...");
			
			List<String> latestLines = findLatestMacLines(file);
			
			if( !latestLines.isEmpty() ){
				
				VFSUtils.copyMetroLatestFiles( baseName ,file.getParent().toString() + File.separator , latestLines,destDir);
				
			}
			
		}else{
			LOG.info("监控到 其他来源的 文件 ：" + baseName + "有更改。");
			System.out.println( "还未未定义操作...." );
		}
		
	}
	
	
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {
		
		String baseName = changeEvent.getFile().getName().getBaseName();
		
		//校验文件创建
		if(baseName.contains("MAC_DAY")){
			
			LOG.info(" 检测到有新的轨交校验文件被创建 ,该校验文件名为: " + baseName );
			fileChanged(changeEvent);
		}else{
			
			//实时文件创建   执行拷贝操作
			if( baseName.contains("FLUXNEW_SEG") ){
				LOG.info("检测到有新的轨交实时文件 ,文件名为: " + baseName );
				VFSUtils.copyLatestFLUXNEWFile(changeEvent.getFile(),destDir);
			}
			
		}
		
	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		System.out.println("fileDeleted");
	}
	
	
	
	/**   获取校验文件中最新的行 
	 * @param macFile  发生改变的校验文件
	 * @return
	 */
	public List<String>  getLatestLines( FileObject macFile ){
		
		List<String> latestLines = new ArrayList<String>();
		
		try {
			
			macLines = IOUtils.readLines(macFile.getContent().getInputStream());
			
		} catch (FileSystemException e) {
			LOG.error("读取轨交校验文件" + macFile.toString() +"失败。", e);
			macLines = null;
		} catch (IOException e) {
			LOG.error("解析轨交校验文件" +  macFile.toString() + "发生io异常。", e);
			macLines = null;
		}
		
		
		if( macLines != null && !macLines.isEmpty() ){
			
			String name = macFile.getName().getBaseName();
			int size = macLines.size();
			
			if( name.equals( mmf.getCurrentMacFileName() ) ){
				
				int currentLineNum = mmf.getCurrentLineNum();
				
				if( size > currentLineNum  ){
					
					mmf.changeCurrentLineNum(name, size);
					latestLines = macLines.subList(currentLineNum, size);
					macLines = null;
					
					LOG.info( "检验文件：" + macFile + " 内容有更新，更新的行号范围是： (  " + currentLineNum + " , " + size + " ) ." );
					
				}else{
					// size = currentLineNum;
					mmf.changeCurrentLineNum(name, size);
					macLines = null;
					LOG.info( "检验文件：" + macFile + "  内容更新有异常 , 上一次 最新的行号为 ： " 
								       + currentLineNum + " , 当前最新的为 ： " + size + " , 将更改当前记录的最新行号为." );
				} 
				
				
			}else{
				
				mmf.changeCurrentLineNum(name, size);
				latestLines.addAll(macLines);
				macLines = null;
				
				LOG.info( "检验文件：" + macFile + " 内容有更新，更新的行号范围是： (  0 , " + size + " ) ." );
			}
			
		
		}
	
		return latestLines;
		
		
	}

	
	private List<String> findLatestMacLines( FileObject macFile ){
		
		List<String> latestLines = new ArrayList<String>();
		
		try {
			
			currentMacLines = IOUtils.readLines(macFile.getContent().getInputStream());
			
		} catch (FileSystemException e) {
			LOG.error("读取轨交校验文件" + macFile.toString() +"失败。", e);
			currentMacLines = null;
		} catch (IOException e) {
			LOG.error("解析轨交校验文件" +  macFile.toString() + "发生io异常。", e);
			currentMacLines = null;
		}
		
		if( currentMacLines != null && !currentMacLines.isEmpty() ){
			
			if( oldMacLines == null ){
				
				latestLines.addAll(currentMacLines);
				
			}else{
				
				latestLines = (List<String>) CollectionUtils.subtract(currentMacLines, oldMacLines);
			}
		}
		mmf.setCurrentMacFileName(macFile.getName().getBaseName());
		oldMacLines = currentMacLines;
		
		LOG.info( "检验文件：" + macFile + " 内容有更新，最新的内容为: " + latestLines );
		
		return latestLines;
		
	}
	
	public int getMacFileOffset(){
		
		return mmf.getCurrentLineNum();
	}

}
