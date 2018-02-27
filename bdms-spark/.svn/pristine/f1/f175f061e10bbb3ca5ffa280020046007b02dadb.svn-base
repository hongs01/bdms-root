package com.bdms.spark.customreceiver

import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.storage.StorageLevel
import org.apache.spark.Logging
import org.apache.mina.transport.socket.nio.NioSocketConnector
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import java.nio.charset.Charset
import java.net.InetSocketAddress
import org.apache.mina.core.service.IoHandler
import org.apache.mina.core.session.IoSession
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.transport.socket.SocketConnector
import org.apache.mina.core.future.ConnectFuture

/**
  * Description:
  * 		使用mina的自定义的socket接收器。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-1下午1:54:49            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
class CustomSocketReceiver (host: String, port: Int)
  extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) with Logging {

  def onStart() {
    // Start the thread that receives data over a connection
    new Thread("Socket Receiver") {
      override def run() { receive() }
    }.start()
  }

  def onStop() {
   // There is nothing much to do as the thread calling receive()
   // is designed to stop by itself isStopped() returns false
  }

  /** Create a socket connection and receive data until receiver is stopped */
  private def receive(){
    
      var connector : SocketConnector = null 
      var future : ConnectFuture = null
      var session : IoSession = null
      
      try {
	        // 创建一个socket连接
			connector = new NioSocketConnector();
			// 设置链接超时时间
			connector.setConnectTimeoutMillis(3000);
			// 获取过滤器链
			val filterChain = connector.getFilterChain();
			// 添加编码过滤器 处理乱码、编码问题
	
			filterChain.addLast("codec", new ProtocolCodecFilter(
					new TextLineCodecFactory(Charset.forName("UTF-8"))));
	
			// 消息核心处理器
			connector.setHandler( new IoHandler(){
			  
					  // 当客户端连接进入时  
					 @throws(classOf[Exception])
			         def sessionOpened(session : IoSession ) {
					   logInfo("会话被打开....")
					 }  
			  
					 @throws(classOf[Exception])
				     def exceptionCaught(session : IoSession , cause : Throwable)  {  
				        logError("客户端发送信息异常....",cause);  
				     }  
				  
				     // 当客户端发送消息到达时  
					  @throws(classOf[Exception])
				     def messageReceived(session : IoSession , message : Object )  {  
	
					    store( message.toString()); 
				      
				      }  
					  
				     @throws(classOf[Exception])
				     def sessionClosed( session : IoSession) {  
				        logInfo("客户端与服务端断开连接.....");  
				      } 
				     
				     @throws(classOf[Exception])
				     def sessionCreated(session : IoSession ) {
				        logInfo("创建新会话....")
				     }
				     
				     @throws(classOf[Exception])
				     def sessionIdle(session : IoSession ,  status : IdleStatus){}
				     
				     @throws(classOf[Exception])
					 def messageSent( session : IoSession,message : Object ) {}  
			  
			});
	
			// 连接服务器，知道端口、地址
			future = connector.connect(new InetSocketAddress(host, port));
			// 等待连接创建完成
			future.awaitUninterruptibly();
			// 获取当前session
			session = future.getSession();
			
       }catch {
	     case e: java.net.ConnectException =>
	       restart("Error connecting to " + host + ":" + port, e)
	     case t: Throwable =>
	       restart("Error receiving data", t)
		}finally {
			val future = session.getCloseFuture();
			future.awaitUninterruptibly(10000);
			connector.dispose();
		}
		
  }
  
  
  
}