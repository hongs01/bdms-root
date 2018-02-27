package com.bdms.dams.metro;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.util.AxisUtil;
import com.bdms.entity.dams.Criterion;
import com.bdms.entity.dams.Dzwl;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbase.util.DSHbaseDateUtil;
import com.bdms.hbse.enums.HistoryPredicteDZWL;
import com.bdms.hbse.enums.StreamingDZWL;

@Service(value="realTimeDZWLService")
public class RealTimeDZWLServiceImpl implements RealTimeDZWLService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RealTimeDZWLServiceImpl.class);

	@Autowired
	private DZWLService dZWLService;
	
	@Autowired
	private HbaseService hbaseService;
	
	@Autowired
	private CriterionService criterionService;
	
	
	/* (non-Javadoc)
	 * @see com.bdms.dams.metro.RealTimeDZWLService#getDZWLRealTimeDataByName(java.lang.String)
	 */
	public Map<String,Object> getDZWLRealTimeYHSDataByName(String qym,String dtime) {
	
		List<StreamingDZWL> columnToBack = Arrays.asList(StreamingDZWL.SJ,StreamingDZWL.YHS,StreamingDZWL.YHSTB,StreamingDZWL.MRS,StreamingDZWL.MRSTB,StreamingDZWL.MCS,StreamingDZWL.MCSTB);
		
		Map<String, String> findAllDzwls = dZWLService.findAllDzwls();
		
		String code = findAllDzwls.get(qym);
		if( StringUtils.isBlank(qym)){
			LOG.error("数据库中不存在 重点区域： " + qym + " 所对应的code." );
			return new HashMap<String,Object>();
		}
		LOG.info("区域名是" + qym + "所对应的日期是"+dtime );
		
		
		
		
		//从mysql中取最小值来修正数据;
		String minValStr="";
		String multiRate="";
		List<Dzwl> dzwlList=dZWLService.findAll();
		for(Dzwl dzwl:dzwlList){
			if(dzwl.getName().equals(qym)){
				minValStr=dzwl.getMin();
				multiRate=dzwl.getMulti();
			}
		}
		if(multiRate.equals("")){
			multiRate="1";
		}
		Long minval=0L;
		if(!minValStr.equals("")){
			minval=Long.parseLong(minValStr);
		}
		
		
		List<Map<String, String>> latestData = hbaseService.getDZWLDayDataByName(code, columnToBack,dtime);
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		if( !latestData.isEmpty()  )
		{
			
			Map<String,ArrayList<long[]>> smap = new HashMap<String, ArrayList<long[]>>();
			long[] row_ren = null;
			long[] row_mr = null;
			long[] row_mc = null;
			
			String sj  = StreamingDZWL.SJ.getName();
			String yhs = StreamingDZWL.YHS.getName();
			String mrs = StreamingDZWL.MRS.getName();
			String mcs = StreamingDZWL.MCS.getName();
			
			List<long[]> list_ren = new ArrayList<long[]>();
			List<long[]> list_mr = new ArrayList<long[]>();
			List<long[]> list_mc = new ArrayList<long[]>();
			
			try 
			{
				for( Map<String, String> map :  latestData )
				{
					
						row_ren = new long[2];
						row_ren[0] = DSHbaseDateUtil.parseDZWLTimeStrToLong(map.get(sj));
						
						//根据minval处理曲线数据
						if(minval==0){
							row_ren[1] = Long.parseLong(map.get(yhs));
						}else{
							if(Long.parseLong(map.get(yhs))>minval){
								row_ren[1] = Math.round((Long.parseLong(map.get(yhs))-minval)*Double.parseDouble(multiRate));
							}else{
								row_ren[1] = 0;
							}
						}
						
						
						list_ren.add(row_ren);
						row_mr = new long[2];
						row_mr[0] = DSHbaseDateUtil.parseDZWLTimeStrToLong(map.get(sj));
						row_mr[1] = Long.parseLong(map.get(mrs));
						row_mc = new long[2];
						row_mc[0] = DSHbaseDateUtil.parseDZWLTimeStrToLong(map.get(sj));
						row_mc[1] = Long.parseLong(map.get(mcs));
						list_mr.add(row_mr);
						list_mc.add(row_mc);
				}
				
				smap.put("ren", (ArrayList<long[]>) list_ren);
				smap.put("mr", (ArrayList<long[]>) list_mr);
				smap.put("mc", (ArrayList<long[]>) list_mc);
				
			} 
			catch (ParseException e)
			{
				LOG.error("时间转换失败。",e);
			}
			
			data.put("data", smap);
			Map<String, String> map = latestData.get(latestData.size() - 1 );
			data.putAll(map);
			
			//根据minval处理最近的一条数据
			Long yhstemp=Long.parseLong((String) data.get("yhs"));
			if(minval!=0){
				if(yhstemp>minval){
					data.put("yhs", Math.round((yhstemp-minval)*Double.parseDouble(multiRate)));
				}else{
					data.put("yhs", 0L);
				}
			}
			
			
			
			//获取最新的预测值
			Map<String, String> h = hbaseService.getHistoryDZWLDataByTimeStrAndName( map.get(StreamingDZWL.SJ.getName()), code, Arrays.asList(HistoryPredicteDZWL.YHS));
			data.put("pyhs", h.get(HistoryPredicteDZWL.YHS.getName()));
			
			//根据minval处理最近的一条历史值数据
			if(data.get("pyhs")!=null){
				Long pyhstemp=Long.parseLong((String) data.get("pyhs"));
				if(minval!=0){
					if(pyhstemp>minval){
						data.put("pyhs", Math.round((pyhstemp-minval)*Double.parseDouble((multiRate))));
					}else{
						data.put("pyhs", 0L);
					}
				}
			}
		}
		
		return data;
		
	}


	@Override
	public Map<String, Object> getDZWLHistoryTimeYHSDataByName(String qym,String dtime) {
		
		
		List<HistoryPredicteDZWL> columnToBackh = Arrays.asList(HistoryPredicteDZWL.SJ,HistoryPredicteDZWL.YHS,HistoryPredicteDZWL.YHSTB,HistoryPredicteDZWL.MRS,HistoryPredicteDZWL.MRSTB,HistoryPredicteDZWL.MCS,HistoryPredicteDZWL.MCSTB);
		
		Map<String, String> findAllDzwls = dZWLService.findAllDzwls();
		
		
		String code = findAllDzwls.get(qym);
		if( StringUtils.isBlank(qym)){
			LOG.error("数据库中不存在 重点区域： " + qym + " 所对应的code." );
			return new HashMap<String,Object>();
		}
		
		String dayStr = "";
		if(dtime==null || dtime.equals("")){
			dayStr = hbaseService.getDZWLLatestTimeStr().split(" ")[0];
			LOG.info("getDZWLHistoryTimeYHSDataByName日期1为"+dayStr);
		}else{
			dayStr = dtime;
			LOG.info("getDZWLHistoryTimeYHSDataByName日期2为"+dayStr);
		}
		
		
		//从mysql中取最小值来修正数据;
		String minValStr="";
		String multiRate="";
		List<Dzwl> dzwlList=dZWLService.findAll();
		for(Dzwl dzwl:dzwlList){
			if(dzwl.getName().equals(qym)){
				minValStr=dzwl.getMin();
				multiRate=dzwl.getMulti();
			}
		}
		if(multiRate.equals("")){
			multiRate="1";
		}
		Long minval=0L;
		if(!minValStr.equals("")){
			minval=Long.parseLong(minValStr);
		}
		
		
		
		List<Map<String, String>> historyData = hbaseService.getHistoryDZWLDataByName(code, columnToBackh,dayStr);
		
		Map<String,Object> data = new HashMap<String,Object>();

		if(!historyData.isEmpty()){
			Map<String,ArrayList<long[]>> smap = new HashMap<String, ArrayList<long[]>>();
			long[] row_ren = null;
			
			String sj  = StreamingDZWL.SJ.getName();
			String yhs = StreamingDZWL.YHS.getName();
			
			List<long[]> list_ren = new ArrayList<long[]>();
			try {
				for( Map<String, String> map :  historyData ){
					
						row_ren = new long[2];
						row_ren[0] = DSHbaseDateUtil.parseDZWLTimeStrToLong(dtime+" "+map.get(sj).split(" ")[1]);
						
						//根据minval处理曲线数据
						if(minval==0){
							row_ren[1] = Long.parseLong(map.get(yhs));
						}else{
							if(Long.parseLong(map.get(yhs))>minval){
								row_ren[1] = Math.round((Long.parseLong(map.get(yhs))-minval)*Double.parseDouble((multiRate)));
							}else{
								row_ren[1] = 0;
							}
						}
						
						list_ren.add(row_ren);
					}
				smap.put("ren", (ArrayList<long[]>) list_ren);
			} catch (ParseException e) {
				LOG.error("时间转换失败。",e);
			}
			
			data.put("data", smap);
			//data.putAll(historyData.get(historyData.size() - 1 ));
		}
		
		setYAxis(data,code);
		
		return data;
		
	}

	
	
	//为指定站点加载y轴范围配置
	private void setYAxis( Map<String, Object> data,String station_id){
			
			Criterion criter = criterionService.findByCodeAndType(station_id, "elecfenceUserNum");
			String level = criter.getLevel();
			if(level == null ){
				LOG.error("数据库中 station_id为" + station_id + "的level字段为空");
			}
			String[] split = level.split(",");
			
			String yAxis = AxisUtil.getYAxis("0",split[0],split[0],split[1],split[1],split[2],split[2],String.valueOf(Integer.MAX_VALUE));
			
			data.put("yAxis", yAxis);
			
		}


	public Map<String, Object> getLDOA(String apname)
	{
		return dZWLService.getLDOA(apname);
	}


	@Override
	public Map<String, Object> getLDOA() 
	{
		return dZWLService.getLDOA();
	}

}
