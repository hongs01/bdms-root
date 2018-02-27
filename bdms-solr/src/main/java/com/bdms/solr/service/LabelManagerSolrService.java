package com.bdms.solr.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.core.solr.LabelManager;


@Service
public interface LabelManagerSolrService {
	
	Page<LabelManager> findAll(Pageable pageable);
	
	Page<LabelManager> findAllLables(Pageable pageable);
}
