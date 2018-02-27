package com.bdms.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.auth.Account;



/* 
 * Description:
 * 		接口：帐号相关信息数据操作接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-9下午5:21:38            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Integer>,
		JpaSpecificationExecutor<Account> {

	/**
	 * description:根据帐号名称查找帐号
	 * 
	 * @param accountName
	 * @return Account 2015-7-9 下午5:22:12 by Yuxl
	 */
	@Query("SELECT a FROM Account a WHERE a.accountName=?0")
	Account findByAccountName(String accountName);

}
