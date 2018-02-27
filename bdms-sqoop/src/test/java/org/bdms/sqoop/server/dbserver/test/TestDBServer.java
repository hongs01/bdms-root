package org.bdms.sqoop.server.dbserver.test;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bdms.sqoop.db.conn.SqoopDbConnection;
import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.db.info.DataBaseInfo;
import com.bdms.sqoop.db.info.TableInfo;
import com.bdms.sqoop.ds_enum.DBType;
import com.bdms.sqoop.server.dbserver.DBServer;

public class TestDBServer {
	
	
	
	//@Test
	public void testAll() throws Exception{
		
		Connection conn = SqoopDbConnection.getConn( getSQLSERVER() );
		
		Assert.assertNotNull("获取数据库连接失败", conn);
		
		DBServer ds = new DBServer(conn);
		//List<TableInfo> databases = ds.getTables(false);
		List<ColumnInfo> tableColumn = ds.getTableColumn("JCJ_CJJL");
		for(ColumnInfo ci : tableColumn){
			
			System.out.println(ci.getName() + "======" + ci.getType() + "======" + ci.getSize());
		}
		
	}

	private DBConnectionInfo getOreacle(){
			
			DBConnectionInfo dc = new DBConnectionInfo();
			dc.setDataBaseName("dsdb");
			dc.setDbType(DBType.ORACLE);
			dc.setHost("192.168.9.100");
			dc.setPassword("jj");
			dc.setUserName("jj");
			
			return dc;
		}
	
	private DBConnectionInfo getSQLSERVER(){
		
		DBConnectionInfo dc = new DBConnectionInfo();
		dc.setDataBaseName("HJSL_SH_YS");
		dc.setDbType(DBType.SQLSERVER);
		dc.setHost("192.168.2.235");
		dc.setPassword("sa");
		dc.setUserName("sa");
		
		return dc;
	}
	
	private DBConnectionInfo getMYSQL(){
		
		DBConnectionInfo dc = new DBConnectionInfo();
		dc.setDataBaseName("hive");
		dc.setDbType(DBType.MYSQL);
		dc.setHost("192.168.9.38");
		dc.setPassword("root");
		dc.setUserName("root");
		
		return dc;
	}
}
