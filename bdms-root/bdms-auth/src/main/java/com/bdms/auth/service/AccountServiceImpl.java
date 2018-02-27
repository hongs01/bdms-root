package com.bdms.auth.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.Integer;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bdms.auth.dao.AccountDao;
import com.bdms.entity.auth.Account;
import com.bdms.entity.auth.Role;

/* 
 * Description:
 * 		帐号接口实现
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-8上午9:46:54            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RoleService roleService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.auth.service.AccountService#saveAccount(com.bdms.core.auth.Account
	 * )
	 */
	@Override
	public Account saveAccount(Account account) {
		account.setActive(true); // 增加的帐号初始的时候是激活状态
		Date date = new Date(); // 插入当前时间到数据库中
		Timestamp timestamp = new Timestamp(date.getTime());
		account.setLastUpdateTime(timestamp);
		return accountDao.save(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.auth.service.AccountService#getAccountById(java.lang.Integer)
	 */
	@Override
	public Account getAccountById(Integer id) {
		return accountDao.findOne(id);

	}

	@Override
	public void delAccount(Integer id) {
		try {
			accountDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.auth.service.AccountService#finaAllByPage(org.springframework
	 * .data.domain.Pageable)
	 */
	@Override
	public Page<Account> finaAllByPage(Pageable pageable) {
		return accountDao.findAll(pageable);
	}

	@Override
	public void accountAddrole(Role role, Integer accountid) {
		//role.setRoleId(123);
		//role.setRoleName("管理员");
		//Account account = accountDao.findOne(accountid);
		//account.setRoleIds(role.getRoleId());
	}

	@Override
	public void accountAddrole(Role role) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdms.auth.service.AccountService#findAllWhitPage(int, int)
	 */
	@Override
	public Page<Account> findAllWhitPage(int page, int rows,
			final String accountName, final String startDate,
			final String endDate) {
		Sort sort = new Sort(Direction.DESC, "lastUpdateTime"); // 按照更新时间倒叙排序
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 构建动态查询
		Specification<Account> specification = new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (accountName != null && accountName != "") {
					predicate.add(cb.like(
							root.get("accountName").as(String.class), "%"
									+ accountName + "%"));
				}
				if (startDate != null && startDate != "") {
					predicate.add(cb.greaterThan(
							root.get("lastUpdateTime").as(String.class),
							startDate));
				}
				if (endDate != null && endDate != "") {
					predicate.add(cb.lessThan(
							root.get("lastUpdateTime").as(String.class),
							endDate));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};

		return accountDao.findAll(specification, pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.auth.service.AccountService#findByAccountName(java.lang.String)
	 */
	@Override
	public Account findByAccountName(String accountName) {
		return accountDao.findByAccountName(accountName);
	}

	@Override
	public Set<String> findRoles(String accountName) {
		Account account = findByAccountName(accountName);
		Set<String> set = new HashSet<String>();
		if (null == account) {
			return set;
		}
 		String[] roleIdS = account.getRoleIds().split(",");
		for(String roleId : roleIdS) {
		set.add(roleService.findRoles(roleId));
		}
		//return roleService.findRoles(account.getRoleIds().toArray(new Integer[0]));
		return set;
	}

	@Override
	public Set<String> findPermissions(String accountName) {
		Account account = findByAccountName(accountName);
		Set<String> set = new HashSet<String>();
		if (null == account){
			return set;
		}
		String[] roleIdS = account.getRoleIds().split(",");
		for(String roleId : roleIdS) {
		set.add(roleService.findPermissions(roleId));
		}
		return set;
	}

	}

	
	


