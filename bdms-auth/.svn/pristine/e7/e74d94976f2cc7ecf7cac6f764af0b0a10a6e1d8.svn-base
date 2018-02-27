package com.bdms.auth.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.auth.dao.RoleDao;
import com.bdms.entity.auth.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		Role role= roleDao.findOne(id);
		return role;
	
	}

	@Override
	public void delRole(Integer id) {
		// TODO Auto-generated method stub
		try {
			roleDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Page<Role> finaAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return roleDao.findAll(pageable);
	}
	
	public Role getRoleByroleId(Integer roleId){
		Role role = roleDao.findOne(roleId);
		return role;
	}
	
//	@Override
//	public Set<String> findRoles(Integer[] roleIds) {
//		Set<String> roles = new HashSet<String>();
//		for(Integer roleId : roleIds) {
//			Role role = getRoleByroleId(roleId);
//			if(role != null) {
//				roles.add(role.getRoleName());
//			}
//		}
//		return roles;
//	}
//	
//	@Override
//	public Set<String> findPermissions(Integer[] roleIds) {
//		Set<Integer> remark = new HashSet<Integer>();
//		for(Integer roleId : roleIds) {
//			Role role = getRoleByroleId(roleId);
//			if(role != null) {
//				
//				remark.add(role.getRoleId());
//			}
//		}		
//		return permissionService.findPermissions(remark);
//	}
	@Override
	public String findRoles(String roleIds) {

			Role role = getRoleByroleId(Integer.valueOf(roleIds));
			if (null == role) {
				return "";
			}
		
		return role.getRoleName();
	}
	
	@Override
	public String findPermissions(String roleIds) {
		Role role = getRoleByroleId(Integer.valueOf(roleIds));
		if (null == role) {
			return "";
		}	
		return permissionService.findPermissions(role.getRoleId());
	}

	/* (non-Javadoc)
	 * 根据roleIds查找角色列表
	 * @see com.bdms.auth.service.RoleService#getRoleByAccountId(java.lang.String)
	 */
	@Override
	public List<Role> getRolesByRoleIds(String roleIds) {
		// TODO Auto-generated method stub
		String[] subRoleIds = roleIds.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for(String s:subRoleIds){
			list.add(Integer.parseInt(s));
		}
		List<Role> roles=new ArrayList<Role>();
		for(int roleId:list){
			Role role=roleDao.findByRoleId(roleId);
			roles.add(role);
		}
		return roles;
	}
	
}
