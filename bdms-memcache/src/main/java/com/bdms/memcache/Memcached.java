package com.bdms.memcache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;






public class Memcached {
	static MemCachedClient cachedClient = new MemCachedClient(); 
	public Memcached(){
		 @SuppressWarnings("unused")		       
	    SockIOPool pool = SockIOPool.getInstance();                   
		 String[] servers = {"192.168.66.10:12000","192.168.66.11:12000","192.168.66.12:12000",
				 "192.168.66.13:12000","192.168.66.14:12000","192.168.66.15:12000",
				 "192.168.66.16:12000","192.168.66.17:12000",
				 "192.168.66.10:12001","192.168.66.11:12001","192.168.66.12:12001",
				 "192.168.66.13:12001","192.168.66.14:12001","192.168.66.15:12001",
				 "192.168.66.16:12001","192.168.66.17:12001"};          
	    Integer[]  weight={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};  
	    pool.setWeights(weight);//设置服务器权重  
		pool.setServers(servers); 
		//设置初始连接数、最小连接数、最大连接数、最大处理时间  
	    pool.setInitConn(10);        
	    pool.setMinConn(10);         
	    pool.setMaxConn(1000);         
	    pool.setMaxIdle(1000*60*60);  
	    
	    pool.setMaintSleep(60); //设置连接池守护线程的睡眠时间         
	    //设置TCP参数，连接超时  
	    pool.setNagle(false);         
	    pool.setSocketTO(60);        
	    pool.setSocketConnectTO(0); 
	    
	    //初始化并启动连接池 
	    pool.initialize();  		
	}
         
        
    public boolean put(String key, Object value) {  
	  return cachedClient.set(key, value);   
	} 
  
    public boolean put(String key, Object value, Integer expire) {    
	  return cachedClient.set(key, value, expire);    
    }           
  
    public  boolean replace(String key, Object value) {         
	  return cachedClient.replace(key, value);     
	}         
    
    public  boolean replace(String key, Object value, Integer expire) {   
	 return cachedClient.replace(key, value, expire);    
	}   
 
    public  Object get(String key) {     
	 return cachedClient.get(key);    
	}       
    
    public boolean delete(String key){
    	cachedClient.delete(key);
    	return true;
    }
 
    public Map<String,Object> getMulti(String[] keys){        
	 return cachedClient.getMulti(keys);    
	}   
 
   public boolean keyExists(String key){         
	 return cachedClient.keyExists(key);   
   }
	

   public static   Map<String,String> getAllKeys() {
	   Map<String,String> hashMap=new HashMap<String,String>();
	   //List list = new ArrayList();
	    Map<String,Map<String,String>>  items =cachedClient.statsItems();
	    for (Iterator itemIt = items.keySet().iterator(); itemIt.hasNext();) {
	        String itemKey = (String) itemIt.next();      
	       Map maps = (Map) items.get(itemKey);
	        for (Iterator mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {
	            String mapsKey = (String) mapsIt.next();
	            String mapsValue = (String) maps.get(mapsKey);        
	           if (mapsKey.endsWith("number")) {
	                String[] arr = mapsKey.split(":");
	                int slabNumber = Integer.valueOf(arr[1].trim());
	                int limit = Integer.valueOf(mapsValue.trim());     
	                Map<String,Map<String,String>> dumpMaps = cachedClient.statsCacheDump(slabNumber, limit);
	                for (Iterator dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {
	                    String dumpKey =(String) dumpIt.next();                         
	                   Map allMap = dumpMaps.get(dumpKey);
	                    for (Iterator allIt = allMap.keySet().iterator(); allIt.hasNext();) {
	                        String allKey = (String)allIt.next();
	                        hashMap.put(allKey.trim(),allKey.trim());
	                        //list.add(allKey.trim());
	                    }
	                }
	            }
	        }
	    }
	    return   hashMap;
	}
   
   public boolean fulshAll(List list)
   {
   	for(int i=0;i<list.size();i++)
   	 { 
   		cachedClient.delete((String)list.get(i));
   	 }
   	return true;	
   }
}



	
	
	

