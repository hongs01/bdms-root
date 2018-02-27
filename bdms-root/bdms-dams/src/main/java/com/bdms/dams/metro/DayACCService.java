package com.bdms.dams.metro;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bdms.hbse.enums.SortKey;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.Axis;

/* 
 * Description:
 * 		    地铁客流量实施数据，业务层
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-15上午11:08:18            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface DayACCService {

	/**
	 * description:
	 * 
	 * @param dateTime
	 *            日期的毫秒数
	 * @param station_id
	 *            站点id
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	@Deprecated
	Map<String, Object> axisDayDataforHighCharts(long dateTime,
			String station_id);

	/**
	 * description:
	 * 
	 * @param dateTime
	 *            日期的毫秒数
	 * @param station_id
	 *            站点id
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	Map<String, Object> axisDayEnterDataforHighCharts(
			String dateTime,String station_id);

	/**
	 * description:
	 * 
	 * @param dateTime
	 *            日期的毫秒数
	 * @param station_id
	 *            站点id
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	Map<String, Object> axisDayExitDataforHighCharts(
			String dateTime,String station_id);

	// 日 进出站人数差
	Map<String, Object> axisDayEnterOutDiffDataforHighCharts(
			String dateTime,String sid);
	
	// 日 进出站人数差统计
		Map<String, Object> axisDayEnterOutDiffDataStaticforHighCharts(
				String dateTime,String sid);

	// 日 进出站人数和
	Map<String, Object> axisDayExitEnterSumDataforHighCharts(
			String dateTime,String sid);

	/**
	 * description: 获取进站预测数据
	 * 
	 * @param dateTime
	 *            日期的毫秒数
	 * @param station_id
	 *            站点id
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	Map<String, Object> axisDayEnterPredicteDataforHighCharts(
			String dateTime,String station_id);

	/**
	 * description: 获取出站站预测数据
	 * 
	 * @param dateTime
	 *            日期的毫秒数
	 * @param station_id
	 *            站点id
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	Map<String, Object> axisDayExitPredicteDataforHighCharts(
			String dateTime,String station_id);

	// 日 进出站人数差 预测数据
	Map<String, Object> axisDayEnterOutDiffPredicteDataforHighCharts(
			String dateTime,String sid);
	
	// 日 进出站人数差 预测统计数据
		Map<String, Object> axisDayEnterOutDiffPredicteDataStaticforHighCharts(
				 String dateTime,String sid);

	// 日 进出站人数和 预测数据
	Map<String, Object> axisDayExitEnterSumPredicteDataforHighCharts(
			String dateTime,String sid);

	/**
	 * description: 获取最新的 进站人数最多的前 N个
	 * 
	 * @param offset
	 *            偏移量 ,用于分页
	 * @param n
	 *            取多少个
	 * @return List<Map<String,Object>>
	 * 
	 *         返回一个封装了热门进站站点的细节信息的集合,使用Map封装细节
	 * 
	 *         example : tmp.put("id", String); 站点id
	 *          tmp.put("name",String);站点名字 
	 *          tmp.put("gis", double[2]); 站点坐标 
	 *          tmp.put("enter", int); 进站人数
	 * 
	 *         2015-8-6 上午10:46:49 by Lixc
	 */
	String getStationDayLatestEnterDataTopN(int offset, int n);

	/**
	 * 获取最新的 进站人数最多的前 N个，并将进站人数按照进站人数排序
	 * @param sortKey
	 * @param offset
	 * @param n
	 * @return
	 */
	String getStationDayLatestDataTopNAddAllTimes(SortKey sortKey, int offset,
			int n);

	/**
	 * description: 获取最新的 出站人数最多的前 N个
	 * 
	 * @param offset
	 *            偏移量 ,用于分页
	 * @param n
	 *            取多少个
	 * @return List<Map<String,Object>>
	 * 
	 *         返回一个封装了热门出站站点的细节信息的集合,使用Map封装细节
	 * 
	 *         example : tmp.put("id", String); 站点id
	 *          tmp.put("name",String) ； 站点名字 
	 *          tmp.put("gis", double[2]); 站点坐标
	 *          tmp.put("exit", int); 出站人数
	 * 
	 *         2015-8-6 上午10:46:49 by Lixc
	 */
	String getStationDayLatestExistDataTopN(int offset, int n);

	/**
	 * description: 获取最新的 进出站人数之差 最多的前 N个
	 * 
	 * @param offset
	 *            偏移量 ,用于分页
	 * @param n
	 *            取多少个
	 * @return List<Map<String,Object>>
	 * 
	 *         返回一个封装了热门进出差站点的细节信息的集合,使用Map封装细节
	 * 
	 *         example : tmp.put("id", String); 站点id tmp.put("name",String);
	 *         站点名字 tmp.put("gis", double[2]); 站点坐标 tmp.put("enter_exit_sub",
	 *         int); 进出站人数差 2015-8-6 上午10:46:49 by Lixc
	 */
	String getStationDayLatestEnterExistSubDataTopN(int offset, int n);

	/**
	 * description: 获取最新的 进出站人数之和最多的前 N个
	 * 
	 * @param offset
	 *            偏移量 ,用于分页
	 * @param n
	 *            取多少个
	 * @return List<Map<String,Object>>
	 * 
	 *         返回一个封装了热门进出和站点的细节信息的集合,使用Map封装细节
	 * 
	 *         example : tmp.put("id", String); 站点id tmp.put("name",String);
	 *         站点名字 tmp.put("gis", double[2]); 站点坐标 tmp.put("enter_exit_sub",
	 *         int); 进出站人数和 2015-8-6 上午10:46:49 by Lixc
	 */
	String getStationDayLatestEnterExistSumDataTopN(int offset, int n);

	/**
	 * description:
	 * 
	 * @param dayStr
	 *            日期字串 格式为 ： yyyMM
	 * @param station_id
	 *            站点名字
	 * @return List<String> 2015-7-15 上午11:26:00 by Yuxl
	 */
	List<Map<String, String>> axisMonthData(String monthStr, String station_id);

	/**
	 * description:x轴的坐标
	 * 
	 * @return Axis<String> 2015-7-15 上午11:17:26 by Yuxl
	 */
	Axis<String> DayACCAxis(List<String> axisData);

	Option SDayDataOption();

	Option SMonthDataOption();

	/**
	 * description: 获取前n个热门线路,n最大为1000
	 * 
	 * @param n
	 * @return List<Map<String,Object>>
	 * 
	 *         返回值 是 热门站线路的细节的 集合 热门线路的细节封装为Map ,包含7个key example:
	 *         map.put("o_id",String); map.put("o_name", String);
	 *         map.put("o_gis", doubele[2]); map.put("d_name", String);
	 *         map.put("d_id", String); map.put("d_gis", doubele[2]);
	 *         tmp.put("pass_sum", int);
	 * 
	 *         2015-8-7 下午1:35:34 by Lixc
	 */
	List<String> dayODtopNData(int n);
	
	/**
	  * description: 获取最新的 进站人数最多的前 N个
	  * @param n  取多少个
	  * @return  Map<String,Object>   
	  *  
	  *  返回一个封装了热门进站站点的细节信息的集合,使用Map封装细节
	  *  
	  * example : 
	  *   
	  *   Map<String,Object>  result = ..;
	  * 
	  *      List<Map> list
	  *       map right = null;
	  *       for(...){
	  *          right = new HashMap();
	  *          right.put("name",站点名字);
	  *          right.put("count",人数);
	  *          list.add(right);
	  *       }
	  *       
	  *       map top = new HashMap();
	  *       top.put("name",站点名字);
	  *       top.put("ename",英文名字);
	  *       top.put("count",最热站点人数);
	  *       top.put("updateTime",数据更新时间);
	  *       top.put("totoal",轨交总共人数);
	  *       
	  *       result.add("right",right);
	  *       result.add("top",top);
	  *       
						
	  * 2015-8-6 上午10:46:49
	  * by Lixc
	 */
	Map<String,Object> getStationDayLatestEnterDataTopNForRight(int n);
	
	/**
	  * description: 获取最新的 出站人数最多的前 N个
	  * @param n  取多少个
	  * @return  Map<String,Object>   
	  *  
	  *  返回一个封装了热门进站站点的细节信息使用Map封装细节
	  *  
	  * example : 
	  *   
	  *   Map<String,Object>  result = ..;
	  * 
	  *      List<Map> list
	  *       map right = null;
	  *       for(...){
	  *          right = new HashMap();
	  *          right.put("name",站点名字);
	  *          right.put("count",人数);
	  *          list.add(right);
	  *       }
	  *       
	  *       map top = new HashMap();
	  *       top.put("name",站点名字);
	  *       top.put("ename",英文名字);
	  *       top.put("count",最热站点人数);
	  *       top.put("updateTime",数据更新时间);
	  *       top.put("totoal",轨交总共人数);
	  *       
	  *       result.add("right",right);
	  *       result.add("top",top);
	  *       
						
	  * 2015-8-6 上午10:46:49
	  * by Lixc
	 */
	Map<String,Object> getStationDayLatestExitDataTopNForRight(int n);
	
	/**
	  * description: 获取最新的 进出站人数差最多的前 N个
	  * @param n  取多少个
	  * @return  Map<String,Object>   
	  *  
	  *  返回一个封装了热门进站站点的细节信息的使用Map封装
	  *  
	  * example : 
	  *   
	  *   Map<String,Object>  result = ..;
	  * 
	  *      List<Map> list
	  *       map right = null;
	  *       for(...){
	  *          right = new HashMap();
	  *          right.put("name",站点名字);
	  *          right.put("count",人数);
	  *          list.add(right);
	  *       }
	  *       
	  *       map top = new HashMap();
	  *       top.put("name",站点名字);
	  *       top.put("ename",英文名字);
	  *       top.put("count",最热站点人数);
	  *       top.put("updateTime",数据更新时间);
	  *       top.put("totoal",轨交总共人数);
	  *       
	  *       result.add("right",right);
	  *       result.add("top",top);
	  *       
						
	  * 2015-8-6 上午10:46:49
	  * by Lixc
	 */
	Map<String,Object> getStationDayLatestEnterExitSubDataTopNForRight(int n);
	
	/**
	  * description: 获取最新的 进出站人数和最多的前 N个
	  * @param n  取多少个
	  * @return  Map<String,Object>   
	  *  
	  *  返回一个封装了热门进站站点的细节信息使用Map封装
	  *  
	  * example : 
	  *   
	  *   Map<String,Object>  result = ..;
	  * 
	  *      List<Map> list
	  *       map right = null;
	  *       for(...){
	  *          right = new HashMap();
	  *          right.put("name",站点名字);
	  *          right.put("count",人数);
	  *          list.add(right);
	  *       }
	  *       
	  *       map top = new HashMap();
	  *       top.put("name",站点名字);
	  *       top.put("ename",英文名字);
	  *       top.put("count",最热站点人数);
	  *       top.put("updateTime",数据更新时间);
	  *       top.put("totoal",轨交总共人数);
	  *       
	  *       result.add("right",right);
	  *       result.add("top",top);
	  *       
						
	  * 2015-8-6 上午10:46:49
	  * by Lixc
	 */
	Map<String,Object> getStationDayLatestEnterExitSumDataTopNForRight(int n);
	
	List<String> dayODtopNStaionPairs(int n );

	List<Map<String,Object>>  dayODtopNStaionNum(int offset,int n);
	
	List<Map<String,Object>>  getODtopNStaionNumForOneDay(String date,int offset,int n);
	
	List<String> getODtopNStaionPairsForOneDay(String dateTime,int n );
	
}
