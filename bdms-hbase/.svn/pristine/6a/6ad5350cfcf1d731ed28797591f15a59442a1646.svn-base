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
public enum ImgHTableMeta {
	
	
	TABLENAME{
		
		public String getName() {
			
			return "jk_img_meta";
		}
		
		public byte[] getBytes() {
			
			return "jk_img_meta".getBytes();
		}
		
		
	},
	
	CF{
		
		public String getName() {
			
			return "metadata";
		}
		
		public byte[] getBytes() {
			
			return "metadata".getBytes();
		}
		
		
	},
	TIMESTAMP{
		
		public String getName() {
			
			return "timeStamp";
		}
		
		public byte[] getBytes() {
			
			return "timeStamp".getBytes();
		}
		
		
	},
	CAMERAID{
		
		public String getName() {
			
			return "cameraId";
		}
		
		public byte[] getBytes() {
			
			return "cameraId".getBytes();
		}
		
		
	},
	PEOPLENUM{
		
		public String getName() {
			
			return "peopleNum";
		}
		
		public byte[] getBytes() {
			
			return "peopleNum".getBytes();
		}
		
		
	},
	DENSITYLEVEL{
		
		public String getName() {
			
			return "densityLevel";
		}
		
		public byte[] getBytes() {
			
			return "densityLevel".getBytes();
		}
		
		
	},
	DENSITYIMAGE{
		
		public String getName() {
			
			return "densityImage";
		}
		
		public byte[] getBytes() {
			
			return "densityImage".getBytes();
		}
		
		
	},
	GROUPNUM{
		
		public String getName() {
			
			return "groupNum";
		}
		
		public byte[] getBytes() {
			
			return "groupNum".getBytes();
		}
		
		
	},
	GROUPIMAGE{
		
		public String getName() {
			
			return "groupImage";
		}
		
		public byte[] getBytes() {
			
			return "groupImage".getBytes();
		}
		
		
	},
	WARNLEVEL{
		
		public String getName() {
			
			return "warnLevel";
		}
		
		public byte[] getBytes() {
			
			return "warnLevel".getBytes();
		}
		
		
	},WARNIMAGE{
		
		public String getName() {
			
			return "wrnImage";
		}
		
		public byte[] getBytes() {
			
			return "wrnImage".getBytes();
		}
		
	},RESERVED{
		
		public String getName() {
			
			return "reserved";
		}
		
		public byte[] getBytes() {
			
			return "reserved".getBytes();
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
	
	public static Put createImgPut(String timeStamp, String cameraId,
			String peopleNum, String densityLevel, String densityImage,
			String groupNum, String groupImage, String warnLevel,
			String wrnImage,String reserved) {
		
		byte[] cf = CF.getBytes();
		Put row = new Put(Bytes.toBytes(cameraId  + "_" + timeStamp));
		row.add(cf, TIMESTAMP.getBytes(), Bytes.toBytes(timeStamp));
		row.add(cf, CAMERAID.getBytes(), Bytes.toBytes(cameraId));
		row.add(cf, PEOPLENUM.getBytes(), Bytes.toBytes(peopleNum));
		row.add(cf, DENSITYLEVEL.getBytes(), Bytes.toBytes(densityLevel));
		row.add(cf, DENSITYIMAGE.getBytes(), Bytes.toBytes(densityImage));
		row.add(cf, GROUPNUM.getBytes(), Bytes.toBytes(groupNum));
		row.add(cf, GROUPIMAGE.getBytes(), Bytes.toBytes(groupImage));
		row.add(cf, WARNLEVEL.getBytes(), Bytes.toBytes(warnLevel));
		row.add(cf, WARNIMAGE.getBytes(), Bytes.toBytes(wrnImage));
		//row.add(cf, RESERVED.getBytes(), Bytes.toBytes(reserved));
		
		return row;
	}
	
	public static List<ImgHTableMeta> getAllColumn(){
		
		
		return Arrays.asList(TIMESTAMP,CAMERAID,PEOPLENUM,DENSITYLEVEL,DENSITYIMAGE,
							GROUPNUM,GROUPIMAGE,WARNLEVEL,WARNIMAGE);
		
	}
	
	public static String getClassicsColumnValue(){
		
		 return "00000000";
	}

}
