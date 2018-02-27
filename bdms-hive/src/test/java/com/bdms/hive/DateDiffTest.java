/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-12-16 下午1:56:51
 */
package com.bdms.hive;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.bdms.hive.exceptions.HiveDateException;
import com.bdms.hive.exceptions.HiveStringException;

/** 
 * @author 李晓聪
 * @date 2014-12-16 下午1:56:51
 * @Description:  TODO
 */
public class DateDiffTest {
	
	@Test
	public void testAll() throws HiveStringException, ParseException, HiveDateException{
		
		DateDiff dd = new DateDiff();
		
		Assert.assertEquals(dd.evaluate("2011-12-12 02:30:30", "2010-05-01 00:30:30", "year"), 1);
		Assert.assertEquals(dd.evaluate("2011-12-12 02:30:30", "2011-11", "month"), 1);
		Assert.assertEquals(dd.evaluate("2011-12-02 02:30:30", "2010-12-01 12:30:30", "day"), 366);
		Assert.assertEquals(dd.evaluate("2011-12-01 02:30:30", "2011-12-01 01:30:30", "hour"), 1);
		Assert.assertEquals(dd.evaluate("2011-12-01 02:30:30", "2011-12-01 02:00:00", "minute"), 30);
		Assert.assertEquals(dd.evaluate("2011-12-01 02:30:30", "2011-12-01 02:00:00", "second"), 30*60+30);
	}

}
