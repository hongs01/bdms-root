package com.bdms.dams.video;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.video.dao.VideoDao;
import com.bdms.entity.dams.Video;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.ImgHTableMeta;

@Service(value="dayACCVideoService")
public class DayACCVideoServiceImpl implements DayACCVideoService{

	private static final Logger LOG = LoggerFactory.getLogger(DayACCVideoServiceImpl.class);

	@Autowired
	private HbaseService hbaseService;
	
	@Autowired
	private VideoDao videoDao;

	@Override
	public Map<String, Object> getVideoDataById(String videoId) {
		

        List<byte[]> columnToBack = Arrays.asList(ImgHTableMeta.TIMESTAMP.getBytes(),
        										  ImgHTableMeta.PEOPLENUM.getBytes(),
        										  ImgHTableMeta.DENSITYIMAGE.getBytes(),
        										  ImgHTableMeta.DENSITYLEVEL.getBytes(),
        										  ImgHTableMeta.GROUPIMAGE.getBytes(),
        										  ImgHTableMeta.GROUPNUM.getBytes(),
        										  ImgHTableMeta.WARNIMAGE.getBytes(),
        										  ImgHTableMeta.WARNLEVEL.getBytes()
        										  );
		
		Map<String, String> videoData = new HashMap<String,String>();
		try {
			videoData = hbaseService.getVideoDataByVideoId(videoId, columnToBack);
		} catch (IOException e) {
			LOG.error("获取探头  " + videoId + "的最新数据失败。" , e);
		}
		
		Map<String, Object> metroDataOut=new HashMap<String, Object>() ;
		
		metroDataOut.put("VEDIOID", videoId);
		metroDataOut.put("CTIME", videoData.get(ImgHTableMeta.TIMESTAMP.getName()));
		metroDataOut.put("PEOPLENUM", videoData.get(ImgHTableMeta.PEOPLENUM.getName()));
		metroDataOut.put("DENSITYLEVEL", videoData.get(ImgHTableMeta.DENSITYLEVEL.getName()));
		metroDataOut.put("GROUPNUM", videoData.get(ImgHTableMeta.GROUPNUM.getName()));
		metroDataOut.put("WARNLEVEL", videoData.get(ImgHTableMeta.WARNLEVEL.getName()));
		metroDataOut.put("DENSITYIMAGEPATH", videoData.get(ImgHTableMeta.DENSITYIMAGE.getName()));
		metroDataOut.put("GROUPIMAGEPATH", videoData.get(ImgHTableMeta.GROUPIMAGE.getName()));
		metroDataOut.put("WARNIMAGEPATH", videoData.get(ImgHTableMeta.WARNIMAGE.getName()));
	 
	return  metroDataOut;
	}

	@Override
	public List<Map<String, Object>> getVideoData() {
		
		
		List<String> findAllVideos = findAllVideos();
		
		List<Map<String, String>> alldata = new ArrayList<Map<String,String>>();
		try {
			alldata = hbaseService.getLatestImgMeta(findAllVideos);
		} catch (IOException e) {
			LOG.error("查询视频的最新数据失败。", e);
		}
		
		List<Map<String, Object>> alldataout=new ArrayList<Map<String, Object>>();
		
		for (Map<String, String> map : alldata) {
			Map<String, Object> mapOUT=new HashMap<String, Object>();
            mapOUT.put("VEDIOID", map.get(ImgHTableMeta.CAMERAID.getName()));
            mapOUT.put("CTIME", map.get(ImgHTableMeta.TIMESTAMP.getName()));
            mapOUT.put("PEOPLENUM", map.get(ImgHTableMeta.PEOPLENUM.getName()));
            mapOUT.put("DENSITYLEVEL", map.get(ImgHTableMeta.DENSITYLEVEL.getName()));
            mapOUT.put("GROUPNUM", map.get(ImgHTableMeta.GROUPNUM.getName()));
            mapOUT.put("WARNLEVEL", map.get(ImgHTableMeta.WARNLEVEL.getName()));
            mapOUT.put("DENSITYIMAGEPATH", map.get(ImgHTableMeta.DENSITYIMAGE.getName()));
            mapOUT.put("GROUPIMAGEPATH", map.get(ImgHTableMeta.GROUPIMAGE.getName()));
            mapOUT.put("WARNIMAGEPATH", map.get(ImgHTableMeta.WARNIMAGE.getName()));
            alldataout.add(mapOUT);
 	      }
		return alldataout;
		
	}

	@Override
	public List<String> findAllVideos() {
		List<String> list=new ArrayList<String>();
		for(Video v : videoDao.findAll()){
			list.add(v.getCameraId());
		}
		return list;
	}

	
	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videoDao.findAll();
	}

	@Override
	public List<String> findAllVideoNames() {
		List<Video> objs=videoDao.findAll();
		List<String> videolist=new ArrayList<String>(); 
		String video="";
		for(Video obj:objs){
			video=obj.getCameraName();
			if(!videolist.contains(video)){
				videolist.add(video);
			}
		}
		Collections.sort(videolist);
		return videolist;
	}

	
	public List<Video> findByareaId(String areaId) {
	    List<Video> list=new ArrayList<Video>();
	    
		for(Video vid:videoDao.findAll()){
			if(areaId.equals(vid.getAreaId())){
				list.add(vid);
			}
		}
		
		return list;
	} 


	
	/* (non-Javadoc)
	 * @see com.bdms.dams.video.DayACCVideoService#getVideoDayDataByIdForHighchart(java.lang.String)
	 */
	public Map<String, Object> getVideoDayDataByIdForHighchart(String videoId) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		List<Map<String, Object>> dayData = hbaseService.getVideoDayDataByID(videoId, Arrays.asList(ImgHTableMeta.TIMESTAMP,ImgHTableMeta.PEOPLENUM));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if( !dayData.isEmpty() ){
			
			/* 解析视频的画图数据 */
			List<long[]> data = new ArrayList<long[]>();
			long[] point = null; 
			
			String time = null;
			String count = null;
			
			for(Map<String,Object> map : dayData ){
				
				 point = new long[2];
				 time  = (String) map.get(ImgHTableMeta.TIMESTAMP.getName());
				 count = (String) map.get(ImgHTableMeta.PEOPLENUM.getName());
				 if( time != null){
					 
					 try {
						point[0] = format.parse(time).getTime();
						if(count == null) count="0";
						point[1] = Long.parseLong(count);
						
						data.add(point);
						
					} catch (ParseException e) {
						LOG.error( "时间字串 " +time + " 转成 日期失败。", e);
					}
				 }
				
			}
			
			
			/* 提取最新的数据  */
			Map<String, Object> latestMap = dayData.get(dayData.size() -1 );
			String latestTime = (String) latestMap.get(ImgHTableMeta.TIMESTAMP.getName());
			
			String rowKey = videoId + "-" + latestTime;
			
			try {
				
				Map<String, String> tmpMap = hbaseService.findDataByRowKey(ImgHTableMeta.TABLENAME.getName(),rowKey.getBytes(), 
						ImgHTableMeta.CF.getBytes(), Arrays.asList(ImgHTableMeta.DENSITYLEVEL.getBytes(),ImgHTableMeta.GROUPNUM.getBytes(),ImgHTableMeta.WARNLEVEL.getBytes()));
				
				latestMap.putAll(tmpMap);
				
			} catch (IOException e) {
				LOG.error("获取 表 " + ImgHTableMeta.TABLENAME.getName() + "中的  行号为 "  + rowKey + "的数据失败。" , e);
			}
			
			
			/* 封装 结果数据  */
			result.put("data", data);
			result.putAll(latestMap);
			
		}
		
		return result;
	}

	@Override
	public Video findByCameraId(String cameraId) {
		
		return videoDao.findByCameraId(cameraId);
	} 

}
