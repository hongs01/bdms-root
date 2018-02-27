package com.bdms.dams.weight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bdms.entity.dams.SecondWeight;

@Repository
public interface SecondWeightDao extends JpaRepository<SecondWeight, Integer>,
	JpaSpecificationExecutor<SecondWeight>{

	
	@Transactional
	@Modifying	
	@Query("update SecondWeight sw set sw.peopleNum = ?1 , sw.secondMark = ?2 ,sw.alarmLevel= ?3 where sw.id = ?4")
	int updateSecondWeight(int peopleNum,double secondMark,int alarmLevel,int id);
	
	
}
