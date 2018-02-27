package com.bdms.ftp.ftplistener;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;

public class FTPDZWLListener implements FileListener {
	
	private String address;

	private int port;

	public FTPDZWLListener(String address, String port) {
		this.address = address;
		this.port = Integer.parseInt(port);
	}

	@Override
	public void fileChanged(FileChangeEvent arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {
		VFSUtils.dzwlFileToSocket(changeEvent,address,port);

	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
