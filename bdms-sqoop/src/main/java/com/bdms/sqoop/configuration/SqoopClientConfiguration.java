/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-22 下午6:54:38
 */
package com.bdms.sqoop.configuration;

/** 
 * @author 李晓聪
 * @date 2014-12-22 下午6:54:38
 * @Description:  TODO
 */
public class SqoopClientConfiguration {
	
	private String sqoopServerURL;
	private int securityMaxConnections;

	public SqoopClientConfiguration() {
		super();
	}

	public SqoopClientConfiguration(String sqoopServerURL,
			int securityMaxConnections) {
		super();
		this.sqoopServerURL = sqoopServerURL;
		this.securityMaxConnections = securityMaxConnections;
	}

	public String getSqoopServerURL() {
		return sqoopServerURL;
	}

	public void setSqoopServerURL(String sqoopServerURL) {
		this.sqoopServerURL = sqoopServerURL;
	}

	public int getSecurityMaxConnections() {
		return securityMaxConnections;
	}

	public void setSecurityMaxConnections(int securityMaxConnections) {
		this.securityMaxConnections = securityMaxConnections;
	}
	
	

}
