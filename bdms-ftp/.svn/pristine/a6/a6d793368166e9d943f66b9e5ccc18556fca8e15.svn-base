package com.bdms.ftp.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class DirListener {
	
	private final static  String DIR = "/tmp/files/";
	
	private File listenedDir;
	private List<File> old_files;
	private List<File> new_files;
	private List<File> add_files;
	
	public DirListener(){
		
		listenedDir = new File(DIR);
		if(listenedDir.isFile() || !listenedDir.exists()){
			listenedDir.mkdir();
		}
		
		old_files = Arrays.asList(listenedDir.listFiles());
	}
	
	//不考虑 文件删除 和 文件夹中含文件夹的情况   
	@SuppressWarnings("unchecked")
	public List<File> startListenAndReturnNewFiles(){
		
		add_files = new ArrayList<File>();
		new_files = Arrays.asList(listenedDir.listFiles());
		
		if( new_files.size() > old_files.size() ){
			// 有新文件 
			add_files =  (List<File>) CollectionUtils.subtract(new_files, old_files);
			old_files = new_files;
		}
		
		return add_files;
	}
	

}
