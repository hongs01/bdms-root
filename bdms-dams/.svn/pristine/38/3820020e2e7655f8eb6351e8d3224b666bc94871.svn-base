package com.bdms.dams.wifi.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.WifiData;

@Repository
public interface WifiDataDao extends JpaRepository<WifiData, Integer>,
JpaSpecificationExecutor<WifiData>{
	
	@Query("SELECT * FROM WifiData")
	List<WifiData> findAll();
	
	//@Query("SELECT * FROM wifiData a where a.apname=?0")
	//List<WifiData> getwifiDataTotal(String apname);
}
