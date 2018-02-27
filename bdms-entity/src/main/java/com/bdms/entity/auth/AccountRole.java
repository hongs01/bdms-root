package com.bdms.entity.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/* 
 * Description:
 * 		帐号，角色关系表
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-7下午4:35:14            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Entity
@Table(name="accountRole")
public class AccountRole implements Serializable{

	/** */
	private static final long serialVersionUID = -579949479133074818L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "accountId")
	private Integer accountId;
	
	@Column(name = "roleId")
	private Integer roleId; 
	
	@Column(name="verion")
	@Version
	private Long version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
