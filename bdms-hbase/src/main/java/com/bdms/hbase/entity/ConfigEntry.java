package com.bdms.hbase.entity;

public class ConfigEntry {

	private String table_name ;
	private String family1_name;
	private String family2_name;
	private String row_key;
	private String zkquorum;
	private String clientPort;
	private String rootdir;
	
	
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getFamily1_name() {
		return family1_name;
	}
	public void setFamily1_name(String family1_name) {
		this.family1_name = family1_name;
	}
	public String getFamily2_name() {
		return family2_name;
	}
	public void setFamily2_name(String family2_name) {
		this.family2_name = family2_name;
	}
	public String getRow_key() {
		return row_key;
	}
	public void setRow_key(String row_key) {
		this.row_key = row_key;
	}
	public String getZkquorum() {
		return zkquorum;
	}
	public void setZkquorum(String zkquorum) {
		this.zkquorum = zkquorum;
	}
	public String getClientPort() {
		return clientPort;
	}
	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}
	public String getRootdir() {
		return rootdir;
	}
	public void setRootdir(String rootdir) {
		this.rootdir = rootdir;
	}
	@Override
	public String toString() {
		return "ConfigEntry [table_name=" + table_name + ", family1_name="
				+ family1_name + ", family2_name=" + family2_name
				+ ", row_key=" + row_key + ", zkquorum=" + zkquorum
				+ ", clientPort=" + clientPort + ", rootdir=" + rootdir + "]";
	}
	
	
}
