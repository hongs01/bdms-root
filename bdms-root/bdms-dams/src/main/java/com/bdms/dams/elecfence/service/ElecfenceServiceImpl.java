package com.bdms.dams.elecfence.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.elecfence.dao.ElecfenceDao;
import com.bdms.entity.dams.Elecfence;

/* 
 * Description:
 * 		电子围栏服务类
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
@Service("ElecfenceService")
public class ElecfenceServiceImpl implements ElecfenceService {

	@Autowired
	private ElecfenceDao elecfenceDao;

	@Override
	public Elecfence findByCode(String code) {
		
		return elecfenceDao.findByCode(code);
	}

	@Override
	public List<Elecfence> findAllElecfences() {
		// TODO Auto-generated method stub
		return elecfenceDao.findAll();
	}

	@Override
	public List<Elecfence> findByName(String name) {
		// TODO Auto-generated method stub
		return elecfenceDao.findByName(name);
	}
	
	public Map<String,String> findAllElecfencesNameAndCode() {
		Map<String,String> map=new HashMap<String,String>();
		for(Elecfence d: elecfenceDao.findAll()){
			map.put( d.getName(),d.getCode());
		}
		return map;
	} 
	
	
	public Map<String,String> findAllElecfencesNameAndCodeReversed() {
		
		Map<String,String> map=new HashMap<String,String>();
		for(Elecfence d: elecfenceDao.findAll()){
			map.put( d.getCode(),d.getName());
		}
		return map;
	} 

	

}
