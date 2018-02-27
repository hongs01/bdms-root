package com.bdms.web.solr.lablemange.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SolrController {

	@RequestMapping(value = "/solr")
	public String solrIndex() {
		return "solr/index";
	}

	@RequestMapping(value = "/solr/data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView solrData(String keyword) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("keyword", keyword);
		return new ModelAndView("solr/solrData", model);
	}

	public Map<String, Object> getserchData() {
		return null;

	}
}
