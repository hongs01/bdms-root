package com.bdms.web.bdmsinfo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/* 
 * Description:
 * 		宣传信息。
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-2-11上午9:49:34            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
public class BdmsInfoController {

	public static Logger logger = LoggerFactory
			.getLogger(BdmsInfoController.class);

	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView infoIndex() {
		logger.info("首页信息");
		return new ModelAndView("bdmsInfo/index");
	}

	@RequestMapping(value = "/release", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView release() {
		logger.info("版本信息");
		return new ModelAndView("bdmsInfo/release");
	}

	/**
	 * description:项目列表
	 * 
	 * @return String 2015-7-13 下午2:44:45 by Yuxl
	 */
	@RequestMapping(value = "/project")
	public String projectList() {
		return "bdmsInfo/projectList";
	}

}
