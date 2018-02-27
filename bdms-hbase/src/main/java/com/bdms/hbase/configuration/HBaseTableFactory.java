package com.bdms.hbase.configuration;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseTableFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(HBaseTableFactory.class);
	
	private HConnection conn = HbaseConf.getInstance().getHConnection() ; 
	
	
	public HTableInterface createHBaseTable(String tableName) throws IOException{
		
		HTableInterface table = null;
		
		if( StringUtils.isNotBlank(tableName) ){
			
			table = conn.getTable(TableName.valueOf(tableName));
		}else{
			
			LOG.error("tableName must be not null!");
		}
		
		return  table;
		
	}

}
