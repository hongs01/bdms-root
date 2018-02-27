package com.bdms.dams.weight.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.AreaFirstSecondWeight;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;


/**
  * Description:
  * 
  * 		计分功能接口定义
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午3:35:36            1.0            Created by Chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public interface WeightService {
	
	
	/**
	  * description:	获取所有的区域
	  * @return			区域列表
	  * List<Area>
	  * 2014-3-7 下午12:18:30
	  * by Chenfeng
	 */
	List<Area> getAreas();
	
	/**
	  * description:	      通过areaId(区域id)和typeId(站点的id)来获取二级列表
	  * @param areaId     区域编号
	  * @param typeCode   站点编号
	  * @return			      二级列表(结果集)
	  * List<Map<String,String>>
	  * 2014-3-5 下午3:36:47
	  * by Chenfeng
	 */
	List<Map<String,String>> getSecondByTypeId(Integer areaId,String typeCode);
		
	/**
	  * description:	通过区域areaId获取一级列表以及总分
	  * @param areaId   区域编号
	  * @return			每个map中包含总分，任取其中一个即可
	  * List<Map<String,String>>
	  * 2014-3-5 下午3:39:02
	  * by Chenfeng
	 */
	List<Map<String,String>> getFirstTotalByAreaId(Integer areaId);
	
	
	//测试函数
	List<AreaFirstSecondWeight> getAllById(Integer areaId);
	
	
	int getGJETByStationId(String stationId);
	
	//获取轨交进站人数、出站人数、进出站人数和
	int getGJEnterExitPeoleNumByStationId(String station,String name);
	
	List<Integer> getLevelByStationId(String stationId, String code);
	
	int getDZWLETById(String id);
	
	//获取分数最高的前10个区域
	List<Area> getTopAreasByMark();
	
	//通过areaId获取一个区域中所有的信息，封装到map中
	//Map<String,Object> getAreaAllInfoByAreaId(Integer areaId);
	Area getAreaAllInfoByAreaId(Integer areaId);
	
	Area getTopestMark();
	
	List<FirstWeight> getFirstWeighsByAreaId(Integer areaId);
	
	List<SecondWeight> getSecondWeightByTypeId(Integer typeId);

	Page<Area> findAllWhitPage(int page, int rows, String areaId,
			String areaName, double totalMark);

    //向firstWeight表中保存一条记录
	FirstWeight saveFirst(FirstWeight firstweight);

	//根据Id获取一级表的一条数据
	FirstWeight getFirstById(Integer id);

	//根据Id删除firstWeight表的一条记录
	void delFirst(Integer id);

	/*向secondWeight中保存一条数据*/
	SecondWeight saveSecond(SecondWeight secondweight);

	/*根据Id获取secondWeight表的一条数据*/
	SecondWeight getSecondById(int id);

	/*根据Id删除secondWeight表的一条数据*/
	void delSecond(Integer id);

	
	/**
	 * @param page  当前页面
	 * @param rows  页面行数
 	 * @param id    id
	 * @param typeName 类型名称
	 * @return
	 */
	Page<FirstWeight> findAllFirstPage(int page, int rows, int id, String typeName);

	/**
	 * @param page   当前页面
	 * @param rows   页面行数
	 * @param id        id
	 * @param name   名称
	 * @return
	 */
	Page<SecondWeight> findAllSecondPage(int page, int rows, int id, String name);
}
