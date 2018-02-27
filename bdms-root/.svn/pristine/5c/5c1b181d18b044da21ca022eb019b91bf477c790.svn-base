package com.bdms.dams.video;

import java.util.List;
import java.util.Map;


import com.bdms.entity.dams.Video;


public interface DayACCVideoService {
	
	Map<String, Object> getVideoDataById(String videoId);
	
	/**
	  * description: 通过探头的id获取 该探头的最新的一天的数据  
	  * @param videoId
	  * @return
	  * Map<String,Object>
	  * 2014-1-22 下午3:35:18
	  * by Lixc
	 */
	Map<String, Object> getVideoDayDataByIdForHighchart(String videoId);

	List<Map<String, Object>> getVideoData();
	
	List<String> findAllVideos();
//获取mysql中所有视频的字段信息
	List<Video> findAll();
	//获取所有的视频名字
	List<String> findAllVideoNames();
	//
	List<Video> findByareaId(String areaId);
	
	Video findByCameraId(String cameraId);
	
}
