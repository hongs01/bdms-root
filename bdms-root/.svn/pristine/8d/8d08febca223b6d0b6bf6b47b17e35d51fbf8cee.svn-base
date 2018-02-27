package com.bdms.web.sqoop.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.sqoop.WebServer.SQOOP2WebServer;
import com.bdms.sqoop.db.conn.SqoopDbConnection;
import com.bdms.sqoop.db.info.ColumnInfo;
import com.bdms.sqoop.db.info.DBConnectionInfo;
import com.bdms.sqoop.ds_enum.DBType;
import com.bdms.sqoop.service.SQOOP2ClusterService;
import com.bdms.sqoop.service.SQOOP2DBService;


@Controller
public class SqoopDataImportController {

	private static final Logger LOG = Logger.getLogger(SqoopDataImportController.class);
	
	private SQOOP2WebServer server;
	
	@Autowired
	private SQOOP2DBService dbService;
	@Autowired
	private SQOOP2ClusterService clusterService;
	
	//跳转到数据导入的页面
	@RequestMapping("/sqoop")
	public String indexPage(){
		 return "sqoop/dataimport";
	}
	
	//测试数据库连接
	@RequestMapping(value="/sqoop/testDBConn",method=RequestMethod.POST)
	@ResponseBody
	public Map testConn(int type,HttpServletRequest request){
		
		DBConnectionInfo dbInfo = new DBConnectionInfo();
		dbInfo.setDataBaseName(request.getParameter("name"));
		dbInfo.setHost(request.getParameter("host"));
		dbInfo.setPort(request.getParameter("port"));
		dbInfo.setUserName(request.getParameter("user"));
		dbInfo.setPassword(request.getParameter("password"));
		
		switch (type) {
			case 1:
				dbInfo.setDbType( DBType.SQLSERVER );
				break;
			case 2:
				dbInfo.setDbType( DBType.ORACLE );
				break;
			case 3:
				dbInfo.setDbType( DBType.MYSQL );
				break;
	
			default:
				LOG.error("暂不支持的数据库类型！");
				break;
		}
		
		//测试数据库连接 
		boolean valid = false;
		Connection conn = null;
		
		Map<String,Object> res = new HashMap<String, Object>();
		String  sessionId = request.getSession().getId();
		
		try {
			conn = SqoopDbConnection.getConn(dbInfo);
			valid = !conn.isClosed();
			
			server.storeDBConnectionInfo(sessionId, dbInfo);
			server.storeConnection(dbInfo.hashCode(), conn);
			
			res.put("tables",dbService.getTables(dbInfo, true));
			
		} catch (Exception e) {
			LOG.error("测试连接失败", e);
			valid = false;
		}
		if(valid){
			res.put("status", "ok");
		}else{
			server.removeDBConnectionInfo(sessionId);
			try {
				server.removeConnection(dbInfo.hashCode());
			} catch (SQLException e) {
			}
			res.put("status", "no");
		}
		
		return res;
	}
	
	@RequestMapping(value="/sqoop/getColumn",method=RequestMethod.POST)
	@ResponseBody
	public Map getColumn(HttpServletRequest request){
		
		Map<String,Object> res = new HashMap<String, Object>();
		
		DBConnectionInfo dbInfo = server.getDBConnectionInfo(request.getSession().getId());
		
		try {
			String tableName = request.getParameter("table");
			List<ColumnInfo> tableColumn = dbService.getColumsByTableName(dbInfo, tableName);
			
			res.put("column",tableColumn);
			res.put("status", "ok");
			
		} catch (Exception e) {
			LOG.error("获取中指定的表的字段信息失败", e);
			res.put("status", "no");
		}
		return res;
	}
	
	@RequestMapping(value="/sqoop/doimport",method=RequestMethod.POST)
	@ResponseBody
	public void doImport( HttpServletRequest request ){
		
		String sessionId = request.getSession().getId();
		
		try {
			String parameter = request.getParameter("selectColumns");
			
			clusterService.doImport(sessionId,Arrays.asList(parameter.split(";")));
			
		} catch (Exception e) {
			
		}finally{
			//删除本次导入操作的 所存储的参数
		}
		
	}
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		
		server = SQOOP2WebServer.getSQOOP2WebServer();
		
		/*binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));*/
	}
	
}
