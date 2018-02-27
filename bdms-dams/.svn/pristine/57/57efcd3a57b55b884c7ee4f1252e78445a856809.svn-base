package com.bdms.dams.weight.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bdms.dams.area.dao.AreaDao;
import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.dzwl.dao.DzwlDao;
import com.bdms.dams.metro.DayACCMetroService;
import com.bdms.dams.station.dao.StationDao;
import com.bdms.dams.weight.dao.FirstWeightDao;
import com.bdms.dams.weight.dao.SecondWeightDao;
import com.bdms.dams.weight.dao.WeightDao;
import com.bdms.dams.wifi.service.WifiDataService;
import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.AreaFirstSecondWeight;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;

@Service("weightService")
public class WeightServiceImpl implements WeightService {

	private static final Logger LOG = Logger.getLogger(WeightServiceImpl.class);
	
	@Autowired
	private WeightDao weightDao;
	
	@Autowired
	private FirstWeightDao firstweightDao;
	
	@Autowired
	private WifiDataService wifiDataService;
	
	@Autowired
	private SecondWeightDao secondweightDao;

	@Autowired
	private StationDao stationDao;
	
	@Autowired
	private DzwlDao dzwlDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private DayACCMetroService dayACCMetroService;

	@Autowired
	private CriterionService criterionService;

	@Autowired
	private DZWLService dzwlService;

	@Autowired
	private FirstWeightDao firstWeightDao;
	

	@Override
	public List<Map<String, String>> getSecondByTypeId(Integer areaId,
			String typeCode) {

		List<Map<String, String>> list = sortSecondByAreaId(areaId);

		List<Map<String, String>> listOut = new ArrayList<>();

		for (Map<String, String> map : list) {

			Map<String, String> mapOut = new HashMap<>();

			if (typeCode.equals(map.get("typeCode"))) {

				String typeName = map.get("name");
				String name = "";
				if (typeName.equals("stationEnterRT")) {

					name = "进站得分";

				} else if (typeName.equals("stationExitRT")) {

					name = "出站得分";

				} else if (typeName.equals("stationSumRT")) {

					name = "进出和得分";

				} else if (typeName.equals("elecfenceUserNum")) {

					name = "电子围栏得分";
				}

				mapOut.put("count", map.get("count"));
				
				mapOut.put("enterTimes", map.get("enterTimes"));
				
				mapOut.put("name", name);
				
				mapOut.put("secondMark", map.get("secondMark"));
				
				listOut.add(mapOut);
			}
		}
		return listOut;
	}
	
	@Override
	public List<Map<String, String>> getFirstTotalByAreaId(Integer areaId) {

		List<Map<String, String>> list = getFirstByAreaId(areaId);

		String areaName = areaDao.findAreaNameById(areaId).getAreaName();
		
		List<Map<String, String>> listOut = new ArrayList<>();

		double totalMark = 0f;

		String result = null;

		Integer count = 0;

		for (Map<String, String> map : list) {

			Double firstWeight = Double.parseDouble(map.get("firstWeight"));

			Double firstMark = Double.parseDouble(map.get("firstMark"));

			totalMark += firstWeight * firstMark;
		}

		result = ((Long) Math.round(totalMark)).toString();

		count = list.size();

		for (Map<String, String> map : list) {

			Map<String, String> mapOut = new HashMap<>();

			// 获取类型
			String typeName = map.get("typeName");
			String name = "";

			// 获取站点编号
			String station = map.get("typeCode");
			
			if (typeName.equals("metro")) {

				// 获取轨交的站点名称
				name = stationDao.findByStation(station).getName().toString();

			} else if (typeName.equals("dzwl")) {

				// 获取电子围栏的站点名
				name = dzwlDao.findByCode(station).getName().toString();
			}

			mapOut.put("areaName", areaName);
			
			mapOut.put("firstMark", map.get("firstMark"));

			mapOut.put("typeCode", station);

			mapOut.put("typeName", typeName);

			mapOut.put("name", name);

			mapOut.put("totalMark", result);

			mapOut.put("count", count.toString());

			listOut.add(mapOut);
		}

		return listOut;
	}
	
	
	/**
	  * description:  	通过区域id来获取一级列表
	  * @param areaId	区域编号
	  * @return
	  * List<Map<String,String>>
	  * 2014-3-5 下午3:51:29
	  * by Chenfeng
	 */
	private List<Map<String, String>> getFirstByAreaId(Integer areaId) {

		List<Map<String, String>> listOut = new ArrayList<>();

		// 根据areaId得到所有的firstWeight
		List<FirstWeight> firstWeights = weightDao.findFristWeightByAreaId(areaId);

		List<Map<String, String>> maps = sortSecondByAreaId(areaId);

		for (FirstWeight fw : firstWeights) {

			double firstMark = 0;
			String typeCode = fw.getTypeCode();
			Double firstWeight = fw.getWeight();
			
			for (Map<String, String> map : maps) {

				if (typeCode.equals(map.get("typeCode"))) {

					Double secondWeight = Double.parseDouble(map.get("secondWeight"));
					
					Double secondMark = Double.parseDouble(map.get("secondMark"));
					
					firstMark += secondWeight * secondMark;
				}
			}

			Map<String, String> mapOut = new HashMap<>();

			mapOut.put("typeName", fw.getTypeName());
			
			mapOut.put("typeCode", typeCode);
			
			mapOut.put("firstWeight", firstWeight.toString());
			
			mapOut.put("firstMark", firstMark + "");
			
			listOut.add(mapOut);
		}
		return listOut;

	}

	// 得到三张表联合查询的结果
	private List<AreaFirstSecondWeight> getAllByAreaId(Integer id) {

		return weightDao.getAllByAreaId(id);
	}


	/**
	  * description:		得到轨交的进站次数x
	  * @param stationId	轨交站点编号
	  * @return
	  * int
	  * 2014-3-5 下午3:52:30
	  * by Chenfeng
	 */
	
	@Override
	public int getGJETByStationId(String stationId) {

		int enterTimes = 0;

		Map<String, Object> map = dayACCMetroService.getEnterExitPeopleNumById(stationId);

		//map中取出来的实时进站人数map.get("PEOPLENUM")有可能会为空值，如果为空的话，那么视为进站人数为0
		if (map.get("PEOPLENUM") != null) {

			enterTimes = Integer.parseInt((String) map.get("PEOPLENUM"));

		}

		return enterTimes;
	}
	
	//获取轨交进站人数和出站人数
	@Override
	public int getGJEnterExitPeoleNumByStationId(String station,String name){
				
		Map<String, Object> map = dayACCMetroService.getEnterExitPeopleNumById(station);
		
		int peopleNum = 0;

		if("stationEnterRT".equals(name)){
			//进站人数
			if(map.get("PEOPLENUM") != null){
				
				peopleNum = Integer.parseInt((String)map.get("PEOPLENUM"));
			}
			
		}else if("stationExitRT".equals(name)){
			
			//出站人数
			if(map.get("PEOPLENUMOUT") != null){
				
				peopleNum = Integer.parseInt((String)map.get("PEOPLENUMOUT"));
			}
		}else if("stationSumRT".equals(name)){
			
			//进出站人数和
			int peopleEnter = 0;
			int peopleExit = 0;
			if(map.get("PEOPLENUM") != null){
				
				peopleEnter = Integer.parseInt((String)map.get("PEOPLENUM"));
			}
			if(map.get("PEOPLENUMOUT") != null){
				
				peopleExit = Integer.parseInt((String)map.get("PEOPLENUMOUT"));
			}
			peopleNum = peopleEnter + peopleExit;
			
		}else if("stationSubRT".trim().equals(name)){
			
			//进出站人数差
			int peopleEnter = 0;
			int peopleExit = 0;
			if(map.get("PEOPLENUM") != null){
				
				peopleEnter = Integer.parseInt((String)map.get("PEOPLENUM"));
			}
			if(map.get("PEOPLENUMOUT") != null){
				
				peopleExit = Integer.parseInt((String)map.get("PEOPLENUMOUT"));
			}
			peopleNum = Math.abs(peopleEnter - peopleExit);	
			//LOG.info("进出差："+peopleNum);
			
		}
		
		return peopleNum;
		
	}	
	
	/**
	  * description:		得到阈值yList
	  * @param stationId	站点编号(轨交和电子围栏编号)
	  * @param code
	  * @return
	  * List<Integer>
	  * 2014-3-5 下午3:53:16
	  * by Chenfeng
	 */
	
	@Override
	public List<Integer> getLevelByStationId(String stationId, String code) {

		List<Integer> yList = new ArrayList<>();
		
		String[] levelEnter = criterionService.findByCodeAndType(stationId, code).getLevel().split(",");

		for (String string : levelEnter) {

			yList.add(Integer.parseInt(string));
		}

		return yList;
	}
	
	/**
	  * description:	得到电子围栏的进站次数x
	  * @param id		电子围栏的编号
	  * @return
	  * int
	  * 2014-3-5 下午3:54:03
	  * by Chenfeng
	 */
	
	@Override
	public int getDZWLETById(String id) {

		int enterTimes = 0;

		Map<String, String> map = dzwlService.getDZWLById(id);

		//map中取出来的实时进站人数map.get("yhs")有可能会为空值，如果为空的话，那么视为进站人数为0
		if (map.get("yhs") != null) {

			enterTimes = Integer.parseInt(map.get("yhs"));
			
		} 
		return enterTimes;

	}
	
	/**
	  * description:		通过区域编号获取二级列表
	  * @param areaId
	  * @return
	  * List<Map<String,String>>  map中存放相同id(即fistWeight表中的id相同)项
	  * 2014-3-5 下午3:54:44
	  * by Chenfeng
	 */
	private List<Map<String,String>> sortSecondByAreaId(Integer areaId){
		
		List<Map<String,String>> listOut = new ArrayList<>();
		
		// 1、根据areaId得到所有的firstWeight
		List<FirstWeight> firstWeights = weightDao.findFristWeightByAreaId(areaId);		
		
		// 2.根据firstWeight的每个id得到所有的secondWeight
		for(FirstWeight fw : firstWeights){
			
			String typeName = fw.getTypeName();
			
			//获取站点code（stationId）
			String typeCode = fw.getTypeCode();
			
			Double weightFirst = fw.getWeight();
			
			List<SecondWeight> secondWeights = weightDao.findSecondByTypeId(fw.getId());
			
			Integer  count  = secondWeights.size();
	
			for(SecondWeight sw:secondWeights){
			
				String name = sw.getName();	
				Double weightSecond = sw.getWeight();
				int enterTimes = 0;
				Double p = null;
				
				if(typeName.equals("电子围栏")){
					
					//获取电子围栏进站次数
					enterTimes = getDZWLETById(typeCode);
					
					// 得到阈值yList
					List<Integer> yList = getLevelByStationId(typeCode,name);
					
					//计算得分
					p = new Calculate().getCalculate(enterTimes, yList);
										
				}else if (typeName.equals("轨交")){
					
					//获取轨交进站次数
					enterTimes = getGJETByStationId(typeCode);
					
					// 得到阈值yList
					List<Integer> yList = getLevelByStationId(typeCode,name);
					
					//计算得分
					p = new Calculate().getCalculate(enterTimes, yList);
				}
				
				Map<String,String>  mapOut = new HashMap<>();
				
				mapOut.put("count", count.toString());
				
				mapOut.put("enterTimes", enterTimes+"");
				
				mapOut.put("typeName", typeName);
				
				mapOut.put("typeCode", typeCode);
				
				mapOut.put("firstWeight", weightFirst.toString());
				
				mapOut.put("name", name);
				
				mapOut.put("secondMark", p.toString());
				
				mapOut.put("secondWeight", weightSecond.toString());
				
				listOut.add(mapOut);
			}	
		}
		return listOut;
	}
	//测试函数
	@Override
	public List<AreaFirstSecondWeight> getAllById(Integer areaId) {
		
		
		return getAllByAreaId(areaId);
	}

	@Override
	public List<Area> getAreas() {
		
		return areaDao.findAll();
	}

	@Override
	public List<Area> getTopAreasByMark() {
		
		Sort sort = new Sort(Direction.DESC,"totalMark");
		
		return areaDao.findAll(sort);
	}

	@Override
	public Area getAreaAllInfoByAreaId(Integer areaId) {
		
		Area area = areaDao.findAreaNameById(areaId);
		
		List<FirstWeight> firstWeightList = weightDao.findFristWeightByAreaId(areaId);
		
		for(FirstWeight fw:firstWeightList){
			
			String stationId = fw.getTypeCode();
			String typeName = fw.getTypeName();
			
			String stationName = "";
			
			if("dzwl".equals(typeName)){
				
				//stationName = dzwlDao.findByCode(stationId).getName().toString();
				stationName = dzwlDao.findByCode(stationId).getOname().toString();
			}else if("metro".equals(typeName)){
				
				stationName = stationDao.findByStation(stationId).getName().toString();
				
			}else if("wifiData".equals(typeName)){
				
				stationName = wifiDataService.getwifiDataTotal(stationId).get(0).getStationName().toString();
			}
			
			List<SecondWeight> secondWeightList = weightDao.findSecondByTypeId(fw.getId());
			
			fw.setSecondWeightList(secondWeightList);
			fw.setStationName(stationName);
		}
		
		area.setFirstWeightList(firstWeightList);
		
		return area;
	}

	@Override
	public Area getTopestMark() {
		List<Area> areas = getTopAreasByMark();
		return areas.get(0);
	}

	@Override
	public List<FirstWeight> getFirstWeighsByAreaId(Integer areaId) {
		
		return weightDao.findFristWeightByAreaId(areaId);
	}

	@Override
	public List<SecondWeight> getSecondWeightByTypeId(Integer typeId) {
		
		return weightDao.findSecondByTypeId(typeId);
	}
	
	@Override
	public Page<Area> findAllWhitPage(int page, int rows, final String areaId,
			final String areaName, final double totalMark ) {
		Sort sort = new Sort(Direction.DESC, "totalMark"); // 按照更新时间倒叙排序
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 构建动态查询
		Specification<Area> specification = new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (areaId != null && areaId != "") {
					predicate.add(cb.like(
							root.get("areaId").as(String.class), "%"
									+ areaId + "%"));
				}
				if (areaName != null && areaName != "") {
					predicate.add(cb.like(
							root.get("areaName").as(String.class),
							areaName));
				}
				if (totalMark != 0) {
					predicate.add(cb.le(
							root.get("totalMark").as(Double.class),
							totalMark));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};

		return areaDao.findAll(specification, pageable);
	}
/*
	@Override
	public FirstWeight getFirstAllById(Integer id) {
		// TODO Auto-generated method stub
		return   firstweightDao.findAll(id);
	}
*/
	
    //向firstWeight表中保存一条记录
	@Override
	public FirstWeight saveFirst(FirstWeight firstweight) {
		// TODO Auto-generated method stub
		return firstweightDao.save(firstweight);
	}
	
	//根据Id获取一级表的一条数据
	@Override
	public FirstWeight getFirstById(Integer id) {
		// TODO Auto-generated method stub
		return firstweightDao.findOne(id);
	}

	//根据Id删除firstWeight表的一条记录
	@Override
	public void delFirst(Integer id) {
			try {
				firstweightDao.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/*向secondWeight中保存一条数据*/
	@Override
	public SecondWeight saveSecond(SecondWeight secondweight) {
		// TODO Auto-generated method stub
		return secondweightDao.save(secondweight);
	}

	/*根据Id获取secondWeight表的一条数据*/
	@Override
	public SecondWeight getSecondById(int id) {
		// TODO Auto-generated method stub
		return secondweightDao.findOne(id);
	}

	/*根据Id删除secondWeight表的一条数据*/
	@Override
	public void delSecond(Integer id) {
		// TODO Auto-generated method stub
		try {
			secondweightDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*按页获取一级数据
	 *  (non-Javadoc)
	 * @see com.bdms.dams.weight.service.WeightService#findAllFirstPage(int, int, int, java.lang.String)
	 */
	@Override
	public Page<FirstWeight> findAllFirstPage(int page, int rows, final int id,
			final String typeName) {
		Sort sort = new Sort(Direction.DESC, "firstMark"); // 按照总分倒叙排序
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 构建动态查询
		Specification<FirstWeight> specification = new Specification<FirstWeight>() {
			@Override
			public Predicate toPredicate(Root<FirstWeight> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (typeName != null && typeName != "") {
					predicate.add(cb.like(
							root.get("typeName").as(String.class), "%"
									+ typeName + "%"));
				} 
				String point =id+"";
				if (!point.equals("")) {
					predicate.add(cb.equal(
							root.get("id").as(Integer.class), "%"
									+ id + "%"));
				} 
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
		return firstweightDao.findAll(specification, pageable);
	
	}
 
	/* 按页获取二级数据
	 * (non-Javadoc)
	 * @see com.bdms.dams.weight.service.WeightService#findAllSecondPage(int, int, int, java.lang.String)
	 */
	@Override
	public Page<SecondWeight> findAllSecondPage(int page, int rows, final int id,
			final String name) {
		Sort sort = new Sort(Direction.DESC, "secondMark"); // 按照总分倒叙排序
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 构建动态查询
		Specification<SecondWeight> specification = new Specification<SecondWeight>() {
			@Override
			public Predicate toPredicate(Root<SecondWeight> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (name != null && name != "") {
					predicate.add(cb.like(
							root.get("name").as(String.class), "%"
									+ name + "%"));
				} 
				String point =id+"";
				if (!point.equals("")) {
					predicate.add(cb.equal(
							root.get("id").as(Integer.class), "%"
									+ id + "%"));
				} 
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
		return secondweightDao.findAll(specification, pageable);
	}
}
 

