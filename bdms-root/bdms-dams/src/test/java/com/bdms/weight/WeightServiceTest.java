package com.bdms.weight;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bdms.dams.station.JunitSpringInitialize;
import com.bdms.dams.weight.dao.WeightDao;

public class WeightServiceTest extends JunitSpringInitialize{
	
	@Autowired
	WeightDao weightDao;
	
	@Test
	public void testUpdate(){
		
			//weightDao.updatePeopleNumInFirstWeight("0725", 100);
		}
	
	
}
