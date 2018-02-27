package com.bdms.common.codec;

import org.apache.commons.codec.binary.Base64;   
import org.apache.commons.codec.digest.DigestUtils;

public  class CodecUtils {

	//把密码进行MD5加密 
	public static String getMd5Pwd(String password){   
		         String returnStr = DigestUtils.md5Hex(password);   
		         System.out.println(returnStr);   
		         return DigestUtils.md5Hex(returnStr);   
		    } 
	    
    //把密码进行SHA1加密  
	        
     public static String getSha1Pwd(String password){   
	                String returnStr = DigestUtils.shaHex(password);   
	                System.out.println(returnStr);   
	                return returnStr;   
	     } 
     
      
     // 把密码进行BASE64加密  
       
     public static String getBase64Pwd(String password){   
              byte[] b = Base64.encodeBase64(password.getBytes(), true);   
              String returnStr = new String(b);   
              System.out.println(returnStr);   
              return returnStr;   
         }   
     
     //将密码进行BASE64解密
     public static String getUnBase64Pwd(String password){   
         byte[] b = Base64.decodeBase64(password.getBytes());   
         String returnStr = new String(b);   
         System.out.println(returnStr);   
         return returnStr;   
         }
     
     
//     public static void main(String[] args) {   
//    	        String password = "abc";   
//    	        CodecUtils.getMd5Pwd(password);   
//    	        CodecUtils.getSha1Pwd(password);   
//    	        CodecUtils.getBase64Pwd(password);   
//    	        CodecUtils.getUnBase64Pwd("YWJj");   
//    	     }  
}
