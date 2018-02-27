package com.bdms.ftp.ftplistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * Description:
  * 		这是一个单例 , 主要用于  轨交的mac校验文件的  行号 的判断  。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-14上午11:07:54            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class MetroMacFileLineAnalyer {

	private  static final Logger LOG = LoggerFactory.getLogger(MetroMacFileLineAnalyer.class);
	
	private static MetroMacFileLineAnalyer mmf;
	
	private int currentLineNum = 0;
	private String currentMacFileName;
	
	private int changeTimesCount = 0;
	
	private MetroMacFileLineAnalyer(){}
	
	public static MetroMacFileLineAnalyer getInstance(){
		
		if( mmf == null ){
			
			synchronized ( LOG ) {
				
				if( mmf == null ){
					
					mmf = new MetroMacFileLineAnalyer();
				}
			}
		}
		
		return mmf;
	}

	public void changeCurrentLineNum( String macFileName, int lineNum ){
		
		synchronized ( LOG ){
		
			if( currentMacFileName == null ){
				
				currentMacFileName = macFileName;
				currentLineNum = lineNum;
				changeTimesCount = 1;
				
				
			}else{
				
				if( currentMacFileName.equals(macFileName) ){
					
					currentLineNum = lineNum;
					changeTimesCount += 1;
					
				}else{
					
					LOG.info( " 监听的校验文件发生改变, 由监听  " + currentMacFileName + ", 改变成了监听 " +  macFileName );
					
					currentMacFileName = macFileName;
					currentLineNum = lineNum;
					changeTimesCount = 1;
					
				}
				
			}
		}
		
		LOG.info("当前的轨交校验文件为 : " + currentMacFileName + " , 最新的行号为 : " 
				+ currentLineNum  + " , 该校验文件被检测到的更改事件的次数为 : " + changeTimesCount);
		
	}

	public int getCurrentLineNum(){
			
		return currentLineNum;
		
	}
	
	public int getChangeTimesCount(){
		
		return changeTimesCount;
		
	}

	public String getCurrentMacFileName(){
		
		return currentMacFileName;
		
		
	}
	
	public void setCurrentMacFileName(String currentMacFileName){
		
		this.currentMacFileName = currentMacFileName;
	}

	public static void main(String[] args) {
		
		System.out.println( "aa".equals(null) );
	}
}
