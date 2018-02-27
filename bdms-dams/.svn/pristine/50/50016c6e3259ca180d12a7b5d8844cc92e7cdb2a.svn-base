package com.bdms.dams.video.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.Query;

import org.springframework.stereotype.Repository;

import com.bdms.entity.dams.Video;



/* 
 * Description:
 * 		接口：站点相关信息数据操作接口
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-7-24 下午5:17:12           1.0            Created by YangBo
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */

@Repository
public interface VideoDao extends JpaRepository<Video, Integer>,
		JpaSpecificationExecutor<Video>{
	
	@Query("SELECT * FROM Video a WHERE a.cameraId=?0")
	 Video findByCameraId(String cameraId);
	
}
