package com.bdms.dams.area.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Area;

/**
  * Description:
  * 		
  * 接口：操作数据库区域表相关方法
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午3:48:40            1.0            Created by Chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public interface AreaDao extends JpaRepository<Area, Integer>,
		JpaSpecificationExecutor<Area> {

	
	@Query("SELECT * FROM Area")
	List<Area> findAll();
	
	
	@Query("SELECT * FROM Area a WHERE a.id=?0")
	Area findAreaNameById(Integer id);
	
	
}
