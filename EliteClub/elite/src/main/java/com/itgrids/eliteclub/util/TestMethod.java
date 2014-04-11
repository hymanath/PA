package com.itgrids.eliteclub.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itgrids.eliteclub.service.WebserviceHandlerSevice;

public class TestMethod {

	/**
	 * Apr 10, 2014
	 */
	public static void main(String[] args) {
		//Build application context by reading spring-config.xml
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
       
        //Get an instance of ProductService class;
        WebserviceHandlerSevice prdSvc = (WebserviceHandlerSevice) ctx.getBean("webserviceHandlerSeviceImpl");
       
        //Call getProduct method of ProductService
      prdSvc.loadObject(1);

	}

}
