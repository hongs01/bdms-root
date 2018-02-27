package com.dbms.memcache.configtest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.bdms.memcache.read.*;
public class MemcheTest extends JunitSpringInitialize {

	@Autowired
	private MemcacheReadService memcacheReadService; 
	
	/*@Test
	public void readTest() {
		Map<String,String> data=memcacheReadService.read();
		Set<String> set=data.keySet();
		Iterator iter = set.iterator();
		int i=0;
		while(iter.hasNext()){
			
			String key = (String)iter.next();
			if(memcacheReadService.get(key)!=null){
				if(i==5) break;
				i=i+1;
			System.out.println(key);//获取key
			System.out.println(memcacheReadService.get(key));//获取缓存中key对应的value
			}
		}
	//	System.out.println("开始读数据...");
	//  System.out.println(set.size());	
		
		
		
	}
	@Test
	public void getAllValueTest() {
		List list=new ArrayList();
		list=memcacheReadService.getAllValue();
		for(int i=0;i<4;i++){
			
			JsonData jd=(JsonData)list.get(i);
			System.out.println(jd.GIX_X+"\t"+jd.GIY_Y);
		}
		
}*/
}
