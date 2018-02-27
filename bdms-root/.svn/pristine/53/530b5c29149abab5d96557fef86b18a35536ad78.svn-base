package org.bdms.sqoop;


import java.util.List;

import org.apache.sqoop.client.SqoopClient; 
import org.apache.sqoop.model.MConnection; 
import org.apache.sqoop.model.MConnectionForms; 
import org.apache.sqoop.model.MForm;
import org.apache.sqoop.model.MInput;
import org.apache.sqoop.model.MJob; 
import org.apache.sqoop.model.MJobForms; 
import org.apache.sqoop.model.MSubmission; 
import org.apache.sqoop.submission.counter.Counter; 
import org.apache.sqoop.submission.counter.CounterGroup; 
import org.apache.sqoop.submission.counter.Counters; 
import org.apache.sqoop.validation.Status; 

public class Sqoop2Access {

	/**
	 *  <dependency>
			<groupId>org.apache.sqoop</groupId>
			<artifactId>sqoop-client</artifactId>
			<version>1.99.3</version>
		</dependency>
	 *
	 *
	*/
 
	public static void main(String[] args) {
		
		//1、Initialization（创建sqoop client） 
		String url = "http://192.168.10.240:12000/sqoop/"; 
		SqoopClient client = new SqoopClient(url); 
		//client.setServerUrl(url); 
		
		//2、Create Connection 
		//Dummy connection object 
		MConnection newCon = createConnection(client);
      
		//3、create job(import job) 
		//Creating dummy job object(利用connectionID去创建job)
		MJob newjob = createJob(client, newCon);
		
		//4、Job Submission 
		//Job submission start 
		
		MSubmission submission = client.startSubmission(newjob.getPersistenceId());
		
		System.out.println("Status : " + submission.getStatus()); 
		if(submission.getStatus().isRunning() && submission.getProgress() != -1) { 
			System.out.println("Progress : " + String.format("%.2f %%", submission.getProgress() * 100));

		} 
		System.out.println("Hadoop job id :" + submission.getExternalId()); 
		System.out.println("Job link : " + submission.getExternalLink());
		System.out.println( submission.getStatus());
		System.out.println( submission.getProgress());
		
		/*Counters counters = submission.getCounters(); 
		if(counters != null) { 
			System.out.println("Counters:"); 
			for(CounterGroup group : counters) { 
					System.out.print("\t"); 
					System.out.println(group.getName()); 
					for(Counter counter : group) { 
						System.out.print("\t\t"); 
						System.out.print(counter.getName()); 
						System.out.print(": "); 
						System.out.println(counter.getValue()); 
					} 
			 } 
			} */
		if(submission.getExceptionInfo() != null) { 
			System.out.println("Exception info : " +submission.getExceptionInfo()); 
		} 
		//Check job status 
		MSubmission submission1 = client.getSubmissionStatus(newjob.getPersistenceId()); 
		boolean boo = true;
		while( boo){
			if(submission1.getStatus().isRunning() && submission1.getProgress() != -1) { 
				System.out.println(submission.getStatus());
					System.out.println("Progress2 : " + submission1.getProgress()  +"----" +  String.format("%.2f %%", submission1.getProgress() * 100)); 
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else{
					boo = false;
				}
		}
		//Stop a running job 
		//submission1.stopSubmission(jid); 
		}

	private static MConnection createConnection(SqoopClient client) {
		
		MConnection newCon = client.newConnection(1); 
		//Set connection forms value");
		//client.n
		
		MConnectionForms conForms = newCon.getConnectorPart(); 
		MConnectionForms frameworkForms = newCon.getFrameworkPart();
		
		//newCon.setName("MyConnection"); 
		//Set connection forms values  
       conForms.getStringInput("connection.connectionString").setValue("jdbc:sqlserver://192.168.10.206:1433;DatabaseName=zz");
	   conForms.getStringInput("connection.jdbcDriver").setValue("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
       conForms.getStringInput("connection.username").setValue("sa");
       conForms.getStringInput("connection.password").setValue("Aa!"); 
       frameworkForms.getIntegerInput("security.maxConnections").setValue(0); 
       
       Status status = client.createConnection(newCon); 

       //检测链接的相关状态 
       if(status.canProceed()) { 
			System.out.println("Created. New Connection ID : " + newCon.getPersistenceId()); 
			} else { 
				System.out.println("Check for status and forms error "); 
			} 
       printMessage(newCon.getConnectorPart().getForms());
       printMessage(newCon.getFrameworkPart().getForms());
		return newCon;
	}

	private static MJob createJob(SqoopClient client, MConnection newCon) {
		
		MJob newjob = client.newJob( newCon.getPersistenceId(), org.apache.sqoop.model.MJob.Type.IMPORT); 
		MJobForms connectorForm = newjob.getConnectorPart(); 
		MJobForms frameworkForm = newjob.getFrameworkPart(); 
		
		newjob.setName("ImportJob"); 
		//Database configuration 
	    //connectorForm.getStringInput("table.schemaName").setValue("T_LGY_JNLK"); 
		//Input either table name or sql 
		//connectorForm.getStringInput("table.tableName").setValue("FactCJDB");
		
		
		String sql = " select * from (select SZJLX,SZJH,SLKXM,SXB,SFJH,SLGBM,PHOTO_ID,SZZXX,SZZXZQH,SMZ,DSBM,row_number() over (order by SZJH) AS id," +
				"CONVERT(nvarchar(40),DCSRQ,120) AS DCSRQ,CONVERT(nvarchar(40),DCSSJ,120) AS DCSSJ ,CONVERT(nvarchar(40),DSCSJ,120) AS DSCSJ," +
				"CONVERT(nvarchar(40),DRZSJ,120) AS DRZSJ,CONVERT(nvarchar(40),DTFSJ,120) AS DTFSJ,CONVERT(nvarchar(40),DLFSJ,120) AS DLFSJ,CONVERT(nvarchar(40),DLLSJ,120) AS DLLSJ," +
				"CONVERT(nvarchar(40),DTFSCSJ,120) AS DTFSCSJ,CONVERT(nvarchar(40),DZFSJ,120) AS DZFSJ,CONVERT(nvarchar(40),SSJTQSJ,120) AS SSJTQSJ,CONVERT(nvarchar(40),SSJRKSJ,120) AS SSJRKSJ," +
				"CONVERT(nvarchar(40),SBSJ,120) AS SBSJ,CONVERT(nvarchar(40),DUPDATE,120) AS DUPDATE,CONVERT(nvarchar(40),DINSERT,120) AS DINSERT,CONVERT(nvarchar(40),LWGXTIME,120) AS LWGXTIME" +
				" from T_LGY_JNLK ) a where ${CONDITIONS}";
		
		String sql1 = " select SZJLX,SZJH,SLKXM,DCSRQ,id from (select SZJLX,SZJH,SLKXM,CONVERT(nvarchar(40),DCSRQ,120) AS DCSRQ,row_number() over (order by SLGBM) AS id" +
				" from T_LGY_JNLK where SZJH='130534196804251815' ) a where ${CONDITIONS}";
		
		
		connectorForm.getStringInput("table.sql").setValue(sql1);
		
		//导入的字段，不设置表示*
		connectorForm.getStringInput("table.columns").setValue("SZJH,SLKXM");
		connectorForm.getStringInput("table.partitionColumn").setValue("id");
		
		//Set boundary value only if required 
		//connectorForm.getStringInput("table.boundaryQuery").setValue(""); 
		//Output configurations 
		frameworkForm.getEnumInput("output.storageType").setValue("HDFS"); 
		frameworkForm.getEnumInput("output.outputFormat").setValue("TEXT_FILE");
		//Other option: SEQUENCE_FILE 
		frameworkForm.getStringInput("output.outputDirectory").setValue("/tmp/testSqoop02"); 
		//Job resources 
		frameworkForm.getIntegerInput("throttling.extractors").setValue(1); //map数
		frameworkForm.getIntegerInput("throttling.loaders").setValue(1); // reduce数
		
		Status statusJob = client.createJob(newjob);
		
		//检测作业状态相关信息 
		if(statusJob.canProceed()) { 
			System.out.println("New Job ID: "+ newjob.getPersistenceId()); 
			} else { 
				System.out.println("Check for status and forms error "); 
			} 
		
		   printMessage(newjob.getConnectorPart().getForms());
	       printMessage(newjob.getFrameworkPart().getForms());
		return newjob;
	} 
	
	private static void printMessage(List<MForm> formList) {
		  for(MForm form : formList) {
		    List<MInput<?>> inputlist = form.getInputs();
		    if (form.getValidationMessage() != null) {
		      System.out.println("Form message: " + form.getValidationMessage());
		    }
		    for (MInput minput : inputlist) {
		      if (minput.getValidationStatus() == Status.ACCEPTABLE) {
		        System.out.println("Warning:" + minput.getValidationMessage());
		      } else if (minput.getValidationStatus() == Status.UNACCEPTABLE) {
		        System.out.println("Error:" + minput.getValidationMessage());
		      }
		    }
		  }
		}
}