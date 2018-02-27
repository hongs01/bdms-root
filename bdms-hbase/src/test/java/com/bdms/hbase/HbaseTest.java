package com.bdms.hbase;

 
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;

import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

import com.bdms.hbase.app.HbaseActionService;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbase.service.HbaseServiceImpl;
import com.bdms.hbase.util.CollectionSortUtil;
import com.bdms.hbse.enums.ResultType;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingDZWL;

public class HbaseTest extends JunitSpringInitialize{

	
	private Logger LOG = LoggerFactory.getLogger(HbaseTest.class);
	
	@Autowired
	private HbaseService hbaseService; 
	
	//@Test
	public void testODDaytop100() throws IOException{

		long t1 = System.currentTimeMillis();
		
		List<Map<String, Object>> odData = hbaseService.getDayODtopNData(0,10);
		long t2 = System.currentTimeMillis();
		
	          // resultList是需要排序的list，其内放的是Map
	          // 返回的结果集
		
		for(Map<String, Object> map:odData){
			System.out.println(map);
		}
	}
	
	
	
	//@Test
	public void testHbaseDay() throws IOException{

		// 某站点 某天的 所有的 每五分钟的统计数据 
		long t1 = System.currentTimeMillis();
		List<Map<String, String>> stationStreamData = hbaseService.getStationDayData( "20150626", "0245",ResultType.DAY_EXIT);
		long t2 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t2 - t1)  + "***********************" + stationStreamData.size());
	
			
			System.out.println(stationStreamData);
		
			
		
		// 某换乘站   某天的 所有的 每五分钟的统计数据 
		long t3 = System.currentTimeMillis();
		List<Map<String, String>> hcStationStreamData = hbaseService.getStationDayData( "20150626", "0045",ResultType.DAY_ALL);
		long t4 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t4 - t3)  + "***********************" + hcStationStreamData.size());
		for( int i = 0 ; i < 10 ; i++   ){
			
			System.out.println(hcStationStreamData.get(i));
		}
		
		
		// 某线路   某天的 所有的 每五分钟的统计数据 
		long t5 = System.currentTimeMillis();
		List<Map<String, String>>lineStreamData = hbaseService.getStationDayData( "20150626", "0100",ResultType.DAY_ALL);
		long t6 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t6 - t5)  + "***********************" + lineStreamData.size());
		for( int i = 0 ; i < 10 ; i++ ){
			
			System.out.println(lineStreamData.get(i));
		}
		
		// 某线路   某天的 所有的 每五分钟的统计数据 
		long t7 = System.currentTimeMillis();
		List<Map<String, String>>countStreamData = hbaseService.getStationDayData( "20150626", "0099",ResultType.DAY_ALL);
		long t8 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t8 - t7)  + "***********************" + countStreamData.size());
		for( int i = 0 ; i < 10 ; i++  ){
			
			System.out.println(countStreamData.get(i));
		}
		
		
	}
	

	//@Test
	public void testHbaseDayNeedGroupResult() throws IOException{
		
		Map<String, List<String>> data = null;
		
		

		// 某站点 某天的 所有的 每五分钟的统计数据 
		long t1 = System.currentTimeMillis();
		data = hbaseService.getStationDayDataNeedGroupResult( "20150626", "0242");
		long t2 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t2 - t1)  + "***********************" );
		printData(data);
	
			
		
		// 某换乘站   某天的 所有的 每五分钟的统计数据 
		long t3 = System.currentTimeMillis();
		data = hbaseService.getStationDayDataNeedGroupResult( "20150626", "0045");
		long t4 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t4 - t3)  + "***********************" );
		printData(data);
		
		
		// 某线路   某天的 所有的 每五分钟的统计数据 
		long t5 = System.currentTimeMillis();
		data = hbaseService.getStationDayDataNeedGroupResult( "20150626", "0100");
		long t6 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t6 - t5)  + "***********************" );
		printData(data);
		
		// 某线路   某天的 所有的 每五分钟的统计数据 
		long t7 = System.currentTimeMillis();
		data = hbaseService.getStationDayDataNeedGroupResult( "20150626", "0099");
		long t8 = System.currentTimeMillis();
		System.err.println(  "---------------" + ( t8 - t7)  + "***********************" );
		printData(data);
		
		
	}
	
	private void printData(Map<String, List<String>> data  ){
		
		
		List<String> x = null;
		
		Set<String> keySet =  data.keySet() ;
		for(  String key : keySet  ){
			
			x = data.get(key);
			System.out.println( x.size() + " ******* " +  x );
			
		}
		
	}
	
	//@Test
	public void test01() throws IOException{
	
		testHbaseDay();
		testHbaseDayNeedGroupResult();
	}
	
	//@Test
	public void testHbaseMonth() throws IOException{
		
		
		long t1 = System.currentTimeMillis();
	
		List<Map<String, String>> stationDayData = hbaseService.getStationMonthata("201506", "0743");
		long t2 = System.currentTimeMillis();
		
		System.err.println(  "---------------" + ( t2 - t1)  + "***********************" + stationDayData.size());
		
		
	}
	
	
	//@Test
	public void testPredicte() throws IOException{
		
	/*	 List<Map<String, String>> stationDayPredicteData = hbaseService.getStationDayPredicteData("20150626", "0099",ResultType.DAY_ALL);
		
		 System.out.println("---" + stationDayPredicteData.size() + "---");
		 
		for(Map<String, String> map : stationDayPredicteData ){
			
			System.out.println(map);
		}*/
		
		LOG.debug("-----------------------------------");
		System.out.println(hbaseService.getPredicteData("20150626080200", "0241", ResultType.DAY_ENTER));
		System.out.println(hbaseService.getPredicteData("20150626080200", "0241", ResultType.DAY_EXIT));
		System.out.println(hbaseService.getPredicteData("20150626080200", "0241", ResultType.DAY_SUB));
		System.out.println(hbaseService.getPredicteData("20150626080200", "0241", ResultType.DAY_SUM));
	}
	
	//@Test
	public void testTopN() throws IOException{
		
		long t1 = System.currentTimeMillis();
		List<Map<String, Object>> stationDayLatestDataTopN = hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_TIMES, 0, 50);
		System.out.println(System.currentTimeMillis() - t1);
		
		long t2 = System.currentTimeMillis();
		List<Map<String, Object>> stationDayLatestDataTopN1 = hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_TIMES, 0, 50);
		System.out.println(System.currentTimeMillis() - t2);
		
		long t3 = System.currentTimeMillis();
		List<Map<String, Object>> stationDayLatestDataTopN2 = hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_TIMES, 0, 50);
		System.out.println(System.currentTimeMillis() - t3);
		
		for(Map<String, Object> map : stationDayLatestDataTopN ){
			
			System.out.println(map);
		}
		
		//System.out.println(hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_TIMES, 0, 10));
		//System.out.println(hbaseService.getStationDayLatestDataTopN(SortKey.EXIT_TIMES, 0, 10));
		//System.out.println(hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_EXIT_SUB, 0, 10));
		//System.out.println(hbaseService.getStationDayLatestDataTopN(SortKey.ENTER_EXIT_SUM, 0, 10));
	}
	
	@Test
	public void testStoreFile(){
		
		
		hbaseService.storeImgMetaData("20150624091429", "015112017166014", "0000", "A", ".\\images\\20150624091428_015112017166014_density.jpg", "0007", ".\\images\\20150624091428_015112017166014_group.jpg", "C", ".\\images\\20150624091428_015112017166014_warn.jpg","00000000");
		
	}
	
	//@Test
	public static void testH() throws IOException{
		
		
		 Configuration hConf = getConf();
	     HConnection conn = HConnectionManager.createConnection(hConf);
	     
	     HTableInterface table = conn.getTable("monthgj");
	     
	     Scan scan=new Scan();
	     scan.setCaching(1000);
	     
	     byte[] luxnew = "luxnew".getBytes();
		 scan.addFamily(luxnew);
		 
	     byte[] LINE_ID = "LINE_ID".getBytes();
		 scan.addColumn(luxnew, LINE_ID);
		 
	     byte[] STATION_ID = "STATION_ID".getBytes();
		 scan.addColumn(luxnew, STATION_ID);
		
	     byte[] START_TIME = "DAY".getBytes();
		 scan.addColumn(luxnew, START_TIME);
		 
		
		 byte[] ENTER_TIMES = "ENTER_AVG".getBytes();
		 scan.addColumn(luxnew, ENTER_TIMES);
		 
		 byte[] EXIT_TIMES = "EXIT_AVG".getBytes();
		 scan.addColumn(luxnew, EXIT_TIMES);
		 
		// PageFilter pf = new PageFilter(10);
		 
		 long currentTimeMillis = System.currentTimeMillis();
		 
		 SingleColumnValueFilter scvf = new SingleColumnValueFilter(luxnew,STATION_ID,CompareOp.EQUAL,Bytes.toBytes("0743"));
		 /*
		 BinaryComparator binaryComparator = new  BinaryComparator(Bytes.toBytes("count"));
		 QualifierFilter qff = new QualifierFilter(CompareOp.GREATER,binaryComparator);*/
		 
		 RowFilter rowFilter = new RowFilter(CompareOp.EQUAL,  new BinaryPrefixComparator(Bytes.toBytes( "201506" )));
		 
		 List<Filter> filters = new ArrayList<Filter>();
		 filters.add(scvf);
		 //filters.add(rowFilter);
		 
		 
		 
		
	     scan.setFilter(new FilterList(Operator.MUST_PASS_ALL, filters));
	     // scan.setFilter(rowFilter);
	    // scan.setMaxResultSize(10);
	     
	     scan.setStartRow(Bytes.toBytes( "20150625" ));
	     scan.setStopRow(Bytes.toBytes(  "20150630" ));
	     
	     ResultScanner scanner = table.getScanner(scan);
	     
	     
	     Map<String,List<Map<String,String>>> allData = new HashMap<String,List<Map<String,String>>>();
	     List<Map<String,String>>  lineData = new ArrayList<Map<String,String>>();
	     Map<String,String>  data = new HashMap<String,String>();
	    
	     for( Result res : scanner ){
	    
	    	 System.out.print(  new String(res.getValue(luxnew, STATION_ID) ) + "  --  ");
	    	 System.out.print( new String(res.getValue(luxnew, START_TIME) ) + "  --  ");
	    	 System.out.print( new String(res.getValue(luxnew, ENTER_TIMES) ) + "  --  ");
	    	 System.out.println( new String(res.getValue(luxnew, EXIT_TIMES) ));
	     }
	     
	     System.out.println(System.currentTimeMillis() -currentTimeMillis  );
	     
	     scanner.close();
	     scanner = null;
	}

	
	public static void testDay() throws IOException{
			
			
			 Configuration hConf = getConf();
		     HConnection conn = HConnectionManager.createConnection(hConf);
		     
		     HTableInterface table = conn.getTable("history_predicte_gj");
		     
		     Scan scan=new Scan();
		     scan.setCaching(1000);
		     
		     byte[] luxnew = "luxnew".getBytes();
			 scan.addFamily(luxnew);
			 
		   
			 
		     byte[] STATION_ID = "STATION_ID".getBytes();
			 scan.addColumn(luxnew, STATION_ID);
			
		     byte[] START_TIME = "START_TIME".getBytes();
			 scan.addColumn(luxnew, START_TIME);
			 
			
			 byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
			 scan.addColumn(luxnew, ENTER_TIMES);
			 
			 byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
			 scan.addColumn(luxnew, EXIT_TIMES);
			 
			// PageFilter pf = new PageFilter(10);
			 
			 long currentTimeMillis = System.currentTimeMillis();
			 
			 SingleColumnValueFilter scvf = new SingleColumnValueFilter(luxnew,STATION_ID,CompareOp.EQUAL,Bytes.toBytes("count"));
			 /*
			 BinaryComparator binaryComparator = new  BinaryComparator(Bytes.toBytes("count"));
			 QualifierFilter qff = new QualifierFilter(CompareOp.GREATER,binaryComparator);*/
			 
			 RowFilter rowFilter = new RowFilter(CompareOp.EQUAL,  new BinaryPrefixComparator(Bytes.toBytes( "201506" )));
			 
			 List<Filter> filters = new ArrayList<Filter>();
			 filters.add(scvf);
			 //filters.add(rowFilter);
			 
			 Get get = new Get(Bytes.toBytes( "1633-11111111231500" ));
			 
			
		   //  scan.setFilter(new FilterList(Operator.MUST_PASS_ALL, filters));
		     // scan.setFilter(rowFilter);
		    // scan.setMaxResultSize(10);
		     
		     scan.setStartRow(Bytes.toBytes( "hcz" ));
		     scan.setStopRow(Bytes.toBytes(  "hdz" ));
		     
		     Result res = table.get(get);
		     
		     System.out.print(new String(res.getRow()) + "---");
		    
		     System.out.print(  new String(res.getValue(luxnew, STATION_ID) ) + "  --  ");
	    	 System.out.print( new String(res.getValue(luxnew, START_TIME) ) + "  --  ");
	    	 System.out.print( new String(res.getValue(luxnew, ENTER_TIMES) ) + "  --  ");
	    	 System.out.println( new String(res.getValue(luxnew, EXIT_TIMES) ));
		     
		    /*ResultScanner scanner = table.getScanner(scan);
		     
		     
		     
		     Map<String,List<Map<String,String>>> allData = new HashMap<String,List<Map<String,String>>>();
		     List<Map<String,String>>  lineData = new ArrayList<Map<String,String>>();
		     Map<String,String>  data = new HashMap<String,String>();
		    
		     int n = 0 ;
		     
		     for( Result res : scanner ){
		    	 n += 1;
		    	 System.out.print(  new String(res.getValue(luxnew, STATION_ID) ) + "  --  ");
		    	 System.out.print( new String(res.getValue(luxnew, START_TIME) ) + "  --  ");
		    	 System.out.print( new String(res.getValue(luxnew, ENTER_TIMES) ) + "  --  ");
		    	 System.out.println( new String(res.getValue(luxnew, EXIT_TIMES) ));
		     }
		     
		     System.out.println(System.currentTimeMillis() -currentTimeMillis  );
		     System.out.println(n );
		     
		     scanner.close();
		     scanner = null;
		     */
		}
	
	
	public static void testDayLatest() throws IOException{
		
		
		 Configuration hConf = getConf();
	     HConnection conn = HConnectionManager.createConnection(hConf);
	     
	     HTableInterface table = conn.getTable("test_streaming_gj");
	   
	    // System.out.println(conn.isTableAvailable(TableName.valueOf("test_streaming_gs")));
	     
	     Scan scan=new Scan();
	     scan.setCaching(1000);
	     scan.setMaxVersions(3);
	     
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
		 
		// PageFilter pf = new PageFilter(10);
		 
		 long currentTimeMillis = System.currentTimeMillis();
		 
		 //SingleColumnValueFilter scvf = new SingleColumnValueFilter(luxnew,STATION_ID,CompareOp.EQUAL,Bytes.toBytes("count"));
		 /*
		 BinaryComparator binaryComparator = new  BinaryComparator(Bytes.toBytes("count"));
		 QualifierFilter qff = new QualifierFilter(CompareOp.GREATER,binaryComparator);*/
		 
		// RowFilter rowFilter = new RowFilter(CompareOp.EQUAL,  new BinaryPrefixComparator(Bytes.toBytes( "201506" )));
		 
		// List<Filter> filters = new ArrayList<Filter>();
		 //filters.add(scvf);
		 //filters.add(rowFilter);
		 
		// Get get = new Get(Bytes.toBytes( "1633-11111111231500" ));
		 
		
	   //  scan.setFilter(new FilterList(Operator.MUST_PASS_ALL, filters));
	     // scan.setFilter(rowFilter);
	    // scan.setMaxResultSize(10);
	     
	     scan.setStartRow(Bytes.toBytes( "0099-2" ));
	     scan.setStopRow(Bytes.toBytes(  "0099-3" ));
	     
	     //Result res = table.get(get);
	     
	   
	     
	    ResultScanner scanner = table.getScanner(scan);
	     
	     
	     
	     Map<String,List<Map<String,String>>> allData = new HashMap<String,List<Map<String,String>>>();
	     List<Map<String,String>>  lineData = new ArrayList<Map<String,String>>();
	     Map<String,String>  data = new HashMap<String,String>();
	    
	     for (Result r : scanner) {
	    	 
	    	 List<Cell> columnCells = r.getColumnCells(Bytes.toBytes("luxnew"), Bytes.toBytes("ENTER_TIMES"));
	    	 for (Cell kv : columnCells) {
	                System.out.println(String.format("row:%s,  qualifiervalue:%s.", 
	                        Bytes.toString(kv.getRowArray(),kv.getRowOffset(),kv.getRowLength()), 
	                        //Bytes.toString(kv.getFamilyArray(),kv.getFamilyOffset(),kv.getFamilyLength()), 
	                        //Bytes.toString(kv.getQualifierArray(),kv.getQualifierOffset(),kv.getQualifierLength()), 
	                        Bytes.toString(kv.getValueArray(),kv.getValueOffset(),kv.getValueLength())
	                      ));
	            }
	    	 
	    	
	            for (Cell kv : r.rawCells()) {
	                System.out.println(String.format("row:%s, family:%s, qualifier:%s, qualifiervalue:%s, timestamp:%s.", 
	                        Bytes.toString(kv.getRowArray(),kv.getRowOffset(),kv.getRowLength()), 
	                        Bytes.toString(kv.getFamilyArray(),kv.getFamilyOffset(),kv.getFamilyLength()), 
	                        Bytes.toString(kv.getQualifierArray(),kv.getQualifierOffset(),kv.getQualifierLength()), 
	                        Bytes.toString(kv.getValueArray(),kv.getValueOffset(),kv.getValueLength()),
	                        kv.getTimestamp()));
	            }
	        }
	        
	     scanner.close();
	     scanner = null;
	     
	}

	
	private static Configuration getConf() {
		
		String HBASESITE = "hbase/hbase-site.xml";
		String HBASEHDFS = "hbase/hdfs-site.xml";
		
		
		
		
		Configuration hConf = HBaseConfiguration.create();
	   // hConf.set("hbase.zookeeper.quorum", zk);
	    //hConf.set("hbase.zookeeper.property.clientPort", "2181") ;
	   // hConf.set("hbase.rootdir","hdfs://dswhhadoop-1:8020/apps/hbase/data");
	    hConf.addResource(HBASEHDFS);
	    hConf.addResource(HBASESITE);
		return hConf;
	}
	
	public static void main(String[] args) throws IOException {
		
		testDayLatest();
	}
	
	
	/*@Test
	public void testCountRows() throws Throwable{
		
		    Configuration configuration = getConf();
		   
		    //提高RPC通信时长
		    configuration.setLong("hbase.rpc.timeout", 600000);
		    //设置Scan缓存
		    configuration.setLong("hbase.client.scanner.caching", 1000);
		    
		    AggregationClient aggregationClient = new AggregationClient(configuration);
		    
		    Scan scan = new Scan();
		    
		    //指定扫描列族，唯一值
		    byte[] cf = "position".getBytes();
		    scan.addFamily(cf);
		    
		    //long rowCount = aggregationClient.rowCount(TABLE_NAME, null, scan);
		    long rowCount = aggregationClient.rowCount(TableName.valueOf("testGIS002"), new LongColumnInterpreter(), scan);
		    System.out.println("row count is " + rowCount);
		 
	}*/
	
	@Test
	  public void testHdfs() throws IOException{
		  
		  Configuration conf = new Configuration();
		  String HBASEHDFS = "hbase/hdfs-site.xml";
		  String CORE = "hbase/core-site.xml";
		  conf.addResource(HBASEHDFS);
		  conf.addResource(CORE);
		  
		  FileSystem fs =  FileSystem.get(conf);
		  
		  Path path = new Path("/ftp/");
		  FileStatus[] listStatus = fs.listStatus(path);
		  for(FileStatus f : listStatus){
			 System.out.println( f.getModificationTime());
			  
		  }
		  
	  }
	
	@Test
	public void testInsertData() throws IOException, ParseException{
		
		 Configuration hConf = getConf();
	     HConnection conn = HConnectionManager.createConnection(hConf);
	   
	    TableName name = TableName.valueOf("streaming_dzwl");
	     
	     if(!conn.isTableAvailable(name)){
	    	 
	    	 HBaseAdmin admin = new HBaseAdmin(hConf);
	    	 HTableDescriptor htd = new HTableDescriptor(name);
			 
	    	 HColumnDescriptor hcd = new HColumnDescriptor("dzwl");
			 hcd.setMaxVersions(1);
			 hcd.setBloomFilterType(BloomType.ROWCOL);
			 hcd.setInMemory(true);
			// hcd.setTimeToLive(60*60*30)
			 htd.addFamily( hcd);
			 admin.createTable(htd);
			 admin.close();
			 
	    	 
	     }
	     
	     SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	     long baseTime = sd.parse("2015-08-22 00:00").getTime();
	     long gap = 30 * 60 * 1000;
	     
	     Random ran = new Random();
	    
	     byte[] cf = Bytes.toBytes("dzwl");
	     
	     byte[] rwm = Bytes.toBytes("rwm");
	     byte[] qym = Bytes.toBytes("qym");
	     byte[] sj = Bytes.toBytes("sj");
	     
	     byte[] yhs = Bytes.toBytes("yhs");
	     byte[] yhstb = Bytes.toBytes("yhstb");
	     
	     byte[] mrs = Bytes.toBytes("mrs");
	     byte[] mrstb = Bytes.toBytes("mrstb");
	     
	     byte[] mcs = Bytes.toBytes("mcs");
	     byte[] mcstb = Bytes.toBytes("mcstb");
	     
 	     
	     HTableInterface table = conn.getTable(name);
	     List<Put> puts = new ArrayList<Put>(); 
	     
	     String format =null;
	     int n1 = 20000;
	     int n2 = 70000;
	     
	     for( int i = 0 ; i < 48 ; i++ ){
	    	 
	    	format = sd.format( new Date( baseTime + gap * i ));//.split(" ")[1];
	    	
			Put p1 = new Put(Bytes.toBytes("00001-" + format));
		     
		     p1.add(cf, rwm, Bytes.toBytes("重点区域-顾村"));
		     p1.add(cf, qym, Bytes.toBytes("00001"));
		     p1.add(cf, sj, Bytes.toBytes(format));
		     
		     p1.add(cf, yhs, Bytes.toBytes(String.valueOf( n1 + ran.nextInt(20000))));
		     p1.add(cf, yhstb, Bytes.toBytes(String.valueOf( n1 + ran.nextDouble()*20000)));
		     
		     p1.add(cf, mrs, Bytes.toBytes(String.valueOf( n1 + ran.nextInt(20000))));
		     p1.add(cf, mrstb, Bytes.toBytes(String.valueOf( n1 + ran.nextDouble()*20000)));
		     
		     p1.add(cf, mcs, Bytes.toBytes(String.valueOf( n1 + ran.nextInt(20000))));
		     p1.add(cf, mcstb, Bytes.toBytes(String.valueOf( n1 + ran.nextDouble()*20000)));
		     
		     Put p2 = new Put(Bytes.toBytes("00002-" + format));
		     
		     p2.add(cf, rwm, Bytes.toBytes("重点区域-外滩"));
		     p2.add(cf, qym, Bytes.toBytes("00002"));
		     p2.add(cf, sj, Bytes.toBytes(format));
		     
		     p2.add(cf, yhs, Bytes.toBytes(String.valueOf( n2 + ran.nextInt(40000))));
		     p2.add(cf, yhstb, Bytes.toBytes(String.valueOf( n2 + ran.nextDouble()*40000)));
		     
		     p2.add(cf, mrs, Bytes.toBytes(String.valueOf( n2 + ran.nextInt(40000))));
		     p2.add(cf, mrstb, Bytes.toBytes(String.valueOf( n2 +ran.nextDouble()*40000)));
		     
		     p2.add(cf, mcs, Bytes.toBytes(String.valueOf( n2 + ran.nextInt(40000))));
		     p2.add(cf, mcstb, Bytes.toBytes(String.valueOf( n2 + ran.nextDouble()*40000)));
		     
		     puts.add(p1);
		     puts.add(p2);
	    	 
	     }
	     
	    
	     
	     table.put(puts);
	     
	     table.close();
	     
	}
	
	
	@Test
	public void testGetDZWLDataByName(){
		
		List<Map<String, String>> dzwlDayDataByNames = hbaseService.getDZWLDayDataByName("外滩", StreamingDZWL.getAllColumn(),null);
		
		long t1 = System.currentTimeMillis();
		List<Map<String, String>> dzwlDayDataByName = hbaseService.getDZWLDayDataByName("外滩", StreamingDZWL.getAllColumn(),null);
		System.out.println( System.currentTimeMillis() - t1);
		
		for( Map<String,String> map : dzwlDayDataByName ){
			
			System.out.println( map );
		}
	}
	
	
}
