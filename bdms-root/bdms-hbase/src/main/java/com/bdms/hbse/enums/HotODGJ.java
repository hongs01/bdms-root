package com.bdms.hbse.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		od数据的表   对应于 hbase中的 表  hot_od_gj
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24上午9:52:16            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum HotODGJ {

	  TABLENAME{
			
			public String getName() {
				
				return "hot_od_gj";
			}
			
			public byte[] getBytes() {
				
				return "hot_od_gj".getBytes();
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
		
		public static List<HotODGJ> getAllColumn(){
			
			
			return Arrays.asList(PASS_NUM,OD);
		}
}
