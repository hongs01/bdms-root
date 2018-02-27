package com.bdms.sqoop.service;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.sqoop.WebServer.SQOOP2WebServer;
import com.bdms.sqoop.dao.SQOOP2DBDao;
import com.bdms.sqoop.db.conn.SqoopDbConnection;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.db.info.TableInfo;


@Service(value="dbService")
public class SQOOP2DBServiceImpl implements SQOOP2DBService {
	
	private SQOOP2WebServer server;
	
	@Autowired
	private SQOOP2DBDao sQOOP2DBDao;
	
	public SQOOP2DBServiceImpl(){
		
		server = SQOOP2WebServer.getSQOOP2WebServer();
	}
	
	public List getTables(DBConnectionInfo dbInfo,boolean isContainVeriw)  throws Exception {
		
		
		return sQOOP2DBDao.getTables(getConnection( dbInfo ), isContainVeriw);
	}

	public List getColumsByTableName(DBConnectionInfo dbInfo,String tableName)  throws Exception{
		
		List columsByTableName = sQOOP2DBDao.getColumsByTableName( getConnection( dbInfo ), tableName);
		
		TableInfo table = new TableInfo();
		table.setColumns(columsByTableName);
		table.setName(tableName);
		
		server.storeimportTable(dbInfo.hashCode(), table);
		
		return columsByTableName;
	}

	private Connection getConnection(DBConnectionInfo dbInfo ) throws Exception{
		
		int hashCode = dbInfo.hashCode();
		Connection connection = server.getConnection(hashCode);
		
		if(connection == null){
			connection = SqoopDbConnection.getConn(dbInfo);
			server.storeConnection(hashCode, connection);
		}
		
		return connection;
	}

}
