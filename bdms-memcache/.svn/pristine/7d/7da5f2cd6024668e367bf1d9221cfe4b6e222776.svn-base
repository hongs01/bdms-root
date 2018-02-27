package com.bdms.memcache.configuration;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whalin.MemCached.SockIOPool;

public class Memcachedconfig {

	private static Logger log = LoggerFactory.getLogger(Memcachedconfig.class);

	public static CompositeConfiguration config = new CompositeConfiguration();

	public static final String MECACHESERVERCONFIG = "memcache/memcache-server-config.properties";

	public static final String SERVERS = "servers";

	public static final String INITCONN = "InitConn";

	public static final String MINCONN = "MinConn";

	public static final String MAXCONN = "MaxConn";

	public static final String MAXIDLE = "MaxIdle";

	public static final String MAINTSLEEP = "MaintSleep";

	public static final String NAGLE = "Nagle";

	public static final String SOCKETTO = "SocketTO";

	public static final String SOCKETCONNECTTO = "SocketConnectTO";

	public Memcachedconfig() {

		try {
			log.info("初始化Memcached配置,建立连接池...");
			config.addConfiguration(new PropertiesConfiguration(
					MECACHESERVERCONFIG));
			SockIOPool sockIOPool = SockIOPool.getInstance();
			for (int i = 0; i < config.getStringArray(SERVERS).length; i++) {
				System.out.println(config.getStringArray(SERVERS)[i]);
			}
			sockIOPool.setServers(config.getStringArray(SERVERS));
			sockIOPool.setInitConn(config.getInt(INITCONN));
			sockIOPool.setMinConn(config.getInt(MINCONN));
			sockIOPool.setMaxConn(config.getInt(MAXCONN));
			
			sockIOPool.setMaxIdle(config.getInt(MAXIDLE)*1000*60);
			
			sockIOPool.setNagle(config.getBoolean(NAGLE));
			sockIOPool.setSocketTO(config.getInt(SOCKETTO));
			sockIOPool.setSocketConnectTO(config.getInt(SOCKETCONNECTTO));
			sockIOPool.initialize();
		} catch (Exception e) {
			log.error("初始化失败...");
			e.printStackTrace();
		}
	}

	public static Memcachedconfig getInstance() {
		Memcachedconfig memcachedconfig=new Memcachedconfig();
		return memcachedconfig;
	}
}
