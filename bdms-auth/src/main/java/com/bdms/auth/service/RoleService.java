package com.bdms.auth.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.entity.auth.Role;
 


@Service
public interface RoleService {
    
	void saveRole(Role role);
	
	Role getRoleById(Integer id);
		
	void delRole( Integer id);
	
	Page<Role> finaAllByPage(Pageable pageable);
	
	String findRoles(String roleIds);
	
	String findPermissions(String roleIds);
	
	/*根据roleIds查找角色*/
	List<Role> getRolesByRoleIds(String roleIds);
}
