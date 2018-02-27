package com.bdms.sqoop.dao;

import java.sql.Connection;
import java.util.List;

public interface SQOOP2DBDao {

	List getTables(Connection conn,boolean isContainVeriw)throws Exception;
	List getColumsByTableName(Connection conn,String tableName) throws Exception;
}
