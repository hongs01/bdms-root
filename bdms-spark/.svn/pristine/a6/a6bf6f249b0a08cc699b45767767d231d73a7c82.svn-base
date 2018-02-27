package com.bdms.spark;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class TestMemcache implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	// 创建全局的唯一实例 
    protected static MemCachedClient mcc = new MemCachedClient(); 
    protected static TestMemcache memCached = new TestMemcache(); 

    // 设置与缓存服务器的连接池 
    static { 
    	
    	  // 设置cache数据的原始类型是String
		  //默认值是false
		 //只有在确定cache的数据类型是string的情况下才设为true，这样可以加快处理速度。
    	mcc.setPrimitiveAsString(true);
    	
        // 服务器列表和其权重，个人memcached地址和端口号 
        String[] servers = { "192.168.66.10:12000","192.168.66.11:12000","192.168.66.12:12000","192.168.66.13:12000",
        					 "192.168.66.14:12000","192.168.66.15:12000","192.168.66.16:12000","192.168.66.17:12000",
        		             "192.168.66.10:12001","192.168.66.11:12001","192.168.66.12:12001","192.168.66.13:12001",
        		             "192.168.66.14:12001","192.168.66.15:12001","192.168.66.16:12001","192.168.66.17:12001"
        		}; 
        Integer[] weights = { 3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}; 

        // 获取socke连接池的实例对象 
        SockIOPool pool = SockIOPool.getInstance(); 

        // 设置服务器信息 
        pool.setServers(servers); 
        pool.setWeights(weights); 
        
        //容错  默认为true
       // pool.setFailover(true);

        // 设置初始连接数、最小和最大连接数以及最大处理时间 
        pool.setInitConn(100); 
        pool.setMinConn(100); 
        pool.setMaxConn(250); 
        pool.setMaxIdle(1000 * 60 * 60 * 6); 

        // #设置连接池维护线程的睡眠时间
        pool.setMaintSleep(2000); 

        //设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
        pool.setNagle(false); 
        
        //设置socket的读取等待超时时间 
        pool.setSocketTO(3000); 
        
       // pool.setSocketConnectTO(0); 

        // 初始化连接池 
        pool.initialize(); 
    } 

    /** 
     * 保护型构造方法，不允许实例化！ 
     * 
     */ 
    protected TestMemcache() { 

    } 

    /** 
     * 获取唯一实例. 
     * 
     * @return 
     */ 
    public static TestMemcache getInstance() { 
        return memCached; 
    } 

    /** 
     * 添加一个指定的值到缓存中. 
     * 
     * @param key 
     * @param value 
     * @return 
     */ 
    public boolean add(String key, Object value) { 
        return mcc.set(key, value); 
    } 

    public boolean add(String key, Object value, Date expiry) { 
        return mcc.set(key, value, expiry); 
    } 

    /** 
     * 替换一个指定的值到缓存中. 
     * 
     * @param key 
     * @param value 
     * @return 
     */ 
    public boolean replace(String key, Object value) { 
        return mcc.replace(key, value); 
    } 

    public boolean replace(String key, Object value, Date expiry) { 
        return mcc.replace(key, value, expiry); 
    } 

    /** 
     * 删除一个指定的值到缓存中. 
     * 
     * @param key 
     * @param value 
     * @return 
     */ 
    public boolean delete(String key) { 
        return mcc.delete(key); 
    } 

    /** 
     * 根据指定的关键字获取对象. 
     * 
     * @param key 
     * @return 
     */ 
    public Object get(String key) { 
    	//System.out.println( mcc.stats());
        return mcc.get(key); 
    } 

  public  static void main(String[] args) throws InterruptedException, IOException {
	  
    	// JSONNonTransactionalSerializer js = new JSONNonTransactionalSerializer();
    	//TestMemcache instance = memCached.getInstance();
    	
    	//getAllKeys(mcc);
    	//mcc.statsItems().size();
    	//System.out.println(instance.get("m1000") );
    	//System.out.println(mcc.statsItems().size());
    	//System.out.println(((byte[])instance.get("jechedo187501")).length);
    	
    	 /*for(int i = 0 ; i < 20000000 ; i++){
    		if(instance.get("jechedo"+i) == null){
    			System.err.println("jechedo"+i);
    			Thread.sleep(1000);
    		}else{
    			System.out.println("jechedo"+i);
    		}
    	}*/
    	
    	 URL url = new URL("http://dswhhadoop-3:6766/testGIS/*");
    	 URLConnection c =url.openConnection();
    	 c.setRequestProperty("Accept", "application/json");
		
    	 c.connect();
    	 
    	 System.out.println("---" + c.getHeaderFields());
   
    	 
	}
  
  
  public static List<String> getAllKeys(MemCachedClient memCachedClient) {  
	   
	    List<String> list = new ArrayList<String>();  
	    
	    Map<String, Map<String, String>> items = memCachedClient.statsItems();
	    
	    for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {  
	        String itemKey = itemIt.next();  
	        Map<String, String> maps = items.get(itemKey);  
	        for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {  
	            String mapsKey = mapsIt.next();  
	               String mapsValue = maps.get(mapsKey);  
	               if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str  
	                String[] arr = mapsKey.split(":");  
	                   int slabNumber = Integer.valueOf(arr[1].trim());  
	                   int limit = Integer.valueOf(mapsValue.trim());  
	                   Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);  
	                   for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {  
	                       String dumpKey = dumpIt.next();  
	                       Map<String, String> allMap = dumpMaps.get(dumpKey);  
	                       for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {  
	                           String allKey = allIt.next();  
	                           //list.add(allKey.trim());
	                           if(allKey.contains("m") )
	                           System.out.println(allKey.trim() + " --- > " + memCachedClient.get(allKey));
	  
	                       }  
	                   }  
	               }  
	        }  
	    }  
	    return list;  
	}

} 
