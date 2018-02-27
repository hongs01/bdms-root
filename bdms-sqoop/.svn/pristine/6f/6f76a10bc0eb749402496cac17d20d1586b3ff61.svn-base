package com.bdms.sqoop.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.sqoop.WebServer.SQOOP2WebServer;
import com.bdms.sqoop.dao.SQOOP2ClusterDao;
import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.db.info.TableInfo;
import com.bdms.sqoop.db.sql.SQLServerCreateSQL;
import com.bdms.sqoop.ds_enum.OutputFormatType;
import com.bdms.sqoop.ds_enum.StorageType;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;


@Service(value="clusterService")
public class SQOOP2ClusterServiceImpl implements SQOOP2ClusterService {
	
	@Autowired
	private SQOOP2ClusterDao clusterDao;

	public void doImport(String sessionId,List<String> importColumNames) throws Exception {
		
		SQOOP2WebServer server = SQOOP2WebServer.getSQOOP2WebServer();
		
		DBConnectionInfo dbInfo = server.getDBConnectionInfo(sessionId);
		TableInfo getimportTable = server.getimportTable(dbInfo.hashCode());
		
		DBParam dbParam = getDBParam(getimportTable,importColumNames);
		ClusterParam clusterParam = getClusterParam(getimportTable.getName());
		
		clusterDao.doImport(dbInfo, dbParam, clusterParam);
		
	}
	
	private DBParam getDBParam(TableInfo importTable,List<String> columns_import ){
		
		
		List<ColumnInfo> lcs = new ArrayList<ColumnInfo>();
		
		for(ColumnInfo ci : importTable.getColumns() ){
			
			if(columns_import.contains(ci.getName())){
				lcs.add(ci);
			}
		}
		
		DBParam dp = new DBParam();
		dp.setConditions("");
		dp.setImportColumns(lcs);
		dp.setPartitionColumn("pk");
		dp.setScs(new SQLServerCreateSQL());
		dp.setTableName(importTable.getName());
		
		return dp;
	}
	
	private ClusterParam getClusterParam(String dir){
		
		ClusterParam cp = new ClusterParam();
		cp.setExtractors(24);
		cp.setLoaders(4);
		cp.setOutputDirectory("/sqoop/" + dir  );
		cp.setOutputFormat(OutputFormatType.TEXT_FILE);
		cp.setStorageType(StorageType.HDFS);
		
		return cp;
	}
	

}
