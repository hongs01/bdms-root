package com.bdms.ftp.mina;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Description:
 * 		消息句柄
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-24上午11:27:05            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class Message2SparkHandler implements IoHandler {

	private Logger logger = LoggerFactory.getLogger(Message2SparkHandler.class);

	private IoAcceptor acceptor;

	Map<Long, IoSession> clientsessions = new HashMap<Long, IoSession>();

	public Message2SparkHandler(IoAcceptor acceptor) {
		this.acceptor = acceptor;
		this.clientsessions = acceptor.getManagedSessions();

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable throwable)
			throws Exception {
		long time = session.getCreationTime();
		String clientIP = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();

		logger.error(time + ":" + clientIP + "发生错误....", "FTP监听发生错误");

		throwable.printStackTrace();

	}

	@Override
	public void messageReceived(IoSession session, Object object)
			throws Exception {
		String clientIP = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();
		logger.info("接受" + clientIP + "的信息:" + object.toString());
		logger.info("当前链接数量" + clientsessions.size());
		logger.info("当前链接数量====" + acceptor.getManagedSessions().size());

		for (Long key : clientsessions.keySet()) {
			clientsessions.get(key).write(object.toString());
		}
		

	}

	@Override
	public void messageSent(IoSession session, Object object) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		String clientIP = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();

		logger.info("当前链接数量====" + acceptor.getManagedSessions().size());
		logger.debug(clientIP + "关闭...");

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		String clientIP = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();
		logger.debug(clientIP + "创建...");
		logger.debug("当前客户链接数量：" + acceptor.getManagedSessionCount());

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus idleStatus)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		String clientIP = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();
		logger.info(clientIP + "欢迎来到Spark Socket的世界....");
		//session.write("欢迎来到Spark Socket的世界....");
	}

}
