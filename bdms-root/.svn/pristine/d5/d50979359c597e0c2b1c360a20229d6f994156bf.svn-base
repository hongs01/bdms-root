package com.bdms.dams.dijkstra;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/* 
 * Description:
 * 		dijkstra算法中，地铁站实体
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-11下午12:56:21            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class Station {

	private String name; // 地铁站名称，假设具备唯一性

	public Station prev; // 本站在lineNo线上面的前一个站

	public Station next; // 本站在lineNo线上面的后一个站

	// 本站到某一个目标站(key)所经过的所有站集合(value)，保持前后顺序
	private Map<Station, LinkedHashSet<Station>> orderSetMap = new HashMap<Station, LinkedHashSet<Station>>();

	public Station(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * description:获取经过站
	 * 
	 * @param station
	 * @return LinkedHashSet<Station> 2015-8-11 下午1:09:32 by Yuxl
	 */
	public LinkedHashSet<Station> getAllPassedStations(Station station) {
		if (orderSetMap.get(station) == null) {
			LinkedHashSet<Station> set = new LinkedHashSet<Station>();
			set.add(this);
			orderSetMap.put(station, set);
		}
		return orderSetMap.get(station);
	}

	public Map<Station, LinkedHashSet<Station>> getOrderSetMap() {
		return orderSetMap;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Station) {
			Station s = (Station) obj;
			if (s.getName().equals(this.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

}
