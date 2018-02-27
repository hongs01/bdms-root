package com.bdms.flume.test;

import java.nio.charset.Charset;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

/**
  * Description:
  * 		flume-1.5.2测试程序。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2016-1-19下午3:21:14            1.0            Created by chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2016, 迪爱斯通信设备有限公司保留。
 */
public class MyRpcClientFacade implements Runnable{
	
	  private RpcClient client;
	  private String hostname;
	  private int port;
	  private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	  * description:		初始化client
	  * @param hostname		主机名/IP
	  * @param port			端口号
	  * void
	  * 2014-5-9 下午3:21:48
	  * by chenfeng
	 */
	public void init(String hostname, int port) {
	    // Setup the RPC connection
	    this.hostname = hostname;
	    this.port = port;
	    this.client = RpcClientFactory.getDefaultInstance(hostname, port);
	    // Use the following method to create a thrift client (instead of the above line):
	    // this.client = RpcClientFactory.getThriftInstance(hostname, port);
	  }

	  /**
	  * description:		发送数据到flume
	  * @param data			String类型的数据
	  * void
	  * 2014-5-9 下午3:22:33
	  * by chenfeng
	 */
	public void sendDataToFlume(String data) {
	    // Create a Flume Event object th                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          at encapsulates the sample data
	    Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

	    // Send the event
	    try {
	      client.append(event);
	    } catch (EventDeliveryException e) {
	      // clean up and recreate the client
	      client.close();
	      client = null;
	      client = RpcClientFactory.getDefaultInstance(hostname, port);
	      // Use the following method to create a thrift client (instead of the above line):
	      // this.client = RpcClientFactory.getThriftInstance(hostname, port);
	    }
	  }

	public synchronized void sendDataToFlumeInThread(String data){
		 // Create a Flume Event object th                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          at encapsulates the sample data
	    Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

	    // Send the event
	    try {
	      client.append(event);
	    } catch (EventDeliveryException e) {
	      // clean up and recreate the client
	      client.close();
	      client = null;
	      client = RpcClientFactory.getDefaultInstance(hostname, port);
	      // Use the following method to create a thrift client (instead of the above line):
	      // this.client = RpcClientFactory.getThriftInstance(hostname, port);
	    }
	}
	  /**
	  * description:		关闭连接
	  * void
	  * 2014-5-9 下午3:23:04
	  * by chenfeng
	 */
	public void cleanUp() {
	    // Close the RPC connection
	    client.close();
	  }

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" send data");
			sendDataToFlumeInThread(data);
		}
	}
}
