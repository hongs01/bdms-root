package com.bdms.sqoop.dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.sqoop.server.dbserver.DBServer;

@Service(value="sQOOP2DBDao")
public class SQOOP2DBDaoImpl implements SQOOP2DBDao{
	
	@Autowired
	private DBServer dbServer;
	
	public List getTables(Connection conn, boolean isContainVeriw) throws Exception {
		
		dbServer.setConn(conn);
		return dbServer.getTables(isContainVeriw);
	}

	public List getColumsByTableName(Connection conn, String tableName) throws Exception {
		
		dbServer.setConn(conn);
		
		return dbServer.getTableColumn(tableName);
	}
	
}
