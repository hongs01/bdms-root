package com.dw.ftp.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dw.ftp.client.MinaClient;
import com.dw.ftp.json.JsonHelper;


//import com.facebook.fb303.FacebookService.AsyncProcessor.getCounter;

/* 
 * Description:
 * 		FTP文件操作
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2016-4-28上午9:35:43       1.0            Created by chenfeng
 * 
 * =============================================================
 * 
 * 
 */
public class VFSUtils {

	private static final Logger LOG = LoggerFactory.getLogger(VFSUtils.class);

	/** 初始化 */
	private static FileSystemManager fsManager = null;
	
	static {
		try {
			fsManager = VFS.getManager();
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	public static final int BUFSIZE = 1024 * 8;
	/**
	 * java合并多个小文件
	 * @param outFile
	 * @param files
	 */
	@SuppressWarnings("resource")
	public static void mergeFilesThenUpload(String outPath,String outFileName, List<String> files,String path2) {
		FileChannel outChannel = null;
		if(new File(outPath+outFileName).exists()){
			LOG.info("文件已存在，不作处理");
		}else{
		LOG.info("Merge files into " + outPath+outFileName);
			try {
				outChannel = new FileOutputStream(outPath+outFileName).getChannel();
				for(String f : files){
					FileChannel fc = new FileInputStream(f).getChannel(); 
					ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
					while(fc.read(bb) != -1){
						bb.flip();
						outChannel.write(bb);
						bb.clear();
					}
					fc.close();
				}
				LOG.info("Merge SUCCESS!! ");
				//upload to hdfs
				Configuration conf = new Configuration(); 
				conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());  
				conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName() );
				String HBASEHDFS = "hbase/hdfs-site.xml"; 
				String CORE = "hbase/core-site.xml";
			    conf.addResource(HBASEHDFS); conf.addResource(CORE); 
			    FileSystem fs =	FileSystem.get(conf); 
			    LOG.info("已连接到hdfs："+fs);
			    
			    Path p = new Path(fs.getConf().get("fs.defaultFS")+path2 + outFileName);
			    
			    LOG.info("文件"+p+"是否存在："+fs.exists(p));
			    //BufferedReader input=null;
			    
			    LOG.info("本地文件路径:"+outPath+outFileName+",准备上传到hdfs: "+p.toString());
			    if(!fs.exists(p)){
			    	long aaa = System.currentTimeMillis(); 
			    	fs.copyFromLocalFile(new Path(outPath+outFileName), p);
			    	fs.close(); 
			    	fs = null; 
			    	long bbb = System.currentTimeMillis();
			    	LOG.info("文件上传到hdfs成功!耗时：" + (bbb - aaa) / 1000 + "s");
			    }else{
			    	LOG.info("文件已存在，不作处理");
			    }
								
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {if (outChannel != null) {outChannel.close();}} catch (IOException ignore) {}
			}
		}
	}

	// 
	public static void testFileToSocket(FileChangeEvent arg0, String address,
			int port) throws IOException {
		System.out.println("fileCreated:" + arg0.getFile().getName());
		FileObject file = arg0.getFile();
		String fileName = file.getName().getBaseName();
		System.out.println(fileName);

		MinaClient client = null;
		InputStreamReader reader = null;
		BufferedReader bufferedReader = null;
		String line = null;
		
		try {
			
			reader = new InputStreamReader(file
					.getContent().getInputStream(), "GBK");
			
			bufferedReader = new BufferedReader(reader);
			
			client = new MinaClient(address, port);
			
			LOG.info("开始解析测试文件:" + file);
			int count=0;
			if (client.connect()) {
				
				while ((line = bufferedReader.readLine()) != null ) {
						
					
//					Map map = new JsonHelper().toMap(line);
//					String userId = (String) map.get("userId");
//					String imei = (String) map.get("imei");
//					String ip = (String) map.get("ip");
//					String longitude = (String) map.get("longitude");
//					String latitude = (String)map.get("latitude");
//					String loginLocation = (String)map.get("loginLocation");
//					
//					String hsOsVersion = (String) map.get("hsOsVersion");
//					String brand = (String) map.get("brand");
//					String resolution = (String) map.get("resolution");
//					String osType = (String) map.get("osType");
//					String osVersion = (String)map.get("osVersion");
//					String currentPage = (String)map.get("currentPage");
//					
//					String enterTime = (String) map.get("enterTime");
//					String leaveTime = (String) map.get("leaveTime");
//					String contentId = (String) map.get("contentId");
//					String contentType = (String) map.get("contentType");
//					String operationType = (String)map.get("operationType");
//					String status = (String)map.get("status");
//					String log = (String)map.get("log");
//					
//					String total = userId+";"+imei+";"+ip+";"+longitude+";"+latitude+";"+loginLocation+";"+hsOsVersion+";"+
//							brand+";"+resolution+";"+osType+";"+osVersion+";"+currentPage+";"+enterTime+";"+leaveTime+";"+
//							contentId+";"+contentType+";"+operationType+";"+status+";"+log;
					count++;
					System.out.println(count+"---"+line);
					//client.send(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (client != null) {
				client.close();
				client = null;
			}
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}

				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}

	
	/**
	  * description:  将指定路径 转换成 FileObject
	  * @param path
	  * @return
	  * FileObject
	  * 2015-9-21 下午12:03:41
	  * by 
	 */
	public static FileObject resolveFile(String path){
		
		FileObject file = null;
		try {
			
			file = fsManager.resolveFile(path);
			
		} catch (FileSystemException e) {
			
			LOG.error("解析文件时 , commons vfs 转换数据文件 ： " + path+ " 失败..", e);
		}
		
		return file;
	}
	
	public static void main(String[] args) throws Exception {

		FileSystemManager fsManager = VFS.getManager();
		FileObject fileObject = fsManager.resolveFile("D:\\test.txt");

		FileContent content = fileObject.getContent();

		// InputStream inputStream = content.getInputStream();

		OutputStream outputStream = content.getOutputStream(true);
		outputStream
				.write(("123456787-" + new Date(System.currentTimeMillis()) + "\n").getBytes());
		Thread.sleep(3000);
		outputStream
				.write(("123456787-" + new Date(System.currentTimeMillis()) + "\n").getBytes());
		outputStream.flush();

		System.out.println(content.getLastModifiedTime());
		content.setLastModifiedTime(1441762015574L);
		System.out.println(new Date(content.getLastModifiedTime()));
	}

}
