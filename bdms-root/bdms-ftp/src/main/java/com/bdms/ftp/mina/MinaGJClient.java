package com.bdms.ftp.mina;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

import com.bdms.ftp.ftplistener.VFSUtils;

public class MinaGJClient {

	/**
	 * description:
	 * 
	 * @param args
	 *            void 2015-8-24 下午1:55:35 by Yuxl
	 * @throws FileSystemException
	 */

	public static void main(String[] args) throws FileSystemException {
		final FileSystemManager fsManager = VFS.getManager();
		FileObject fileObject = fsManager.resolveFile("ftp://Administrator:DSwh110119@192.168.7.194/GJ/");

		DefaultFileMonitor monitor = new DefaultFileMonitor(new FileListener() {

			@Override
			public void fileDeleted(FileChangeEvent arg0) throws Exception {
				System.err.println("fileDeleted");

			}

			@Override
			public void fileCreated(FileChangeEvent changeEvent)
					throws Exception {
				System.err.println("fileCreated");
				//VFSUtils.gjFileToHdfsOrSocket(changeEvent);	
			}

			@Override
			public void fileChanged(FileChangeEvent arg0) throws Exception {
				System.err.println("fileChanged");	

			}
		});
		monitor.addFile(fileObject);
		monitor.setRecursive(true);
		monitor.start();
		new Thread(monitor).start();
		
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
