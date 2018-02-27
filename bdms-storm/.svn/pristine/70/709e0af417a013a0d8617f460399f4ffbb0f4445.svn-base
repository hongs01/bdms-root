package com.ds.bdms.storm;

import java.util.Arrays;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy.Units;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;


public class SKHTopogy {
	
	
	
	public static void main(String[] args){
		
		String id = "storm_kafka_hdfs";
		
		KafkaSpout kafkaSpout = new KafkaSpout(getSpoutConfig(id));
		HdfsBolt hdfsBolt = getHdfsBolt("/tmp/storms/");
		
		TopologyBuilder topology = new TopologyBuilder();
		topology.setSpout("kafka-reader", kafkaSpout,50);
		topology.setBolt("token", new MessageTotenizer(),55).shuffleGrouping("kafka-reader");
		//topology.setBolt("compare", new GISCompare(),25).fieldsGrouping("token", new Fields("GIS"));
		topology.setBolt("store-hdfs", hdfsBolt,60).shuffleGrouping("token");
		
		Config conf = new Config();
		conf.put(Config.NIMBUS_HOST,"dswhhadoop-2");
		//conf.put(Config.NIMBUS_THRIFT_PORT, "6627");
		conf.setNumWorkers(24);
		
		try {
			StormSubmitter.submitTopology("mytest", conf, topology.createTopology());
			System.out.println("  mytest is ok. ");
		} catch (AlreadyAliveException e) {
			System.err.println( "mytest err1" );
			e.printStackTrace();
		} catch (InvalidTopologyException e) {
			System.err.println( "mytest err2" );
			e.printStackTrace();
		}
		
	}
	
	public static SpoutConfig getSpoutConfig( String  id ){
		
		String topic = "test670";
		
		String zkHosts = "dswhhadoop-4:2181,dswhhadoop-3:2181,dswhhadoop-2:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181,dswhhadoop-1:2181";
		BrokerHosts brokerHosts = new ZkHosts(zkHosts);
		
		SpoutConfig spoutConfig = new SpoutConfig(brokerHosts,topic,"/storm",id);
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConfig.forceFromStart  = false;
		spoutConfig.zkServers = Arrays.asList(new String[]{"dswhHadoop-1","dswhHadoop-2","dswhHadoop-3","dswhHadoop-4","dswhHadoop-5","dswhHadoop-6","dswhHadoop-7","dswhHadoop-8",});
		spoutConfig.zkPort = 2181;
		
		return spoutConfig;
	}

	public static HdfsBolt getHdfsBolt(String path){
		
		DelimitedRecordFormat format = new DelimitedRecordFormat();
		format.withFieldDelimiter("\t");
		SyncPolicy syncPolicy = new CountSyncPolicy(1000); // sync the filesystem after every 1k tuples
		FileRotationPolicy rotationPolicy = new FileSizeRotationPolicy(50.0f, Units.MB);
		FileNameFormat fileNameFormat = new DefaultFileNameFormat().withPath(path).withPrefix("app_").withExtension(".log"); // set file name format
		
		HdfsBolt hdfsBolt = new HdfsBolt();
		hdfsBolt.withFsUrl("hdfs://dswhhadoop-2:8020");
		hdfsBolt.withFileNameFormat(fileNameFormat);
		hdfsBolt.withRecordFormat(format);
		hdfsBolt.withRotationPolicy(rotationPolicy);
		hdfsBolt.withSyncPolicy(syncPolicy);
		
		return hdfsBolt;
	}
}
