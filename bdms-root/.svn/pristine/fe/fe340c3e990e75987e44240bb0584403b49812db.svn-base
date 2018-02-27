package com.bdms.spark.hbase;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class LineNumUtil implements Serializable{
	
	private static final long serialVersionUID = -4605711809336151833L;
	
	private static final String ZKC = "dswhhadoop-4:2181,dswhhadoop-3:2181,dswhhadoop-2:2181,dswhhadoop-5:2181,dswhhadoop-6:2181,dswhhadoop-7:2181,dswhhadoop-8:2181,dswhhadoop-1:2181";
	private static final int SESSION_TIMEOUT = 5000;  
	private static final String PATH = "/SparkData";
	
    private static CountDownLatch connectedSignal = new CountDownLatch(1); 
    
	private static LineNumUtil line;
	
	private ZooKeeper zk ;
    
	
	private int lineNum = 0 ; 
	

	private LineNumUtil(){
		
		try {
			zk = new ZooKeeper(ZKC, SESSION_TIMEOUT, new ConnWatcher());
			// 等待连接完成  
			connectedSignal.await(); 
			
			if (zk.exists(PATH, false) == null){
				zk.create(PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
				//zk.create("/spark/line","0".getBytes() ,CreateMode.)
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
	public static LineNumUtil getInstance(){
		
		if(line == null ){
			
			synchronized (LineNumUtil.class) {
				if(line == null){
					line = new LineNumUtil();
				}
			}
		}
		
		 
		
		return line;
	}

	public int nextLineNum(){
		
		try {
			zk.getData(PATH, true, null);
			
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return lineNum++;
		
	}
	
	public void  resetLineNum(){
		
		lineNum = 0;
	}
	
	
	static class ConnWatcher implements Watcher {  
        public void process(WatchedEvent event) {  
            // 连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected  
            if (event.getState() == KeeperState.SyncConnected) {  
                // 放开闸门, wait在connect方法上的线程将被唤醒  
                connectedSignal.countDown();  
            }  
        }  
    }  
}
