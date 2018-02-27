package com.bdms.hbse.enums;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		视频数据相关的表。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-27上午10:01:57            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum WifiTableMeta {
	
	
	TABLENAME{
		
		public String getName() {
			
			return "wifi";
		}
		
		public byte[] getBytes() {
			
			return "wifi".getBytes();
		}
		
		
	},
	
	CF{
		
		public String getName() {
			
			return "info";
		}
		
		public byte[] getBytes() {
			
			return "info".getBytes();
		}
		
		
	},
	TIME{
		
		public String getName() {
			
			return "time";
		}
		
		public byte[] getBytes() {
			
			return "time".getBytes();
		}
		
		
	},
	COUNT{
		
		public String getName() {
			
			return "count";
		}
		
		public byte[] getBytes() {
			
			return "count".getBytes();
		}
		
		
	},
	SID{
		
		public String getName() {
			
			return "sid";
		}
		
		public byte[] getBytes() {
			
			return "sid".getBytes();
		}
		
		
	};
	
	public byte[] getBytes(){ return null; }
	public String getName(){ return null; }
	
	
	public static void createSelf(HBaseAdmin admin) throws IOException{
		
		 HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(TABLENAME.getName()));
		 //htd.setValue(HTableDescriptor.SPLIT_POLICY, classOf[KeyPrefixRegionSplitPolicy].getName())
		// htd.setValue("KeyPrefixRegionSplitPolicy.prefix_length", "2");
		 
		 HColumnDescriptor hcd = new HColumnDescriptor(CF.getName());
		 hcd.setMaxVersions(1);
		 hcd.setBloomFilterType(BloomType.ROWCOL);
		 htd.addFamily( hcd);
		 admin.createTable(htd);
		
	}
	
	public static Put createWifiPut(String time, String count,
			String sid) {
		
		byte[] cf = CF.getBytes();
		Put row = new Put(Bytes.toBytes(sid  + "-" + time));
		row.add(cf, TIME.getBytes(), Bytes.toBytes(time));
		row.add(cf, COUNT.getBytes(), Bytes.toBytes(count));
		row.add(cf, SID.getBytes(), Bytes.toBytes(sid));
		return row;
	}
	
	public static List<WifiTableMeta> getAllColumn(){
		
		
		return Arrays.asList(TIME,COUNT,SID);
		
	}
	
	public static String getClassicsColumnValue(){
		
		 return "00000000";
	}

}
