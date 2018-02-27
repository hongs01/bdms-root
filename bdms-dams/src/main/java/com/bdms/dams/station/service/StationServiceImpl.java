package com.bdms.dams.station.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bdms.dams.station.dao.StationDao;
import com.bdms.entity.dams.Station;

/* 
 * Description:
 * 		帐号接口实现
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
@Service("stationService")
public class StationServiceImpl implements StationService {

	@Autowired
	private StationDao stationDao;

	@Override
	public Map<String,String> findByLine(String line) {
		List<Station> stationList= stationDao.findByLine(line);
		Map<String,String> stationMap=new LinkedHashMap<String,String>();
		for(Station sta:stationList){
			stationMap.put(sta.getStation(), sta.getName());
		}
		return stationMap;
		
	}


	@Override
	public List<Station> findBylineName(String line) {
		List<Station> stationList= stationDao.findByLine(line);
		
		return stationList;
	}
	
	@Override
	public Station findById(Integer id) {
		return stationDao.findOne(id);
	}

	@Override
	public Map<String,String> findAllTrans() {
		
		// 构建动态查询
		Specification<Station> specification = new Specification<Station>() {
			
			@Override
			public Predicate toPredicate(Root<Station> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(cb.notEqual(
				root.get("transferSt").as(String.class),""));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
			List<Station> stationList = stationDao.findAll(specification);
			Map<String,String> transMap=new HashMap<String,String>();
			for(Station sta:stationList){
				transMap.put(sta.getStation(),sta.getTransferSt());
			}
			return transMap;
	}

	@Override
	public Station findByStation(String station) {
		return stationDao.findByStation(station);
	}

	@Override
	public List<String> findAllLines() {
		List<Station> objs=stationDao.findAll();
		List<String> lineList=new ArrayList<String>(); 
		String line="";
		for(Station obj:objs){
			line=obj.getLine();
			if(!lineList.contains(line)){
				lineList.add(line);
			}
		}
		Collections.sort(lineList);
		return lineList;
				
	}

	@Override
	public Map<String, String> findAllGis() {
		List<Station> objs=stationDao.findAll();
		Map<String, String> gisMap=new HashMap<String,String>();
		for(Station obj:objs){
			gisMap.put(obj.getStation(), obj.getX()+","+obj.getY()+","+obj.getName());
		}
		return gisMap;
	}

	@Override
	public Map<String, Station> findAllStas() {
		List<Station> objs=stationDao.findAll();
		Map<String, Station> gisMap=new HashMap<String,Station>();
		for(Station obj:objs){
			gisMap.put(obj.getStation(), obj);
		}
		return gisMap;
	}


	@Override
	public Station findByName(String name) {
		return stationDao.findByName(name).get(0);
	}




}
