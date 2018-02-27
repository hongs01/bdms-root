package com.bdms.auth;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpringCoreConfigTest  extends JunitSpringInitialize{

	/**日志配置 */
	private static Logger logger=LoggerFactory.getLogger(SpringCoreConfigTest.class);
	
	@Test
	public void testSpingIOC() {
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
