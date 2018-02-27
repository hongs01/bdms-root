package com.bdms.dams.metro;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.station.service.StationService;
import com.bdms.dams.util.StationGISContainer;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingGJ;

@Service(value="dayACCMetroService")
public class DayACCMetroServiceImpl implements DayACCMetroService{

	@Autowired
	private HbaseService hbaseService;
	
    @Autowired
    private CriterionService criterionService;
    
    @Autowired
    private StationService stationService;
    
    private StationGISContainer sgc = StationGISContainer.getInstance();
	
	
	
	public void checkGISHasExists(){
		
		if(sgc.gisIsNotExist()){
			//System.err.println(stationService.findAllGis());
			sgc.setGis(stationService.findAllStas());
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCMetroService#getMetroDataById(java.lang.String)
	 */
	@Override
	public Map<String, Object> getMetroDataById(String stationId) {
		
        List<StreamingGJ> columnToBack = Arrays.asList(StreamingGJ.START_TIME,StreamingGJ.STATION_ID,StreamingGJ.ENTER_TIMES,StreamingGJ.EXIT_TIMES);
		
		Map<String, String> metroData = hbaseService.getMetroDataByStationId(stationId, columnToBack);
	
		Map<String, Object> metroDataOUT=new HashMap<String, Object>();
		
		metroDataOUT.put("STATIONID", stationId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date dd=null;
		String newCtime="";
		try {
			dd = sdf.parse(metroData.get(StreamingGJ.START_TIME.getName()));
			newCtime=sdf.format(new Date(dd.getTime()+5*60*1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		metroDataOUT.put("CTIME", newCtime);
		metroDataOUT.put("PEOPLENUM", metroData.get(StreamingGJ.ENTER_TIMES.getName()));
		metroDataOUT.put("PEOPLENUMOUT", metroData.get(StreamingGJ.EXIT_TIMES.getName()));
		
		String[] levelEnter=criterionService.findByCodeAndType(stationId, "stationEnterRT").getLevel().split(",");
		
		if(Integer.parseInt(metroData.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[0])){
				metroDataOUT.put("Level",1);
		}else if((Integer.parseInt(metroData.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[1]))){
			metroDataOUT.put("Level", 2);
		}else if((Integer.parseInt(metroData.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[2]))){
			metroDataOUT.put("Level", 3);
		}else{
			metroDataOUT.put("Level", 4);
		}
		
		return metroDataOUT;
	}


	

	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCMetroService#getMetroData()
	 */
	@Override
	public List<Map<String, Object>> getMetroData() {
		checkGISHasExists();
		List<Map<String, String>> alldata= hbaseService.getAllGJLatestData(sgc.getAllStationId(), SortKey.ENTER_EXIT_SUM);
		
		List<Map<String, Object>> alldataout=new ArrayList<Map<String, Object>>();
		
		for (Map<String, String> map : alldata) {
			Map<String, Object> mapOUT=new HashMap<String, Object>();
            mapOUT.put("STATIONID", map.get(StreamingGJ.STATION_ID.getName()));
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date dd=null;
    		String newCtime="";
    		try {
    			dd = sdf.parse(map.get(StreamingGJ.START_TIME.getName()));
    			newCtime=sdf.format(new Date(dd.getTime()+5*60*1000));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		
            mapOUT.put("CTIME",newCtime);
            mapOUT.put("PEOPLENUM", map.get(StreamingGJ.ENTER_TIMES.getName()));
            mapOUT.put("PEOPLENUMOUT", map.get(StreamingGJ.EXIT_TIMES.getName()));
            
            String[] levelEnter=criterionService.findByCodeAndType(map.get(StreamingGJ.STATION_ID.getName()), "stationEnterRT").getLevel().split(",");
    		
    		if(Integer.parseInt(map.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[0])){
    			mapOUT.put("Level",1);
    		}else if((Integer.parseInt(map.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[1]))){
    			mapOUT.put("Level", 2);
    		}else if((Integer.parseInt(map.get(StreamingGJ.ENTER_TIMES.getName()))<Integer.parseInt(levelEnter[2]))){
    			mapOUT.put("Level", 3);
    		}else{
    			mapOUT.put("Level", 4);
    		}
    		alldataout.add(mapOUT);
		}
		return alldataout;
		 
	}
	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.DayACCMetroService#getMetroData()
	 */
	@Override
	public List<Map<String, Object>> getAlertMetroData() {
		checkGISHasExists();
		List<Map<String, String>> alldata= hbaseService.getAllGJLatestData(sgc.getAllStationId(), SortKey.ENTER_TIMES);
		
		List<Map<String, Object>> alldataout=new ArrayList<Map<String, Object>>();
		
		for (Map<String, String> map : alldata) {
			Map<String, Object> mapOUT=new HashMap<String, Object>();
			String[] levelEnter=criterionService.findByCodeAndType(map.get(StreamingGJ.STATION_ID.getName()), "stationEnterRT").getLevel().split(",");
    		
    		if((Integer.parseInt(map.get(StreamingGJ.ENTER_TIMES.getName()))>=Integer.parseInt(levelEnter[2]))){
    			mapOUT.put("Level", 4);
    			mapOUT.put("STATIONID", map.get(StreamingGJ.STATION_ID.getName()));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                java.util.Date dd=null;
        		String newCtime="";
        		try {
        			dd = sdf.parse(map.get(StreamingGJ.START_TIME.getName()));
        			newCtime=sdf.format(new Date(dd.getTime()+5*60*1000));
        		} catch (ParseException e) {
        			e.printStackTrace();
        		}
        		
                mapOUT.put("CTIME",newCtime);
                mapOUT.put("PEOPLENUM", map.get(StreamingGJ.ENTER_TIMES.getName()));
                alldataout.add(mapOUT);
    		}
    		
		}
		return alldataout;
		 
	}


	@Override
	public Map<String, Object> getEnterExitPeopleNumById(String stationId) {
		List<StreamingGJ> columnToBack = Arrays.asList(StreamingGJ.START_TIME,StreamingGJ.STATION_ID,StreamingGJ.ENTER_TIMES,StreamingGJ.EXIT_TIMES);
		
		Map<String, String> metroData = hbaseService.getMetroDataByStationId(stationId, columnToBack);
	
		Map<String, Object> metroDataOUT=new HashMap<String, Object>();

		metroDataOUT.put("PEOPLENUM", metroData.get(StreamingGJ.ENTER_TIMES.getName()));
		metroDataOUT.put("PEOPLENUMOUT", metroData.get(StreamingGJ.EXIT_TIMES.getName()));
		return metroDataOUT;
	}
	
	


}
