package com.bdms.auth.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.entity.auth.Account;
import com.bdms.entity.auth.Role;

import java.util.Set;
/* 
 * Description:
 * 		账户接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-7下午4:46:16            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface AccountService {

	/**
	 * description:保存帐号
	 * 
	 * @param account
	 * @return 保存的帐号 Account 2015-7-8 上午9:26:09 by Yuxl
	 */
	Account saveAccount(Account account);

	/**
	 * description: 通过Id 查找帐号
	 * 
	 * @param id
	 * @return 查找到的帐号 Account 2015-7-8 上午9:26:36 by Yuxl
	 */
	Account getAccountById(Integer id);

	/**
	 * description:通过Id删除帐号
	 * 
	 * @param id
	 *            void 2015-7-8 上午9:27:19 by Yuxl
	 */
	void delAccount(Integer id);

	/**
	 * description:
	 * 
	 * @param page
	 *            当前页面
	 * @param rows
	 *            页面条数
	 * @param accountname
	 *            用户名
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return Page<Account> 2015-7-8 下午2:04:31 by Yuxl
	 */
	Page<Account> findAllWhitPage(int page, int rows, String accountName,
			String startDate, String endDate);

	/**
	 * description:返回带分页信息的帐号列表内容
	 * 
	 * @param pageable
	 * @return Page<Account> 2015-7-8 上午9:30:40 by Yuxl
	 */
	Page<Account> finaAllByPage(Pageable pageable);

	/**
	 * description:帐号增加角色
	 * 
	 * @param role
	 *            void 2015-7-8 上午9:30:50 by Yuxl
	 */
	void accountAddrole(Role role);

	/**
	 * description: 给指定的帐号增加单个角色
	 * 
	 * @param role
	 * @param accountid
	 *            void 2015-7-8 上午9:31:20 by Yuxl
	 */
	void accountAddrole(Role role, Integer accountid);

	/**
	 * description:根据用户名查找用户
	 * 
	 * @param accountName
	 * @return Account 2015-7-9 下午4:05:34 by Yuxl
	 */
	Account findByAccountName(String accountName);
	
	public Set<String> findRoles(String accountName);

    public Set<String> findPermissions(String accountName);
}
