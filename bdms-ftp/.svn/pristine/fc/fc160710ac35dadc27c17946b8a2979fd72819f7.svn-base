package com.ds.bdms.bdms_ftp;


import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ftpserver.usermanager.Md5PasswordEncryptor;
import org.apache.ftpserver.usermanager.PasswordEncryptor;


public class TestJ {

	public static void main(String[] args) throws Exception {
		/*
			PasswordEncryptor passwordEncryptor = new Md5PasswordEncryptor();
			System.out.println(passwordEncryptor.encrypt("1234567"));
		*/
		
		textSubtract();
		
	}
	
	
	public static void textSubtract() throws InterruptedException{
		
		File demo1 = new File("D:/java/eg/demo1");
		
		
		List<File> asList1 = Arrays.asList(demo1.listFiles());
  //Thread.sleep(30000);
		//List<File> asList2 = Arrays.asList(demo1.listFiles());
		
		//List<File> subtract = (List<File>) CollectionUtils.subtract(asList2, asList1);
		
		for(File file : asList1 ){
			
			System.out.println(file.getName());
		}
		
	}
}
