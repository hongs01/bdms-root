package com.bdms.hbase.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionSortUtil {
	
	/**
	 * description:对List<Map<String,String>>按照Map中的某个value进行排序；
	 * @param odData
	 * @return
	 * List<Map<String,String>>
	 * 2015-8-6 上午10:22:16
	 * by YangBo
	 */
	public static List<Map<String,String>> listMapSort(List<Map<String,String>> odData,final String sortStr){
		Collections.sort(odData,new Comparator<Map<String,String>>() {
	           public int compare(Map<String, String> o1,Map<String, String> o2) {
	            //o1，o2是list中的Map，可以在其内取得值，按其排序，s1和s2是排序字段值
	               Integer s1 = Integer.parseInt(o1.get(sortStr));
	               Integer s2 = Integer.parseInt(o2.get(sortStr));
	            if(s1>s2) {
	             return -1;
	            }else {
	             return 1;
	            }
	           }
	          });
		return odData;
	}
	
	public static void main(String[] args){
		System.out.println("start");
		List<Map<String,String>> odData=new ArrayList<Map<String,String>>();
		for(int i=0;i<10;i++){
			Map<String,String> map=new HashMap<String,String>();
			map.put("a",Math.getExponent(Math.random()*100)+"");
			System.out.println(map);
			odData.add(map);
		}
		
		
		System.out.println(listMapSort(odData,"a").size());
		for(Map<String, String> maptemp:odData){
			System.out.println(maptemp);
		}
		System.out.println("start");
	}
	
}
