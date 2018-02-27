package com.bdms.dams.weight.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bdms.dams.area.dao.AreaDao;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.dzwl.dao.DzwlDao;
import com.bdms.dams.metro.RealTimeDZWLService;
import com.bdms.dams.weight.dao.AreaUpdate;
import com.bdms.dams.weight.dao.FirstWeightDao;
import com.bdms.dams.weight.dao.SecondWeightDao;
import com.bdms.dams.weight.dao.WeightDao;
import com.bdms.dams.weight.service.Calculate;
import com.bdms.dams.weight.service.WeightService;
import com.bdms.dams.wifi.service.WifiDataService;
import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.Dzwl;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;
import com.bdms.hbase.service.HbaseService;
import com.bdms.hbse.enums.HistoryPredicteDZWL;

@Component
public class OnTimeUpdateTask {

	@Autowired
	private WeightService weightService;
	
	@Autowired
	private WeightDao weightDao;
	
	@Autowired
	private SecondWeightDao secondWeightDao;
	
	@Autowired
	private FirstWeightDao firstWeightDao;
	
	@Autowired 
	private AreaDao areaDao;
	
	@Autowired
	private AreaUpdate areaUpdate;
	
	@Autowired
	private WifiDataService wifiDataService;
	
	@Autowired
	private RealTimeDZWLService realTimeDZWLService;
	
	@Autowired
	private DzwlDao  dzwlDao;
	
	@Autowired
	private HbaseService hbaseService;
	
	@Autowired
	private DZWLService dzwlService;
	
	/** Logger */    
	private static final Logger LOG = Logger.getLogger(OnTimeUpdateTask.class);     
	 
	@Scheduled(cron="0 0/1 * * * ? ") //每隔5分钟触发一次
	public void timeTask() {

		LOG.info("定时任务开始执行sssssssss" + new Date().getMinutes());

		// 计算secondWeight表中的secondMark值并更新到数据库中

		updateSecondWeight(secondWeightDao.findAll());

		// 计算firstMark值并更新到firstWeight表中

		updateFirstWeight(firstWeightDao.findAll());

		// 计算totalMark值并更新到area表中
		updateArea(areaDao.findAll());

		LOG.info("定时任务结束" + new Date().getMinutes());

	}

	//计算totalMark值并更新到area表中
	private void updateArea(List<Area> areas){
		
		for(Area area : areas){
			
			Integer areaId =  area.getId();
			double totalMark = 0f;
			int alarmCount = 0;
			
			int alarmLevel = 0;
			List<FirstWeight> firstWeights = weightDao.findFristWeightByAreaId(areaId);
			List<Integer> alarmLevels = new ArrayList<>();
			
			long DZWLNum = 0;			//电子围栏实时人数
			int increment = 0;			//区域的人数增量
			
			Dzwl dzwl = null;
			long StreamPreNum = 0L;		//电子围栏预测值
			
			long allSub = 0L;			//所有站点进出站差的和
			
			for(FirstWeight fw:firstWeights){
				
				totalMark += fw.getFirstMark()*fw.getWeight();
				
				if(fw.getAlarmLevel()>= 2){
					
					alarmCount ++;
				}
				alarmLevels.add(fw.getAlarmLevel());

				//计算所有站点的进出站差的和
				if(fw.getTypeName().equals("metro")){
					
					allSub += weightService.getGJEnterExitPeoleNumByStationId(fw.getTypeCode(), "stationSubRT");
					//LOG.info("所有站点的进出站差的和的值是：" + allSub);
					
				}
				
				//计算电子围栏下一个10分钟的实时预测值
				if(fw.getTypeName().equals("dzwl")){
					
					List<Dzwl> dzwls = dzwlDao.findAll();
					
					for(Dzwl d :dzwls){
						
						if(fw.getTypeCode().equals(d.getCode())){
							
							dzwl = d;
						}
					}
					
					Map<String, String> map = dzwlService.getDZWLById(fw.getTypeCode());
					
					DZWLNum =Math.round((Integer.parseInt(map.get("yhs"))- Integer.parseInt(dzwl.getMin()))* Double.parseDouble(dzwl.getMulti()));
					String sj = map.get("sj").toString();
					//LOG.info("时间的值是sj："+sj);

					List<Map<String,String>> hissublist = getHistoryPeopleSub(fw.getTypeCode(),sj);
					
					//LOG.info("两个历史值是："+hissublist.get(0).get("yhs")+"-------------"+hissublist.get(1).get("yhs"));
//					
					Long hisyhs1 = Math.round((Long.parseLong(hissublist.get(0).get("yhs")) - Long.parseLong(dzwl.getMin())) * Double.parseDouble(dzwl.getMulti()));
					
					Long hisyhs2 = Math.round((Long.parseLong(hissublist.get(1).get("yhs"))- Long.parseLong(dzwl.getMin())) * Double.parseDouble(dzwl.getMulti()));
					//LOG.info("两个历史值计算后是："+hisyhs1+"-------------"+hisyhs2);
					
					if( hisyhs1 != 0 ){
						
						StreamPreNum = Math.round(DZWLNum * hisyhs2 / hisyhs1);
					}	
										
				}			
			}	
			
			increment = Math.round((StreamPreNum - DZWLNum - allSub) / 10);
			//选出alarmLevel最大值	
			//alarmLevel = Collections.max(alarmLevels);
			
			//根据得分来设置告警级别
			alarmLevel = setAlarmLevel(totalMark);
			//LOG.info(areaId+"updateArea 中 alarmLevel最大值是:"+alarmLevel);
			areaUpdate.updateTotalMark(totalMark,alarmCount,alarmLevel, DZWLNum,increment,areaId);
		}
		
	}
	
	//获取当前时间与后10分钟历史值得人数差
	private List<Map<String,String>> getHistoryPeopleSub(String stationId,String date){
		
		//LOG.info("getHistoryPeopleSub'date is :"+date);
		
		List<HistoryPredicteDZWL> columnToBackh = Arrays.asList(HistoryPredicteDZWL.SJ,HistoryPredicteDZWL.YHS,HistoryPredicteDZWL.YHSTB,HistoryPredicteDZWL.MRS,HistoryPredicteDZWL.MRSTB,HistoryPredicteDZWL.MCS,HistoryPredicteDZWL.MCSTB);
				
		List<Map<String,String>> list = hbaseService.getDZWLHistorySubData(stationId,columnToBackh,date);
		
		//LOG.info("getHistoryPeopleSub'date:"+list.size());
		
		return list;
		
	}	
	//计算firstWeight表中的firstMark值并更新到数据库中
	private void updateFirstWeight(List<FirstWeight> firstWeights){
		
		for(FirstWeight fw:firstWeights){
			
			double firstMark = 0;
			//String typeCode = fw.getTypeCode();
			Integer id = fw.getId();
			
			int alarmCount = 0;
			int alarmLevel = 0;
			
			List<SecondWeight> secondWeights =  weightDao.findSecondByTypeId(id);
			
			List<Integer> alarmLevels = new ArrayList<>();
			
			for(SecondWeight sw:secondWeights){
				
				firstMark += sw.getWeight() * sw.getSecondMark();
								
				if(sw.getAlarmLevel()>= 2){
					//如果分数大于60分，那么告警数目+1
					alarmCount ++;
				}
				
				alarmLevels.add(sw.getAlarmLevel());
			}
//			LOG.info(fw.getId()+"的firstMark:"+firstMark);	
			//选出alaemLevel最大值	
			//alarmLevel = Collections.max(alarmLevels);
			
			//根据得分来设置告警级别
			alarmLevel = setAlarmLevel(firstMark);
			//LOG.info(typeCode+"updateFirsrWeight 中 alarmLevel最大值是:"+alarmLevel);
			
			firstWeightDao.updateFirstMark(firstMark, alarmCount,alarmLevel,fw.getId());
		}		
		
	}

	
	//计算secondWeight表中的secondMark值并更新到数据库中
	private void updateSecondWeight(List<SecondWeight> secondWeights){
		
		for(SecondWeight sw: secondWeights){
			
			String name = sw.getName();
			
			String stationId = sw.getStationId();
			
			int id = sw.getId();
			
			int peopleNum = 0;		
			
			double secondMark = 0f;
			
			int alarmLevel = 0;
			
			if(name.contains("station")){
							
				//得出人数(进站、出站、进出和、进出差)
				peopleNum = weightService.getGJEnterExitPeoleNumByStationId(stationId, name);
				
				//得到yList
				List<Integer> yList = weightService.getLevelByStationId(stationId,name);
				
				//计算得分
				secondMark = new Calculate().getCalculate(peopleNum, yList);
				//LOG.info("计算得分     stationId:"+stationId+"----"+"name:"+name+"----"+"peopleNum:"+peopleNum+"----"+"secondMark:"+secondMark);
						
			}else if("elecfenceUserNum".equals(name)){
				
				peopleNum = weightService.getDZWLETById(stationId);
				
				//得到yList
				List<Integer> yList = weightService.getLevelByStationId(stationId,name);
				
				//计算得分
				secondMark = new Calculate().getCalculate(peopleNum, yList);
			}else if("wifiData".equals(name)){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				
				java.util.Date date = new java.util.Date();
				
				String time = sdf.format(date);
				
				String apNameAndTimeStr = stationId+"-"+time;
				//String apNameAndTimeStr= "6314226560034CB80EBF2-20140327";
				List<String[]> listOut = (List<String[]>) wifiDataService.getWifi2DayDataForHighchart(apNameAndTimeStr).get("data");
				peopleNum = Integer.parseInt(listOut.get(listOut.size()-1)[1]);
				//LOG.info("wifiData人数："+peopleNum);
				//得到yList
				List<Integer> yList = weightService.getLevelByStationId(stationId,name);
				
				//计算得分
				secondMark = new Calculate().getCalculate(peopleNum, yList);
			}
			
			//根据得分来设置告警级别
			alarmLevel = setAlarmLevel(secondMark);
			//将人数和得分更新到secondWeight表中
			//LOG.info("更新secondWeight表："+id+"-----"+peopleNum+"----"+"secondMark");			
			secondWeightDao.updateSecondWeight(peopleNum, secondMark,alarmLevel, id);
			
		}
	}
	
	//根据得分(secondMark)设置告警级别
	private int setAlarmLevel(double mark) {

		int alarmLevel = 0;

		if (mark < 60f) {

			alarmLevel = 1;

		} else if ((60f <= mark) || (mark < 80f)) {

			alarmLevel = 2;

		} else if ((80f <= mark) || (mark < 90f)) {

			alarmLevel = 3;

		} else {

			alarmLevel = 4;
		}

		return alarmLevel;
	}
}