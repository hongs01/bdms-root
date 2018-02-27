/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-24 上午9:16:04
 */
package com.bdms.sqoop.param;

import com.bdms.sqoop.ds_enum.OutputFormatType;
import com.bdms.sqoop.ds_enum.StorageType;

/** 
 * @author 李晓聪
 * @date 2014-12-24 上午9:16:04
 * @Description:  TODO
 */
public class ClusterParam {
	
	private StorageType storageType;
	private OutputFormatType outputFormat;
	private String outputDirectory;
	private int extractors;  //map数据
	private int loaders; //reduce数
	
	
	
	public ClusterParam() {
		super();
	}
	
	public ClusterParam(StorageType storageType, OutputFormatType outputFormat,
			String outputDirectory, int extractors, int loaders) {
		super();
		this.storageType = storageType;
		this.outputFormat = outputFormat;
		this.outputDirectory = outputDirectory;
		this.extractors = extractors;
		this.loaders = loaders;
	}
	public StorageType getStorageType() {
		return storageType;
	}
	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}
	public OutputFormatType getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(OutputFormatType outputFormat) {
		this.outputFormat = outputFormat;
	}
	public String getOutputDirectory() {
		return outputDirectory;
	}
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	public int getExtractors() {
		return extractors;
	}
	public void setExtractors(int extractors) {
		this.extractors = extractors;
	}
	public int getLoaders() {
		return loaders;
	}
	public void setLoaders(int loaders) {
		this.loaders = loaders;
	}
	
	
}
