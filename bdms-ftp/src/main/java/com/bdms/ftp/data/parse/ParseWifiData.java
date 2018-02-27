package com.bdms.ftp.data.parse;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ParseWifiData {
	
	private File file; 	
	
	//构造函数
	public ParseWifiData(File file){
		this.file = file;
	}
	
	//每个字节的高四位和第四位互换
	private String handelByte(byte[] ba)throws UnsupportedEncodingException {
		for (int i = 0; i < ba.length; ++i) {
			byte b = ba[i];
			ba[i] = (byte) (((b & 0xf) << 4) | ((b & 0xf0) >> 4));
		}
		String s = new String(ba, "utf8");
		String stemp=s.replaceAll("[^(a-zA-Z0-9-.: )]{1,3}", ",");
		//System.out.println(stemp);

		return stemp;
	}
	//获取一个wifi的zip文件中数据的总数
	public int getCount(){
		int count = 0;
		ZipFile zf = null;
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;		
		if (!file.exists()) {
			System.out.println("file not exist" + file.getPath());
			return count;
		}
		try {
			zf = new ZipFile(file);
			Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zf.entries();
			while (en.hasMoreElements()) {
				ZipEntry entry = en.nextElement();
				is = zf.getInputStream(entry);
				bis = new BufferedInputStream(is);

				dis = new DataInputStream(bis);
				count = dis.readInt();				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			close(dis, bis, dis);

		}
		return count;
	}
	//读取一个zip文件中的wifi数据，并将所有的数据存到List中
	public List<String> parseData() {

		List<String> list = new ArrayList<>();
		ZipFile zf = null;
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;		
		if (!file.exists()) {
			System.out.println("file not exist" + file.getPath());
			return null;
		}
		try {
			zf = new ZipFile(file);
			Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zf.entries();
			while (en.hasMoreElements()) {
				ZipEntry entry = en.nextElement();
				is = zf.getInputStream(entry);
				bis = new BufferedInputStream(is);

				dis = new DataInputStream(bis);
				int size = dis.readInt();
				for(int i = 0; i < size; i ++){
					int length = dis.readInt();
					byte[] bytes = new byte[length];
					
					dis.read(bytes, 0, length);
					//System.out.println("i:"+(i+1));
					
					String s = handelByte(bytes);
					list.add(s);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			close(dis, bis, dis);

		}
		return list;
	}

	//关闭资源
	private void close(DataInputStream dis,BufferedInputStream bis,InputStream is){
		try {
			if(is != null)is.close();
			is = null;
			
			if(bis != null)bis.close();
			bis = null;
			
			if(dis != null)dis.close();
			dis = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		ParseWifiData p = new ParseWifiData(new File("d:\\LOCATION_WIFIMac_20150828154330291NO.1001536.zip"));
		List<String> list = p.parseData();
		int n = p.getCount();
		
		System.out.println("count:"+n);
		for(int i=0;i<list.size();i++){
			System.out.println("strings:"+list.get(i));
		}
		
//		String regEx="^LOCATION_WIFIMac_[0-9]{17}NO.[0-9]{7}.zip$";
//		Pattern pattern = Pattern.compile(regEx);
//		Matcher matcher = pattern.matcher("LOCATION_WIFIMac_20150828154330291NO.1001536.zip");
//		System.out.println(matcher.matches());

	}
	
}
