package com.bdms.spark.dzwl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		临时历史预测数据处理程序。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-23上午10:39:50            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class HistoryDataStore { 
	
	private static final String  HBASESITE = "hbase/hbase-site.xml";
	private static final String  HBASEHDFS = "hbase/hdfs-site.xml" ;
	
	
	
	public static void main(String[] args) throws Exception {
		
		Configuration hConf =HBaseConfiguration.create();
		hConf.addResource(HBASESITE);
		hConf.addResource(HBASEHDFS);
	    HConnection conn = HConnectionManager.createConnection(hConf);
	  
	   TableName name = TableName.valueOf("history_predicte_dzwl");
	    
	   //如果hbase中不存在该表  则创建
	    if(!conn.isTableAvailable(name)){
	   	 
	   	 HBaseAdmin admin = new HBaseAdmin(hConf);
	   	 HTableDescriptor htd = new HTableDescriptor(name);
			 
	   	 HColumnDescriptor hcd = new HColumnDescriptor("dzwl");
			 hcd.setMaxVersions(1);
			 hcd.setBloomFilterType(BloomType.ROWCOL);
			// hcd.setInMemory(true);
			// hcd.setTimeToLive(60*60*30)
			 htd.addFamily( hcd);
			 admin.createTable(htd);
			 admin.close();
			 
	   	 
	    }
	    
	    byte[] cf = Bytes.toBytes("dzwl");
	    
	    byte[] rwm = Bytes.toBytes("rwm");
	    byte[] qym = Bytes.toBytes("qym");
	    byte[] sj = Bytes.toBytes("sj");
	    
	    byte[] yhs = Bytes.toBytes("yhs");
	    byte[] yhstb = Bytes.toBytes("yhstb");
	    
	    byte[] mrs = Bytes.toBytes("mrs");
	    byte[] mrstb = Bytes.toBytes("mrstb");
	    
	    byte[] mcs = Bytes.toBytes("mcs");
	    byte[] mcstb = Bytes.toBytes("mcstb");
	    
	     
	    HTableInterface table = conn.getTable(name);
	    
	    List<Put> puts = new ArrayList<Put>(); 
	    
	    //历史数据的地址
	    File cvsDir = new File("/tmp/cvs/");
	    
	    InputStreamReader reader = null;
	    BufferedReader bufferedReader = null;
	    String str = null;
	    String[] vals = null;
	    
	    Put put = null;
	    String time = null;
	    
	    //读取历史数据  并存储
	    for( File file : cvsDir.listFiles()){
	   	 
	        reader = new InputStreamReader(new FileInputStream(file),"GBK");
	    	bufferedReader = new BufferedReader(reader);
	    	
	    	
	    	while( (str = bufferedReader.readLine()) != null){
	    		
	    		vals = str.split(",");
	    		if(vals.length == 9 && vals[2].length() > 6){
	    			
	    			if( "顾村".equals(vals[1])){
	    				vals[1] = "00001";
	    			}else if( "外滩".equals(vals[1])){
	    				vals[1] = "00002";
	    			}else{
	    				vals[1] = "00003";
	    			}
	    			
	    			
	    			System.err.println( str );
		    		
		    		 time = vals[2].split(" ")[1];
		    		
		    		 put = new Put(Bytes.toBytes(vals[1] + "-" + time));
				     
		    		 put.add(cf, rwm, Bytes.toBytes(vals[0]));
		    		 put.add(cf, qym, Bytes.toBytes(vals[1]));
		    		 put.add(cf, sj, Bytes.toBytes(time));
				     
		    		 put.add(cf, yhs, Bytes.toBytes(vals[3]));
		    		 put.add(cf, yhstb, Bytes.toBytes(vals[4]));
				     
		    		 put.add(cf, mrs, Bytes.toBytes(vals[5]));
		    		 put.add(cf, mrstb, Bytes.toBytes(vals[6]));
				     
		    		 put.add(cf, mcs, Bytes.toBytes(vals[7]));
		    		 put.add(cf, mcstb, Bytes.toBytes(vals[8]));
		    		
		    		 puts.add(put);
	    			
	    		}
	    		
	    	}
	   	 
	    }
	    
	   table.put(puts);
	    
	   table.close();
	}

}
