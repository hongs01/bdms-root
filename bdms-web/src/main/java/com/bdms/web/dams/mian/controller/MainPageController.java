package com.bdms.web.dams.mian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dams/main/mainPage/")
public class MainPageController {
	/**
	 * 跳转到主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public String main() {
		return "dams/main/mainPage";
	}

	/**
	 * description: 简单的主页
	 * 
	 * @return String 2015-9-25 上午9:31:20 BY YuXiaoLin
	 */
	@RequestMapping(value = "simplepage", method = { RequestMethod.GET })
	public String simplepage() {
		return "dams/main/simplepage";
	}
}
