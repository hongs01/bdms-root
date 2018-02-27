package com.bdms.hbse.enums;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		od数据的表   对应于 hbase中的 表  hot_od_day_gj
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-11-25上午9:52:16            1.0            Created by chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum OneDayHotODGJ {

	  TABLENAME{
			
			public String getName() {
				
				return "hot_od_day_gj";
			}
			
			public byte[] getBytes() {
				
				return "hot_od_day_gj".getBytes();
			}
			
		},
		
		CF{
			
			public String getName() {
				
				return "luxnew";
			}
			
			public byte[] getBytes() {
				
				return "luxnew".getBytes();
			}
			
			
		},
		PASS_NUM{
			
			public byte[] getBytes(){
				return Bytes.toBytes("PASS_NUM");
			}
			
			 public String getName(){
					return "PASS_NUM";
				} 
			
		},
		OD{
			public byte[] getBytes(){
				return Bytes.toBytes("OD");
			}
			
			 public String getName(){
					return "OD";
				} 
			
		};
		
		public byte[] getBytes(){ return null; }
		public String getName(){ return null; }
		
		public static void createSelf(HBaseAdmin admin) throws IOException
		{
			HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(TABLENAME.getName()));
			HColumnDescriptor hcd = new HColumnDescriptor(CF.getName());
			hcd.setMaxVersions(1);
			hcd.setBloomFilterType(BloomType.ROWCOL);
			htd.addFamily(hcd);
			admin.createTable(htd);
		}
		
		public static List<OneDayHotODGJ> getAllColumn(){
			
			
			return Arrays.asList(PASS_NUM,OD);
		}
}
