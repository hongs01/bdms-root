package org.bdms.sqoop;

import java.util.ArrayList;
import java.util.List;


import com.bdms.sqoop.configuration.SqoopClientConfiguration;
import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.db.sql.SQLServerCreateSQL;
import com.bdms.sqoop.ds_enum.DBType;
import com.bdms.sqoop.ds_enum.OutputFormatType;
import com.bdms.sqoop.ds_enum.SqoopProperyEntry;
import com.bdms.sqoop.ds_enum.StorageType;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;
import com.bdms.sqoop.server.sqoopserver.Sqoop2Server;
import com.bdms.sqoop.server.sqoopserver.SqoopDataImport;
import com.bdms.sqoop.tools.PropertiesReadTool;

public class Sqoop2ImportTest {

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Sqoop2ImportTest obj = new Sqoop2ImportTest();
		Sqoop2Server s2s = new SqoopDataImport(obj.getSqoopClientConfiguration());
		long connID = s2s.createConnection("myconn", obj.getDBConnectionInfo());
		long jobId = s2s.createJobByConnection(connID, "myjob"+Math.random()*10, obj.getDBParam(), obj.getClusterParam());
		s2s.startJob(jobId);
	}
	
	private SqoopClientConfiguration getSqoopClientConfiguration(){
		
		//PropertiesReadTool prt = new PropertiesReadTool();
		
		//String url = prt.getValue(SqoopProperyEntry.SQOOP_SERVER_URL, null);
		//String num = prt.getValue(SqoopProperyEntry.SECURITY_MAXCONNECTIONS,"0");
		
		//System.err.println(url);
		
		SqoopClientConfiguration cc = new SqoopClientConfiguration();
		cc.setSecurityMaxConnections(10);
		cc.setSqoopServerURL("http://hadoop-6:12000/sqoop/");
		
		return cc;
		
	}
	
	private DBConnectionInfo getDBConnectionInfo(){
		
		DBConnectionInfo dc = new DBConnectionInfo();
		dc.setDataBaseName("HJSL_SH_YS");
		dc.setDbType(DBType.SQLSERVER);
		dc.setHost("192.168.2.235");
		dc.setPassword("sa");
		dc.setUserName("sa");
		
		return dc;
	}
	private DBParam getDBParam(){
		
		List<ColumnInfo> lcs = new ArrayList<ColumnInfo>();
		lcs.add(new ColumnInfo("ID","varchar","32"));
		lcs.add(new ColumnInfo("JCJ_AJXX_ID","varchar","32"));
		lcs.add(new ColumnInfo("JLZT","int","10"));
		lcs.add(new ColumnInfo("CSZT","int","10"));
		lcs.add(new ColumnInfo("SJC","datetime","23"));
		lcs.add(new ColumnInfo("BZ","varchar","400"));
		lcs.add(new ColumnInfo("CJBH","varchar","32"));
		lcs.add(new ColumnInfo("YSAJBH","varchar","32"));
		lcs.add(new ColumnInfo("CJKSSJ","datetime","23"));
		lcs.add(new ColumnInfo("CJJSSJ","datetime","23"));
		lcs.add(new ColumnInfo("CJPC","varchar","20"));
		lcs.add(new ColumnInfo("CDCLQD","varchar","8000"));
		
		DBParam dp = new DBParam();
		dp.setConditions("");
		dp.setImportColumns(lcs);
		dp.setPartitionColumn("pk");
		dp.setScs(new SQLServerCreateSQL());
		dp.setTableName("JCJ_CJJL");
		
		return dp;
	}
	private ClusterParam getClusterParam(){
		
		ClusterParam cp = new ClusterParam();
		cp.setExtractors(24);
		cp.setLoaders(4);
		cp.setOutputDirectory("/tmp/sqoop07");
		cp.setOutputFormat(OutputFormatType.TEXT_FILE);
		cp.setStorageType(StorageType.HDFS);
		
		return cp;
	}
}
