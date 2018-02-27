package com.bdms.dams.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Bean
	public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		 	registry.addHandler(systemWebSocketHandler(),"/webSocketServer");
	        registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").withSockJS();
	}

}
