package com.bdms.ftp.ftplistener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.bdms.ftp.mina.MinaClientHandler;
import com.bdms.ftp.socket.SendC;

public class FtpToHDFS {

	/**
	 * description:
	 * 
	 * @param args
	 *            void 2015-8-22 下午1:07:08 by Yuxl
	 * @throws FileSystemException
	 */

	public static DefaultFileMonitor get() throws FileSystemException {
		
		final FileSystemManager fsManager = VFS.getManager();

		FileObject fileObject = fsManager.resolveFile("ftp://Administrator:DSwh110119@192.168.7.194/GJ");
		
		DefaultFileMonitor defaultFileMonitor = new DefaultFileMonitor(
				new FileListener() {

					@Override
					public void fileDeleted(FileChangeEvent arg0)
							throws Exception {
						System.out.println("fileDeleted");

					}

					@Override
					public void fileCreated(FileChangeEvent arg0)
							throws Exception {
						System.out.println("fileCreated");
						//VFSUtils.gjFileToHdfsOrSocket(arg0);					
					}

					@Override
					public void fileChanged(FileChangeEvent arg0)
							throws Exception {
						System.out.println("fileChanged");
						//VFSUtils.gjFileToHdfsOrSocket(arg0);	
					}
				});
		defaultFileMonitor.addFile(fileObject);
		defaultFileMonitor.setRecursive(true);

		 return defaultFileMonitor;
	}

	public static void main(String[] args) throws FileSystemException {
		
			DefaultFileMonitor defaultFileMonitor = get();
			
			defaultFileMonitor.start();
			
			new Thread(defaultFileMonitor).start();
		  
	}

}
