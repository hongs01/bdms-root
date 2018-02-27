package com.bdms.dams.metro;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
  * Description:
  * 		实时电子围栏服务接口。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24下午4:17:04            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Service
public interface RealTimeDZWLService {
	
	
	/**
	  * description:
	  * @param qym  区域名
	  * void
	  * 2015-8-24 下午4:17:43
	  * by Lixc
	 */
	Map<String,Object> getDZWLRealTimeYHSDataByName(String qym,String dtime);
	
	
	
	/**
	 * @param qym   区域名
	 * @return
	 */
	Map<String,Object> getDZWLHistoryTimeYHSDataByName(String qym,String dtime);


	/**
	 * @param apname   区域名
	 * @return
	 */
	Map<String, Object> getLDOA(String apname);



	Map<String, Object> getLDOA();

}
