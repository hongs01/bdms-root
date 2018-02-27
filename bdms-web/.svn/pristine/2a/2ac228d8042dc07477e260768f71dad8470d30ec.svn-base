package com.bdms.web.auth.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = "/admin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String admin() {
		return "admin/index";
	}

	@RequestMapping(value = "/head", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String head() {
		return "admin/head";
	}
	
	@RequestMapping(value = "/menu", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String meun() {
		return "admin/menu";
	}
	
	@RequestMapping(value = "/foot", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String foot() {
		return "admin/foot";
	}
}
