package com.bdms.dams.station.station;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdms.dams.station.JunitSpringInitialize;
import com.bdms.dams.video.DayACCVideoService;

public class DayVideoServiceTest extends JunitSpringInitialize{
	@Autowired
	DayACCVideoService dayACCVideoService;
	@Test
	public void testGeVideoDataById(){
		
		dayACCVideoService.getVideoDataById("015112017166013");
		
	}
	
	@Test
	public void testGetVideoData(){
		
		dayACCVideoService.getVideoData();
		
	}
}
