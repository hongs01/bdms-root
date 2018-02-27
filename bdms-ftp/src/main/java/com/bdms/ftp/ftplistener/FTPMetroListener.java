package com.bdms.ftp.ftplistener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Description:
 * 		地铁数据监听
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
public class FTPMetroListener implements FileListener {
	
	private static Logger LOG = LoggerFactory.getLogger(FTPMetroListener.class);

	private String address;

	private int port;
	
	private int rowNum = 0;
	
	private MetroMacFileLineAnalyer mmf;
	
	private List<String> oldMacLines;
	private List<String> currentMacLines;
	
	private List<String> macLines;
	
	public FTPMetroListener(String address, String port) {
		
		this.address = address;
		this.port = Integer.parseInt(port);
		
		mmf = MetroMacFileLineAnalyer.getInstance();
	}

	@Override
	public void fileChanged(FileChangeEvent event) throws Exception {
		
		FileObject file = event.getFile();
		
		String baseName = file.getName().getBaseName();
		
		//System.out.println("fileChanged -> " +  baseName);
		//System.out.println(new Date(file.getContent().getLastModifiedTime()));
		//System.out.println(file.getContent().getSize());
		
		if( baseName.contains("MAC_DAY") ){
			
			LOG.info("监控到 轨交检验文件 ：" + baseName + "有更改，开始解析 ...");
			
			List<String> latestLines = findLatestMacLines(file);
			
			if( !latestLines.isEmpty() ){
				
				VFSUtils.parseLatestMacFileData(address, port,file.getParent().toString() , latestLines);
				
			}
			
			//rowNum=VFSUtils.gjMD5File(event,address,port,rowNum);
			
			
		}else{
			LOG.info("监控到 其他来源的 文件 ：" + baseName + "有更改。");
			System.out.println( "还未未定义操作...." );
		}
		
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
		
		if( macLines != null){
	  
			int size = macLines.size();
			
			if( size > rowNum ){
				
				latestLines = macLines.subList(rowNum, size);
				
				LOG.info( "检验文件：" + macFile + " 内容有更新，更新的行号范围是： (  " + rowNum + " , " + size + " ) ." );
				
				rowNum = size;
				macLines = null;
				
			}else{
				LOG.info( "检验文件：" + macFile + "  内容更新有异常  上一次 最新的行号为 ： " + rowNum + " , 当前最新的为 ： " + size );
			}
		}
		//System.err.println( latestLines.size() );
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
		
		return rowNum;
	}
	@Override
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {
		
		
		String baseName = changeEvent.getFile().getName().getBaseName();
		if(baseName.contains("MAC_DAY_")){
			
			LOG.info(" 检测到有新的轨交校验文件被创建 ,该校验文件名为: " + baseName );
			fileChanged(changeEvent);
			
		}
		//VFSUtils.gjFileToHdfsOrSocket(changeEvent,address,port);
	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		System.out.println("fileDeleted");
	}
	
	public static void main(String[] args) throws FileSystemException {
		
		FTPMetroListener a = new FTPMetroListener("11","123");
		
		 FileSystemManager manager = VFS.getManager();
		
		System.out.println(a.getLatestLines(manager.resolveFile("C:/MAC_DAY_20150708")));
		System.out.println( a.getMacFileOffset() );
	}

}
