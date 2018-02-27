package com.bdms.common.collections;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;

 

public class BufferUsage {
 
	public static void BufferUsage(){
		//创建数据
		Book book1=new Book("Hadoop","001",25.5);
		Book book2=new Book("JSP","002",28.8);
		Book book3=new Book("C#程序设计","003",35.6);
		Book book4=new Book("java","004",45.2);
		
		//创建一个Buffer
		Buffer buffer=BufferUtils.typedBuffer(new BoundedFifoBuffer(3), Book.class);
		buffer.add(book1);   
		buffer.add(book2);   
		buffer.add(book3);   
		Book removed=(Book) buffer.remove();
		System.out.println("Removed:"+removed);
		buffer.add(book4);
		
		//获取buffer中的数据
		for (int i = 0; i < 3; i++) {   
			System.out.println(buffer.get());   
			buffer.remove();   
			}    
	}
	
//	public static void main(String[] args){
//		BufferUsage();
//	}
	
}
