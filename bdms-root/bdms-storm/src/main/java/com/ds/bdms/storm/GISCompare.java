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

public class GISCompare extends BaseRichBolt{
	
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = LogFactory.getLog(GISCompare.class);
	
	private OutputCollector collector;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		this.collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		
		String gis = input.getString(0);
		collector.emit(new Values(gis,"oooooo"));
		collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line", "len"));
	}

	@Override
	public void cleanup() {
		
	}

	
	
	
}
