package com.bdms.dams.websocket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	final static Logger LOGGER=LoggerFactory.getLogger(SystemWebSocketHandler.class);
	
	private static final List<WebSocketSession> SESSIONS=new ArrayList<WebSocketSession>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		LOGGER.debug("contect websocket sucess!\n");
		SESSIONS.add(session);
		LOGGER.debug("当前连接人数："+SESSIONS.size());
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		 String text=(String) message.getPayload();
		 LOGGER.debug(text);
		 session.sendMessage(new TextMessage(text));		
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		LOGGER.error("contect error");
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		LOGGER.debug("contect websocket Closed");

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
