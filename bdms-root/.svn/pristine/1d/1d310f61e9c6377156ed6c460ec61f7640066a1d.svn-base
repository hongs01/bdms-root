package com.bdms.dams.wifi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.video.DayACCVideoServiceImpl;
import com.bdms.dams.wifi.dao.WifiDao;
import com.bdms.entity.dams.Wifi;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.WifiTableMeta;

/* 
 * Description:
 * 		帐号接口实现
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-24 下午5:17:12             1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service("WifiService")
public class WifiServiceImpl implements WifiService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DayACCVideoServiceImpl.class);

	@Autowired
	private HbaseService hbaseService;

	@Autowired
	private WifiDao wifiDao;

	@Override
	public Wifi findByCode(String code) {
		
		return wifiDao.findByCode(code);
	}

	@Override
	public List<Wifi> findAllElecfences() {
		// TODO Auto-generated method stub
		return wifiDao.findAll();
	}

	@Override
	public List<Wifi> findByName(String name) {
		// TODO Auto-generated method stub
		return wifiDao.findByName(name);
	}

	@Override
	public List<Wifi> findAll() {
		// TODO Auto-generated method stub
		return  wifiDao.findAll();
	}
	
	
	/* (non-Javadoc)
	 * @see com.bdms.dams.video.DayACCVideoService#getVideoDayDataByIdForHighchart(java.lang.String)
	 */
	public Map<String, Object> getWifiDayDataByIdForHighchart(String sid) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		List<Map<String, Object>> dayData = hbaseService.getWifiDayDataByID(sid, Arrays.asList(WifiTableMeta.TIME,WifiTableMeta.COUNT));
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if( !dayData.isEmpty() ){
			
			/* 解析视频的画图数据 */
			List<long[]> data = new ArrayList<long[]>();
			long[] point = null; 
			
			String time = null;
			String count = null;
			
			for(Map<String,Object> map : dayData ){
				
				 point = new long[2];
				 time  = (String) map.get(WifiTableMeta.TIME.getName());
				 time = time+"00";
				 count = (String) map.get(WifiTableMeta.COUNT.getName());
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
			/* 封装 结果数据  */
			result.put("data", data);
			
		}
		
		
		return result;
	}

	

}
