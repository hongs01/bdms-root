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

import com.alibaba.fastjson.JSON;
import com.bdms.dams.metro.DayACCService;
import com.bdms.hbse.enums.SortKey;

/* 
 * Description:
 * 		热力图
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-12上午9:40:27            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value = "/dams/metro/heatMetro/")
public class HeatMetroController {

	@Autowired
	private DayACCService accService;
	
	
	/**
	 * description:热力图页面
	 * 
	 * @return String 2015-8-12 上午10:11:27 by Yuxl
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView page(){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/heatMetro",map);
	}
	
	/**
	 * description:热力图页面
	 * 
	 * @return String 2015-8-12 上午10:11:27 by Yuxl
	 */
	@RequestMapping(value = "page2", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView page2(){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/metroMainTest",map);
	}
	
	/**
	 * description:热力图页数据
	 * 
	 * @return String 2015-8-12 上午10:11:27 by Yuxl
	 */
	
	@ResponseBody
	@RequestMapping(value="data")
	public String data() {
		//return accService.getStationDayLatestEnterDataTopN(0,10);
		return accService.getStationDayLatestDataTopNAddAllTimes(SortKey.ENTER_TIMES, 0, 10);
	}
	
	
	/**
	 * 获取热门图in信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataIn", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataIn() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterDataTopNForRight(10));
	}
	
	
	/**
	 * 获取热门图out信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataOut", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataOut() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestExitDataTopNForRight(10));
	}
	
	/**
	 * 获取热门图inoutsub信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataSub", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataSub() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterExitSubDataTopNForRight(10));
	}
	
	
	/**
	 * 获取热门图inoutsum信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataSum", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataSum() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterExitSumDataTopNForRight(10));
	}
}
