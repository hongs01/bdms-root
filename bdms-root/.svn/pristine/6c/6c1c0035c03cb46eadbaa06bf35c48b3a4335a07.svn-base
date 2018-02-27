package com.bdms.dams.mysqlwifi.schedule;

import java.sql.*;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class WifiMysqlConn {	
	
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final Logger LOG = Logger.getLogger(WifiMysqlConn.class);     
	private final static String wifiMysqlConfigPropertiesPath = "system/wifimysql-config.properties";
	static PropertiesConfiguration propertiesConfig=null;
	static{try{
		Class.forName(DRIVER);
		propertiesConfig=new PropertiesConfiguration(wifiMysqlConfigPropertiesPath);
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	final static String URL=propertiesConfig.getString("wifi.mysql.url");
	final static String USERNAME=propertiesConfig.getString("wifi.mysql.user");
	final static String PASSWORD=propertiesConfig.getString("wifi.mysql.password");
	public final static String PREFIX=propertiesConfig.getString("wifi.mysql.tableprefix");
	
	public final static String MYSQLTABTIME=propertiesConfig.getString("wifi.mysql.tabletime");
	public final static String HBASETABNAME=propertiesConfig.getString("wifi.hbase.tablename");
	
	final static String POLLINGRATE=propertiesConfig.getString("wifi.mysql.pollingrate");

	public static Connection getConnection(){
		try{
			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void closeConnection(ResultSet rs,PreparedStatement pst,Connection conn)
	{
		try{
			if(rs!=null)
			{
				rs.close();
			}
		}catch(Exception e){}

		try{
			if(pst!=null)
			{
				pst.close();
			}
		}catch(Exception e){}

		try{
			if(conn!=null)
			{
				conn.close();
			}
		}catch(Exception e){}

	}	

}