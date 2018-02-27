/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2015-3-4 上午10:13:58
 */
package com.bdms.sqoop.ds_enum;

/** 
 * @author 李晓聪
 * @date 2015-3-4 上午10:13:58
 * @Description:  TODO
 */
public enum OutputFormatType {

	TEXT_FILE(){
		public String toString() {
			return "TEXT_FILE";
		}
	},SEQUENCE_FILE(){
		public String toString() {
			return "SEQUENCE_FILE";
		}
	}
}
