package com.bdms.common.email;

import java.io.UnsupportedEncodingException;

import org.apache.commons.mail.EmailException;

public class Send {

	/**
	 * @param args
	 * @throws EmailException 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws EmailException, UnsupportedEncodingException {
		SendEmailToMe emailToMe =new SendEmailToMe();
		
		for (int i = 0; i < 2; i++) {
			
			String content=new String("及时发这个晚安".getBytes("gb2312"), "utf-8");
					
					
			boolean ok= emailToMe.sendmsgtome(content);
			System.out.println(ok);
		}
		
	}

}
