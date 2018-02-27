package com.bdms.hbase.search;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import com.bdms.hbase.extractor.RowExtractor;

public class HBaseSearchServiceTest {
	
	private HBaseSearchService search = new HBaseSearchServiceImpl();
	
	//@Test
	public void testSearch01() throws IOException{
		
		 long t1 = System.currentTimeMillis();
		 Scan scan=new Scan();
	     scan.setCaching(1000);
	     scan.setMaxVersions(1);
	     
	     byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		 
	     byte[] STATION_ID = "STATION_ID".getBytes();
		 scan.addColumn(luxnew, STATION_ID);
		
	     byte[] START_TIME = "START_TIME".getBytes();
		 scan.addColumn(luxnew, START_TIME);
		 
		
		 byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		 scan.addColumn(luxnew, ENTER_TIMES);
		 
		 byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		 scan.addColumn(luxnew, EXIT_TIMES);
		 
		
		  scan.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
		  scan.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
		 
		 List<Map<String, String>> res = search.search("streaming_gj", scan, new RowExtractor<Map<String,String>>() {

			 private Map<String,String> map = null;
			 
			public Map<String, String> extractRowData(Result result, int rowNum)
					throws IOException {
				
				map = new HashMap<String,String>();
				
				for( Cell cell : result.rawCells() ){
					
					map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				
				return map;
			}
		});
		 
		 System.out.println( System.currentTimeMillis() - t1 );
		 
		 System.out.println( res );
		 System.out.println( res.size() );
		
		 
	}
	
	//@Test
	public void testSearchMore01() throws IOException{
		
		 long t1 = System.currentTimeMillis();
		 
		 Scan scan=new Scan();
	     scan.setCaching(1000);
	     scan.setMaxVersions(1);
	     
	     byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		 
	     byte[] STATION_ID = "STATION_ID".getBytes();
		 scan.addColumn(luxnew, STATION_ID);
		
	     byte[] START_TIME = "START_TIME".getBytes();
		 scan.addColumn(luxnew, START_TIME);
		 
		
		 byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		 scan.addColumn(luxnew, ENTER_TIMES);
		 
		 byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		 scan.addColumn(luxnew, EXIT_TIMES);
		 
		  Scan scan1 = new Scan(scan);
		  scan1.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
		  scan1.setStopRow(Bytes.toBytes(  "0099-20150626120000" ));
		  
		  Scan scan2 = new Scan(scan);
		  scan2.setStartRow(Bytes.toBytes( "0099-20150626120000" ));
		  scan2.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
		 
		 List<Map<String, String>> res = search.searchMore("streaming_gj", Arrays.asList(scan1,scan2), new RowExtractor<Map<String,String>>() {

			 private Map<String,String> map = null;
			 
			public Map<String, String> extractRowData(Result result, int rowNum)
					throws IOException {
				
				map = new HashMap<String,String>();
				
				for( Cell cell : result.rawCells() ){
					
					map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				
				return map;
			}
		});
		 
		 System.out.println( System.currentTimeMillis() - t1 );
		 
		 System.out.println( res );
		 System.out.println( res.size() );
		
		 
	}
	
	
	//@Test
	public void testSearchMore012() throws IOException{
		
		 long t1 = System.currentTimeMillis();
		 
		 Scan scan=new Scan();
	     scan.setCaching(1000);
	     scan.setMaxVersions(1);
	     
	     byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		 
	     byte[] STATION_ID = "STATION_ID".getBytes();
		 scan.addColumn(luxnew, STATION_ID);
		
	     byte[] START_TIME = "START_TIME".getBytes();
		 scan.addColumn(luxnew, START_TIME);
		 
		
		 byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		 scan.addColumn(luxnew, ENTER_TIMES);
		 
		 byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		 scan.addColumn(luxnew, EXIT_TIMES);
		 
		
		  scan.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
		  scan.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
		  
	   SearchMoreTable<Map<String,String>> a1 = new SearchMoreTable<>("streaming_gj", scan,new RowExtractor<Map<String,String>>() {

			 private Map<String,String> map = null;
			 
			public Map<String, String> extractRowData(Result result, int rowNum)
					throws IOException {
				
				map = new HashMap<String,String>();
				
				for( Cell cell : result.rawCells() ){
					
					map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				
				return map;
			}
		});
		  
	   Scan scan1 =new Scan();
	   scan1.setCaching(1000);
	   scan1.setMaxVersions(1);
	   scan1.addColumn(luxnew, STATION_ID);
	   scan1.addColumn(luxnew, START_TIME);
	  // scan1.addColumn(luxnew, ENTER_TIMES);
	   //scan1.addColumn(luxnew, EXIT_TIMES);
	   scan1.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
	   scan1.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
	   
	   SearchMoreTable<Map<String,String>> a2 = new SearchMoreTable<>("test_streaming_gj", scan1,new RowExtractor<Map<String,String>>() {

			 private Map<String,String> map = null;
			 
			public Map<String, String> extractRowData(Result result, int rowNum)
					throws IOException {
				
				map = new HashMap<String,String>();
				
				for( Cell cell : result.rawCells() ){
					
					map.put( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()), 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				
				return map;
			}
		});
	   
	   
	   
		 Map<String, List<Map<String, String>>> res = search.searchMore(Arrays.asList( a1,a2));
		
		 
		 System.out.println( System.currentTimeMillis() - t1 );
		 
		 System.out.println( res.get("streaming_gj") );
		 System.out.println( res.get("test_streaming_gj") );
		
		 
	}
	
	
	//@Test
	public void testSearch02() throws InstantiationException, IllegalAccessException, IOException{
		
	     long t1 = System.currentTimeMillis(); 
		 Scan scan=new Scan();
	     scan.setCaching(1000);
	     scan.setMaxVersions(1);
	     
	     scan.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
		 scan.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
	     
	     byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		 
		 
	     byte[] STATION_ID = "STATION_ID".getBytes();
		 scan.addColumn(luxnew, STATION_ID);
		
	     byte[] START_TIME = "START_TIME".getBytes();
		 scan.addColumn(luxnew, START_TIME);
		 
		
		 byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		 scan.addColumn(luxnew, ENTER_TIMES);
		 
		 byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		 scan.addColumn(luxnew, EXIT_TIMES);
		 
		 
		 List<StreamingGJ> res = search.search("streaming_gj", scan, StreamingGJ.class);
		 
		 System.out.println( System.currentTimeMillis() - t1 );
		 
		 System.out.println( res );
		 System.out.println( res.size() );
	}
	
	//@Test
	public void testSearchMore02() throws InstantiationException, IllegalAccessException, IOException{
		
		long t1 = System.currentTimeMillis(); 
		Scan scan=new Scan();
		scan.setCaching(1000);
		scan.setMaxVersions(1);
		
		byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		
		
		byte[] STATION_ID = "STATION_ID".getBytes();
		scan.addColumn(luxnew, STATION_ID);
		
		byte[] START_TIME = "START_TIME".getBytes();
		scan.addColumn(luxnew, START_TIME);
		
		
		byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		scan.addColumn(luxnew, ENTER_TIMES);
		
		byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		scan.addColumn(luxnew, EXIT_TIMES);
		
		  Scan scan1 = new Scan(scan);
		  scan1.setStartRow(Bytes.toBytes( "0099-20150626000000" ));
		  scan1.setStopRow(Bytes.toBytes(  "0099-20150626120000" ));
		  
		  Scan scan2 = new Scan(scan);
		  scan2.setStartRow(Bytes.toBytes( "0099-20150626120000" ));
		  scan2.setStopRow(Bytes.toBytes(  "0099-20150626240000" ));
		
		
		List<StreamingGJ> res = search.searchMore("streaming_gj", Arrays.asList(scan1,scan2), StreamingGJ.class);
		
		System.out.println( System.currentTimeMillis() - t1 );
		
		System.out.println( res );
		System.out.println( res.size() );
	}
	
	
	
	
	//@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException{
		
		testSearch01();
		testSearch02();
	}
	
	//@Test
	public void  testSearch03() throws IOException, ParseException{
		
		long t1 = System.currentTimeMillis();
		
		byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		byte[] STATION_ID = "STATION_ID".getBytes();
		byte[] START_TIME = "START_TIME".getBytes();
		byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		
		 SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
	     long baseTime = sd.parse("20150626000000").getTime();
	     long gap = 5 * 60 * 1000;
	     int size = 288;
	     
	     List<Get> gets = new ArrayList<Get>(size);
	     Get get1 = null;
		 for(int i = 0; i < size ; i++){
			 
			get1 = new Get(Bytes.toBytes("0099-" + sd.format(new Date( baseTime + i*gap )) ));
			get1.addColumn(luxnew, STATION_ID);
			get1.addColumn(luxnew, START_TIME);
			get1.addColumn(luxnew, ENTER_TIMES);
			get1.addColumn(luxnew, EXIT_TIMES);
			
			gets.add(get1);
		}
		
		 for(int i = 0; i < size ; i++){
			 
				get1 = new Get(Bytes.toBytes("0242-" + sd.format(new Date( baseTime + i*gap )) ));
				get1.addColumn(luxnew, STATION_ID);
				get1.addColumn(luxnew, START_TIME);
				get1.addColumn(luxnew, ENTER_TIMES);
				get1.addColumn(luxnew, EXIT_TIMES);
				
				gets.add(get1);
			}
			
		
		
		List<List<String>> result = search.search("streaming_gj",gets,new RowExtractor<List<String>>() {
			
			private List<String> res;
		   
			public List<String> extractRowData(Result result, int rowNum)
					throws IOException {
				
				res = new ArrayList<String>();
				
                for( Cell cell : result.rawCells() ){
					
					res.add( Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()) + " : " + 
			                        Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
				}
				return res;
			}
			
		});
		
		System.out.println(  System.currentTimeMillis() - t1 );
		
		System.out.println( result );
		System.out.println( result.size() );
		
		
	}
	
	//@Test
	public void  testSearch04() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		long t1 = System.currentTimeMillis();
		
		byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		byte[] STATION_ID = "STATION_ID".getBytes();
		byte[] START_TIME = "START_TIME".getBytes();
		byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		
		 SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
	     long baseTime = sd.parse("20150626000000").getTime();
	     long gap = 5 * 60 * 1000;
	     int size = 288;
	     
	     List<Get> gets = new ArrayList<Get>(size);
	     Get get1 = null;
		 for(int i = 0; i < size ; i++){
			 
			get1 = new Get(Bytes.toBytes("0099-" + sd.format(new Date( baseTime + i*gap )) ));
			get1.addColumn(luxnew, STATION_ID);
			get1.addColumn(luxnew, START_TIME);
			get1.addColumn(luxnew, ENTER_TIMES);
			get1.addColumn(luxnew, EXIT_TIMES);
			
			gets.add(get1);
		}
		
		 for(int i = 0; i < size ; i++){
			 
				get1 = new Get(Bytes.toBytes("0242-" + sd.format(new Date( baseTime + i*gap )) ));
				get1.addColumn(luxnew, STATION_ID);
				get1.addColumn(luxnew, START_TIME);
				get1.addColumn(luxnew, ENTER_TIMES);
				get1.addColumn(luxnew, EXIT_TIMES);
				
				gets.add(get1);
			}
			
		
		
		List<StreamingGJ> result = search.search("streaming_gj", gets,StreamingGJ.class);
		
		System.out.println(  System.currentTimeMillis() - t1 );
		
		System.out.println( result );
		System.out.println( result.size() );
		
		
	}
	
	//@Test
	public void  testSearchMore04() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		long t1 = System.currentTimeMillis();
		byte[] luxnew = "luxnew".getBytes();
		// scan.addFamily(luxnew);
		byte[] STATION_ID = "STATION_ID".getBytes();
		byte[] START_TIME = "START_TIME".getBytes();
		byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		
		
		 SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
	     long baseTime = sd.parse("20150626000000").getTime();
	     long gap = 5 * 60 * 1000;
	     int size = 288;
	     
	     List<Get> gets = new ArrayList<Get>(size);
	     Get get1 = null;
		 for(int i = 0; i < size ; i++){
			 
			get1 = new Get(Bytes.toBytes("0099-" + sd.format(new Date( baseTime + i*gap )) ));
			get1.addColumn(luxnew, STATION_ID);
			get1.addColumn(luxnew, START_TIME);
			get1.addColumn(luxnew, ENTER_TIMES);
			get1.addColumn(luxnew, EXIT_TIMES);
			
			gets.add(get1);
		}
		 
		 for(int i = 0; i < size ; i++){
			 
			get1 = new Get(Bytes.toBytes("0242-" + sd.format(new Date( baseTime + i*gap )) ));
			get1.addColumn(luxnew, STATION_ID);
			get1.addColumn(luxnew, START_TIME);
			get1.addColumn(luxnew, ENTER_TIMES);
			get1.addColumn(luxnew, EXIT_TIMES);
			
			gets.add(get1);
		}
		
		
	
		
		
		List<StreamingGJ> result = search.searchMore("streaming_gj", gets,2,StreamingGJ.class);
		
		System.out.println(  System.currentTimeMillis() - t1 );
		
		System.out.println( result );
		System.out.println( result.size() );
		
		
	}
	
	
	

}
