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
@Table(name = "video")
@Entity
public class Video implements Serializable{
	
	private static final long serialVersionUID = 8620068064334937340L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "areaId")
	private String areaId;
	
	@Column(name = "cameraId")
	private String cameraId;
	
	@Column(name = "cameraName")
	private String cameraName;
	
	@Column(name = "areaName")
	private String areaName;
	
	
	@Column(name="cameraVRCId")
	private String cameraVRCId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getCameraName() {
		return cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCameraVRCId() {
		return cameraVRCId;
	}

	public void setCameraVRCId(String cameraVRCId) {
		this.cameraVRCId = cameraVRCId;
	}
	
}
