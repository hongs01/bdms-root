package com.bdms.web.prewaring.controller;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.bdms.dams.metro.DayACCOutService;
import com.bdms.entity.dams.StationAlamCameraInfo;
/* 
 * Description:
 * 		站点告警级别页面控制
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-9-29 上午11:17:16       1.0            Created by chenfeng
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value="dams/prewaring/prewaringLevel")
public class PrewaringController {

		
		@Autowired
		private  DayACCOutService accOutService;		
		
		/**跳转到prewaring页面Page
		 * @return
		 */
			@RequestMapping(value = "page", method = { RequestMethod.GET })
			public String prewaring() {
				return "dams/prewaring/prewaringPage";
			}
		
		
		/**
		 * 获取站点告警级别的数据，并转化成JSON数据 
		 * 
		 * @return
		 * @throws UnsupportedEncodingException
		 */

		@ResponseBody
		@RequestMapping(value = "prewaringdata", method = { RequestMethod.GET,
				RequestMethod.POST })
		public Map<String, List<StationAlamCameraInfo>> getStaionLevel() throws UnsupportedEncodingException {
			
			return accOutService.GetlevelStations();
		}
		/**
		 * 获取站点告警级别的数据，并转化成JSON数据 
		 * 
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		@ResponseBody
		@RequestMapping(value = "prewaringdata/{level}", method = { RequestMethod.GET,
				RequestMethod.POST })
		public List<Map<String, String>> getStaionLevel(@PathVariable(value = "level")  Integer level ) throws UnsupportedEncodingException {
			
			return accOutService.GetlevelStations(level);
			 

		}

}
