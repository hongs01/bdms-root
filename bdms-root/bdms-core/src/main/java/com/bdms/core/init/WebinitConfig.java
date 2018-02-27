package com.bdms.core.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebinitConfig {
	private static Logger log = LoggerFactory.getLogger(WebinitConfig.class);

	private static final String SYSTEMCONFIG = "system/system-config.properties";

	private static CompositeConfiguration config = new CompositeConfiguration();

	public WebinitConfig(ServletContextEvent servletContextEvent) {

		try {
			config.addConfiguration(new PropertiesConfiguration(SYSTEMCONFIG));
		} catch (Exception e) {
			log.error("加载web配置文件失败！");
		}

		ServletContext servletContext = servletContextEvent.getServletContext();
		String baseURL = (String) servletContext.getAttribute("APP_PATH");
		String theme = (String) servletContext.getAttribute("THEME");
		String uitheme=(String) servletContext.getAttribute("THEMEUI");
		if (baseURL == null || "".equals(baseURL)) {
			servletContext.setAttribute("APP_PATH",
					config.getString("bdms.baseURL"));
		}
		if (theme == null || "".equals(theme)) {
			servletContext
					.setAttribute("THEME", config.getString("bdms.theme"));
		}
		if (uitheme == null || "".equals(uitheme)) {
			servletContext
			.setAttribute("THEMEUI", config.getString("bdms.theme.ui"));
		}
	}

	public static void intiweb(ServletContextEvent servletContextEvent) {
		log.info("初始化web配置文件...");
		new WebinitConfig(servletContextEvent);
	}

}
