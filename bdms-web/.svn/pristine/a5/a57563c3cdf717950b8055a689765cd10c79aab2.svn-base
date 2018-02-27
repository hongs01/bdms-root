package com.bdms.web.dams.score.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdms.dams.score.service.AreaDataService;
import com.bdms.dams.weight.service.WeightService;
import com.bdms.entity.dams.Area;
import com.bdms.entity.dams.AreaData;

@Controller
@RequestMapping(value = "/dams/score/scorePage/")
public class ScorePageController {

	@Autowired
	private AreaDataService areaDataService;

	@Autowired
	private WeightService weightService;

	/**
	 * 跳转到实时监控得分页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "page", method = { RequestMethod.GET })
	public ModelAndView score() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topimg", "titleNameS");
		return new ModelAndView("dams/score/scorePage", map);
	}

	/**
	 * 跳转到测试页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "test", method = { RequestMethod.GET })
	public String test() {
		return "dams/score/testPage";
	}

	/**
	 * 获取一级数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "areaData", method = { RequestMethod.GET,
			RequestMethod.POST })
	public List<AreaData> findFirstAll() throws UnsupportedEncodingException {

		return areaDataService.findAll();
	}

	/**
	 * description: 获取最高得分的区域，定义到页面上
	 * 
	 * @return String 2015-11-6 下午4:13:26 BY YuXiaoLin
	 */
	@RequestMapping(value = "getTopAreaScore", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getTopAreaScore() {
		String topid = weightService.getTopestMark().getId().toString();
		String str = "redirect:getAreaScore/" + topid;
		return str;
	}

	/**
	 * description:跳转到评分页面
	 * 
	 * @param areaId
	 *            区域id
	 * @return ModelAndView 2015-11-6 下午3:27:10 BY YuXiaoLin
	 */
	@RequestMapping(value = "getAreaScore/{areaId}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getAreaScore(
			@PathVariable(value = "areaId") String areaId) {

		Area area = weightService.getAreaAllInfoByAreaId(Integer
				.parseInt(areaId));
		List<Area> areas=weightService.getTopAreasByMark();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("area", area);
		model.put("areas", areas);
		return new ModelAndView("dams/score/getAreaScore", model);
	}
}
