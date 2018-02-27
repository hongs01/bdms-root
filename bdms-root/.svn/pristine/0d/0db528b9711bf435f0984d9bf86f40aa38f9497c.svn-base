/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-17 上午9:45:13
 */
package org.bdms.sqoop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.bdms.sqoop.ds_enum.DBType;



/** 
 * @author 李晓聪
 * @date 2014-12-17 上午9:45:13
 * @Description:  TODO
 */
public class DBUtilsTest {
	/*
	@Test
	public void test01() throws ClassNotFoundException, SQLException{
		
		Connection conn = JDBCConnTool.getConn(DBType.SQLSERVER, "192.168.9.39", "zz", "sa", "Aa!");
		
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet tables = metaData.getTables(null, "%","%",new String[]{"TABLE"}); 
		
		while(tables.next()){
			String tableName = tables.getString("TABLE_NAME") ;
			ResultSet columnSet = metaData.getColumns(null, "%", tableName, "%");
			while(columnSet.next()){
				String columnName = columnSet.getString("COLUMN_NAME");
				System.out.println("   " + columnName);
			}
		}
		
	}
	*/
	
/*	public void test02(){
		
		try {
			DBManager db = new DBManager(DBType.SQLSERVER,"192.168.9.39", "zz", "sa", "Aa!");
			
			List<String> tables = db.getTables(true);
			for(String tableName : tables){
				
				System.out.println(tableName + ":");
				db.getTableColumn(tableName);
				
				System.out.println();
				System.out.println("*************************************");
				System.out.println();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
	/**
	 * @Title:test03 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @Return void
	 * @Description:TODO 获取数据库下的所有的库名
	 */
	/*@Test
	public void test03() throws ClassNotFoundException, SQLException{
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String url = "jdbc:sqlserver://192.168.9.39:1433;DatabaseName=zz";
		String url = "jdbc:sqlserver://192.168.9.39:1433;xopenStates=false;sendTimeAsDatetime=true;trustServerCertificate=false;sendStringParametersAsUnicode=true;selectMethod=direct;responseBuffering=adaptive;packetSize=8000;loginTimeout=15;lockTimeout=-1;lastUpdateCount=true;encrypt=false;disableStatementPooling=true;databaseName=zz;applicationName=Microsoft SQL Server JDBC Driver";
		
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, "sa", "Aa!");
		
		System.out.println( connection.getMetaData().getURL());
		
		//System.out.println(connection.getClientInfo().keySet());
		
		
		DatabaseMetaData metaData = connection.getMetaData();
		
		ResultSet catalogs = metaData.getCatalogs();
		while(catalogs.next()){
			System.out.println(catalogs.getString("TABLE_CAT"));
		}
		
	}*/
	
}
