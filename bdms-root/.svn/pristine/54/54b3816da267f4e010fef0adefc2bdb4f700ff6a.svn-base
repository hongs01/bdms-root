package com.bdms.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.auth.Account;
import com.bdms.entity.auth.Role;


 

/**
  * Description:
  * 		角色实体类
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2016-1-12下午5:30:46            1.0            Created by YuXiaoLin
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{
	
	/**
	  * description:
	  * 根据角色Id查找角色
	  * @param accountId
	  * @return
	  * Account
	  * 2016-1-13 下午2:58:55
	  * by Hongs
	 */
	@Query("SELECT a FROM Role a WHERE a.roleId=?0")
	Role findByRoleId(Integer roleId);

}
