package com.bdms.spark.hbase;

import java.io.InterruptedIOException;
import java.io.Serializable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.util.Bytes;

import scala.annotation.bridge;


public class HBaseServer implements Serializable {
	
	private static final long serialVersionUID = -2189979656135497707L;

	private static HBaseServer hbs;
	
	private HTable table;
	private HBaseAdmin admin;
	private Configuration hConf;
	private Put put;
	
	private HBaseServer(){
		
		try {
			table = getHTable();
		} catch (Exception e) {
			System.out.println("........................");
		}
	}
	
	
	public static  HBaseServer  getHBaseServer(){
		
		if( hbs == null ){
			synchronized (HBaseServer.class) {
				if(hbs == null){
					hbs = new HBaseServer();
				}
			}
		}
		return hbs;
	}
	
	
	public HTable getHTable() throws Exception{
		
		if(table == null ){
			
			if(admin == null){
				
				getHBaseAdmin();
			}
			
			if(!admin.tableExists("person")){
			  HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("person"));
			  htd.addFamily(new HColumnDescriptor("position"));
			  htd.addFamily(new HColumnDescriptor("abstruct"));
			  admin.createTable(htd);
				
			 if(!admin.tableExists("person")){
				 return null;
				}
			}
			table =  new HTable(hConf,"person");
		}
		
	  return table;
		
	}
	
	public HBaseAdmin getHBaseAdmin() throws Exception{
		
		if(admin == null){
			admin = new HBaseAdmin(getConfiguration());
		}
		
		return admin;
	}
	
	public Configuration getConfiguration(){
		
		if( hConf == null ){
			hConf = HBaseConfiguration.create();
		    hConf.set("hbase.zookeeper.quorum", "dswhhadoop-4,dswhhadoop-3,dswhhadoop-2,dswhhadoop-5,dswhhadoop-6,dswhhadoop-7,dswhhadoop-8,dswhhadoop-1");
			hConf.set("hbase.zookeeper.property.clientPort", "2181");  
			hConf.set("hbase.rootdir","hdfs://dswhhadoop-2:8020/apps/hbase/data");
		}
		return hConf;
	}
	
	public static void main(String[] args) {
	
		
		System.out.println( 1.35/100 );
		System.out.println( 1.35/1000 );
		double a = 0.0;
		int n = 0 ;
		while(true){
			n++;
			a = Math.random();
			if( a < 0.01 ){
				System.out.println(n + "--" + a);
				break;
			}
		
		}
	}
	

}
