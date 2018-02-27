package com.bdms.ftp.ftplistener;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: wifi数据监听...
 * 
 * History： ============================================================= Date
 * Version Memo 2015-9-17下午4:07:42 1.0 Created by YuXiaoLin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class FTPWifiListener implements FileListener {

	private String address;

	private int port;

	private static Logger LOG = LoggerFactory.getLogger(FTPWifiListener.class);

	public FTPWifiListener(String address, String port) {
		this.address = address;
		this.port = Integer.parseInt(port);
	}

	@Override
	public void fileChanged(FileChangeEvent arg0) throws Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.vfs2.FileListener#fileCreated(org.apache.commons.vfs2
	 * .FileChangeEvent)
	 */
	@Override
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {
		// 监听到有文件创建
		FileObject file = changeEvent.getFile();

		String baseName = file.getName().getBaseName();
		if (baseName.endsWith(".zip")) {
			LOG.info("监听到WIFI数据文件...,准备向Sokect传输数据...");
			VFSUtils.wifiDataToSocket(changeEvent, address, port);
		} else {
			LOG.info("不是WIFI数据...,不做处理");
		}

		

	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		
	}

}
