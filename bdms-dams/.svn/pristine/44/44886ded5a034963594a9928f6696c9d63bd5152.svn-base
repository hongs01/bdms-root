package com.bdms.dams.station.station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdms.dams.dijkstra.DataBuilder;
import com.bdms.dams.dijkstra.Station;
import com.bdms.dams.dijkstra.Subway;
import com.bdms.dams.station.JunitSpringInitialize;
import com.bdms.dams.station.service.StationService;

public class StationServiceTest extends JunitSpringInitialize {

	private static Logger logger = LoggerFactory
			.getLogger(StationServiceTest.class);

	@Autowired
	private StationService stationService;

	@Autowired
	private DataBuilder builder;

	// @Autowired
	// private Subway subway;

	// @Test
	public void testTransferSt() {
		// // 根据线路查找所有站点
		// Map<String,String> stationMap=stationService.findByLine("01");
		// for(Map.Entry<String, String> entry:stationMap.entrySet()){
		// System.out.println(entry.getKey()+"----"+entry.getValue());
		// }
		// // 根据id查找站点
		// System.out.println("347th stop is :"+stationService.findById(347).getName());
		// // 根据station编号查找
		// System.out.println("0111 stop is :"+stationService.findByStation("0111").getName());
		// // 查找所有换乘站
		// Map<String,String> transMap=stationService.findAllTrans();
		// for(Map.Entry<String, String> entry:transMap.entrySet()){
		// System.out.println(entry.getKey()+"----"+entry.getValue());
		// }
		// // 查找所有线路编号
		// List<String> lines=stationService.findAllLines();
		// for(String str:lines){
		// System.out.println(str+" ");
		// }
		// 查找所有gis
		// Map<String,Station> gisMap=stationService.findAllStas();
		// for(Map.Entry<String, Station> entry:gisMap.entrySet()){
		// System.out.println(entry.getValue());
		// }
		// stationService.findAllLines();

	}

	@Test
	public void dtest() {

		// Subway subway=new Subway();
		// Subway subway2=new Subway();

		 //System.out.println(stationService.findByStation("0126").getName());
		 //System.out.println(stationService.findByStation("0111").getName());

		String strs = "0247-0246, 0126-0835, 0118-0111, 0111-0118, 0246-0247, 0926-0931, 0247-0123, 0235-0234, 0126-0111, 0111-0835";

		
		
		builder.init();

		List<String> stlist = Arrays.asList(strs.split(","));
		List<String> odtoplist = Arrays.asList("0247-0246", "0126-0835", "0118-0111", "0111-0118", "0246-0247", "0926-0931", "0247-0123", "0235-0234", "0126-0111", "0111-0835");

		//System.out.println(stlist);
		//System.out.println(stationService.findByStation("0118").getName());
		//System.out.println(stationService.findByStation("0835").getName());
		
		
		for (int j = 0; j < 3; j++) {
			
			for (int i = 0; i < odtoplist.size(); i++) {
				Subway subway = new Subway();
				subway.setBuilder(builder);
				//System.out.println(stlist.get(i).split("-")[0]);
				
				String st=stlist.get(i).split("-")[0].trim();
				String et=stlist.get(i).split("-")[1].trim();
				
				
				
			String start=stationService.findByStation(st).getName();
	        String end=stationService.findByStation(et).getName();
	  
	        
	        
	        subway.calculate(new com.bdms.dams.dijkstra.Station(start), new
	        		 com.bdms.dams.dijkstra.Station(end));
	        

			}
			
		}
		
		
		
		// builder.init();
		//
		// for(int i=0;i<10;i++)
		// {
		//
		//
		// Subway subway=new Subway();
		//
		// subway.setBuilder(builder);
		// subway.calculate(new com.bdms.dams.dijkstra.Station("上海火车站"), new
		// com.bdms.dams.dijkstra.Station("莘庄"));
		// for (int j = 0; j < subway.getPassStionList().size(); j++) {
		// System.out.println(">>>>>>>>>"+subway.getPassStionList().get(j).getName());
		// }
		// }

		// subway.setBuilder(builder);
		// subway2.setBuilder(builder);
		// System.out.println("+++++++++++++++++++++"+builder.getTotalStaion());
		//
		// subway.calculate(new com.bdms.dams.dijkstra.Station("成山路"), new
		// com.bdms.dams.dijkstra.Station("黄兴路"));
		//
		// subway2.calculate(new com.bdms.dams.dijkstra.Station("成山路"), new
		// com.bdms.dams.dijkstra.Station("黄兴路"));
		// System.out.println("====================");
		// for (Station s : subway.getPassStionList()) {
		// System.out.println(s.getName());
		// }

	}

}
