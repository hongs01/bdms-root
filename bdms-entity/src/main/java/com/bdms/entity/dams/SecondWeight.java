package com.bdms.entity.dams;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "secondWeight")
@Entity
public class SecondWeight implements java.io.Serializable {

	/** */
	private static final long serialVersionUID = -3710590288313564489L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	// @JoinColumn(name = "typeId")
	// private FirstWeight firstWeight;
	@Column(name = "typeId")
	private Integer typeId;

	@Column(name = "name")
	private String name;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "stationId")
	private String stationId;

	@Column(name = "alarmLevel")
	private Integer alarmLevel;

	@Column(name = "secondMark")
	private Double secondMark;
	
	@Column(name = "peopleNum")
	private Integer peopleNum;

	public Double getSecondMark() {
		return secondMark;
	}

	public void setSecondMark(Double secondMark) {
		this.secondMark = secondMark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

}
