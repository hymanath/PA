
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;

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
	
	/*public void testGetUserLocationDetailsByUserIds()
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
	}*/
	/*public void testSaveUserProfileImageNameToDB()
	{
		Integer result = userDAO.saveUserProfileImageNameToDB(1l, "1.jpg");
		System.out.println(result);
	}
	
	public void testGetUserByUserId()
	{
		User user = userDAO.getUserByUserId(1l);
		System.out.println(user.getFirstName());
	}
	
	public void testAllRegisteredUsersData()
	{
		List<Object[]> users = userDAO.allRegisteredUsersData();
		System.out.println(users.size());
		if(users != null && users.size() > 0)
		{
			for(Object[] result : users)
			{
				System.out.println(result[0]);
				System.out.println(result[1]);
				System.out.println(result[2]);
			}
		}
		
	}*/
	
	public void testGetUserEmail()
	{
		List<Object[]> result = userDAO.getUserEmail(2l);
		System.out.println(result.size());
		if(result != null && result.size() > 0)
		{
		for(Object[] params: result)
		{
			System.out.println(params[0]);
			System.out.println(params[1].toString()+" "+params[2].toString());
			System.out.println(params[3]);
		}
	 }
	}
}
