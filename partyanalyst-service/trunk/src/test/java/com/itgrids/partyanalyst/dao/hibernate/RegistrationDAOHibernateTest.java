package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroupRelation;

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
	
	/*public void testGetAll(){
		List<Registration> users = registrationDAO.getAll();
		System.out.println(users.size());
		for(Registration user:users){
			if(user.getUserCategory() != null)
			System.out.println(user.getUserCategory().getUserCatagory());
		}
	}
	
	public void testToDeleteRegistration(){
		registrationDAO.remove(new Long(1));
		setComplete();
	}

	public void testGetDetails(){
		List<Registration> regUser = registrationDAO.findByUserRegistrationId(3l);
		Assert.assertEquals(1, regUser.size());
	}
	
	public void testGetUserEntitlements(){
		Set<UserGroupRelation> entitlements = registrationDAO.get(1l).getUserGroupRelations();
		System.out.println(entitlements.size());
	}*/
	
	/*public void testGetUserEntitlements(){		
		List arr = registrationDAO.getAllRegisteredUsers();
		Assert.assertEquals(1, arr.size());
	}*/
	
	/*public void testSubUsers()
	{
		Registration reg =  registrationDAO.get(5L);
		System.out.println(reg.getParentUser());
		System.out.println(reg.getSubUsers().size());
	}*/
	
	public void test()
	{
		registrationDAO.getAll();
	}
}
