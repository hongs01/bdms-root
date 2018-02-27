package com.bdms.dams.station.station;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdms.dams.metro.DayACCOutService;
import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.station.JunitSpringInitialize;
import com.bdms.entity.dams.StationAlamCameraInfo;

public class DayACCServiceTest extends JunitSpringInitialize {

	@Autowired
	private DayACCService dayACCService;
	
	@Autowired
	private DayACCOutService accOutService;
	
	@Test
	public  void testACCOUT()
	{
		//accOutService.GetlevelStations();
		//System.out.println("红色"+accOutService.GetlevelStations().get("redStationlist").size());
		//System.out.println("橙色"+accOutService.GetlevelStations().get("orangeStationlist").size());
		//System.out.println("黄色"+accOutService.GetlevelStations().get("yellowStationlist").size());
		//System.out.println("绿色"+accOutService.GetlevelStations().get("greenStationlist").size());
		StationAlamCameraInfo stationAlamCameraInfo=accOutService.getAlamCameraInfo();
		System.out.println("级别"+stationAlamCameraInfo.getLevel());
		System.out.println(accOutService.getAlamCameraInfo().getStation().getName());
		
	}
	
	//@Test
	public void testLatestTopN(){
		
		//dayACCService.getStationDayLatestExistDataTopN(0, 100);
		//long t1 = System.currentTimeMillis();
		dayACCService.getStationDayLatestExitDataTopNForRight( 10 );
		//System.out.println(System.currentTimeMillis() - t1);
		//System.out.println(dayACCService.getStationDayLatestEnterExistSubDataTopN(0, 100));
		
	}
	
	//@Test
	public void testODTopN(){
	
	
		
		for(String tre : dayACCService.dayODtopNData(10)){
			
			System.out.println(tre);
		}
		
	}
	
	//@Test
	public void testTopN(){
	
	
		
		System.out.println( dayACCService.getStationDayLatestEnterDataTopNForRight(10) );
		System.out.println( dayACCService.getStationDayLatestExitDataTopNForRight(10) );
		System.out.println( dayACCService.getStationDayLatestEnterExitSumDataTopNForRight(10) );
		System.out.println( dayACCService.getStationDayLatestEnterExitSubDataTopNForRight(10) );
		
	}
	
}
