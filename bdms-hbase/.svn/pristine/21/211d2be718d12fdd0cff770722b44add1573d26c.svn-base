package com.bdms.hbase.util;

import com.bdms.common.lang.StringUtils;

/**
  * Description:
  * 		单例  用于存储最新的图片数据更新时间
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-25上午9:26:39            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class LatestTimeStore {

	
	private static LatestTimeStore lts;
	
	private String timeStr;
	
	private LatestTimeStore(){}
	
	public static LatestTimeStore getInstance(){
		
		if( lts == null ){
			 
			synchronized (LatestTimeStore.class) {
				
				if(lts == null ){
					
					lts = new LatestTimeStore();
				}
			}
		}
		
		return lts;
	}

	
	
	public String getTimeStr() {
		
		return timeStr;
	}
	

	public void setTimeStr(String timeStr) {
		
		this.timeStr = timeStr;
	}
	
	public boolean checkTimeStrIsNotBank(){
		
		return StringUtils.isNotBlank(timeStr);
	}

}
