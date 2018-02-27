package com.bdms.hbse.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		轨交   对应于 hbase中的 表 test_streaming_gj。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24下午2:02:10            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum StreamingGJ {

  TABLENAME{
		
		public String getName() {
			
			return "streaming_gj";
		}
		
		public byte[] getBytes() {
			
			return "streaming_gj".getBytes();
		}
		
	},
	
	TABLENAMEHIS{
		
		public String getName() {
			
			return "streaming_gj_history";
		}
		
		public byte[] getBytes() {
			
			return "streaming_gj_history".getBytes();
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
	START_TIME{
		
		public byte[] getBytes(){
			return Bytes.toBytes("START_TIME");
		}
		
		 public String getName(){
				return "START_TIME";
			} 
		
	},
	ENTER_TIMES{
		public byte[] getBytes(){
			return Bytes.toBytes("ENTER_TIMES");
		}
		
		 public String getName(){
				return "ENTER_TIMES";
			} 
		
	},
	EXIT_TIMES{
		public byte[] getBytes(){
		 return Bytes.toBytes("EXIT_TIMES");
	    }
		
		 public String getName(){
				
				return "EXIT_TIMES";
			} 
	},
	STATION_ID{
		
		public byte[] getBytes(){
			 return Bytes.toBytes("STATION_ID");
		    }
		
		 public String getName(){
				
				return "STATION_ID";
			} 
	};
	
	public byte[] getBytes(){ return null; }
	public String getName(){ return null; }
	
	public static String getClassicsColumnValue(){
		
		return "0099";
	}
	
	 
	public static List<StreamingGJ>  getAllColum(){
		
		return Arrays.asList(STATION_ID,START_TIME,ENTER_TIMES,EXIT_TIMES);
	}
	
}
