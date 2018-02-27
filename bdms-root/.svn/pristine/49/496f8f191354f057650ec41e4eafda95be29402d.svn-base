package com.bdms.web.auth.role.controller;

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

import com.bdms.auth.service.RoleService;
import com.bdms.entity.auth.Permission;
import com.bdms.entity.auth.Role;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 跳转到帐户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role/list", method = { RequestMethod.GET })
	public String roleList() {
		return "auth/role/role_list";
	}

	/**
	 * 获取数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */

	@RequestMapping(value = "/role/list/data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> roleListData(int page, int rows) {
		Pageable pageable = new PageRequest(page - 1, rows);
		Page<Role> rolebypage = roleService.finaAllByPage(pageable);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", rolebypage.getTotalElements());
		model.put("rows", rolebypage.getContent());
		return model;
	}
	
	/**
	 * 跳转增加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/auth/role/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String accountAdd() {
		return "auth/role/role_add";
	}
	
	
	/**
	 * 提交增加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/auth/role/doadd", method = RequestMethod.POST)
	public @ResponseBody
	boolean doRoleAdd(Role role) {
		Permission permission=new Permission();
		permission.setModule("aaa");
		roleService.saveRole(role);
		return true;
	}

	
	
	/**
	 * 跳转到编辑角色页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/auth/role/edit/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView roleEdit(@PathVariable(value = "id") Integer id) {

		Role role = roleService.getRoleById(id);
		
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("role", role);

		return new ModelAndView("auth/role/role_edit", model);
	}
	
	
	/**
	 * 提交编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/auth/role/doedit", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean doaccountEdit(Role role) {
		roleService.saveRole(role);
		return true;
	}
	
	
	/**
	 * 删除角色
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/role/delete/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delRole(@PathVariable(value = "id") Integer id) {
		roleService.delRole(id);
			return true;
	}
	
	
	/**
	  * description:根据帐户表中的roleIds查找角色列表
	  * @param roleIds
	  * @return
	  * List<Role>
	  * 2016-1-14 下午1:57:43
	  * by Hongs
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/role/getRolesByRoleIds/{roleIds}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public List<Role> getRolesByRoleIds(@PathVariable(value = "roleIds") String roleIds) {
		
			return roleService.getRolesByRoleIds(roleIds) ;
	}
}
