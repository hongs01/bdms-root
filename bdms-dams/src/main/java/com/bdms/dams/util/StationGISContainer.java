package com.bdms.dams.util;

import java.util.Map;
import java.util.Set;

import com.bdms.entity.dams.Station;


/**
  * Description:
  * 		存储所有的station信息 , 
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-7下午1:34:23            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class StationGISContainer {

	private static StationGISContainer cgs;
	
	private Map<String,Station> giss;
	
	private StationGISContainer(){}
	
	public static StationGISContainer getInstance(){
		
		if( cgs == null ){
			
			synchronized (StationGISContainer.class) {
				if(cgs == null){
					
					cgs = new StationGISContainer();
				}
			}
		}
		
		return cgs;
	}
	
	
	public boolean gisIsNotExist(){
		
		return giss == null ? true:false;
		
	}
	
	public void setGis(Map<String,Station> giss){
		
		this.giss = giss;
	}
	
	/**
	  * description:
	  * @param key  stattion_id
	  * @return
	  * Station
	  * 2015-8-7 下午1:35:02
	  * by Lixc
	 */
	public Station getGis(String key){
		
		return giss.get(key);
	}
	
	
	public Set<String>  getAllStationId(){
		
		
		return giss.keySet();
	}

}
