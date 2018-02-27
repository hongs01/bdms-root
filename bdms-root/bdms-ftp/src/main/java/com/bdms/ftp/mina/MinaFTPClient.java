package com.bdms.ftp.mina;

import java.util.Timer;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftp.FtpFileObject;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystem;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.ftp.ftplistener.DSFtpVFSFileMonitor;
import com.bdms.ftp.ftplistener.FTPDZWLListener;
import com.bdms.ftp.ftplistener.FTPMetroListener;
import com.bdms.ftp.ftplistener.FTPVideoListener;
import com.bdms.ftp.ftplistener.FTPWifiListener;
import com.bdms.ftp.ftplistener.MetroLostPathsCheckTask;

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
	 * 地铁数据为2345
	 * 电子围栏为2346
	 * 视频数据为2347
	 *
	 * 
	 * 
	 * @param args
	 *            void 2015-8-26 下午3:03:27 by Yuxl
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
		case "2345":
			//处理地铁数据
			fileMonitor=new DSFtpVFSFileMonitor(new FTPMetroListener(args[1], args[2]));
			
			//fileMonitor=new DSFtpVFSFileMonitor(new GAFTPListener(args[1]));

			//启动 定时器  循环 遍历丢失的文件     
			Timer timer = new Timer();
			//MetroLostPathsCheckTask 第三个参数  为  存储  轨交丢失文件路径 的  文件路径   (不带 文件名) 若为 null  则为 相对路径 / 
			timer.schedule(new MetroLostPathsCheckTask(args[1],args[2],null), 10000, 60*1000);
			LOG.info("启动丢失文件检测定时器  成功...");
			
			break;
		
		case "2346":
			fileMonitor=new DSFtpVFSFileMonitor(new FTPDZWLListener(args[1], args[2]));
			break;
		
		case "2347":
			fileMonitor=new DSFtpVFSFileMonitor(new FTPVideoListener(args[1], args[2]));
			fileMonitor.setDelay(200);
			break;
			
		case "2348":
			fileMonitor=new DSFtpVFSFileMonitor(new FTPWifiListener(args[1], args[2]));
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
