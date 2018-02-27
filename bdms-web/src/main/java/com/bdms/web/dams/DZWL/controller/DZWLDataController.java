package com.bdms.web.dams.DZWL.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.dams.dzwl.DZWLService;

@Controller
@RequestMapping(value = "/dams/dzwl/")
public class DZWLDataController {
	@Autowired
	DZWLService dzwlService;
	/**
	 * 获取电子围栏全部数据
	 * @param stationid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getdzwldata", method = {
			RequestMethod.GET, RequestMethod.POST })
	public List<Map<String, Object>> getvideodata(String stationid) {
		return dzwlService.getDZWLData();
		 
	}

}
