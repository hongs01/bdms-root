package com.bdms.dams.mysqlwifi.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bdms.dams.wifi.service.WifiDataService;
import com.bdms.entity.dams.WifiData;

@Component
public class WifiMysql2HbaseTask {
	
	/** Logger */    
	private static final Logger LOG = Logger.getLogger(WifiMysql2HbaseTask.class);   
	private static final String  HBASESITE = "hbase/hbase-site.xml";
	private static final String  HBASEHDFS = "hbase/hdfs-site.xml" ;
	Map<String,String> lastTimes=new HashMap<String,String>();
	 
	@Autowired 
	private	WifiDataService wifiDataService;
	
	@Scheduled(cron="0 0/5 * * * ? ") //每隔5分钟触发一次
	public void timeTask() {
		long start=System.currentTimeMillis();
		LOG.info("定时任务开始执行-------------" + new Date());
	    String sql;
	    String tablecurrent="";
	    String mysqltabletime="";
	    String hbasetablename="";
	    
	    String pollingrate="";
	    
		String tablebefore="";
		List<String> tablelist=new ArrayList<String>();
		//数据库连接
	    WifiMysqlConn p=new WifiMysqlConn();
		Connection conn=p.getConnection();
		tablecurrent=p.PREFIX;
		mysqltabletime=p.MYSQLTABTIME;
		hbasetablename=p.HBASETABNAME;
		pollingrate=p.POLLINGRATE;
		
		LOG.info("成功加载MySQL驱动程序"+";lastTimesSizeIs:"+lastTimes.size());
		Statement stmt = null;
		ResultSet rs = null;
        try {
        	//设置事务，避免丢失数据；
        	conn.setAutoCommit(false);
            stmt = conn.createStatement();
            
            //根据系统时间选择对应月份的表
            LOG.info(new SimpleDateFormat("yyyyMM").format(new Date()));
//            tablecurrent=tablecurrent+new SimpleDateFormat("yyyyMM").format(new Date());
            tablecurrent=tablecurrent+mysqltabletime;
            tablelist.add(tablecurrent);
            
            //遍历上次读取的Map(apname,lasttimestamp)，处理新数据
            ArrayList<String> resultList=new ArrayList<String>();
            if(lastTimes==null || lastTimes.size()==0){
            	LOG.info("第一次读取数据，认为无增量,只更新Map(apname,lasttimestamp)");
            }else{
            	 for(Map.Entry<String, String> entry:lastTimes.entrySet()){
                 	//LOG.info("size = "+lastTimes.size()+",Key = "+entry.getKey()+",Value = "+entry.getValue());
                 	sql="select apname,(stime div "+pollingrate+") as st,sum(maccount),count(maccount) from "+entry.getKey().split("-")[0]+" where stime >"+entry.getValue()+" and apname ='"+entry.getKey().split("-")[1]+"' group by st";
             		rs=stmt.executeQuery(sql);
             		while(rs.next()){
             			String dateTransfered=new SimpleDateFormat("yyyyMMddHHmmss").format(Long.parseLong(rs.getString(2))*Long.parseLong(pollingrate)*1000);
//             			LOG.info(rs.getString(1)+"----"+dateTransfered+"----"+rs.getString(3)+"----"+rs.getString(4));
             			resultList.add(rs.getString(1)+";"+dateTransfered+";"+rs.getString(3)+";"+rs.getString(4));
             		}
                 }
            	 mergeIntoHbase(hbasetablename,"info",resultList);
            }
            
            //更新Map(apname,lasttimestamp),供下次读取；
            for(String str:tablelist){
            	LOG.info("current table is :" +str);
            	List<WifiData> wifiDataList=wifiDataService.getStations();
            	String availableApnames="(";
            	for(WifiData wd:wifiDataList){
            		availableApnames+="'"+wd.getApname()+"',";
            	}
            	if(availableApnames.endsWith(",")){
            		availableApnames=availableApnames.substring(0, availableApnames.length()-1);
            	}
            	availableApnames+=availableApnames=")";
            	
        		sql="select apname,max(stime) from "+str+" where apname in "+ availableApnames +" group by apname";
        		LOG.info("SQLIS:"+sql);
        		rs=stmt.executeQuery(sql);
        		while(rs.next()){
        			LOG.info(rs.getString(1)+"----"+rs.getString(2));
        			lastTimes.put(str+"-"+rs.getString(1),""+Long.parseLong(rs.getString(2)));
        		}
            }
            
            //提交
            conn.commit();
			conn.setAutoCommit(true);			
        }catch (Exception e) {
        	lastTimes.clear();
            LOG.error("操作错误");
			e.printStackTrace();
			try {
				if(conn!=null){
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				if(stmt!=null){stmt.close();}
				if(conn!=null){conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
        long end=System.currentTimeMillis();
		LOG.info("定时任务结束--------------" + new Date()+"；执行时间："+(end-start)/1000+"秒"+";currenttable is :"+tablecurrent);
	}
	
//	把统计好的5分钟数据合并入hbase中
	private void mergeIntoHbase(String tablename,String cloumnFamilyName,List<String> resultList) throws Exception {
		
		Configuration hConf =HBaseConfiguration.create();
		hConf.addResource(HBASESITE);
		hConf.addResource(HBASEHDFS);
	    HConnection hconn = HConnectionManager.createConnection(hConf);
	  
	   TableName name = TableName.valueOf(tablename);
	    
	   //如果hbase中不存在该表  则创建
	    if(!hconn.isTableAvailable(name)){
	   	 
	   	 HBaseAdmin admin = new HBaseAdmin(hConf);
	   	 HTableDescriptor htd = new HTableDescriptor(name);
			 
	   	 HColumnDescriptor hcd = new HColumnDescriptor(cloumnFamilyName);
			 hcd.setMaxVersions(1);
			 hcd.setBloomFilterType(BloomType.ROWCOL);
			// hcd.setInMemory(true);
			// hcd.setTimeToLive(60*60*30)
			 htd.addFamily( hcd);
			 admin.createTable(htd);
			 admin.close();
	    }
	    
	    byte[] cf = Bytes.toBytes("info");
	    byte[] sid = Bytes.toBytes("sid");
	    byte[] time = Bytes.toBytes("time");
	    byte[] count = Bytes.toBytes("count");	
	    byte[] num = Bytes.toBytes("num");
	     
	    HTableInterface table = hconn.getTable(name);
	    
	    List<Put> puts = new ArrayList<Put>(); 
	    Put put = null;
	    Get get = null;
	    String[] vals = null;
	    
	    for(String str:resultList){
	    	vals=str.split(";");
	    	get=new Get(Bytes.toBytes(vals[0]+"-"+vals[1]));
	    	get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("count"));
	    	get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("num"));
	    	Result result=table.get(get);
	    	LOG.info("result.size()IS:"+result.size());
	    	String[] valInHbase=new String[2];
	    	String[] valnew=new String[2];
	    	if(result.size()==0){
	    		LOG.info("hbase中还没有此时间段的数据");
	    		valnew[0]=vals[2];
	    		valnew[1]=vals[3];
	    	}else{
	    		//如hbase里已有此五分钟记录，则将新的记录合并进去
	    		valnew[0]=(Long.parseLong(vals[2])+Long.parseLong(Bytes.toString(result.list().get(0).getValue())))+"";
	    		valnew[1]=(Long.parseLong(vals[3])+Long.parseLong(Bytes.toString(result.list().get(1).getValue())))+"";
	    	}
	    	LOG.info("valnewIS："+valnew[0]+"____"+valnew[1]);
	    	
//	    	String dateTransfered=new SimpleDateFormat("yyyyMMddHHmmss").format(Long.parseLong(vals[0])*1000);
	    	put = new Put(Bytes.toBytes(vals[0]+"-"+vals[1]));
		    put.add(cf, sid, Bytes.toBytes(vals[0]));
		    put.add(cf, time, Bytes.toBytes(vals[1]));
		    put.add(cf, count, Bytes.toBytes(valnew[0]));    		
		    put.add(cf, num, Bytes.toBytes(valnew[1])); 
		    puts.add(put);
	    }
	    table.put(puts);
	    table.close();
	    hconn.close();
	}
}