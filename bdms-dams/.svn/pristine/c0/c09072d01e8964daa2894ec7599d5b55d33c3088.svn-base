package com.bdms.dams.metro;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import org.springframework.stereotype.Service;

import com.bdms.entity.dams.StationAlamCameraInfo;


/* 
 * Description:
 * 		对外接口信息
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-19下午3:01:42            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface DayACCOutService {

	/**
	 * description:测试接口
	 * 
	 * @return String 2015-8-19 下午5:55:12 by Yuxl
	 */
	String hello();

	/**
	 * description:获取所有站点信息 进 出 信息
	 * 
	 * @return List<Map<String,Object>> 2015-8-19 下午3:06:38 by Yuxl
	 */
	List<Map<String, String>> getAllSationLatest();

	/**
	 * description:通过站点站点站点Id 该站 进出 信息
	 * 
	 * @param stationId
	 * @return Map<String,Object> 2015-8-19 下午3:08:41 by Yuxl
	 */
	Map<String, Object> getLatestBySation(
			@WebParam(name = "stationId") String stationId);

	/**
	 * description:按照级别获取不同预警级别站点信息
	 * 
	 * @return Map<String,List<Station>> 2015-9-15 下午1:49:42 BY YuXiaoLin
	 */
	Map<String, List<StationAlamCameraInfo>> GetlevelStations();

	/**
	 * description:按照级别获取不同预警级别站点信息
	 * 
	 * @return Map<String,List<Station>> 2015-9-29 下午1:49:42 BY ChenFeng
	 */
	//Map<String, List<StationAlamCameraInfo>> GetlevelStations(Integer level);
	List<Map<String,String>>GetlevelStations(Integer level);
	/**
	 * description: 获取该报警区域中最高百分比的站点
	 * 
	 * @return StationAlamCameraInfo 2015-9-16 上午10:53:38 BY YuXiaoLin
	 */
	StationAlamCameraInfo getMaxAlamCameraInfo(
			List<StationAlamCameraInfo> stationAlamCameraInfos);

	/**
	 * description:公安预警规则
	 * 
	 * 当红色区域有一个警的时候，选择红色最高那个 当橙色区域有一个报警的时候，选择最高百分比的那一个 以此类推
	 * 
	 * @param stationAlamCameraInfos
	 * @return StationAlamCameraInfo 2015-9-16 上午11:05:53 BY YuXiaoLin
	 */
	StationAlamCameraInfo getAlamCameraInfo();

	
	/**
	 * description: 对外Json数据接口提供 给GIS
	 * Key说明:
	 *  AlamID    报警站点ID
	 *  AlamName  报警点名称
	 *  AlamLevel 报警级别
	 *  AlamPeopleNumber 报警点人数
	 *  AlamCameraID1 报警人数摄像头1
	 *  AlamCameraID2 报警人数摄像头2
	 *  AlamCameraID3 报警人数摄像头3
	 *  AlamCameraID4 报警人数摄像头4
	 *
	 * 
	 * @return Map<String,String> 2015-9-17 下午12:11:06 BY YuXiaoLin
	 */
	Map<String, String> AlamCameraInfo();

}
