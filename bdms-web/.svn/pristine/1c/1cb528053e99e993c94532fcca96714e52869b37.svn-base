package com.bdms.web.auth.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.auth.service.AccountService;
import com.bdms.auth.service.RoleService;
import com.bdms.entity.auth.Account;

/* 
 * Description:
 * 		描述账号管理控制页面
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-6-26下午12:45:09            1.0            Created by HongShuai
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService roleService;

	/**
	 * description:跳转到增加页面，弹出增加页面对话框
	 * 
	 * @return String 2015-7-8 上午10:03:59 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String accountAdd() {
		return "auth/account/account_add";
	}

	/**
	 * description:增加账户
	 * 
	 * @param account
	 * @return boolean 2015-7-8 上午10:05:36 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/doadd", method = RequestMethod.POST)
	public @ResponseBody
	boolean doAccountAdd(Account account) {
		boolean result = false;
		if (accountService.saveAccount(account) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * description:跳转到列表页面
	 * 
	 * @return String 2015-7-8 上午11:05:52 by Yuxl
	 */
	@RequestMapping(value = "/account/list", method = { RequestMethod.GET })
	public String accountList() {
		return "auth/account/account_list";
	}

	/**
	 * description:获取列表Json数据
	 * 
	 * @param page
	 * @param rows
	 * @return Map<String,Object> 2015-7-8 上午11:06:55 by Yuxl
	 */
	@RequestMapping(value = "/account/list/data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> accountListData(int page, int rows, String accountName,
			String startDate, String endDate) {
		Page<Account> accountbypage = accountService.findAllWhitPage(page,
				rows, accountName, startDate, endDate);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", accountbypage.getTotalElements());
		model.put("rows", accountbypage.getContent());
		return model;
	}

	/**
	 * description: 跳转到编辑页面
	 * 
	 * @param id
	 * @return ModelAndView 2015-7-9 下午3:04:22 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/edit/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView accountEdit(@PathVariable(value = "id") int id) {
		Account account = accountService.getAccountById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("account", account);
		return new ModelAndView("auth/account/account_edit", model);
	}

	/**
	 * description:编辑
	 * 
	 * @param account
	 * @return boolean 2015-7-9 下午3:09:04 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/doedit", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean doaccountEdit(Account account) {
		boolean result = false;
		if (accountService.saveAccount(account) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * description:通过帐号名称，查找该帐号是否已存在
	 * 
	 * @param accountName
	 * @return boolean 2015-7-9 下午5:31:25 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/isexist", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean findByAccountName(String accountName) {
		boolean result = false;
		if (accountService.findByAccountName(accountName) == null) {
			result = true;
		}
		return result;
	}

	/**
	 * description:跳转到角色修改页面
	 * 
	 * @param id
	 * @return ModelAndView 2015-7-13 下午12:55:39 by Yuxl
	 */
	@RequestMapping(value = "/auth/account/change/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView roleChange(@PathVariable(value = "id") int id) {
		Account account = accountService.getAccountById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("account", account);
		return new ModelAndView("auth/account/role_change", model);
	}

	@RequestMapping(value = "/auth/account/dochange", method = RequestMethod.POST)
	public @ResponseBody
	String doRoleChange(Account account) {
		accountService.saveAccount(account);
		return null;
	}

	/**
	 * 提交编辑账户
	 * 
	 * @param account
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/auth/account/delete/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delAccount(@PathVariable(value = "id") Integer id) {
		accountService.delAccount(id);
		return true;
	}

	@RequestMapping(value = "/account/serchAccount", method = { RequestMethod.GET })
	public Account serchAccount(String accountname) {
		Account account = new Account();
		String accountList = account.getAccountName();
		if (accountname == accountList) {

		}
		/* return "auth/account/account_list"; */
		return account;
	}

	/**
	 * 获取数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */

	@RequestMapping(value = "account/list/searchdata", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> accountListData(
			@ModelAttribute(value = "account") Account account) {

		return null;
	}

	@RequestMapping(value = "/auth/account", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getAccountById(int id) {
		Account account = accountService.getAccountById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("account", account);
		return new ModelAndView("auth/account", model);
	}

}
