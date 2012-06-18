package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserDAO;

public class UserDAOHibernateTest extends BaseDaoTestCase{

	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*public void test()
	{
		userDAO.getAll();
	}*/
	
	/*public void testCheckUserPassword()
	{
		List<User> list = userDAO.checkUserPassword("7258695", 12l);
		System.out.println(list.size());
	}*/
	
	/*public void testChangeUserPassword()
	{
	Integer result = userDAO.changeUserPassword("7258695", 12l, IConstants.TRUE, new Date());
	System.out.println(result);
	}*/
	
	/*public void testGetUserIdByUserName()
	{
		List<Object> list = userDAO.getUserIdByUserName("Anuradha@aa.com");
		System.out.println(list.get(0));
	}*/
	
	/*public void testFindByUserNameAndPassword()
	{
		User user = userDAO.findByUserNameAndPassword("aaaa@aa.com", "3685171");
		System.out.println(user.getUserName());
	}*/
	
	/*public void test()
	{
		System.out.println(userDAO.getUserProfileImageNameByUserId(1l));
	}*/
	
	public void testGetUserLocationDetailsByUserIds()
	{
		List<Long> userIds = new ArrayList<Long>(0);
		userIds.add(1l);
		
		List<Object[]> list = userDAO.getUserLocationDetailsByUserIds(userIds);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("----\t"+obj.toString());
		}
	}
}
