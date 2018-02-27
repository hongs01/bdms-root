package com.bdms.sqoop.WebServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bdms.sqoop.WebServer.SQOOP2WebServer;

public class ProjectStartListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		
		SQOOP2WebServer server = SQOOP2WebServer.getSQOOP2WebServer();
		arg0.getServletContext().setAttribute("server", server);
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
		 Object attribute = arg0.getServletContext().getAttribute("server");
		 if(attribute != null){
			 SQOOP2WebServer s2w = (SQOOP2WebServer)attribute;
			 s2w = null;
		 }
		
	}
}
