package com.bdms.dams.dzwl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Dzwl;


@Service
public interface DZWLService {

	List<Map<String, Object>> getDZWLData();
	
	Map<String, String> getDZWLById(String id);

	/**
	 * description:查找所有站点
	 * 
	 * @param
	 * @return Map<String,Dzwl> 2015-8-7 下午5:17:12 by YangBo
	 */
	Map<String,String> findAllDzwls();
	
	List<Dzwl> findAll();

	/**
	 * description:查找某个设备最新的一条数据
	 * 
	 * @param
	 * @return Map<String,Dzwl> 2015-8-7 下午5:17:12 by YangBo
	 */
	Map<String, Object> getLDOA(String apName);

	List<Dzwl> findAllEnabled(int enable);

	Map<String, Object> getLDOA();
}
