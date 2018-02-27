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

public enum Wifi2Meta
{
	TABLENAME
	{
		public String getName()
		{
			return "wifi2";
		}
		public byte[] getBytes()
		{
			return "wifi2".getBytes();
		}
	},
	
	CF
	{
		public String getName()
		{
			return "info";
		}
		
		public byte[] getBytes()
		{
			return "info".getBytes();
		}
	},
	
	SID
	{
		public String getName()
		{
			return "sid";
		}
		
		public byte[] getBytes()
		{
			return "sid".getBytes();
		}
	},
	
	TIME
	{
		public String getName()
		{
			return "time";
		}
		
		public byte[] getBytes()
		{
			return "time".getBytes();
		}
		
	},
	
	MACOUNT
	{
		public String getName()
		{
			return "count";
		}
		
		public byte[] getBytes()
		{
			return "count".getBytes();
		}
		
	},
	
	NUM
	{
		public String getName()
		{
			return "num";
		}
		
		public byte[] getBytes()
		{
			return "num".getBytes();
		}
	};
	
	public byte[] getBytes(){ return null; }
	public String getName()
	{ 
		return null;
	}
	
	public static void createSelf(HBaseAdmin admin) throws IOException
	{
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(TABLENAME.getName()));
		HColumnDescriptor hcd = new HColumnDescriptor(CF.getName());
		hcd.setMaxVersions(1);
		hcd.setBloomFilterType(BloomType.ROWCOL);
		htd.addFamily(hcd);
		admin.createTable(htd);
	}
	
	public static Put createWifiPut(String time, String count,
			String apname,String num)
	{
		
		byte[] cf = CF.getBytes();
		Put row = new Put(Bytes.toBytes(time  + "-" + apname));
		row.add(cf, SID.getBytes(), Bytes.toBytes(apname));
		row.add(cf, TIME.getBytes(), Bytes.toBytes(time));
		row.add(cf, MACOUNT.getBytes(), Bytes.toBytes(count));
		row.add(cf,NUM.getBytes(),Bytes.toBytes(num));
		return row;
	}
	
	public List<Wifi2Meta> getAllColumns()
	{
		return Arrays.asList(SID,TIME,MACOUNT,NUM);
	}
	
	public static String getClassicsColumnValue()
	{
		
		 return "00000000";
	}
	
}


