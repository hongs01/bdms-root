package com.bdms.ftp.ftplistener;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;

public class FTPVideoListener implements FileListener{
	
	private String address;

	private int port;

	public FTPVideoListener(String address, String port) {
		this.address = address;
		this.port = Integer.parseInt(port);
	}

	@Override
	public void fileChanged(FileChangeEvent arg0) throws Exception {
		System.out.println("ftpvideolistener-fileChanged");
		
	}

	@Override
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {
		
		FileObject file = changeEvent.getFile();
		
		if(file.getType().getName().equals("file")){
			System.out.println("检测到新文件:  " + file );
			VFSUtils.videoFileToSocket(file,address,port);
		}else{
			System.out.println("检测到新的非文件 :  " + file );
		}
		
	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		System.out.println("ftpvideolistener-fileDeleted");
	}

}
