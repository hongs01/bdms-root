package com.bdms.hbase;

 
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;

public class HBaseApp {
	//创建表、插入记录、查询一条记录、遍历所有的记录、删除表
		public static final String TABLE_NAME="test909";
		public static final String FAMILY_NAME="abstruct";
		public static final String FAMILY_NAME1="position";
		public static final String ROW_KEY="";
		
		public static void main(String[] args) throws Exception {
			
			Configuration conf =HBaseConfiguration.create();
			//定位在hdfs中的位置
			
			conf.set("hbase.zookeeper.quorum", "dswhhadoop-4,dswhhadoop-3,dswhhadoop-2,dswhhadoop-5,dswhhadoop-6,dswhhadoop-7,dswhhadoop-8,dswhhadoop-1");
			//zookeeper定位
			conf.set("hbase.zookeeper.property.clientPort", "2181"); 
			conf.set("hbase.rootdir","hdfs://dswhhadoop-2:8020/apps/hbase/data");
			//查找在hdfs中的路径
			
			//创建表，删除表使用HBaseAdmin
			 
			HBaseAdmin hBaseAdmin= new HBaseAdmin(conf);
			 
			//建表操作
		//	createTable(hBaseAdmin);
 
			
			
			
			//删除表操作
			//deleteTable(hBaseAdmin);

			
			
			
			//插入记录，查询一条记录，遍历所有的记录用HTable
			//插入记录操作
			final HTable hTable=new HTable(conf,TABLE_NAME);
		//	insertTable(hTable);
			
		
			
			
			//查询操作
		//	findTable(hTable);
			
			
			
			//浏览操作
			scanTable(hTable);
			
			
			hTable.close();
			
			 
		}

		private static void insertTable(final HTable hTable)
				throws InterruptedIOException,
				RetriesExhaustedWithDetailsException {
			Put put=new Put(ROW_KEY.getBytes());
			put.add(FAMILY_NAME.getBytes(),"age".getBytes(),"25".getBytes());
			put.add(FAMILY_NAME.getBytes(),"sex".getBytes(),"gilr".getBytes());
			put.add(FAMILY_NAME1.getBytes(),"x".getBytes(),"1".getBytes());
			put.add(FAMILY_NAME1.getBytes(),"y".getBytes(),"2".getBytes());
			hTable.put(put);
		}

		private static void findTable(final HTable hTable) throws IOException {
			Get get=new Get(ROW_KEY.getBytes());
			hTable.get(get);
			final Result result=hTable.get(get);
			final byte[] value=result.getValue(FAMILY_NAME.getBytes(), "age".getBytes());
			System.out.println(result+"\t"+new String(value));
		}

		private static  List getAlldata(final HTable hTable) throws IOException {
			List list=new ArrayList();
			Scan scan=new Scan();
			final ResultScanner scanner=hTable.getScanner(scan);
			int i=0;
			for(Result result1:scanner){
				if(i==10000000) break;
				i++;
				final byte[] value1=result1.getValue(FAMILY_NAME.getBytes(), "address".getBytes());
				final byte[] value2=result1.getValue(FAMILY_NAME.getBytes(), "name".getBytes());
				final byte[] value3=result1.getValue(FAMILY_NAME.getBytes(), "phoneNoPer".getBytes());
				final byte[] value4=result1.getValue(FAMILY_NAME.getBytes(), "time".getBytes());
				final byte[] value5=result1.getValue(FAMILY_NAME1.getBytes(), "gis_x".getBytes());
				final byte[] value6=result1.getValue(FAMILY_NAME1.getBytes(), "gis_y".getBytes());
				final byte[] value7=result1.getValue(FAMILY_NAME1.getBytes(), "simId".getBytes());
				String s=new String(value1)+"\t"+new String(value2)+"\t"+new String(value3)+"\t"+new String(value4)+"\t"+new String(value5)+"\t"+new String(value6)+"\t"+new String(value7);
				//System.out.println(new String(value1)+"\t"+new String(value2)+"\t"+new String(value3)+"\t"+new String(value4)+"\t"+new String(value5)+"\t"+new String(value6)+"\t"+new String(value7));	
			    //System.out.println(s);
			    list.add(s);
			   // System.out.println("添加");
			}
			return list;
			
			
		}
		
		
		public static void scanTable(final HTable hTable) throws IOException {
			List list = new ArrayList();
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s1=time.format(new Date());
			//System.out.println("开始存入时间："+time.format(new Date()));
			list=getAlldata(hTable);
			String s2=time.format(new Date());
			//System.out.println("存入完成时间/开始取出时间："+time.format(new Date()));
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
			String s3=time.format(new Date());
			System.out.println("开始存入时间："+s1);
			System.out.println("存入完成时间/开始取出时间："+s2);
			System.out.println("取出完成时间："+s3);
			 
		}

		private static void createTable(HBaseAdmin hBaseAdmin)
				throws IOException {
			if(!hBaseAdmin.tableExists(TABLE_NAME)){
				 
				//HTableDescriptor tableDescriptor=new HTableDescriptor(TableName.valueOf(TABLE_NAME));
				HTableDescriptor tableDescriptor=new HTableDescriptor(TABLE_NAME);
				HColumnDescriptor family=new HColumnDescriptor(FAMILY_NAME);
				HColumnDescriptor family1=new HColumnDescriptor(FAMILY_NAME1);
				tableDescriptor.addFamily(family);//添加列族
				tableDescriptor.addFamily(family1);
				hBaseAdmin.createTable(tableDescriptor);}
			System.out.println("表创建成功");
		}

		private static void deleteTable(HBaseAdmin hBaseAdmin)
				throws IOException {
			hBaseAdmin.disableTable(TABLE_NAME);
			hBaseAdmin.deleteTable(TABLE_NAME);
		}
}
