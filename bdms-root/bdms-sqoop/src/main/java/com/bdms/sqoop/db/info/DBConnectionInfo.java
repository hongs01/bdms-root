/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-22 下午5:17:37
 */
package com.bdms.sqoop.db.info;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bdms.sqoop.ds_enum.DBType;
import com.bdms.sqoop.exception.NullException;


/** 
 * @author 李晓聪
 * @date 2014-12-22 下午5:17:37
 * @Description:  TODO
 */
public class DBConnectionInfo {

	private static final Logger LOG = Logger.getLogger(DBConnectionInfo.class);
	
	private String dataBaseName;
	private String host;
	private String port;
	private String userName;
	private String password;
	
	private DBType dbType;
	
	private String url;
	private String driver;

	public DBConnectionInfo() {
		super();
	}

	public DBConnectionInfo(String dataBaseName, String host, String port,
			String userName, String password, DBType dbType) {
		super();
		this.dataBaseName = dataBaseName;
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.dbType = dbType;
		
	}
	

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DBType getDbType() {
		return dbType;
	}

	public void setDbType(DBType dbType) {
		this.dbType = dbType;
	}
	
	
	public String getConnectURL() throws NullException{
		
		if( StringUtils.isBlank(url)){
			
			if(dbType == null ){
				LOG.error("DBType不能为空。");
				throw new NullException();
			}
			
			if( StringUtils.isBlank(host)){
				LOG.error("host不能为空。");
				throw new NullException();
			}
			
			if(StringUtils.isBlank(port)) port = dbType.getDefaultPort();
			
			if(StringUtils.isBlank(dataBaseName)){
				LOG.error("dataBaseName不能为空。");
				throw new NullException();
			};
			
			url = dbType.getUrlModel().replace("{host}",host).replace("{port}", port).replace("{db}", dataBaseName);
		}	
		
		return  url;
	}
	
	public String getConnectDriver() throws NullException{
		
		if(StringUtils.isBlank(driver)){
			if(dbType == null ){
				LOG.error("DBType不能为空。");
				throw new NullException();
			}else{
				driver =  dbType.getDriver();
			}
		}
		
		return driver;
	}

	@Override
	public String toString() {
		return "DBConnectionInfo [dataBaseName=" + dataBaseName + ", host="
				+ host + ", port=" + port + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
	
}
