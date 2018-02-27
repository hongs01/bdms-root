package com.bdms.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.bdms.kafka.entity.KafkaPropertyEntity;

public class DSProducer {

	private static final Logger LOG = Logger.getLogger(DSProducer.class);
	
//s	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
   public static void main(String[] args) throws UnknownHostException {
		
		String hostName = InetAddress.getLocalHost().getHostName();
		
		
		
		String[] tmp = hostName.split("-");
		int n = 1;
		if(tmp.length == 2 ){
			n = Integer.parseInt(tmp[1]);
		}
		
		
		KafKaServer kafKaServer = KafKaServer.getKafKaServer();
		KafkaPropertyEntity kafkaPropertyEntry = kafKaServer.getKafkaPropertyEntry();
		
		int gap = kafkaPropertyEntry.getGap();
		int start = (n - 1 )*gap;
		
		int threads = kafkaPropertyEntry.getThreads();
		
		int sgap = gap / threads;
		LOG.info("  hostName :  " + hostName + ", 其负责的id区间为：  ( " + start +  " , " + (start + gap) + ")"  );  
		
		for (int i = 0; i < threads ; i++) {
				new Thread(new ProducerThread(kafkaPropertyEntry.getTopic(),start + i*sgap ,start + (i + 1 )*sgap,kafkaPropertyEntry.getCache(),kafkaPropertyEntry.getId_random(),kafkaPropertyEntry.getInterval())).start();
		}
		
		/*LOG.info("\r\n");
		LOG.info("\r\n");
		LOG.info(  "*******************************我是分割线***********************************" );
		LOG.info("\r\n");
		LOG.info("\r\n");*/
			
	}
	
	
	
	
}
