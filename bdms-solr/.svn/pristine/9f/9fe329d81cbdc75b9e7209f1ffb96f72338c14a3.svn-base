package com.bdms.solr;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;

import com.bdms.core.solr.LabelManager;
import com.bdms.solr.service.LabelManagerSolrService;


public class SolrBeanTest extends JunitSpringInitialize {


	@Autowired
	private LabelManagerSolrService labelManagerSolrService;
	
	@Test
	public void getBeantest() {
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++) {
			System.out.println(beans[i]);
		}
		
		Pageable pageable=new SolrPageRequest(1,10);
		
		Page<LabelManager> findAllLables = labelManagerSolrService.findAllLables(pageable);
		
		List<LabelManager> labelManagers=findAllLables.getContent();
		
		System.out.println(labelManagers.size());
		
		for (int i = 0; i < labelManagers.size(); i++) {
			System.out.println(labelManagers.get(i).getTitle());
		}
		
	}

	
}
