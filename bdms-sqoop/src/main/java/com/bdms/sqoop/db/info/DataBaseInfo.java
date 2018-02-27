/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-22 下午5:17:49
 */
package com.bdms.sqoop.db.info;

import java.util.List;

/** 
 * @author 李晓聪
 * @date 2014-12-22 下午5:17:49
 * @Description:  TODO
 */
public class DataBaseInfo {

	
	
	private String name;
	private String url;
	
	private List<TableInfo> tables;
	
	public DataBaseInfo() {
		super();
	}

	public DataBaseInfo(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TableInfo> getTables() {
		return tables;
	}

	public void setTables(List<TableInfo> tables) {
		this.tables = tables;
	}
	
}
