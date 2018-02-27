package com.bdms.web.auth.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.auth.service.UserService;
import com.bdms.entity.auth.User;
 

 

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	/**
	 * 跳转到帐户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = { RequestMethod.GET })
	public String roleList() {
		return "auth/user/user_list";
	}
	
	/**
	 * 获取数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/user/list/data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> ListData(int page, int rows) {
		Pageable pageable = new PageRequest(page - 1, rows);
		Page<User> userbypage = userService.finaAllByPage(pageable);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", userbypage.getTotalElements());
		model.put("rows", userbypage.getContent());
		return model;
	}


}
