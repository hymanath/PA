package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.model.Registration;

public class RegistrationDAOHibernateTest extends BaseDaoTestCase{
	
	private IRegistrationDAO registrationDAO;
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
/*	Registration reg = new Registration("Ram","middleName","Charan2","itgrids2","password2",java.sql.Date.valueOf("2009-08-06"),"email@gmail.com","99655652","5846526",
			"address","gender","India","517325","access","type");

	public void testToCreateRegistration(){		
		registrationDAO.save(reg);
		setComplete();		
	}*/
	/*@SuppressWarnings("unchecked")
	public void testFindByUserNameAndPassword(){		
		List<Registration> registrations = registrationDAO.findByUserNameAndPassword("itgrids2", "password2");
		System.out.println(registrations.get(0).getUserName());
		Assert.assertEquals("Ram", registrations.get(0).getFirstName());
		
	}*/
	
	public void testGetAll(){
		List<Registration> users = registrationDAO.getAll();
		System.out.println(users.size());
		for(Registration user:users){
			if(user.getUserCategory() != null)
			System.out.println(user.getUserCategory().getUserCatagory());
		}
	}
	
	
	/*public void testToDeleteRegistration(){
		registrationDAO.remove(new Long(1));
		setComplete();
	}*/

	public void testGetDetails(){
		List<Registration> regUser = registrationDAO.findByUserRegistrationId(3l);
		Assert.assertEquals(1, regUser.size());
	}
}
