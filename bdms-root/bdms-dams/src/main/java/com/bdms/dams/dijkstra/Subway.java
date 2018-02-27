/**
 * 
 */
package com.bdms.dams.dijkstra;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.stereotype.Service;



/* 
 * Description:
 * 		计算地铁最短路径
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-11下午1:16:03            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Service
public class Subway {
	
	private List<Station> outList = new ArrayList<Station>();//记录已经分析过的站点
	
	private List<Station> passStionList = new ArrayList<Station>();
	
	private DataBuilder builder;
	
	public DataBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(DataBuilder builder) {
		this.builder = builder;
	}

	
	
	public List<Station> getOutList() {
		return outList;
	}

	public void setOutList(List<Station> outList) {
		this.outList = outList;
	}
	
	

	public List<Station> getPassStionList() {
		return passStionList;
	}

	public void setPassStionList(List<Station> passStionList) {
		this.passStionList = passStionList;
	}

	//计算从s1站到s2站的最短经过路径
	public void calculate(Station s1,Station s2){
		
		if(outList.size() == builder.getTotalStaion()){
			System.out.println("找到目标站点："+s2.getName()+"，共经过"+(s1.getAllPassedStations(s2).size()-1)+"站");
			for(Station station : s1.getAllPassedStations(s2)){
				System.out.print(station.getName()+"->");
				passStionList.add(station);
			}
			return;
		}
		if(!outList.contains(s1)){
			outList.add(s1);
		}
		//如果起点站的OrderSetMap为空，则第一次用起点站的前后站点初始化之
		if(s1.getOrderSetMap().isEmpty()){
			List<Station> Linkedstations = getAllLinkedStations(s1);
			for(Station s : Linkedstations){
				s1.getAllPassedStations(s).add(s);
			}
		}
		Station parent = getShortestPath(s1);//获取距离起点站s1最近的一个站（有多个的话，随意取一个）
		if(parent == s2){
			System.out.println("找到目标站点："+s2+"，共经过"+(s1.getAllPassedStations(s2).size()-1)+"站");
			for(Station station : s1.getAllPassedStations(s2)){
				System.out.print(station.getName()+"->");
				passStionList.add(station);
			}
			return;
		}
		for(Station child : getAllLinkedStations(parent)){
			if(outList.contains(child)){
				continue;
			}
			int shortestPath = (s1.getAllPassedStations(parent).size()-1) + 1;//前面这个1表示计算路径需要去除自身站点，后面这个1表示增加了1站距离
			if(s1.getAllPassedStations(child).contains(child)){
				//如果s1已经计算过到此child的经过距离，那么比较出最小的距离
				if((s1.getAllPassedStations(child).size()-1) > shortestPath){
					//重置S1到周围各站的最小路径
					s1.getAllPassedStations(child).clear();
					s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
					s1.getAllPassedStations(child).add(child);
				}
			} else {
				//如果s1还没有计算过到此child的经过距离
				s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
				s1.getAllPassedStations(child).add(child);
			}
		}
		outList.add(parent);
		calculate(s1,s2);//重复计算，往外面站点扩展
	}
	
	//取参数station到各个站的最短距离，相隔1站，距离为1，依次类推
	private Station getShortestPath(Station station){
		int minPatn = Integer.MAX_VALUE;
		Station rets = null;
		for(Station s :station.getOrderSetMap().keySet()){
			if(outList.contains(s)){
				continue;
			}
			LinkedHashSet<Station> set  = station.getAllPassedStations(s);//参数station到s所经过的所有站点的集合
			if(set.size() < minPatn){
				minPatn = set.size();
				rets = s;
			}
		}
		return rets;
	}
	
	//获取参数station直接相连的所有站，包括交叉线上面的站
	private List<Station> getAllLinkedStations(Station station){
		List<Station> linkedStaions = new ArrayList<Station>();
		for(List<Station> line : builder.getLineSet()){
			if(line.contains(station)){//如果某一条线包含了此站，注意由于重写了hashcode方法，只有name相同，即认为是同一个对象
				Station s = line.get(line.indexOf(station));
				if(s.prev != null){
					linkedStaions.add(s.prev);
				}
				if(s.next != null){
					linkedStaions.add(s.next);
				}
			}
		}
		return linkedStaions;
	}

	
}
