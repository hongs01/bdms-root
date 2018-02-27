/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-24 上午10:08:42
 */
package com.bdms.sqoop.exception;

/** 
 * @author 李晓聪
 * @date 2014-12-24 上午10:08:42
 * @Description:  TODO
 */
public class CreateException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public CreateException() {
		this("创建connection发生异常。");
	}
	
	public CreateException(String msg) {
		super(msg);
	}

}
