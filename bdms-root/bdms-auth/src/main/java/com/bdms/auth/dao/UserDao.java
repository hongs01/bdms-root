package com.bdms.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdms.entity.auth.User;


/**
  * Description:
  * 		用户实体类
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2016-1-12下午5:31:14            1.0            Created by YuXiaoLin
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

 
}
