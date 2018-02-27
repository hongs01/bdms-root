package com.bdms.common.db;

import java.sql.DriverManager;

/* 
 * Description:
 * 		驱动注册
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2014-12-19上午11:27:30            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class RegDirver {

	private final String SQLSEVER_DRIVER_NAME = ""; // SqlServer TODO

	private final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver"; // mysql

	private final String ORCL_DRIVER_NAME = ""; // orcl TODO

	private final String DB2_DRIVER_NAME = ""; // DB2 TODO

	public void reg(DbType dbType) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		switch (dbType) {
		case DB2:
				Class.forName(DB2_DRIVER_NAME).newInstance();
			break;
		case MYSQL:
				Class.forName(MYSQL_DRIVER_NAME).newInstance();
			break;
		case ORCL:
				Class.forName(ORCL_DRIVER_NAME).newInstance();
			break;
		case SQLSERVER:
				Class.forName(SQLSEVER_DRIVER_NAME).newInstance();
			break;
		default:
			break;
		}
	}

	public void regAll() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Class.forName(DB2_DRIVER_NAME).newInstance();
		Class.forName(MYSQL_DRIVER_NAME).newInstance();
		Class.forName(ORCL_DRIVER_NAME).newInstance();
		Class.forName(SQLSEVER_DRIVER_NAME).newInstance();
		DriverManager.println("注册了所有的数据库驱动，包含mysql，sqlserver等");
	}
}
