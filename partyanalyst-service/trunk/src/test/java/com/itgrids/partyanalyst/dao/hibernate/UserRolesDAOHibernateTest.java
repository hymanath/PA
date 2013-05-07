package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserRolesDAOHibernateTest extends BaseDaoTestCase{

	private IUserRolesDAO userRolesDAO;

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	
	/*public void test()
	{
		userRolesDAO.getAll();
	}*/
	
	/*public void testGetUserRoles()
	{
		List<Object[]> userRoles = userRolesDAO.getUserRoles(28L);
		System.out.println(userRoles.size());
		for(Object[] param : userRoles){
			System.out.println((Long)param[0]);
			System.out.println(param[1].toString());
		}
	}*/
	
	/*public void test()
	{
		 List<Object[]> list = userRolesDAO.getAllFreeuser();
		 for(Object[] params : list)
		 {
			 
			 System.out.println(params[0].toString());
			// System.out.println(params[1].toString());
		 }
		
		
	}*/
	
	/*public void testGetUserRolesOfAUser()
	{
		List<String> roles = userRolesDAO.getUserRolesOfAUser(1l);
		System.out.println(roles.size());
		
		for(String str : roles)
		{
			System.out.println(str);
		}
	}
	*/
	/*public void testgetAllFreeusertoSendEmail()
	{
		 List<Object[]> roles = userRolesDAO.getAllFreeusertoSendEmail();
		System.out.println(roles.size());
		
		}*/
	
	/*public void testgetUsersForSendingEmails()
	{
		 List<Object[]> roles = userRolesDAO.getUsersForSendingEmails(1l,1l,1l,1l,1l);
		System.out.println(roles.size());
		
		}*/
	
	/*public void test()
	{
		 List<Object[]> list = userRolesDAO.getAllRestrictedUsers();
		 System.out.println(list.size());
	}*/
	
	public void testgetAllUsersByRoleType()
	{
		List<Object[]> list = userRolesDAO.getAllUsersByRoleType(IConstants.PARTY_ANALYST_USER);
		System.out.println(list.size());
		for(Object[] params : list)
		System.out.println(params[0]+"----- "+params[1]+" "+params[2]);
	}
	
}
