package com.bdms.dams.criterion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.criterion.dao.CriterionDao;
import com.bdms.entity.dams.Criterion;

/* 
 * Description:
 * 		帐号接口实现
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-24 下午5:17:12             1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service("criterionService")
public class CriterionServiceImpl implements CriterionService {

	@Autowired
	private CriterionDao criterionDao;

	@Override
	public Criterion findByCodeAndType(String code,String type) {
		return criterionDao.findByCodeAndType(code,type);
	}

	
	public void save(Iterable<Criterion> entities) {
		
		criterionDao.save(entities);
		
	}


}
