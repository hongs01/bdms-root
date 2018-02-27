package com.bdms.entity.dams;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 
 * Description:
 * 		站点实体对象
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-3 上午10:17:12       1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Table(name = "criterion")
@Entity
public class Criterion implements Serializable{
	
	private static final long serialVersionUID = 8620068064334937340L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "code")
	private String code;

	@Column(name = "type")
	private String type;
	
	@Column(name="level")
	private String level;
	
	@Column(name="version")
	private String version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
