package com.bdms.entity.auth;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/* 
 * Description:
 * 		帐号实体对象
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-8上午9:46:30            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 8620068064334937340L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "accountName")
	private String accountName;

	@Column(name = "password")
	private String password;
	
	@Column(name="lastUpdateTime")
	private Timestamp lastUpdateTime;
	
	@Column(name="isActive")
	private boolean isActive;

	@OneToOne(cascade=CascadeType.ALL,optional=true)
	@JoinColumn(name="userid",referencedColumnName="id",unique=true)
	private User user;

	@Column(name="roles")
	private String roles;
	
	@Column(name="roleIds")
	private String roleIds;
	
	@Column(name="version")
	@Version
	private Long version;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	
}
