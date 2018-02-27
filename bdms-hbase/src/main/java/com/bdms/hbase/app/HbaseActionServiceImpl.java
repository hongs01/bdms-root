package com.bdms.hbase.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bdms.hbase.configuration.HbaseConf;
import com.bdms.hbse.enums.ImgHTableMeta;



@Service("hbaseActionService")
public class HbaseActionServiceImpl implements HbaseActionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(HbaseActionServiceImpl.class);


	private HConnection conn;
	
	public HbaseActionServiceImpl(){
		conn = HbaseConf.getInstance().getHConnection();
	}

	public List<Map<String,String>> getAlldata(String table,Map<String,List<String>> columns,int num) throws IOException{
		List<Map<String,String>> res = new ArrayList<Map<String,String>>();
		Map<String,String> row=null;
		Scan scan=new Scan();
		scan.setMaxResultSize(num);
		Set<String> keySet =columns.keySet();
		
		scan.setCaching(1000);
		for(String fn:keySet){
			for(String cf:columns.get(fn)){
				scan.addColumn(fn.getBytes(), cf.getBytes());
			}
		}
		
		final ResultScanner scanner=conn.getTable(table).getScanner(scan);
		
		int i=0;
		for(Result result1:scanner){
			if(num>0){
				if(i==num) break;
				i++;
			}
			
			row=new HashMap<String,String>();
			
			for(String fn:keySet){
				for(String cf:columns.get(fn)){
					row.put(cf,new String( result1.getValue(fn.getBytes(), cf.getBytes())));
				}
			}
			res.add(row);
		}
		
		return res;
		
	}
	 
	//单线程查询
	public List<Map<String,String>> execSearch(String table,Map<byte [],NavigableSet<byte []>> cfAndCs,FilterList filterList,List<byte []> csFilter,Map<String,String> prop) throws IOException{
		if(csFilter == null) csFilter = new ArrayList<byte []>();
		 Scan scan = createScan(cfAndCs, filterList,prop);
		 HTableInterface htable = conn.getTable(table);
		return execSearch(cfAndCs, csFilter, scan, htable);
	}
	

	private List<Map<String,String>> execSearch(Map<byte[],NavigableSet<byte[]>> cfAndCs,List<byte[]> csFilter,Scan scan,
			HTableInterface htable) throws IOException {
		
		List<Map<String,String>> lineData=new ArrayList<Map<String,String>>();
		ResultScanner scanner=htable.getScanner(scan);
		
		Map<String,String> data;
		for(Result res:scanner){
			data=new HashMap<String,String>();
			 for(byte[] key:cfAndCs.keySet()){
				
				 for(byte[] cn:cfAndCs.keySet()){
					 if(!csFilter.contains(cn)){
						 data.put(new String(cn), new String(res.getValue(key, cn)));
					 }
				 }
			 }
			 lineData.add(data);
		}
		scanner.close();
		scanner=null;
		return lineData;
		
	}
	 

	//多线程并发
	public List<Map<String, String>> execSearchByMoreThreads(String table,
			Map<byte[], NavigableSet<byte[]>> cfAndCs, FilterList filterList,
			List<byte[]> csFilter, List<Map<String, String>> props)
			throws IOException {
		
		if(csFilter == null) csFilter = new ArrayList<byte []>();
		
		 
		 CountDownLatch  cdl = new CountDownLatch(props.size());
		
		 List< List<Map<String,String>> > ress = new  ArrayList<List<Map<String,String>>>();
		 
		 List<Map<String,String>>  lineData = null;
		 for(Map<String,String> prop : props ){
			 lineData = new ArrayList<Map<String,String>>();
			 ress.add(lineData);
			 new Thread(new HbaseSearchByMoreThread(conn.getTable(table),createScan(cfAndCs, filterList,prop),cfAndCs,csFilter, lineData,cdl)).start();
			 
		 }
		 try {
			cdl.await();
		} catch (InterruptedException e) {
			LOG.error("并发读取数据失败。",e);
		}
		 List<Map<String,String>>  result  = new ArrayList<Map<String,String>>();
		 
		 for( List<Map<String,String>> res : ress ){
			 
			 result.addAll(res);
		 }
		 
		 ress = null;
		 lineData = null;
		 
		return result;
	}
	
	
	public Map<String,List<String>> execSearchByMoreThreadsNeedGroupResult(String table,
						Map<byte [], NavigableSet<byte []>>  cfAndCs,FilterList filterList, 
						NavigableSet<byte []> colsResturn, List<Map<String,String>> props) throws IOException{
		
	  
		 
		 CountDownLatch  cdl = new CountDownLatch(props.size());
		
		 List<Map<String, List<String>> > ress = new  ArrayList<Map<String,List<String>>>();
		 
		 Map<String,List<String>>  lineData = null;
		 
		 for(Map<String,String> prop : props ){
			 
			 lineData = new HashMap<String,List<String>>();
			 
			  for(byte [] col :  colsResturn ){
				  lineData.put(new String(col), new ArrayList<String>());
			  }
			 
			 ress.add(lineData);
			 new Thread(new HbaseSearchByMoreThreadNeedGroupResult(conn.getTable(table),createScan(cfAndCs, filterList,prop),cfAndCs,colsResturn, lineData,cdl)).start();
			 
		 }
		 try {
			cdl.await();
		} catch (InterruptedException e) {
			LOG.error("并发读取数据失败。",e);
		}
		 
		 Map<String, List<String>>  result  = new HashMap<String,List<String>>();
		 for(byte [] col :  colsResturn ){
			 result.put(new String(col), new ArrayList<String>());
		  }
		 String key  = null;
		 for( Map<String,List<String>> res : ress ){
			 
			 for(byte [] col :  colsResturn ){
				key = new String(col);
				result.get(key).addAll(res.get(key));
			  }
			
		 }
		 
		 ress = null;
		 lineData = null;
		 
		return result;
		
	}
	
	
	private Scan createScan(Map<byte [], NavigableSet<byte []>> cfAndCs,
			FilterList filterList, Map<String,String> prop) {
		
		 Scan scan = new Scan();
	     scan.setCaching(1000);
	     scan.setStartRow(Bytes.toBytes(prop.get("startRow")));
	     scan.setStopRow(Bytes.toBytes(prop.get("stopRow")));
	     if(filterList != null )scan.setFilter(filterList);
	     scan.setFamilyMap(cfAndCs);
		return scan;
	}

	/**
	 * @author Administrator
	 * 内部类   实现多线程并行查询
	 *
	 */
	static class HbaseSearchByMoreThread implements Runnable{
		
		
		private CountDownLatch  cdl;
		
		private HTableInterface table;
		private Scan scan;
		private List<byte[]> csFilter;
		private Map<byte[], NavigableSet<byte[]>> cfAndCs;
		
		private List<Map<String,String>>  lineData;
		
		public HbaseSearchByMoreThread( HTableInterface table , Scan scan,Map<byte[], NavigableSet<byte[]>> cfAndCs,List<byte[]> csFilter,List<Map<String,String>>  lineData ,CountDownLatch  cdl ){
			
			this.table = table;
			this.scan = scan;
			this.cfAndCs = cfAndCs;
			this.csFilter = csFilter;
			this.lineData = lineData;
			this.cdl = cdl;
		}

		@Override
		public void run() {
			
			try{
				
				ResultScanner scanner =  table.getScanner(scan);
			     
				Map<String, String> data  ;
				 byte[] value = null;
			     for( Result res : scanner ){
			    	 
			    	 data = new HashMap<String,String>();
			    	 for(byte[] key : cfAndCs.keySet()){
			    		
						 for(byte[] cn : cfAndCs.get(key)){
							 value = res.getValue(key,cn);
							 if(!csFilter.contains(cn)){
								 if(value == null ){
									 data.put(new String(cn), "");
								 }else{
									 data.put(new String(cn), new String(value));
								 }
							 }
						  }
				     }
			    	 lineData.add(data);
			     }
			     
			     scanner.close();
			     scanner = null;
			     table.close();
			     table = null;
			     
			} catch (IOException e) {
				LOG.error("读取Hbase失败", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
	}

	static class HbaseSearchByMoreThreadNeedGroupResult implements Runnable{
		
		
		private CountDownLatch  cdl;
		
		private HTableInterface table;
		private Scan scan;
		private NavigableSet<byte []> colsResturn;
		private Map<byte[], NavigableSet<byte[]>> cfAndCs;
		
		private Map<String,List<String>>  lineData;
		
		public HbaseSearchByMoreThreadNeedGroupResult( HTableInterface table , Scan scan,Map<byte[], NavigableSet<byte[]>> cfAndCs,NavigableSet<byte []> colsResturn,Map<String,List<String>>  lineData ,CountDownLatch  cdl ){
			
			this.table = table;
			this.scan = scan;
			this.cfAndCs = cfAndCs;
			this.colsResturn = colsResturn;
			this.lineData = lineData;
			this.cdl = cdl;
		}

		@Override
		public void run() {
			
			try{
				ResultScanner scanner =  table.getScanner(scan);
			     
				 List<String> data  ;
				 byte[] value = null;
				 String col = null;
			     for( Result res : scanner ){
			    	 
			    	 for(byte[] key : cfAndCs.keySet()){
			    		
						 for(byte[] cn : cfAndCs.get(key)){
							
							 if(colsResturn.contains(cn)){
								 
								 col = new String(cn);
								 data = lineData.get(col);
								 
								 value = res.getValue(key,cn);
								if(value == null){
									data.add("");
								}else{
									data.add( new String(value));
								}
							 }
						  }
				     }
			     }
			     
			     scanner.close();
			     scanner = null;
			     table.close();
			     table = null;
			     
			} catch (IOException e) {
				LOG.error("读取Hbase失败", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.app.HbaseAppService#storeDataToHbase(java.lang.String, org.apache.hadoop.hbase.client.Put)
	 */
	public void storeDataToHbase(String name, Put put) throws IOException {
		
		TableName tn = TableName.valueOf(name);
		
		//判断表是否存在
		if(!conn.isTableAvailable(tn)){
			
			ImgHTableMeta.createSelf(new HBaseAdmin(conn.getConfiguration()));
		}
		
		HTableInterface table = conn.getTable(tn);
		table.put(put);
		
		table.close();
		table = null;
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.app.HbaseActionService#storeDataToHbase(java.lang.String, java.util.List)
	 */
	public void storeDataToHbase(String tableName, List<Put> puts)
			throws IOException {
		
		TableName tn = TableName.valueOf(tableName);
		
		//判断表是否存在
		if(!conn.isTableAvailable(tn)){
			
			ImgHTableMeta.createSelf(new HBaseAdmin(conn.getConfiguration()));
		}
		
		HTableInterface table = conn.getTable(tn);
		
		table.setAutoFlush(false,true);
		table.put(puts);
		table.flushCommits();
		table.close();
		table = null;
		
	}

	/* (non-Javadoc)
	 * @see com.bdms.hbase.app.HbaseActionService#searchResultByGets(java.lang.String, java.util.List, byte[], java.util.List)
	 */
	public List<Map<String,String>> searchResultByGets(String tableName,List<Get> gets) throws IOException {
		
		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		Map<String,String> map =  null;
		
		HTableInterface table = conn.getTable(tableName);
		Result[] results = table.get(gets);
		
		for(Result result : results ){
			
			if( !result.isEmpty() ){
				
				map = new HashMap<String,String>();
				
				for( Cell cell : result.rawCells() ){
					
					map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				
				if( !map.isEmpty() ){
					
					maps.add(map);
				}
			}
			
		}
		
		table.close();
		table = null;
		
		return maps;
		
	}

	public Map<String, String> searchResultByGet(String tableName, Get get) throws IOException {
		
		Map<String,String> map = new HashMap<String,String>();
		
		get.setMaxVersions(1);
		HTableInterface table = conn.getTable(tableName);
		Result result = table.get(get);
		
		
		for( Cell cell : result.rawCells() ){
			
			map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
	                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
		}
			
		table.close();
		table = null;
		
		return map;
	}

	public String getLatestDateStr(String tableName, byte[] cf,
			String timeColumn) throws IOException {
	
		
		 Scan scan=new Scan();
	     scan.setReversed(true);
	     scan.setCaching(1);
	     scan.setMaxResultSize(1);
	     
	     byte[] bytes = Bytes.toBytes(timeColumn);
		 scan.addColumn(cf, bytes);
		
	     
	     ResultScanner scanner = conn.getTable(tableName).getScanner(scan);
	     
	     Result next = scanner.next();
	     
	     byte[] value = next.getValue(cf, bytes);
	     
	     scanner.close();
	     scanner = null;
	     
	     if( value == null){
	    	 
	    	 return null;
	     }
	     
	     
		return  new String(value);
	}


	
	public String getFirstResult(String tableName, Scan scan, byte[] cf,
			String columnsInclude) throws IOException {
		
		 HTableInterface table = conn.getTable(tableName);
		 ResultScanner scanner = table.getScanner(scan);
		 
		 Result next = scanner.next();
		 
		if(next == null ){
			
			return null;
		}
		 
		  byte[] value = next.getValue(cf, Bytes.toBytes(columnsInclude) );
		     
		 table.close();
	     scanner.close();
	     scanner = null;
	     table = null;
		     
	     if( value == null){
	    	 
	    	 return null;
	     }
		     
		return  new String(value);
		
		 
	}
	
	
	
	
}
