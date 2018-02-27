package com.bdms.web.hbase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.bdms.hbase.service.HbaseService;



@Controller
public class HbaseController {
	
	
	@Autowired
	private HbaseService hbaseService; 
	//private HTable hTable= null;
	
	private static final Logger LOG = Logger.getLogger(HbaseController.class);
	@RequestMapping(value="/hbase",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView hbaseDisplay() {
		
		return new ModelAndView("/hbase/hbaseShow");
	}
	@RequestMapping(value = "/hbase/heatMap", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Map<String,String>> data(HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		List<Map<String,String>> li=new ArrayList<Map<String,String>>();
		int k=getHbaseAll().size();
		for(int j=0;j<10;j++)
		{
			Random rand = new Random();
			int result3 = rand.nextInt(k);
			//System.out.println("aaaaa");
			li.add(getHbaseAll().get(result3));
		}
		//return getHbaseAll();
		 return li;	
	}
	
	@RequestMapping(value = "/hbase/updateHeatMap", method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Map<String,String>> data2(HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		List<Map<String,String>> li=new ArrayList<Map<String,String>>();
		int k=getHbaseAll().size();
		for(int j=0;j<10;j++)
		{
			Random rand = new Random();
			int result3 = rand.nextInt(k);
			//System.out.println("aaaaa");
			li.add(getHbaseAll().get(result3));
		}
		//return getHbaseAll();
		
		 return li;
		//return getHbaseAll();
	}
	
	//获取hbase中的所有值
	public List<Map<String,String>> getHbaseAll() throws IOException{
        Map<String,List<String>> cfs = new HashMap<String,List<String>>(); 
		List<String> cf2 = new ArrayList<String>();
		cf2.add("count");
		cf2.add("lng");
		cf2.add("lat");	
		cfs.put("position", cf2);	
		return hbaseService.getAllData("testGIS09", cfs,-1);
		
	}
	//addOverlay
	@RequestMapping(value="/hbase/addOverlay",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView OverlayDisplay() {
		
		return new ModelAndView("/hbase/addOverlay");
	}
	
	@RequestMapping(value = "/hbase/getStrPng", method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String pngPath(HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		return "oop";
	}
	

}
