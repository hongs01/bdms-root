package com.bdms.common.db;

/* 
 * Description:
 * 		拼接连接URL
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2014-12-19下午2:56:09            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class DbUrl {

	private String serverIp;

	private String port;
	
	private final String MYSQL_URL_PRE="jdbc:mysql://"; //TODO
	
	private final String SQLSERVER_URL_PRE=""; //TODO
	
	private final String ORCL_URL_PRE="";     //TODO
	
	private final String BD2_URL_PRE="";     //TODO
	
	private String URL;
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerIp() {
		return serverIp;
	}
	
	public DbUrl(String ip,String port,DbType dbType)
	{
		switch(dbType)
		{
			case MYSQL:
				setURL(MYSQL_URL_PRE+ip+":"+port);
				break;
			case SQLSERVER:
				setURL(SQLSERVER_URL_PRE+ip+":"+port);
			case DB2:
				setURL(BD2_URL_PRE+ip+":"+port);
			break;
			case ORCL:
				setURL(ORCL_URL_PRE+ip+":"+port);
			break;
			default:
			break;
		}
	}
}
