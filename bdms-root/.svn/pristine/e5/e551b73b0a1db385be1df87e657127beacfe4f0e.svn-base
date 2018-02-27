package com.bdms.dams.wifi.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.bdms.entity.dams.WifiData;

@Service
public interface WifiDataService {

	List<WifiData> getStations();
	
	/**
	 * 按照apName和时间 获取wifi信息
	 * @param apNameAndTimeStr
	 * @return
	 * add by zuoyang
	 */
	Map<String, Object> getWifi2DayDataForHighchart(String apNameAndTimeStr);

	/**
	 * 
	按照apname获取wifiData表的全部数据
	*/
	List<WifiData> getwifiDataTotal(String apname);

}
