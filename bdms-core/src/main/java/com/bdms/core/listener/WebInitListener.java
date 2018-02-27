package com.bdms.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.core.init.WebinitConfig;


/* 
 * Description:
 * 		监听项目启动，初始化一些配置
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-6-15上午10:17:12            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class WebInitListener implements ServletContextListener {

	
	private static Logger log = LoggerFactory.getLogger(WebInitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("web各类配置文件开始初始化...");
		WebinitConfig.intiweb(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub

	}

}
