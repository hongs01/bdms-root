package com.bdms.web.dams.metro.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.dams.metro.DayACCService;
@Controller
@RequestMapping(value = "/dams/metro/insideMetro/")
public class InsideMetroController {
	
	@Autowired
	private DayACCService accService;
	
	/**
	 * 跳转到insideMetro页面
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView insideMetro(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/insideMetro",map);
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
	public Map<String, Object> getSDayInOutSubDataStaticForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayEnterOutDiffDataStaticforHighCharts( dateTime,sid );
	
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
}
