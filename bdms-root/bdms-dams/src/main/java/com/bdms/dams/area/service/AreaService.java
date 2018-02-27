package com.bdms.dams.area.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bdms.entity.dams.Area;

@Service
public interface AreaService {

	/*
	 *得到全部区域 
	 * */
	List<Area> findAll();

	/*根据Id获取一条区域数据*/
	Area getAreaById(Integer id);

	/*保存区域信息*/
	Area saveArea(Area area);

	/*通过id删除账户*/
	void delArea(Integer id);

	/**
	 * @param page   当前页面
	 * @param rows   页面条数
  	 * @param areaName  区域名
	 * @return
	 */
	Page<Area> findAllWhitPage(int page, int rows, String areaName);
}
