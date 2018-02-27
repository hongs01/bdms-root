/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-22 下午7:53:51
 */
package com.bdms.sqoop.exception;

/** 
 * @author 李晓聪
 * @date 2014-12-22 下午7:53:51
 * @Description:  TODO
 */
public class NullException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public NullException() {
		this("空值异常。");
	}

	public NullException(String msg){
		super(msg);
	}
}
