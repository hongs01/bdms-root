package com.bdms.common.lang;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

public class DateUtils {
	
	 public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
	 public static final long MILLIS_PER_SECOND = 1000;
	 public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	 public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	 public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
	 public final static int SEMI_MONTH = 1001;
	 public final static int RANGE_WEEK_SUNDAY = 1;
	 public final static int RANGE_WEEK_MONDAY = 2;
	 public final static int RANGE_WEEK_RELATIVE = 3;
	 public final static int RANGE_WEEK_CENTER = 4;	
	 public final static int RANGE_MONTH_SUNDAY = 5;
	 public final static int RANGE_MONTH_MONDAY = 6;
	 private final static int MODIFY_TRUNCATE = 0;
	 private final static int MODIFY_ROUND = 1;
	 private final static int MODIFY_CEILING= 2;
	 private static final int[][] fields = {
		  	              {Calendar.MILLISECOND},
		  	              {Calendar.SECOND},
		 	              {Calendar.MINUTE},
		  	              {Calendar.HOUR_OF_DAY, Calendar.HOUR},
		 	              {Calendar.DATE, Calendar.DAY_OF_MONTH, Calendar.AM_PM 
		 	             },
		 	              {Calendar.MONTH, DateUtils.SEMI_MONTH},
		 	              {Calendar.YEAR},
		               {Calendar.ERA}};
	 
  //日期格式转换
public Date str2Date(String date,String format) throws ParseException{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date parse = simpleDateFormat.parse(date);
		
		return parse;
    }

 
	 
	 public static boolean isSameDay(Date date1, Date date2) {
		  	          if (date1 == null || date2 == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar cal1 = Calendar.getInstance();
		  	          cal1.setTime(date1);
		 	          Calendar cal2 = Calendar.getInstance();
		  	          cal2.setTime(date2);
		  	          return isSameDay(cal1, cal2);
		  	      }


	 public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		  	          if (cal1 == null || cal2 == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
		  	                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		  	                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
		  	      }
	 
	 
	 public static boolean isSameInstant(Date date1, Date date2) {
		  	          if (date1 == null || date2 == null) {
		 	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          return date1.getTime() == date2.getTime();
		  	      }

	 
	 public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		  	          if (cal1 == null || cal2 == null) {
		 	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          return cal1.getTime().getTime() == cal2.getTime().getTime();
		  	      }
	 
	 public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
		  	          return parseDateWithLeniency(str, parsePatterns, true);
		  	      }
	 
	 public static Date parseDateStrictly(String str, String[] parsePatterns) throws ParseException {
		  	          return parseDateWithLeniency(str, parsePatterns, false);
		 	      }
	 
	 private static Date parseDateWithLeniency(String str, String[] parsePatterns,
			  	              boolean lenient) throws ParseException {
			  	          if (str == null || parsePatterns == null) {
			  	              throw new IllegalArgumentException("Date and Patterns must not be null");
			  	          }
			  	          
			  	          SimpleDateFormat parser = new SimpleDateFormat();
			  	          parser.setLenient(lenient);
			  	          ParsePosition pos = new ParsePosition(0);
			  	          for (int i = 0; i < parsePatterns.length; i++) {
			  	  
			  	              String pattern = parsePatterns[i];
			  	  
			  	              // LANG-530 - need to make sure 'ZZ' output doesn't get passed to SimpleDateFormat
			  	              if (parsePatterns[i].endsWith("ZZ")) {
			  	                  pattern = pattern.substring(0, pattern.length() - 1);
			  	              }
			  	              
			  	              parser.applyPattern(pattern);
			  	              pos.setIndex(0);
			  	  
			  	              String str2 = str;
			  	              // LANG-530 - need to make sure 'ZZ' output doesn't hit SimpleDateFormat as it will ParseException
			  	              if (parsePatterns[i].endsWith("ZZ")) {
			  	                  int signIdx  = indexOfSignChars(str2, 0);
			  	                  while (signIdx >=0) {
			  	                      str2 = reformatTimezone(str2, signIdx);
			  	                      signIdx = indexOfSignChars(str2, ++signIdx);
			  	                  }
			  	              }
			  	  
			  	              Date date = parser.parse(str2, pos);
			  	              if (date != null && pos.getIndex() == str2.length()) {
			  	                  return date;
			  	              }
			  	          }
			  	          throw new ParseException("Unable to parse the date: " + str, -1);
			  	      }

	 private static int indexOfSignChars(String str, int startPos) {
		  	          int idx = StringUtils.indexOf(str, '+', startPos);
		 	          if (idx < 0) {
		  	              idx = StringUtils.indexOf(str, '-', startPos);
		  	          }
		  	          return idx;
		  	      }



	 private static String reformatTimezone(String str, int signIdx) {
		  	          String str2 = str;
		  	          if (signIdx >= 0 &&
		  	              signIdx + 5 < str.length() &&
		  	              Character.isDigit(str.charAt(signIdx + 1)) &&
		  	              Character.isDigit(str.charAt(signIdx + 2)) &&
		  	              str.charAt(signIdx + 3) == ':' &&
		  	              Character.isDigit(str.charAt(signIdx + 4)) &&
		  	              Character.isDigit(str.charAt(signIdx + 5))) {
		  	              str2 = str.substring(0, signIdx + 3) + str.substring(signIdx + 4);
		  	          }
		  	          return str2;
		  	      }
		 	  

	 public static Date addYears(Date date, int amount) {
		  	          return add(date, Calendar.YEAR, amount);
		  	      }
	 
	 
	 public static Date addMonths(Date date, int amount) {
		  	          return add(date, Calendar.MONTH, amount);
		  	      }
	 
	 
	 public static Date addWeeks(Date date, int amount) {
		  	          return add(date, Calendar.WEEK_OF_YEAR, amount);
		 	      }



	 public static Date addDays(Date date, int amount) {
		  	          return add(date, Calendar.DAY_OF_MONTH, amount);
		  	      }
	 
	 
	 public static Date addHours(Date date, int amount) {
		  	          return add(date, Calendar.HOUR_OF_DAY, amount);
		  	      }

	 
	 public static Date addMinutes(Date date, int amount) {
		  	          return add(date, Calendar.MINUTE, amount);
		  	      }



	 public static Date addSeconds(Date date, int amount) {
		  	          return add(date, Calendar.SECOND, amount);
		  	      }

	 
	 public static Date addMilliseconds(Date date, int amount) {
		  	          return add(date, Calendar.MILLISECOND, amount);
		  	      }

	 
	 public static Date add(Date date, int calendarField, int amount) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar c = Calendar.getInstance();
		  	          c.setTime(date);
		  	          c.add(calendarField, amount);
		  	          return c.getTime();
		  	      }
	 
	 
	 public static Date setYears(Date date, int amount) {
		  	          return set(date, Calendar.YEAR, amount);
		  	      }

		  	  
	 public static Date setMonths(Date date, int amount) {
		  	          return set(date, Calendar.MONTH, amount);
		  	      }

	 
	 public static Date setDays(Date date, int amount) {
		  	          return set(date, Calendar.DAY_OF_MONTH, amount);
		 	      }

	 
	 public static Date setHours(Date date, int amount) {
		  	          return set(date, Calendar.HOUR_OF_DAY, amount);
		 	      }


	 
	 public static Date setMinutes(Date date, int amount) {
		  	          return set(date, Calendar.MINUTE, amount);
		  	      }

	 
	 public static Date setSeconds(Date date, int amount) {
		  	          return set(date, Calendar.SECOND, amount);
		  	      }

	 
	 public static Date setMilliseconds(Date date, int amount) {
		  	          return set(date, Calendar.MILLISECOND, amount);
		 	      }

	 
	 private static Date set(Date date, int calendarField, int amount) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          // getInstance() returns a new object, so this method is thread safe.
		  	          Calendar c = Calendar.getInstance();
		  	          c.setLenient(false);
		  	          c.setTime(date);
		  	          c.set(calendarField, amount);
		  	          return c.getTime();
		  	      }  

	 
	 public static Calendar toCalendar(Date date) {
		 	          Calendar c = Calendar.getInstance();
		  	          c.setTime(date);
		  	          return c;
		  	      }

	 
	 public static Date round(Date date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar gval = Calendar.getInstance();
		  	          gval.setTime(date);
		  	          modify(gval, field, MODIFY_ROUND);
		  	          return gval.getTime();
		  	      }

	 
	 public static Calendar round(Calendar date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar rounded = (Calendar) date.clone();
		  	          modify(rounded, field, MODIFY_ROUND);
		  	          return rounded;
		  	      }

	 
	 public static Date round(Object date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          if (date instanceof Date) {
		  	              return round((Date) date, field);
		  	          } else if (date instanceof Calendar) {
		  	              return round((Calendar) date, field).getTime();
		  	          } else {
		  	              throw new ClassCastException("Could not round " + date);
		  	          }
		  	      }

	 
	 public static Date truncate(Date date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar gval = Calendar.getInstance();
		  	          gval.setTime(date);
		  	          modify(gval, field, MODIFY_TRUNCATE);
		  	          return gval.getTime();
		  	      }

	 
	 public static Calendar truncate(Calendar date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar truncated = (Calendar) date.clone();
		  	          modify(truncated, field, MODIFY_TRUNCATE);
		  	          return truncated;
		  	      }

	 
	 public static Date truncate(Object date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          if (date instanceof Date) {
		  	              return truncate((Date) date, field);
		  	          } else if (date instanceof Calendar) {
		  	              return truncate((Calendar) date, field).getTime();
		  	          } else {
		  	              throw new ClassCastException("Could not truncate " + date);
		  	          }
		  	      }

	 
	 
	 public static Date ceiling(Date date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar gval = Calendar.getInstance();
		  	          gval.setTime(date);
		  	          modify(gval, field, MODIFY_CEILING);
		  	          return gval.getTime();
		  	      }

	 
	 
	 public static Calendar ceiling(Calendar date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar ceiled = (Calendar) date.clone();
		  	          modify(ceiled, field, MODIFY_CEILING);
		  	          return ceiled;
		  	      }

	 
	 public static Date ceiling(Object date, int field) {
		  	          if (date == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          if (date instanceof Date) {
		  	              return ceiling((Date) date, field);
		  	          } else if (date instanceof Calendar) {
		  	              return ceiling((Calendar) date, field).getTime();
		  	          } else {
		  	              throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
		  	          }
		  	      }

	 
	 private static void modify(Calendar val, int field, int modType) {
		  	          if (val.get(Calendar.YEAR) > 280000000) {
		  	              throw new ArithmeticException("Calendar value too large for accurate calculations");
		  	          }
		  	          
		  	          if (field == Calendar.MILLISECOND) {
		  	              return;
		  	          }
		  	  
		  	          // ----------------- Fix for LANG-59 ---------------------- START ---------------
		  	          // see http://issues.apache.org/jira/browse/LANG-59
		  	          //
		  	          // Manually truncate milliseconds, seconds and minutes, rather than using
		  	          // Calendar methods.
		  	  
		  	          Date date = val.getTime();
		  	          long time = date.getTime();
		 	          boolean done = false;
		  	  
		 	          // truncate milliseconds
		  	          int millisecs = val.get(Calendar.MILLISECOND);
		  	          if (MODIFY_TRUNCATE == modType || millisecs < 500) {
		  	              time = time - millisecs;
		  	          }
		  	          if (field == Calendar.SECOND) {
		  	              done = true;
		  	          }
		    
		  	          // truncate seconds
		  	          int seconds = val.get(Calendar.SECOND);
		  	          if (!done && (MODIFY_TRUNCATE == modType || seconds < 30)) {
		  	              time = time - (seconds * 1000L);
		  	          }
		  	          if (field == Calendar.MINUTE) {
		  	              done = true;
		  	          }
		  	  
		  	          // truncate minutes
		  	          int minutes = val.get(Calendar.MINUTE);
		  	          if (!done && (MODIFY_TRUNCATE == modType || minutes < 30)) {
		  	              time = time - (minutes * 60000L);
		  	          }
		  	  
		  	          // reset time
		  	          if (date.getTime() != time) {
		  	              date.setTime(time);
		  	              val.setTime(date);
		  	          }
		  	          // ----------------- Fix for LANG-59 ----------------------- END ----------------
		  	  
		  	          boolean roundUp = false;
		  	          for (int i = 0; i < fields.length; i++) {
		  	              for (int j = 0; j < fields[i].length; j++) {
		  	                  if (fields[i][j] == field) {
		  	                      //This is our field... we stop looping
		  	                      if (modType == MODIFY_CEILING || (modType == MODIFY_ROUND && roundUp)) {
		  	                          if (field == DateUtils.SEMI_MONTH) {
		  	                              //This is a special case that's hard to generalize
		  	                              //If the date is 1, we round up to 16, otherwise
		  	                              //  we subtract 15 days and add 1 month
		  	                              if (val.get(Calendar.DATE) == 1) {
		  	                                  val.add(Calendar.DATE, 15);
		  	                              } else {
		  	                                  val.add(Calendar.DATE, -15);
		  	                                  val.add(Calendar.MONTH, 1);
		  	                              }
		  	  // ----------------- Fix for LANG-440 ---------------------- START ---------------
		  	                          } else if (field == Calendar.AM_PM) {
		  	                              // This is a special case
		  	                              // If the time is 0, we round up to 12, otherwise
		  	                              //  we subtract 12 hours and add 1 day
		  	                              if (val.get(Calendar.HOUR_OF_DAY) == 0) {
		  	                                  val.add(Calendar.HOUR_OF_DAY, 12);
		  	                              } else {
		  	                                  val.add(Calendar.HOUR_OF_DAY, -12);
		  	                                  val.add(Calendar.DATE, 1);
		  	                              }
		  	  // ----------------- Fix for LANG-440 ---------------------- END ---------------
		  	                          } else {
		  	                              //We need at add one to this field since the
		  	                              //  last number causes us to round up
		  	                              val.add(fields[i][0], 1);
		  	                          }
		  	                      }
		  	                      return;
		  	                  }
		  	              }
		  	              //We have various fields that are not easy roundings
		  	              int offset = 0;
		  	              boolean offsetSet = false;
		  	              //These are special types of fields that require different rounding rules
		  	              switch (field) {
		  	                  case DateUtils.SEMI_MONTH:
		  	                      if (fields[i][0] == Calendar.DATE) {
		  	                          //If we're going to drop the DATE field's value,
		  	                          //  we want to do this our own way.
		  	                          //We need to subtrace 1 since the date has a minimum of 1
		  	                          offset = val.get(Calendar.DATE) - 1;
		  	                          //If we're above 15 days adjustment, that means we're in the
		  	                          //  bottom half of the month and should stay accordingly.
		  	                          if (offset >= 15) {
		  	                              offset -= 15;
		  	                          }
		  	                          //Record whether we're in the top or bottom half of that range
		 	                          roundUp = offset > 7;
		 	                          offsetSet = true;
		                        }
		  	                      break;
		  	                  case Calendar.AM_PM:
		  	                      if (fields[i][0] == Calendar.HOUR_OF_DAY) {
		  	                          //If we're going to drop the HOUR field's value,
		  	                          //  we want to do this our own way.
		  	                          if (offset >= 12) {
		  	                              offset -= 12;
		  	                          }
		  	                          roundUp = offset >= 6;
		  	                          offsetSet = true;
		  	                      }
		  	                      break;
		  	              }
		  	              if (!offsetSet) {
		  	                  int min = val.getActualMinimum(fields[i][0]);
		  	                  int max = val.getActualMaximum(fields[i][0]);
		  	                  //Calculate the offset from the minimum allowed value
		  	                  offset = val.get(fields[i][0]) - min;
		  	                  //Set roundUp if this is more than half way between the minimum and maximum
		  	                  roundUp = offset > ((max - min) / 2);
		  	              }
		  	              //We need to remove this field
		 	              if (offset != 0) {
		  	                  val.set(fields[i][0], val.get(fields[i][0]) - offset);
		  	              }
		  	          }
		  	          throw new IllegalArgumentException("The field " + field + " is not supported");
		  	  
		  	      }

	 
	 public static Iterator iterator(Date focus, int rangeStyle) {
		  	          if (focus == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar gval = Calendar.getInstance();
		  	          gval.setTime(focus);
		  	          return iterator(gval, rangeStyle);
		  	      }

	 
	 public static Iterator iterator(Calendar focus, int rangeStyle) {
		  	          if (focus == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          Calendar start = null;
		  	          Calendar end = null;
		  	          int startCutoff = Calendar.SUNDAY;
		  	          int endCutoff = Calendar.SATURDAY;
		  	          switch (rangeStyle) {
		  	              case RANGE_MONTH_SUNDAY:
		  	              case RANGE_MONTH_MONDAY:
		  	                  //Set start to the first of the month
		  	                  start = truncate(focus, Calendar.MONTH);
		  	                  //Set end to the last of the month
		  	                  end = (Calendar) start.clone();
		  	                  end.add(Calendar.MONTH, 1);
		  	                  end.add(Calendar.DATE, -1);
		  	                  //Loop start back to the previous sunday or monday
		  	                  if (rangeStyle == RANGE_MONTH_MONDAY) {
		  	                      startCutoff = Calendar.MONDAY;
		  	                      endCutoff = Calendar.SUNDAY;
		  	                  }
		  	                  break;
		  	              case RANGE_WEEK_SUNDAY:
		  	              case RANGE_WEEK_MONDAY:
		  	              case RANGE_WEEK_RELATIVE:
		  	              case RANGE_WEEK_CENTER:
		  	                  //Set start and end to the current date
		  	                  start = truncate(focus, Calendar.DATE);
		  	                  end = truncate(focus, Calendar.DATE);
		  	                  switch (rangeStyle) {
		  	                      case RANGE_WEEK_SUNDAY:
		  	                          //already set by default
		  	                          break;
		  	                      case RANGE_WEEK_MONDAY:
		  	                          startCutoff = Calendar.MONDAY;
		  	                          endCutoff = Calendar.SUNDAY;
		  	                          break;
		  	                      case RANGE_WEEK_RELATIVE:
		  	                          startCutoff = focus.get(Calendar.DAY_OF_WEEK);
		  	                          endCutoff = startCutoff - 1;
		  	                          break;
		  	                      case RANGE_WEEK_CENTER:
		  	                          startCutoff = focus.get(Calendar.DAY_OF_WEEK) - 3;
		  	                          endCutoff = focus.get(Calendar.DAY_OF_WEEK) + 3;
		  	                          break;
		  	                  }
		                    break;
		  	              default:
		  	                  throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
		  	          }
		  	          if (startCutoff < Calendar.SUNDAY) {
		  	              startCutoff += 7;
		  	          }
		  	          if (startCutoff > Calendar.SATURDAY) {
		  	              startCutoff -= 7;
		  	          }
		  	          if (endCutoff < Calendar.SUNDAY) {
		                endCutoff += 7;
		  	          }
		  	          if (endCutoff > Calendar.SATURDAY) {
		  	              endCutoff -= 7;
		  	          }
		  	          while (start.get(Calendar.DAY_OF_WEEK) != startCutoff) {
		  	              start.add(Calendar.DATE, -1);
		  	          }
		  	          while (end.get(Calendar.DAY_OF_WEEK) != endCutoff) {
		  	              end.add(Calendar.DATE, 1);
		  	          }
		  	          return new DateIterator(start, end);
		  	      }

	 
	 public static Iterator iterator(Object focus, int rangeStyle) {
		  	          if (focus == null) {
		  	              throw new IllegalArgumentException("The date must not be null");
		  	          }
		  	          if (focus instanceof Date) {
		  	              return iterator((Date) focus, rangeStyle);
		  	          } else if (focus instanceof Calendar) {
		  	              return iterator((Calendar) focus, rangeStyle);
		  	          } else {
		  	              throw new ClassCastException("Could not iterate based on " + focus);
		  	          }
		  	      }

	 
	 public static long getFragmentInMilliseconds(Date date, int fragment) {
		  	          return getFragment(date, fragment, Calendar.MILLISECOND);    
		  	      }
	 
	 
	 public static long getFragmentInSeconds(Date date, int fragment) {
		  	          return getFragment(date, fragment, Calendar.SECOND);
		  	      }


	 
	 public static long getFragmentInMinutes(Date date, int fragment) {
		  	          return getFragment(date, fragment, Calendar.MINUTE);
		  	      }

	 
	   public static long getFragmentInHours(Date date, int fragment) {
		    	          return getFragment(date, fragment, Calendar.HOUR_OF_DAY);
		    	      }

	   
	   public static long getFragmentInDays(Date date, int fragment) {
		    	          return getFragment(date, fragment, Calendar.DAY_OF_YEAR);
		    	      }

	   public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
		    	      return getFragment(calendar, fragment, Calendar.MILLISECOND);
		    	    }

	   
	   public static long getFragmentInSeconds(Calendar calendar, int fragment) {
		    	          return getFragment(calendar, fragment, Calendar.SECOND);
		    	      }

	   
	   
	   public static long getFragmentInMinutes(Calendar calendar, int fragment) {
		    	          return getFragment(calendar, fragment, Calendar.MINUTE);
		    	      }

	   
	   public static long getFragmentInHours(Calendar calendar, int fragment) {
		    	          return getFragment(calendar, fragment, Calendar.HOUR_OF_DAY);
		    	      }

	   
	   public static long getFragmentInDays(Calendar calendar, int fragment) {
		    	          return getFragment(calendar, fragment, Calendar.DAY_OF_YEAR);
		    	      }

	   
	   private static long getFragment(Date date, int fragment, int unit) {
		    	          if(date == null) {
		    	              throw  new IllegalArgumentException("The date must not be null");
		    	          }
		    	          Calendar calendar = Calendar.getInstance();
		    	          calendar.setTime(date);
		    	          return getFragment(calendar, fragment, unit);
		    	      }

	   
	   private static long getFragment(Calendar calendar, int fragment, int unit) {
		    	          if(calendar == null) {
		    	              throw  new IllegalArgumentException("The date must not be null"); 
		    	          }
		    	          long millisPerUnit = getMillisPerUnit(unit);
		    	          long result = 0;
		    	          
		    	          // Fragments bigger than a day require a breakdown to days
		    	          switch (fragment) {
		    	              case Calendar.YEAR:
		    	                  result += (calendar.get(Calendar.DAY_OF_YEAR) * MILLIS_PER_DAY) / millisPerUnit;
		    	                  break;
		    	              case Calendar.MONTH:
		    	                  result += (calendar.get(Calendar.DAY_OF_MONTH) * MILLIS_PER_DAY) / millisPerUnit;
		    	                  break;
		    	          }
		    	  
		    	          switch (fragment) {
		    	              // Number of days already calculated for these cases
		    	              case Calendar.YEAR:
		    	              case Calendar.MONTH:
		    	              
		    	              // The rest of the valid cases
		    	              case Calendar.DAY_OF_YEAR:
		    	              case Calendar.DATE:
		    	                  result += (calendar.get(Calendar.HOUR_OF_DAY) * MILLIS_PER_HOUR) / millisPerUnit;
		    	                  //$FALL-THROUGH$
		    	              case Calendar.HOUR_OF_DAY:
		    	                  result += (calendar.get(Calendar.MINUTE) * MILLIS_PER_MINUTE) / millisPerUnit;
		    	                  //$FALL-THROUGH$
		    	              case Calendar.MINUTE:
		    	                  result += (calendar.get(Calendar.SECOND) * MILLIS_PER_SECOND) / millisPerUnit;
		    	                  //$FALL-THROUGH$
		    	              case Calendar.SECOND:
		    	                  result += (calendar.get(Calendar.MILLISECOND) * 1) / millisPerUnit;
		    	                  break;
		    	              case Calendar.MILLISECOND: 
		    	                  break;//never useful
		    	              default: 
		    	                  throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
		    	          }
		    	          return result;
		    	      }

	   
	   public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
		    	          return truncatedCompareTo(cal1, cal2, field) == 0;
		    	      }

	   
	   public static boolean truncatedEquals(Date date1, Date date2, int field) {
		    	          return truncatedCompareTo(date1, date2, field) == 0;
		    	      }

	   
	   public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
		    	          Calendar truncatedCal1 = truncate(cal1, field);
		    	          Calendar truncatedCal2 = truncate(cal2, field);
		    	          return truncatedCal1.getTime().compareTo(truncatedCal2.getTime());
		    	      }

	   
	   public static int truncatedCompareTo(Date date1, Date date2, int field) {
		    	          Date truncatedDate1 = truncate(date1, field);
		    	          Date truncatedDate2 = truncate(date2, field);
		    	          return truncatedDate1.compareTo(truncatedDate2);
		    	      }
	   
	   
	   private static long getMillisPerUnit(int unit) {
		    	          long result = Long.MAX_VALUE;
		    	          switch (unit) {
		    	              case Calendar.DAY_OF_YEAR:
		    	              case Calendar.DATE:
		    	                  result = MILLIS_PER_DAY;
		    	                  break;
		    	              case Calendar.HOUR_OF_DAY:
		    	                  result = MILLIS_PER_HOUR;
		    	                  break;
		    	              case Calendar.MINUTE:
		    	                  result = MILLIS_PER_MINUTE;
		    	                  break;
		    	              case Calendar.SECOND:
		    	                  result = MILLIS_PER_SECOND;
		    	                  break;
		    	              case Calendar.MILLISECOND:
		    	                  result = 1;
		    	                  break;
		    	              default: throw new IllegalArgumentException("The unit " + unit + " cannot be represented is milleseconds");
		    	          }
		    	          return result;
		    	      }


	   static class DateIterator implements Iterator {
		    	          private final Calendar endFinal;
		    	          private final Calendar spot;
		    	          
		   
		    	          DateIterator(Calendar startFinal, Calendar endFinal) {
		    	              super();
		    	              this.endFinal = endFinal;
		    	              spot = startFinal;
		    	              spot.add(Calendar.DATE, -1);
		    	          }
		    	           public boolean hasNext() {
		    	        	   	              return spot.before(endFinal);
		    	        	   	          }
		    	        	   	  
		    	        	  
		    	        	   	          public Object next() {
		    	        	                 if (spot.equals(endFinal)) {
		    	        	   	                  throw new NoSuchElementException();
		    	        	 	              }
		    	        	   	              spot.add(Calendar.DATE, 1);
		    	        	                 return spot.clone();
		    	        	   	          }
		    	        	  
		    	        	   	          public void remove() {
		    	        	                 throw new UnsupportedOperationException();
		    	        	   	          }
		    	        	   	      }
 	   
} 




