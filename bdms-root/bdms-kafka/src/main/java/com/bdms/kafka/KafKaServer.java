package com.bdms.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bdms.kafka.entity.KafkaPropertyEntity;
import com.bdms.kafka.enums.KafkaPropertyEnum;
import com.bdms.kafka.util.PropertiesReadUtil;

public class KafKaServer {
	
	
	private static final Logger LOG = Logger.getLogger(KafKaServer.class);
	
	private static KafKaServer server;
	
	private KafkaPropertyEntity kpe;
	
	private int totalCount;
	private int count = 0;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	long t = 0;
	long t2 = 0;
	int cn = 0;
	private  Date date ;
	
	
	private KafKaServer(){
		
		kpe = new KafkaPropertyEntity();
		
		PropertiesReadUtil prop = new PropertiesReadUtil();
		
		String topic = prop.getValue(KafkaPropertyEnum.TOPIC,null);
		if(StringUtils.isBlank(topic)){
			LOG.error("配置文件中  topic 为空 ");
			System.exit(-1);
		}else{
			kpe.setTopic(topic);
		}
		
		String threads = prop.getValue(KafkaPropertyEnum.THREADS_NUM,null);
		if(StringUtils.isBlank(threads)){
			LOG.warn("配置文件中  threads_num 为空 ,启用默认值50");
			kpe.setThreads(50);
		}else{
			kpe.setThreads(Integer.parseInt(threads));
		}
		
		String gap = prop.getValue(KafkaPropertyEnum.GAP,null);
		int s = 0;
		if(StringUtils.isBlank(gap)){
			LOG.warn("配置文件中  gap 为空 ,启用默认值0");
			kpe.setGap(0);
		}else{
			s = Integer.parseInt(gap);
			kpe.setGap(s);
		}
		
		String random = prop.getValue(KafkaPropertyEnum.ID_RANDOM,null);
		if(StringUtils.isBlank(random)){
			LOG.warn("配置文件中  id_random 为空 ,启用默认值0");
			kpe.setId_random(0);
		}else{
			kpe.setId_random(Integer.parseInt(random));
		}
		
		String cache = prop.getValue(KafkaPropertyEnum.MAXRECORDCACHE,null);
		if(StringUtils.isBlank(cache)){
			LOG.warn("配置文件中  maxrecordcache 为空 ,启用默认值50000");
			kpe.setCache(50000);
		}else{
			kpe.setCache(Integer.parseInt(cache));
		}
		
		String interval = prop.getValue(KafkaPropertyEnum.INTERVAL,null);
		if(StringUtils.isBlank(interval)){
			LOG.warn("配置文件中  interval 为空 ,启用默认值：1");
			kpe.setInterval(1);
		}else{
			kpe.setInterval(Integer.parseInt(interval));
		}
		
		LOG.info(" kafka数据推送配置为 : " + kpe.toString());
	}
	
	
	public static KafKaServer getKafKaServer(){
		
		if( server == null ){
			synchronized (KafKaServer.class) {
				if(server == null){
					server = new KafKaServer();
				}
			}
		}
		return server;
	}
	
	public void recordProducerMSG(int n,int threadCount , String topicName,String startTime,int interval){
		
		totalCount = threadCount;
		
		LOG.info(  "*******************************我是分割线***********************************" );
		LOG.info("\r\n");
		LOG.info("\r\n");
		LOG.info(  "第" + n + " 轮开始往kafka推送数据： "  );
		LOG.info(  "推送配置： "  );
		LOG.info(  "接收数据的topic：  " +  topicName );
		LOG.info(  "推送数据的线程数：     " +  threadCount );
		LOG.info(  "推送数据的开始时间： " +  startTime );
		LOG.info(  "推送数据的时间间隔： " +  interval +" 分钟");
		LOG.info("\r\n");
		LOG.info("\r\n");
		LOG.info(  "*******************************我是分割线***********************************" );
		
	}

	public synchronized void addNum(String name, int n ){
			
		count = count + 1 ;
		t2 =  System.currentTimeMillis() - t;
		
		if(t2 > 60000){
			LOG.error(  name + " 线程掉队。。。 " );
			System.exit(-1);
		}
		
		if(n != cn){
			LOG.error(  "推送批次错乱，n=" + n + " , cn = " + cn );
			System.exit(-1);
		}
		
		if( count >= totalCount ){
			
			count = 0;
			
			LOG.info(  "*******************************我是分割线***********************************" );
			LOG.info("\r\n");
			LOG.info("\r\n");
			LOG.info("第" + n + "轮数据插入完成，结束时间为：" + df.format( new Date()) +"  耗时： " + t2 + " ms"  );
			LOG.info("\r\n");
			LOG.info("\r\n");
			LOG.info(  "*******************************我是分割线***********************************" );
		}
		
	}
	
	public synchronized long getTime( int n ){
		
		if(n != cn ){
			if(n != cn){
				date = new Date();
				recordProducerMSG(n,kpe.getThreads(), kpe.getTopic(), df.format(date), kpe.getInterval());
				t = date.getTime();
				cn = n;
			}
		}
		
		return t;
	}
	
	public KafkaPropertyEntity getKafkaPropertyEntry(){
		
		return kpe;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(KafKaServer.getKafKaServer().getKafkaPropertyEntry().toString());
	}
	
	
}
