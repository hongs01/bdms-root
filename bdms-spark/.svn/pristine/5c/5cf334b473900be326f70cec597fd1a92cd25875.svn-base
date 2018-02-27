package com.bdms.spark.util;

import java.io.Serializable;

public class SortNumberKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 0 代表前面补充0      
    // n 代表长度为 n      
    // d 代表参数为正数型    
	private String format = "%0nd";
	
	public SortNumberKey(int number_length){
		
		format = format.replace("n", String.valueOf(number_length));      
	}

	
	public String getKey(int key){
		
		return  String.format(format, key);
	
	}
	
}
