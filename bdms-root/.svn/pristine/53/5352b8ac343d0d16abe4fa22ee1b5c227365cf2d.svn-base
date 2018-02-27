package com.bdms.hbase.configuration;

import java.io.IOException;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bdms.hbse.enums.ConfigEnum;

@Component(value="hc")
public class HbaseConf {

	private static Logger LOG = LoggerFactory.getLogger(HbaseConf.class);
	
	public static final String HBASESERVERCONFIG = "hbase/hbase-server-config.properties";
	public static final String HBASESITE = "hbase/hbase-site.xml";
	public static final String HBASEHDFS = "hbase/hdfs-site.xml";
	
	private static HbaseConf hc;
	
	private CompositeConfiguration config = new CompositeConfiguration();
	private Configuration configuration;
	private HConnection conn;
 
	private HbaseConf(){
	/*	
		try {
			config.addConfiguration(new PropertiesConfiguration(HBASESERVERCONFIG));
		} catch (ConfigurationException e) {
			LOG.error("加载配置文件失败", e);
			System.exit(-1);
		}*/
		configuration = HBaseConfiguration.create();
		configuration.addResource(HBASEHDFS);
		configuration.addResource(HBASESITE);
		
		//configuration.set("hbase.zookeeper.quorum", config.getString(ConfigEnum.ZKQUORUM.toString()) ); //zookeeper定位
		//configuration.set("hbase.zookeeper.property.clientPort", config.getString(ConfigEnum.CLIENTPORT.toString()));
		//configuration.set("hbase.rootdir",config.getString(ConfigEnum.ROOTDIR.toString()));
		
		//注释这部本，可以不链接habse 进行测试
//		try {
//			conn = HConnectionManager.createConnection(configuration);
//		} catch (IOException e) {
//			LOG.error("初始化HConnection失败", e);
//		}
		
	}
	
	public static HbaseConf getInstance(){
		
		if( hc == null ){
			
			synchronized (HbaseConf.class) {
				if( hc == null ){
					hc = new HbaseConf();
				}
			}
		}
		
		return hc;
	}

	public  Configuration getConfiguration() {
		
		return configuration;
	}
	
	public CompositeConfiguration getCompositeConfiguration(){
		
		return config;
	}
	
	public HConnection getHConnection(){
		
		return conn;
	}


	

}
