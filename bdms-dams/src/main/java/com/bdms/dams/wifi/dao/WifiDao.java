package com.bdms.dams.wifi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Wifi;


@Repository
public interface WifiDao extends JpaRepository<Wifi, Integer>,
		JpaSpecificationExecutor<Wifi>{
	@Query("SELECT * FROM Wifi a WHERE a.code=?0")
	Wifi findByCode(String code);
	
	@Query("SELECT * FROM Wifi a WHERE a.name=?0")
	List<Wifi> findByName(String name);
}