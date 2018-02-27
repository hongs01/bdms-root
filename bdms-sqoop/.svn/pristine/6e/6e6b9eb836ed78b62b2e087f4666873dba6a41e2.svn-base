/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-25 下午1:44:45
 */
package com.bdms.sqoop.db.sql;

import java.util.List;

import com.bdms.sqoop.db.info.ColumnInfo;


/** 
 * @author 李晓聪
 * @date 2014-12-25 下午1:44:45
 * @Description:  TODO
 */
public interface SQLCreateServer {
	
	/**
	 * @Title:appendSQL              sql拼接
	 * @param tableName              表名 
	 * @param cis                    需要导入的字段
	 * @param conditions             筛选条件
	 * @param partitionColumn        分区字段   可以为null  其为null needCreatePk 则为false;
	 * @param needCreatePk           是否需要自动构建分区字段
	 * @throws Exception
	 * @Return String
	 * @Description:TODO  
	 */
	public String appendSQL(String tableName,List<ColumnInfo> cis,String conditions,String partitionColumn,boolean needCreatePk) throws Exception;

}
