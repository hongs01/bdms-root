package com.bdms.web.dams.DZWL.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.elecfence.service.ElecfenceService;
import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.metro.RealTimeDZWLService;
import com.bdms.entity.dams.Dzwl;
@Controller
@RequestMapping(value="/dams/DZWL/DZWLPage/")
public class DZWLController {
	
	
	@Autowired
	private DZWLService dZWLService;
	@Autowired
	RealTimeDZWLService realTimeDZWLService;

	/**跳转到DZWLPage界面
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView DZWLPage(){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("topimg", "titleNamezdcs");
		return new ModelAndView ("dams/DZWL/DZWLPage",map);
	}
	
	/**
	 * description: 跳转 到进站实时监控页面(可带参数)
	 * 
	 * @return String 2015-7-28 下午3:45:34 by Yangb
	 */
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView DZWLPage(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		map.put("sid", sid);
		return new ModelAndView("/dams/DZWL/DZWLPage",map);
	}
	
	/**
	 * 获取电子围栏的全部可用数据
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "allelecfences", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<Dzwl> findAll() throws UnsupportedEncodingException {
		List<Dzwl> elecfences =dZWLService.findAllEnabled(1);
		return elecfences;		
	}
	
	/**
	 * 
	 * 获取当前用户人数的json数据
	 * @param dateTime
	 * @param sid
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dzwlyhs", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getDZWLRealTimeYHSDataForHigcharts(String qym,String dateTime ) throws UnsupportedEncodingException {
		
		return realTimeDZWLService.getDZWLRealTimeYHSDataByName(qym,dateTime);

	}
	
	
	/**
	 * 获取历史用户人数的json数据
	 * @param qym
	 * @param time
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "dzwlyhsh", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getDZWLHistoryTimeYHSDataForHigcharts(String qym,String dateTime ) throws UnsupportedEncodingException {
		
		return realTimeDZWLService.getDZWLHistoryTimeYHSDataByName(qym,dateTime);

	}
	
	/**
	 * 获取某个apname 最新的一条数据(ldoa  lastest data of apname)
	 * @param qym
	 * @param time
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "ldoa/{apname}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getDZWLLastestDataOfApname(@PathVariable(value="apname") String apname) throws UnsupportedEncodingException {
		
		return realTimeDZWLService.getLDOA(apname);

	}
	
	/**
	 * 获取某个apname 最新的一条数据(ldoa  lastest data of apname)
	 * @param qym
	 * @param time
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "ldoa", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getDZWLLastestDataOfALL() throws UnsupportedEncodingException {
		
		return realTimeDZWLService.getLDOA();

	}
	
}
