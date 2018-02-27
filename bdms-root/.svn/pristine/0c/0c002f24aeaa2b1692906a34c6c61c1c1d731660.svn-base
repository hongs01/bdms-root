package com.bdms.dams.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.score.dao.AreaDataDao;
import com.bdms.entity.dams.AreaData;


@Service("AreaDataService")
public class AreaDataServiceImpl implements AreaDataService{

	@Autowired
	private  AreaDataDao areaDataDao;
	
	@Override
	public List<AreaData> findAll() {
		// TODO Auto-generated method stub
		return areaDataDao.findAll();
	}

}
