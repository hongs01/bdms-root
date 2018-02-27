package com.bdms.ftp.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Description:
 * 		客户端
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-27上午9:44:28            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class MinaClient {
	
	private Logger LOG = LoggerFactory.getLogger(MinaClient.class);

	private SocketConnector connector;
	private ConnectFuture future;
	private IoSession session;

	private String address;
	private int port;

	public MinaClient(String address, int port) {
		this.address=address;
		this.port=port;
	}

	public boolean connect() {
		
		boolean connected = false;

		try{
			// 创建一个socket连接
			connector = new NioSocketConnector();
			// 设置链接超时时间
			connector.setConnectTimeoutMillis(3000);
			// 获取过滤器链
			DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
			// 添加编码过滤器 处理乱码、编码问题
	
			filterChain.addLast("codec", new ProtocolCodecFilter(
					new TextLineCodecFactory(Charset.forName("UTF-8"))));
	
			// 消息核心处理器
			connector.setHandler(new MinaClientHandler());
	
			// 连接服务器，知道端口、地址
			future = connector.connect(new InetSocketAddress(address, port));
			// 等待连接创建完成
			future.awaitUninterruptibly();
			// 获取当前session
			session = future.getSession();
			
			connected = true;
			
		}catch( Exception e){
			 
			LOG.error("创建scoket连接失败。。。。", e);
			connected = false;
			
		}
		
		return connected;
	}

	public void setAttribute(Object key, Object value) {
		session.setAttribute(key, value);
	}

	public void send(Object message) {
		
		if(session.isClosing()){
			LOG.error(" seesion  已关闭 ， 重新获取  session" );
			session = future.getSession();
		}
		session.write(message);
		
	}

	public boolean close() {
		CloseFuture future = session.getCloseFuture();
		future.awaitUninterruptibly(1000 );
		connector.dispose();
		return true;
	}

	public SocketConnector getConnector() {
		return connector;
	}

	public IoSession getSession() {
		return session;
	}

}
