package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.utils.IConstants;

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
		Registration reg =  registrationDAO.get(359L);
		System.out.println(reg.getParentUser());
		System.out.println(reg.getSubUsers());
	}*/
	/*public void testGetSubusersByParentUserId(){
		
		List<Object[]> list = registrationDAO.getSubusersByParentUserId(1L);
		if(list.size() !=0){
		Object[] params = (Object[])list.get(0);
		System.out.println(params[0]);
		System.out.println(params[1]);
		StringBuilder str = new StringBuilder();
		str.append(params[0]).append(",").append(params[1]);
		System.out.println(str);
		}
		
		//+(new Long(obj.getParentUser().getRegistrationId().toString())));
		
		
	}*/
	
	/*public void test()
	{
		registrationDAO.getAll();
	}*/
	
	/*public void testSaveUserProfileImageNameToDB()
	{
		Integer result = registrationDAO.saveUserProfileImageNameToDB(805l, "anu.jpg");
		System.out.println(result);
	}*/
	/*public void testChangeUserPassword()
	{
		Integer result = registrationDAO.changeUserPassword("jyothi", 800l, IConstants.TRUE, new Date());
		System.out.println(result);
		
	}*/
	/*public void testCheckUserPassword()
	{
		List<Registration> list = registrationDAO.checkUserPassword("3352312",800l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetUserProfileImageNameByUserId()
	{
		List list = registrationDAO.getUserProfileImageNameByUserId(800l);
		System.out.println(list.size());
	}*/
	
	public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(800l);
		Object[] params = null;
		List locationDetails = registrationDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(locationDetails.size());
		if(locationDetails != null && locationDetails.size() > 0)
		{
			
		}
		
	}
}
