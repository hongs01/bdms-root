package com.bdms.hbase.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;


import com.bdms.common.lang.StringUtils;
import com.bdms.hbase.app.HbaseActionService;
import com.bdms.hbase.util.DSHbaseDateUtil;
import com.bdms.hbase.util.NumberUtil;
import com.bdms.hbse.enums.HistoryPredicteDZWL;
import com.bdms.hbse.enums.HistoryPredicteGJ;
import com.bdms.hbse.enums.HotODGJ;
import com.bdms.hbse.enums.ImgHTableMeta;
import com.bdms.hbse.enums.OneDayHotODGJ;
import com.bdms.hbse.enums.ResultType;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingDZWL;
import com.bdms.hbse.enums.StreamingGJ;
import com.bdms.hbse.enums.Wifi2Meta;
import com.bdms.hbse.enums.WifiTableMeta;

/**
  * Description:
  * 		这是一个例子。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-8-22下午2:08:04            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service(value = "hbaseService")
public class HbaseServiceImpl implements HbaseService {

	private static final Logger LOG = LoggerFactory
			.getLogger(HbaseServiceImpl.class);

	//private static final String STREAMINGTABLE = "streaming_gj";

	//private static final String ODTOP100TABLE = "hot_od_gj";

	//private static final String PREDICTETABLE = "history_predicte_gj";
	

	@Autowired
	private HbaseActionService hbaseActionService;

	@Autowired
	private HbaseTemplate hbaseTemplate;

	public List<Map<String, String>> getAllData(String tableName,
			Map<String, List<String>> columns, int num) {

		List<Map<String, String>> data = null;
		try {
			data = hbaseActionService.getAlldata(tableName, columns, num);
		} catch (IOException e) {
			LOG.error("读取数据失败", e);
		}

		return data;
	}

	// 单线程读取每天数据 代码备份
	public List<Map<String, String>> getStationDayDataSingle(String dayStr,
			String station_id) throws IOException {

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);

		cs.add(Bytes.toBytes("START_TIME"));
		cs.add(Bytes.toBytes("ENTER_TIMES"));
		cs.add(Bytes.toBytes("EXIT_TIMES"));

		byte[] cf = Bytes.toBytes("luxnew");
		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);

		Map<String, String> prop = new HashMap<String, String>();
		prop.put("startRow", station_id + "-" + dayStr + "00");
		prop.put("stopRow", station_id + "-" + dayStr + "24");

		return hbaseActionService.execSearch("dsgj", cfAndCs, null, null, prop);

	}

	// 单线程读取每月数据 代码备份
	public List<Map<String, String>> getStationMonthataSingle(String monthStr,
			String station_id) throws IOException {

		byte[] filter = Bytes.toBytes("STATION_ID");

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
		cs.add(filter);
		cs.add(Bytes.toBytes("DAY"));
		cs.add(Bytes.toBytes("ENTER_AVG"));
		cs.add(Bytes.toBytes("EXIT_AVG"));

		byte[] cf = Bytes.toBytes("luxnew");

		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);

		// 注册 不需要返回的列
		List<byte[]> csFilter = new ArrayList<byte[]>();
		csFilter.add(filter);

		// 列值过滤
		// RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new
		// BinaryPrefixComparator(Bytes.toBytes( monthStr )));
		SingleColumnValueFilter scvf = new SingleColumnValueFilter(cf, filter,
				CompareOp.EQUAL, Bytes.toBytes(station_id));

		List<Filter> filters = new ArrayList<Filter>();
		filters.add(scvf);
		// filters.add(rowFilter);
		FilterList filterList = new FilterList(Operator.MUST_PASS_ALL, filters);

		Map<String, String> prop = new HashMap<String, String>();
		prop.put("startRow", monthStr + "00");
		prop.put("stopRow", monthStr + "32");

		return hbaseActionService.execSearch("monthgj", cfAndCs, filterList,
				csFilter, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.hbase.service.HbaseService#getStationDayPredicteData(java.lang
	 * .String, java.lang.String, com.bdms.hbse.enums.ResultType)
	 */
	public List<Map<String, String>> getStationDayPredicteData( String dayStr,
			String station_id, ResultType type) throws IOException {
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			calendar.setTime(simpleDateFormat.parse(dayStr));
		} catch (ParseException e) {
			LOG.error("日期格式转换失败.", e);
		}
		String str = null;
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n > 1 && n < 7) {
			str = "11111111"; // 工作日
		} else {
			str = "00000000"; // 周末
		}

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);

		cs.add(Bytes.toBytes("START_TIME"));

		switch (type) {
		case DAY_ENTER:
			cs.add(Bytes.toBytes("ENTER_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 进站预测信息");
			break;
		case DAY_EXIT:
			cs.add(Bytes.toBytes("EXIT_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 出站预测信息");
			break;
		case DAY_ALL:
			cs.add(Bytes.toBytes("ENTER_TIMES"));
			cs.add(Bytes.toBytes("EXIT_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 进出站预测信息");
			break;
		default:
			LOG.error("获取数据的类型设置错误");
			break;
		}

		byte[] cf = Bytes.toBytes("luxnew");
		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);

		return getStationDayDataByMoreThread(HistoryPredicteGJ.TABLENAME.getName(), cfAndCs, null,
				null, str, station_id);

	}

	// 默认采用多线程并发查询每五分钟的实时数据 线程数 固定为4个
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.hbase.service.HbaseService#getStationDayData(java.lang.String,
	 * java.lang.String, com.bdms.hbse.enums.ResultType)
	 */
	public List<Map<String, String>> getStationDayData(String dayStr,
			String station_id, ResultType type) throws IOException {

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);

		cs.add(Bytes.toBytes("START_TIME"));

		switch (type) {
		case DAY_ENTER:
			cs.add(Bytes.toBytes("ENTER_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 实时进站信息");
			break;
		case DAY_EXIT:
			cs.add(Bytes.toBytes("EXIT_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 实时出站信息");
			break;
		case DAY_ALL:
			cs.add(Bytes.toBytes("ENTER_TIMES"));
			cs.add(Bytes.toBytes("EXIT_TIMES"));
			LOG.info("请求 日期为" + dayStr + " 的  站点 id为 ：" + station_id
					+ " 实时进出站信息");
			break;
		default:
			LOG.error("获取数据的类型设置错误");
			break;
		}

		byte[] cf = StreamingGJ.CF.getBytes();
		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);
		//判断从实时表还是历史表中取数据
		LOG.info("采用的日期是："+dayStr);
		String table=StreamingGJ.TABLENAME.getName();
		if(dayStr.contains("-") && "his".equals(dayStr.split("-")[1])){
				table=StreamingGJ.TABLENAMEHIS.getName();
		}
		LOG.info("采用的表是："+table);
		return getStationDayDataByMoreThread(table, cfAndCs, null,
				null, dayStr.split("-")[0], station_id);
	}

	// 分配线程任务
	private List<Map<String, String>> getStationDayDataByMoreThread(
			String table, Map<byte[], NavigableSet<byte[]>> cfAndCs,
			FilterList filterList, List<byte[]> csFilter, String dayStr,
			String station_id) throws IOException {

		// 分配每个线程负责的 rowkey的区域
		List<Map<String, String>> props = new ArrayList<Map<String, String>>();
		Map<String, String> prop = null;

		// 固定为4个线程 读取每天的数据 ，也就是将一天 24小时平均分成 4份
		int gap = 24 / 4;
		for (int i = 0; i < 4; i++) {

			prop = new HashMap<String, String>();
			prop.put(
					"startRow",
					station_id + "-" + dayStr
							+ NumberUtil.convetorNumToString(i * gap));
			prop.put(
					"stopRow",
					station_id + "-" + dayStr
							+ NumberUtil.convetorNumToString((i + 1) * gap));
			props.add(prop);
		}

		return hbaseActionService.execSearchByMoreThreads(table, cfAndCs,
				filterList, csFilter, props);
	}

	// 默认采用多线程并发查询每月数据 线程数 固定为 8 个
	public List<Map<String, String>> getStationMonthata(String monthStr,
			String station_id) throws IOException {

		byte[] filter = Bytes.toBytes("STATION_ID");

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
		cs.add(filter);
		cs.add(Bytes.toBytes("DAY"));
		cs.add(Bytes.toBytes("ENTER_AVG"));
		cs.add(Bytes.toBytes("EXIT_AVG"));

		byte[] cf = Bytes.toBytes("luxnew");

		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);

		// 注册 不需要返回的列
		List<byte[]> csFilter = new ArrayList<byte[]>();
		csFilter.add(filter);

		// 列值过滤
		// RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new
		// BinaryPrefixComparator(Bytes.toBytes( monthStr )));
		SingleColumnValueFilter scvf = new SingleColumnValueFilter(cf, filter,
				CompareOp.EQUAL, Bytes.toBytes(station_id));

		List<Filter> filters = new ArrayList<Filter>();
		filters.add(scvf);
		// filters.add(rowFilter);
		FilterList filterList = new FilterList(Operator.MUST_PASS_ALL, filters);

		return getStationMonthDataByMoreThread("monthgj", cfAndCs, filterList,
				csFilter, monthStr);
	}

	// 分配线程任务
	private List<Map<String, String>> getStationMonthDataByMoreThread(
			String table, Map<byte[], NavigableSet<byte[]>> cfAndCs,
			FilterList filterList, List<byte[]> csFilter, String monthStr)
			throws IOException {

		// 分配每个线程负责的 rowkey的区域
		List<Map<String, String>> props = new ArrayList<Map<String, String>>();
		Map<String, String> prop = null;

		// 固定为8个线程 读取每月的数据
		int gap = 4;

		for (int i = 0; i < 8; i++) {

			prop = new HashMap<String, String>();
			prop.put("startRow",
					monthStr + NumberUtil.convetorNumToString(i * gap));
			prop.put("stopRow",
					monthStr + NumberUtil.convetorNumToString((i + 1) * gap));
			props.add(prop);
		}

		return hbaseActionService.execSearchByMoreThreads(table, cfAndCs,
				filterList, csFilter, props);
	}

	@Deprecated
	public Map<String, List<String>> getStationDayDataNeedGroupResult(
			String dayStr, String station_id) throws IOException {

		// 注册查询的列
		NavigableSet<byte[]> cs = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
		// cs.add(filter);
		byte[] start_time = Bytes.toBytes("START_TIME");
		cs.add(start_time);
		byte[] enter = Bytes.toBytes("ENTER_TIMES");
		cs.add(enter);
		byte[] exit = Bytes.toBytes("EXIT_TIMES");
		cs.add(exit);

		byte[] cf = Bytes.toBytes("luxnew");
		Map<byte[], NavigableSet<byte[]>> cfAndCs = new HashMap<byte[], NavigableSet<byte[]>>();
		cfAndCs.put(cf, cs);

		// 注册 不需要返回的列
		// List<byte []> csFilter = new ArrayList<byte []>();
		// csFilter.add(filter);

		// 列值过滤
		// RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new
		// BinaryPrefixComparator(Bytes.toBytes( dayStr )));
		// SingleColumnValueFilter scvf = new
		// SingleColumnValueFilter(cf,filter,CompareOp.EQUAL,Bytes.toBytes(station_id));

		// List<Filter> filters = new ArrayList<Filter>();
		// filters.add(scvf);
		// filters.add(rowFilter);

		// FilterList filterList = new FilterList(Operator.MUST_PASS_ALL,
		// filters);

		return getStationDayDataByMoreThreadNeedGroupResult("dsgj", cfAndCs,
				null, cs, dayStr, station_id);
	}

	// 分配线程任务
	@Deprecated
	private Map<String, List<String>> getStationDayDataByMoreThreadNeedGroupResult(
			String table, Map<byte[], NavigableSet<byte[]>> cfAndCs,
			FilterList filterList, NavigableSet<byte[]> colsResturn,
			String dayStr, String station_id) throws IOException {

		// 分配每个线程负责的 rowkey的区域
		List<Map<String, String>> props = new ArrayList<Map<String, String>>();
		Map<String, String> prop = null;

		// 固定为4个线程 读取每天的数据 ，也就是将一天 24小时平均分成 4份
		int gap = 24 / 4;
		for (int i = 0; i < 4; i++) {

			prop = new HashMap<String, String>();
			prop.put(
					"startRow",
					station_id + "-" + dayStr
							+ NumberUtil.convetorNumToString(i * gap));
			prop.put(
					"stopRow",
					station_id + "-" + dayStr
							+ NumberUtil.convetorNumToString((i + 1) * gap));
			props.add(prop);
		}

		return hbaseActionService.execSearchByMoreThreadsNeedGroupResult("dsgj",
				cfAndCs, filterList, colsResturn, props);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.hbase.service.HbaseService#getStationDayLatestDataTopN(com.bdms
	 * .hbse.enums.SortKey, int, int)
	 */
	@Deprecated
	public List<Map<String, Object>> getStationDayLatestDataTopN(
			SortKey sortKey, int offset, int n) throws IOException {

		List<Map<String, Object>> latestData = new ArrayList<Map<String, Object>>();
		
		String latestDate = getStreamingGJLatestTimeStr();
		
		System.err.println(latestDate);

		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setStartRow(Bytes.toBytes("0101-" + latestDate ) );
		scan.setStopRow(Bytes.toBytes("3000-"  + latestDate));
		
		

		final byte[] luxnew = "luxnew".getBytes();

		final byte[] STATION_ID = "STATION_ID".getBytes();
		scan.addColumn(luxnew, STATION_ID);

		final byte[] START_TIME = "START_TIME".getBytes();
		scan.addColumn(luxnew, START_TIME);

		final byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		final byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();
		
		SingleColumnValueFilter scvf = new SingleColumnValueFilter(luxnew,START_TIME,CompareOp.EQUAL,Bytes.toBytes(latestDate));
		scan.setFilter(scvf);
		

		switch (sortKey) {

		case ENTER_TIMES:
			scan.addColumn(luxnew, ENTER_TIMES);
			latestData = hbaseTemplate.find(StreamingGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Object>>() {

						private Map<String, Object> map;

						public Map<String, Object> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Object>();
							map.put("STATION_ID",
									new String(result.getValue(luxnew,
											STATION_ID)));
							map.put("START_TIME",
									new String(result.getValue(luxnew,
											START_TIME)));
							map.put("ENTER_TIMES", Integer.parseInt(new String(
									result.getValue(luxnew, ENTER_TIMES))));
							return map;
						}
					});
			break;
		case EXIT_TIMES:
			scan.addColumn(luxnew, EXIT_TIMES);
			latestData = hbaseTemplate.find(StreamingGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Object>>() {

						private Map<String, Object> map;

						public Map<String, Object> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Object>();
							map.put("STATION_ID",
									new String(result.getValue(luxnew,
											STATION_ID)));
							map.put("START_TIME",
									new String(result.getValue(luxnew,
											START_TIME)));
							map.put("EXIT_TIMES", Integer.parseInt(new String(
									result.getValue(luxnew, EXIT_TIMES))));

							return map;
						}
					});
			break;
		case ENTER_EXIT_SUB:
			scan.addColumn(luxnew, ENTER_TIMES);
			scan.addColumn(luxnew, EXIT_TIMES);
			latestData = hbaseTemplate.find(StreamingGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Object>>() {

						private Map<String, Object> map;

						public Map<String, Object> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Object>();
							map.put("STATION_ID",
									new String(result.getValue(luxnew,
											STATION_ID)));
							map.put("START_TIME",
									new String(result.getValue(luxnew,
											START_TIME)));
							map.put("ENTER_EXIT_SUB",
									Integer.parseInt(new String(result
											.getValue(luxnew, ENTER_TIMES)))
											- Integer.parseInt(new String(
													result.getValue(luxnew,
															EXIT_TIMES))));

							return map;
						}
					});
			break;
		case ENTER_EXIT_SUM:
			scan.addColumn(luxnew, ENTER_TIMES);
			scan.addColumn(luxnew, EXIT_TIMES);
			latestData = hbaseTemplate.find(StreamingGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Object>>() {

						private Map<String, Object> map;

						public Map<String, Object> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Object>();
							map.put("STATION_ID",
									new String(result.getValue(luxnew,
											STATION_ID)));
							map.put("START_TIME",
									new String(result.getValue(luxnew,
											START_TIME)));
							map.put("ENTER_EXIT_SUM",
									Integer.parseInt(new String(result
											.getValue(luxnew, ENTER_TIMES)))
											+ Integer.parseInt(new String(
													result.getValue(luxnew,
															EXIT_TIMES))));

							return map;
						}
					});
			break;
		}

		List<Map<String, Object>> topN = getTopNByLatestData(latestData,
				sortKey, offset, n);
		latestData = null;

		return topN;

	}

	/* t(non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getStationDayLatestData()
	 */
	@Deprecated
	public List<Map<String, Object>> getStationDayLatestData() {
		
		List<Map<String, Object>> latestData = new ArrayList<Map<String, Object>>();

		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setStartRow(Bytes.toBytes("latest-0101"));
		scan.setStopRow(Bytes.toBytes("latest-2000"));

		final byte[] luxnew = "luxnew".getBytes();

		final byte[] STATION_ID = "STATION_ID".getBytes();
		scan.addColumn(luxnew, STATION_ID);

		final byte[] START_TIME = "START_TIME".getBytes();
		scan.addColumn(luxnew, START_TIME);

		final byte[] ENTER_TIMES = "ENTER_TIMES".getBytes();
		final byte[] EXIT_TIMES = "EXIT_TIMES".getBytes();

		scan.addColumn(luxnew, ENTER_TIMES);
		scan.addColumn(luxnew, EXIT_TIMES);
		latestData = hbaseTemplate.find(StreamingGJ.TABLENAME.getName(), scan,
				new RowMapper<Map<String, Object>>() {

					private Map<String, Object> map;

					public Map<String, Object> mapRow(Result result, int rowNum)
							throws Exception {

						map = new HashMap<String, Object>();
						map.put("STATION_ID",
								new String(result.getValue(luxnew, STATION_ID)));
						map.put("START_TIME",
								new String(result.getValue(luxnew, START_TIME)));
						map.put("ENTER_TIMES",
								new String(result.getValue(luxnew, ENTER_TIMES)));
						map.put("EXIT_TIMES",
								new String(result.getValue(luxnew, EXIT_TIMES)));
						return map;
					}
				});

		return latestData;
	}

	// 根据人数排序,并截取
	private List<Map<String, Object>> getTopNByLatestData(
			List<Map<String, Object>> latestData, SortKey sortKey, int offset,
			int n) {

		switch (sortKey) {
		case ENTER_TIMES:
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				@Override
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int) (o2.get("ENTER_TIMES"))
							- (int) (o1.get("ENTER_TIMES"));
				}

			});
			break;
		case EXIT_TIMES:
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				@Override
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int) (o2.get("EXIT_TIMES"))
							- (int) (o1.get("EXIT_TIMES"));
				}

			});
			break;
		case ENTER_EXIT_SUM:
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				@Override
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int) (o2.get("ENTER_EXIT_SUM"))
							- (int) (o1.get("ENTER_EXIT_SUM"));
				}

			});
			break;
		case ENTER_EXIT_SUB:
			Collections.sort(latestData, new Comparator<Map<String, Object>>() {

				@Override
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {

					return (int) (o2.get("ENTER_EXIT_SUB"))
							- (int) (o1.get("ENTER_EXIT_SUB"));
				}

			});
			break;

		default:
			LOG.error("未知错误");
			break;
		}
		int size = latestData.size();
		int toIndex = offset + n;
		
		if(size > 0 ){

			if (offset >= size) {
				offset = size - 1;
			}
	
			if (toIndex > size) {
	
				toIndex = size;
			}
		}else{
			return new ArrayList<Map<String, Object>>();
		}

		return latestData.subList(offset, toIndex);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdms.hbase.service.HbaseService#getDayODtopNData(int)
	 */
	public List<Map<String, Object>> getDayODtopNData(int offset, int n)
			throws IOException {

		List<Map<String, Object>> top100Data = new ArrayList<Map<String, Object>>();

		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setStartRow(Bytes.toBytes(String.format("%04d", offset)));
		scan.setStopRow(Bytes.toBytes(String.format("%04d", (offset + n))));

		final byte[] luxnew = HotODGJ.CF.getBytes();
		final byte[] OD = HotODGJ.OD.getBytes();
		scan.addColumn(luxnew, OD);

		final byte[] PASS_NUM = HotODGJ.PASS_NUM.getBytes();
		scan.addColumn(luxnew, PASS_NUM);

		top100Data = hbaseTemplate.find(HotODGJ.TABLENAME.getName(), scan,
				new RowMapper<Map<String, Object>>() {
					private Map<String, Object> map;
					private String[] od;

					public Map<String, Object> mapRow(Result result, int rowNum)
							throws Exception {
						map = new HashMap<String, Object>();
						od = new String(result.getValue(luxnew, OD)).split("-");
						map.put("O", od[0]);
						map.put("D", od[1]);
						map.put("PASS_NUM", Integer.parseInt(new String(result
								.getValue(luxnew, PASS_NUM))));
						return map;
					}
				});

		return top100Data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdms.hbase.service.HbaseService#getPredicteData(java.lang.String,
	 * java.lang.String, com.bdms.hbse.enums.ResultType)
	 */
	public List<Map<String, Integer>> getPredicteData(String dayStr,
			String station_id, ResultType type) throws IOException {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			calendar.setTime(simpleDateFormat.parse(dayStr.substring(0, 8)));
		} catch (ParseException e) {
			LOG.error("日期格式转换失败.", e);
		}
		String str = null;

		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n > 1 && n < 7) {
			str = "11111111"; // 工作日
		} else {
			str = "00000000"; // 周末
		}

		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setStartRow(Bytes.toBytes(station_id + "-" + str + "000000"));
		scan.setStopRow(Bytes.toBytes(station_id + "-" + str
				+ dayStr.substring(8)));

		final byte[] cf = HistoryPredicteGJ.CF.getBytes();
		final byte[] start_time = HistoryPredicteGJ.START_TIME.getBytes();
		final byte[] enter = HistoryPredicteGJ.ENTER_TIMES.getBytes();
		final byte[] exit = HistoryPredicteGJ.EXIT_TIMES.getBytes();

		scan.addColumn(cf, start_time);

		List<Map<String, Integer>> resData = new ArrayList<Map<String, Integer>>();

		switch (type) {

		case DAY_ENTER:

			scan.addColumn(cf, enter);

			resData = hbaseTemplate.find(HistoryPredicteGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Integer>>() {

						private Map<String, Integer> map;
						private String timeStr;

						public Map<String, Integer> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Integer>();
							timeStr = new String(result
									.getValue(cf, start_time)).substring(8, 10);
							if (timeStr.startsWith("0")) {
								map.put("hour",
										Integer.parseInt(timeStr.substring(1)));
							} else {
								map.put("hour", Integer.parseInt(timeStr));
							}
							map.put("count", Integer.parseInt(new String(result
									.getValue(cf, enter))));

							return map;
						}
					});

			;
			break;
		case DAY_EXIT:

			scan.addColumn(cf, exit);

			resData = hbaseTemplate.find(HistoryPredicteGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Integer>>() {

						private Map<String, Integer> map;
						private String timeStr;

						public Map<String, Integer> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Integer>();
							timeStr = new String(result
									.getValue(cf, start_time)).substring(8, 10);

							if (timeStr.startsWith("0")) {
								map.put("hour",
										Integer.parseInt(timeStr.substring(1)));
							} else {
								map.put("hour", Integer.parseInt(timeStr));
							}
							map.put("count", Integer.parseInt(new String(result
									.getValue(cf, exit))));

							return map;
						}
					});
			break;
		case DAY_SUM:

			scan.addColumn(cf, enter);
			scan.addColumn(cf, exit);

			resData = hbaseTemplate.find(HistoryPredicteGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Integer>>() {

						private Map<String, Integer> map;
						private String timeStr;

						public Map<String, Integer> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Integer>();
							timeStr = new String(result
									.getValue(cf, start_time)).substring(8, 10);

							if (timeStr.startsWith("0")) {
								map.put("hour",
										Integer.parseInt(timeStr.substring(1)));
							} else {
								map.put("hour", Integer.parseInt(timeStr));
							}

							map.put("count",
									Integer.parseInt(new String(result
											.getValue(cf, exit)))
											+ Integer
													.parseInt(new String(
															result.getValue(cf,
																	enter))));

							return map;
						}
					});
			break;

		case DAY_SUB:

			scan.addColumn(cf, enter);
			scan.addColumn(cf, exit);

			resData = hbaseTemplate.find(HistoryPredicteGJ.TABLENAME.getName(), scan,
					new RowMapper<Map<String, Integer>>() {

						private Map<String, Integer> map;
						private String timeStr;

						public Map<String, Integer> mapRow(Result result,
								int rowNum) throws Exception {

							map = new HashMap<String, Integer>();
							timeStr = new String(result
									.getValue(cf, start_time)).substring(8, 10);

							if (timeStr.startsWith("0")) {
								map.put("hour",
										Integer.parseInt(timeStr.substring(1)));
							} else {
								map.put("hour", Integer.parseInt(timeStr));
							}

							map.put("count",
									Integer.parseInt(new String(result
											.getValue(cf, enter)))
											- Integer.parseInt(new String(
													result.getValue(cf, exit))));

							return map;
						}
					});
			break;

		default:
			LOG.error("获取数据的类型设置错误");
			break;
		}
		return resData;

	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#storeImgMetaData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Deprecated
	public void storeImgMetaData(String timeStamp, String cameraId,
			String peopleNum, String densityLevel, String densityImage,
			String groupNum, String groupImage, String warnLevel,
			String wrnImage,String reserved) {
		
		
		boolean  pass =  checkImgMetaData(timeStamp, cameraId, peopleNum, densityLevel,
				densityImage, groupNum, groupImage, warnLevel, wrnImage);
		
		if(StringUtils.isBlank(reserved)){
					
					reserved = "";
				}
		
		
		if( pass ){
			
			Put row = ImgHTableMeta.createImgPut(timeStamp, cameraId, peopleNum,
					densityLevel, densityImage, groupNum, groupImage,
					warnLevel, wrnImage,reserved);
			
			try {
				hbaseActionService.storeDataToHbase(ImgHTableMeta.TABLENAME.getName(), row);
			} catch (IOException e) {
				LOG.error("记录存储失败。",e);
			}
			
		}
		
	}


	/**
	  * description: 检查传入参数的合法性
	  * @return
	  * boolean
	  * 2015-8-20 下午3:07:17
	  * by Lixc
	 */
	@Deprecated
	private boolean checkImgMetaData(String timeStamp, String cameraId,
			String peopleNum, String densityLevel, String densityImage,
			String groupNum, String groupImage, String warnLevel,
			String wrnImage) {
		
		boolean pass = true;
		
		if(StringUtils.isBlank(timeStamp)){
			LOG.error("timeStamp 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(cameraId)){
			LOG.error("cameraId 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(peopleNum)){
			LOG.error("peopleNum 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(densityLevel)){
			LOG.error("densityLevel 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(densityImage)){
			LOG.error("densityImage 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(groupNum)){
			LOG.error("groupNum 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(groupImage)){
			LOG.error("groupImage 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(warnLevel)){
			LOG.error("warnLevel 不能为空!");
			pass = false;
		}
		
		if(StringUtils.isBlank(wrnImage)){
			LOG.error("wrnImage 不能为空!");
			pass = false;
		}
		
		
		return pass;
	}

	
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#storeImgMetaData(java.util.List)
	 */
	@Deprecated
	public void storeImgMetaData(List<Map<String, String>> listData) {
		
		List<Put> rows = new ArrayList<Put>();
		
		Put row = null;
		for( Map<String, String> map : listData ){
			
			row = ImgHTableMeta.createImgPut(map.get(ImgHTableMeta.TIMESTAMP.getName()), map.get(ImgHTableMeta.CAMERAID.getName()), map.get(ImgHTableMeta.PEOPLENUM.getName()),
					map.get(ImgHTableMeta.DENSITYLEVEL.getName()), map.get(ImgHTableMeta.DENSITYIMAGE.getName()), map.get(ImgHTableMeta.GROUPNUM.getName()), map.get(ImgHTableMeta.GROUPIMAGE.getName()),
					map.get(ImgHTableMeta.WARNLEVEL.getName()), map.get(ImgHTableMeta.WARNIMAGE.getName()),map.get(ImgHTableMeta.RESERVED.getName()));
			
			
			rows.add(row);
			
		}
		
		try {
			hbaseActionService.storeDataToHbase(ImgHTableMeta.TABLENAME.getName(), rows);
		} catch (IOException e) {
			LOG.error("批量存储数据失败。",e);
		}
		
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getLatestImgMeta()
	 */
	public List<Map<String, String>> getLatestImgMeta( Collection<String> ids  ) throws IOException {
	
		
		 List<byte[]> columnToBack = Arrays.asList( ImgHTableMeta.CAMERAID.getBytes(),
				 								   ImgHTableMeta.TIMESTAMP.getBytes(),
												   ImgHTableMeta.PEOPLENUM.getBytes(),
												   ImgHTableMeta.DENSITYIMAGE.getBytes(),
												   ImgHTableMeta.DENSITYLEVEL.getBytes(),
												   ImgHTableMeta.GROUPIMAGE.getBytes(),
												   ImgHTableMeta.GROUPNUM.getBytes(),
												   ImgHTableMeta.WARNIMAGE.getBytes(),
												   ImgHTableMeta.WARNLEVEL.getBytes()
												  );
		 
		
		String timeStr = null; 
		String rowKey = null;
		
		 byte[] cf = ImgHTableMeta.CF.getBytes();
		
		 List<Get> gets = new ArrayList<Get>();
		 Get get = null;
		
		for( String id : ids){
			
			timeStr = getImgLatestTimeStr(id);
			rowKey = id + "-" + timeStr;
			
			get = new Get(rowKey.getBytes());
			get.setMaxVersions(1);
			for( byte[] cn : columnToBack ){
				get.addColumn(cf, cn);
			}
			
			gets.add(get);
			
		}

		return hbaseActionService.searchResultByGets(ImgHTableMeta.TABLENAME.getName(), gets);
	}

	private Map<String, Object> doSearchImgLatestDataById( byte[] start,byte[] stop ) {
		
		List<Map<String, Object>> latest;
		Scan scan = new Scan();
		scan.setReversed(true);
		scan.setCaching(1);
		scan.setStartRow(stop);
		scan.setStopRow(start);
		
		Filter filter = new PageFilter(1L);
		scan.setFilter(filter);
		
		scan.setMaxResultSize(1L);

		final byte[] cf           = ImgHTableMeta.CF.getBytes();
		final byte[] timeStamp    = ImgHTableMeta.TIMESTAMP.getBytes();
		final byte[] cameriaId    = ImgHTableMeta.CAMERAID.getBytes();
		final byte[] peopleNum    = ImgHTableMeta.PEOPLENUM.getBytes();
		final byte[] densityLevel = ImgHTableMeta.DENSITYLEVEL.getBytes();
		final byte[] densityImage = ImgHTableMeta.DENSITYIMAGE.getBytes();
		final byte[] groupNum     = ImgHTableMeta.GROUPNUM.getBytes();
		final byte[] groupImage   = ImgHTableMeta.GROUPIMAGE.getBytes();
		final byte[] warnLevel    = ImgHTableMeta.WARNLEVEL.getBytes();
		final byte[] warnImage    = ImgHTableMeta.WARNIMAGE.getBytes();
		
		scan.addColumn(cf, timeStamp);
		scan.addColumn(cf, cameriaId);
		scan.addColumn(cf, peopleNum);
		scan.addColumn(cf, densityLevel);
		scan.addColumn(cf, densityImage);
		scan.addColumn(cf, groupNum);
		scan.addColumn(cf, groupImage);
		scan.addColumn(cf, warnLevel);
		scan.addColumn(cf, warnImage);
		

		latest = hbaseTemplate.find(ImgHTableMeta.TABLENAME.getName(), scan,
				
							new RowMapper<Map<String, Object>>() {
			
								private Map<String, Object> map;
			
								public Map<String, Object> mapRow(Result result, int rowNum)
										throws Exception {
									map = new HashMap<String, Object>();
									map.put(ImgHTableMeta.TIMESTAMP.getName(), new String(result.getValue(cf, timeStamp)));
									map.put(ImgHTableMeta.CAMERAID.getName(), new String(result.getValue(cf,cameriaId )));
									map.put(ImgHTableMeta.PEOPLENUM.getName(), new String(result.getValue(cf, peopleNum)));
									map.put(ImgHTableMeta.DENSITYLEVEL.getName(), new String(result.getValue(cf, densityLevel)));
									map.put(ImgHTableMeta.DENSITYIMAGE.getName(), new String(result.getValue(cf, densityImage)));
									map.put(ImgHTableMeta.GROUPNUM.getName(), new String(result.getValue(cf, groupNum)));
									map.put(ImgHTableMeta.GROUPIMAGE.getName(), new String(result.getValue(cf, groupImage)));
									map.put(ImgHTableMeta.WARNLEVEL.getName(), new String(result.getValue(cf, warnLevel)));
									map.put(ImgHTableMeta.WARNIMAGE.getName(), new String(result.getValue(cf, warnImage)));
									return map;
								}
						});
		
		
		if(latest.isEmpty()){
			
			return null;
		}
		
		return latest.get(0);
	}
	
	//获取最新数据的时间chuan
	public String getStreamingGJLatestTimeStr(){
		
		Scan scan = new Scan();
		scan.setCaching(1);
		scan.setReversed(true);
		scan.setMaxResultSize(1L);
		
		Filter filter = new PageFilter(1L);
		scan.setFilter(filter);
		
		String classicsColumnValue = StreamingGJ.getClassicsColumnValue();
		
		scan.setStopRow(Bytes.toBytes(classicsColumnValue + "-2") );
		scan.setStartRow(Bytes.toBytes(classicsColumnValue + "-3" ));
		final byte[] luxnew = StreamingGJ.CF.getBytes();
		final byte[] START_TIME = StreamingGJ.START_TIME.getBytes();
		scan.addColumn(luxnew, START_TIME);
		
		String str = null;
		try {
			str = hbaseActionService.getFirstResult( StreamingGJ.TABLENAME.getName(),scan,luxnew,StreamingGJ.START_TIME.getName());
		} catch (IOException e) {
			LOG.error("查询最新轨交数据时间失败。", e);
		}
		
		 if(str == null ){
			 LOG.warn( "hbase中表 " + StreamingGJ.TABLENAME.getName() + "内容为空." );
			 return DSHbaseDateUtil.getHtimeStreing(System.currentTimeMillis());
		 }
		 
		return str;
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getAllGJLatestData(java.util.Set, com.bdms.hbse.enums.SortKey)
	 */
	public List<Map<String, String>> getAllGJLatestData(Set<String> stationIds,SortKey sortKey){
		
		String streamingLatestTimeStr = getStreamingGJLatestTimeStr();
		List<Get>  gets = new ArrayList<Get>();
		
		List<byte[]> columBytes = null;
		
		switch (sortKey) {
		
			case ENTER_TIMES:
				//colums = Arrays.asList(StreamingGJ.STATION_ID.getName(),StreamingGJ.START_TIME.getName(),StreamingGJ.ENTER_TIMES.getName());
				columBytes = Arrays.asList(StreamingGJ.STATION_ID.getBytes(),StreamingGJ.START_TIME.getBytes(),StreamingGJ.ENTER_TIMES.getBytes());
				break;
	
			case EXIT_TIMES:
				//colums = Arrays.asList(StreamingGJ.STATION_ID.getName(),StreamingGJ.START_TIME.getName(),StreamingGJ.EXIT_TIMES.getName());
				columBytes = Arrays.asList(StreamingGJ.STATION_ID.getBytes(),StreamingGJ.START_TIME.getBytes(),StreamingGJ.EXIT_TIMES.getBytes());
				break;
				
			case ENTER_EXIT_SUM:
				//colums = Arrays.asList(StreamingGJ.STATION_ID.getName(),StreamingGJ.START_TIME.getName(),StreamingGJ.ENTER_TIMES.getName(),StreamingGJ.EXIT_TIMES.getName());
				columBytes = Arrays.asList(StreamingGJ.STATION_ID.getBytes(),StreamingGJ.START_TIME.getBytes(),StreamingGJ.ENTER_TIMES.getBytes(),StreamingGJ.EXIT_TIMES.getBytes());
				break;
				
			case ENTER_EXIT_SUB:
				//colums = Arrays.asList(StreamingGJ.STATION_ID.getName(),StreamingGJ.START_TIME.getName(),StreamingGJ.ENTER_TIMES.getName(),StreamingGJ.EXIT_TIMES.getName());
				columBytes = Arrays.asList(StreamingGJ.STATION_ID.getBytes(),StreamingGJ.START_TIME.getBytes(),StreamingGJ.ENTER_TIMES.getBytes(),StreamingGJ.EXIT_TIMES.getBytes());
				break;
			default:
				LOG.error("配置新数据查询信息失败。");
				break;
		}
		
		byte[] cf = StreamingGJ.CF.getBytes();
		
		Get get = null;
		for(String id : stationIds){
			
			if(!id.startsWith("00")){
				get = new Get(Bytes.toBytes(id + "-" + streamingLatestTimeStr));
				for(byte[] q : columBytes){
					get.addColumn(cf, q);
				}
				gets.add(get);
			}
		}
		
		List<Map<String, String>> res_tmp = new ArrayList<Map<String, String>>();
		try {
			res_tmp = hbaseActionService.searchResultByGets(StreamingGJ.TABLENAME.getName(), gets);
		} catch (IOException e) {
			LOG.error("查询最新数据失败。", e);
		}
		
		return res_tmp;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getGJLatestData(java.util.Set, com.bdms.hbse.enums.SortKey)
	 */
	@Override
	public List<Map<String, Object>> getGJLatestDataForACC(Set<String> stationIds,SortKey sortKey) {
		
		
		
		List<Map<String,Object>>  res = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		List<Map<String, String>> res_tmp = getAllGJLatestData( stationIds,sortKey);
		
		//类型转换
		switch (sortKey) {
		
			case ENTER_TIMES:
				
				for( Map<String, String> m : res_tmp ){
					map = new HashMap<String,Object>();
					map.put(StreamingGJ.STATION_ID.getName(), m.get(StreamingGJ.STATION_ID.getName()));
					map.put(StreamingGJ.START_TIME.getName(), m.get(StreamingGJ.START_TIME.getName()));
					map.put(StreamingGJ.ENTER_TIMES.getName(), Integer.parseInt(m.get(StreamingGJ.ENTER_TIMES.getName())));
					res.add(map);
				}
				
				break;
	
			case EXIT_TIMES:
				
					for( Map<String, String> m : res_tmp ){
						map = new HashMap<String,Object>();
						map.put(StreamingGJ.STATION_ID.getName(), m.get(StreamingGJ.STATION_ID.getName()));
						map.put(StreamingGJ.START_TIME.getName(), m.get(StreamingGJ.START_TIME.getName()));
						map.put(StreamingGJ.EXIT_TIMES.getName(), Integer.parseInt(m.get(StreamingGJ.EXIT_TIMES.getName())));
						res.add(map);
					}
				break;
				
			case ENTER_EXIT_SUM:
				
					for( Map<String, String> m : res_tmp ){
						map = new HashMap<String,Object>();
						map.put(StreamingGJ.STATION_ID.getName(), m.get(StreamingGJ.STATION_ID.getName()));
						map.put(StreamingGJ.START_TIME.getName(), m.get(StreamingGJ.START_TIME.getName()));
						map.put("ENTER_EXIT_SUM", Integer.parseInt(m.get(StreamingGJ.ENTER_TIMES.getName())) + Integer.parseInt(m.get(StreamingGJ.EXIT_TIMES.getName())));
						res.add(map);
					}
				break;
				
			case ENTER_EXIT_SUB:
				
					for( Map<String, String> m : res_tmp ){
						map = new HashMap<String,Object>();
						map.put(StreamingGJ.STATION_ID.getName(), m.get(StreamingGJ.STATION_ID.getName()));
						map.put(StreamingGJ.START_TIME.getName(), m.get(StreamingGJ.START_TIME.getName()));
						map.put("ENTER_EXIT_SUB", Integer.parseInt(m.get(StreamingGJ.ENTER_TIMES.getName())) - Integer.parseInt(m.get(StreamingGJ.EXIT_TIMES.getName())));
						res.add(map);
					}
				break;
			default:
				LOG.error("配置新数据查询信息失败。");
				break;
		}
		
		
		
		return res;
	}

	public String getDZWLLatestTimeStr2(String qym) {

		Scan scan = new Scan();
		scan.setCaching(1);
		scan.setReversed(true);
		scan.setMaxResultSize(1L);
		
		Filter filter = new PageFilter(1L);
		scan.setFilter(filter);
		
		//String qym = StreamingDZWL.getClassicsColumnValue();
		
		scan.setStopRow(Bytes.toBytes(qym + "-2"));
		scan.setStartRow(Bytes.toBytes( qym + "-3"));
		
		final byte[] cf = StreamingDZWL.CF.getBytes();
		final byte[] sj = StreamingDZWL.SJ.getBytes();
		
		scan.addColumn(cf, sj);
	
		String str = null;
		try {
			str = hbaseActionService.getFirstResult( StreamingDZWL.TABLENAME.getName(),scan,cf,StreamingDZWL.SJ.getName());
		} catch (IOException e) {
			LOG.error("查询最新电子围栏数据时间失败。", e);
		}
		
		 if( str == null ){
			 LOG.warn( "hbase中表 " + StreamingDZWL.TABLENAME.getName() + "内容为空." );
			 return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		 }
		 
		
		 return str;
		
		
	}
	
	
	public String getDZWLLatestTimeStr() {

		Scan scan = new Scan();
		scan.setCaching(1);
		scan.setReversed(true);
		scan.setMaxResultSize(1L);
		
		Filter filter = new PageFilter(1L);
		scan.setFilter(filter);
		
		String qym = StreamingDZWL.getClassicsColumnValue();
		
		LOG.info("qym的值："+qym);
		
		scan.setStopRow(Bytes.toBytes(qym + "-2"));
		scan.setStartRow(Bytes.toBytes( qym + "-3"));
		
		final byte[] cf = StreamingDZWL.CF.getBytes();
		final byte[] sj = StreamingDZWL.SJ.getBytes();
		
		scan.addColumn(cf, sj);
	
		String str = null;
		try {
			str = hbaseActionService.getFirstResult( StreamingDZWL.TABLENAME.getName(),scan,cf,StreamingDZWL.SJ.getName());
		} catch (IOException e) {
			LOG.error("查询最新电子围栏数据时间失败。", e);
		}
		
		 if( str == null ){
			 LOG.warn( "hbase中表 " + StreamingDZWL.TABLENAME.getName() + "内容为空." );
			 return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		 }
		 
		
		 return str;
		
		
	}

	
	private String getImgLatestTimeStr(String cameraId){
		
		if( cameraId == null ){
		
			Get get = new Get(Bytes.toBytes(ImgHTableMeta.getClassicsColumnValue()));
			byte[] cf = ImgHTableMeta.CF.getBytes();
			get.addColumn(cf, ImgHTableMeta.TIMESTAMP.getBytes());
			  
			String name = ImgHTableMeta.TIMESTAMP.getName();
			Map<String, String> searchResultByGet = new HashMap<String,String>();
			try {
				searchResultByGet = hbaseActionService.searchResultByGet(ImgHTableMeta.TABLENAME.getName(), get);
			} catch (IOException e) {
				LOG.error("获取最新的视频时间失败",e);
			}
			
			return  searchResultByGet.get(name);
			
		}else{
			
			
			Scan scan = new Scan();
			scan.setCaching(1);
			scan.setReversed(true);
			scan.setMaxResultSize(1L);
			
			Filter filter = new PageFilter(1L);
			scan.setFilter(filter);
			
			scan.setStopRow(Bytes.toBytes(cameraId + "-2"));
			scan.setStartRow(Bytes.toBytes( cameraId + "-3"));
			
			final byte[] cf = ImgHTableMeta.CF.getBytes();
			final byte[] sj = ImgHTableMeta.TIMESTAMP.getBytes();
			
			scan.addColumn(cf, sj);
		
			String str = null;
			try {
				str = hbaseActionService.getFirstResult( ImgHTableMeta.TABLENAME.getName(),scan,cf,ImgHTableMeta.TIMESTAMP.getName());
			} catch (IOException e) {
				LOG.error("查询最视频数据时间失败。", e);
			}
			
			 if( str == null ){
				 LOG.warn( "hbase中表 " + ImgHTableMeta.TABLENAME.getName() + "内容为空." );
				 return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			 }
			 
			 return str;
			
		}
		
		
	}
	
	private String getWifiLatestTimeStr(String sid){
		
		if( sid == null ){
		
			Get get = new Get(Bytes.toBytes(WifiTableMeta.getClassicsColumnValue()));
			byte[] cf = WifiTableMeta.CF.getBytes();
			get.addColumn(cf, WifiTableMeta.TIME.getBytes());
			  
			String name = WifiTableMeta.TIME.getName();
			Map<String, String> searchResultByGet = new HashMap<String,String>();
			try {
				searchResultByGet = hbaseActionService.searchResultByGet(WifiTableMeta.TABLENAME.getName(), get);
			} catch (IOException e) {
				LOG.error("获取最新的视频时间失败",e);
			}
			
			return  searchResultByGet.get(name);
			
		}else{
			
			
			Scan scan = new Scan();
			scan.setCaching(1);
			scan.setReversed(true);
			scan.setMaxResultSize(1L);
			
			Filter filter = new PageFilter(1L);
			scan.setFilter(filter);
			
			scan.setStopRow(Bytes.toBytes(sid + "-2"));
			scan.setStartRow(Bytes.toBytes( sid + "-3"));
			
			final byte[] cf = WifiTableMeta.CF.getBytes();
			final byte[] sj = WifiTableMeta.TIME.getBytes();
			
			scan.addColumn(cf, sj);
		
			String str = null;
			try {
				str = hbaseActionService.getFirstResult( WifiTableMeta.TABLENAME.getName(),scan,cf,WifiTableMeta.TIME.getName());
			} catch (IOException e) {
				LOG.error("查询最视频数据时间失败。", e);
			}
			
			 if( str == null ){
				 LOG.warn( "hbase中表 " + WifiTableMeta.TABLENAME.getName() + "内容为空." );
				 return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			 }
			 
			 return str;
			
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getDZWLDayDataByName(java.lang.String, java.util.List)
	 */
	public  List<Map<String,String>> getDZWLDayDataByName(String qym,final List<StreamingDZWL> columsToBack,String dtime){
		
		String dzwlLatestTimeStr = "";
		String table = "";
		if(dtime==null || dtime.equals("")){
			dzwlLatestTimeStr = getDZWLLatestTimeStr();
			table = StreamingDZWL.TABLENAME.getName();
		}else{
			dzwlLatestTimeStr = dtime;
			table = StreamingDZWL.TABLENAMEHIS.getName();
		}
		
		LOG.info("getDZWLLatestTimeStr的日期是"+dzwlLatestTimeStr);
		Scan scan = new Scan();
		scan.setCaching(1000);
		
		scan.setStartRow(Bytes.toBytes(qym + "-" + dzwlLatestTimeStr.split(" ")[0] + " 00:00" ));
		scan.setStopRow(Bytes.toBytes( qym + "-" + dzwlLatestTimeStr + "1"));
		
		final byte[] cf = StreamingDZWL.CF.getBytes();
		
		for( StreamingDZWL cl : columsToBack ){
			scan.addColumn(cf, cl.getBytes());
		}
		
		 List<Map<String,String>> latestData = hbaseTemplate.find(table, scan,
				 
				   new RowMapper<Map<String,String>>() {
			      
			 		private Map<String,String> row ;
			 		private byte[] value;

					public Map<String,String> mapRow(Result result, int rowNum)
							throws Exception {
						
						row = new HashMap<String,String>();
						
						for( StreamingDZWL cl : columsToBack ){
							
							value = result.getValue(cf, cl.getBytes());
							if(value != null ){
								row.put(cl.getName(), new String(value));
							}
						}

						return row;
					}
				});
		 
		
		 return latestData;
		 
	}

	
	public  List<Map<String,String>> getDZWLDayData(Collection<String> qyms,final List<StreamingDZWL> columsToBack){
			
		//LOG.info("最新时间：dzwlLatestTimeStr："+dzwlLatestTimeStr);
        List<Get>  gets = new ArrayList<Get>();
		
		
		byte[] cf = StreamingDZWL.CF.getBytes();
		
		Get get = null;
		for(String id : qyms){
			
			String dzwlLatestTimeStr = getDZWLLatestTimeStr2(id);
			
			get = new Get(Bytes.toBytes(id + "-" + dzwlLatestTimeStr));
			for(StreamingDZWL q : columsToBack){
				get.addColumn(cf, q.getBytes());
			}
			gets.add(get);
		}
		
		List<Map<String, String>> res_tmp = new ArrayList<Map<String, String>>();
		try {
			res_tmp = hbaseActionService.searchResultByGets(StreamingDZWL.TABLENAME.getName(), gets);
		} catch (IOException e) {
			LOG.error("查询最新数据失败。", e);
		}
		
		return res_tmp;
		 
	}

	

	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getHistoryDZWLDataByName(java.lang.String, java.util.List)
	 */
	public List<Map<String, String>> getHistoryDZWLDataByName(String qym,
			final List<HistoryPredicteDZWL> columsToBack) {
		
		return historyDZWLSearch(qym, columsToBack,Bytes.toBytes(qym + "-11111111-"  + "00:00" ),Bytes.toBytes( qym + "-" + "23:59"));
	}
	//根据时间判断是工作日or周末
	public List<Map<String, String>> getHistoryDZWLDataByName(String qym,
			final List<HistoryPredicteDZWL> columsToBack,String dayStr) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(simpleDateFormat.parse(dayStr));
		} catch (ParseException e) {
			LOG.error("日期格式转换失败.", e);
		}
		String str = null;
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n > 1 && n < 7) {
			str = "11111111"; // 工作日
		} else {
			str = "00000000"; // 周末
		}
		LOG.info("logstris:"+str+"----"+qym + "-" + str + "-"  + "00:00");
		return historyDZWLSearch(qym, columsToBack,Bytes.toBytes(qym + "-" + str + "-"  + "00:00" ),Bytes.toBytes( qym + "-" + str + "-"   + "23:59"));
	}
	

	public List<Map<String,String>> getDZWLHistorySubData(String qym,final List<HistoryPredicteDZWL> columsToBack,String date){
			
		List<Map<String, String>> listOut = new ArrayList<>();
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(simpleDateFormat.parse(date.split(" ")[0]));
		} catch (ParseException e) {
			LOG.error("日期格式转换失败.", e);
		}
		String str = null;
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n > 1 && n < 7) {
			str = "11111111"; // 工作日
		} else {
			str = "00000000"; // 周末
		}
		
		List<Map<String, String>> list = getHistoryDZWLDataByName(qym,columsToBack);
		String last1sttimestr="";
		String last2ndtimestr="";
		if(list!=null && list.size()>1){
			last1sttimestr=list.get(list.size()-1).get("sj").split(" ")[1];
			last2ndtimestr=list.get(list.size()-2).get("sj").split(" ")[1];
		}
		
		LOG.info("last1sttimestr&last2ndtimestrIs:"+last1sttimestr+"--"+last2ndtimestr+"--"+date);
						
		byte[]startRow = null;
		byte[] stopRow = null;
		if(last1sttimestr.equals(date)){
			
			listOut.add(list.get(0));
			listOut.add(list.get(list.size()-1));

		}else{
			
			startRow = (qym + "-"+str+ "-" + date).getBytes();	
			stopRow = (qym + "-"+str+ "-" + "23:59").getBytes();
			LOG.info(startRow.toString()+"==2=="+stopRow.toString());
			List<Map<String, String>> list1 = historyDZWLSearch(qym, columsToBack,startRow,stopRow);
			
			listOut.add(list1.get(0));
			listOut.add(list1.get(1));
		}
		
		return listOut;
	}
	
	private List<Map<String, String>> historyDZWLSearch(String qym,
			final List<HistoryPredicteDZWL> columsToBack,byte[] startRow,byte[] stopRow) {
		
		Scan scan = new Scan();
		scan.setCaching(1000);
		
		scan.setStartRow(startRow);
		scan.setStopRow(stopRow);
		
		final byte[] cf = HistoryPredicteDZWL.CF.getBytes();
		
		for( HistoryPredicteDZWL cl : columsToBack ){
			scan.addColumn(cf, cl.getBytes());
		}
		
		 List<Map<String,String>> historeyData = hbaseTemplate.find(HistoryPredicteDZWL.TABLENAME.getName(), scan,
				 
				   new RowMapper<Map<String,String>>() {
			      
			 		private Map<String,String> row ;

					public Map<String,String> mapRow(Result result, int rowNum)
							throws Exception {
						
						row = new HashMap<String,String>();
						
						for( HistoryPredicteDZWL cl : columsToBack ){
							
							row.put(cl.getName(), new String(result.getValue(cf, cl.getBytes())));
						}

						return row;
					}
				});
		 
		 
		 return historeyData;
	}
	
	
	public Map<String, String> getHistoryDZWLDataByTimeStrAndName(String timeStr,String qym,
			final List<HistoryPredicteDZWL> columsToBack){
		
		LOG.info("testtimeStris:"+timeStr);
		

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(simpleDateFormat.parse(timeStr.split(" ")[0]));
		} catch (ParseException e) {
			LOG.error("日期格式转换失败.", e);
		}
		String str = null;
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n > 1 && n < 7) {
			str = "11111111"; // 工作日
		} else {
			str = "00000000"; // 周末
		}
		
		
		List<Map<String, String>> historyDZWLSearch = historyDZWLSearch(qym, columsToBack,Bytes.toBytes(qym + "-"+str+"-" + timeStr.split(" ")[1] ),
						Bytes.toBytes( qym + "-"+str+"-" + timeStr.split(" ")[1] + "1"));
		
		if( historyDZWLSearch.isEmpty() ){
			
			return new HashMap<String,String>();
		}
		
		return historyDZWLSearch.get(0);
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getMetroDataByStationId(java.lang.String, java.util.List)
	 */
	public Map<String, String> getMetroDataByStationId(String stationId,
			List<StreamingGJ> columsToBack) {
		//获取最新的时间
		 String timeStr = getStreamingGJLatestTimeStr();
		 //拼出行健
		  byte[] row = Bytes.toBytes( stationId  + "-" + timeStr );
		  //查询方式
		  Get get = new Get(row);
		  //获取列族名
		  byte[] cf = StreamingGJ.CF.getBytes();
		  
		 for( StreamingGJ cl : columsToBack ){
			 get.addColumn(cf, cl.getBytes());
			 
		 }
		 Map<String, String> searchResultByGet=null ;
		try {
			
			  searchResultByGet = hbaseActionService.searchResultByGet(StreamingGJ.TABLENAME.getName(), get);
		
		} catch (IOException e) {
			LOG.error("获取站点 " + stationId + "的数据失败", e);
		}
		return searchResultByGet;
		 
		
	}
	
	
	@Override
	public List<Map<String, String>> getMetroData(List<StreamingGJ> columsToBack) {
		//获取最新的时间
		 String timeStr = getStreamingGJLatestTimeStr();
		 //拼出行健
		  byte[] row = Bytes.toBytes( StreamingGJ.STATION_ID  + "-" + timeStr );
		  //查询方式
		  List<Get> gets = new ArrayList<Get>();
		  //获取列族名
		  byte[] cf = StreamingGJ.CF.getBytes();
		  
		  List<String> cls = new ArrayList<String>();
		  
		 for( StreamingGJ cl : columsToBack ){
			 ((Get) gets).addColumn(cf, cl.getBytes());
			 cls.add(cl.getName());
		 }
		 List<Map<String, String>> searchResultByGets=null ;
		try {
			
			  searchResultByGets = hbaseActionService.searchResultByGets(StreamingGJ.TABLENAME.getName(), gets);
		
		} catch (IOException e) {
			LOG.error("获取数据失败.", e);
		}
		return searchResultByGets;
		 
		
	}
	
	
	     //获取一行id
	public Map<String, String> getVideoDataByVideoId(String videoId,
				List<byte[]> columsToBack) throws IOException {
			//获取最新的时间
			 String timeStr = getImgLatestTimeStr(videoId);
			 String rowKey = videoId + "-" + timeStr;
			 
			return  findDataByRowKey( ImgHTableMeta.TABLENAME.getName(),rowKey.getBytes(), ImgHTableMeta.CF.getBytes(),columsToBack );
			 
			
		}
	
	
	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#getVideoDayDataByID(java.lang.String, java.util.List)
	 */
	public List<Map<String, Object>> getVideoDayDataByID(String cameraId, List<ImgHTableMeta> cns){
		
		//获取最新的时间
		 String timeStr = getImgLatestTimeStr(cameraId).substring(0,8);
		
		List<Map<String, Object>> latest = new ArrayList<Map<String, Object>>();
		Scan scan = new Scan();
		scan.setCaching(10000);
		scan.setMaxVersions(1);
		
		String start = cameraId + "-" + timeStr + "000000";
		String end   = cameraId + "-" + timeStr + "240000";
		
		scan.setStartRow(start.getBytes());
		scan.setStopRow(end.getBytes());
		
		
		byte[] cf           = ImgHTableMeta.CF.getBytes();
	
		for( ImgHTableMeta cn : cns ){
			
			scan.addColumn(cf, cn.getBytes());
		}
		
		

		latest = hbaseTemplate.find(ImgHTableMeta.TABLENAME.getName(), scan,
				
							new RowMapper<Map<String, Object>>() {
			
								private Map<String, Object> map;
			
								public Map<String, Object> mapRow(Result result, int rowNum)
										throws Exception {
									
									map = new HashMap<String, Object>();
									
									 for (Cell kv : result.rawCells()) {
							                
							                map.put( Bytes.toString(kv.getQualifierArray(),kv.getQualifierOffset(),kv.getQualifierLength()),  Bytes.toString(kv.getValueArray(),kv.getValueOffset(),kv.getValueLength()));
							            }
									
								
									return map;
								}
						});
		
		
		
			return latest  ;
		
	}
		

	/* (non-Javadoc)
	 * @see com.bdms.hbase.service.HbaseService#findDataByRowKey(java.lang.String, byte[], byte[], java.util.List)
	 */
	public Map<String,String> findDataByRowKey(String tableName,byte[] rowkey,byte[] cf,List<byte[]> cns) throws IOException{
		
		Get get = new Get(rowkey);
		
		for( byte[] cn : cns ){
			get.addColumn(cf, cn);
		}
		
		get.setMaxVersions(1);
		
		return hbaseActionService.searchResultByGet(tableName, get);
		
		
	}

	@Override
	public List<Map<String, Object>> getWifiDayDataByID(String sid,
			List<WifiTableMeta> cns) {
		//获取最新的时间
		 String timeStr = getWifiLatestTimeStr(sid).substring(0,8);
//		 String timeStr = "20150914";
		
		List<Map<String, Object>> latest = new ArrayList<Map<String, Object>>();
		Scan scan = new Scan();
		scan.setCaching(10000);
		scan.setMaxVersions(1);
		
		String start = sid + "-" + timeStr + "000000000";
		String end   = sid + "-" + timeStr + "240000000";
		
		scan.setStartRow(start.getBytes());
		scan.setStopRow(end.getBytes());
		
		
		byte[] cf           = WifiTableMeta.CF.getBytes();
	
		for( WifiTableMeta cn : cns ){
			
			scan.addColumn(cf, cn.getBytes());
		}
		
		

		latest = hbaseTemplate.find(WifiTableMeta.TABLENAME.getName(), scan,
				
							new RowMapper<Map<String, Object>>() {
			
								private Map<String, Object> map;
			
								public Map<String, Object> mapRow(Result result, int rowNum)
										throws Exception {
									
									map = new HashMap<String, Object>();
									
									 for (Cell kv : result.rawCells()) {
							                
							                map.put( Bytes.toString(kv.getQualifierArray(),kv.getQualifierOffset(),kv.getQualifierLength()),  Bytes.toString(kv.getValueArray(),kv.getValueOffset(),kv.getValueLength()));
							            }
									
								
									return map;
								}
						});
		
		
		
			return latest  ;
		
	
	}

	@Override
	public List<Map<String, Object>> getWifi2DayData(String apNameAndTimeStr,List<Wifi2Meta> cns)
	{
		
		String apName = apNameAndTimeStr.split("-")[0];
		String timeStr = (apNameAndTimeStr.split("-")[1]).substring(0,8);
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Scan scan = new Scan();
		scan.setCaching(10000);
		scan.setMaxVersions(1);
		
		String start = apName + "-" + timeStr + "000000";
		String end   = apName + "-" + timeStr + "240000";
		
		scan.setStartRow(start.getBytes());
		scan.setStopRow(end.getBytes());
		
		
		byte[] cf      = Wifi2Meta.CF.getBytes();
	
		for( Wifi2Meta cn : cns )
		{
			
			scan.addColumn(cf, cn.getBytes());
		}
		
		

		res = hbaseTemplate.find(Wifi2Meta.TABLENAME.getName(), scan,
				
							new RowMapper<Map<String, Object>>() {
			
								private Map<String, Object> map;
			
								public Map<String, Object> mapRow(Result result, int rowNum)
										throws Exception {
									
									map = new HashMap<String, Object>();
									
									 for (Cell kv : result.rawCells()) {
							                
							                map.put( Bytes.toString(kv.getQualifierArray(),kv.getQualifierOffset(),kv.getQualifierLength()),  Bytes.toString(kv.getValueArray(),kv.getValueOffset(),kv.getValueLength()));
							            }
									
								
									return map;
								}
						});
		
		
		
			return res  ;
	}
	

	@Override
	public List<Map<String, Object>> getODtopNDataForOneDay(String date,int offset, int n)
			throws IOException {

		List<Map<String, Object>> top10oData = new ArrayList<Map<String, Object>>();

		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setMaxVersions(1);
		String start = date+ String.format("%04d", offset) ;
		
		String end =  date + String.format("%04d", (offset + n)) ;
		
		LOG.info("date的值是："+date+"---startTime的值是："+start+"endTime的值是："+end);
		scan.setStartRow(start.getBytes());
		scan.setStopRow(end.getBytes());
		
		final byte[] luxnew = OneDayHotODGJ.CF.getBytes();
		final byte[] OD = OneDayHotODGJ.OD.getBytes();
		scan.addColumn(luxnew, OD);

		final byte[] PASS_NUM = OneDayHotODGJ.PASS_NUM.getBytes();
		scan.addColumn(luxnew, PASS_NUM);
		
		top10oData = hbaseTemplate.find(OneDayHotODGJ.TABLENAME.getName(), scan,
				new RowMapper<Map<String, Object>>() {
					private Map<String, Object> map;
					private String[] od;

					public Map<String, Object> mapRow(Result result, int rowNum)
							throws Exception {
						map = new HashMap<String, Object>();
						od = new String(result.getValue(luxnew, OD)).split("-");
						map.put("O", od[0]);
						map.put("D", od[1]);
						map.put("PASS_NUM", Integer.parseInt(new String(result
								.getValue(luxnew, PASS_NUM))));
						
						return map;
					}
				});

		return top10oData;
	}

	
	
}
