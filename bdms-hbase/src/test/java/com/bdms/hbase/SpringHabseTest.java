package com.bdms.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

public class SpringHabseTest extends JunitSpringInitialize{

	@Autowired
	private HbaseTemplate hbaseTemplate;
	
	//@Test
	public void testFindData() {
		
		
		String dayStr = "20150626";
		String station_id = "0242";
		
		long currentTimeMillis = System.currentTimeMillis();
		
		 byte[]  filter = Bytes.toBytes("STATION_ID");
			
		   //注册查询的列
		   NavigableSet<byte []> cs = new TreeSet<byte []>(Bytes.BYTES_COMPARATOR);
		   cs.add(filter);
		   cs.add(Bytes.toBytes("START_TIME"));
		   cs.add(Bytes.toBytes("ENTER_TIMES"));
		   cs.add(Bytes.toBytes("EXIT_TIMES"));
		   
		   byte[] cf = Bytes.toBytes("luxnew");
		   final Map<byte [], NavigableSet<byte []>> cfAndCs = new HashMap<byte [], NavigableSet<byte []>>();
		   cfAndCs.put(cf, cs);
			
		   
		   // 注册 不需要返回的列
		   final List<byte []> csFilter = new ArrayList<byte []>();
		   csFilter.add(filter);
			
		 
		 //列值过滤
		 // RowFilter rowFilter = new RowFilter(CompareOp.EQUAL,  new BinaryPrefixComparator(Bytes.toBytes( dayStr )));
		// SingleColumnValueFilter scvf = new SingleColumnValueFilter(cf,filter,CompareOp.EQUAL,Bytes.toBytes(station_id));
		 
		// List<Filter> filters = new ArrayList<Filter>();
		// filters.add(scvf);
		 //filters.add(rowFilter);
		 
		 //FilterList filterList = new FilterList(Operator.MUST_PASS_ALL, filters);
		
		 
		 Scan scan = new Scan();
	     scan.setCaching(1000);
	     scan.setStartRow(Bytes.toBytes( station_id + "-" + dayStr+"00"));
	     scan.setStopRow(Bytes.toBytes (station_id + "-" + dayStr+"24"));
	    // scan.setFilter(filterList);
	     scan.setFamilyMap(cfAndCs);
		
		
		List<Map<String,String>> rows = hbaseTemplate.find("dsgj",scan, new RowMapper<Map<String,String>>() {
			
			private Map<String,String> res = null;
			@Override
			public Map<String,String> mapRow(Result result, int rowNum) throws Exception {
				
				res = new HashMap<String,String>();
				 for(byte[] key : cfAndCs.keySet()){
					 for(byte[] cn : cfAndCs.get(key)){
						 if(!csFilter.contains(cn)){
							 res.put(new String(cn), new String(result.getValue(key,cn)));
						 }
					  }
			     }
				return res;
				
			}
		});
		
		System.err.println(  "---------------" + ( System.currentTimeMillis() - currentTimeMillis)  + "***********************" + rows.size());
		
	}
	
	//@Test
	public void test01(){
	
		testFindData();
		testFindData();
		testFindData();
		testFindData();
	}
	
}
