/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-5 下午1:52:20
 */
package com.bdms.hive.exceptions;

/** 
 * @author 李晓聪
 * @date 2014-12-5 下午1:52:20
 * @Description:  TODO
 */
public class HiveStringException extends Exception {

	private static final long serialVersionUID = 1L;

	public HiveStringException(String message, Throwable cause) {
		super(message, cause);
	}
	public HiveStringException(String message) {
		super(message);
	}
	public HiveStringException() {
		super();
	}

}
