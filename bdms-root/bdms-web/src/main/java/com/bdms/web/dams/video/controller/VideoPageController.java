package com.bdms.web.dams.video.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.video.DayACCVideoService;
import com.bdms.entity.dams.Video;

@Controller
@RequestMapping(value = "/dams/video/videoPage/")
public class VideoPageController {
	
	@Autowired
	private DayACCVideoService dayACCVideoService;
	
	/**
	 * 跳转到videoPage界面
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView videopage() {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("topimg", "titleName1");
		return new ModelAndView( "dams/video/videoPage", map);
	}
	
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView videopageId(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleNameW");
		map.put("sid", sid);
		return new ModelAndView( "dams/video/videoPage", map);
	}
	
	
	/**获取视频所有值
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "videos", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<Video> findAll() throws UnsupportedEncodingException {
		List<Video> videos =dayACCVideoService.findAll();
		return videos;		
	}
	
	/**根据区域id获取视频名称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "cameranamesbyareaId/{areaId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<Video> getCameranamesbyareaId(@PathVariable(value = "areaId") String areaId) throws UnsupportedEncodingException {
		return  dayACCVideoService.findByareaId(areaId);
	}
	
	
	/**根据video id获取视频名称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "cameranamesbyvideoId/{areaId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Video getCameranamesbyvideoId(@PathVariable(value = "areaId") String areaId) throws UnsupportedEncodingException {
		return  dayACCVideoService.findByCameraId(areaId);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "cameranamesbycameraId/{cameraId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getCameranamesbycameraId(@PathVariable(value = "cameraId") String cameraId) throws UnsupportedEncodingException {
		return  dayACCVideoService.getVideoDayDataByIdForHighchart(cameraId);
	}
	
}
