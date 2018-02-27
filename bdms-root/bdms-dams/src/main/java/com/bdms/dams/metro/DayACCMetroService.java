package com.bdms.dams.metro;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface DayACCMetroService {

	/**
	 * 按照id获取地铁信息
	 * @param stationId
	 * @return
	 */
	Map<String, Object> getMetroDataById(String stationId);
	
	
	/**
	 * 按照id获取进站人数和出站人数
	 * @param stationId
	 * @return
	 */
	Map<String, Object> getEnterExitPeopleNumById(String stationId);
	
	
	/**
	 * 获取所有地铁信息
	 * @return
	 */
	List<Map<String, Object>> getMetroData();
	
	
	/**
	 * 获取所有预警地铁站信息
	 * @return
	 */
	List<Map<String, Object>> getAlertMetroData();
	
	
	
}
