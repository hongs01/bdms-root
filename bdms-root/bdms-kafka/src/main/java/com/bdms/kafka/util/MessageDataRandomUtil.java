package com.bdms.kafka.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDataRandomUtil {

	private String simId;

	private String gis_x;

	private String gis_y;

	private String time;

	private String phoneNoPer;

	private String address;

	private String name;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	GISRandomUtil gis = new GISRandomUtil();
	private String[] ss = null;
	
	public MessageDataRandomUtil() {
		super();
	}
	
	public MessageDataRandomUtil(Integer simId) {
		
		init(simId);
		
	}
	
	public void init(Integer simId){
		
		this.simId = String.format("%050d", simId);
		ss = gis.getGISData();
		gis_x =ss[0];
		gis_y = ss[1];
		time = df.format(new Date());
		phoneNoPer = "188027369";
		address = "上海徐汇区";
		name = "张三李四";
	}
	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getGis_x() {
		return gis_x;
	}

	public void setGis_x(String gis_x) {
		this.gis_x = gis_x;
	}

	public String getGis_y() {
		return gis_y;
	}

	public void setGis_y(String gis_y) {
		this.gis_y = gis_y;
	}

	public String getPhoneNoPer() {
		return phoneNoPer;
	}

	public void setPhoneNoPer(String phoneNoPer) {
		this.phoneNoPer = phoneNoPer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return simId + " " + gis_x + " " + gis_y + " " + time + " "
				+ phoneNoPer + " " + address + " " + name;
	}

}
