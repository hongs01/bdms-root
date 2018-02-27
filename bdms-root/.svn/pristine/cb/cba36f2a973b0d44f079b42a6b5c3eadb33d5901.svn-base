package com.bdms.web.weight.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bdms.dams.area.service.AreaService;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.dzwl.dao.DzwlDao;
import com.bdms.dams.metro.RealTimeDZWLService;
import com.bdms.dams.weight.dao.WeightDao;
import com.bdms.dams.weight.service.WeightService;
import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.HistoryPredicteDZWL;
import com.bdms.hbse.enums.StreamingDZWL;

/**
  * Description:
  * 		
  * 计分功能对应的controller
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午4:01:58            1.0            Created by Chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value = "dams/weight/weightLevel")
public class WeightController {
	
	@Autowired
	private WeightDao weightDao;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private WeightService weightService;
	
	@Autowired
	private DZWLService dzwlService;
	
	@Autowired
	private RealTimeDZWLService realTimeDZWLService;
	
	@Autowired
	private HbaseService hbaseService;
	
	@Autowired
	private DzwlDao dzwlDao;
	
	//test
	@RequestMapping(value = "getTime", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String,Object> getTime() throws UnsupportedEncodingException {

		return realTimeDZWLService.getDZWLRealTimeYHSDataByName("陆家嘴", "2015-11-27 23:40");
	}
	
	@RequestMapping(value = "getHbase", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<Map<String,String>> getHbase() throws UnsupportedEncodingException {

		List<HistoryPredicteDZWL> columnToBackh = Arrays.asList(HistoryPredicteDZWL.SJ,HistoryPredicteDZWL.YHS,HistoryPredicteDZWL.YHSTB,HistoryPredicteDZWL.MRS,HistoryPredicteDZWL.MRSTB,HistoryPredicteDZWL.MCS,HistoryPredicteDZWL.MCSTB);
		return hbaseService.getDZWLHistorySubData("00007", columnToBackh, "23:30");
	}
	
	
	/**
	  * description:获取全部的区域，按照得分由高到低降序排列
	  * @return
	  * @throws UnsupportedEncodingException
	  * List<Area>
	  * 2014-3-7 下午2:14:48
	  * by Chenfeng
	 */
	
	@RequestMapping(value = "area", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<Area> getAreas() throws UnsupportedEncodingException {

		return weightService.getTopAreasByMark();
	}
	
	@RequestMapping(value = "topArea", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Area getTopestAreas() throws UnsupportedEncodingException {

		return weightService.getTopestMark();
	}
	//得到所有的区域
	@RequestMapping(value = "allAreas", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<Area> getAllAreas() throws UnsupportedEncodingException {

		return weightService.getAreas();
	}
	@RequestMapping(value = "firsts/{areaId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<FirstWeight> getFirstByAreaId(@PathVariable(value = "areaId")  Integer areaId) throws UnsupportedEncodingException {

		return weightService.getFirstWeighsByAreaId(areaId);
	}
	
	@RequestMapping(value = "seconds/{typeId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<SecondWeight> getSecondByTypeId(@PathVariable(value = "typeId")  Integer typeId) throws UnsupportedEncodingException {

		return weightService.getSecondWeightByTypeId(typeId);
	}
	
	//通过areaId获取该区域中所有的信息
	@ResponseBody
	@RequestMapping(value = "getAll/{areaId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Area getAllByAreaId(@PathVariable(value = "areaId")  Integer areaId) throws UnsupportedEncodingException {

		//return weightDao.getAllByAreaId(areaId);
		return weightService.getAreaAllInfoByAreaId(areaId);

	}
//	//测试代码
		@ResponseBody
		@RequestMapping(value = "dzwl/{id}", method = { RequestMethod.GET,
				RequestMethod.POST })
		public Map<String, String> getDzwlById(@PathVariable(value = "id")  String id) throws UnsupportedEncodingException {

			return dzwlService.getDZWLById(id);
			//return weightService.getAreaAllInfoByAreaId(areaId);

		}
		
		@ResponseBody
		@RequestMapping(value = "getDzwlData", method = { RequestMethod.GET,
				RequestMethod.POST })
		public List<Map<String, Object>> getDzwlData() throws UnsupportedEncodingException {

			return dzwlService.getDZWLData();
			//return weightService.getAreaAllInfoByAreaId(areaId);

		}
		
		@ResponseBody
		@RequestMapping(value = "getDzwlLatestData/{id}", method = { RequestMethod.GET,
				RequestMethod.POST })
		public List<Map<String,String>> getDzwlLatestData(@PathVariable(value = "id")  String id) throws UnsupportedEncodingException {

			//return dzwlService.getDZWLData();
			List<StreamingDZWL> columnToBack = Arrays.asList(StreamingDZWL.QYM,StreamingDZWL.SJ,StreamingDZWL.YHS);
			//Log.info("id:"+id+"---dzwlDao.findByCode(id).getName():"+dzwlDao.findByCode(id).getName());
			return hbaseService.getDZWLDayDataByName(dzwlDao.findByCode(id).getName(),columnToBack,null);
			//return weightService.getAreaAllInfoByAreaId(areaId);

		}
		
	
}
