package com.bdms.hbase.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import com.bdms.hbse.enums.HistoryPredicteDZWL;
import com.bdms.hbse.enums.ImgHTableMeta;
import com.bdms.hbse.enums.OneDayHotODGJ;
import com.bdms.hbse.enums.ResultType;
import com.bdms.hbse.enums.SortKey;
import com.bdms.hbse.enums.StreamingDZWL;
import com.bdms.hbse.enums.StreamingGJ;
import com.bdms.hbse.enums.Wifi2Meta;
import com.bdms.hbse.enums.WifiTableMeta;

@Service
public interface HbaseService {

	/**
	 * 
	 * @param tableName
	 * @param columns
	 *            Map<String,List<String>> -- >> Map<列族名,List<列名>>
	 * @param num
	 *            小于零 表示获取所有
	 * @return
	 */
	public List<Map<String, String>> getAllData(String table,
			Map<String, List<String>> columns, int num);

	/**
	 * @Title:getStationDayData
	 * @param dayStr
	 *            日期字串 ：yyyyMMdd
	 * @param station_id
	 *            站点 id
	 * @return
	 * @Return List<String>
	 * @Description:TODO 获取 指定站点的 某天的 数据
	 */
	List<Map<String, String>> getStationDayData(String dayStr,
			String station_id, ResultType type) throws IOException;

	/**
	 * @Title:getStationDayData
	 * @param dayStr
	 *            日期字串 ：yyyyMMdd
	 * @param station_id
	 *            站点 id
	 * @return
	 * @Return List<String>
	 * @Description:TODO 获取 指定站点的 某天的 预测数据
	 */
	List<Map<String, String>> getStationDayPredicteData(String dayStr,
			String station_id, ResultType type) throws IOException;

	/**
	 * @Title:getStationDayData
	 * @param monthStr
	 *            日期字串 ：yyyyMM
	 * @param station_id
	 *            站点 id
	 * @return
	 * @Return List<String>
	 * @Description:TODO 获取 指定站点的 某天的 全部数据
	 */
	List<Map<String, String>> getStationMonthata(String monthStr,
			String station_id) throws IOException;

	/**
	 * @Title:getStationDayData
	 * @param dayStr
	 *            日期字串 ：yyyyMMdd
	 * @param station_id
	 *            站点 id
	 * @return
	 * @Return List<String>
	 * @Description:TODO 获取 指定站点的 某天的 全部数据
	 */
	@Deprecated
	Map<String, List<String>> getStationDayDataNeedGroupResult(String dayStr,
			String station_id) throws IOException;

	// <T> List<T> getStationData( String timeStr, String
	// station_id,List<DSHBaseTables> cols,final RowMapper<T> action ) throws
	// IOException;

	/**
	 * description: 获取最新的 人数最多的 前n个站点
	 * 
	 * @param sortKey
	 * @param offset
	 * @param n
	 * @return
	 * @throws IOException
	 *             List<Map<String,Object>> 2015-8-6 上午11:32:37 by Lixc
	 */
	@Deprecated
	List<Map<String, Object>> getStationDayLatestDataTopN(SortKey sortKey,
			int offset, int n) throws IOException;

	/**
	 * description: 获取 所有 站的当前 最新的信息
	 * 
	 * @return List<Map<String,Object>> 2015-8-19 下午3:14:53 by Yuxl
	 */
	@Deprecated
	List<Map<String, Object>> getStationDayLatestData();

	/**
	 * getDayODtopNData 获取前n条热门线路
	 * 
	 * @param n
	 * @return
	 * @Return Map<String, Object> key: O,D,PASS_SUM value:String,String,int
	 * @Description:TODO 获某天的OD top10 数据
	 */
	List<Map<String, Object>> getDayODtopNData(int offset, int n)
			throws IOException;

	/**
	 * description: 获取当前时间的预测值
	 * 
	 * @param dayStr
	 * @param station_id
	 * @param type
	 * @return
	 * @throws IOException
	 *             List<Map<String,Integer>>
	 * 
	 *             example map = new HashMap(); map.put("hour",int);
	 *             map.put("count",int); list.add(map);
	 * 
	 *             2015-8-12 下午3:35:45 by Lixc
	 */
	List<Map<String, Integer>> getPredicteData(String dayStr,
			String station_id, ResultType type) throws IOException;
	
	
	/**
	  * description:  存储单条图片的的相关信息     //未完  待续
	  * @param timeStamp
	  * @param cameraId
	  * @param peopleNum
	  * @param densityLevel
	  * @param densityImage
	  * @param groupNum
	  * @param groupImage
	  * @param warnLevel
	  * @param wrnImage
	  * void
	  * 2015-8-20 下午2:11:26
	  * by Lixc
	 */
	@Deprecated
	void storeImgMetaData(String timeStamp,String cameraId,String peopleNum,String densityLevel,String densityImage,
						  String groupNum,String groupImage,String warnLevel,String wrnImage,String reserved);
	
	

	/**
	  * description:  存储单条图片的的相关信息     //未完  待续
	  * @param timeStamp
	  * @param cameraId
	  * @param peopleNum
	  * @param densityLevel
	  * @param densityImage
	  * @param groupNum
	  * @param groupImage
	  * @param warnLevel
	  * @param wrnImage
	  * void
	  * 2015-8-20 下午2:11:26
	  * by Lixc
	 */
	@Deprecated
	void storeImgMetaData(List<Map<String,String>> listData);
	
	
	/**
	  * description:  从hbase中获取最新的 图片的 元信息     //未完  待续
	  * @return
	  * Map<String,Object>
	  * 2015-8-20 下午5:12:11
	  * by Lixc
	 */
	List<Map<String, String>> getLatestImgMeta( Collection<String> ids  ) throws IOException;
	
	
	/**
	  * description:  获取最新数据  
	  * @param stationIds
	  * @param sortKey
	  * @return      map中包含的key有     START_TIME,STATION_ID  (共有)
	  * 									,EXIT_TIMES(sortKey = EXIT_TIMES )
	  * 									,ENTER_TIMES(sortKey = EXIT_TIMES )
	  * 									,ENTER_EXIT_SUB(sortKey = ENTER_EXIT_SUB )
	  * 									,ENTER_EXIT_SUM(sortKey = ENTER_EXIT_SUM )
	  * List<Map<String,String>>
	  * 2015-8-22 下午1:14:26
	  * by Lixc
	 */
	List<Map<String, Object>> getGJLatestDataForACC( Set<String> stationIds,SortKey sortKey);

	
	/**
	  * description:  获取最新数据
	  * @param stationIds
	  * @param sortKey
	  * @return    map中包含的key有     START_TIME,STATION_ID ,EXIT_TIMES,ENTER_TIMES
	  * List<Map<String,String>>
	  * 2015-8-22 下午2:16:07
	  * by Lixc
	 */
	List<Map<String, String>> getAllGJLatestData(Set<String> stationIds,SortKey sortKey);
	
	
	/**
	  * description: 通过区域名 获取hbase中该区域的实时数据
	  * @param qym  区域名
	  * @param columsToBack  需要返回的列
	  * @return 键值对的集合
	  * 2015-8-24 下午2:40:48
	  * by Lixc
	 */
	 List<Map<String,String>> getDZWLDayDataByName(String qym,List<StreamingDZWL> columsToBack,String dtime);
	 
	 /**
	  * description: 通过区域名 获取hbase中该区域的实时数据
	  * @param qym  区域名
	  * @param columsToBack  需要返回的列
	  * @return 键值对的集合
	  * 2015-8-24 下午2:40:48
	  * by Lixc
	 */
	 List<Map<String,String>> getHistoryDZWLDataByName(String qym,List<HistoryPredicteDZWL> columsToBack);
	 List<Map<String,String>> getHistoryDZWLDataByName(String qym,List<HistoryPredicteDZWL> columsToBack,String dayStr);
	 /**
	 * @param timeStr  HH:mm
	 * @param qym
	 * @param columsToBack
	 * @return
	 */
	Map<String, String> getHistoryDZWLDataByTimeStrAndName(String timeStr,String qym,
				List<HistoryPredicteDZWL> columsToBack);

	
	
	/* 
	 * @Title: getMetroDataByStationId  通过stationId 获取单条记录
	 * @Description: TODO 
	 * @param @param stationId
	 * @param @param columsToBack
	 * @param @return    
	 * @return Map<String,String>      key 为  columsToBack中的值
	 * @throws 
	 * @Author  Lixc
	*/
	Map<String,String> getMetroDataByStationId(String stationId,List<StreamingGJ> columsToBack);


	List<Map<String, String>> getMetroData(List<StreamingGJ> columsToBack);

	Map<String, String> getVideoDataByVideoId(String videoId,List<byte[]> columsToBack) throws IOException;

	List<Map<String, String>> getDZWLDayData(Collection<String> qyms,List<StreamingDZWL> columnToBack);
	
	/**
	  * description: 获取轨交数据的最新时间
	  * @return
	  * String
	  * 2014-1-7 上午10:00:44
	  * by Lixc
	 */
	String getStreamingGJLatestTimeStr();
	
	/**
	  * description:  获取电子围栏的最新时间
	  * @return
	  * String
	  * 2014-1-13 下午8:36:34
	  * by Lixc
	 */
	String getDZWLLatestTimeStr();
	
	
	/**
	  * description:  获取 某个 探头的  最新的一天的数据
	  * @param cameraId
	  * @param cns  需要返回的列
	  * @return
	  * List<Map<String,Object>>
	  * 2014-1-22 下午12:51:53
	  * by Lixc
	 */
	List<Map<String, Object>> getVideoDayDataByID(String cameraId, List<ImgHTableMeta> cns);
	
	/**
	  * description:  通过 rowKey从Hbase中取特定的一行数据
	  * @param tableName
	  * @param rowkey
	  * @param cf
	  * @param cns
	  * @return
	  * @throws IOException
	  * Map<String,String>
	  * 2014-1-22 下午2:17:50
	  * by Lixc
	 */
	Map<String,String> findDataByRowKey(String tableName,byte[] rowkey,byte[] cf,List<byte[]> cns) throws IOException;


	/**
	  * description:  获取 某个 wifi区域  最新的一天的数据
	  * @param cameraId
	  * @param cns  需要返回的列
	  * @return
	  * List<Map<String,Object>>
	  * 2014-1-22 下午12:51:53
	  * by Lixc
	 */
	List<Map<String, Object>> getWifiDayDataByID(String cameraId, List<WifiTableMeta> cns);

	/**
	  * description:  获取 某个 wifi设备区域  最新的一天的数据
	  * @param apNameAndTimeStr 设备ID+"-"+日期
	  * @param cns  需要返回的列
	  * @return
	  * List<Map<String,Object>>
	 */
	List<Map<String, Object>> getWifi2DayData(String apNameAndTimeStr,List<Wifi2Meta> cns);
	

	List<Map<String, Object>> getODtopNDataForOneDay(String date,int offset, int n)throws IOException;
	
	List<Map<String,String>> getDZWLHistorySubData(String qym,final List<HistoryPredicteDZWL> columsToBack,String date);
	
	
	
}
