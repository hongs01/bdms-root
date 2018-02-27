package com.bdms.web.auth.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.auth.service.PermissionService;
import com.bdms.entity.auth.Permission;
import com.bdms.entity.auth.Role;

 

/* 
 * Description:
 * 		描述权限管理控制页面
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-6-30下午12:45:09            1.0            Created by HongShuai
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
public class PermissionController {

	
	@Autowired
	private PermissionService permissionService;
	/**
	 * 跳转到权限管理页面
	 * @return
	 */
	@RequestMapping(value = "/permission/list", method = { RequestMethod.GET })
	public String permissionList() {
		return "auth/permission/permission_list";
	}
	 
	
	/**
	 *  获取数据
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/permission/list/data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> permissionListData(int page, int rows) {
		Pageable pageable = new PageRequest(page - 1, rows);
		Page<Permission> permissionbypage = permissionService.finaAllByPage(pageable);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", permissionbypage.getTotalElements());
		model.put("rows", permissionbypage.getContent());
		return model;
	}
	
	/**
	 * 跳转增加页面
	 * @return
	 */
	@RequestMapping(value="/auth/permission/add",method={RequestMethod.GET,
			RequestMethod.POST})
	public String permissionAdd(){
         return "auth/permission/permission_add";		
	}
	
	/**
	 * 提交增加页面
	 * @param permission
	 * @return
	 */
	@RequestMapping(value="auth/permission/doadd",method=RequestMethod.POST)
			public @ResponseBody
			boolean doPermissionAdd(Permission permission){
	       permissionService.savePermission(permission);	
		   return true;
	}
	
	/**
	 * 跳转到编辑权限页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/permission/edit/{id}",method={
        RequestMethod.GET,RequestMethod.POST})
	public ModelAndView permissionEdit(@PathVariable(value="id") int id){
		
		Permission permission =permissionService.getPermissionById(id);
		
		Map <String, Object> model =new HashMap<String, Object>();
		
		model.put("permission", permission);
		
		return new ModelAndView("auth/permission/permission_edit",model);
		
	}
	
	/**
	 * 提交编辑权限页面
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/auth/permission/doedit", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean dopermissionEdit(Permission permission){
           permissionService.savePermission(permission);
		return true;
           }
	
	
	@ResponseBody
	@RequestMapping(value = "/auth/permission/delete/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delAccount(@PathVariable(value = "id") Integer id) {
		permissionService.delPermission(id);
			return true;
	}
	     
	/**
	  * description:
	  * 根据Role表的permissionIds获取权限列表
	  * @param permissionIds
	  * @return
	  * List<Permission>
	  * 2016-1-14 下午2:01:18
	  * by Hongs
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/permission/getPermissionsByPermissionIds/{permissionIds}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public List<Permission> getRolesByRoleIds(@PathVariable(value = "permissionIds") String permissionIds) {
		
			return permissionService.getPermissionsByPermissionIds(permissionIds);
	}
	}
	
	

