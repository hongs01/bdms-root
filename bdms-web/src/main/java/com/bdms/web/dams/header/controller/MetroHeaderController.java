package com.bdms.web.dams.header.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dams/header/metroHeader/")
public class MetroHeaderController {
	/**
	 * 跳转到轨交的公共界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public String header() {
		return "dams/header/metroHeader";
	}
}
