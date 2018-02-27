/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-22 下午5:18:19
 */
package com.bdms.sqoop.db.info;

/** 
 * @author 李晓聪
 * @date 2014-12-22 下午5:18:19
 * @Description:  TODO
 */
public class ColumnInfo {
	
	private String name;
	private String type;
	private String size;
	
	public ColumnInfo() {
		super();
	}

	public ColumnInfo(String name, String type, String size) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ColumnInfo [name=" + name + ", type=" + type + ", size=" + size
				+ "]";
	}

}
