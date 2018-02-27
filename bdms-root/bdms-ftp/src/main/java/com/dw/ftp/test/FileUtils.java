package com.dw.ftp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import com.dw.ftp.json.Employee;

public class FileUtils {

	//以行为单位读取文件
	public static void readFromFile(String fileName){
		
		File file = new File(fileName);
		BufferedReader reader = null;	
		try {
			
			reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
                System.out.println("line " + line + ": " + tempString);  
                line++;  
            }  
            reader.close();  
			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			
			 if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException  e) { 
	                	e.printStackTrace();
	                }
	           }  
		  }
	}
	
	//以行为单位写入文件
	public static void write2File(String fileName,String content){
		FileWriter writer = null; 
		try {		
			 writer = new FileWriter(fileName, true); 
			 writer.write(content+System.getProperty("line.separator"));  
			 writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(), 1000, 1000);
	
	}

}
