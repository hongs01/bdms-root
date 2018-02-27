package com.bdms.web.dams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
 * Description:
 * 		上海市局项目页面控制
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-13下午3:15:47            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Controller
public class DAController {

	/**
	 * description:上海项目首页
	 * 
	 * @return String 2015-7-13 下午4:24:46 by Yuxl
	 */
	@RequestMapping(value = "/dams", method = { RequestMethod.GET })
	public String index() {
		return "dams/index";
	}
	
	/**
	 * mac桌面
	 * @return
	 */
	@RequestMapping(value = "/dams/index", method = { RequestMethod.GET })
	public String index1() {
		return "dams/index1";
	}

	/**
	 * description:总览
	 * 
	 * @return String 2015-7-13 下午4:25:40 by Yuxl
	 */
	@RequestMapping(value = "/dams/main", method = { RequestMethod.GET })
	public String main() {
		return "dams/main";
	}



}
