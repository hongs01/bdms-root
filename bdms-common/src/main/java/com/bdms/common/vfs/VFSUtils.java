package com.bdms.common.vfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.vfs2.FileNotFolderException;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.VfsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Description:
 * 		更换的v2版本，方法得修改
 * 
 * History：
 * =============================================================
 * Date                      Version        Memo
 * 2015-8-22下午1:39:23            1.0            Created by YuXiaolin
 * 
 * =============================================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class VFSUtils {

//	  private static Logger logger = LoggerFactory.getLogger(VFSUtils.class);   
//      
//	     private static FileSystem fsManager = null;   
//	        
//	     static  
//	     {   
//	         try  
//	         {   
//	             fsManager = VfsLog.getManager();   
//	         }   
//	         catch (FileNotFolderException e)   
//	         {   
//	             logger.error("init vfs fileSystemManager fail.", e);   
//	         }   
//	     }
//	     
//	     //读取文件，并将其中内容以byte进行输出
//	     public static byte[] readFileToByteArray(String filePath)   
//	             throws IOException   
//	    {   
//	         if (StringUtils.isEmpty(filePath))   
//	         {   
//	             throw new IOException("File '" + filePath + "' is empty.");   
//	         }   
//	         FileObject fileObj = null;   
//	         InputStream in = null;   
//	         try  
//	         {   
//	             fileObj = fsManager.resolveFile(filePath);   
//	             if (fileObj.exists())   
//	             {   
//	                 System.out.println(fileObj.getType().getName());   
//	                 if (FileType.FOLDER.equals(fileObj.getType()))   
//	                 {   
//	                    throw new IOException("File '" + filePath   
//	                            + "' exists but is a directory");   
//	                }   
//	                else  
//	                {   
//	                    in = fileObj.getContent().getInputStream();   
//	                    return IOUtils.toByteArray(in);   
//	                }   
//	            }   
//	            else  
//	            {   
//	                throw new FileNotFoundException("File '" + filePath   
//	                        + "' does not exist");   
//	            }   
//	        }   
//	        catch (FileSystemException e)   
//	        {   
//	            throw new IOException("File '" + filePath + "' resolveFile fail.");   
//	        }   
//	        finally  
//	        {   
//	            IOUtils.closeQuietly(in);   
//	            if (fileObj != null)   
//	            {   
//	                fileObj.close();   
//	            }   
//	        }   
//	    }
//	     
//	     //读取文件，并将内容以字符串string进行输出
//	     public static String readFileToString(String filePath, String encoding)   
//	             throws IOException   
//	     {   
//	         if (StringUtils.isEmpty(filePath))   
//	        {   
//	             throw new IOException("File '" + filePath + "' is empty.");   
//	         }   
//	         FileObject fileObj = null;   
//	         InputStream in = null;   
//	         try  
//	         {   
//	             fileObj = fsManager.resolveFile(filePath);   
//	             if (fileObj.exists())   
//	             {   
//	                 if (FileType.FOLDER.equals(fileObj.getType()))   
//	                 {   
//	                     throw new IOException("File '" + filePath   
//	                             + "' exists but is a directory");   
//	                 }   
//	                 else  
//	                 {   
//	                    in = fileObj.getContent().getInputStream();   
//	                     return IOUtils.toString(in, encoding);   
//	                 }   
//	             }   
//	             else  
//	             {   
//	                 throw new FileNotFoundException("File '" + filePath   
//	                         + "' does not exist");   
//	            }   
//	         }   
//	         catch (FileSystemException e)   
//	         {   
//	             throw new IOException("File '" + filePath + "' resolveFile fail.");   
//	         }   
//	         finally  
//	         {   
//	             IOUtils.closeQuietly(in);   
//	            if (fileObj != null)   
//	             {   
//	                 fileObj.close();   
//	             }   
//	         }   
//	     }   
}
