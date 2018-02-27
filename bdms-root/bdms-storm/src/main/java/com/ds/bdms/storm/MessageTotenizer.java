package com.ds.bdms.storm;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class MessageTotenizer extends BaseRichBolt {

	private static final long serialVersionUID = 5903493419073396180L;
	
	private static final Log LOG = LogFactory.getLog(MessageTotenizer.class);

	private OutputCollector collector;
	private StringBuffer  res;
	private String[] split;
	

	public void execute(Tuple input) {
		
		
		split = input.getString(0).split( " " );
		res = new StringBuffer();
		
		if(split.length > 2 ){
			
			res.append("simId:");
			res.append(split[0]);
			res.append("#");
			res.append("GIS_X:");
			res.append(split[1]);
			res.append("#");
			res.append("GIS_Y:");
			res.append(split[2]);
			
			collector.emit(new Values(res.toString()));
		}
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("GIS"));
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		this.collector = collector;
		
	}

	

}
