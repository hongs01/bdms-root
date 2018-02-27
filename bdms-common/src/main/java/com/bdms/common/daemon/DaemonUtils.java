package com.bdms.common.daemon;

import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;


//app客户端程序
public class DaemonUtils {
	public void destroy() { 
		System.out.println("destroy method...");
	} 
	public void init(DaemonContext ctx) throws DaemonInitException,Exception{
		System.out.println("init method..." +ctx.toString());
	}
	public void start() throws Exception{
		System.out.println("start method...");
	}
	public void stop() throws Exception{
		System.out.println("stop method...");
		
	}
		       
}
