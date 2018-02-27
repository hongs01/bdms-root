package com.bdms.dams.weight.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.AreaFirstSecondWeight;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;



/**
  * Description:
  * 		
  * 计分功能数据库访问接口定义
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午4:09:48            1.0            Created by Chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public interface WeightDao{

	
	/**
	  * description:	通过区域编号三表联合查询
	  * @param id		区域编号
	  * @return
	  * List<AreaFirstSecondWeight>
	  * 2014-3-5 下午4:12:22
	  * by Chenfeng
	 */
	List<AreaFirstSecondWeight> getAllByAreaId(Integer id);
	
	/**
	  * description:	通过区域编号获取firstWeight表中数据
	  * @param areId	区域编号
	  * @return
	  * List<FirstWeight>
	  * 2014-3-5 下午4:13:31
	  * by Chenfeng
	 */
	List<FirstWeight> findFristWeightByAreaId(Integer areId);
	
	/**
	  * description:	通过站点编号获取secondWeight表中数据
	  * @param typeId	站点编号
	  * @return
	  * List<SecondWeight>
	  * 2014-3-5 下午4:14:23
	  * by Chenfeng
	 */
	List<SecondWeight> findSecondByTypeId(Integer typeId);
	
	
	List<FirstWeight> findAllFirst();
	
	
}
