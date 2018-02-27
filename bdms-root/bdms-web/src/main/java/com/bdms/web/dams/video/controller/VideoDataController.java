package com.bdms.web.dams.video.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.dams.video.DayACCVideoService;

@Controller
@RequestMapping(value = "/dams/vedio/")
public class VideoDataController {

	@Autowired
	DayACCVideoService dayACCVideoService;
	
	/**
	 * 根据videoid获取视频数据
	 * @param stationid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getvediodatabyid/{vedioid}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getvideodatabyid(@PathVariable(value = "vedioid") String stationid) {
		return dayACCVideoService.getVideoDataById(stationid);
		 
	}
	
	/**
	 * 获取全部的视频数据
	 * @param stationid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getvediodata", method = {
			RequestMethod.GET, RequestMethod.POST })
	public List<Map<String, Object>> getvideodata() {
		return dayACCVideoService.getVideoData();
		 
	}
}
