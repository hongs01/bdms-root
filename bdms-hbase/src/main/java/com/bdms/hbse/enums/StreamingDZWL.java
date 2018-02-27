package com.bdms.hbse.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

/**
  * Description:
  * 		电子围栏的表    对应于 hbase中的 表 streaming_dzwl。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-24下午2:01:41            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public enum StreamingDZWL {
	

	/*
	 * Description: 表名
	 */
	TABLENAME{
			
			public String getName() {
				
				return "streaming_dzwl";
			}
			
			public byte[] getBytes() {
				
				return "streaming_dzwl".getBytes();
			}
			
		},
		
	TABLENAMEHIS{
			
			public String getName() {
				
				return "streaming_dzwl_history";
			}
			
			public byte[] getBytes() {
				
				return "streaming_dzwl_history".getBytes();
			}
			
		},
		
		/*
		 * Description: 列族名
		 */
		CF{
			
			public String getName() {
				
				return "dzwl";
			}
			
			public byte[] getBytes() {
				
				return "dzwl".getBytes();
			}
			
			
		},
		RWM{
			
			public byte[] getBytes(){
				return Bytes.toBytes("rwm");
			}
			
			 public String getName(){
					return "rwm";
				} 
			
		},
		QYM{
			public byte[] getBytes(){
				return Bytes.toBytes("qym");
			}
			
			 public String getName(){
					return "qym";
				} 
			
		},
		SJ{
			public byte[] getBytes(){
			 return Bytes.toBytes("sj");
		    }
			
			 public String getName(){
					
					return "sj";
				} 
			
		},
		YHS{
			
			public byte[] getBytes(){
				 return Bytes.toBytes("yhs");
			    }
			
			 public String getName(){
					
					return "yhs";
				} 
		},
		YHSTB{
			
			public byte[] getBytes(){
				return Bytes.toBytes("yhstb");
			}
			
			 public String getName(){
					return "yhstb";
				} 
		   
		},
		MRS{
			public byte[] getBytes(){
				return Bytes.toBytes("mrs");
			}
			
			 public String getName(){
					return "mrs";
				} 
			
		},
		MRSTB{
			public byte[] getBytes(){
			 return Bytes.toBytes("mrstb");
		    }
			
			 public String getName(){
					
					return "mrstb";
				} 
		},
		MCS{
			
			public byte[] getBytes(){
				 return Bytes.toBytes("mcs");
			    }
			
			 public String getName(){
					
					return "mcs";
				} 
		},
		MCSTB{
			
			public byte[] getBytes(){
				 return Bytes.toBytes("mcstb");
			    }
			
			 public String getName(){
					
					return "mcstb";
				} 
		};
		
		public byte[] getBytes(){ return null; }
		public String getName(){ return null; }
		
		public static String getClassicsColumnValue(){
			
			return "00001";
		}
		
		
		public static List<StreamingDZWL> getAllColumn(){
			
			return Arrays.asList(RWM,QYM,SJ,YHS,YHSTB,MRS,MRSTB,MCS,MCSTB);
		}

}
