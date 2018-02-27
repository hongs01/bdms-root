/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-24 上午9:15:54
 */
package com.bdms.sqoop.param;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.sql.SQLCreateServer;
import com.bdms.sqoop.exception.NullException;


/** 
 * @author 李晓聪
 * @date 2014-12-24 上午9:15:54
 * @Description:  TODO
 */
public class DBParam {
	
	private static final Logger LOG = Logger.getLogger(DBParam.class);
	
	private static final String[] acceptablePartitionColumns = new String[]{"tinyint","int","smallint","mediumint" +
			"bigint","float","double","decimal","number","numeric"};
	
	
	private SQLCreateServer scs;
	
	private String tableName;
	private String partitionColumn;
	private List<ColumnInfo> importColumns;
	private String conditions;
	private String customSQL;
	
	private boolean useCustomSQL = false;
	private boolean checked = false;
	private boolean needCreatePk = false;
	

	public DBParam() {
		super();
	}

	public DBParam(SQLCreateServer scs,String tableName, String partitionColumn,
			List<ColumnInfo> importColumns,String conditions) {
		super();
		this.scs = scs;
		this.tableName = tableName;
		this.partitionColumn = partitionColumn;
		this.importColumns = importColumns;
		this.conditions = conditions;
	}
	
	public String getImportSQL() throws Exception{
	
		if(useCustomSQL){
			if(StringUtils.isBlank( customSQL )){
				LOG.error("选择自定义sql导入模式，自定义sql为空。");
				throw new NullException();
			}
			if(StringUtils.isBlank(partitionColumn)){
				LOG.error("选择自定义sql导入模式，partitionColumn为空。(注：partitionColumn为分区字段，需要是数字数据类型，同时是表中存在的。若表中不存在这样的字段，可以使用子查询的方式构建。)");
				throw new NullException();
			}
			return customSQL;
		}else{
		
			if(!checked)paramCheck();
			
			if(checked){
				if( scs == null){
					LOG.error("参数SQLCreateServer为null！");
					throw new Exception("参数SQLCreateServer为null！");
				}
				return scs.appendSQL(tableName, importColumns, conditions,partitionColumn,needCreatePk);
				
			}else{
				LOG.error("没有通过参数检查！");
				throw new Exception("没有通过参数检查！");
			}
				
	    }
		
	}
	
	
	/**
	 * @Title:useCustomSQL 
	 * @param sql
	 * @Return void
	 * @Description:TODO 使用自定义sql模式导入  。
	 *  注 ：  正确设置partitionColumn，
	 *  partitionColumn为分区字段，需要是数字数据类型，同时是表中存在的。若表中不存在这样的字段，可以使用子查询的方式构建。
	 */
	public void useCustomSQL(String sql) {
		this.useCustomSQL = true;
		this.customSQL = sql;
	}
	
	public void paramCheck(){
		
		this.checked = false;
		
		if(StringUtils.isBlank(tableName)){
			LOG.error("参数tableName为空！");
			return;
		}
		
		if( importColumns == null || importColumns.isEmpty()){
			LOG.error("参数importColumns为空！");
			return;
		}
		
		if(StringUtils.isBlank(partitionColumn)){
			needCreatePk = true;
			partitionColumn = "sqoopPK";
			LOG.warn("参数partitionColumn为空,自动构建 partitionColumn 为  sqoopPK");
		}else{
			boolean contains = false;
			for(ColumnInfo ci : importColumns){
				if( partitionColumn.equals(ci.getName())){
					contains = true;
					String type = ci.getType();
					if( ! Arrays.asList(acceptablePartitionColumns).contains(type.toLowerCase())){
						LOG.error("所给参数 partitionColumn 在数据库中类型不是数字数据类型，不能作为 数据导入的partitionColumn！");
						return;
					}
					break;
				}
			}
			
			if( !contains ){
				needCreatePk = true;
				LOG.warn("所给参数 partitionColumn，不存在于需要导入的字段列表中，将构建该字段。");
			}
		}
		this.checked = true;
		
	}
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.checked = false;
		this.tableName = tableName;
	}

	public String getPartitionColumn() {
		return partitionColumn;
	}

	public void setPartitionColumn(String partitionColumn) {
		this.checked = false;
		this.partitionColumn = partitionColumn;
	}

	public List<ColumnInfo> getImportColumns() {
		return importColumns;
	}
	public void setImportColumns(List<ColumnInfo> importColumns) {
		this.checked = false;
		this.importColumns = importColumns;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.checked = false;
		this.conditions = conditions;
	}
	public String getCustomSQL() {
		return customSQL;
	}
	public boolean isUseCustomSQL() {
		return useCustomSQL;
	}
	public boolean isChecked() {
		return checked;
	}
	public boolean isNeedCreatePk() {
		return needCreatePk;
	}

	public SQLCreateServer getScs() {
		return scs;
	}

	public void setScs(SQLCreateServer scs) {
		this.scs = scs;
	}
	
}
