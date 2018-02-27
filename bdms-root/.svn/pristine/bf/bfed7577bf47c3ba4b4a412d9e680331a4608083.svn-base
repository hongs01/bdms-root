package com.bdms.auth.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.entity.auth.Permission;


@Service
public interface PermissionService {
 
	void savePermission(Permission permission);
	
	void delPermission(Integer id);
	
	Page<Permission> finaAllByPage(Pageable pageable);
	
	String findPermissions(Integer permissionIds);
	/************根据权限Id查询****************/
	Permission getPermissionById(Integer id);
	
	
	/**
	  * description:* 
	  * 根据Role的permissionIds获取权限列表
	  * @param permissionIds
	  * @return
	  * List<Permission>
	  * 2016-1-14 下午1:26:11
	  * by Hongs
	 */
	List<Permission> getPermissionsByPermissionIds(String permissionIds);
	
}
