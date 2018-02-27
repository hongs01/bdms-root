package com.bdms.sqoop.WebServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bdms.sqoop.configuration.SqoopClientConfiguration;
import com.bdms.sqoop.db.conn.SqoopDbConnection;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.db.info.TableInfo;
import com.bdms.sqoop.ds_enum.SqoopProperyEntry;
import com.bdms.sqoop.server.sqoopserver.Sqoop2Server;
import com.bdms.sqoop.server.sqoopserver.SqoopDataImport;
import com.bdms.sqoop.tools.PropertiesReadTool;


public class SQOOP2WebServer {
	
	private static final Logger LOG = Logger.getLogger(SQOOP2WebServer.class);
	
	private  static SQOOP2WebServer server;
	
	private SqoopClientConfiguration scc;
	private Sqoop2Server s2s;
	
	private Map<Integer,Connection> conns = new HashMap<Integer,Connection>();
	private Map<Integer,TableInfo> importTables = new HashMap<Integer,TableInfo>();
	private Map<String,DBConnectionInfo> dbInfos = new HashMap<String,DBConnectionInfo>();
	
	private  SQOOP2WebServer(){
		
		scc = getScc();
		s2s = new SqoopDataImport(scc);
		
	};
	public static SQOOP2WebServer getSQOOP2WebServer(){
		
		if( server == null ){
			
			synchronized (SQOOP2WebServer.class) {
				if(server == null){
					server = new SQOOP2WebServer();
				}
			}
		}
		
		return server;
	}
	
	public Sqoop2Server getSqoop2Server() {
		
		if(s2s == null ){
			s2s = new SqoopDataImport(getSqoopClientConfiguration());
		}
		
		return s2s;
	}
	
	public SqoopClientConfiguration getSqoopClientConfiguration() {
		
		if(scc == null )scc = getScc();
		
		return scc;
	}

	private SqoopClientConfiguration getScc(){
		
		PropertiesReadTool prt = new PropertiesReadTool();
		
		String url = prt.getValue(SqoopProperyEntry.SQOOP_SERVER_URL, null);
		String num = prt.getValue(SqoopProperyEntry.SECURITY_MAXCONNECTIONS,"0");
		
		SqoopClientConfiguration cc = new SqoopClientConfiguration();
		cc.setSecurityMaxConnections(Integer.parseInt(num));
		cc.setSqoopServerURL(url);
		
		return cc;
	}
	
	
	public void storeimportTable(int id ,TableInfo table ){
		
		synchronized (importTables) {
			
			importTables.put(id, table);
		}
	}
	
	public TableInfo getimportTable(int id){
		
		return importTables.get(id);
		
	}
	public void removeimportTable( int id ){
		
		synchronized (importTables) {
			
			importTables.remove(id);
		}
	}
	
	public void storeConnection(int id , Connection conn ){
		
		synchronized (conns) {
			
			conns.put(id, conn);
		}
	}
	
	public Connection getConnection( int id ){
		
		return conns.get(id);
		
	}
	public void removeConnection( int id ) throws SQLException{
		
		synchronized (conns) {
			Connection connection = conns.get(id);
			SqoopDbConnection.close(connection, null, null);
			conns.remove(id);
		}
	}
	
	public void storeDBConnectionInfo(String id , DBConnectionInfo dbInfo ){
		
		synchronized (dbInfos) {
			
			dbInfos.put(id, dbInfo);
		}
	}
	
	public DBConnectionInfo getDBConnectionInfo( String id ){
		
		return dbInfos.get(id);
		
	}
	public void removeDBConnectionInfo( String id ){
		
		synchronized (dbInfos) {
		
			dbInfos.remove(id);
		}
	}
}
