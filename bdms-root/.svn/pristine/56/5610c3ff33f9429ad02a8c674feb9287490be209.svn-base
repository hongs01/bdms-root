package com.bdms.dams.dijkstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdms.dams.station.service.StationService;

/* 
 * Description:
 * 		构建站点数据
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-11下午1:10:33            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Service
public class DataBuilder {

	@Autowired
	private StationService stationService;

	private Set<List<Station>> lineSet = new HashSet<List<Station>>();
	public static List<Station> line1 = new ArrayList<Station>();// 1号线
	public static List<Station> line2 = new ArrayList<Station>();// 2号线
	public static List<Station> line3 = new ArrayList<Station>();// 3号线
	public static List<Station> line4 = new ArrayList<Station>();// 4号线
	public static List<Station> line5 = new ArrayList<Station>();// 5号线
	public static List<Station> line6 = new ArrayList<Station>();// 6号线
	public static List<Station> line7 = new ArrayList<Station>();// 7号线
	public static List<Station> line8 = new ArrayList<Station>();// 8号线
	public static List<Station> line9 = new ArrayList<Station>();// 9号线
	public static List<Station> line10 = new ArrayList<Station>();// 10号线
	public static List<Station> line11 = new ArrayList<Station>();// 11号线
	public static List<Station> line12 = new ArrayList<Station>();// 12号线
	public static List<Station> line13 = new ArrayList<Station>();// 13号线
	public static List<Station> line16 = new ArrayList<Station>();// 16号线

	public int totalStaion = 0;

	public void init() {
		/* 1号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("01")) {
			line1.add(new Station(s.getName()));
		}
		for (int i = 0; i < line1.size(); i++) {
			if (i < line1.size() - 1) {
				line1.get(i).next = line1.get(i + 1);
				
				line1.get(i + 1).prev = line1.get(i);
			}
		}

		/* 2号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("02")) {
			line2.add(new Station(s.getName()));
		}
		for (int i = 0; i < line2.size(); i++) {
			if (i < line2.size() - 1) {
				line2.get(i).next = line2.get(i + 1);
				line2.get(i + 1).prev = line2.get(i);
			}
		}

		/* 3号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("03")) {
			line3.add(new Station(s.getName()));
		}
		for (int i = 0; i < line3.size(); i++) {
			if (i < line3.size() - 1) {
				line3.get(i).next = line3.get(i + 1);
				line3.get(i + 1).prev = line3.get(i);
			}
		}

		/* 4号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("04")) {
			line4.add(new Station(s.getName()));
		}
		for (int i = 0; i < line4.size(); i++) {
			if (i < line4.size() - 1) {
				line4.get(i).next = line4.get(i + 1);
				line4.get(i + 1).prev = line4.get(i);
			}
		}

		/* 5号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("05")) {
			line5.add(new Station(s.getName()));
		}
		for (int i = 0; i < line5.size(); i++) {
			if (i < line5.size() - 1) {
				line5.get(i).next = line5.get(i + 1);
				line5.get(i + 1).prev = line5.get(i);
			}
		}

		/* 6号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("06")) {
			line5.add(new Station(s.getName()));
		}
		for (int i = 0; i < line6.size(); i++) {
			if (i < line6.size() - 1) {
				line6.get(i).next = line6.get(i + 1);
				line6.get(i + 1).prev = line6.get(i);
			}
		}

		/* 7号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("07")) {
			line7.add(new Station(s.getName()));
		}
		for (int i = 0; i < line7.size(); i++) {
			if (i < line7.size() - 1) {
				line7.get(i).next = line7.get(i + 1);
				line7.get(i + 1).prev = line7.get(i);
			}
		}

		/* 8号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("08")) {
			line8.add(new Station(s.getName()));
		}
		for (int i = 0; i < line8.size(); i++) {
			if (i < line8.size() - 1) {
				line8.get(i).next = line8.get(i + 1);
				line8.get(i + 1).prev = line8.get(i);
			}
		}

		/* 9号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("09")) {
			line9.add(new Station(s.getName()));
		}
		for (int i = 0; i < line9.size(); i++) {
			if (i < line9.size() - 1) {
				line9.get(i).next = line9.get(i + 1);
				line9.get(i + 1).prev = line9.get(i);
			}
		}

		/* 10号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("10")) {
			line10.add(new Station(s.getName()));
		}
		for (int i = 0; i < line10.size(); i++) {
			if (i < line10.size() - 1) {
				line10.get(i).next = line10.get(i + 1);
				line10.get(i + 1).prev = line10.get(i);
			}
		}

		/* 11号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("11")) {
			line11.add(new Station(s.getName()));
		}
		for (int i = 0; i < line11.size(); i++) {
			if (i < line11.size() - 1) {
				line11.get(i).next = line11.get(i + 1);
				line11.get(i + 1).prev = line11.get(i);
			}
		}

		/* 12号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("12")) {
			line12.add(new Station(s.getName()));
		}
		for (int i = 0; i < line12.size(); i++) {
			if (i < line12.size() - 1) {
				line12.get(i).next = line12.get(i + 1);
				line12.get(i + 1).prev = line12.get(i);
			}
		}

		/* 13号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("13")) {
			line13.add(new Station(s.getName()));
		}
		for (int i = 0; i < line13.size(); i++) {
			if (i < line13.size() - 1) {
				line13.get(i).next = line13.get(i + 1);
				line13.get(i + 1).prev = line13.get(i);
			}
		}

		/* 16号线 */
		for (com.bdms.entity.dams.Station s : stationService
				.findBylineName("16")) {
			line16.add(new Station(s.getName()));
		}
		for (int i = 0; i < line16.size(); i++) {
			if (i < line16.size() - 1) {
				line16.get(i).next = line16.get(i + 1);
				line16.get(i + 1).prev = line16.get(i);
			}
		}
		lineSet.add(line1);
		lineSet.add(line2);
		lineSet.add(line3);
		lineSet.add(line4);
		lineSet.add(line5);
		lineSet.add(line6);
		lineSet.add(line7);
		lineSet.add(line8);
		lineSet.add(line9);
		lineSet.add(line10);
		lineSet.add(line11);
		lineSet.add(line12);
		lineSet.add(line13);
		lineSet.add(line16);

		totalStaion = line1.size() + line2.size() + line3.size() + line4.size()
				+ line5.size() + line6.size() + line7.size() + line8.size()
				+ line9.size() + line10.size()+line11.size()+line12.size()+line13.size()+line16.size();

		// for (String l : stationService.findAllLines()) {
		// List<Station> line = new ArrayList<Station>();
		// if (l != "00") {
		//
		// for (com.bdms.core.station.Station s : stationService
		// .findBylineName(l)) {
		// line.add(new Station(s.getName()));
		// }
		// for (int i = 0; i < line.size(); i++) {
		// line.get(i).next = line.get(i + 1);
		// line.get(i + 1).prev = line.get(i);
		// }
		// }
		// lineSet.add(line);
		// totalStaion=totalStaion+line.size();

	}

	public Set<List<Station>> getLineSet() {
		return lineSet;
	}

	public void setLineSet(Set<List<Station>> lineSet) {
		this.lineSet = lineSet;
	}

	public int getTotalStaion() {
		return totalStaion;
	}

	public void setTotalStaion(int totalStaion) {
		this.totalStaion = totalStaion;
	}

}
