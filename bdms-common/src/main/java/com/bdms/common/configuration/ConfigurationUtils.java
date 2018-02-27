package com.bdms.common.configuration;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationUtils {

private ConfigurationUtils(){
	
}

private static ConfigurationUtils configurationUtils;
public synchronized static ConfigurationUtils getInstance(){
	if(configurationUtils==null){
		configurationUtils=new ConfigurationUtils();
	}
	return configurationUtils;
}

public static CompositeConfiguration config=new CompositeConfiguration();
static{
		try{
			config.addConfiguration(new PropertiesConfiguration("jdbc.properties"));
			
		}catch(ConfigurationException e){
			e.printStackTrace();
		}
	}
	public String getProperty(String key){
		return config.getString(key);
	}
}

