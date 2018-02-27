/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-23 下午4:35:14
 */
package com.bdms.sqoop.server.sqoopserver;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MForm;
import org.apache.sqoop.model.MInput;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.submission.SubmissionStatus;
import org.apache.sqoop.validation.Status;

import com.bdms.sqoop.configuration.SqoopClientConfiguration;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;


/** 
 * @author 李晓聪
 * @date 2014-12-23 下午4:35:14
 * @Description:  TODO
 */
public abstract class Sqoop2Server {
	
	private static final Logger LOG = Logger.getLogger(Sqoop2Server.class);
	
	private SqoopClientConfiguration cc;
	private SqoopClient client;
	
	protected Sqoop2Server( SqoopClientConfiguration cc ){
		this.cc = cc;
		this.client = new SqoopClient(cc.getSqoopServerURL());
	}
	
	public abstract long createConnection(String connectionName,DBConnectionInfo ci) throws Exception;
	public abstract long createJobByConnection(long connectionId,String jobName,DBParam dp,ClusterParam cp) throws Exception;

	public void startJob(long jobId){
		
		MSubmission submission = client.startSubmission(jobId);
		
		SubmissionStatus status = submission.getStatus();
		LOG.info("Status : " + status); 
		if(status.isRunning() && submission.getProgress() != -1) { 
			LOG.info(" Progress : " + String.format("%.2f %%", submission.getProgress() * 100)); 
		} 
		LOG.info("Hadoop job id :" + submission.getExternalId()); 
		LOG.info("Job link : " + submission.getExternalLink()); 
		
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
			LOG.error("Exception info : " +submission.getExceptionInfo()); 
		} 
		
		//Check job status 
		printJobStatus(jobId); 
		
	}


	/**
	 * @Title:getJobStatus 
	 * @param jobId
	 * @Return void
	 * @Description:TODO
	 */
	protected void printJobStatus(long jobId) {
		
		MSubmission submission = client.getSubmissionStatus(jobId); 
		if(submission.getStatus().isRunning() && submission.getProgress() != -1) { 
				LOG.info("Progress : " + String.format("%.2f %%", submission.getProgress() * 100)); 
		}
	}
	protected void stopJob(long jobId) {
		
		client.stopSubmission(jobId);
		client.deleteJob(jobId);
	}

	
	public SqoopClientConfiguration getCc() {
		return cc;
	}
	public SqoopClient getClient() {
		return client;
	}
	
	
	@SuppressWarnings("rawtypes")
	protected  void printMessage(List<MForm> formList) {
		
		  for(MForm form : formList) {
		    List<MInput<?>> inputlist = form.getInputs();
		    if (form.getValidationMessage() != null) {
		     LOG.info("Form message: " + form.getValidationMessage());
		    }
		    for (MInput minput : inputlist) {
		      if (minput.getValidationStatus() == Status.ACCEPTABLE) {
		    	  LOG.info("Warning:" + minput.getValidationMessage());
		      } else if (minput.getValidationStatus() == Status.UNACCEPTABLE) {
		    	  LOG.info("Error:" + minput.getValidationMessage());
		      }
		    }
		  }
		}

}
