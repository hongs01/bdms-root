package com.bdms.entity.dams;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "firstWeight")
@Entity
public class FirstWeight implements java.io.Serializable {

	/** */
	private static final long serialVersionUID = -7774622516596759665L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "areaId")
	private Integer areaId;

	@Column(name = "typeName")
	private String typeName;

	@Column(name = "typeCode")
	private String typeCode;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "alarmLevel")
	private Integer alarmLevel;

	@Column(name = "alarmCount")
	private Integer alarmCount;

	@Column(name = "firstMark")
	private Double firstMark;
	
	@Transient
	private String stationName;
	
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Transient
	private List<SecondWeight> secondWeightList;

	public List<SecondWeight> getSecondWeightList() {
		return secondWeightList;
	}

	public void setSecondWeightList(List<SecondWeight> secondWeightList) {
		this.secondWeightList = secondWeightList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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

	public double getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(Double firstMark) {
		this.firstMark = firstMark;
	}

}
