/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2015-3-9 下午2:38:32
 */
package com.bdms.kafka.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bdms.kafka.enums.KafkaPropertyEnum;



/** 
 * @author 李晓聪
 * @date 2015-3-9 下午2:38:32
 * @Description:  TODO
 */
public class PropertiesReadUtil {
	
	private final static Logger LOG = Logger.getLogger(PropertiesReadUtil.class);
	
	private final static String DEFAULT_PATH="/kafka/kafka-server-config.properties";
	
	private String properties_path;
	private Properties prop;
	private String value ;
	public PropertiesReadUtil(){
		this.properties_path = DEFAULT_PATH;
	}
	public PropertiesReadUtil(String properties_path){
		this.properties_path = properties_path;
	}
	
	private void loadProperties() throws IOException{
		
		if( StringUtils.isNotBlank(properties_path) ){
			
			InputStream resourceAsStream = PropertiesReadUtil.class.getResourceAsStream(properties_path);
			if( resourceAsStream == null){
				throw new IOException("指定路径配置文件加载失败: " + properties_path);
			}
			prop = new Properties();
			prop.load(resourceAsStream);
			
		}else{
			throw new IOException(properties_path + " 为空 ！");
		}
		
	}
	
	public String getValue(KafkaPropertyEnum key,String default_value){
		
		try {
			if(prop == null ){
				loadProperties();
			}
			if(default_value == null ){
				value= prop.getProperty(key.toString());
			}else{
				value = prop.getProperty(key.toString(),default_value);
			}
		} catch (IOException e) {
			LOG.error("加载配置文件失败。", e);
		}
		
		return value;
	}
}
