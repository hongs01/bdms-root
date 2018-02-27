package com.bdms.hbse.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		对应于 hbase中的 表  history_predicte_gj。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24上午9:52:59            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum HistoryPredicteGJ {

	TABLENAME{
		
		public String getName() {
			
			return "history_predicte_gj";
		}
		
		public byte[] getBytes() {
			
			return "history_predicte_gj".getBytes();
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
	
	public static List<HistoryPredicteGJ>  getAllColum(){
		
		return Arrays.asList(STATION_ID,START_TIME,ENTER_TIMES,EXIT_TIMES);
	}
}

