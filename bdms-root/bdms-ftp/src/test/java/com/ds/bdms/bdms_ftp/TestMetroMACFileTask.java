package com.ds.bdms.bdms_ftp;

import java.util.Timer;

import org.junit.Test;

import com.bdms.ftp.listener.FTPMetroMACFileTask;

public class TestMetroMACFileTask {
	
	@Test
	public void testTimerTask(){
		
		Timer t = new Timer();
		t.schedule(new FTPMetroMACFileTask("E:\\pcs_201508201606.csv"), 1000, 1000*60);
	}
	
	public static void main(String[] args) {
		
		Timer t = new Timer();
		t.schedule(new FTPMetroMACFileTask("E:\\pcs_201508201606.csv"), 1000, 1000*60);
	}

}
