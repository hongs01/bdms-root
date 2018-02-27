package com.bdms.web.dams.score.area.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.bdms.dams.area.service.AreaService;
import com.bdms.dams.weight.service.WeightService;
import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.FirstWeight;
import com.bdms.entity.dams.SecondWeight;
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "dams/score/area/")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private WeightService weightService;
	
	/********************************area*************************/
	/**
	 * 跳转到area_list页面
	 * @return
	 */
	@RequestMapping(value = "area_list", method = { RequestMethod.GET })
	public String areaList() {
		return "dams/score/area/area_list";
	}
	
	
	/**
	 * 获取列表Json数据
	 * @param page
	 * @param rows
	 * @param areaName
	 * @return
	 */
	@RequestMapping(value = "data", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> areaListData(int page, int rows, String areaName
		 ) {
		Page<Area> accountbypage = areaService.findAllWhitPage(page,
				rows, areaName);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", accountbypage.getTotalElements());
		model.put("rows", accountbypage.getContent());
		return model;
	}

	
	
	/**
	 * 跳转到area_add页面
	 * @return
	 */
	@RequestMapping(value = "area_add", method = { RequestMethod.GET })
	public String areaadd() {
		return "dams/score/area/area_add";
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView areaEdit(@PathVariable(value = "id") int id) {
		Area area = areaService.getAreaById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("area", area);
		return new ModelAndView("dams/score/area/area_edit", model);
	}
	
	/**
	 * 添加一条记录到area表
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "doadd", method = RequestMethod.POST)
	public @ResponseBody
	boolean doAreaAdd(Area area) {
		boolean result = false;
		if (areaService.saveArea(area) != null) {
			result = true;
		}
		return result;
	}
	
	
	/**
	 * 添加编辑记录到area表
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public @ResponseBody
	boolean doareaEdit(Area area) {
		boolean result = false;
		if (areaService.saveArea(area) != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delArea(@PathVariable(value = "id") Integer id) {
		areaService.delArea(id);
		return true;
	}
	
	/**
	 * 跳转到一级显示页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "nextlev/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView nextlev(@PathVariable(value = "id") int id) {
		//List<FirstWeight> first = weightService.getFirstWeighsByAreaId(id);
		int firstlev=id;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstlev", firstlev);
		return new ModelAndView("dams/score/area/area_nextlev", model);

	}
	/*跳转到一级得分页面*/
	@RequestMapping(value = "area_nextlev", method = { RequestMethod.GET })
	public String nextlev() {
		return "dams/score/area/area_nextlev";
	}
	
	/**
	 * 获取first列表Json数据
	 * @param page
	 * @param rows
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value = "firstdata/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> firstListData(@PathVariable(value = "id")int page, int rows, int id, String typeName
		 ) { 
		Page<FirstWeight> accountbypage = weightService.findAllFirstPage(page,
				rows,id, typeName);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", accountbypage.getTotalElements());
		model.put("rows", accountbypage.getContent());
		return model;
	}
	
	/*******************first*****************/
	
	/**
	 * 跳转到一级增加页面
	 * @return
	 */
	@RequestMapping(value = "first_add", method = { RequestMethod.GET })
	public String firstadd() {
		return "dams/score/first/first_add";
	}
	
	
	/**
	 * 添加一条记录到一级表
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "firstadd", method = RequestMethod.POST)
	public @ResponseBody
	boolean dofirstAdd(FirstWeight firstweight) {
		boolean result = false;
		if (weightService.saveFirst(firstweight) != null) {
			result = true;
		}
		return result;
	}
	/**
	 * 跳转到一级编辑页面
	 * @return
	 */
	@RequestMapping(value = "first_edit/{id}", method = { RequestMethod.GET })
	public ModelAndView firstEdit(@PathVariable(value = "id") int id) {
		FirstWeight firstweight = weightService.getFirstById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("first", firstweight);
		return new ModelAndView("dams/score/first/first_edit", model);
	}
	
	/**
	 * 将编辑后的一条记录添加到firstweight
	 * @param firstweight
	 * @return
	 */
	@RequestMapping(value = "firstedit", method = RequestMethod.POST)
	public @ResponseBody
	boolean dofirstEdit(FirstWeight firstweight) {
		boolean result = false;
		if (weightService.saveFirst(firstweight) != null) {
			result = true;
		}
		return result;
	}
	
	/*根据Id删除firstWeight表的一条数据*/
	@ResponseBody
	@RequestMapping(value = "deletefirst/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delFirst(@PathVariable(value = "id") Integer id) {
		weightService.delFirst(id);
		return true;
	}
	
	/**
	 * 跳转到二级得分页面
	 * @return
	 */
	@RequestMapping(value = "first_nextlev/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView firstnextlev(@PathVariable(value = "id") int id) {
		int secondlev=id;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("secondlev", secondlev);
		return new ModelAndView("dams/score/first/first_nextlev", model);
	}
	
	
	@RequestMapping(value = "seconddata/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> secondListData(@PathVariable(value = "id")int page, int rows, int id, String name )
	{ 
		Page<SecondWeight> accountbypage = weightService.findAllSecondPage(page,
				rows,id, name);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", accountbypage.getTotalElements());
		model.put("rows", accountbypage.getContent());
		return model;
	}
	
	
	
	/**************************second**********************/
	/**
	 * 跳转到二级添加页面
	 * @return
	 */
	@RequestMapping(value = "second_add", method = { RequestMethod.GET })
	public String secondadd() {
		return "dams/score/second/second_add";
	}
	
	/*向secondWeight添加一条记录*/
	@RequestMapping(value = "secondadd", method = RequestMethod.POST)
	public @ResponseBody
	boolean dosecondAdd(SecondWeight secondweight) {
		boolean result = false;
		if (weightService.saveSecond(secondweight) != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 跳转到二级编辑页面
	 * @return
	 */
	@RequestMapping(value = "second_edit/{id}", method = { RequestMethod.GET })
	public ModelAndView secondEdit(@PathVariable(value = "id") int id) {
		SecondWeight secondweight = weightService.getSecondById(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("second", secondweight);
		return new ModelAndView("dams/score/second/second_edit", model);
	}
	
	/**
	 * 将编辑后的一条记录添加到secondweight
	 * @param secondweight
	 * @return
	 */
	@RequestMapping(value = "secondedit", method = RequestMethod.POST)
	public @ResponseBody
	boolean dosecondEdit(SecondWeight secondweight) {
		boolean result = false;
		if (weightService.saveSecond(secondweight) != null) {
			result = true;
		}
		return result;
	}
	
	/*根据Id删除SecondWeight表的一条记录*/
	@ResponseBody
	@RequestMapping(value = "deletesecond/{id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public boolean delSecond(@PathVariable(value = "id") Integer id) {
		weightService.delSecond(id);
		return true;
	}
}
