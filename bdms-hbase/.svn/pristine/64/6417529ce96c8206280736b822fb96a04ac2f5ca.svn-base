package com.bdms.hbase.search;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.hbase.configuration.HBaseTableFactory;
import com.bdms.hbase.extractor.RowExtractor;

/**
  * Description:
  * 		Hbase查询服务 HBaseSearchService的实现类。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-9-25上午9:16:14            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class HBaseSearchServiceImpl implements HBaseSearchService {

	private static final Logger LOG = LoggerFactory.getLogger(HBaseSearchServiceImpl.class);
	
	private HBaseTableFactory factory = new HBaseTableFactory();
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, org.apache.hadoop.hbase.client.Scan, com.bdms.hbase.extractor.RowExtractor)
	 */
	public <T> List<T> search(String tableName, Scan scan,
			RowExtractor<T> extractor) throws IOException {
		
		List<T> data = new ArrayList<T>();
		
		HTableInterface table = factory.createHBaseTable(tableName);
		
		if(table != null ){
			
			ResultScanner scanner = table.getScanner(scan);
			int n = 0;
			T row = null;
		    for( Result result : scanner){
		    	row = extractor.extractRowData(result, n);
		    	data.add(row);
		    	n++;
		    }
		    
		    close( table, scanner);
		}
		
		return data;
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, org.apache.hadoop.hbase.client.Scan, java.lang.Class)
	 */
	public <T> List<T> search(String tableName, Scan scan, Class<T> cls) throws IOException, InstantiationException, IllegalAccessException {
		
		List<T> data = new ArrayList<T>();
		
		HTableInterface table = factory.createHBaseTable(tableName);
		
		if(table != null ){
			
			Field[] fields = cls.getDeclaredFields();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			for( Field field : fields ){
				
				if( field.getModifiers() == 2 ){
					
					fieldMap.put(field.getName().toLowerCase(), field);
				}
			}
			fields = null;
			
			T obj = null;
			
			ResultScanner scanner = table.getScanner(scan);
			String column = null;
			
			Field field = null;
			
		    for( Result result : scanner){
		    	
		    	 if( !result.isEmpty() ){
		    		 
		    		 obj = cls.newInstance();
		    		 
			    	 for( Cell cell : result.listCells() ){
			    		
			    		 column = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
			    		 /*检查该列是否在实体类中存在对应的属性,若存在则 为其赋值*/
			    		 if( (field = fieldMap.get(column.toLowerCase())) != null ){
			    			 
			    			 field.setAccessible(true);
							 field.set(obj, Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			    		 }
			    		 
			    	 }
			    	 
			    	 data.add(obj);
		         }
					
		    }
		    
		    close( table, scanner);
		}
		
		return data;
		
	}

	
	public static void  close( HTableInterface table, ResultScanner scanner ){
		
		    try {
		    	if( table != null ){
		    		table.close();
		    		table = null;
		    	}
		    	if( scanner != null ){
			    	scanner.close();
			    	scanner = null;
		    	}
			} catch (IOException e) {
				LOG.error("关闭 HBase的表  " + table.getName().toString() + " 失败。", e );
			}
		
	}


	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#searchMore(java.lang.String, java.util.List, com.bdms.hbase.extractor.RowExtractor)
	 */
	public <T> List<T> searchMore(String tableName, List<Scan> scans,
			RowExtractor<T> extractor) throws IOException {
		
		List<T> data = new ArrayList<T>();
		
		if( scans != null && !scans.isEmpty()  ){
			
		    int size = scans.size();
		
		    if( size == 1 ){
		    	
		    	data = search(tableName , scans.get(0),extractor);
		    	
		    }else{
		    	/* 有多个 scan时  采用多线程查询  */
		    	CountDownLatch  cdl = new CountDownLatch(size);
		    	
		    	List<List<T>> res_total = new ArrayList<List<T>>(size);
		    	List<T> res = null;
		    	for( Scan scan : scans ){
		    		
		    		res = new ArrayList<T>();
		    		res_total.add(res);
		    		
		    		 new Thread(new MoreRowExtractor<T>( this,tableName,scan,extractor,res,cdl )).start();
		    	}
		    	
		    	try {
					cdl.await();
				} catch (InterruptedException e) {
					LOG.error("等待查询HBase表" + tableName + "返回结果时发生异常。 ", e);
				}
		    	
		    	/* 结果汇总 */
		    	for( List<T> tmp : res_total ){
		    		
		    		data.addAll(tmp);
		    	}
		    	res_total = null;
		    	
		    }
		
		}else{
			LOG.error(" scans must not null or empty !");
		}
		return data;
	}
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#searchMore(java.util.List)
	 */
	public <T> Map<String,List<T>> searchMore(List<SearchMoreTable<T>> more) throws IOException {
		
		Map<String,List<T>> data = new HashMap<String,List<T>>();
		
		if( more != null && !more.isEmpty()  ){
			
			int size = more.size();
				
			CountDownLatch  cdl = new CountDownLatch(size);
			
			List<T> res = null;
			
			String tableName = null;
			for( SearchMoreTable<T> t : more ){
				
				tableName = t.getTableName();
				res = new ArrayList<T>();
				data.put(tableName, res);
				new Thread(new MoreRowExtractor<T>( this,tableName,t.getScan(),t.getExtractor(),res,cdl )).start();
			}
			
			try {
				cdl.await();
			} catch (InterruptedException e) {
				LOG.error("等待查询HBase表返回结果时发生异常。 ", e);
			}
			
		}else{
			LOG.error(" more must not null or empty !");
		}
		return data;
	}


	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#searchMore(java.lang.String, java.util.List, java.lang.Class)
	 */
	public <T> List<T> searchMore(String tableName, List<Scan> scans,
			Class<T> cls) throws IOException, InstantiationException,
			IllegalAccessException {
		
		List<T> data = new ArrayList<T>();
		
		if( scans != null && !scans.isEmpty()  ){
			
		    int size = scans.size();
		
		    if( size == 1 ){
		    	
		    	data = search(tableName , scans.get(0),cls);
		    	
		    }else{
		    	/* 有多个 scan时  采用多线程查询  */
		    	CountDownLatch  cdl = new CountDownLatch(size);
		    	
		    	List<List<T>> res_total = new ArrayList<List<T>>(size);
		    	List<T> res = null;
		    	for( Scan scan : scans ){
		    		
		    		res = new ArrayList<T>();
		    		res_total.add(res);
		    		
		    		 new Thread(new MoreClass<T>( this,tableName,scan,cls,res,cdl )).start();
		    	}
		    	
		    	try {
					cdl.await();
				} catch (InterruptedException e) {
					LOG.error("等待查询HBase表" + tableName + "返回结果时发生异常。 ", e);
				}
		    	
		    	/* 结果汇总 */
		    	for( List<T> tmp : res_total ){
		    		
		    		data.addAll(tmp);
		    	}
		    	res_total = null;
		    	
		    }
		
		}else{
			LOG.error(" scans must not null or empty !");
		}
		return data;
	}
	
	
	/**
	  * Description:
	  * 		查询HBase数据的一个线程类。
	  * 
	  * History：
	  * =============================================================
	  * Date                      Version        Memo
	  * 2014-1-30下午3:02:53            1.0            Created by LiXiaoCong
	  * 
	  * =============================================================
	  * 
	  * Copyright 2015, 迪爱斯通信设备有限公司保留。
	 */
	static class MoreRowExtractor<T> implements Runnable{
		
		private HBaseSearchServiceImpl search;
		private String tableName;
		private Scan scan;
		private RowExtractor<T> extractor;
		
		private List<T> res;
		
		private CountDownLatch  cdl;
		
		public MoreRowExtractor( HBaseSearchServiceImpl search,String tableName, Scan scan,
				RowExtractor<T> extractor,List<T> res,CountDownLatch  cdl ){
			
			this.search = search;
			this.tableName = tableName;
			this.scan = scan;
			this.extractor = extractor;
			this.res = res;
			this.cdl = cdl;
		}
		
		public void run() {
			
			try{
				res.addAll(search.search(tableName, scan, extractor));
				
			}catch( IOException e){
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
		
	}
	
	/**
	  * Description:
	  * 		查询HBase数据的一个线程类。
	  * 
	  * History：
	  * =============================================================
	  * Date                      Version        Memo
	  * 2014-1-30下午3:02:53            1.0            Created by LiXiaoCong
	  * 
	  * =============================================================
	  * 
	  * Copyright 2015, 迪爱斯通信设备有限公司保留。
	 */
	static class MoreClass<T> implements Runnable{
		
		private HBaseSearchServiceImpl search;
		
		private String tableName;
		private Scan scan;
		private Class<T> cls;
		
		private List<T> res;
		
		private CountDownLatch  cdl;
		
		public MoreClass(HBaseSearchServiceImpl search, String tableName, Scan scan,
				Class<T> cls,List<T> res,CountDownLatch  cdl ){
			this.search = search;
			this.tableName = tableName;
			this.scan = scan;
			this.cls = cls;
			this.res = res;
			this.cdl = cdl;
		}
		
		public void run() {
			
			try{
				
				res.addAll(search.search(tableName, scan, cls));
				
			}catch( IOException e){
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (IllegalArgumentException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (IllegalAccessException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (InstantiationException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
		
	}
	
	/**
	 * Description:
	 * 		查询HBase数据的一个线程类。
	 * 
	 * History：
	 * =============================================================
	 * Date                      Version        Memo
	 * 2014-1-30下午3:02:53            1.0            Created by LiXiaoCong
	 * 
	 * =============================================================
	 * 
	 * Copyright 2015, 迪爱斯通信设备有限公司保留。
	 */
	static class MoreGetExtractor<T> implements Runnable{
		
		private HBaseSearchServiceImpl search;
		private String tableName;
		private List<Get> gets;
		private RowExtractor<T> extractor;
		
		private List<T> res;
		
		private CountDownLatch  cdl;
		
		public MoreGetExtractor( HBaseSearchServiceImpl search,String tableName, List<Get> gets,
				RowExtractor<T> extractor,List<T> res,CountDownLatch  cdl ){
			
			this.search = search;
			this.tableName = tableName;
			this.gets = gets;
			this.extractor = extractor;
			this.res = res;
			this.cdl = cdl;
		}
		
		public void run() {
			
			try{
				res.addAll(search.search(tableName, gets, extractor));
				
			}catch( IOException e){
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
		
	}
	
	/**
	 * Description:
	 * 		查询HBase数据的一个线程类。
	 * 
	 * History：
	 * =============================================================
	 * Date                      Version        Memo
	 * 2014-1-30下午3:02:53            1.0            Created by LiXiaoCong
	 * 
	 * =============================================================
	 * 
	 * Copyright 2015, 迪爱斯通信设备有限公司保留。
	 */
	static class MoreGetClass<T> implements Runnable{
		
		private HBaseSearchServiceImpl search;
		
		private String tableName;
		private List<Get> gets;
		private Class<T> cls;
		
		private List<T> res;
		
		private CountDownLatch  cdl;
		
		public MoreGetClass(HBaseSearchServiceImpl search, String tableName, List<Get> gets,
				Class<T> cls,List<T> res,CountDownLatch  cdl ){
			this.search = search;
			this.tableName = tableName;
			this.gets = gets;
			this.cls = cls;
			this.res = res;
			this.cdl = cdl;
		}
		
		public void run() {
			
			try{
				
				res.addAll(search.search(tableName, gets, cls));
				
			}catch( IOException e){
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (IllegalArgumentException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (IllegalAccessException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			} catch (InstantiationException e) {
				LOG.error("查询 HBase表 " + tableName +  " 表中的数据失败。", e);
			}finally{
				cdl.countDown();
			}
			
		}
		
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, java.util.List, com.bdms.hbase.extractor.RowExtractor)
	 */
	public <T> List<T> search(String tableName, List<Get> gets,
			RowExtractor<T> extractor) throws IOException {
		
		List<T> data = new ArrayList<T>();
		
		HTableInterface table = factory.createHBaseTable(tableName);
		
		if(table != null ){
			
			Result[] results = table.get(gets);
			int n = 0;
			T row = null;
		    for( Result result : results){
		    	if( !result.isEmpty() ){
			    	row = extractor.extractRowData(result, n);
			    	data.add(row);
			    	n++;
		    	}
		    }
		    
		    close( table, null);
		}
		
		return data;
	
	}
	


	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, java.util.List, java.lang.Class)
	 */
	public <T> List<T> search(String tableName, List<Get> gets, Class<T> cls)
			throws IOException, InstantiationException, IllegalAccessException {
		
		List<T> data = new ArrayList<T>();
		
		HTableInterface table = factory.createHBaseTable(tableName);
		
		if(table != null ){
			
			Field[] fields = cls.getDeclaredFields();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			for( Field field : fields ){
				
				if( field.getModifiers() == 2 ){
					
					fieldMap.put(field.getName().toLowerCase(), field);
				}
			}
			fields = null;
			
			T obj = null;
			
			Result[] results = table.get(gets);
			String column = null;
			
			Field field = null;
			
		    for( Result result : results){
		    	
		    	 if( !result.isEmpty() ){
		    		 
		    		 obj = cls.newInstance();
			    	 for( Cell cell : result.listCells() ){
			    		
			    		 column = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
			    		 /*检查该列是否在实体类中存在对应的属性,若存在则 为其赋值*/
			    		 if( (field = fieldMap.get(column.toLowerCase())) != null ){
			    			 
			    			 field.setAccessible(true);
							 field.set(obj, Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			    		 }
			    		 
			    	 }
			    	 data.add(obj);
		    	 }
					
		    }
		    
		    close( table, null);
		}
		
		return data;
	}
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, org.apache.hadoop.hbase.client.Get, com.bdms.hbase.extractor.RowExtractor)
	 */
	public <T> T search(String tableName, Get get, RowExtractor<T> extractor)
			throws IOException {
		
		 T obj = null;
		 List<T> res = search(tableName,Arrays.asList(get),extractor);
		 if( !res.isEmpty()){
			 obj = res.get(0);
		 }
		
		return obj;
	}


	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#search(java.lang.String, org.apache.hadoop.hbase.client.Get, java.lang.Class)
	 */
	public <T> T search(String tableName, Get get, Class<T> cls)
			throws IOException, InstantiationException, IllegalAccessException {
		
		 T obj = null;
		 List<T> res = search(tableName,Arrays.asList(get),cls);
		 if( !res.isEmpty()){
			 obj = res.get(0);
		 }
		return obj;
	}


	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#searchMore(java.lang.String, java.util.List, int, com.bdms.hbase.extractor.RowExtractor)
	 */
	public <T> List<T> searchMore(String tableName, List<Get> gets,
			int perThreadExtractorGetNum, RowExtractor<T> extractor)
			throws IOException {
		
		List<T> data = new ArrayList<T>();
		
		if( gets != null && !gets.isEmpty() ){
			
			int size = gets.size();
			if( size <= perThreadExtractorGetNum ){
				
				data = search(tableName, gets, extractor);
				
			}else{
				
				int threadNum = (int)Math.ceil(size / (double)perThreadExtractorGetNum);
				
				CountDownLatch  cdl = new CountDownLatch(threadNum);
				List<List<T>> res_total = new ArrayList<List<T>>(size);
		    	List<T> res = null;
				
		    	List<Get> tmp = null;
		    	
				for(int i = 0 ; i < threadNum ; i++ ){
					
					res = new ArrayList<T>();
		    		res_total.add(res);
		    		
		    		if( i == (threadNum - 1) ){
		    			tmp = gets.subList(perThreadExtractorGetNum*i, size);
		    		}else{
		    			tmp = gets.subList(perThreadExtractorGetNum*i, perThreadExtractorGetNum*(i + 1));
		    		}
		    		
		    		new Thread(new MoreGetExtractor<T>( this,tableName,tmp,extractor,res,cdl )).start();
					
				}
				
				try {
					cdl.await();
				} catch (InterruptedException e) {
					LOG.error("等待查询HBase表" + tableName + "返回结果时发生异常。 ", e);
				}
		    	
		    	/* 结果汇总 */
		    	for( List<T> c_res : res_total ){
		    		
		    		data.addAll(c_res);
		    	}
		    	res_total = null;
				
			}
			
		}else{
			
			LOG.error("批量查询的 gets不能为空.");
		}
		
		
		return data;
	}


	/* (non-Javadoc)
	 * @see com.bdms.hbase.search.HBaseSearchService#searchMore(java.lang.String, java.util.List, int, java.lang.Class)
	 */
	public <T> List<T> searchMore(String tableName, List<Get> gets,
			int perThreadExtractorGetNum, Class<T> cls) throws IOException,
			InstantiationException, IllegalAccessException {
	
		List<T> data = new ArrayList<T>();
		
		if( gets != null && !gets.isEmpty() ){
			
			int size = gets.size();
			if( size <= perThreadExtractorGetNum ){
				
				data = search(tableName, gets, cls);
				
			}else{
				
				int threadNum = (int)Math.ceil(size / (double)perThreadExtractorGetNum);
				
				CountDownLatch  cdl = new CountDownLatch(threadNum);
				List<List<T>> res_total = new ArrayList<List<T>>(size);
		    	List<T> res = null;
				
		    	List<Get> tmp = null;
		    	
				for(int i = 0 ; i < threadNum ; i++ ){
					
					res = new ArrayList<T>();
		    		res_total.add(res);
		    		
		    		if( i == (threadNum - 1) ){
		    			tmp = gets.subList(perThreadExtractorGetNum*i, size);
		    		}else{
		    			tmp = gets.subList(perThreadExtractorGetNum*i, perThreadExtractorGetNum*(i + 1));
		    		}
		    		
		    		new Thread(new MoreGetClass<T>( this,tableName,tmp,cls,res,cdl )).start();
					
				}
				
				try {
					cdl.await();
				} catch (InterruptedException e) {
					LOG.error("等待查询HBase表" + tableName + "返回结果时发生异常。 ", e);
				}
		    	
		    	/* 结果汇总 */
		    	for( List<T> c_res : res_total ){
		    		
		    		data.addAll(c_res);
		    	}
		    	res_total = null;
				
			}
			
		}else{
			
			LOG.error("批量查询的 gets不能为空.");
		}
		
		
		return data;
	}


   
}
