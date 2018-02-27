package com.bdms.kafka;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bdms.kafka.config.KafkaConfig;
import com.bdms.kafka.util.MessageDataRandomUtil;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

public class ProducerThread implements Runnable {

	private static final Logger LOG = Logger.getLogger(ProducerThread.class); 
	
	public Producer<String, String> producer;
	
	private String topic;
	
	private int start;
	private int end;
	private int cache;
	
	private long interval;
	
	private Random random = new Random();
	private int range;
	
	private KafKaServer server;
	
	private int last_end = 0;
	
	private  String name ;
	
	private MessageDataRandomUtil messageDataRandomUtil = new MessageDataRandomUtil();;
	
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public ProducerThread( String topic,int start,int end,int cache,int range ,int interval) {
		
		producer = new Producer<String, String>(new KafkaConfig().getProducerConfig());
		
		Thread currentThread = Thread.currentThread();
		name = currentThread.getName();
		
		this.topic = topic;
		this.start = start;
		this.end = end;
		this.cache = 50000;
		this.range = range;
		this.interval = 60*1000*interval;
		
		LOG.info("线程 " + name + "负责的推送数据的id的大致区间为: ( " + start + " , " + end + " )" );
		
		server = KafKaServer.getKafKaServer();
		
	}

	@Override
	public void run() {

		List<KeyedMessage<String, String>> messages = new ArrayList<KeyedMessage<String, String>>();
		KeyedMessage<String, String> keyedMessage = null;
		
		
		
		int n = 1;
		long t = 0 ;
		

		
		while( n < Integer.MAX_VALUE ){
			
			t = server.getTime(n);
			
			if(range > 0 ){
				last_end =  end + random.nextInt(range);
			}else{
				last_end = end;
			}
			
			for (int i = start; i < last_end; i++) {
				
				messageDataRandomUtil.init(i);
				keyedMessage = new KeyedMessage<String, String>(topic,messageDataRandomUtil.toString() );
				messages.add(keyedMessage);
				
				if( messages.size() >= cache ){
					producer.send(messages);
					messages = new ArrayList<KeyedMessage<String, String>>();
				}
				Thread.yield();
			}
			
			if( messages.size() > 0 ){
				producer.send(messages);
				messages = new ArrayList<KeyedMessage<String, String>>();
			}
			
			long l = System.currentTimeMillis() - t;
			LOG.info("线程" + name + " 完成数据推送任务，推送数据 ：" + (last_end - start) + " 条,耗时： " + l + "ms");	
		    LOG.info(" n = " + n + " , 开始等待下一轮,等待的时间为：" + (interval -l) + "ms");
		    
		    server.addNum(name,n);
		    n = n + 1 ;
		    
		    try {
				Thread.sleep(( interval - (System.currentTimeMillis()-t)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    
		}
		
	}
	

}
