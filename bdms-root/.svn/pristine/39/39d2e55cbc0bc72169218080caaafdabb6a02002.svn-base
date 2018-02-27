package com.bdms.dams.metro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bdms.dams.camera.service.CameraService;
import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.station.service.StationService;
import com.bdms.dams.util.StationGISContainer;
import com.bdms.entity.dams.Camera;
import com.bdms.entity.dams.Station;
import com.bdms.entity.dams.StationAlamCameraInfo;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingGJ;

/* 
 * Description:
 * 		对外接口实现
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-19下午3:10:10            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service(value = "dayACCOutService")
public class DayACCOutServiceImpl implements DayACCOutService {

	
	@Autowired
	private HbaseService hbaseService;
	@Autowired
	private StationService stationService;

	@Autowired
	private CriterionService criterionService;

	@Autowired
	private CameraService cameraService;

	private StationGISContainer sgc = StationGISContainer.getInstance();

	public void checkGISHasExists() {

		if (sgc.gisIsNotExist()) {
			// System.err.println(stationService.findAllGis());
			sgc.setGis(stationService.findAllStas());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdms.dams.metro.DayACCOutService#getAllSationLatest()
	 */
	@Override
	public List<Map<String, String>> getAllSationLatest() {

		checkGISHasExists();

		return hbaseService.getAllGJLatestData(sgc.getAllStationId(),
				SortKey.ENTER_EXIT_SUM);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.dams.metro.DayACCOutService#getLatestBySation(java.lang.String)
	 */
	@Override
	public Map<String, Object> getLatestBySation(String stationId) {

		return null;
	}

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "你好，欢迎访问ws";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.dams.metro.DayACCOutService#storeImgMetaData(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public void storeImgMetaData(String timeStamp, String cameraId,
			String peopleNum, String densityLevel, String densityImage,
			String groupNum, String groupImage, String warnLevel,
			String wrnImage, String reserved) {

		hbaseService.storeImgMetaData(timeStamp, cameraId, peopleNum,
				densityLevel, densityImage, groupNum, groupImage, warnLevel,
				wrnImage, reserved);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdms.dams.metro.DayACCOutService#GetlevelStations()
	 */
	@Override
	public Map<String, List<StationAlamCameraInfo>> GetlevelStations() {

		List<Map<String, String>> AllSationLatest = getAllSationLatest();
		List<StationAlamCameraInfo> redStationlist = new ArrayList<StationAlamCameraInfo>();
		List<StationAlamCameraInfo> orangeStationlist = new ArrayList<StationAlamCameraInfo>();
		List<StationAlamCameraInfo> yellowStationlist = new ArrayList<StationAlamCameraInfo>();
		List<StationAlamCameraInfo> greenStationlist = new ArrayList<StationAlamCameraInfo>();

		Map<String, List<StationAlamCameraInfo>> levelStations = new HashMap<String, List<StationAlamCameraInfo>>();

		for (int i = 0; i < AllSationLatest.size(); i++) {
			Map<String, String> map = AllSationLatest.get(i);
			String stationId = map.get(StreamingGJ.STATION_ID.getName());
			Station station = stationService.findByStation(stationId);
			
			//判断是否存在摄像头
			if(cameraService.findCamerasByStationId(stationId).size()>0)
			{
				
				StationAlamCameraInfo stationAlamCameraInfo = new StationAlamCameraInfo();
	
				Integer criterionRed = Integer.parseInt(criterionService
						.findByCodeAndType(stationId, "stationEnterRT").getLevel()
						.split(",")[2]);
				Integer criterionOrange = Integer.parseInt(criterionService
						.findByCodeAndType(stationId, "stationEnterRT").getLevel()
						.split(",")[1]);
	
				Integer criterionYellow = Integer.parseInt(criterionService
						.findByCodeAndType(stationId, "stationEnterRT").getLevel()
						.split(",")[0]);
	
				Integer enterTimes = Integer.parseInt(map
						.get(StreamingGJ.ENTER_TIMES.getName()));
	
				// 红色
				if (enterTimes > criterionRed) {
					stationAlamCameraInfo.setStation(station);
					stationAlamCameraInfo.setEnterTimes(enterTimes);
					stationAlamCameraInfo.setLevelValue(criterionRed);
					stationAlamCameraInfo.setLevel("4");
					redStationlist.add(stationAlamCameraInfo);
	
				} else {
					// 橙色
					if (enterTimes > criterionOrange) {
						stationAlamCameraInfo.setStation(station);
						stationAlamCameraInfo.setEnterTimes(enterTimes);
						stationAlamCameraInfo.setLevelValue(criterionOrange);
						stationAlamCameraInfo.setLevel("3");
						orangeStationlist.add(stationAlamCameraInfo);
					} else {
						// 黄色
						if (enterTimes > criterionYellow) {
	
							stationAlamCameraInfo.setStation(station);
							stationAlamCameraInfo.setEnterTimes(enterTimes);
							stationAlamCameraInfo.setLevelValue(criterionYellow);
							stationAlamCameraInfo.setLevel("2");
							yellowStationlist.add(stationAlamCameraInfo);
							// 绿色
						} else {
							stationAlamCameraInfo.setStation(station);
							stationAlamCameraInfo.setEnterTimes(enterTimes);
							stationAlamCameraInfo.setLevelValue(criterionYellow);
							stationAlamCameraInfo.setLevel("1");
							greenStationlist.add(stationAlamCameraInfo);
						}
					}
				}
				
	
			}
		}
		

		levelStations.put("redStationlist", redStationlist);
		levelStations.put("orangeStationlist", orangeStationlist);
		levelStations.put("yellowStationlist", yellowStationlist);
		levelStations.put("greenStationlist", greenStationlist);

		return levelStations;
	}

	/*
	 * 根据告警级别得到相应的站点
	 * 
	 * */
	
//	@Override
//	public Map<String, List<StationAlamCameraInfo>> GetlevelStations(Integer level) {
//		
//		Map<String, List<StationAlamCameraInfo>> levelStations = GetlevelStations();
//		
//		Map<String, List<StationAlamCameraInfo>> alarmLevleStations = new HashMap<String, List<StationAlamCameraInfo>>(); 
//		
//		List<StationAlamCameraInfo> redStationlist = new ArrayList<StationAlamCameraInfo>();
//		List<StationAlamCameraInfo> orangeStationlist = new ArrayList<StationAlamCameraInfo>();
//		List<StationAlamCameraInfo> yellowStationlist = new ArrayList<StationAlamCameraInfo>();
//		List<StationAlamCameraInfo> greenStationlist = new ArrayList<StationAlamCameraInfo>();
//		
//		switch (level) {
//		//所有的告警站点
//		case 0:
//					
//			alarmLevleStations = levelStations;
//					
//			break;
//		//绿色
//		case 1:
//			
//			greenStationlist = levelStations.get("greenStationlist");
//			alarmLevleStations.put("greenStationlist", greenStationlist);
//			
//			break;
//		//黄色
//		case 2:
//
//			yellowStationlist = levelStations.get("yellowStationlist");
//			alarmLevleStations.put("yellowStationlist", yellowStationlist);
//			
//			break;
//		//橙色
//		case 3:
//
//			orangeStationlist = levelStations.get("orangeStationlist");
//			alarmLevleStations.put("orangeStationlist", orangeStationlist);
//			
//			break;
//		//红色
//		case 4:
//
//			redStationlist = levelStations.get("redStationlist");
//			levelStations.put("redStationlist", redStationlist);
//			
//			break;
//		default:
//			break;
//	
//		}
//			
//		return alarmLevleStations;
//	}
	@Override
	public List<Map<String, String>> GetlevelStations(Integer level) {
		
		Map<String, List<StationAlamCameraInfo>> levelStations = GetlevelStations();
		List<Map<String, String>> stationAlarmLevel = new ArrayList<>();
		switch (level) {
//		//所有的告警站点
//		case 0:
//					
//			
//					
//			break;
		//绿色
		case 1:
			// 取绿色的报警信息
			List<StationAlamCameraInfo> greenStationlist = levelStations.get("greenStationlist");
			for (int i = 0; i < greenStationlist.size(); i++) {
				Map<String, String> map = new HashMap<>();
				map.put("station", greenStationlist.get(i).getStation().getName().toString());
				map.put("enterTimes", greenStationlist.get(i).getEnterTimes().toString());
				map.put("level", greenStationlist.get(i).getLevel().toString());
				stationAlarmLevel.add(map);
			}
			
			
			break;
		//黄色
		case 2:
			// 取黄色的报警信息
			List<StationAlamCameraInfo> yellowStationlist = levelStations.get("yellowStationlist");
			for (int i = 0; i < yellowStationlist.size(); i++) {
				Map<String, String> map = new HashMap<>();
				map.put("station", yellowStationlist.get(i).getStation().getName().toString());
				map.put("enterTimes", yellowStationlist.get(i).getEnterTimes().toString());
				map.put("level", yellowStationlist.get(i).getLevel().toString());
				stationAlarmLevel.add(map);
			}		
			break;
		//橙色
		case 3:
			// 取出橙色的报警信息
			List<StationAlamCameraInfo> orangeStationlist = levelStations.get("orangeStationlist");
			for (int i = 0; i < orangeStationlist.size(); i++) {
				Map<String, String> map = new HashMap<>();
				map.put("station", orangeStationlist.get(i).getStation().getName().toString());
				map.put("enterTimes", orangeStationlist.get(i).getEnterTimes().toString());
				map.put("level", orangeStationlist.get(i).getLevel().toString());
				stationAlarmLevel.add(map);
			}
			break;
		//红色
		case 4:
			
			//取出红色的报警信息
			List<StationAlamCameraInfo>  redStationlist = levelStations.get("redStationlist");
			for(int i = 0 ;i < redStationlist.size() ; i ++){
				Map<String , String> map = new HashMap<>();
				map.put("station", redStationlist.get(i).getStation().getName().toString());
				map.put("enterTimes", redStationlist.get(i).getEnterTimes().toString());
				map.put("level", redStationlist.get(i).getLevel().toString());
				stationAlarmLevel.add(map);
			}

			break;
		default:
			break;
	
		}
		
		return stationAlarmLevel;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.dams.metro.DayACCOutService#getMaxAlamCameraInfo(java.util.List)
	 */
	@Override
	public StationAlamCameraInfo getMaxAlamCameraInfo(
			List<StationAlamCameraInfo> stationAlamCameraInfos) {
		float pres = 0;
		int index = 0;

		for (int i = 0; i < stationAlamCameraInfos.size(); i++) {
			float temp = (float) stationAlamCameraInfos.get(i).getEnterTimes()
					/ stationAlamCameraInfos.get(i).getLevelValue();
			if (temp > pres) {
				pres = temp;
				index = i;
			}

		}
		return stationAlamCameraInfos.get(index);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.dams.metro.DayACCOutService#getAlamCameraInfo(java.util.List)
	 */
	@Override
	public StationAlamCameraInfo getAlamCameraInfo() {
		StationAlamCameraInfo alamCameraInfo = null;
		if (GetlevelStations().get("redStationlist").size() > 0) {
			alamCameraInfo = getMaxAlamCameraInfo(GetlevelStations().get(
					"redStationlist"));
		} else {
			if (GetlevelStations().get("orangeStationlist").size() > 0) {
				alamCameraInfo = getMaxAlamCameraInfo(GetlevelStations().get(
						"orangeStationlist"));
			} else {
				if (GetlevelStations().get("yellowStationlist").size() > 0) {
					alamCameraInfo = getMaxAlamCameraInfo(GetlevelStations()
							.get("yellowStationlist"));
				} else {
					alamCameraInfo = getMaxAlamCameraInfo(GetlevelStations()
							.get("greenStationlist"));
				}
			}
		}

		return alamCameraInfo;
	}

	/**
	 * description:报警信息
	 * 
	 * @return Map<String,String> 2015-9-17 下午12:28:22 BY YuXiaoLin
	 */
	public Map<String, String> AlamCameraInfo() {

		StationAlamCameraInfo StationAlamCameraInfo = getAlamCameraInfo();

		Map<String, String> map = new HashMap<String, String>();

		List<Camera> cameras = cameraService
				.findCamerasByStationId(StationAlamCameraInfo.getStation()
						.getStation());

		map.put("AlamID", StationAlamCameraInfo.getStation().getStation());
		map.put("AlamName", StationAlamCameraInfo.getStation().getName());
		map.put("AlamLevel", StationAlamCameraInfo.getLevel());
		map.put("AlamPeopleNumber", StationAlamCameraInfo.getEnterTimes()
				.toString());

		String alamCameraID = "AlamCameraID";
		int index = 0;

		if (cameras.size() >= 4) {

			index = 4;
		} else {
			index = cameras.size();
		}

		for (int i = 0; i < index; i++) {
			int temp=i+1;
			String alamCameraIDindex = alamCameraID +temp;
			map.put(alamCameraIDindex, cameras.get(i).getCameraMACId());
		}
		return map;

	}
}
