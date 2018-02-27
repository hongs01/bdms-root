package com.bdms.web.dams.metro.controller;

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
import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.station.service.StationService;
import com.bdms.entity.dams.Station;

/* 
 * Description:
 * 		地铁进站控制层，所有地铁进出站人数差请求
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-29下午1:20:24            1.0            Created by Hongs
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value = "/dams/metro/inoutsub/")
public class MetroInOutSubController {
	
	@Autowired
	private DayACCService accService;
	
	@Autowired
	private StationService stationService;

	/**跳转到进出站差监控页面
	 * description:
	 * @return
	 * String
	 * 2015-7-29 下午1:19:20
	 * by Hongs
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView inoutsub() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/inoutsub",map);
	}
	
	/**
	 * description: 跳转 到进出站差实时监控页面(可带参数)
	 * 
	 * @return String 2015-11-03 下午3:45:34 by Yangb
	 */
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView inoutsub(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		map.put("sid", sid);
		return new ModelAndView("/dams/metro/inoutsub",map);
	}
	
	/**
	 * 获取电子围栏当日json数据  进出站人数差
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayInOutSubdata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayInOutSubDataForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterOutDiffDataforHighCharts( dateTime,sid );
	
	}
	
	/**
	 * 获取电子围栏当日json数据  进出站人数差统计数据 
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayInOutSubdataStatic", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayInOutSubDataStaticForHigcharts(String dateStr,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterOutDiffDataStaticforHighCharts( dateStr,sid );
	
	}
	
	/**
	 * 获取电子围栏当日json数据  进出站人数差 预测数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayInOutSubPredicteData", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayInOutSubPredicteDataForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterOutDiffPredicteDataforHighCharts( dateTime,sid );
	
	}
	
	/**
	 * 获取电子围栏当日json数据  进出站人数差 预测统计数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayInOutSubPredicteDataStatic", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayInOutSubPredicteDataStaticForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterOutDiffPredicteDataStaticforHighCharts( dateTime,sid );
	
	}
	
	
	
	/**
	 * description: 获取所有线路信息json数据
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "lines", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getLines() throws UnsupportedEncodingException {
		List<String> option =stationService.findAllLines();
		return JSON.toJSONString(option);		
	}
	
	/**
	 * description: 获取某个线路的站点信息json数据
	 * @param line
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "stationsbyline/{line}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, String> getStationsByLine(@PathVariable(value = "line") String line) throws UnsupportedEncodingException {
		Map<String, String> model =stationService.findByLine(line);
		return model;		
	}
	
	/**
	 * description: 获取某个站点的json数据
	 *  @param station
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "station/{station}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Station getStation(@PathVariable(value = "station") String station) throws UnsupportedEncodingException {
		Station stat =stationService.findByStation(station);
		return stat;		
	}
	
	

}
