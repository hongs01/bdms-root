package com.bdms.dams.dzwl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.dzwl.dao.DzwlDao;
import com.bdms.dams.mysqlwifi.schedule.WifiMysqlConn;
import com.bdms.entity.dams.Dzwl;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.StreamingDZWL;


/**
  * Description:
  * 		电子围栏服务实现方法，请大家一定记住写注释
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2016-1-13上午10:21:47            1.0            Created by YuXiaoLin
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service(value="dZWLService")
public class DZWLServiceImpl implements DZWLService{
	@Autowired
	private HbaseService hbaseService;
    @Autowired
	private CriterionService criterionService;
    @Autowired
	private DzwlDao dzwlDao;
	    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DZWLServiceImpl.class);
	@Override
	public List<Map<String, Object>> getDZWLData() {
		// TODO Auto-generated method stub

        List<StreamingDZWL> columnToBack = Arrays.asList(StreamingDZWL.QYM,StreamingDZWL.SJ,StreamingDZWL.YHS);
        	
        Map<String, String> findAllDzwls = findAllDzwlsReversed();
        
        
		List<Map<String, String>> alldata= hbaseService.getDZWLDayData(findAllDzwls.keySet(),columnToBack);
        
		//System.out.println( alldata );
		List<Map<String, Object>> alldataout=new ArrayList<Map<String, Object>>();
		
		String value = null;
		
		for (Map<String, String> map : alldata) {
			
			value = map.get(StreamingDZWL.QYM.getName());
			
			Map<String, Object> mapOUT=new HashMap<String, Object>();
			mapOUT.put("AREAID", value);
			mapOUT.put("AREANAME", findAllDzwls.get(value));
            mapOUT.put("CTIME", map.get(StreamingDZWL.SJ.getName()));
            
			mapOUT.put("PEOPLENUM",  map.get(StreamingDZWL.YHS.getName()));
			
            String[] levelEnter=criterionService.findByCodeAndType(value, "elecfenceUserNum").getLevel().split(",");
    		

    		if(Integer.parseInt(map.get(StreamingDZWL.YHS.getName()))<Integer.parseInt(levelEnter[0])){

    			mapOUT.put("Level",1);

    		}else if((Integer.parseInt(map.get(StreamingDZWL.YHS.getName()))<Integer.parseInt(levelEnter[1]))){

 
    			mapOUT.put("Level", 2);

    		}else if((Integer.parseInt(map.get(StreamingDZWL.YHS.getName()))<Integer.parseInt(levelEnter[2]))){


    			mapOUT.put("Level", 3);
    		}else{
    			mapOUT.put("Level", 4);
    		}
    		alldataout.add(mapOUT);
		}
		return alldataout;
	}

	@Override
	public Map<String, String> getDZWLById(String id) {
		
		 	List<StreamingDZWL> columnToBack = Arrays.asList(StreamingDZWL.QYM,StreamingDZWL.SJ,StreamingDZWL.YHS);        	        
	        
	        Map<String, String> mapOUT = new HashMap<String, String>();
	        
	        Map<String, String> findAllDzwls = findAllDzwlsReversed();
	        
			List<Map<String, String>> alldata = hbaseService.getDZWLDayData(findAllDzwls.keySet(),columnToBack);
	       			
			String value = null;
			
			for (Map<String, String> map : alldata) {
				
				value = map.get(StreamingDZWL.QYM.getName());
				
				if(value.equals(id)){
					
					mapOUT = map;
				}
				
			}
			return mapOUT;
	}

	@Override
	public Map<String,String> findAllDzwls() {
		Map<String,String> map=new HashMap<String,String>();
		for(Dzwl d: dzwlDao.findAll()){
			map.put( d.getName(),d.getCode());
		}
		return map;
	} 
	
	
	public Map<String,String> findAllDzwlsReversed() {
		
		Map<String,String> map=new HashMap<String,String>();
		for(Dzwl d: dzwlDao.findAll()){
			map.put( d.getCode(),d.getName());
		}
		return map;
	}


	public List<Dzwl> findAll() {
		// TODO Auto-generated method stub
		return dzwlDao.findAll();
	}

	public Map<String, Object> getLDOA(String apName) 
	{
		Map<String, Object> data = new HashMap<String,Object>();
		Connection conn = WifiMysqlConn.getConnection();
		PreparedStatement prep = null;
		ResultSet rs = null;
		String tableName = WifiMysqlConn.PREFIX+WifiMysqlConn.MYSQLTABTIME;
		try {
			 prep = conn.prepareStatement("select apname,stime,maccount from "+tableName+" where stime=(select max(stime) from "+tableName+" where apname='"+apName+"') and apname='"+apName+"' ");
			 rs = prep.executeQuery();
			 if(rs.next())
			 {
				 data.put("apname", rs.getString("apname"));
				 data.put("stime", rs.getLong("stime"));
				 data.put("maccount", rs.getInt("maccount"));
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				prep.close();
				rs.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return data;
	}

	public List<Dzwl> findAllEnabled(int enable) {
		return  dzwlDao.getByEnable(enable);
	}

	@Override
	public Map<String, Object> getLDOA()
	{
		Map<String, Object> data = new HashMap<String,Object>();
		List<AP> list = new ArrayList<AP>();
		//String[] strs = null;
		AP ap = null;
		Connection conn = WifiMysqlConn.getConnection();
		PreparedStatement prep = null;
		ResultSet rs = null;
		String tableName = WifiMysqlConn.PREFIX+WifiMysqlConn.MYSQLTABTIME;
		try {
			 prep = conn.prepareStatement("SELECT A.apname,A.stime,A.maccount FROM "+tableName+" A,(SELECT CONCAT(apname,MAX(stime)) AS BB FROM "+tableName+" GROUP BY apname) B WHERE CONCAT(A.apname,A.stime)=B.BB");
			 rs = prep.executeQuery();
			 while(rs.next())
			 {
				 ap = new AP(rs.getString("apname"),rs.getLong("stime"),rs.getInt("maccount"));
				 list.add(ap);
			 }
			 data.put("data", list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
				prep.close();
				rs.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return data;
	}


}

class AP
{
	private String apname;
	private long stime;
	private int maccount;
	
	public AP()
	{
		
	}
	public AP(String apname, long stime, int maccount) {
		this.apname = apname;
		this.stime = stime;
		this.maccount = maccount;
	}
	public String getApname() {
		return apname;
	}
	public void setApname(String apname) {
		this.apname = apname;
	}
	public long getStime() {
		return stime;
	}
	public void setStime(long stime) {
		this.stime = stime;
	}
	public int getMaccount() {
		return maccount;
	}
	public void setMaccount(int maccount) {
		this.maccount = maccount;
	}
	
	
	
}
