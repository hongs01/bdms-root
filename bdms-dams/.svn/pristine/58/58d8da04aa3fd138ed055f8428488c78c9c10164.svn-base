package com.bdms.dams.area.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.bdms.dams.area.dao.AreaDao;
import com.bdms.entity.dams.Area;


@Service("areaService")
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> findAll() {
		
		return areaDao.findAll();
	}

	/* 根据Id获取一条记录
	 * (non-Javadoc)
	 * @see com.bdms.dams.area.service.AreaService#getAreaById(java.lang.Integer)
	 */
	@Override
	public Area getAreaById(Integer id) {
		// TODO Auto-generated method stub
		return areaDao.findOne(id);
	}

	/* 添加记录到area表中
	 * (non-Javadoc)
	 * @see com.bdms.dams.area.service.AreaService#saveArea(com.bdms.core.area.Area)
	 */
	@Override
	public Area saveArea(Area area) {
		return areaDao.save(area);
	}

	/* 根据Id删除一个区域
	 * (non-Javadoc)
	 * @see com.bdms.dams.area.service.AreaService#delArea(java.lang.Integer)
	 */
	@Override
	public void delArea(Integer id) {
		try {
			areaDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public Page<Area> findAllWhitPage(int page, int rows, final String areaName) {
		Sort sort = new Sort(Direction.DESC, "totalMark"); // 按照总分倒叙排序
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 构建动态查询
		Specification<Area> specification = new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (areaName != null && areaName != "") {
					predicate.add(cb.like(
							root.get("areaName").as(String.class), "%"
									+ areaName + "%"));
				} 
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
		return areaDao.findAll(specification, pageable);
	}

}
