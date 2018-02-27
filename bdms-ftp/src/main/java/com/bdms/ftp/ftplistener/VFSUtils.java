package com.bdms.ftp.ftplistener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileFilterSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdms.common.lang.StringUtils;
import com.bdms.ftp.data.parse.ParseWifiData;
import com.bdms.ftp.mina.MinaClient;

//import com.facebook.fb303.FacebookService.AsyncProcessor.getCounter;

/* 
 * Description:
 * 		FTP文件操作
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-27上午9:35:43            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
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

	//
	public static void gjFileToHdfsOrSocket(FileChangeEvent arg0,
			String address, int port) throws IOException {
		System.out.println("fileCreated:" + arg0.getFile().getName());
		FileObject createdFile = arg0.getFile();
		String fileName = createdFile.getName().getBaseName();
		System.out.println(fileName);

		/*
		 * if (fileName.startsWith("FLUXNEW_DAY_") ||
		 * fileName.startsWith("OD_DAY_")) { // hdfs BufferedReader input =
		 * null; System.out.println("transfer to hdfs"); long filesize =
		 * fsManager.resolveFile(arg0.getFile().toString())
		 * .getContent().getSize(); System.out.println("init filesize is :" +
		 * filesize); long counter = 0; while (filesize == 0) { try { counter++;
		 * System.out.println(counter + "---sleep 1 sec,filesize is:" +
		 * filesize); Thread.sleep(1000); //
		 * filesize=fsManager.resolveFile(arg0.
		 * getFile().toString()).getContent().getSize();
		 * System.out.println(arg0.getFile()); filesize =
		 * arg0.getFile().getContent().getSize(); if (counter > 30) { break; } }
		 * catch (InterruptedException e) { e.printStackTrace(); } }
		 * Configuration conf = new Configuration(); String HBASEHDFS =
		 * "hbase/hdfs-site.xml"; String CORE = "hbase/core-site.xml";
		 * conf.addResource(HBASEHDFS); conf.addResource(CORE); FileSystem fs =
		 * FileSystem.get(conf); System.out.println(fs); long aaa =
		 * System.currentTimeMillis(); Path p = new
		 * Path("hdfs://dswhhadoop-1:8020/tmp/tmp/" + fileName);
		 * System.out.println(p); FSDataOutputStream output = fs.create(p);
		 * input = new BufferedReader(new InputStreamReader(createdFile
		 * .getContent().getInputStream())); System.out.println("create input");
		 * String message = null; while ((message = input.readLine()) != null) {
		 * // System.out.println(message); // 向服务器端发送信息
		 * output.write(message.getBytes()); } output.flush(); output.close();
		 * fs.close(); fs = null; long bbb = System.currentTimeMillis();
		 * System.out.println("hdfs success!耗时：" + (bbb - aaa) / 1000 + "s"); }
		 * else
		 */
		if (fileName.startsWith("FLUXNEW_SEG_")) {
			InputStreamReader reader = new InputStreamReader(arg0.getFile()
					.getContent().getInputStream());
			BufferedReader bufferedReader = new BufferedReader(reader);

			System.out.println("small files");
			MinaClient client = new MinaClient(address, port);
			if (client.connect()) {
				while (bufferedReader.ready()) {
					client.send(bufferedReader.readLine());
					// System.out.println(bufferedReader.readLine());
				}
			}
			client.close();
		}
	}

	//
	public static int gjMD5File(FileChangeEvent arg0, String address, int port,
			int RowNum) throws IOException {

		FileObject createdFile = arg0.getFile();
		String fileName = createdFile.getName().getBaseName();
		System.out.println(createdFile.getParent());

		// String regEx="\\w+(,\\w+)* ";
		// Pattern pattern = Pattern.compile(regEx);

		if (fileName.contains("MAC_DAY_")) {

			int tempRowNum = 0;
			String str;
			BufferedReader macReader = new BufferedReader(
					new InputStreamReader(arg0.getFile().getContent()
							.getInputStream()));

			while ((str = macReader.readLine()) != null) {

				// Matcher matcher = pattern.matcher(str);
				// System.out.println(matcher.matches());
				if (str.contains("FLUXNEW_SEG_")) {
					tempRowNum++;
					if (tempRowNum > RowNum) {
						String filename = str.split(",")[1];
						System.out.println(filename);
						// 写给socket

						if (fsManager.resolveFile(
								arg0.getFile().getParent().toString()
										+ filename).exists()) {
							InputStreamReader reader = new InputStreamReader(
									fsManager
											.resolveFile(
													arg0.getFile().getParent()
															.toString()
															+ filename)
											.getContent().getInputStream());
							BufferedReader bufferedReader = new BufferedReader(
									reader);
							MinaClient client = new MinaClient(address, port);
							if (client.connect()) {
								while (bufferedReader.ready()) {
									client.send(bufferedReader.readLine());
									// System.out.println(bufferedReader.readLine());
								}
							}
							client.close();
						} else {
							System.out.println(fileName + "尼玛不存在，写毛线校验文件里面去啊！");
						}

					}
					RowNum = tempRowNum;
				}
			}
			System.out.println("RowNum is :" + RowNum);
		}
		return RowNum;
	}

	/**
	 * 解析轨交校验文件 最新的行
	 * 
	 * @param address
	 * @param port
	 * @param latestLines
	 * @throws IOException
	 */
	public static void parseLatestMacFileData(String address, int port,
			String rootDir, List<String> latestLines) {

		String fileName = null;
		FileObject latestFile = null;
		String path = null;

		MetroLostFilePaths mlfs = MetroLostFilePaths.getInstance(null);
		for (String line : latestLines) {

			if (line.contains("FLUXNEW_SEG")) {

				fileName = line.split(",")[1];
				LOG.info("开始解析新增的轨交数据文件 ： " + fileName);

				try {
					path = rootDir + File.separator + fileName;
					latestFile = fsManager.resolveFile(path);

					if (latestFile.exists()) {

						if (!parseLatestFLUXNEWFile(address, port, latestFile)) {
							LOG.info("轨交数据文件 ： " + fileName + " 解析失败，添加到丢失列表。");
							mlfs.addLostFile(path);
						} else {
							LOG.info("轨交数据文件 ： " + fileName + " 解析成功。");
						}

					} else {
						LOG.error(" 轨交文件  ：  " + fileName + "不存在，添加到丢失列表。");
						mlfs.addLostFile(path);
					}

				} catch (FileSystemException e) {
					LOG.error("解析文件时 , commons vfs 转换最新轨交数据文件 ： " + fileName
							+ " 失败..", e);
				}

			}else if(line.contains("OD_DAY")){
				fileName = line.split(",")[1];
				LOG.info("轨交OD数据文件 ： " + fileName + " 已被监听到。");
				LOG.info("开始解析新增的轨交OD数据文件 ： " + fileName);

				try {
					path = rootDir + File.separator + fileName;
					latestFile = fsManager.resolveFile(path);

					if (latestFile.exists()) {
						LOG.info("已检测到新增的轨交OD数据文件 ： " + fileName);
						if (!parseLatestODFile(address, port, latestFile)) {
							LOG.info("轨交OD文件 ： " + fileName + " 解析失败，添加到丢失列表。");
							mlfs.addLostFile(path);
						} else {
							LOG.info("轨交OD文件 ： " + fileName + " 解析成功。");
						}
					} else {
						LOG.error(" OD数据文件  ：  " + fileName + "不存在，添加到丢失列表。");
						mlfs.addLostFile(path);
					}
				} catch (FileSystemException e) {
					LOG.error("解析文件时 , commons vfs 转换最新轨交数据文件 ： " + fileName
							+ " 失败..", e);
				} catch (IOException e) {
					LOG.error("文件上传到 hdfs时失败  ", e);
				}	
			}else if(line.contains("FLUXNEW_DAY")){
				fileName = line.split(",")[1];
				LOG.info("轨交Day数据文件 ： " + fileName + " 已被监听到。");
				LOG.info("开始解析新增的轨交Day数据文件 ： " + fileName);

				try {
					path = rootDir + File.separator + fileName;
					latestFile = fsManager.resolveFile(path);
					
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					String fileDateStr=fileName.split("_")[2];
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(fileDateStr)); 
					cal.set( Calendar.DATE, 1 );  
			        cal.roll(Calendar.DATE, - 1 );  
			        Date endTime=cal.getTime();
			        LOG.info("当月最后一天为："+sdf.format(endTime)+",文件日期为："+fileDateStr);
					
					if (sdf.format(endTime).equals(fileDateStr)){
						if(latestFile.exists()) {
							LOG.info("已检测到新增的轨交Day数据文件 ： " + fileName);
							if (!parseLatestDayFile(address, port,latestFile)) {
								LOG.info("轨交Day文件 ： " + fileName + " 解析失败，添加到丢失列表。");
								mlfs.addLostFile(path);
							} else {
								LOG.info("轨交Day文件 ： " + fileName + " 解析成功。");
							}
						} else {
							LOG.error(" Day数据文件  ：  " + fileName + "不存在，添加到丢失列表。");
							mlfs.addLostFile(path);
						}
					}
				} catch (FileSystemException e) {
					LOG.error("解析文件时 , commons vfs 转换最新轨交数据文件 ： " + fileName
							+ " 失败..", e);
				} catch (IOException e) {
					LOG.error("文件上传到 hdfs时失败  ", e);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * 通过regx查找本地路径下符合条件的所有文件(只是一级目录，未递归)
	 * @param filePath
	 * @return  List<String filename>
	 */
	 static List<String> getFiles(String filePath,String filename){
		 File root = new File(filePath);
		 File[] files = root.listFiles();
		 List<String> filelist=new ArrayList<String>();
		 
		 
		 String regEx = "^"+filename.substring(0,filename.length()-2)+"[0-9]{2}$";
		 Pattern pattern = Pattern.compile(regEx);
		 Matcher matcher = null;
		 
		 for(File file:files){ 
			 matcher = pattern.matcher(file.getName());
			 if(file.isFile()&& matcher.matches()){
				 filelist.add(file.getAbsolutePath());
			 }   
		 }
		 return filelist;
	 }
	
	
	

	/*
	 * @Title: copyMetroLatestFiles 拷贝最新的 轨交文件 到 指定路径
	 * 
	 * @Description: TODO
	 * 
	 * @param @param rootDir
	 * 
	 * @param @param latestLines
	 * 
	 * @param @param destDir
	 * 
	 * @return void
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static void copyMetroLatestFiles(String macFileName, String rootDir,
			List<String> latestLines, String destDir) {

		String fileName = null;
		FileObject latestFile = null;

		MetroLostFiles mlfs = MetroLostFiles.getInstance();

		String localMacFilePath = destDir + File.separator + macFileName;

		for (String line : latestLines) {

			if (line.contains("FLUXNEW_SEG")) {

				fileName = line.split(",")[1];

				try {
					latestFile = fsManager.resolveFile(rootDir + File.separator +fileName);

					if (latestFile.exists()) {

						LOG.info("开始拷贝新增的轨交数据文件 ： " + fileName);

						if (copyLatestFLUXNEWFile(latestFile, destDir)) {

							// 更新本地 MAC文件
							LOG.info(" 更新本地 MAC文件....");
							updateLocalMetroMacFile(localMacFilePath, line);
						} else {
							LOG.error(" 轨交文件  ：  " + fileName
									+ "复制失败, 添加到 丢失列表..");
							mlfs.addLostFile(new MetroLostFile(line,
									latestFile, destDir));
						}

					} else {

						LOG.error(" 轨交文件  ：  " + fileName + "不存在, 添加到 丢失列表..");
						mlfs.addLostFile(new MetroLostFile(line, latestFile,
								destDir));
					}

				} catch (IOException e) {
					LOG.error("copy 文件是  commons vfs 转换最新轨交数据文件 ： " + fileName
							+ " 失败...", e);
				}

			}
		}

	}

	public static boolean checkFileExistsWithString(String filePath)
			throws FileSystemException {

		if (fsManager.resolveFile(filePath).exists()) {

			return true;
		}

		return false;
	}

	/*
	 * @Title: copyLatestFLUXNEWFile
	 * 
	 * @Description: TODO 将文件拷贝到 指定目录下 ,目标文件存在的情况下 放弃拷贝
	 * 
	 * @param @param latestFile
	 * 
	 * @param @param destDir
	 * 
	 * @param @return
	 * 
	 * @return boolean
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static boolean copyLatestFLUXNEWFile(FileObject latestFile,
			String destDir) {

		return copyFileWithSameName(latestFile, destDir);

	}

	/*
	 * @Title: copyLatestFLUXNEWFile
	 * 
	 * @Description: TODO 将文件拷贝到 指定目录下 ,目标文件存在的情况下 放弃拷贝
	 * 
	 * @param @param latestFile
	 * 
	 * @param @param destDir
	 * 
	 * @param @return
	 * 
	 * @return boolean
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static boolean copyFileWithSameName(FileObject file, String destDir) {

		boolean status = false;

		String baseName = file.getName().getBaseName();
		String destPath = destDir + File.separator + baseName;

		LOG.debug(" 开始拷贝文件 : " + file + "到  " + destPath);

		try {
			// commons vfs 的 自带的拷贝方法
			FileObject destFile = fsManager.resolveFile(destPath);

			if (!destFile.exists()) {
				destFile.copyFrom(file, new FileSelector() {

					@Override
					public boolean traverseDescendents(FileSelectInfo fileInfo)
							throws Exception {
						return true;
					}

					@Override
					public boolean includeFile(FileSelectInfo fileInfo)
							throws Exception {
						return true;
					}
				});

				LOG.info(" 拷贝文件 : " + file + "到  " + destPath + "成功 .");
			} else {
				LOG.info(" 目标文件  " + destFile + "已经存在...");
			}

			// commons io 拷贝
			/*
			 * File destination = new File( destPath ); if(
			 * !destination.exists() ){
			 * FileUtils.copyInputStreamToFile(file.getContent
			 * ().getInputStream(), destination); LOG.info( " 开始拷贝文件 : " + file
			 * + "到  " + destPath + "成功 ." ); }else{ LOG.info(" 目标文件  " +
			 * destFile + "已经存在..."); }
			 */

			status = true;

		} catch (IOException e) {
			status = false;
			LOG.error("目标文件 " + file + " 拷贝 到  " + destPath + " 发生异常 ", e);
		}

		return status;
	}

	/*
	 * @Title: appendContent
	 * 
	 * @Description: TODO 将 文件校验 追加到 最新校验文件 后面
	 * 
	 * @param @param localMacFilePath 如果本地不存在指定的mac文件,则创建
	 * 
	 * @param @param content
	 * 
	 * @return void
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static void updateLocalMetroMacFile(String localMacFilePath,
			String macString) throws IOException {

		FileObject localMac = fsManager.resolveFile(localMacFilePath);

		if (!localMac.exists()) {
			LOG.warn(" 校验文件  " + localMacFilePath + " 不存在,执行创建操作...");
			localMac.createFile();
		}

		appendContent(localMac, macString + "\n");

	}

	/*
	 * @Title: appendContent
	 * 
	 * @Description: TODO 将 content 追加到 file 后面
	 * 
	 * @param @param file
	 * 
	 * @param @param content
	 * 
	 * @return void
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static void appendContent(FileObject file, String content) {

		if (StringUtils.isNotBlank(content)) {

			OutputStream outputStream;
			try {
				outputStream = file.getContent().getOutputStream(true);
				outputStream.write(content.getBytes());
				outputStream.flush();
				outputStream.close();

				LOG.info("写入内容 " + content + " 到 文件  " + file + " 成功.");

			} catch (FileSystemException e) {
				LOG.error(" 读取文件   " + file + " 失败.", e);
			} catch (IOException e) {
				LOG.error("写入内容 " + content + " 到 文件  " + file + " 失败了.", e);
			}
		} else {

			LOG.error("  追加的内容 为空! ");
		}

	}

	/*
	 * @Title: parseLatestMacFile
	 * 
	 * @Description: TODO 解析新增的单个轨交数据文件
	 * 
	 * @param @param address
	 * 
	 * @param @param port
	 * 
	 * @param @param latestFile
	 * 
	 * @return void
	 * 
	 * @throws
	 * 
	 * @Author Lixc
	 */
	public static boolean parseLatestFLUXNEWFile(String address, int port,
			FileObject latestFile) {

		InputStreamReader reader = null;
		BufferedReader bufferedReader = null;

		MinaClient client = null;

		String lineData = null;

		boolean status = false;

		try {

			reader = new InputStreamReader(latestFile.getContent()
					.getInputStream());
			bufferedReader = new BufferedReader(reader);

			// LOG.info( latestFile + " 读取 到的大小为 ： " +
			// latestFile.getContent().getSize());
			LOG.info("开始创建socket客户端,并发送信息...");
			client = new MinaClient(address, port);

			// 连接 ftp 并发送内容
			if (client.connect()) {

				while ((lineData = bufferedReader.readLine()) != null) {

					client.send(lineData);

				}
				status = true;
			} else {
				LOG.error("连接 ftp服务器失败 。。。");
				status = false;
			}

		} catch (Exception e) {
			status = false;
			LOG.error("解析新增的轨交数据文件 ： " + latestFile + " 失败。。。。", e);

		} finally {

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
				LOG.error("缓冲流关闭失败。。。");
			}

		}

		return status;
	}
	
	/**
	 * 解析新增的单个轨交OD数据文件
	 * @param address
	 * @param port
	 * @param latestFile
	 * @return 
	 * @throws IOException 
	 * @Author Yangbo
	 */
	public static boolean parseLatestODFile(String address, int port,
			FileObject latestFile) throws IOException {
		boolean status = false;
		LOG.info("开始初始化 hdfs Conf!!!");
		Configuration conf = new Configuration();
		LOG.info("初始化 hdfs Conf:"+conf);
		conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());  
		conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName() );
		String HBASEHDFS = "hbase/hdfs-site.xml"; 
		String CORE = "hbase/core-site.xml";
	    conf.addResource(HBASEHDFS); conf.addResource(CORE); 
	    LOG.info("加载配置文件");
	    FileSystem fs =	FileSystem.get(conf); 
	    LOG.info("已连接到hdfs："+fs);
	    
//	    Path dirpath=new Path(fs.getConf().get("fs.defaultFS")+"/tmp/tmp/");
//	    LOG.info("路径:"+dirpath+"是否存在:"+fs.exists(dirpath));
	    
	    Path p = new Path(fs.getConf().get("fs.defaultFS")+"/tmp/od/" + latestFile.getName().getBaseName());
	    
	    LOG.info("文件"+p+"是否存在："+fs.exists(p));
	    //BufferedReader input=null;
	    
	    LOG.info("本地文件路径:"+latestFile.toString()+",准备上传到hdfs: "+p.toString());
	    if(!fs.exists(p)){
	    	long aaa = System.currentTimeMillis(); 
	    	fs.copyFromLocalFile(new Path(latestFile.toString()), p);
	    	fs.close(); 
	    	fs = null; 
	    	long bbb = System.currentTimeMillis();
	    	LOG.info("文件上传到hdfs成功!耗时：" + (bbb - aaa) / 1000 + "s");
	    }else{
	    	LOG.info("文件已存在，不作处理");
	    }
		status=true;
		
		return status;
	}
	
	/**
	 * 解析轨交Day数据文件
	 * @param address
	 * @param port
	 * @param latestFile
	 * @return 
	 * @throws IOException 
	 * @Author Yangbo
	 */
	public static boolean parseLatestDayFile(String address, int port,
			FileObject latestFile) throws IOException {
		boolean status = false;
		
//		将vfs FileObject转换成java File
		File file=null;
		try {
			file = latestFile.getFileSystem().replicateFile(latestFile,
					new FileSelector() {
						@Override
						public boolean traverseDescendents(
								FileSelectInfo fileSelectInfo) throws Exception {
							return true;
						}
						@Override
						public boolean includeFile(FileSelectInfo fileSelectInfo)
								throws Exception {
							return true;
						}
					});
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
		LOG.info("转换后路径为："+file.getParentFile().getAbsolutePath()+",转换后文件名为:"+file.getName());
		String realPath=file.getParentFile().getAbsolutePath();
		String baseName=file.getName();
		List<String> files=getFiles(realPath,baseName);
		String path2="/tmp/gj/";
		mergeFilesThenUpload(realPath+Path.SEPARATOR,baseName.substring(0,baseName.length()-2),files,path2);
	    status = true;
		return status;
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
	

	/**
	 * 从公安交换平台的ftp 进入我们ftp 服务器
	 * 
	 * @param latestLines
	 * @param ftpAdress
	 * @return
	 */
	public boolean copyMetro2DSFtp(List<String> latestLines, String ftpAdress) {
		return false;

	}

	//
	public static int gjMD5File2(String address, int port,
			FileObject listenFile, int RowNum) throws IOException {
		String fileName = listenFile.getName().getBaseName();
		if (fileName.contains("MAC_DAY_")) {
			int tempRowNum = 0;
			String str;
			BufferedReader macReader = new BufferedReader(
					new InputStreamReader(listenFile.getContent()
							.getInputStream()));
			while ((str = macReader.readLine()) != null) {
				if (str.contains("FLUXNEW_SEG_")) {
					tempRowNum++;
					if (tempRowNum > RowNum) {
						String filename = str.split(",")[1];
						System.out.println(filename);
						// 写给socket
						InputStreamReader reader = new InputStreamReader(
								listenFile.getContent().getInputStream());
						BufferedReader bufferedReader = new BufferedReader(
								reader);
						MinaClient client = new MinaClient(address, port);
						if (client.connect()) {
							while (bufferedReader.ready()) {
								client.send(bufferedReader.readLine());
							}
						}
						client.close();

					}
					RowNum = tempRowNum;
				}
			}
			System.out.println("RowNum is :" + RowNum);
		}
		return RowNum;
	}

	// dzwl
	public static void dzwlFileToSocket(FileChangeEvent arg0, String address,
			int port) throws IOException {
		System.out.println("fileCreated:" + arg0.getFile().getName());
		FileObject createdFile = arg0.getFile();
		String fileName = createdFile.getName().getBaseName();
		System.out.println(fileName);

		String regEx = "^pcs_[0-9]{12}.csv$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(fileName);
		System.out.println(matcher.matches());

		if (matcher.matches()) {
			InputStreamReader reader = new InputStreamReader(arg0.getFile()
					.getContent().getInputStream(), "GBK");
			BufferedReader bufferedReader = new BufferedReader(reader);
			MinaClient client = new MinaClient(address, port);
			if (client.connect()) {
				while (bufferedReader.ready()) {
					String str = bufferedReader.readLine();
					System.out.println(str);
					client.send(str);
				}
			}
			client.close();
		}
	}

	// video
	public static void videoFileToSocket(FileObject file, String address,
			int port)  {
		
		
//		String regEx = "^[0-9]{14}.txt$";
//		Pattern pattern = Pattern.compile(regEx);
//		Matcher matcher = pattern.matcher(fileName);
//		System.out.println(matcher.matches());

//		if (matcher.matches()) {
		
		MinaClient client = null;
		InputStreamReader reader = null;
		BufferedReader bufferedReader = null;
		String line = null;
		
		try{
			reader = new InputStreamReader(file
					.getContent().getInputStream(), "GBK");
			
			bufferedReader = new BufferedReader(reader);
			
			client = new MinaClient(address, port);
			
			LOG.info("开始解析文件:" + file);
			
			if (client.connect()) {
				
				while ((line = bufferedReader.readLine()) != null ) {
					
					client.send(line);
				}
				
				LOG.info("文件 " + file + " 解析成功." );
			}else{
				LOG.error("MinaClient 连接失败. ");
			}
			
		}catch( IOException e ){
			LOG.error("解析文件" + file + "失败.", e);
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
				LOG.error("缓冲流关闭失败。。。");
			}

		}
	}

	// wifi
	public static void wifiFileToSocket(FileChangeEvent arg0, String address,
			int port) throws IOException {
		System.out.println("fileCreated:" + arg0.getFile().getName());
		FileObject createdFile = arg0.getFile();
		String fileName = createdFile.getName().getBaseName();

		String regEx = "^(LOCATION_WIFIMac_)[0-9]{17}NO.[0-9]{7}.zip$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(fileName);
		System.out.println(matcher.matches());

		if (matcher.matches()) {
			File file = new File("D://dsftp/" + fileName);
			System.out.println("filepath:" + file.getAbsolutePath());
			// 读取zip文件中的数据（zip文件中记录的条数、时间、id）
			ParseWifiData parseWifiData = new ParseWifiData(file);
			// 获取zip文件中记录的条数
			int count = parseWifiData.getCount();
			// 获取zip文件中区域id（设备id）
			List<String> list = parseWifiData.parseData();
			// 每个文件中的区域id相同，只需要取其中一条记录即可（？？？？？？？）
			String id = list.get(0).substring(0, 8);
			// 获取zip文件的时间
			Pattern p = Pattern.compile("[0-9]+");
			Matcher m = p.matcher(fileName);
			m.find();
			String time = m.group().substring(0, 12);

			// 将时间、区域id和记录条数拼接成一个字符串，中间用逗号隔开
			String datasent = "";

			datasent = time.concat(",") + count;
			datasent = datasent.concat(",").concat(id);

			// InputStreamReader reader = new
			// InputStreamReader(arg0.getFile().getContent().getInputStream(),"GBK");
			// BufferedReader bufferedReader = new BufferedReader(reader);
			MinaClient client = new MinaClient(address, port);
			if (client.connect()) {
				//
				// while (bufferedReader.ready()) {
				// String str=bufferedReader.readLine();
				// System.out.println(str);
				client.send(datasent);
				// }
			}
			client.close();
		}
	}

	/**
	 * description:
	 * 
	 * 获取到WIFi 文件创建， 写入到Sockect里面
	 * 
	 * @param changeEvent
	 * @param address
	 * @param port
	 *            void 2015-9-17 下午4:21:44 BY YuXiaoLin
	 */
	public static void wifiDataToSocket(FileChangeEvent changeEvent,
			String address, int port) {

		FileObject fileObject = changeEvent.getFile();

		String fileName = changeEvent.getFile().getName().getBaseName();

		File file = null;

		try {
			file = fileObject.getFileSystem().replicateFile(fileObject,
					new FileSelector() {

						@Override
						public boolean traverseDescendents(
								FileSelectInfo fileSelectInfo) throws Exception {
							return true;
						}

						@Override
						public boolean includeFile(FileSelectInfo fileSelectInfo)
								throws Exception {
							return true;
						}
					});

		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (file != null) {
			// 读取zip文件中的数据（zip文件中记录的条数、时间、id）
			ParseWifiData parseWifiData = new ParseWifiData(file);

			// 获取zip文件中记录的条数
			int count = parseWifiData.getCount();

			// 获取zip文件中区域id（设备id）
			List<String> list = parseWifiData.parseData();

			// 每个文件中的区域id相同，只需要取其中一条记录即可（？？？？？？？）
			String id = list.get(0).substring(0, 8);

			// 获取zip文件的时间
			Pattern p = Pattern.compile("[0-9]+");

			Matcher m = p.matcher(fileName);

			m.find();

			String time = m.group().substring(0, 12);

			// 将时间、区域id和记录条数拼接成一个字符串，中间用逗号隔开
			String datasent = "";

			datasent = time.concat(",") + count;
			datasent = datasent.concat(",").concat(id);

			MinaClient client = new MinaClient(address, port);
			if (client.connect()) {
				client.send(datasent);
			}
			client.close();

		}

	}

	/**
	 * description:些数据到sokect服务中去
	 * 
	 * @param changeEvent
	 *            void 2015-8-26 下午3:26:05 by Yuxl
	 * @throws IOException
	 */
	public static void W2Scokect(FileChangeEvent changeEvent, String address,
			int port) throws IOException {

		InputStreamReader reader = new InputStreamReader(changeEvent.getFile()
				.getContent().getInputStream());

		BufferedReader bufferedReader = new BufferedReader(reader);

		MinaClient client = new MinaClient(address, port);
		if (client.connect()) {
			while (bufferedReader.ready()) {
				client.send(bufferedReader.readLine());
			}
		}
		client.close();
	}

	
	/**
	  * description:  将指定路径 转换成 FileObject
	  * @param path
	  * @return
	  * FileObject
	  * 2015-9-21 下午12:03:41
	  * by Lixc
	 */
	public static FileObject resolveFile(String path){
		
		FileObject file = null;
		try {
			file = fsManager.resolveFile(path);
		} catch (FileSystemException e) {
			LOG.error("解析文件时 , commons vfs 转换数据文件 ： " + path
					+ " 失败..", e);
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
				.write(("123456787-" + new Date(System.currentTimeMillis()) + "\n")
						.getBytes());
		Thread.sleep(3000);
		outputStream
				.write(("123456787-" + new Date(System.currentTimeMillis()) + "\n")
						.getBytes());
		outputStream.flush();

		System.out.println(content.getLastModifiedTime());
		content.setLastModifiedTime(1441762015574L);
		System.out.println(new Date(content.getLastModifiedTime()));

		// System.out.println("1");
		// FtpClientUtil.appendToFile("MAC_DAY_20150708test",
		// "20150708,FLUXNEW_SEG_20150708182500,322,4e83ed9d3af1b5af80f8cd1d11ad55f6");
		// // FtpClientUtil.appendToFile("MAC_DAY_20150708test",
		// "20150706,FLUXNEW_SEG_20150707235000,6,af8a3ccda5539171342ae7f8c1d2cef0");
		// // FtpClientUtil.appendToFile("MAC_DAY_20150708test",
		// "20150707,FLUXNEW_SEG_20150707235000,6,af8a3ccda5539171342ae7f8c1d2cef0");
		// System.out.println("2");
	}

}
