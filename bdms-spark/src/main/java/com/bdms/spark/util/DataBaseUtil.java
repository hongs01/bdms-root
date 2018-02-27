package com.bdms.spark.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.bdms.entity.dams.Criterion;
import com.bdms.dams.criterion.service.CriterionService;
import com.bdms.dams.dzwl.DZWLService;
import com.bdms.dams.station.service.StationService;




/**
  * Description:
  * 		读取配置文件 与  spark任务需要的各种配置信息的读取。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2015-7-28下午5:47:56            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class DataBaseUtil implements Serializable {

	
	private static final long serialVersionUID = -6756279892720619822L;
	
	private  final static String SPRINGCORE   = "classpath:spring/spring-core-config.xml";
	private  final static String SPRINGHADOOP = "classpath:spring/spring-hadoop-config.xml";
	   
     //读取换乘站信息
	 public static Map<String,String> findAllTrans(){
	   
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    	
        context.setValidating(false);
        
        context.load(SPRINGCORE);
        context.load(SPRINGHADOOP);
        context.refresh();
        StationService bean = context.getBean(StationService.class);
      
        Map<String, String> findAllTrans = bean.findAllTrans();
        
        System.out.println( findAllTrans );
        context.registerShutdownHook();
       // context.stop();
        //context.destroy();
        
       // System.out.println( "****************" + findAllTrans + "*************************");
       
       return findAllTrans;
	   
	 }
	 
	 
	 public static Map<String,String> findAllQYM(){
		   
			GenericXmlApplicationContext context = new GenericXmlApplicationContext();
	    	
	        context.setValidating(false);
	        
	        context.load(SPRINGCORE);
	        context.load(SPRINGHADOOP);
	        context.refresh();
	        DZWLService bean = context.getBean(DZWLService.class);
	      
	        Map<String, String> findAllTrans = bean.findAllDzwls();
	        context.registerShutdownHook();
	       // context.stop();
	        //context.destroy();
	        
	       // System.out.println( "****************" + findAllTrans + "*************************");
	       
	       return findAllTrans;
		   
		 }

	 public static void insertCriterion(List<Criterion> entities){
		 
		 	GenericXmlApplicationContext context = new GenericXmlApplicationContext();
	    	
	        context.setValidating(false);
	        
	        context.load(SPRINGCORE);
	        context.load(SPRINGHADOOP);
	        context.refresh();
	        CriterionService bean = context.getBean(CriterionService.class);
	      
	        bean.save(entities);
	        
	        context.registerShutdownHook();
		 
	 }
	 
}
