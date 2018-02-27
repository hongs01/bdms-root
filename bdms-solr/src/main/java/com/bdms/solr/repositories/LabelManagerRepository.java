package com.bdms.solr.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrRepository;
import org.springframework.stereotype.Repository;

import com.bdms.core.solr.LabelManager;

@Repository
public interface LabelManagerRepository extends SolrRepository<LabelManager,String>{

	Page<LabelManager> findAll(Pageable pageable);
	
	@Query(value = "*:*龙*")
	Page<LabelManager> findAllLables(Pageable pageable);
}
