package com.dw.ftp.client;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dw.ftp.client.DSFtpVFSFileMonitor;
import com.dw.ftp.client.FTPTESTListener;


public class MinaFTPClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(MinaFTPClient.class);

	/**
	 * description:Mina 客户端
	 * 
	 * 传入参数说明：
	 * 0：ftp的地址
	 * 1：soket服务器地址
	 * 2：soket服务器端口
	 * 
	 * 规定：服务器端口说明
	 * 
	 *
	 * 
	 * 
	 * @param args
	 *            void 2016-4-27 上午10:58:27 by chenfeng
	 * @throws FileSystemException 
	 */
	public static void main(String[] args) throws FileSystemException {
		
		if (args.length>0) {
			System.out.println("监听ftp的地址是："+args[0]);
			System.out.println("soket服务器地址:"+args[1]);
			System.out.println("soket服务器端口:"+args[2]);
			
		}
		final FileSystemManager fsManager = VFS.getManager();
		FileObject fileObject = fsManager.resolveFile(args[0]);
		
	/*	FileSystemOptions fsOptions = new FileSystemOptions();
		StaticUserAuthenticator auth = new StaticUserAuthenticator("", "gjzdrlxx", "gjzdrlxx0820");
		DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(fsOptions, auth);
		FtpFileSystemConfigBuilder.getInstance().setPassiveMode(fsOptions, true);
		FileObject fileObject = fsManager.resolveFile(args[0],fsOptions); ftp://10.15.55.15:32121 */
		
		
		DSFtpVFSFileMonitor fileMonitor =null;		
		
		switch (args[2]) {

		
		case "2348":
			fileMonitor=new DSFtpVFSFileMonitor(new FTPTESTListener(args[1], args[2]));
			break;
		
			
		default:
			break;
		}
		
		fileMonitor.setRecursive(true); //一定要放在  fileMonitor.addFile(fileObject)的前面,否则 无法递归
		fileMonitor.addFile(fileObject);
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
