package com.bdms.dams.station.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Station;


/* 
 * Description:
 * 		账户接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-24 下午5:17:12            1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface StationService {

	/**
	 * description: 通过Id查找站点
	 * 
	 * @param id
	 * @return 查找到的站点 Station 2015-7-24 下午5:17:12 by YangBo
	 */
	Station findById(Integer id);

	/**
	 * description: 通过Station code查找站点
	 * 
	 * @param station
	 * @return 查找到的站点 Station 2015-7-24 下午5:17:12 by YangBo
	 */
	Station findByStation(String station);

	/**
	 * description:查找线路line上所有站点的相关信息
	 * 
	 * @param line
	 * @return Map<station,name> 2015-7-24 下午5:17:12 by YangBo
	 */
	Map<String, String> findByLine(String line);

	/**
	 * description:通过线路找线路上的站
	 * 
	 * @param line
	 * @return List<String> 2015-8-11 下午2:22:17 by Yuxl
	 */
	List<Station> findBylineName(String line);

	/**
	 * description:查找所有换乘站点
	 * 
	 * @param
	 * @return Map<String,String> 2015-7-24 下午5:17:12 by YangBo
	 */
	Map<String, String> findAllTrans();

	/**
	 * description:查找所有线路编码
	 * 
	 * @param
	 * @return List<String> 2015-7-24 下午5:17:12 by YangBo
	 */
	List<String> findAllLines();

	/**
	 * description:查找所有站点gis
	 * 
	 * @param
	 * @return Map<String,String> 2015-8-7 下午5:17:12 by YangBo
	 */
	Map<String, String> findAllGis();

	/**
	 * description:查找所有站点
	 * 
	 * @param
	 * @return Map<String,String> 2015-8-7 下午5:17:12 by YangBo
	 */
	Map<String, Station> findAllStas();
	
	/**
	 * description: 通过Station name查找站点
	 * 
	 * @param name
	 * @return 查找到的站点 Station 2015-7-24 下午5:17:12 by YangBo
	 */
	Station findByName(String name);
	
}
