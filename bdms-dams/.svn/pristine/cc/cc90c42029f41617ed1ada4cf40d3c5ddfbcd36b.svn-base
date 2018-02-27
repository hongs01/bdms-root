package com.bdms.dams.weight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdms.entity.dams.Area;

@Repository
public interface AreaUpdate extends JpaRepository<Area, Integer>,
		JpaSpecificationExecutor<Area> {
	
	@Transactional
	@Modifying	
	@Query("update Area a set a.totalMark = ?1,a.alarmCount = ?2,a.alarmLevel = ?3,a.DZWLNum = ?4,a.increment=?5 where a.id = ?6")
	int updateTotalMark(double totalMark,int alarmCount,int alarmLevel,Long DZWLNum,Integer increment,Integer id );
}
