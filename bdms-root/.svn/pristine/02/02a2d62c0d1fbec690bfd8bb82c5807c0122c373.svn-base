/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2015-3-9 下午2:38:32
 */
package com.bdms.sqoop.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bdms.sqoop.ds_enum.SqoopProperyEntry;


/** 
 * @author 李晓聪
 * @date 2015-3-9 下午2:38:32
 * @Description:  TODO
 */
public class PropertiesReadTool {
	
	private final static Logger LOG = Logger.getLogger(PropertiesReadTool.class);
	
	private final static String DEFAULT_PATH="/sqoop/sqoop-server-config.properties";
	
	private String properties_path;
	private Properties prop;
	
	public PropertiesReadTool(){
		this.properties_path = DEFAULT_PATH;
	}
	public PropertiesReadTool(String properties_path){
		this.properties_path = properties_path;
	}
	
	private Properties loadProperties() throws IOException{
		
		if(prop != null){
			return prop;
		}
		
		if( StringUtils.isNotBlank(properties_path) ){
			
			InputStream resourceAsStream = PropertiesReadTool.class.getResourceAsStream(properties_path);
			if( resourceAsStream == null){
				throw new IOException("指定路径配置文件加载失败: " + properties_path);
			}
			
			prop = new Properties();
			prop.load(resourceAsStream);
			
		}else{
			throw new IOException(properties_path + " 为空 ！");
		}
		
		return prop;
	}
	
	public String getValue(SqoopProperyEntry key,String default_value){
		
		String value = null;
		try {
			Properties loadProperties = loadProperties();
			if(default_value == null ){
				value= loadProperties.getProperty(key.toString());
			}else{
				value = loadProperties.getProperty(key.toString(),default_value);
			}
		} catch (IOException e) {
			LOG.error("加载配置文件失败。", e);
		}
		
		return value;
	}
	
	public static void main(String[] args) {
		
		PropertiesReadTool p = new PropertiesReadTool();
		System.out.println(p.getValue(SqoopProperyEntry.SQOOP_SERVER_URL, null));
	}
}
