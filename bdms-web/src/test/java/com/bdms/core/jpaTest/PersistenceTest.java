package com.bdms.core.jpaTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.entity.dams.Demo;


/* 
 * Description:
 * 		persistence 配置文件测试
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-2-2下午5:13:50            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class PersistenceTest {

	public static Logger logger = LoggerFactory
			.getLogger(PersistenceTest.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	/** 随机的一个整型数据 */
	AtomicInteger atom = new AtomicInteger();

	@Before
	public void before() {
		logger.info("初始化entityManagerFactory");
		entityManagerFactory = Persistence
				.createEntityManagerFactory("main-persistence");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void after() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	@Test
	public void insert() {
		logger.info("》》》》》》》》》》》》测试数据插入《《《《《《《《《《《《《《《《《《《《《《");
		entityManager.getTransaction().begin();
		int beforeSize=queryListSize();
		logger.info("》》》》》》》》》插入前总数：《《《《《《《《《《《《《《《《《《《《《《" + beforeSize);
		
		//Account account=generateRadomAccount();
		//entityManager.persist(account);
		Demo demo=new Demo();
		demo.setInfo("demo测试信息");
		demo.setName("demo的Name");
		entityManager.persist(demo);
		entityManager.getTransaction().commit();
		int aftersize=queryListSize();
		logger.info("》》》》》》》》》插入后总数：《《《《《《《《《《《《《《《《《《《《《《" + aftersize);
		Assert.assertEquals(beforeSize+1, aftersize);
	}

	public int queryListSize() {
		logger.info("数据总条数测试");
		List<Demo> demos = entityManager.createQuery(
				"select t from Demo t ",Demo.class).getResultList();
		return demos.size();
	}

}
