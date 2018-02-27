package com.bdms.common.collections;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.BagUtils;
import org.apache.commons.collections.bag.HashBag;

public class CollectionsUtils {

	public static void BagUsage(){
		//创建数据
		Book book1=new Book("Hadoop","001",25.5);
		Book book2=new Book("JSP","002",28.8);
		Book book3=new Book("C#程序设计","003",35.6);
		
		//创建一个包
		Bag bag=BagUtils.typedBag(new HashBag(), Book.class);
		bag.add(book1,100);  //添加100本book1到包里面
		bag.add(book2,200);  //添加200本book2到包里面
		bag.add(book3,300);  //添加300本book3到包里面
		
		//对包进行计算
		double price1=book1.getPrice();
		double price2=book2.getPrice();
		double price3=book3.getPrice();
		 
		//显示结果
		System.out.println("the price of "+book1.getName()+" is "+price1);
		System.out.println("the price of "+book2.getName()+" is "+price2);
		System.out.println("the price of "+book3.getName()+" is "+price3);
	}
	
	public static void main(String[] args){
		BagUsage();
	}
}
