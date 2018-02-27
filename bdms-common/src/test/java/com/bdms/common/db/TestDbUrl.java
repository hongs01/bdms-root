package com.bdms.common.db;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import junit.framework.TestCase;

public class TestDbUrl extends TestCase {
	
	@Test
	public void testMysqlDbUrl()
	{
		DbUrl dbUrl=new DbUrl("192.168.9.179", "3306", DbType.MYSQL);
		System.out.println(dbUrl.getURL());
		assertEquals("jdbc:mysql://192.168.9.179:3306", dbUrl.getURL());
	}
	 
}
