package com.bdms.entity.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;



@Entity
@Table(name="role")
public class Role implements Serializable{
	
	
	private static final long serialVersionUID = 8620068064334937340L;

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="roleId")
	private Integer roleId;
	
	@Column(name="roleName")
	private String roleName;
	
	@Column(name="verion")
	@Version
	private Long version;
	
	@Column(name="accountId")
	private Integer accountId;
	
	@Column(name="permissionIds")
	private String permissionIds;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionId(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	
}
