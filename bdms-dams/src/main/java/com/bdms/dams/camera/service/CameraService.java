package com.bdms.dams.camera.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Camera;

@Service
public interface CameraService {
	
	List<Camera> findCamerasByStationId(String stationId);
	
	List<Camera> findCamerasInfoByStationId(String stationId);
}
