package com.bdms.dams.weight.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.AreaFirstSecondWeight;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;



/**
  * Description:
  * 		
  * 计分功能数据库操作接口实现类
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午4:15:23            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public class WeightDaoImpl implements WeightDao {

	/** Logger */    
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(WeightDaoImpl.class);  
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AreaFirstSecondWeight> getAllByAreaId(Integer id) {

		/*
		 * 三张表联合查询： 根据区域id(id为输入参数)， firstWeight表中的areaId等于area表中的id，
		 * firstWeight表中的id等于secondWeight表中的typeId三个条件得出结果
		 */
		//String jpql = "select new com.bdms.core.weight.AreaFirstSecondWeight(a.id,a.areaName,a.area,a.totalMark,a.alarmLevel,a.alarmCount,f.areaId,f.typeName,f.typeCode,f.firstMark,f.alarmCount,f.alarmLevel,s.typeId,s.stationId,s.name,s.peopleNum,s.alarmLevel,s.secondMark) from Area a,FirstWeight f,SecondWeight s where a.id=?0 and a.id = f.areaId and f.id = s.typeId";
		String jpql ="select new com.bdms.core.weight.AreaFirstSecondWeight(" +
				"a.id,a.areaName,a.area,a.totalMark,a.alarmLevel,a.alarmCount," +
				"f.areaId,f.typeName,f.typeCode,f.firstMark,f.alarmCount,f.alarmLevel," +
				"s.typeId,s.stationId,s.name,s.alarmLevel,s.secondMark,s.peopleNum) " +
				"from Area a,FirstWeight f,SecondWeight s where a.id = ?0 and a.id = f.areaId and f.id = s.typeId";
		List<AreaFirstSecondWeight> areaFirstWeights = entityManager
				.createQuery(jpql, AreaFirstSecondWeight.class)
				.setParameter(0, id).getResultList();

		return areaFirstWeights;
	}

	@Override
	public List<FirstWeight> findFristWeightByAreaId(Integer areId) {
		String sql = "select * from firstWeight where areaId = ?0";
		@SuppressWarnings("unchecked")
		List<FirstWeight> firstWeights = entityManager
				.createNativeQuery(sql, FirstWeight.class)
				.setParameter(0, areId).getResultList();
		return firstWeights;
	}

	@Override
	public List<SecondWeight> findSecondByTypeId(Integer typeId) {
		String sql = "select * from secondWeight where typeId = ?0";
		@SuppressWarnings("unchecked")
		List<SecondWeight> secondWeights = entityManager
				.createNativeQuery(sql, SecondWeight.class)
				.setParameter(0, typeId).getResultList();
		return secondWeights;
	}

	@Override
	public List<FirstWeight> findAllFirst() {
		
		String sql = "select * from firstWeight";
		
		@SuppressWarnings("unchecked")
		List<Object[]> objs = entityManager.createNativeQuery(sql).getResultList();
		
		List<FirstWeight> listOut = new ArrayList<FirstWeight>();
		
		for(Object[] obj: objs){
			
			FirstWeight fw = new FirstWeight();
			
			fw.setId((Integer)obj[0]);
			
			fw.setAreaId((Integer)obj[1]);
			
			fw.setTypeName((String)obj[2]);
			
			fw.setTypeCode((String)obj[3]);
			
			fw.setWeight((double)obj[4]);
				
			listOut.add(fw);
		}
		return listOut;
	}

}
