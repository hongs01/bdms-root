package com.bdms.kafka.config;

import java.util.Properties;

import kafka.producer.ProducerConfig;

public class KafkaConfig {
	final String MBL =  "dswhhadoop-10:6667,dswhhadoop-1:6667,dswhhadoop-11:6667,dswhhadoop-12:6667,dswhhadoop-13:6667,dswhhadoop-14:6667,dswhhadoop-15:6667,dswhhadoop-16:6667," +
						"dswhhadoop-17:6667,dswhhadoop-18:6667,dswhhadoop-19:6667,dswhhadoop-2:6667,dswhhadoop-20:6667,dswhhadoop-21:6667,dswhhadoop-22:6667,dswhhadoop-23:6667," +
						"dswhhadoop-24:6667,dswhhadoop-25:6667,dswhhadoop-26:6667,dswhhadoop-27:6667,dswhhadoop-28:6667,dswhhadoop-29:6667,dswhhadoop-3:6667,dswhhadoop-30:6667," +
						"dswhhadoop-31:6667,dswhhadoop-32:6667,dswhhadoop-33:6667,dswhhadoop-34:6667,dswhhadoop-35:6667,dswhhadoop-36:6667,dswhhadoop-37:6667,dswhhadoop-38:6667," +
						"dswhhadoop-39:6667,dswhhadoop-4:6667,dswhhadoop-40:6667,dswhhadoop-41:6667,dswhhadoop-42:6667,dswhhadoop-43:6667,dswhhadoop-44:6667,dswhhadoop-45:6667," +
						"dswhhadoop-46:6667,dswhhadoop-47:6667,dswhhadoop-48:6667,dswhhadoop-49:6667,dswhhadoop-50:6667,dswhhadoop-51:6667,dswhhadoop-52:6667,dswhhadoop-53:6667," +
						"dswhhadoop-54:6667,dswhhadoop-55:6667,dswhhadoop-56:6667,dswhhadoop-57:6667,dswhhadoop-58:6667,dswhhadoop-59:6667,dswhhadoop-60:6667,dswhhadoop-61:6667," +
						"dswhhadoop-62:6667,dswhhadoop-63:6667,dswhhadoop-64:6667,dswhhadoop-9:6667";
	
	final String ZKC =  "dswhhadoop-10:2181,dswhhadoop-1:2181,dswhhadoop-11:2181,dswhhadoop-12:2181,dswhhadoop-13:2181,dswhhadoop-14:2181,dswhhadoop-15:2181,dswhhadoop-16:2181," +
						"dswhhadoop-17:2181,dswhhadoop-18:2181,dswhhadoop-19:2181,dswhhadoop-2:2181,dswhhadoop-20:2181,dswhhadoop-21:2181,dswhhadoop-22:2181,dswhhadoop-23:2181," +
						"dswhhadoop-24:2181,dswhhadoop-25:2181,dswhhadoop-26:2181,dswhhadoop-27:2181,dswhhadoop-28:2181,dswhhadoop-29:2181,dswhhadoop-3:2181,dswhhadoop-30:2181," +
						"dswhhadoop-31:2181,dswhhadoop-32:2181,dswhhadoop-33:2181,dswhhadoop-34:2181,dswhhadoop-35:2181,dswhhadoop-36:2181,dswhhadoop-37:2181,dswhhadoop-38:2181," +
						"dswhhadoop-39:2181,dswhhadoop-4:2181,dswhhadoop-40:2181,dswhhadoop-41:2181,dswhhadoop-42:2181,dswhhadoop-43:2181,dswhhadoop-44:2181,dswhhadoop-45:2181," +
						"dswhhadoop-46:2181,dswhhadoop-47:2181,dswhhadoop-48:2181,dswhhadoop-49:2181,dswhhadoop-50:2181,dswhhadoop-51:2181,dswhhadoop-52:2181,dswhhadoop-53:2181," +
						"dswhhadoop-54:2181,dswhhadoop-55:2181,dswhhadoop-56:2181,dswhhadoop-57:2181,dswhhadoop-58:2181,dswhhadoop-59:2181,dswhhadoop-60:2181,dswhhadoop-61:2181," +
						"dswhhadoop-62:2181,dswhhadoop-63:2181,dswhhadoop-64:2181,dswhhadoop-9:2181";
	
	final String SC = "kafka.serializer.StringEncoder";
	final String PC = "com.bdms.kafka.SimplePartitioner";
	final String RRA = "1";
	static Properties props = new Properties();

	public KafkaConfig() {

		props.put("metadata.broker.list", MBL);
		props.put("serializer.class", SC); // 默认字符串编码消息
		props.put("partitioner.class", PC);
		props.put("request.required.acks", RRA);
		props.put("zk.connect", ZKC);
	}

	public  ProducerConfig getProducerConfig() {
		
		ProducerConfig config = new ProducerConfig(props);
		return config;
	}
}