package com.bdms.hbase.app;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.springframework.stereotype.Service;

@Service
public interface HbaseActionService {

	
	List<Map<String,String>> getAlldata(String table,Map<String, List<String>> columns,int num) throws IOException;
	
	
	/* 
	 * @Title: execSearch   单线程查询  hbase 中的数据
	 * @Description: TODO 
	 * @param @param table   hbase的表名
	 * @param @param cfAndCs   列族与列的键值对
	 * @param @param filterList   hbase的 FilterList
	 * @param @param csFilter    不要返回的列
	 * @param @param prop       包含  startRow 和 stopRow 两条记录
	 * @param @return
	 * @param @throws IOException    
	 * @return List<Map<String,String>>    
	 * @throws 
	 * @Author  Lixc
	*/
	List<Map<String,String>> execSearch(String table, Map<byte [], NavigableSet<byte []>>  cfAndCs,FilterList filterList, List<byte []> csFilter, Map<String,String> prop) throws IOException;
	
	//多线程并发执行查询
	List<Map<String,String>> execSearchByMoreThreads(String table, Map<byte [], NavigableSet<byte []>>  cfAndCs,FilterList filterList, List<byte []> csFilter, List<Map<String,String>> props) throws IOException;
	
	
	Map<String,List<String>> execSearchByMoreThreadsNeedGroupResult(String table, Map<byte [], NavigableSet<byte []>>  cfAndCs,FilterList filterList, NavigableSet<byte []> colsResturn, List<Map<String,String>> props) throws IOException;
	
	/**
	  * description:  往hbase的表中插入单条数据
	  * @param tableName
	  * @param put
	  * void
	  * 2015-8-20 下午3:15:50
	  * by Lixc
	 */
	void storeDataToHbase(String tableName,Put put) throws IOException;
	
	/**
	  * description:  往hbase的表中插入单条数据
	  * @param tableName
	  * @param put
	  * void
	  * 2015-8-20 下午3:15:50
	  * by Lixc
	 */
	void storeDataToHbase(String tableName,List<Put> puts) throws IOException;
	
	 /* 
	 * @Title: searchResultByGets   批量  get查询
	 * @Description: TODO 
	 * @param @param tableName
	 * @param @param gets
	 * @param @param cf
	 * @param @param columnsInclude  
	 * @param @return
	 * @param @throws IOException    
	 * @return List<Map<String,String>>      key 为   columnsInclude中值
	 * @throws 
	 * @Author  Lixc
	*/
	List<Map<String,String>> searchResultByGets(String tableName,List<Get> gets) throws IOException;
	
	 
	Map<String,String> searchResultByGet(String tableName,Get get) throws IOException;
	
	
	//String getLatestDateStr(String tableName,byte[] cf , String timeColumn) throws IOException;
	
	
	String getFirstResult(String tableName,Scan scan,byte[] cf , String columnsInclude)throws IOException;

}
