package com.bdms.dams.camera.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Camera;



@Repository
public interface CameraDao extends JpaRepository<Camera, Integer>,
JpaSpecificationExecutor<Camera>{

	@Query("SELECT * FROM Camera a WHERE a.stationId=?0 and a.IsSelect = 1")
	List<Camera> findCamerasByStationId(String stationId);
	
	@Query("SELECT * FROM Camera a WHERE a.stationId=?0")
	List<Camera> findCamerasInfoByStationId(String stationId);

}
