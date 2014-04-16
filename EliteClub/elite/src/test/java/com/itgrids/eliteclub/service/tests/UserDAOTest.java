package com.itgrids.eliteclub.service.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/applicationContext.xml")
@ActiveProfiles("dev")
@Transactional
public class UserDAOTest {
	Logger log=LogManager.getLogger();	
    @Autowired
	private UserDAO userDAOImpl;
    
  /*  @Rollback(true)
    @Test*/
   
    public void testSave()
    {
    	log.debug("hello");
    	System.out.println(userDAOImpl);
    	
    	User user =new User();
    	//User  us=userDAOImpl.get(1);
    	User u=new User("anil", "a@gmail.com", "95054854444", "56482", null, null);
    	//userDAOImpl.saveOrUpdate(u);
    	User  us=userDAOImpl.save(u);
       // userDAOImpl.saveOrUpdate(new User("anil", "a@gmail.com", "9505485444", "56482", null, null));
    	System.out.println(us.getUserId());
    	
    	
    }
    @Test
    public void testGetUserByIMEINumber()
    {
    	log.debug(" here test case for  getting user by imei");
    User u=	userDAOImpl.getUserByIMEINumber("860070024338231");
    System.out.println(u.getUserId());
    	
    }
    
    
    
}
