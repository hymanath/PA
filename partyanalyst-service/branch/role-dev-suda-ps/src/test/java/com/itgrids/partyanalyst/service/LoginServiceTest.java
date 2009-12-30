package com.itgrids.partyanalyst.service;

import java.util.ArrayList;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.impl.LoginService;
import com.itgrids.partyanalyst.util.DummyRegistrationDAOStuff;

	public class LoginServiceTest{
		
		
		public void test()
		{
			Assert.assertEquals(2,2);
		}
		private IRegistrationDAO registrationDAO;
		private LoginService loginService;
		
		
	
		@Before
		public void setUp() throws Exception {
			registrationDAO = EasyMock.createMock(IRegistrationDAO.class);
			loginService = new LoginService();
			loginService.setRegistrationDAO(registrationDAO);
		}
		
		@Test
		public void testCheckForValidUser(){
			EasyMock.expect(registrationDAO.findByUserNameAndPassword("itgrids","password")).andReturn(DummyRegistrationDAOStuff.getRegistrations());
			EasyMock.replay(registrationDAO);	
			
			Assert.assertEquals("lastName",loginService.checkForValidUser("itgrids","password").getLastName());
			
			EasyMock.verify(registrationDAO);
			
		}
		
		
		@Test
		public void testForInvalidUser(){
			EasyMock.expect(registrationDAO.findByUserNameAndPassword("itgrid","password")).andReturn(DummyRegistrationDAOStuff.getEmptyList());
			EasyMock.replay(registrationDAO);	
			Assert.assertEquals(null,loginService.checkForValidUser("itgrid","password").getRegistrationID());
			EasyMock.verify(registrationDAO);
		}
}

