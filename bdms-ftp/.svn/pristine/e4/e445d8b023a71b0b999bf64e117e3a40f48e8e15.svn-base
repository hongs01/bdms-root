package com.bdms.ftp.simulate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;

public class MD5Util {
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String stringMD5(String input) {
	  try {
	     // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
	     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
	     // 输入的字符串转换成字节数组
	     byte[] inputByteArray = input.getBytes();
	     // inputByteArray是输入字符串转换得到的字节数组
	     messageDigest.update(inputByteArray);
	     // 转换并返回结果，也是字节数组，包含16个元素
	     byte[] resultByteArray = messageDigest.digest();
	     // 字符数组转换成字符串返回
	     return byteArrayToHex(resultByteArray);
	  } catch (NoSuchAlgorithmException e) {
	     return null;
	  }
	}
	
	public static String fileMD5(String inputFile) throws IOException {
	  // 缓冲区大小（这个可以抽出一个参数）
	  int bufferSize = 256 * 1024;
	  FileInputStream fileInputStream = null;
	  DigestInputStream digestInputStream = null;
	  	try {
	     // 拿到一个MD5转换器（同样，这里可以换成SHA1）
	     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
	     // 使用DigestInputStream
	     fileInputStream = new FileInputStream(inputFile);
	     digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
	     
	     // read的过程中进行MD5处理，直到读完文件
	     byte[] buffer =new byte[bufferSize];
	     while (digestInputStream.read(buffer) > 0);
	     // 获取最终的MessageDigest
	     messageDigest= digestInputStream.getMessageDigest();
	     // 拿到结果，也是字节数组，包含16个元素
	     byte[] resultByteArray = messageDigest.digest();
	     // 同样，把字节数组转换成字符串
	     return byteArrayToHex(resultByteArray);
	  } catch (NoSuchAlgorithmException e) {
	     return null;
	  } finally {
	     try {
	        digestInputStream.close();
	     } catch (Exception e) {
	     }
	     try {
	        fileInputStream.close();
	     } catch (Exception e) {
	     }
	  }
	}
	
	
	public static String ftpFileMD5(String inputFile) throws IOException {
		  // 缓冲区大小（这个可以抽出一个参数）
		  int bufferSize = 256 * 1024;
		  FileInputStream fileInputStream = null;
		  DigestInputStream digestInputStream = null;
		  	try {
		     // 拿到一个MD5转换器（同样，这里可以换成SHA1）
		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		     // 使用DigestInputStream
		     FTPClient ftp=FtpClientUtil.getFtp();
//		     fileInputStream = new FileInputStream(inputFile);
		     ftp.changeWorkingDirectory(Config.FTP_PATH);
		     
		     digestInputStream = new DigestInputStream(ftp.retrieveFileStream(inputFile),messageDigest);
		     // read的过程中进行MD5处理，直到读完文件
		     byte[] buffer =new byte[bufferSize];
		     while (digestInputStream.read(buffer) > 0);
		     // 获取最终的MessageDigest
		     messageDigest= digestInputStream.getMessageDigest();
		     // 拿到结果，也是字节数组，包含16个元素
		     byte[] resultByteArray = messageDigest.digest();
		     // 同样，把字节数组转换成字符串
		     return byteArrayToHex(resultByteArray);
		  } catch (NoSuchAlgorithmException e) {
		     return null;
		  } finally {
		     try {
		        digestInputStream.close();
		     } catch (Exception e) {
		     }
		     try {
		        fileInputStream.close();
		     } catch (Exception e) {
		     }
		  }
		}
	
	public static String byteArrayToHex(byte[] byteArray) {
	  // 首先初始化一个字符数组，用来存放每个16进制字符
	  char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
	  // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
	  char[] resultCharArray =new char[byteArray.length * 2];
	  // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
	  int index = 0;
	  for (byte b : byteArray) {
	     resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
	     resultCharArray[index++] = hexDigits[b& 0xf];
	  }
	  // 字符数组组合成字符串返回
	  return new String(resultCharArray);
	}
	
	
	public final static String MD5Row(String file,long rownums,String md5) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date(System.currentTimeMillis()));
		return date+","+file+","+rownums+","+md5+"\r\n";
    }
	
    public static void main(String[] args) throws IOException {
        System.out.println(MD5Util.MD5("20121221"));
        System.out.println(MD5Util.MD5("加密"));
        System.out.println(MD5Util.stringMD5("20121221"));
        System.out.println(MD5Util.stringMD5("加密"));
        System.out.println(MD5Util.fileMD5("/tmp/OD_DAY_20150714"));
//        System.out.println(MD5Util.stringMD5(FtpClientUtil.getFilesStr("ttt.txt")));
//        System.out.println(MD5Util.fileMD5("//tmp//ttt.txt"));
        System.out.println(MD5Util.ftpFileMD5("OD_DAY_20150714"));
        
    }

}
