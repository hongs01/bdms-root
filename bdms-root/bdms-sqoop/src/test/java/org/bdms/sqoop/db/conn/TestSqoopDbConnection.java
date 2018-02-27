package org.bdms.sqoop.db.conn;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import com.bdms.sqoop.db.conn.SqoopDbConnection;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.ds_enum.DBType;

public class TestSqoopDbConnection {
	
	//@Test
	public void testAll() throws Exception{
		
		DBConnectionInfo dc = new DBConnectionInfo();
		dc.setDataBaseName("zz");
		dc.setDbType(DBType.SQLSERVER);
		dc.setHost("192.168.9.39");
		dc.setPassword("Aa!");
		dc.setUserName("sa");
		
		
		Connection conn = SqoopDbConnection.getConn(dc);
		
		Assert.assertNotNull("获取数据库连接失败.",conn);
		
	}

}
