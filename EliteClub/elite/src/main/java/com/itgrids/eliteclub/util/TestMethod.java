package com.itgrids.eliteclub.util;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.dto.UserContactsInputVO;
import com.itgrids.eliteclub.model.User;
import com.itgrids.eliteclub.service.WebserviceHandlerSevice;

public class TestMethod {

	/**
	 * Apr 10, 2014
	 */
	public static void main(String[] args) {
		//Build application context by reading spring-config.xml
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
       
        //Get an instance of ProductService class;
        WebserviceHandlerSevice prdSvc = (WebserviceHandlerSevice) ctx.getBean("webserviceHandlerSevice");
       
         //Call getProduct method of ProductService
         //   User  us= prdSvc.save(new User("anil", "a@gmail.com", "9505485043", "56482", null, null));
         //System.out.println(us);
	
	//test save 
        UserContactsInputVO user =new UserContactsInputVO();
        
        user.setEmailId("rak");
		user.setImeiNo("jhsdjksdfh");
		user.setMobileno("anilkumar");
		user.setUserName("ravula");
		HashMap map=	new HashMap<String, String>();
		map.put("99592325", "1212");
		map.put("995953232", "1212");
        user.setContacts(map);
        prdSvc.loadCategories(user);
	}

}
