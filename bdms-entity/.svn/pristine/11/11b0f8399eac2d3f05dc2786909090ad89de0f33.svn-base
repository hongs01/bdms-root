package com.bdms.entity.dams;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="demo")
@Entity
public class Demo implements Serializable  {
 
	/**序列化*/
	private static final long serialVersionUID = 521157041038982298L;

	 @Id
	 @Column(name="id")
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	 
	 @Column(name="info")
	 private String info;
	 
	 @Column(name="name")
	 private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
}
