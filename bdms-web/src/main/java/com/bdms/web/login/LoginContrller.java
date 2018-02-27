package com.bdms.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/* 
 * Description:
 * 		登录页面控制
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-2-11下午4:35:16            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
public class LoginContrller {

	Logger logger = LoggerFactory.getLogger(LoginContrller.class);
	
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView defaultLogin() {
		return new ModelAndView("/login/defaultLogin");
	}
}
