package com.bdms.web.dams.wifi.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.wifi.service.WifiService;

@Controller
@RequestMapping(value = "/dams/wifi/wifiPage/")
public class WifiPageController {
	
	@Autowired
	private WifiService wifiService;

	/**跳转到wifi页面
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView wifiPage() {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("topimg", "titleNameW");
		
		return new ModelAndView( "dams/wifi/wifiPage", map);
	}
	
	/**
	 * 获取wifi数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "wifiData/{sid}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayWifiDataForHigcharts(@PathVariable(value = "sid")  String sid ) throws UnsupportedEncodingException {
		return wifiService.getWifiDayDataByIdForHighchart( sid );

	}
}
