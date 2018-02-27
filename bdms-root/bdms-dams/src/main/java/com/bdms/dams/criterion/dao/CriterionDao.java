package com.bdms.dams.criterion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Criterion;






/* 
 * Description:
 * 		接口：站点相关信息数据操作接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 *  2015-8-3 上午10:17:12     1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Repository
public interface CriterionDao extends JpaRepository<Criterion, Integer>,
		JpaSpecificationExecutor<Criterion>{
	
	/**
	 * description:根据code查找标准
	 * 
	 * @param code、type
	 * @return Criterion 2015-8-3 上午10:17:12 by YangBo
	 */
	@Query("SELECT * FROM Criterion a WHERE a.code=?0 and a.type=?1")
	Criterion findByCodeAndType(String code,String type);
	
}
