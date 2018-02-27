package com.bdms.web.dams.wifi.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.dams.wifi.service.WifiDataService;
import com.bdms.entity.dams.WifiData;

@Controller
@RequestMapping(value = "/dams/wifi/wifiData/")
public class WifiDataController {

	@Autowired 
	private	WifiDataService wifiDataService;
	
//	@Autowired
//	private WifiDataDao wifiDataDao;  
	
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView wifiPage() {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("topimg", "titleNameW");
		
		return new ModelAndView( "dams/wifi/wifiData", map);
	}
	
	
	@RequestMapping(value = "page/{sid}", method = { RequestMethod.GET })
	public ModelAndView wifiPageId(@PathVariable(value = "sid") String sid) throws UnsupportedEncodingException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleNameW");
		map.put("sid", sid);
		return new ModelAndView("dams/wifi/wifiData",map);
	}
	//得到所有的站点
		@RequestMapping(value = "allStations", method = { RequestMethod.GET,
				RequestMethod.POST })
		public @ResponseBody
		List<WifiData> getAllStations() throws UnsupportedEncodingException {

			return wifiDataService.getStations();
		}
		
		
		/**
		 * 获取wifi数据
		 * 
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		@ResponseBody
		@RequestMapping(value = "wifiData/{sid}", method = { RequestMethod.GET,
				RequestMethod.POST })
		public Map<String, Object> getWifi2DayDataForHighchart(@PathVariable(value = "sid")  String sid ) throws UnsupportedEncodingException {
			return wifiDataService.getWifi2DayDataForHighchart(sid);

		}
		
		/**
		 * 根据apname获取wifiData表的全部数据
		 * @param sid
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		@ResponseBody
		@RequestMapping(value = "getwifiDataTotal/{apname}", method = { RequestMethod.GET,
				RequestMethod.POST })
		public List<WifiData> getwifiDataTotal(@PathVariable(value = "apname")  String apname ) throws UnsupportedEncodingException {
			return wifiDataService.getwifiDataTotal(apname);
			//return wifiDataDao.getwifiDataTotal(apname);
		}
}
