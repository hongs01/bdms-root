package com.bdms.web.dams.metro.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bdms.dams.dijkstra.DataBuilder;
import com.bdms.dams.dijkstra.Station;
import com.bdms.dams.dijkstra.Subway;
import com.bdms.dams.metro.DayACCService;
import com.bdms.dams.station.service.StationService;
import com.bdms.hbase.service.HbaseService;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Map;
import com.sun.tools.javac.code.Attribute.Array;

/* 
 * Description:
 * 		 echarts轨道交通OD热门线路展示
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-3下午4:59:30            1.0            Created by Hongs
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Controller
@RequestMapping(value = "/dams/metro/od")
public class MetroODController {

	@Autowired
	private DayACCService accService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private DataBuilder builder;
	
	@Autowired 
	private Subway subway;
	
	@Autowired 
	private HbaseService  hbaseService;
	/**
	 * description:
	 * 跳转到轨道交通OD热门线路展示界面
	 * @return
	 * String
	 * 2015-8-3 下午5:02:27
	 * by Hongs
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public String od() {
		return "dams/metro/ODhot";
	}
	
	@RequestMapping(value = "odheat", method = { RequestMethod.GET })
	public String odheat() {
		return "dams/metro/ODheat";
	}
	
	@RequestMapping(value = "od", method = { RequestMethod.GET })
	public String od1() {
		return "dams/metro/OD";
	}
	
	
	/**
	 * description:
	 * 跳转到showMap界面
	 * @return
	 * String
	 * 2015-8-12 下午2:24:06
	 * by Hongs
	 */
	@RequestMapping(value = "showmap", method = { RequestMethod.GET })
	public ModelAndView showmap() {
		java.util.Map<String,Object> map=new HashMap<String,Object>();
		map.put("topimg", "titleName");
		return new ModelAndView("dams/metro/showMap",map);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "oddatao", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<String> getOddata( ) throws UnsupportedEncodingException {
		
		/*java.util.Map<String, Object> model=new HashMap<String, Object>();
		
		
		List<java.util.Map<String, String[]>> listgis=new ArrayList<>();
		
		
		java.util.Map<String, String[]> map1=new java.util.HashMap<String, String[]>();
		
		String[] gis1=new String[]{"273.85","426.62"};
		String[] gis2=new String[]{"365.71","409.11"};
		map1.put("肇嘉浜路", gis1);
		map1.put("商城路",gis2);
		
		listgis.add(map1);
		
		
		
		java.util.Map<String, String> mapName1=new HashMap<String,String>();
		java.util.Map<String, String> mapName2=new HashMap<String,String>();
		mapName1.put("name", "肇嘉浜路");
		mapName2.put("name", "商城路");
		
		List<java.util.Map<String, String>> listName =new ArrayList<>();
		listName.add(mapName1);
		listName.add(mapName2);
		
		List<String> listname=new ArrayList<>();
		
		listname.add("{\"商城路\":[\"365.71\",\"409.11\"],\"肇嘉浜路\":[\"273.85\",\"426.62\"]}");
		listname.add("[{\"name\":\"肇嘉浜路\"},{\"name\":\"商城路\"}]");
		 
		model.put("geoCoord", listgis);
		model.put("name", listName);
		*/
		return accService.dayODtopNData(10);
	}
	
	
	/**
	 * description:
	 * 获取返回热力图数据
	 * @return
	 * @throws UnsupportedEncodingException
	 * List<String>
	 * 2015-8-11 下午1:40:23
	 * by Hongs
	 */
	@ResponseBody
	@RequestMapping(value = "odheatdata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getOdheatdata( ) throws UnsupportedEncodingException {
		 
		
		return accService.getStationDayLatestEnterDataTopN(0, 10);
	}
	
	
	/**
	 * description:
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * Option
	 * 2015-8-5 下午5:21:19
	 * by Hongs
	 */
	@ResponseBody
	@RequestMapping(value = "oddata", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getOdData() throws UnsupportedEncodingException {
		Option option=new Option();
		
		option.backgroundColor("#eee");
		option.title("OD轨道交通热门线路示意图");
		 option.title().textStyle().color("#000");
		
		 option.tooltip().trigger(Trigger.item)
         .formatter("{b}");
		
	       option.color("rgba(218, 70, 214, 1)").color("rgba(100, 149, 237, 1)").color("green");

		 option.legend("线路1");
		//option.legend("线路2");
		
		 
       
        Map map1=new Map();
       /* option.series(map1);
        GeoCoord geo= map1.geoCoord();
        
        //map1.geoCoord("zzz", "2344.89", "343").geoCoord(name, x, y)
        geo.put("zzz", "2344.89", "343");
        geo.put(key, x, y);*/   
        java.util.Map<String,String> a = null;
        java.util.Map<String,String> b = null;
        
        a = new HashMap<String,String>();
        a.put("name", "肇嘉浜路");
        b = new HashMap<String,String>();
        b.put("name", "商城路");
        map1.name("线路1");
        map1.type(SeriesType.map);
        map1.mapType("OD");
        
       
        map1.roam(true);
        map1.itemStyle().normal().label().show(true);
        map1.itemStyle().emphasis().label().show(true);
        
        map1.data();
        map1.geoCoord();
        /*map1.geoCoord("肇嘉浜路","273.85", "426.62")
        .geoCoord("商城路", "365.71", "409.11")
        .geoCoord("松江南站", "37.23", "531.02")
        .geoCoord("松江新城", "37.23", "494.34")
        .geoCoord("九亭", "93.98", "438.65");*/
       
        map1.markPoint().symbolSize(3);
        map1.markPoint().data(a,b);
                       /* .data("松江南站")
                        .data("松江新城")
                        .data("九亭");*/
      
        
        map1.markLine().smooth(true);
        map1.markLine().effect().show(true)
        .scaleSize(1)
        .period(20)
        .color("#fff")
        .shadowBlur(5);
        /*java.util.Map<String,String> a = null;
        java.util.Map<String,String> b = null;*/
         
        map1.markLine().symbol("none");
        map1.markLine().itemStyle().normal().borderWidth(1)
              .lineStyle().type();
        
      /*  a = new HashMap<String,String>();
        a.put("name", "肇嘉浜路");
        b = new HashMap<String,String>();
        b.put("name", "商城路");*/
         
        
        map1.markLine().data(a,b);
        
       /* map1.markLine().data([
                   {name:'肇嘉浜路'}, 
                   {name:'商城路'}      //从一个站点流向另一个站点
               ],
               [
                    {name:'九亭'}, 
                    {name:'肇嘉浜路'}
                ],
                [
                 {name:'松江南站'}, 
                 {name:'松江新城'}
               ],
               [
                   {name:'松江新城'}, 
                   {name:'九亭'}
               ]);*/
        
        option.series(map1);
        /*return option;*/
        return JSON.toJSONString(option);
	}
	
	/**
	 * 获取热门线路的最短路路径
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "odshortestpath", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<String> getODShortestPath() throws UnsupportedEncodingException {
		String odpath="";
		
		//List<String> odtoplist =  accService.dayODtopNStaionPairs(10);
		List<String> odtoplist =  accService.dayODtopNStaionPairs(10);
		//List<String> odtoplist = Arrays.asList("0247-0246", "0126-0835", "0118-0111", "0111-0118", "0246-0247", "0926-0931", "0247-0123", "0235-0234", "0126-0111", "0111-0835");
		builder.init();
		
		StringBuffer sb1=new StringBuffer();
		StringBuffer sb2=new StringBuffer();
		StringBuffer sb3=new StringBuffer();
		sb1.append("{");
		sb2.append("[");
		sb3.append("[");
		
		List<Station> listS = null;
		com.bdms.entity.dams.Station findByName = null;
		Station s2=null;
		int nn = 0;
		for(String str:odtoplist){
			 nn += 1;
			Subway subway=new Subway();
			subway.setBuilder(builder);	
			
			long t1 = System.currentTimeMillis();
			subway.calculate(new com.bdms.dams.dijkstra.Station(stationService.findByStation(str.split("-")[0].trim()).getName()), new com.bdms.dams.dijkstra.Station(stationService.findByStation(str.split("-")[1].trim()).getName()));
			//subway.calculate(new com.bdms.dams.dijkstra.Station("富锦路"), new com.bdms.dams.dijkstra.Station("莘庄"));
			//System.err.println( odtoplists );
			//System.err.println( odtoplist );
			//System.err.println( nn + "******************" +  (System.currentTimeMillis() - t1 ) + "*******************" );
			
			listS=subway.getPassStionList();
			
			for(int i=0;i<listS.size();i++){
				
				s2=listS.get(i);
				
				findByName = stationService.findByName(s2.getName());
				Double x = findByName.getX();
				Double y = findByName.getY();
				
				odpath += s2.getName()+"("+x+","+y+"),";
				
				//"商城路":["365.71","409.11"],"肇嘉浜路":["273.85","426.62"]
				sb1.append("'");
				sb1.append( s2.getName());
				sb1.append("':[");
				sb1.append(x);
				sb1.append( ",");
				sb1.append(y);
				sb1.append("],");
				
				//{"name":"肇嘉浜路"},{"name":"商城路"}
				sb2.append("{");
				sb2.append("name:'");
				sb2.append( s2.getName());
				sb2.append( "'},");
				
				if( i < (listS.size()-1) ){
					
					sb3.append("[");
					sb3.append("{");
					sb3.append("name:'");
					sb3.append( s2.getName());
					Station next=null;
					
					sb3.append("'},{name:'");
					sb3.append( listS.get(i+1).getName());
					sb3.append("'");
					sb3.append( "}],");
				}
				
			}
		}
		sb1.deleteCharAt(sb1.length()-1);
		sb2.deleteCharAt(sb2.length()-1);
		sb3.deleteCharAt(sb3.length()-1);
		
		sb1.append("}");
		sb2.append("]");
		sb3.append("]");
		
		List<String> res = new ArrayList<String>();
		res.add(sb1.toString());
		res.add(sb2.toString());
		res.add(sb3.toString());
		res.add(sb2.toString());
		
		return  res;
	}
	
	/**
	 * 获取热门线路的站点名称
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "odshortestPairs", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<String> getODShortestPairs() throws UnsupportedEncodingException {
		List<String> pairList=new ArrayList<String>();
		for(String str:accService.dayODtopNStaionPairs(10)){
			String pair="";
			pair+=stationService.findByStation(str.split("-")[0]).getName()+"-"+stationService.findByStation(str.split("-")[1]).getName();
			pairList.add(pair);
		}
		return pairList;
	}
	
	@ResponseBody
	@RequestMapping(value = "odpersonnum", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<java.util.Map<String, Object>> getODshortestPersonNum(int offset) throws UnsupportedEncodingException {
		 
		return accService.dayODtopNStaionNum(offset,10);
	}
	
	/**
	 * 获取热门图in信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataIn", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataIn() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterDataTopNForRight(10));
	}
	
	/**
	 * 获取热门图out信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataOut", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataOut() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestExitDataTopNForRight(10));
	}
	
	/**
	 * 获取热门图inoutsub信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataSub", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataSub() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterExitSubDataTopNForRight(10));
	}
	
	/**
	 * 获取热门图inoutsum信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "fheatDataSum", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getFheatDataSum() throws UnsupportedEncodingException {
		return JSON.toJSONString(accService.getStationDayLatestEnterExitSumDataTopNForRight(10));
	}
	
	
	
	/**
	  * description:根据日期获取一天的热门OD数据
	  * @param dateTime
	  * @param offset
	  * @return
	  * @throws UnsupportedEncodingException
	  * List<java.util.Map<String,Object>>
	  * 2014-4-3 上午10:13:40
	  * by Lixc
	 */
	@ResponseBody
	@RequestMapping(value = "oneDayODData/{dateTime}/{offset}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<java.util.Map<String, Object>> dayODtopNStaionNumForOneDay(@PathVariable(value = "dateTime")  String dateTime,
			@PathVariable(value = "offset")  Integer offset) throws UnsupportedEncodingException {
		 
		return accService.getODtopNStaionNumForOneDay(dateTime,offset, 10);
	}
	
	//test hbase
	@ResponseBody
	@RequestMapping(value = "testHbase/{dateTime}/{offset}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<java.util.Map<String, Object>> teseHbase(@PathVariable(value = "dateTime")  String dateTime,
			@PathVariable(value = "offset")  int offset) throws UnsupportedEncodingException{
		
		List<java.util.Map<String, Object>> list = null;
		try {
			
			list = hbaseService.getODtopNDataForOneDay(dateTime, offset, 10);

		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "odShortestPath/{dateTime}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<String> getODShortestPathForOneDay(@PathVariable(value = "dateTime")  String dateTime) throws UnsupportedEncodingException {
		String odpath="";
		
		//List<String> odtoplist =  accService.dayODtopNStaionPairs(10);
		List<String> odtoplist =  accService.getODtopNStaionPairsForOneDay(dateTime,10);
		//List<String> odtoplist = Arrays.asList("0247-0246", "0126-0835", "0118-0111", "0111-0118", "0246-0247", "0926-0931", "0247-0123", "0235-0234", "0126-0111", "0111-0835");
		builder.init();
		
		StringBuffer sb1=new StringBuffer();
		StringBuffer sb2=new StringBuffer();
		StringBuffer sb3=new StringBuffer();
		sb1.append("{");
		sb2.append("[");
		sb3.append("[");
		
		List<Station> listS = null;
		com.bdms.entity.dams.Station findByName = null;
		Station s2=null;
		int nn = 0;
		for(String str:odtoplist){
			 nn += 1;
			Subway subway=new Subway();
			subway.setBuilder(builder);	
			
			long t1 = System.currentTimeMillis();
			subway.calculate(new com.bdms.dams.dijkstra.Station(stationService.findByStation(str.split("-")[0].trim()).getName()), new com.bdms.dams.dijkstra.Station(stationService.findByStation(str.split("-")[1].trim()).getName()));
			//subway.calculate(new com.bdms.dams.dijkstra.Station("富锦路"), new com.bdms.dams.dijkstra.Station("莘庄"));
			//System.err.println( odtoplists );
			//System.err.println( odtoplist );
			//System.err.println( nn + "******************" +  (System.currentTimeMillis() - t1 ) + "*******************" );
			
			listS=subway.getPassStionList();
			
			for(int i=0;i<listS.size();i++){
				
				s2=listS.get(i);
				
				findByName = stationService.findByName(s2.getName());
				Double x = findByName.getX();
				Double y = findByName.getY();
				
				odpath += s2.getName()+"("+x+","+y+"),";
				
				//"商城路":["365.71","409.11"],"肇嘉浜路":["273.85","426.62"]
				sb1.append("'");
				sb1.append( s2.getName());
				sb1.append("':[");
				sb1.append(x);
				sb1.append( ",");
				sb1.append(y);
				sb1.append("],");
				
				//{"name":"肇嘉浜路"},{"name":"商城路"}
				sb2.append("{");
				sb2.append("name:'");
				sb2.append( s2.getName());
				sb2.append( "'},");
				
				if( i < (listS.size()-1) ){
					
					sb3.append("[");
					sb3.append("{");
					sb3.append("name:'");
					sb3.append( s2.getName());
					Station next=null;
					
					sb3.append("'},{name:'");
					sb3.append( listS.get(i+1).getName());
					sb3.append("'");
					sb3.append( "}],");
				}
				
			}
		}
		sb1.deleteCharAt(sb1.length()-1);
		sb2.deleteCharAt(sb2.length()-1);
		sb3.deleteCharAt(sb3.length()-1);
		
		sb1.append("}");
		sb2.append("]");
		sb3.append("]");
		
		List<String> res = new ArrayList<String>();
		res.add(sb1.toString());
		res.add(sb2.toString());
		res.add(sb3.toString());
		res.add(sb2.toString());
		
		return  res;
	}
		
}
