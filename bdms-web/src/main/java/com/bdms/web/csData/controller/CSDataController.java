package com.bdms.web.csData.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bdms.hbase.service.HbaseService;

@Controller
@RequestMapping(value = "dams/cs")
public class CSDataController {

	@Autowired
	private HbaseService hbaseService;
	
	@RequestMapping(value = "getCsData/{area}/{date}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<Map<String,String>> getCsDateByAreaAndDate(@PathVariable(value = "area")  String area,@PathVariable(value = "date")  String date) throws UnsupportedEncodingException {

		//return hbaseService.getCSDataByAreaAndDate(area, date);
		return null;
	}
}
