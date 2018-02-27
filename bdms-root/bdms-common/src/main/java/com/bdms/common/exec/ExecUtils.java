package com.bdms.common.exec;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;

public class ExecUtils {

	 public static String ping(String ip){
		    
	        try{
	        String command="ping"+ip;
	        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
	        ByteArrayOutputStream errorStream=new ByteArrayOutputStream();
	        CommandLine commandline=CommandLine.parse(command);
	        DefaultExecutor exec=new DefaultExecutor();
	        exec.setExitValues(null);
	        PumpStreamHandler streamHandler=new PumpStreamHandler(outputStream,errorStream);
	        exec.setStreamHandler(streamHandler);
	        exec.execute(commandline);
	        String out=outputStream.toString("gbk");
	        String error=errorStream.toString("gbk");
	        return out+error;
	        }catch(Exception e){
	            Log log=null;
	        log.error("ping task failed",e);
	        return e.toString();
	        }
	}
	 
}
