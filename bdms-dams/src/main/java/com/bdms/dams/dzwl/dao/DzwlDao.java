package com.bdms.dams.dzwl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Dzwl;



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
public interface DzwlDao extends JpaRepository<Dzwl, Integer>,
		JpaSpecificationExecutor<Dzwl>{
	
	/**
	 * description:根据电子围栏编号查找电子围栏站点
	 * 
	 * @param station
	 * @return DZWL 2015-10-28 上午10:18:12 by Chenfeng
	 */
	@Query("SELECT * FROM Dzwl a WHERE a.code=?0")
	Dzwl findByCode(String code);
	
	/**
	 * description:
	 * 
	 * @param station
	 * @return 
	 */
	@Query("SELECT * FROM Dzwl a WHERE a.enable=:enable")
	List<Dzwl> getByEnable(@Param(value="enable")int enable);
	
}
