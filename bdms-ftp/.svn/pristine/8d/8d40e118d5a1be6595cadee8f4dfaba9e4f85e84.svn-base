package com.bdms.ftp.mina;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import com.bdms.ftp.ftplistener.DSFtpVFSFileMonitor;
import com.bdms.ftp.ftplistener.FTPMetroListener;

public class FTPChaneT {

	/* 
	 * @Title: main 
	 * @Description: TODO 
	 * @param @param args    
	 * @return void    
	 * @throws 
	 * @Author  Lixc
	 */
	public static void main(String[] args) throws FileSystemException {
		final FileSystemManager fsManager = VFS.getManager();
		//FileObject fileObject = fsManager.resolveFile("ftp://gjzdrlxx:gjzdrlxx0820@10.15.55.15:32121/MAC_DAY_20150909");
		FileObject fileObject = fsManager.resolveFile(args[0]);
		DSFtpVFSFileMonitor fileMonitor =null;

		fileMonitor=new DSFtpVFSFileMonitor(new FileListener() {
			
			@Override
			public void fileDeleted(FileChangeEvent event) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void fileCreated(FileChangeEvent event) throws Exception {
			}
			
			@Override
			public void fileChanged(FileChangeEvent event) throws Exception {
				System.out.println(event.getFile().getContent().getSize());
			}
		});
		fileMonitor.addFile(fileObject);
		fileMonitor.setRecursive(true);
		fileMonitor.start();
		
		for (;;) {
			try {
				Thread.currentThread();
				Thread.sleep(1000);
				// System.out.println(System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
