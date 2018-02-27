/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-24 上午9:18:17
 */
package com.bdms.sqoop.server.sqoopserver;

import org.apache.log4j.Logger;
import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MConnection;
import org.apache.sqoop.model.MConnectionForms;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MJobForms;
import org.apache.sqoop.validation.Status;

import com.bdms.sqoop.configuration.SqoopClientConfiguration;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.exception.CreateException;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;


/** 
 * @author 李晓聪
 * @date 2014-12-24 上午9:18:17
 * @Description:  TODO sqoop数据导入实现类
 */
public class SqoopDataImport extends Sqoop2Server {
	
	private static final Logger LOG = Logger.getLogger(SqoopDataImport.class);
	
	private SqoopClient client;
	private SqoopClientConfiguration cc;
	
	public SqoopDataImport(SqoopClientConfiguration cc) {
		
		super(cc);
		this.cc = cc;
		client = getClient();
	}

	/* (non-Javadoc)
	 * @see com.ds.sqoop.server.Sqoop2Server#createConnection(java.lang.String, com.ds.sqoop.db.info.DBConnectionInfo)
	 */
	@Override
	public long createConnection(String connectionName, DBConnectionInfo ci) throws Exception {
		
	   MConnection newCon = client.newConnection(1); 

	   MConnectionForms conForms = newCon.getConnectorPart(); 
	   MConnectionForms frameworkForms = newCon.getFrameworkPart();
	   
	   newCon.setName(connectionName);
       conForms.getStringInput("connection.connectionString").setValue(ci.getConnectURL());
	   conForms.getStringInput("connection.jdbcDriver").setValue(ci.getConnectDriver()); 
       conForms.getStringInput("connection.username").setValue(ci.getUserName());
       conForms.getStringInput("connection.password").setValue(ci.getPassword()); 

       frameworkForms.getIntegerInput("security.maxConnections").setValue(cc.getSecurityMaxConnections()); 
       
       Status status = client.createConnection(newCon); 
       
       printMessage(newCon.getConnectorPart().getForms());
       printMessage(newCon.getFrameworkPart().getForms());

       //检测链接的相关状态 
       if(status.canProceed()) { 
    	   long persistenceId = newCon.getPersistenceId();
			LOG.info(" Created. New Connection ID : " + persistenceId);
			return persistenceId;
		} else { 
			LOG.error("Create connection fail,please check for status and forms error!");
			throw new CreateException();
		} 
	
	}

	/* (non-Javadoc)
	 * @see com.ds.sqoop.server.Sqoop2Server#createJobByConnection(long, java.lang.String, com.ds.sqoop.param.DBParam, com.ds.sqoop.param.ClusterParam)
	 */
	@Override
	public long createJobByConnection(long connectionId, String jobName,
			DBParam dp, ClusterParam cp) throws Exception {
		
		MJob newjob = client.newJob( connectionId, org.apache.sqoop.model.MJob.Type.IMPORT); 
		MJobForms connectorForm = newjob.getConnectorPart(); 
		MJobForms frameworkForm = newjob.getFrameworkPart(); 
		
		newjob.setName(jobName); 
		
		//connectorForm.getStringInput("table.schemaName").setValue("T_LGY_JNLK"); 
		//Input either table name or sql 
		//connectorForm.getStringInput("table.tableName").setValue("FactCJDB");
		
		
		connectorForm.getStringInput("table.sql").setValue(dp.getImportSQL());
		
		//connectorForm.getStringInput("table.columns").setValue("SZJH,SLKXM");
		connectorForm.getStringInput("table.partitionColumn").setValue(dp.getPartitionColumn());
		
		//Set boundary value only if required 
		//connectorForm.getStringInput("table.boundaryQuery").setValue(""); 
		//Output configurations 
		frameworkForm.getEnumInput("output.storageType").setValue(cp.getStorageType().toString()); 
		frameworkForm.getEnumInput("output.outputFormat").setValue(cp.getOutputFormat().toString());
		//Other option: SEQUENCE_FILE 
		frameworkForm.getStringInput("output.outputDirectory").setValue(cp.getOutputDirectory()); 
		//Job resources 
		frameworkForm.getIntegerInput("throttling.extractors").setValue(cp.getExtractors()); 
		frameworkForm.getIntegerInput("throttling.loaders").setValue(cp.getLoaders()); 
		
		Status statusJob = client.createJob(newjob);
		
		printMessage(newjob.getConnectorPart().getForms());
		printMessage(newjob.getFrameworkPart().getForms());
		
		//检测作业状态相关信息 
		if(statusJob.canProceed()) {
			
			long persistenceId = newjob.getPersistenceId();
			LOG.info("New Job ID: "+ persistenceId); 
			return persistenceId;
			
		} else { 
				LOG.error("Create job faile,please check for status and forms error ");
				throw new CreateException("创建job失败");
		} 
		
	}

	

}
