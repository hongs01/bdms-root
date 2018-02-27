package com.bdms.dams.wifi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.ResultType;
import com.bdms.hbse.enums.Wifi2Meta;

import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.util.AxisUtil;
import com.bdms.dams.wifi.dao.WifiDataDao;
import com.bdms.entity.dams.Criterion;
import com.bdms.entity.dams.WifiData;

@Service("wifiDataService")
public class WifiDataServiceImpl implements WifiDataService{
   
	private static final Logger LOG = LoggerFactory.getLogger(WifiDataServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private WifiDataDao wifiDataDao;
	
	@Autowired
	private HbaseService hbaseService;
	
	@Autowired
	private CriterionService criterionService;
	
	
	private final static String wifiMysqlConfigPropertiesPath = "system/wifimysql-config.properties";
	static PropertiesConfiguration propertiesConfig=null;
	
	
	@Override
	public List<WifiData> getStations() {
		// TODO Auto-generated method stub
		return wifiDataDao.findAll();
	}
	
	public Map<String, Object> getWifi2DayDataForHighchart(String apNameAndTimeStr)
	{
		Map<String,Object> result = new HashMap<String,Object>();
		
		List<Map<String, Object>> dayData = hbaseService.getWifi2DayData(apNameAndTimeStr, Arrays.asList(Wifi2Meta.TIME,Wifi2Meta.MACOUNT,Wifi2Meta.NUM));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		
		try {
			propertiesConfig=new PropertiesConfiguration(wifiMysqlConfigPropertiesPath);
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
		String multFactor=propertiesConfig.getString("wifi.data.multiple");
		
		if( !dayData.isEmpty() ){
			
			List<long[]> data = new ArrayList<long[]>();
			long[] point = null; 
			
			String time = null;
			String count = null;
			String num = null;
			long c = 0;
			
			for(Map<String,Object> map : dayData )
			{
				
				 point = new long[2];
				 time  = (String) map.get(Wifi2Meta.TIME.getName());
				 count = (String) map.get(Wifi2Meta.MACOUNT.getName());
				 num = (String) map.get(Wifi2Meta.NUM.getName());
				 if( time != null)
				 {
					 try 
					 {
						point[0] = format.parse(time).getTime();
						if(count == null || null == num) 
						{
							c=0;
						}
						else
						{
							 c = (Long.parseLong(count))/(Long.parseLong(num))*Long.parseLong(multFactor);
						}
						point[1] = c;
						data.add(point);
						
					 } 
					 catch (ParseException e)
					 {
						LOG.error( "时间字串 " +time + " 转成 日期失败。", e);
					 }
				 }
				
			}
			
			result.put("data", data);
			
		}
		 setYAxis(result,apNameAndTimeStr.split("-")[0].toString(),ResultType.WIFIDATA);
		return result;
	}
	
	
	private void setYAxis( Map<String, Object> data,String station_id,ResultType rt){
        String type = null;
		
		boolean isStation = true;
		if(station_id.contains("631422656")){
			 switch (rt) {
				case WIFIDATA:
					type = "wifiData";
					break;
				default:
					type = "wifiData";
					break;
				}
			
		}
		Criterion criter = criterionService.findByCodeAndType(station_id, type);

		String level = criter.getLevel();
		if(level == null ){
			LOG.error("数据库中 station_id为" + station_id + "的level字段为空");
		}
		String[] split = level.split(",");
		
		
		int begin = 0;
		String yAxis = "";
		//wifi 
		yAxis = AxisUtil.getYAxis(String.valueOf(begin),split[0],split[0],split[1],split[1],split[2],split[2],String.valueOf(Integer.MAX_VALUE));
		
		data.put("yAxis", yAxis);
		
	}
	/**
	 * 
	按照apname获取wifiData表的全部数据
	*/
	@Override
	public  List<WifiData> getwifiDataTotal(final String apname) {
		Specification<WifiData> specification = new Specification<WifiData>()
				{

					@Override
					public Predicate toPredicate(Root<WifiData> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						List<Predicate> predicate = new ArrayList<>();
						if(apname!=" ")
						{
							predicate.add(cb.equal(
									root.get("apname").as(String.class),
									apname));
						}
						Predicate[] pre = new Predicate[predicate.size()];
						return query.where(predicate.toArray(pre)).getRestriction();
					}
				
				};
				return 	wifiDataDao.findAll(specification);
		 
	}

}
