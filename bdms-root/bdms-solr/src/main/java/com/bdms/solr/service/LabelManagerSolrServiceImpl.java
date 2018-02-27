package com.bdms.solr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.core.solr.LabelManager;
import com.bdms.solr.repositories.LabelManagerRepository;

@Service("labelManagerSolrService")
public class LabelManagerSolrServiceImpl implements LabelManagerSolrService {

	@Autowired
	private LabelManagerRepository labelManagerRepository;
	
	@Override
	public Page<LabelManager> findAll(Pageable pageable) {
		
		Page<LabelManager> labelManagers=labelManagerRepository.findAll(pageable);
		return labelManagers;
	}

	@Override
	public Page<LabelManager> findAllLables(Pageable pageable) {
		return labelManagerRepository.findAllLables(pageable);
	}
	
	
}
