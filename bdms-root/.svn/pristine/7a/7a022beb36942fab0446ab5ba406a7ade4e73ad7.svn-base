package com.bdms.spark.util;

import java.io.Serializable;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkConfigReadUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static Logger LOG = LoggerFactory.getLogger(SparkConfigReadUtil.class);
	
	private final static String sparkConfigPropertiesPath = "spark/spark-server-config.properties";
	
	private static CompositeConfiguration config = new CompositeConfiguration();
	
	private SparkConfigReadUtil(){}
	
	public static CompositeConfiguration  getConfig(){
		
		try {
			config.addConfiguration(new PropertiesConfiguration(sparkConfigPropertiesPath));
		} catch (ConfigurationException e) {
			LOG.error("加载配置文件失败", e);
		}
		
		return config;
		
	}
	
	public static void main(String[] args) {
		
		CompositeConfiguration config2 = getConfig();
		String name = config2.getString(SparkConfigProperty.GJSocket.getName());
		String port = config2.getString(SparkConfigProperty.GJPort.getName());
		
		System.out.println( name + "----" + port );
	}

}
