package com.bdms.common.digester;

import java.net.URL;

import javax.servlet.ServletException;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;

public class DigesterUtils {

	    private Object servletName;
	    private String servletMapping;
	    private Digester createDigester(){
	        Digester digester =new Digester();
	        digester.addObjectCreate("Root", PutuDesc.class);
	        digester.addObjectCreate("Root/PeakInfo", PeakInfo.class);
	        digester.addSetNestedProperties("Root/PeakInfo");
	        digester.addSetNext("Root/PeakInfo", "setPeakInfo");        
	        return digester;
	    }
	    
	    protected void initServlet() throws ServletException{
	        String[] registrations=new String[10];
	        Digester digester=new Digester();
	        digester.push(this);
	        digester.setNamespaceAware(true);
	        digester.setValidating(false);
	        for(int i=0;i<registrations.length;i+=2){
	        URL url=this.getClass().getResource(registrations[i+1]);
	        if(url!=null)
	            digester.register(registrations[i], url.toString());
	        }
	        }
	    
	    
	    public void addServletMapping(String servletName,String urlPattern){
	        Log log = null;
	       
	    if(log.isDebugEnabled()){
	    log.debug("process servletName="+servletName+",urlPattern="+urlPattern);
	    }
	    if(servletName==null){
	    return;
	    }
	    if(servletName.equals(this.servletName)){
	    this.servletMapping=urlPattern;
	    }
	    }
	   
	    
}
