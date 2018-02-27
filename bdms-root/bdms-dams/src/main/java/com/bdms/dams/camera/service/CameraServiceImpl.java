package com.bdms.dams.camera.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.camera.dao.CameraDao;
import com.bdms.entity.dams.Camera;

@Service("cameraService")
public class CameraServiceImpl implements CameraService{

	@Autowired
	private CameraDao cameraDao;
	
	@Override
	public List<Camera> findCamerasByStationId(String stationId) {
		
		return cameraDao.findCamerasByStationId(stationId);
	}

	@Override
	public List<Camera> findCamerasInfoByStationId(String stationId) {
		
		return cameraDao.findCamerasInfoByStationId(stationId);
	}

}
