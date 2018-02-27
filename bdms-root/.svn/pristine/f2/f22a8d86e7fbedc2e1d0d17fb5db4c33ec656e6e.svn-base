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
 * 2015-7-24 下午5:17:12       1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Table(name = "station")
@Entity
public class Station implements Serializable{
	
	private static final long serialVersionUID = 8620068064334937340L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "version")
	private String version;

	@Column(name = "line")
	private String line;
	
	@Column(name="station")
	private String station;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ename")
	private String ename;

	@Column(name="x")
	private Double x;
	
	@Column(name="y")
	private Double y;
	
	@Column(name="transferSt")
	private String transferSt;
	
	@Column(name="level")
	private String level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public String getTransferSt() {
		return transferSt;
	}

	public void setTransferSt(String transferSt) {
		this.transferSt = transferSt;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
