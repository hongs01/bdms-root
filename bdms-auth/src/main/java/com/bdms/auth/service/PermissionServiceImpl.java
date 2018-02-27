package com.bdms.auth.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.apache.shiro.authz.permission.WildcardPermission;

import com.bdms.auth.dao.PermissionDao;
import com.bdms.entity.auth.Permission;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public void savePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.save(permission);
		
	}

	/* (non-Javadoc)
	 * 根据权限Id查询
	 * @see com.bdms.auth.service.PermissionService#getPermissionById(java.lang.Integer)
	 */
	@Override
	public Permission getPermissionById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.findOne(id);
	}

	@Override
	public void delPermission(Integer id) {
		// TODO Auto-generated method stub
		permissionDao.delete(id);
	}

	@Override
	public Page<Permission> finaAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		System.out.println(permissionDao);
		
		return permissionDao.findAll(pageable);
	}
	
	@Override
	public String findPermissions(Integer permissionId) {
		
		
			Permission permission = getPermissionById(permissionId);
			if(null == permission || StringUtils.isEmpty(permission.getOperation())) {
				return "";
			}
		
		return permission.getOperation(); 
	}

	/* (non-Javadoc)
	 * 根据Role的permissionIds获取权限列表
	 * @see com.bdms.auth.service.PermissionService#getPermissionsByPermissionIds(java.lang.String)
	 */
	@Override
	public List<Permission> getPermissionsByPermissionIds(String permissionIds) {
		String[] subPermissionIds = permissionIds.split(",");
		List<Permission> permissions=new ArrayList<Permission>();

		for(int i = 0; i<subPermissionIds.length; i++){
			Permission permission=permissionDao.findOne(Integer.parseInt(subPermissionIds[i]));
			permissions.add(permission);
		}
		return permissions;
	}

	
}
