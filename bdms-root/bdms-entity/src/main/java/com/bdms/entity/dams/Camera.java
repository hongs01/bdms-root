package com.bdms.entity.dams;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
  * Description:
  * 		摄像头信息实体类
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-15上午11:37:39            1.0            Created by YuXiaoLin
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Table(name = "camera")
@Entity
public class Camera implements Serializable{

	/** */
	private static final long serialVersionUID = 5419624491328991248L;
	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="stationId")
	private String stationId;
	
	@Column(name="stationName")
	private String stationName;
	
	@Column(name="cameraMACId")
	private String cameraMACId;
	
	@Column(name="cameraName")
	private String cameraName;
	
	@Column(name="IsSelect")
	private boolean IsSelect;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCameraMACId() {
		return cameraMACId;
	}

	public void setCameraMACId(String cameraMACId) {
		this.cameraMACId = cameraMACId;
	}

	public String getCameraName() {
		return cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	public boolean isIsSelect() {
		return IsSelect;
	}

	public void setIsSelect(boolean isSelect) {
		IsSelect = isSelect;
	}

}
