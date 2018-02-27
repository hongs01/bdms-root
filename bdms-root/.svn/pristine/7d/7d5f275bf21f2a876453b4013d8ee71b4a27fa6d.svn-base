package com.bdms.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
 * Description:
 * 		描述
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-1-27上午11:04:00            1.0            Created by HongShuai
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class StringUtils {
	public static final String EMPTY = "";
	public static final int INDEX_NOT_FOUND = -1;
	private static final int PAD_LIMIT = 8192;
	
	public StringUtils() {
		          super();
		      }
	
	

	//判断字符串是否为空
	 public static boolean isBlank(String str) {
         int strLen;
         if (str == null || (strLen = str.length()) == 0) {
        	  
              return true;
         }
         for (int i = 0; i < strLen; i++) {
             if ((Character.isWhitespace(str.charAt(i)) == false)) {
            	  
                  return false;
              }
          }
         return true;
      }
	 
	 
	 //字母由大写转换为小写
	 public static char upperCaseToLowerCase(char ch){
		    
		    char vch;
		    vch=(char)(ch+32);
		    return vch;
		    }
	 
	//字母由小写转换为大写
		 public static char LowerCaseToupperCase(char ch){
			    char vch;
			    vch=(char)(ch-32);
			    return vch;
			    }
		 
	// 将字符串按文字堆积的方式换行输出
		 private String s,str="";
		    private String[] strArray;
		    private static int i=0;
		    int j=0;
		    public StringUtils(String strChar){
		    this.s=strChar;
		    this.strArray=new String[s.length()];
		    }
		    public String getNewLine(){
		          if(i<s.length()){
		             strArray[i]=s.substring(i, i+1);
		             int m=strArray[i].charAt(0);
		             if(m>64&&m<91||m>96&&m<123||m>47&&m<58){  //判断是否为字母
		             str+=strArray[i];
		             j++;
		             }
		             else{
		             if(m==32){  //空格
		             str+="\n";
		             j=0;
		             }
		             else if(j>1&&m!=32){
		             str+="\n";
		             str+=strArray[i];
		             }
		             else{
		             str+=strArray[i]+"\n";
		             }
		             }
		             i=i+1;
		             getNewLine();
		          }
		          return str;
		    }
 	   	
		   	public static boolean isNotBlank(String str){
		   	    return !StringUtils.isBlank(str);
		   	    }
		   	
		   	
		   	// removes leading and trailing whitespace
		   	public static String clean(String str) {
		   		         return str == null ? EMPTY: str.trim();
		   		      }
		   	
		   	
		   	 public static String trim(String str) {
		   		           return str == null ? null : str.trim();
		   		     }
		   	 
		   	 
		   	 public static String trimToNull(String str) {
		   		         String ts = trim(str);
		   		           return isEmpty(ts) ? null : ts;
		   		        }
		   	 
		   	 public static String trimToEmpty(String str) {
		            return str == null ? EMPTY : str.trim();
		         }
		        

		   	 public static String strip(String str) {
		   		         return strip(str, null);
		   	    }
		   	 
		   	 
		   	 public static String stripToNull(String str) {
		   		          if (str == null) {
		   		               return null;
		   		           }
		   		           str = strip(str, null);
		   		          return str.length() == 0 ? null : str;
		   		      }
		   	 
		   	 
		   	 public static String stripToEmpty(String str) {
		   		           return str == null ? EMPTY : strip(str, null);
		   		       }
		   	 
		   	 
		   	 public static String strip(String str, String stripChars) {
		   		          if (isEmpty(str)) {
		   		             return str;
		   		          }
		   		          str = stripStart(str, stripChars);
		   		          return stripEnd(str, stripChars);
		   		       }
		   	 
		   	 
		   	 public static String stripStart(String str, String stripChars) {
		   		           int strLen;
		   		           if (str == null || (strLen = str.length()) == 0) {
		   		              return str;
		   		          }
		   		           int start = 0;
		   		          if (stripChars == null) {
		   		              while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
		   		                  start++;
		   		              }
		   		           } else if (stripChars.length() == 0) {
		   		               return str;
		   		          } else {
		   		              while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND)) {
		   		                   start++;
		   		               }
		   		          }
		   		          return str.substring(start);
		   		       }
		   	 
		   	
		   	
		   	public static String stripEnd(String str, String stripChars) {
		   		           int end;
		   		           if (str == null || (end = str.length()) == 0) {
		   		               return str;
		   		           }
		   		   
		   		           if (stripChars == null) {
		   		               while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
		   		                   end--;
		   		               }
		   		           } else if (stripChars.length() == 0) {
		   		               return str;
		   		           } else {
		   		               while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND)) {
		   		                   end--;
		   		               }
		   		          }
		   		          return str.substring(0, end);
		   		      }
		   	
		   	
		   	
		   	public static String[] stripAll(String[] strs) {
		   		          return stripAll(strs, null);
		   		      }
		   	
		   	
		   	public static String[] stripAll(String[] strs, String stripChars) {
		   		           int strsLen;
		   		           if (strs == null || (strsLen = strs.length) == 0) {
		   		              return strs;
		   		           }
		   		           String[] newArr = new String[strsLen];
		   		          for (int i = 0; i < strsLen; i++) {
		   		               newArr[i] = strip(strs[i], stripChars);
		   		           }
		   		           return newArr;
		   		       }
		   	
		   	
		   	 public static boolean equals(String str1, String str2) {
		   		          return str1 == null ? str2 == null : str1.equals(str2);
		   		     }
		   	 
		   	 
		   	 public static boolean equalsIgnoreCase(String str1, String str2) {
		   		           return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
		   		       }
		   	 
		   	 
		   	 public static int indexOf(String str, char searchChar) {
		   		           if (isEmpty(str)) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.indexOf(searchChar);
		   		      }
		   	 
		   	 
		   	 public static int indexOf(String str, char searchChar, int startPos) {
		   		           if (isEmpty(str)) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.indexOf(searchChar, startPos);
		   		       }
		   	 
		   	 
		   	 public static int indexOf(String str, String searchStr) {
		   		         if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.indexOf(searchStr);
		   		       }
		   	 
		   	 
		   	 public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
		   		           return ordinalIndexOf(str, searchStr, ordinal, false);
		   		       }
		   	 
		   	 
		   	 private static int ordinalIndexOf(String str, String searchStr, int ordinal, boolean lastIndex) {
		   		           if (str == null || searchStr == null || ordinal <= 0) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           if (searchStr.length() == 0) {
		   		               return lastIndex ? str.length() : 0;
		   		           }
		   		           int found = 0;
		   		           int index = lastIndex ? str.length() : INDEX_NOT_FOUND;
		   		           do {
		   		               if(lastIndex) {
		   		                   index = str.lastIndexOf(searchStr, index - 1);
		   		               } else {
		   		                   index = str.indexOf(searchStr, index + 1);
		   		               }
		   		               if (index < 0) {
		   		                   return index;
		   		               }
		   		               found++;
		   		           } while (found < ordinal);
		   		           return index;
		   		       }
		   	 
		   	 
		   	 public static int indexOf(String str, String searchStr, int startPos) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           // JDK1.2/JDK1.3 have a bug, when startPos > str.length for "", hence
		   		           if (searchStr.length() == 0 && startPos >= str.length()) {
		   		               return str.length();
		   		           }
		   		          return str.indexOf(searchStr, startPos);
		   		       }
		   	 
		   	 
		   	 public static int indexOfIgnoreCase(String str, String searchStr) {
		   		           return indexOfIgnoreCase(str, searchStr, 0);
		   		      }
		   	 
		   	 
		   	 public static int indexOfIgnoreCase(String str, String searchStr, int startPos) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           if (startPos < 0) {
		   		               startPos = 0;
		   		           }
		   		           int endLimit = (str.length() - searchStr.length()) + 1;
		   		           if (startPos > endLimit) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           if (searchStr.length() == 0) {
		   		               return startPos;
		   		           }
		   		           for (int i = startPos; i < endLimit; i++) {
		   		               if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
		   		                   return i;
		   		               }
		   		           }
		   		           return INDEX_NOT_FOUND;
		   		       }
		   	 
		   	 
		   	 public static int lastIndexOf(String str, char searchChar) {
		   		           if (isEmpty(str)) {
		   		              return INDEX_NOT_FOUND;
		   		          }
		   		          return str.lastIndexOf(searchChar);
		   		      }
		   	 
		   	 
		   	 public static int lastIndexOf(String str, char searchChar, int startPos) {
		   		           if (isEmpty(str)) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.lastIndexOf(searchChar, startPos);
		   		       }
		   	 
		   	 
		   	 public static int lastIndexOf(String str, String searchStr) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.lastIndexOf(searchStr);
		   		       }
		   	 
		   	 
		   	 public static int lastOrdinalIndexOf(String str, String searchStr, int ordinal) {
		   		           return ordinalIndexOf(str, searchStr, ordinal, true);
		   		       }
		   	 
		   	 
		   	 public static int lastIndexOf(String str, String searchStr, int startPos) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return str.lastIndexOf(searchStr, startPos);
		   		       }
		   	 
		   	 
		   	 public static int lastIndexOfIgnoreCase(String str, String searchStr) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           return lastIndexOfIgnoreCase(str, searchStr, str.length());
		   		       }
		   	 
		   	 
		   	 
		   	 public static int lastIndexOfIgnoreCase(String str, String searchStr, int startPos) {
		   		           if (str == null || searchStr == null) {
		   		               return INDEX_NOT_FOUND;
		   		          }
		   		           if (startPos > (str.length() - searchStr.length())) {
		   		               startPos = str.length() - searchStr.length();
		   		           }
		   		           if (startPos < 0) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           if (searchStr.length() == 0) {
		   		               return startPos;
		   		           }
		   		   
		   		           for (int i = startPos; i >= 0; i--) {
		   		               if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
		   		                   return i;
		   		               }
		   		           }
		   		           return INDEX_NOT_FOUND;
		   		      }
		   	 
		   	 
		   	 public static boolean contains(String str, String searchStr) {
		   		           if (str == null || searchStr == null) {
		   		               return false;
		   		           }
		   		           return str.indexOf(searchStr) >= 0;
		   		       }
		   	 
		   	 
		   	 public static boolean containsIgnoreCase(String str, String searchStr) {
		   		           if (str == null || searchStr == null) {
		   		               return false;
		   		           }
		   		           int len = searchStr.length();
		   		           int max = str.length() - len;
		   		           for (int i = 0; i <= max; i++) {
		   		               if (str.regionMatches(true, i, searchStr, 0, len)) {
		   		                   return true;
		   		               }
		   		           }
		   		           return false;
		   		       }
		   	 
		    
		   	 
		   	 public static int indexOfAny(String str, String[] searchStrs) {
		   		           if ((str == null) || (searchStrs == null)) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		          int sz = searchStrs.length;
		   		   
		   		           // String's can't have a MAX_VALUEth index.
		   		           int ret = Integer.MAX_VALUE;
		   		   
		   		           int tmp = 0;
		   		           for (int i = 0; i < sz; i++) {
		   		               String search = searchStrs[i];
		   		               if (search == null) {
		   		                   continue;
		   		               }
		   		               tmp = str.indexOf(search);
		   		               if (tmp == INDEX_NOT_FOUND) {
		   		                   continue;
		   		               }
		   		   
		   		               if (tmp < ret) {
		   		                   ret = tmp;
		   		               }
		   		           }
		   		   
		   		          return (ret == Integer.MAX_VALUE) ? INDEX_NOT_FOUND : ret;
		   		       }
		   	 
		   	 
		   	 public static int lastIndexOfAny(String str, String[] searchStrs) {
		   		           if ((str == null) || (searchStrs == null)) {
		   		               return INDEX_NOT_FOUND;
		   		           }
		   		           int sz = searchStrs.length;
		   		           int ret = INDEX_NOT_FOUND;
		   		           int tmp = 0;
		   		           for (int i = 0; i < sz; i++) {
		   		               String search = searchStrs[i];
		   		               if (search == null) {
		   		                   continue;
		   		               }
		   		               tmp = str.lastIndexOf(search);
		   		               if (tmp > ret) {
		   		                   ret = tmp;
		   		               }
		   		           }
		   		           return ret;
		   		       }
		   	 
		   	 
		   	 public static String substring(String str, int start) {
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		   
		   		           // handle negatives, which means last n characters
		   		           if (start < 0) {
		   		               start = str.length() + start; // remember start is negative
		   		           }
		   		  
		   		           if (start < 0) {
		   		               start = 0;
		   		           }
		   		           if (start > str.length()) {
		   		               return EMPTY;
		   		           }
		   		   
		   		           return str.substring(start);
		   		       }
		   	 
		   	 
		   	 public static String substring(String str, int start, int end) {
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		   
		   		           // handle negatives
		   		           if (end < 0) {
		   		               end = str.length() + end; // remember end is negative
		   		           }
		   		           if (start < 0) {
		   		               start = str.length() + start; // remember start is negative
		   		           }
		   		   
		   		           // check length next
		   		           if (end > str.length()) {
		   		               end = str.length();
		   		           }
		   		   
		   		           // if start is greater than end, return ""
		   		           if (start > end) {
		   		               return EMPTY;
		   		           }
		   		   
		   		           if (start < 0) {
		   		               start = 0;
		   		          }
		   		           if (end < 0) {
		   		               end = 0;
		   		           }
		   		   
		   		           return str.substring(start, end);
		   		       }
		   	 
		   	 
		   	 public static String left(String str, int len) {
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		           if (len < 0) {
		   		               return EMPTY;
		   		           }
		   		           if (str.length() <= len) {
		   		               return str;
		   		           }
		   		           return str.substring(0, len);
		   		       }
		   	 
		   	 
		   	 public static String right(String str, int len) {
		   		           if (str == null) {
		   		               return null;
		   		          }
		   		          if (len < 0) {
		   		               return EMPTY;
		   		           }
		   		           if (str.length() <= len) {
		   		               return str;
		   		           }
		   		           return str.substring(str.length() - len);
		   		       }
		   	 
		   	 
		   	 public static String mid(String str, int pos, int len) {
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		           if (len < 0 || pos > str.length()) {
		   		               return EMPTY;
		   		           }
		   		           if (pos < 0) {
		   		               pos = 0;
		   		           }
		   		           if (str.length() <= (pos + len)) {
		   		               return str.substring(pos);
		   		           }
		   		           return str.substring(pos, pos + len);
		   		       }
		   	 
		   	 public static String substringBefore(String str, String separator) {
		   		           if (isEmpty(str) || separator == null) {
		   		               return str;
		   		           }
		   		           if (separator.length() == 0) {
		   		               return EMPTY;
		   		           }
		   		           int pos = str.indexOf(separator);
		   		           if (pos == INDEX_NOT_FOUND) {
		   		               return str;
		   		           }
		   		           return str.substring(0, pos);
		   		       }
		   	 
		   	 
		   	 public static String substringAfter(String str, String separator) {
		   		           if (isEmpty(str)) {
		   		               return str;
		   		           }
		   		           if (separator == null) {
		   		               return EMPTY;
		   		           }
		   		           int pos = str.indexOf(separator);
		   		           if (pos == INDEX_NOT_FOUND) {
		   		               return EMPTY;
		   		           }
		   		           return str.substring(pos + separator.length());
		   		       }
		   	 
		   	 
		   	 public static String substringBeforeLast(String str, String separator) {
		   		          if (isEmpty(str) || isEmpty(separator)) {
		   		               return str;
		   		           }
		   		          int pos = str.lastIndexOf(separator);
		   		         if (pos == INDEX_NOT_FOUND) {
		   		               return str;
		   		           }
		   		           return str.substring(0, pos);
		   		       }
		   	 
		   	 
		   	 public static String substringAfterLast(String str, String separator) {
		   		           if (isEmpty(str)) {
		   		               return str;
		   		           }
		   		           if (isEmpty(separator)) {
		   		               return EMPTY;
		   		           }
		   		           int pos = str.lastIndexOf(separator);
		   		           if (pos == INDEX_NOT_FOUND || pos == (str.length() - separator.length())) {
		   		               return EMPTY;
		   		           }
		   		          return str.substring(pos + separator.length());
		   		       }
		   	 
		   	 
		   	 
		   	 public static String substringBetween(String str, String tag) {
		   		           return substringBetween(str, tag, tag);
		   		       }
		   	 
		   	 
		   	 
		   	 public static String substringBetween(String str, String open, String close) {
		   		           if (str == null || open == null || close == null) {
		   		              return null;
		   		           }
		   		           int start = str.indexOf(open);
		   		           if (start != INDEX_NOT_FOUND) {
		   		               int end = str.indexOf(close, start + open.length());
		   		               if (end != INDEX_NOT_FOUND) {
		   		                   return str.substring(start + open.length(), end);
		   		               }
		   		           }
		   		           return null;
		   		       }
		   	 
		   	 
		   	 
		   	  
		   	 
		   	 public static String getNestedString(String str, String tag) {
		   		           return substringBetween(str, tag, tag);
		   		       }
		   	 
		   	 
		   	 public static String getNestedString(String str, String open, String close) {
		   		          return substringBetween(str, open, close);
		   		       }
		   	 
		   	 
		   	 public static String[] split(String str) {
		   		           return split(str, null, -1);
		   		       }
		   	 
		   	 
		   	 public static String[] split(String str, char separatorChar) {
		   		           return splitWorker(str, separatorChar, false);
		   		       }
		   	 
		   	 
		   	 public static String[] split(String str, String separatorChars) {
		   		           return splitWorker(str, separatorChars, -1, false);
		   		       }
		   	 
		   	 
		   	 
		   	 public static String[] split(String str, String separatorChars, int max) {
		   		           return splitWorker(str, separatorChars, max, false);
		   		       }
		   	 
		   	 public static String[] splitByWholeSeparator(String str, String separator) {
		   		           return splitByWholeSeparatorWorker( str, separator, -1, false ) ;
		   		       }
		   	 
		   	 
		   	 public static String[] splitByWholeSeparator( String str, String separator, int max ) {
		   		           return splitByWholeSeparatorWorker(str, separator, max, false);
		   		       }
		   	 
		   	 
		   	 public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
		   		           return splitByWholeSeparatorWorker(str, separator, -1, true);
		   		       }
		   	 
		   	 
		   	 
		   	 public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
		   		         return splitByWholeSeparatorWorker(str, separator, max, true);
		   		       }
		   	 
		   	 
		   	 private static String[] splitByWholeSeparatorWorker(String str, String separator, int max, 
		   			                                                           boolean preserveAllTokens) 
		   			       {
		   			           if (str == null) {
		   			               return null;
		   			           }
		   			  
		   			           int len = str.length();
		   			   
		   			           
		   			   
		   			           if ((separator == null) || (EMPTY.equals(separator))) {
		   			               // Split on whitespace.
		   			               return splitWorker(str, null, max, preserveAllTokens);
		   			           }
		   			   
		   			           int separatorLength = separator.length();
		   			   
		   			           ArrayList substrings = new ArrayList();
		   			           int numberOfSubstrings = 0;
		   			           int beg = 0;
		   			           int end = 0;
		   			           while (end < len) {
		   			               end = str.indexOf(separator, beg);
		   			   
		   			               if (end > -1) {
		   			                   if (end > beg) {
		   			                       numberOfSubstrings += 1;
		   			   
		   			                       if (numberOfSubstrings == max) {
		   			                           end = len;
		   			                           substrings.add(str.substring(beg));
		   			                       } else {
		   			                           // The following is OK, because String.substring( beg, end ) excludes
		   			                           // the character at the position 'end'.
		   			                           substrings.add(str.substring(beg, end));
		   			   
		   			                           // Set the starting point for the next search.
		   			                           // The following is equivalent to beg = end + (separatorLength - 1) + 1,
		   			                           // which is the right calculation:
		   			                           beg = end + separatorLength;
		   			                       }
		   			                   } else {
		   			                       // We found a consecutive occurrence of the separator, so skip it.
		   			                       if (preserveAllTokens) {
		   			                           numberOfSubstrings += 1;
		   			                           if (numberOfSubstrings == max) {
		   			                               end = len;
		   			                               substrings.add(str.substring(beg));
		   			                           } else {
		   			                               substrings.add(EMPTY);
		   			                           }
		   			                       }
		   			                       beg = end + separatorLength;
		   			                   }
		   			               } else {
		   			                   // String.substring( beg ) goes from 'beg' to the end of the String.
		   			                   substrings.add(str.substring(beg));
		   			                   end = len;
		   			               }
		   			           }
		   			   
		   			           return (String[]) substrings.toArray(new String[substrings.size()]);
		   			       }
		   	 
		   	 
		   	 public static String[] splitPreserveAllTokens(String str) {
		   		            return splitWorker(str, null, -1, true);
		   		       }
		   	 
		   	 
		   	 public static String[] splitPreserveAllTokens(String str, char separatorChar) {
		   		           return splitWorker(str, separatorChar, true);
		   		       }
		   	 
		   	 
		   	 private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
		   		           // Performance tuned for 2.0 (JDK1.4)
		   		   
		   		           if (str == null) {
		   		              return null;
		   		           }
		   		           int len = str.length();
		   		           
		   		           List list = new ArrayList();
		   		           int i = 0, start = 0;
		   		           boolean match = false;
		   		           boolean lastMatch = false;
		   		          while (i < len) {
		   		               if (str.charAt(i) == separatorChar) {
		   		                   if (match || preserveAllTokens) {
		   		                       list.add(str.substring(start, i));
		   		                       match = false;
		   		                       lastMatch = true;
		   		                   }
		   		                   start = ++i;
		   		                   continue;
		   		               }
		   		               lastMatch = false;
		   		               match = true;
		   		              i++;
		   		           }
		   		           if (match || (preserveAllTokens && lastMatch)) {
		   		               list.add(str.substring(start, i));
		   		           }
		   		           return (String[]) list.toArray(new String[list.size()]);
		   		      }
		   	 
		   	 
		   	 
		   	 public static String[] splitPreserveAllTokens(String str, String separatorChars) {
		   		          return splitWorker(str, separatorChars, -1, true);
		   		       }
		   	 
		   	 private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
		   		           // Performance tuned for 2.0 (JDK1.4)
		   		           // Direct code is quicker than StringTokenizer.
		   		           // Also, StringTokenizer uses isSpace() not isWhitespace()
		   		   
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		           int len = str.length();
		   		           
		   		           List list = new ArrayList();
		   		           int sizePlus1 = 1;
		   		           int i = 0, start = 0;
		   		           boolean match = false;
		   		           boolean lastMatch = false;
		   		          if (separatorChars == null) {
		   		               // Null separator means use whitespace
		   		               while (i < len) {
		   		                   if (Character.isWhitespace(str.charAt(i))) {
		   		                       if (match || preserveAllTokens) {
		   		                          lastMatch = true;
		   		                           if (sizePlus1++ == max) {
		   		                               i = len;
		   		                               lastMatch = false;
		   		                           }
		   		                           list.add(str.substring(start, i));
		   		                           match = false;
		   		                       }
		   		                       start = ++i;
		   		                       continue;
		   		                   }
		   		                   lastMatch = false;
		   		                   match = true;
		   		                   i++;
		   		               }
		   		           } else if (separatorChars.length() == 1) {
		   		               // Optimise 1 character case
		   		               char sep = separatorChars.charAt(0);
		   		               while (i < len) {
		   		                   if (str.charAt(i) == sep) {
		   		                       if (match || preserveAllTokens) {
		   		                           lastMatch = true;
		   		                           if (sizePlus1++ == max) {
		   		                               i = len;
		   		                               lastMatch = false;
		   		                           }
		   		                           list.add(str.substring(start, i));
		   		                           match = false;
		   		                       }
		   		                       start = ++i;
		   		                      continue;
		   		                   }
		   		                   lastMatch = false;
		   		                   match = true;
		   		                   i++;
		   		               }
		   		           } else {
		   		               // standard case
		   		               while (i < len) {
		   		                   if (separatorChars.indexOf(str.charAt(i)) >= 0) {
		   		                       if (match || preserveAllTokens) {
		   		                           lastMatch = true;
		   		                           if (sizePlus1++ == max) {
		   		                               i = len;
		   		                               lastMatch = false;
		   		                           }
		   		                           list.add(str.substring(start, i));
		   		                           match = false;
		   		                       }
		   		                       start = ++i;
		   		                       continue;
		   		                   }
		   		                   lastMatch = false;
		   		                   match = true;
		   		                   i++;
		   		               }
		   		           }
		   		           if (match || (preserveAllTokens && lastMatch)) {
		   		               list.add(str.substring(start, i));
		   		           }
		   		           return (String[]) list.toArray(new String[list.size()]);
		   		     }
		   	 
		   	 
		   	 public static String[] splitByCharacterType(String str) {
		   		           return splitByCharacterType(str, false);
		   		       }
		   	 
		   	 
		   	 public static String[] splitByCharacterTypeCamelCase(String str) {
		   		           return splitByCharacterType(str, true);
		   		       }
		   	 
		   	 
		   	 private static String[] splitByCharacterType(String str, boolean camelCase) {
		   		           if (str == null) {
		   		               return null;
		   		           }
		   		           
		   		           char[] c = str.toCharArray();
		   		           List list = new ArrayList();
		   		           int tokenStart = 0;
		   		           int currentType = Character.getType(c[tokenStart]);
		   		           for (int pos = tokenStart + 1; pos < c.length; pos++) {
		   		               int type = Character.getType(c[pos]);
		   		               if (type == currentType) {
		   		                   continue;
		   		               }
		   		               if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
		   		                   int newTokenStart = pos - 1;
		   		                   if (newTokenStart != tokenStart) {
		   		                       list.add(new String(c, tokenStart, newTokenStart - tokenStart));
		   		                       tokenStart = newTokenStart;
		   		                   }
		   		               } else {
		   		                   list.add(new String(c, tokenStart, pos - tokenStart));
		   		                   tokenStart = pos;
		   		               }
		   		               currentType = type;
		   		           }
		   		           list.add(new String(c, tokenStart, c.length - tokenStart));
		   		           return (String[]) list.toArray(new String[list.size()]);
		   		       }
		   	 
	 
		   	  public static String deleteWhitespace(String str) {
		   		            if (isEmpty(str)) {
		   		                return str;
		   		            }
		   		            int sz = str.length();
		   		            char[] chs = new char[sz];
		   		            int count = 0;
		   		            for (int i = 0; i < sz; i++) {
		   		                if (!Character.isWhitespace(str.charAt(i))) {
		   		                    chs[count++] = str.charAt(i);
		   		                }
		   		            }
		   		            if (count == sz) {
		   		                return str;
		   		            }
		   		            return new String(chs, 0, count);
		   		        }
		   	  
		   	  
		   	  public static String removeStart(String str, String remove) {
		   		            if (isEmpty(str) || isEmpty(remove)) {
		   		                return str;
		   		            }
		   		            if (str.startsWith(remove)){
		   		                return str.substring(remove.length());
		   		            }
		   		            return str;
		   		       }
		   	  
		   	  public static String removeStartIgnoreCase(String str, String remove) {
		   		            if (isEmpty(str) || isEmpty(remove)) {
		   		                return str;
		   		            }
		   		            if (startsWithIgnoreCase(str, remove)) {
		   		                return str.substring(remove.length());
		   		            }
		   		            return str;
		   		        }
		   	  
		   	  
		   	  public static String removeEnd(String str, String remove) {
		   		            if (isEmpty(str) || isEmpty(remove)) {
		   		                return str;
		   		            }
		   		            if (str.endsWith(remove)) {
		   		                return str.substring(0, str.length() - remove.length());
		   		            }
		   		            return str;
		   		        }
		   	  
		   	  
		   	  public static String removeEndIgnoreCase(String str, String remove) {
		   		            if (isEmpty(str) || isEmpty(remove)) {
		   		                return str;
		   		            }
		   		            if (endsWithIgnoreCase(str, remove)) {
		   		                return str.substring(0, str.length() - remove.length());
		   		            }
		   		            return str;
		   		        }
		   	  
		   	 
		   	  
		   	  
		   	  public static String remove(String str, char remove) {
		   		            if (isEmpty(str) || str.indexOf(remove) == INDEX_NOT_FOUND) {
		   		                return str;
		   		            }
		   		            char[] chars = str.toCharArray();
		   		            int pos = 0;
		   		            for (int i = 0; i < chars.length; i++) {
		   		                if (chars[i] != remove) {
		   		                    chars[pos++] = chars[i];
		   		                }
		   		            }
		   		            return new String(chars, 0, pos);
		   		        }
		   	  
		   	  
		   	  
		   			          
		   	  
		   	  
		   	  public static String replaceChars(String str, char searchChar, char replaceChar) {
		   		            if (str == null) {
		   		               return null;
		   		            }
		   		            return str.replace(searchChar, replaceChar);
		   		        }
		   	  
		   	  
		   	  
		   	  
		   	  public static String chomp(String str, String separator) {
		   		            if (isEmpty(str) || separator == null) {
		   		                return str;
		   		            }
		   		            if (str.endsWith(separator)) {
		   		                return str.substring(0, str.length() - separator.length());
		   		            }
		   		            return str;
		   		        }
		   	  
		   	  
		   	  
		   	  public static String chompLast(String str) {
		   		            return chompLast(str, "\n");
		   		        }
		   	  
		   	  
		   	  public static String chompLast(String str, String sep) {
		   		            if (str.length() == 0) {
		   		                return str;
		   		            }
		   		            String sub = str.substring(str.length() - sep.length());
		   		           if (sep.equals(sub)) {
		   		               return str.substring(0, str.length() - sep.length());
		   		            }
		   		            return str;
		   		       }
		   	  
		   	  
		   	  public static String getChomp(String str, String sep) {
		   		            int idx = str.lastIndexOf(sep);
		   		            if (idx == str.length() - sep.length()) {
		   		                return sep;
		   		           } else if (idx != -1) {
		   		                return str.substring(idx);
		   		            } else {
		   		                return EMPTY;
		   		            }
		   		        }
		   	  
		   	  
		   	  public static String prechomp(String str, String sep) {
		   		            int idx = str.indexOf(sep);
		   		            if (idx == -1) {
		   		                return str;
		   		            }             
		   		            return str.substring(idx + sep.length());
		   		        }
		   	  
		   	  
		   	  public static String getPrechomp(String str, String sep) {
		   		            int idx = str.indexOf(sep);
		   		           if (idx == -1) {
		   		               return EMPTY;
		   		            } 
		   		            return str.substring(0, idx + sep.length());
		   		        }
		   	  
		    
		     
		   	  private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
		   		            if (repeat < 0) {
		   		                throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		   		            }
		   		            final char[] buf = new char[repeat];
		   		            for (int i = 0; i < buf.length; i++) {
		   		                buf[i] = padChar;
		   		            }
		   		            return new String(buf);
		   		        }
		   	  
		   	  
		   	  public static String rightPad(String str, int size) {
		   		           return rightPad(str, size, ' ');
		   		        }
		   	  
		   	  
		   	  public static String rightPad(String str, int size, char padChar) {
		   		            if (str == null) {
		   		                return null;
		   		            }
		   		            int pads = size - str.length();
		   		           if (pads <= 0) {
		   		                return str; // returns original String when possible
		   		            }
		   		            if (pads > PAD_LIMIT) {
		   		               return rightPad(str, size, String.valueOf(padChar));
		   		            }
		   		           return str.concat(padding(pads, padChar));
		   		        }
		   	  
		   	  
		   	  public static String rightPad(String str, int size, String padStr) {
		   		            if (str == null) {
		   		                return null;
		   		            }
		   		            if (isEmpty(padStr)) {
		   		                padStr = " ";
		   		            }
		   		            int padLen = padStr.length();
		   		            int strLen = str.length();
		   		            int pads = size - strLen;
		   		            if (pads <= 0) {
		   		                return str; // returns original String when possible
		   		            }
		   		            if (padLen == 1 && pads <= PAD_LIMIT) {
		   		                return rightPad(str, size, padStr.charAt(0));
		   		            }
		   		    
		   		            if (pads == padLen) {
		   		                return str.concat(padStr);
		   		            } else if (pads < padLen) {
		   		                return str.concat(padStr.substring(0, pads));
		   		           } else {
		   		                char[] padding = new char[pads];
		   		                char[] padChars = padStr.toCharArray();
		   		                for (int i = 0; i < pads; i++) {
		   		                    padding[i] = padChars[i % padLen];
		   		               }
		   		                return str.concat(new String(padding));
		   		            }
		   		        }
		   	  
		   	  
		   	  public static String leftPad(String str, int size) {
		   		            return leftPad(str, size, ' ');
		   		        }
		   	  
		   	  
		   	  public static String leftPad(String str, int size, char padChar) {
		   		            if (str == null) {
		   		                return null;
		   		            }
		   		            int pads = size - str.length();
		   		            if (pads <= 0) {
		   		                return str; // returns original String when possible
		   		            }
		   		           if (pads > PAD_LIMIT) {
		   		               return leftPad(str, size, String.valueOf(padChar));
		   		            }
		   		            return padding(pads, padChar).concat(str);
		   		        }
		   	  
		   	  
		   	  public static String leftPad(String str, int size, String padStr) {
		   		            if (str == null) {
		   		               return null;
		   		            }
		   		            if (isEmpty(padStr)) {
		   		               padStr = " ";
		   		            }
		   		            int padLen = padStr.length();
		   		            int strLen = str.length();
		   		            int pads = size - strLen;
		   		            if (pads <= 0) {
		   		                return str; // returns original String when possible
		   		            }
		   		            if (padLen == 1 && pads <= PAD_LIMIT) {
		   		                return leftPad(str, size, padStr.charAt(0));
		   		            }
		   		    
		   		            if (pads == padLen) {
		   		                return padStr.concat(str);
		   		            } else if (pads < padLen) {
		   		                return padStr.substring(0, pads).concat(str);
		   		            } else {
		   		                char[] padding = new char[pads];
		   		               char[] padChars = padStr.toCharArray();
		   		                for (int i = 0; i < pads; i++) {
		   		                    padding[i] = padChars[i % padLen];
		   		                }
		   		                return new String(padding).concat(str);
		   		            }
		   		        }
		   	  
		   	  public static int length(String str) {
		   		            return str == null ? 0 : str.length();
		   		        }
		   	  
		   	  
		   	  public static String center(String str, int size) {
		   		            return center(str, size, ' ');
		   		        }
		   	  
		   	  
		   	  public static String center(String str, int size, char padChar) {
		   		            if (str == null || size <= 0) {
		   		                return str;
		   		            }
		   		           int strLen = str.length();
		   		            int pads = size - strLen;
		   		            if (pads <= 0) {
		   		                return str;
		   		           }
		   		            str = leftPad(str, strLen + pads / 2, padChar);
		   		            str = rightPad(str, size, padChar);
		   		           return str;
		   		        }
		   	  
		   	  
		   	  public static String center(String str, int size, String padStr) {
		   		            if (str == null || size <= 0) {
		   		                return str;
		   		            }
		   		            if (isEmpty(padStr)) {
		   		                padStr = " ";
		   		            }
		   		            int strLen = str.length();
		   		            int pads = size - strLen;
		   		            if (pads <= 0) {
		   		                return str;
		   		            }
		   		            str = leftPad(str, strLen + pads / 2, padStr);
		   		            str = rightPad(str, size, padStr);
		   		            return str;
		   		        }
		   	  
		   	  public static String upperCase(String str) {
		   		           if (str == null) {
		   		                return null;
		   		            }
		   		            return str.toUpperCase();
		   		        }
		   	  
		   	  
		   	  public static String upperCase(String str, Locale locale) {
		   		            if (str == null) {
		   		               return null;
		   		            }
		   		            return str.toUpperCase(locale);
		   		        }
		   	  
		   	  
		   	  public static String lowerCase(String str) {
		   		            if (str == null) {
		   		               return null;
		   		           }
		   		          return str.toLowerCase();
		   		        }
		   	  
		   	  
		   	  public static String lowerCase(String str, Locale locale) {
		   		            if (str == null) {
		   		                return null;
		   		            }
		   		            return str.toLowerCase(locale);
		   		        }
		   	 
		   	 
		   	  
		   	  public static int countMatches(String str, String sub) {
		   		            if (isEmpty(str) || isEmpty(sub)) {
		   		                return 0;
		   		            }
		   		            int count = 0;
		   		            int idx = 0;
		   		            while ((idx = str.indexOf(sub, idx)) != INDEX_NOT_FOUND) {
		   		                count++;
		   		                idx += sub.length();
		   		            }
		   		            return count;
		   		        }
		   	  
		   	  public static boolean isAlpha(String str) {
		   		           if (str == null) {
		   		                return false;
		   		           }
		   		            int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		                if (Character.isLetter(str.charAt(i)) == false) {
		   		                    return false;
		   		               }
		   		           }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  
		   	  public static boolean isAlphaSpace(String str) {
		   		            if (str == null) {
		   		                return false;
		   		            }
		   		            int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		                if ((Character.isLetter(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
		   		                    return false;
		   		                }
		   		          }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  public static boolean isAlphanumeric(String str) {
		   		            if (str == null) {
		   		               return false;
		   		            }
		   		            int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		               if (Character.isLetterOrDigit(str.charAt(i)) == false) {
		   		                    return false;
		   		                }
		   		           }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  public static boolean isAlphanumericSpace(String str) {
		   		            if (str == null) {
		   		                return false;
		   		            }
		   		            int sz = str.length();
		   		            for (int i = 0; i < sz; i++) {
		   		                if ((Character.isLetterOrDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
		   		                   return false;
		   		                }
		   		            }
		   		            return true;
		   		        }
		   	 
		   	  
		   	  public static boolean isNumeric(String str) {
		   		            if (str == null) {
		   		                return false;
		   		            }
		   		            int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		               if (Character.isDigit(str.charAt(i)) == false) {
		   		                    return false;
		   		                }
		   		            }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  
		   	  public static boolean isNumericSpace(String str) {
		   		            if (str == null) {
		   		               return false;
		   		            }
		   		           int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		                if ((Character.isDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
		   		                  return false;
		   		               }
		   		            }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  public static boolean isWhitespace(String str) {
		   		            if (str == null) {
		   		                return false;
		   		            }
		   		            int sz = str.length();
		   		           for (int i = 0; i < sz; i++) {
		   		                if ((Character.isWhitespace(str.charAt(i)) == false)) {
		   		                    return false;
		   		               }
		   		            }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  
		   	  public static boolean isAllLowerCase(String str) {
		   		            if (str == null || isEmpty(str)) {
		   		                return false;
		   		            }
		   		           int sz = str.length();
		   		            for (int i = 0; i < sz; i++) {
		   		                if (Character.isLowerCase(str.charAt(i)) == false) {
		   		                    return false;
		   		                }
		   		            }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  public static boolean isAllUpperCase(String str) {
		   		            if (str == null || isEmpty(str)) {
		   		                return false;
		   		            }
		   		            int sz = str.length();
		   		            for (int i = 0; i < sz; i++) {
		   		                if (Character.isUpperCase(str.charAt(i)) == false) {
		   		                    return false;
		   		               }
		   		            }
		   		            return true;
		   		        }
		   	  
		   	  
		   	  public static String defaultString(String str) {
		   		           return str == null ? EMPTY : str;
		   		        }
		   	  
		   	  
		   	  public static String defaultString(String str, String defaultStr) {
		   		            return str == null ? defaultStr : str;
		   		        }
		   	  
		   	  
		   	  public static String defaultIfBlank(String str, String defaultStr) {
		   		            return StringUtils.isBlank(str) ? defaultStr : str;
		   		        }
		   	  
		   	  
		   	  public static String defaultIfEmpty(String str, String defaultStr) {
		   		            return StringUtils.isEmpty(str) ? defaultStr : str;
		   		        }
		   	  
		   	 
		   	 
		   	  
		   	  public static String abbreviate(String str, int maxWidth) {
		   		            return abbreviate(str, 0, maxWidth);
		   		        }
		   	  
		   	  
		   	  public static String abbreviate(String str, int offset, int maxWidth) {
		   		            if (str == null) {
		   		                return null;
		   		            }
		   		            if (maxWidth < 4) {
		   		                throw new IllegalArgumentException("Minimum abbreviation width is 4");
		   		            }
		   		            if (str.length() <= maxWidth) {
		   		                return str;
		   		            }
		   		            if (offset > str.length()) {
		   		               offset = str.length();
		   		            }
		   		           if ((str.length() - offset) < (maxWidth - 3)) {
		   		                offset = str.length() - (maxWidth - 3);
		   		            }
		   		            if (offset <= 4) {
		   		                return str.substring(0, maxWidth - 3) + "...";
		   		            }
		   		           if (maxWidth < 7) {
		   		                throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
		   		            }
		   		            if ((offset + (maxWidth - 3)) < str.length()) {
		   		                 return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		   		            }
		   		            return "..." + str.substring(str.length() - (maxWidth - 3));
		   		        }
		   	  
		   	  
		   	  
		   	  
		   	  
		   	  public static String difference(String str1, String str2) {
		   		            if (str1 == null) {
		   		                return str2;
		   		            }
		   		            if (str2 == null) {
		   		                return str1;
		   		            }
		   		            int at = indexOfDifference(str1, str2);
		   		            if (at == INDEX_NOT_FOUND) {
		   		                return EMPTY;
		   		           }
		   		            return str2.substring(at);
		   		        }
		   	  
		   	  
		   	  public static int indexOfDifference(String str1, String str2) {
		   		            if (str1 == str2) {
		   		                return INDEX_NOT_FOUND;
		   		            }
		   		            if (str1 == null || str2 == null) {
		   		                return 0;
		   		            }
		   		            int i;
		   		            for (i = 0; i < str1.length() && i < str2.length(); ++i) {
		   		                if (str1.charAt(i) != str2.charAt(i)) {
		   		                    break;
		   		                }
		   		            }
		   		            if (i < str2.length() || i < str1.length()) {
		   		                return i;
		   		            }
		   		            return INDEX_NOT_FOUND;
		   		        }
		   	  
		   	  
		   	  public static int indexOfDifference(String[] strs) {
		   		            if (strs == null || strs.length <= 1) {
		   		               return INDEX_NOT_FOUND;
		   		            }
		   		            boolean anyStringNull = false;
		   		            boolean allStringsNull = true;
		   		           int arrayLen = strs.length;
		   		           int shortestStrLen = Integer.MAX_VALUE;
		   		          int longestStrLen = 0;
		   		   
		   		           // find the min and max string lengths; this avoids checking to make
		   		            // sure we are not exceeding the length of the string each time through
		   		            // the bottom loop.
		   		          for (int i = 0; i < arrayLen; i++) {
		   		                if (strs[i] == null) {
		   		                   anyStringNull = true;
		   		                    shortestStrLen = 0;
		   		               } else {
		   		                   allStringsNull = false;
		   		                    shortestStrLen = Math.min(strs[i].length(), shortestStrLen);
		   		                    longestStrLen = Math.max(strs[i].length(), longestStrLen);
		   		                }
		   		            }
		   		   
		   		            // handle lists containing all nulls or all empty strings
		   		            if (allStringsNull || (longestStrLen == 0 && !anyStringNull)) {
		   		               return INDEX_NOT_FOUND;
		   		            }
		   		    
		   		           // handle lists containing some nulls or some empty strings
		   		            if (shortestStrLen == 0) {
		   		                return 0;
		   		            }
		   		   
		   		            // find the position with the first difference across all strings
		   		            int firstDiff = -1;
		   		            for (int stringPos = 0; stringPos < shortestStrLen; stringPos++) {
		   		               char comparisonChar = strs[0].charAt(stringPos);
		   		                for (int arrayPos = 1; arrayPos < arrayLen; arrayPos++) {
		   		                   if (strs[arrayPos].charAt(stringPos) != comparisonChar) {
		   		                        firstDiff = stringPos;
		   		                       break;
		   		                   }
		   		               }
		   		                if (firstDiff != -1) {
		   		                   break;
		   		                }
		   		            }
		   		    
		   		           if (firstDiff == -1 && shortestStrLen != longestStrLen) {
		   		               // we compared all of the characters up to the length of the
		   		               // shortest string and didn't find a match, but the string lengths
		   		               // vary, so return the length of the shortest string.
		   		              return shortestStrLen;
		   		          }
		   		            return firstDiff;
		   		       }
		   	  
		   	  public static String getCommonPrefix(String[] strs) {
		   		            if (strs == null || strs.length == 0) {
		   		                return EMPTY;
		   		            }
		   		            int smallestIndexOfDiff = indexOfDifference(strs);
		   		          if (smallestIndexOfDiff == INDEX_NOT_FOUND) {
		   		               // all strings were identical
		   		               if (strs[0] == null) {
		   		                    return EMPTY;
		   		                }
		   		               return strs[0];
		   		            } else if (smallestIndexOfDiff == 0) {
		   		               // there were no common initial characters
		   		              return EMPTY;
		   		           } else {
		   		                // we found a common initial character sequence
		   		               return strs[0].substring(0, smallestIndexOfDiff);
		   		            }
		   		        } 
		   	  
		   	  
		   	  public static int getLevenshteinDistance(String s, String t) {
		   		          if (s == null || t == null) {
		   		                throw new IllegalArgumentException("Strings must not be null");
		   		          }
		   		          int n = s.length(); // length of s
		   		                    int m = t.length(); // length of t
		   		            
		   		                  if (n == 0) {
		   		                      return m;
		   		                    } else if (m == 0) {
		   		                       return n;
		   		                    }
		   		           
		   		                   if (n > m) {
		   		                        // swap the input strings to consume less memory
		   		                        String tmp = s;
		   		                       s = t;
		   		                       t = tmp;
		   		                       n = m;
		   		                       m = t.length();
		   		                    }
		   		           
		   		                   int p[] = new int[n+1]; //'previous' cost array, horizontally
		   		                   int d[] = new int[n+1]; // cost array, horizontally
		   		                    int _d[]; //placeholder to assist in swapping p and d
		   		           
		   		                    // indexes into strings s and t
		   		                   int i; // iterates through s
		   		                   int j; // iterates through t
		   		           
		   		                    char t_j; // jth character of t
		   		            
		   		                    int cost; // cost
		   		           
		   		                    for (i = 0; i<=n; i++) {
		   		                        p[i] = i;
		   		                    }
		   		            
		   		                    for (j = 1; j<=m; j++) {
		   		                        t_j = t.charAt(j-1);
		   		                        d[0] = j;
		   		            
		   		                        for (i=1; i<=n; i++) {
		   		                            cost = s.charAt(i-1)==t_j ? 0 : 1;
		   		                            // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
		   		                          d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
		   		                       }
		   		           
		   		                        // copy current distance counts to 'previous row' distance counts
		   		                        _d = p;
		   		                        p = d;
		   		                        d = _d;
		   		                    }
		   		            
		   		                    // our last action in the above loop was to switch d and p, so p now 
		   		                    // actually has the most recent cost counts
		   		                    return p[n];
		   		                }
		   	  
		   	  
		   	  public static boolean startsWith(String str, String prefix) {
		   		           return startsWith(str, prefix, false);
		   		        }
		   	  
		   	  
		   	  public static boolean startsWithIgnoreCase(String str, String prefix) {
		   		          return startsWith(str, prefix, true);
		   		     }
		   	  
		   	  private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
		   		           if (str == null || prefix == null) {
		   		                return (str == null && prefix == null);
		   		            }
		   		           if (prefix.length() > str.length()) {
		   		                return false;
		   		            }
		   		            return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
		   		        }
		   	  
		   	  
		   	  
		   	  
		   	  public static boolean endsWith(String str, String suffix) {
		   		           return endsWith(str, suffix, false);
		   		      }
		   	  
		   	  public static boolean endsWithIgnoreCase(String str, String suffix) {
		   		           return endsWith(str, suffix, true);
		   		        }
		   	  
		   	  
		   	  private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
		   		            if (str == null || suffix == null) {
		   		               return (str == null && suffix == null);
		   		           }
		   		            if (suffix.length() > str.length()) {
		   		                return false;
		   		           }
		   		          int strOffset = str.length() - suffix.length();
		   		            return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
		   		       }
		   	  
		    
		   	  
		   	private static boolean isEmpty(String ts) {
		   		// TODO Auto-generated method stub
		   		return false;
		   		
		   		
		   	}
		   }
 
	

