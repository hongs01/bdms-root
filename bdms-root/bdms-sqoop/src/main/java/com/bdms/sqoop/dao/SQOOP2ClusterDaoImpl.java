package com.bdms.sqoop.dao;

import org.springframework.stereotype.Service;

import com.bdms.sqoop.WebServer.SQOOP2WebServer;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;
import com.bdms.sqoop.server.sqoopserver.Sqoop2Server;

@Service(value="clusterDao")
public class SQOOP2ClusterDaoImpl implements SQOOP2ClusterDao {
	
	public void doImport(DBConnectionInfo dbInfo,DBParam dp,ClusterParam cp) throws Exception{
		
		SQOOP2WebServer server = SQOOP2WebServer.getSQOOP2WebServer();
		Sqoop2Server sqoop2Server = server.getSqoop2Server();
		
		long connID = sqoop2Server.createConnection("myconn", dbInfo );
		long  jobId = sqoop2Server.createJobByConnection(connID, "myjob"+Math.random()*10, dp, cp);
		sqoop2Server.startJob(jobId);
			
	
	}
	
	

}
