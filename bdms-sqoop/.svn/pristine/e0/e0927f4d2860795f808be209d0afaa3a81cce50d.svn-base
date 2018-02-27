/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-25 下午1:59:49
 */
package com.bdms.sqoop.db.sql;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bdms.sqoop.db.info.ColumnInfo;



/** 
 * @author 李晓聪
 * @date 2014-12-25 下午1:59:49
 * @Description:  TODO
 */
public class OracleCreateSQL implements SQLCreateServer {
	
	private static final String[] dateType = new String[]{"date","datetime","smalldatetime","timestamp","datetime2"};

	/* (non-Javadoc)
	 * @see com.ds.sqoop.db.sql.SQLCreateServer#appendSQL(java.lang.String, java.util.List, java.util.List, java.lang.String, boolean)
	 */
	public String appendSQL(String tableName, List<ColumnInfo> cis,
			String conditions, String partitionColumn,boolean needCreatePk) throws Exception {
		
		boolean notBlank = StringUtils.isNotBlank(conditions);
		
		StringBuffer tmpSQL = new StringBuffer();
		tmpSQL.append("select ");
		int size = cis.size();
		for(int i = 0 ; i < size;i++){
			
			ColumnInfo ci = cis.get(i);
			
			String type = ci.getType();
			String name = ci.getName();
			if(Arrays.asList(dateType).contains(type.toLowerCase())){
				tmpSQL.append("CONVERT(nvarchar(40)," + name +",120) AS " + name);
			}else{
				tmpSQL.append(name);
			}
			
			if( i < (size - 1) ){
				tmpSQL.append(",");
			}
		}
		
		if(needCreatePk){
			tmpSQL.append(",row_number() over (order by " + cis.get(0).getName() + ") AS " + partitionColumn );
		}
		tmpSQL.append( " from "  );
		tmpSQL.append( tableName );
		
		if(notBlank){
			tmpSQL.append( " where " );
			tmpSQL.append( conditions );
		}
		
		if(needCreatePk){
			
			StringBuffer t = new StringBuffer();
			t.append("select * from (");
			t.append(tmpSQL);
			t.append(") a where ${CONDITIONS}");
			
			return t.toString();
			
		}else{
			if(notBlank){
				tmpSQL.append( " and " );
			}else{
				tmpSQL.append( " where " );
			}
			tmpSQL.append(" ${CONDITIONS} ");
			return tmpSQL.toString();
		}
	}
	
	/*public static void main(String[] args) throws Exception {
		
		SQLServerCreateSQL ssc = new SQLServerCreateSQL();
		
		String tableName = "zz";
		String conditions = "x>3&ls<6";
		String partitionColumn = "sqoopPK";
		boolean needCreatePk = true;
		List<ColumnInfo> cis = new ArrayList<ColumnInfo>();
		ColumnInfo ci1  = new ColumnInfo("name", "nvarchar", "20");
		ColumnInfo ci2  = new ColumnInfo("sex", "char", "2");
		ColumnInfo ci3  = new ColumnInfo("address", "nvarchar", "120");
		ColumnInfo ci4  = new ColumnInfo("class", "nvarchar", "40");
		ColumnInfo ci5  = new ColumnInfo("phone", "char", "11");
		ColumnInfo ci6  = new ColumnInfo("birthday", "datetime", "40");
		
		cis.add(ci6);
		cis.add(ci5);
		cis.add(ci4);
		cis.add(ci3);
		cis.add(ci2);
		cis.add(ci1);
		
		String appendSQL = ssc.appendSQL(tableName, cis, conditions, partitionColumn, needCreatePk);
		System.out.println(appendSQL);
	}
*/
}
