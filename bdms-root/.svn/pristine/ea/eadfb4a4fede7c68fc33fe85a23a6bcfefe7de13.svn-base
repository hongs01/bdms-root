package com.bdms.entity.dams;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wifiData")
@Entity
public class WifiData implements Serializable{
	private static final long serialVersionUID = 485286804821446268L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
 
    @Column(name = "stationName")
	private String stationName;
    
    @Column(name = "apname")
   	private String apname;

   public String getApname() {
		return apname;
	}

	public void setApname(String apname) {
		this.apname = apname;
	}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getStationName() {
	return stationName;
}

public void setStationName(String stationName) {
	this.stationName = stationName;
}

}
