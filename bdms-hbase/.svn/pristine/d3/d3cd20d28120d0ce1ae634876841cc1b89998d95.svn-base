package com.bdms.hbase.extractor;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;


/**
  * Description:
  * 		HBase 查询结果Result的解析 接口。
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-1-30上午10:50:00            1.0            Created by LiXiaoCong
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public interface RowExtractor<T>  {

	
	/**
	  * description:
	  * @param result  单行 result 
	  * @param rowNum  
	  * @return
	  * @throws Exception
	  * T
	  * 2014-1-30 上午10:54:27
	  * by Lixc
	 */
	T extractRowData(Result result, int rowNum) throws IOException;
	
}