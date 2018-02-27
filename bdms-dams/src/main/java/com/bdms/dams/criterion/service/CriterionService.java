package com.bdms.dams.criterion.service;

import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Criterion;


/* 
 * Description:
 * 		账户接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-3 上午10:17:12       1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface CriterionService {

	/**
	 * description: 通过code查找标准
	 * 
	 * @param code、type
	 * @return 查找到的标准      2015-8-3 上午10:17:12 by YangBo
	 */
	Criterion findByCodeAndType(String code,String type);
	
	void save(Iterable<Criterion> entities);

}
