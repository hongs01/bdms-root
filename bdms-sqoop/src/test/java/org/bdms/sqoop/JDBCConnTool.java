/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-17 上午9:54:20
 */
package org.bdms.sqoop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bdms.sqoop.ds_enum.DBType;


/** 
 * @author 李晓聪
 * @date 2014-12-17 上午9:54:20
 * @Description:  TODO
 */
public class JDBCConnTool {
	
	private static Connection conn ;
	
	private JDBCConnTool(){}
	
	public static Connection getConn( DBType dbType,String host,String dbName,String user,String password) throws ClassNotFoundException, SQLException{
		
		String url = dbType.getUrlModel().replace("{host}", host).replace("{db}", dbName);
		String driver = dbType.getDriver();
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
		
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
