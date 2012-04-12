package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;

public class UserLoginDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	
	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO){
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}
	/*public void test(){
		userLoginDetailsDAO.getAll();
	}*/
	/*public void testGetBySessionId(){
		userLoginDetailsDAO.getBySessionId("12323243423423");
	}*/
	
	/*public void testGetUserPageAccessTime(){
		List<Object[]> list = userLoginDetailsDAO.getUserPageAccessTime("473351802D2129B5EBE185663C0D50EB");
		
		if(list != null && list.size() > 0)
			for(Object[] params : list)
			{
				System.out.println("time is........"+params[0]);
				System.out.println("session id..."+params[1]);
			}
	}*/
	
	/*public void testGetSessionIdsAndLogoutTimeInWithinDates()
	{
		System.out.println(new Date());
		List<Object[]> list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeInWithinDates(new Date(),new Date());
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0] +"----"+params[1]);
		}
	}*/
	
	public void testGetSessionIdsAndLogoutTimeOfTodaysUsers()
	{
		System.out.println(new Date());
		List<Object[]> list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeOfTodaysUsers(new Date());
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0] +"----"+params[1]);
		}
	}
}
