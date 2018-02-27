package com.bdms.dams.station.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Station;



/* 
 * Description:
 * 		接口：站点相关信息数据操作接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-24 下午5:17:12           1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Repository
public interface StationDao extends JpaRepository<Station, Integer>,
		JpaSpecificationExecutor<Station>{
	
	/**
	 * description:根据线路查找站点
	 * 
	 * @param line
	 * @return List<Station> 2015-7-24 下午5:17:12 by YangBo
	 */
	@Query("SELECT * FROM Station a WHERE a.line=?0 order by station")
	List<Station> findByLine(String line);
	
	/**
	 * description:根据站点编号查找站点
	 * 
	 * @param station
	 * @return List<Station> 2015-7-24 下午5:17:12 by YangBo
	 */
	@Query("SELECT * FROM Station a WHERE a.station=?0")
	Station findByStation(String station);
	
	/**
	 * description:根据站点名称查找站点
	 * 
	 * @param name
	 * @return Station 2015-7-24 下午5:17:12 by YangBo
	 */
	@Query("SELECT * FROM Station a WHERE a.name=?0")
	List<Station> findByName(String name);
	
}
