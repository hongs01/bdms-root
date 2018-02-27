package com.bdms.entity.dams;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "area")
@Entity
public class Area implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 485286804821446268L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "areaId")
	private String areaId;

	@Column(name = "areaName")
	private String areaName;

	@Column(name = "area")
	private String area;
	
	@Column(name = "alarmLevel")
	private Integer alarmLevel;

	@Column(name = "alarmCount")
	private Integer alarmCount;

	@Column(name = "totalMark")
	private double totalMark;
	
	@Transient
	private List<FirstWeight> firstWeightList;
	
	@Column(name = "DZWLNum")
	private Long DZWLNum;
	
	@Column(name = "increment")
	private Integer increment;
	
	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	public Long getDZWLNum() {
		return DZWLNum;
	}

	public void setDZWLNum(Long dZWLNum) {
		DZWLNum = dZWLNum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	

	public List<FirstWeight> getFirstWeightList() {
		return firstWeightList;
	}

	public void setFirstWeightList(List<FirstWeight> firstWeightList) {
		this.firstWeightList = firstWeightList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}

	public double getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(double totalMark) {
		this.totalMark = totalMark;
	}

}
