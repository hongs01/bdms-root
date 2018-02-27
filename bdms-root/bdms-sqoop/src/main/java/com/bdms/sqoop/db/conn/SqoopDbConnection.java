/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-17 上午9:54:20
 */
package com.bdms.sqoop.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bdms.sqoop.db.info.DBConnectionInfo;


/** 
 * @author 李晓聪
 * @date 2014-12-17 上午9:54:20
 * @Description:  TODO
 */
public class SqoopDbConnection {
	
	private static Connection conn ;
	
	private SqoopDbConnection(){}
	
	public static Connection getConn( DBConnectionInfo connectionInfo) throws Exception{
		
		Class.forName(connectionInfo.getConnectDriver());
		conn = DriverManager.getConnection(connectionInfo.getConnectURL(), connectionInfo.getUserName(), connectionInfo.getPassword());
		
		return  conn;
	}
	
	public static Connection getConn(String url,String driver,String user,String password) throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
		
		return  conn;
	}
	
	public static void close(Connection conn,Statement statement,ResultSet resultSet) throws SQLException{
		
		if( resultSet != null ){
			
			resultSet.close();
			resultSet=null;
		}
		if( statement != null ){
			
			statement.close();
			statement=null;
		}
		if( conn != null ){
			
			conn.close();
			conn=null;
		}
			
	}


}
