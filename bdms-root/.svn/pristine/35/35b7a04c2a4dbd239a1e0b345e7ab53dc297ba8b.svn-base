package com.bdms.web.dams.metro.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.dams.camera.service.CameraService;
import com.bdms.dams.metro.DayACCMetroService;
import com.bdms.dams.metro.DayACCOutService;
import com.bdms.entity.dams.Camera;

@Controller
@RequestMapping(value = "/dams/metro/")
public class MetroDataController {

	@Autowired
	DayACCMetroService dayACCMetroService;

	@Autowired
	private DayACCOutService accOutService;
	
	@Autowired
	private CameraService cameraService;

	/**
	 * 根据站点id获取轨交json数据
	 * 
	 * @param stationid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getmetrodatabyid/{stationid}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getmetrodatabyid(
			@PathVariable(value = "stationid") String stationid) {
		return dayACCMetroService.getMetroDataById(stationid);

	}

	/**
	 * 直接获取轨交数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getmetrodata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<Map<String, Object>> getmetrodata() {
		return dayACCMetroService.getMetroData();
	}

	/**
	 * 获取轨交报警站点数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getalertmetrodata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<Map<String, Object>> getalertmetrodata() {
		return dayACCMetroService.getAlertMetroData();
	}

	/**
	 * description: 报警区域信息
	 *  AlamID    报警站点ID
	 *  AlamName  报警点名称
	 *  AlamPeopleNumber 报警点人数
	 *  AlamCameraID1 报警人数摄像头1
	 *  AlamCameraID2 报警人数摄像头2
	 *  AlamCameraID3 报警人数摄像头3
	 *  AlamCameraID4 报警人数摄像头4
	 * 
	 * @return Map<String,String> 2015-9-17 下午12:33:11 BY YuXiaoLin
	 */
	@ResponseBody
	@RequestMapping(value = "alam", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, String> AlamCamera() {
		return accOutService.AlamCameraInfo();
	}
	
	
	
	
	/**
	 * 根据videoid获取视频数据
	 * @param stationid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="video/{stationid}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public List<Camera> getvideodatabyid(@PathVariable(value = "stationid") String stationid) {
		return cameraService.findCamerasInfoByStationId(stationid); 
	}
	

}
