package com.bdms.sqoop.dao;

import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.param.ClusterParam;
import com.bdms.sqoop.param.DBParam;

public interface SQOOP2ClusterDao {

	public void doImport(DBConnectionInfo dbInfo,DBParam dp,ClusterParam cp) throws Exception;
}
