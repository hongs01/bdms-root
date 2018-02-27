package com.bdms.ftp.ftplistener;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;

/* 
 * Description:
 * 		FTP文件监听器
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-26下午3:21:12            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class FTPListener implements FileListener {

	@Override
	public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
		System.out.println("文件大小...");
	}

	@Override
	public void fileCreated(FileChangeEvent changeEvent) throws Exception {

		//VFSUtils.W2Scokect(changeEvent,);

	}

	@Override
	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		System.out.println("文件删除...");
	}

}
