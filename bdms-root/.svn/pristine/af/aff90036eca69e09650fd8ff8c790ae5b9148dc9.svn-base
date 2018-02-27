package com.bdms.dams.station;

import java.io.FileNotFoundException;

import javax.security.auth.login.AppConfigurationEntry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-core-config.xml" ,"classpath:spring/spring-hadoop-config.xml" })

public class JunitSpringInitialize extends AbstractJUnit4SpringContextTests {

	/*static {
		try {
			Log4jConfigurer.initLogging("classpath:log/log4j.properties");
			
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}
*/
	@Test
	public void initTest() {
		System.out.println("日志配置成功...");
		System.out.println("spring,配置成功...");
		String [] beans =applicationContext.getBeanDefinitionNames();
		logger.info("\n\n");
		logger.info("开始打印注入容器的Bean...");
		logger.info("\n\n");
		for (String string : beans) {
			logger.info("注入SpringIOC中的Bean:"+string);
		}
		logger.info("\n\n");
	}
}
