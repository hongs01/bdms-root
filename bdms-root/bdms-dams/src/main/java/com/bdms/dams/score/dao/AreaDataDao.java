package com.bdms.dams.score.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;

import com.bdms.entity.dams.AreaData;



public interface AreaDataDao extends JpaRepository<AreaData, Integer>,
                                 JpaSpecificationExecutor<AreaData>{
	@Query("SELECT distinct areaId,areaName FROM areaData")
	List<AreaData> findAll();
}
