package com.bdms.memcache.read;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bdms.memcache.configuration.Memcachedconfig;
import com.whalin.MemCached.MemCachedClient;

@Service("memcacheRead")
public class MemcacheReadServiceImpl implements MemcacheReadService {

	private MemCachedClient cachedClient;

	public MemcacheReadServiceImpl() {
		Memcachedconfig.getInstance();
		cachedClient=new MemCachedClient();
	}

	@Override
	public Map<String,String> read() {
		//cachedClient.add("aaaa", "bbbbbbb");
		//return cachedClient.statsItems();
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
		    return hashMap;
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return cachedClient.get(key); 
	}

}
