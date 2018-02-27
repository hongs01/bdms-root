package com.bdms.web.dams.metro.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/dams/metro/inoutTotal/")
public class MetroInoutTotalController {
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView inoutTotal() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/inoutTotal",map);
	}

	/**
	 * description: 跳转 到进出站全部显示的实时监控页面(可带参数)
	 * 
	 * @return String 2015-11-03 下午3:45:34 by Yangb
	 */
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView inoutTotal(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		map.put("sid", sid);
		return new ModelAndView("/dams/metro/inoutTotal",map);
	}

}
