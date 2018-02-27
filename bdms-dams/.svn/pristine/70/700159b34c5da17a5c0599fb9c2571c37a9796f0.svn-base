//package com.bdms.dams.mysqlwifi.schedule;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.bdms.core.wifi.WifiData;
//import com.bdms.dams.wifi.service.WifiDataService;
//
//@Component
//public class WifiMysqlGeneratorTask {
//	
//	/** Logger */    
//	private static final Logger LOG = Logger.getLogger(WifiMysqlGeneratorTask.class);     
//	 
//	@Autowired 
//	private	WifiDataService wifiDataService;
//	
//	@Scheduled(cron="0/30 * * * * ? ") //每隔10秒触发一次
//	public void timeTask() {
//		long start=System.currentTimeMillis();
//		LOG.info("向mysql插入数据开始执行-------------" + new Date());
//		
//		Connection conn = null;
//	    String sql;
//		String url = "jdbc:mysql://192.168.7.186:3306/bdms?"
//                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
//		String tablecurrent="";
//		List<String> tablelist=new ArrayList<String>();
//		
//        try {
//        	
//            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
//            LOG.info("成功加载MySQL驱动程序----"+System.currentTimeMillis());
//            
//            
//            // 一个Connection代表一个数据库连接
//            conn = DriverManager.getConnection(url);
//            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
//            Statement stmt = conn.createStatement();
//            
//            tablecurrent="ncsapmaccount_if_201511";
//            Long currenttime=System.currentTimeMillis()/1000L;
//            LOG.info("currenttimeIS:"+currenttime);
//            for(int i=0;i<10;i++){
//            	for(WifiData wd:wifiDataService.getStations()){
//            		sql = "insert into "+tablecurrent+"(apname,stime,maccount) values('"+wd.getApname()+"','"+(currenttime-i)+"','"+Math.round(Math.random()*100)+"')";
//                    stmt.executeUpdate(sql);
//            	}
//            }
//        } catch (SQLException e) {
//            LOG.error("MySQL操作错误");
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//        }
//		
//        long end=System.currentTimeMillis();
//		LOG.info("向mysql插入数据任务结束--------------" + new Date()+"；执行时间："+(end-start)/1000+"秒");
//	}
//}