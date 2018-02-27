package com.bdms.dams.station.station;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.metro.RealTimeDZWLService;
import com.bdms.dams.station.JunitSpringInitialize;

public class RealTimeDZWLServiceTest extends JunitSpringInitialize {

	@Autowired
	RealTimeDZWLService realTimeDZWLService;
	
	@Autowired
	DZWLService dZWLService;
	
	@Test
	public void testGetDZWLRealTimeYHSDataByName(){
		
		realTimeDZWLService.getDZWLRealTimeYHSDataByName("外滩","");
		
	}
	
	@Test
	public void testGetDZWLHistoryTimeYHSDataByName(){
		realTimeDZWLService.getDZWLHistoryTimeYHSDataByName("外滩","");
	}
	@Test
	public void testGetDZWLData (){
		
		dZWLService.getDZWLData();
		
	}
	

}
