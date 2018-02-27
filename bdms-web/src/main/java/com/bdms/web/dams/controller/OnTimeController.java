package com.bdms.web.dams.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/dams/metro/")
public class OnTimeController {
	
	
	/**跳转到onTimeHeader页面
	 * @return
	 */
	@RequestMapping(value = "ontimeheader", method = { RequestMethod.GET })
	public String ontimeheader() {
		return "/dams/metro/onTimeHeader";
	}
	/*in*/
	/*@RequestMapping(value = "in2/page", method = { RequestMethod.GET })
	public ModelAndView in2() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/in2",map);
	}*/
	
	@RequestMapping(value = "in2/page/{id}", method = { RequestMethod.GET })
	public String in2(@PathVariable(value = "id")  String id,Map<String,Object> map) {
		map.put("id", id);
		return "/dams/metro/in2";
	}
	/*out*/
	/*@RequestMapping(value = "out2/page", method = { RequestMethod.GET })
	public ModelAndView out2() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/out2",map);
	}*/
	/*inoutsub*/
	/*@RequestMapping(value = "inoutsub2/page", method = { RequestMethod.GET })
	public ModelAndView inoutsub2() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/inoutsub2",map);
	}*/
	/*inoutsum*/
	/*@RequestMapping(value = "inoutsum2/page", method = { RequestMethod.GET })
	public ModelAndView inoutsum2() {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/inoutsum2",map);
	}*/
	/*heatMetro*/
/*	@RequestMapping(value = "heatMap2/page", method = { RequestMethod.GET })
	public ModelAndView heatMap2() {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/heatMetro2",map);
	}*/
	
	/*inoutNum*/
	/*@RequestMapping(value = "inAndout2/page", method = { RequestMethod.GET })
	public ModelAndView inAndout2() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/inAndout2",map);
	}*/
	/*inoutNum*/
	/*@RequestMapping(value = "inAndout3/page", method = { RequestMethod.GET })
	public ModelAndView inAndout3() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg","titleName");
		return new ModelAndView("/dams/metro/inAndout3",map);
	}*/
	/*insideMetro*/
	/*@RequestMapping(value = "insideMetro/page", method = { RequestMethod.GET })
	public ModelAndView insideMetro() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("/dams/metro/insideMetro",map);
	}*/
	@RequestMapping(value = "insideMetro/page/{id}", method = { RequestMethod.GET })
	public String insideMetro(@PathVariable(value = "id")  String id,Map<String,Object> map) {
		map.put("id", id);
		return "/dams/metro/insideMetro";
	}
	
}
