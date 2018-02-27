package com.bdms.dams.elecfence.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Elecfence;


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
public interface ElecfenceService {

	/**
	 * description: 通过Elecfence code查找站点
	 * 
	 * @param Elefence
	 * @return 查找到的站点 Elecfence 2015-7-24 下午5:17:12 by YangBo
	 */
	Elecfence findByCode(String code);

	/**
	 * description:查找所有站点
	 * 
	 * @param
	 * @return Map<String,String> 2015-8-7 下午5:17:12 by YangBo
	 */
	List<Elecfence> findAllElecfences();
	
	/**
	 * description: 通过Elefence name查找站点
	 * 
	 * @param name
	 * @return 查找到的站点 Elefence 2015-7-24 下午5:17:12 by YangBo
	 */
	List<Elecfence> findByName(String name);
	
	Map<String,String> findAllElecfencesNameAndCode(); 
	
	Map<String,String> findAllElecfencesNameAndCodeReversed();
	
}
