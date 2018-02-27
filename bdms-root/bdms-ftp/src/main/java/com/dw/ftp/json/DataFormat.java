package com.dw.ftp.json;

public class DataFormat implements java.io.Serializable{

	private String userId;			//用户id
	private String imei;			//设备标识
	private String ip;				//用户登录IP地址
	private String longitude;		//经度
	private String latitude;		//纬度
	private String loginLocation;	//登陆地点
	private String hsOsVersion;		//华商云版本
	private String brand;			//手机品牌
	private String resolution;		//分辨率
	private String osType;			//操作系类型统
	private String osVersion;		//操作系版本
	private String currentPage;		//当前页面
	private String enterTime;		//进入页面的时间
	private String leaveTime;		//离开页面的时间
	private String contentId;		//内容Id
	private String contentType;		//内容类型
	private String operationType;	//操作类型
	private String status;			//操作状态
	private String log;				//操作日志
	
	public DataFormat(){}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}

	public String getHsOsVersion() {
		return hsOsVersion;
	}

	public void setHsOsVersion(String hsOsVersion) {
		this.hsOsVersion = hsOsVersion;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

}
