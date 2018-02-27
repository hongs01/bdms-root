package com.bdms.dams.elecfence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Elecfence;


@Repository
public interface ElecfenceDao extends JpaRepository<Elecfence, Integer>,
JpaSpecificationExecutor<Elecfence>{
	
	@Query("SELECT * FROM Elecfence a WHERE a.code=?0")
	Elecfence findByCode(String code);
	
	@Query("SELECT * FROM Elecfence a WHERE a.name=?0")
	List<Elecfence> findByName(String name);
}
