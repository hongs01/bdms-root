package com.bdms.web.dams.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bdms.dams.metro.DayACCService;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.code.Trigger;

/* 
 * Description:
 * 		描述
 * 电子围栏客流量分析
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-16上午10:30:54            1.0            Created by HongShuai
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value = "/dams/elecfence/")
public class DamsController {

	@Autowired
	private DayACCService accService;
	
	/**
	 * 跳转到柱状图示例
	 * 
	 * @return
	 */
	@RequestMapping(value = "bar", method = { RequestMethod.GET })
	public String bar() {
		return "/dams/echartsBarShow";
	}

	/**
	 * 跳转到折线图示例
	 * 
	 * @return
	 */
	@RequestMapping(value = "line", method = { RequestMethod.GET })
	public String line() {
		return "/dams/echartsLineShow";
	}
	
	/**
	 * 跳转到折线图示例
	 * 
	 * @return
	 */
	@RequestMapping(value = "hline", method = { RequestMethod.GET })
	public String hline() {
		return "/dams/highchartsExitShow";
	}
	

	/**
	 * 跳转到当日电子围栏趋势图
	 * 
	 * @return
	 */
	@RequestMapping(value = "sdayef", method = { RequestMethod.GET })
	public String sday() {
		return "/dams/elecfence/sdayef";
	}

	/**
	 * 热力图
	 * @return
	 */
	@RequestMapping(value="heatMap",method={RequestMethod.GET})
	public String heatMap() {
		return "/dams/heatMetro";
	}
	
	/**
	 * 热力图框架
	 * @return
	 */
	@RequestMapping(value="fheatMap",method={RequestMethod.GET})
	public String fheatMap() {
		return "/dams/fheatMetro";
	}

	/**
	 * 跳转到当月电子围栏趋势图
	 * 
	 * @return
	 */
	@RequestMapping(value = "smonthef", method = { RequestMethod.GET })
	public String smonth() {
		return "/dams/elecfence/smonthef";
	}

	@RequestMapping(value="sdayelecfence",method={RequestMethod.GET})
	public String sdayelecfence() {
		return "/dams/show/sDayElecFence";
	}
	
	/**
	 * 跳转到双折线显示图
	 * @return
	 */
	@RequestMapping(value="doubleline",method={RequestMethod.GET})
	public String doubleline() {
		return "/dams/DoubleLine";
	}
	/**
	 * 跳转到双折线显示图
	 * @return
	 */
	@RequestMapping(value="timeline",method={RequestMethod.GET})
	public String timeline() {
		return "/dams/timeLine";
	}
	
	/**
	 * description:
	 * 跳转到OD热门线路图
	 * @return
	 * String
	 * 2015-8-4 上午9:36:50
	 * by Hongs
	 */
	@RequestMapping(value="odshow",method={RequestMethod.GET})
	public String odshow() {
		return "/dams/ODShow";
	}
	
	/**
	 * 跳转到堆积柱状图
	 * @return
	 */
	@RequestMapping(value="acmlbar",method={RequestMethod.GET})
	public String acmlbar() {
		return "/dams/acmlBarShow";
	}
	
	
	/**
	 * 跳转到进站显示图
	 * @return
	 */
	@RequestMapping(value="acmlbars",method={RequestMethod.GET})
	public String inshow() {
		return "/dams/inshow";
	}
	
	/**
	 * 获取电子围栏当日json数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "sdaydata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getSDayData() throws UnsupportedEncodingException {
		Option option = accService.SDayDataOption();
		return JSON.toJSONString(option);

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
		
		
		return accService.axisDayEnterDataforHighCharts(dateTime, sid );

	}
	

	/**
	 * 获取当日json数据  出站站数据 
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dayexitdata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSDayExitDataForHigcharts(String dateTime,String sid ) throws UnsupportedEncodingException {
		
		
		return accService.axisDayExitDataforHighCharts(dateTime,sid );

	}

	

	/**
	 * 获取电子围栏当月json数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "smonthdata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getSMonthData() throws UnsupportedEncodingException {
		Option option = accService.SMonthDataOption();

		return JSON.toJSONString(option);
	}
	
	@ResponseBody
	@RequestMapping(value = "oddata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getOdData() throws UnsupportedEncodingException {
		Option option=null;
		
		option.legend("线路1");
		option.legend("线路2");
		
		option.backgroundColor("#eee");
		option.title("'OD轨道交通热门线路示意图'");

        option.tooltip().trigger(Trigger.item)
                .formatter("{b}");
       // option.color(['rgba(218, 70, 214, 1)', 'rgba(100, 149, 237, 1)', 'green']);
		
        
        return JSON.toJSONString(option);
	}
}
