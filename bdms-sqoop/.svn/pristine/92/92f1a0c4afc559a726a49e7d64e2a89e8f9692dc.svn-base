/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-17 下午12:41:40
 */
package com.bdms.sqoop.server.dbserver;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.info.TableInfo;


/** 
 * @author 李晓聪
 * @date 2014-12-17 下午12:41:40
 * @Description:  TODO
 */
@Component(value="dbServer")
public class DBServer {
	
	private DatabaseMetaData metadata;
	
	public DBServer(  Connection conn ) throws Exception{
		
			metadata = conn.getMetaData();
	}
	
	
	public DBServer() {
		super();
	}




	public List<TableInfo>  getTables( boolean containViews ) throws Exception{
		
		List<TableInfo> tableNames = new ArrayList<TableInfo>();
		
		String[] types = null;
		if(containViews){
			types = new String[]{"TABLE","VIEW"};
		}else{
			types = new String[]{"TABLE"};
		}
		
		ResultSet tables = metadata.getTables(null,null,"%",types);
		
		
		TableInfo ti = null;
		while(tables.next()){
			
			
			String tableName = tables.getString("TABLE_NAME");
			String tableType = tables.getString("TABLE_TYPE");
			
			ti = new TableInfo();
			ti.setName(URLEncoder.encode(tableName, "UTF-8"));
			ti.setType(tableType);
			
			tableNames.add(ti);
			
		}
		return tableNames;
	}
	
	public List<ColumnInfo> getTableColumn(String tableName) throws Exception{
		
		List<ColumnInfo> res = new ArrayList<ColumnInfo>();
		ColumnInfo cb = null;
		ResultSet columnSet = metadata.getColumns(null, "%", tableName, "%");
		while(columnSet.next()){
			
			String columnName = columnSet.getString("COLUMN_NAME");
			String typeName = columnSet.getString("TYPE_NAME");
			String size = columnSet.getString("COLUMN_SIZE");
			
			cb = new ColumnInfo();
			cb.setName(URLEncoder.encode(columnName,"UTF-8"));
			cb.setType(typeName);
			cb.setSize(size);
			
			res.add(cb);
		}
		
		return res;
	}


	public void setConn(Connection conn) throws Exception {
		
		metadata = conn.getMetaData();
	}
	
	
	
}
