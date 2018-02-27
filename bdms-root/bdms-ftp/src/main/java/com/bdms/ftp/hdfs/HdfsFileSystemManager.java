package com.bdms.ftp.hdfs;

import org.apache.ftpserver.ftplet.FileSystemManager;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;

/**
 * Impelented FileSystemManager to use HdfsFileSystemView
 */
public class HdfsFileSystemManager implements FileSystemManager {
	public FileSystemView createFileSystemView(User user) throws FtpException {
		return new HdfsFileSystemView(user);
	}
}
