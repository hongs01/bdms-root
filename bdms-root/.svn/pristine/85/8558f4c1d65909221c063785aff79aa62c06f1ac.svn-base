package com.bdms.dams.weight.service;

import java.util.List;

/**
  * Description:
  * 		
  * 计分功能中计算每个二级子项得分类
  * 
  * History：
  * =============================================================
  * Date                      Version        Memo
  * 2014-3-5下午3:41:29            1.0            Created by Chenfeng
  * 
  * =============================================================
  * 
  * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class Calculate {

	/**
	  * description:		计算得分
	  * @param x			实时值(实时进站人数)
	  * @param boundary		参数列表，该列表为计分的临界值的集合
	  * @return				最后得分
	  * double
	  * 2014-3-5 下午3:42:59
	  * by Chenfeng
	 */
	public double getCalculate(int x, List<Integer> boundary) {

		double p = 0;
		
		double y1 = boundary.get(0);
		
		double y2 = boundary.get(1);
		
		double y3 = boundary.get(2);
		
		if((y1>0)&&(y1<y2)&&(y2<y3)){
			
			if (x < y1) {
				
				p = 60 * (x / y1);
				
			} else if ((y1 <= x) && (x < y2)) {
				
				p = 60 + (x - y1) * (80 - 60) / (y2 - y1);
				
			} else if ((y2 <= x) && (x < y3)) {
				
				p = 80 + (x - y2) * (90 - 80) / (y3 - y2);
				
			} else if ((y3 <= x) && (x < 1.2 * y3)) {
				
				p = 90 + (x - y3) * (100 - 90) / (1.2 * y3 - y3);
				
			} else {
				
				p = 100;
			}
			}else{
				
				p = 0;
		}
		return p;
	}
}
