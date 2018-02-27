package com.bdms.dams.weight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdms.entity.dams.FirstWeight;


@Repository
public interface FirstWeightDao extends JpaRepository<FirstWeight, Integer>,
		JpaSpecificationExecutor<FirstWeight> {
	
	@Transactional
	@Modifying	
	@Query("update FirstWeight fw set fw.firstMark = ?1 ,fw.alarmCount = ?2 , fw.alarmLevel = ?3 where fw.id = ?4")
	int updateFirstMark(double firstMark,int alarmCount,int alarmLevel,Integer id);
	
//	@Query("SELECT * FROM FirstWeight fw WHERE fw.areaId = ?0")
//	List<FirstWeight> getFirstWeightsByAreaId(Integer areaId);
	
}
