package com.bdms.common.email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class SendEmailToMe {
	
	public static boolean sendmsgtome(String msg) throws EmailException {
		Email email=new SimpleEmail();
		email.setHostName("192.168.0.66");
		//email.setSslSmtpPort("25");
		email.setSmtpPort(25);
		email.setAuthentication("yuxiaolin@dscomm.com.cn", "yuxiaolin1a");
		//email.setPopBeforeSmtp(true, "192.168.0.66", );
		//email.s
		//email.setAuthenticator(new DefaultAuthenticator("yuxiaolin@dscomm.com.cn", "yuxiaolin1a"));
		//email.setSSL(true);
		email.setFrom("yuxiaolin@dscomm.com.cn");
		email.setSubject("就是发这个完下");
		email.setContent("就是发这个完下", "text/plain; charset=gb2312");
		email.addTo("duzhihao@dscomm.com.cn");
		email.send();
		
		return true;

	}
	
		
}
