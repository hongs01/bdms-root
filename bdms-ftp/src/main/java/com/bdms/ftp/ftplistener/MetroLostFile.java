package com.bdms.ftp.ftplistener;

import org.apache.commons.vfs2.FileObject;

public class MetroLostFile {
	
	private String mac;   //丢失文件 对应的 mac字串
	private FileObject lostFile;  //丢失文件 
	private String destDir;  //目标路径
	
	public MetroLostFile() {}

	public MetroLostFile(String mac, FileObject lostFile, String destDir) {
		super();
		this.mac = mac;
		this.lostFile = lostFile;
		this.destDir = destDir;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public FileObject getLostFile() {
		return lostFile;
	}
	public void setLostFile(FileObject lostFile) {
		this.lostFile = lostFile;
	}
	public String getDestDir() {
		return destDir;
	}
	public void setDestDir(String destDir) {
		this.destDir = destDir;
	}

	public String toString() {
		return "MetroLostFile [mac=" + mac + ", lostFile=" + lostFile
				+ ", destDir=" + destDir + "]";
	}
	
	
	

}
