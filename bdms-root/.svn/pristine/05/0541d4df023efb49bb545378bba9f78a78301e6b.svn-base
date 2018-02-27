package com.bdms.web.dams.metro.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;


import com.alibaba.fastjson.JSON;

import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.station.service.StationService;
import com.bdms.entity.dams.Criterion;
import com.bdms.entity.dams.Station;

/* 
 * Description:
 * 		地铁进站控制层，所有地铁进站请求
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-28下午3:37:06            1.0            Created by Hongs
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Controller
@RequestMapping(value = "/dams/metro/in/")
public class MetroInController implements ServletConfigAware,ServletContextAware {
	
	@Autowired
	private DayACCService accService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private CriterionService criterionService;
	
	private ServletContext servletcontext;

	/**
	 * description: 跳转 到进站实时监控页面
	 * 
	 * @return String 2015-7-28 下午3:45:34 by Hongs
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView in() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/in",map);
	}
	
	/**
	 * description: 跳转 到进站实时监控页面(可带参数)
	 * 
	 * @return String 2015-7-28 下午3:45:34 by Yangb
	 */
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView in(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		map.put("sid", sid);
		return new ModelAndView("/dams/metro/in",map);
	}
	
	/**
	 * description: 跳转 到进站实时监控页面
	 * 
	 * @return String 2015-7-28 下午3:45:34 by Hongs
	 */
	@RequestMapping(value = "pagebartest", method = { RequestMethod.GET })
	public ModelAndView inbartest() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/inbartest",map);
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
	 * description:test of changetheme
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "chtheme", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String chtheme() throws UnsupportedEncodingException {
//		List<String> option =stationService.findAllLines();
		String theme = (String) servletcontext.getAttribute("THEME");
		System.out.println("themeIs:"+theme);
		if (theme == null || "smoothness".equals(theme)) {
			servletcontext.setAttribute("THEME", "black/dot-luv");
		}else{
			servletcontext.setAttribute("THEME", "smoothness");
		}
		return JSON.toJSONString("success");	
	}
	
	/**
	 * description: 获取所有站点gis数据
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "gis", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getGis() throws UnsupportedEncodingException {
		Map<String,String> map =stationService.findAllGis();
		return JSON.toJSONString(map);		
	}
	
	/**
	 * description: 获取所有站点gis数据
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "allstas", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getAllStas() throws UnsupportedEncodingException {
		Map<String,Station> map =stationService.findAllStas();
		return JSON.toJSONString(map);		
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
	
	/**
	 * description: 获取某个站点的json数据
	 *  @param station
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "criterion", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Criterion findByCodeAndType(String code,String type) throws UnsupportedEncodingException {
		Criterion crit =criterionService.findByCodeAndType(code, type);
		return crit;		
	}
	
	
	/**
	 * 获取电子围栏当日json数据  进站数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayenterdata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayEnterDataForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterDataforHighCharts( dateTime,sid );

	}
	
	/**
	 * 获取电子围栏预测json数据  进站数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayenterpredictedata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayEnterPredicteDataForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterPredicteDataforHighCharts( dateTime,sid );

	}
	
	/**
	 * 获取历史累计OD top100数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "top100ODData", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<String> getTop100ODData() throws UnsupportedEncodingException {
		return accService.dayODtopNData(100);
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletcontext=arg0;
		
	}

	@Override
	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	



	
}


