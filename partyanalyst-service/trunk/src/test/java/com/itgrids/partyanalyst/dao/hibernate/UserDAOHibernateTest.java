
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.User;

public class UserDAOHibernateTest extends BaseDaoTestCase{

	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

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
	}*/
	
	/*public void testAllRegisteredUsersData()
	{
		List<Object[]> users = userDAO.allRegisteredUsersData();
		System.out.println(users.size());
		if(users != null && users.size() > 0)
		{
			for(Object[] result : users)
			{
				System.out.println(result[0]+" ----- "+result[1]+" "+result[2]);
			}
		}
		
	}*/
	/*public void testGetUserByUserName()
	{
		List<User> params = userDAO.getUserByUserName("a.dakavaram@itgrids.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
	}*/

/*public void testGetUserEmail()
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
	}*/
	
	/*public void testchangeUserNameAsEmail()
	{
		List<User> params = userDAO.changeUserNameAsEmail("nunna@gmail.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
		
	}*/
	
	/*public void testGetUserDetails()
	{
		List result = userDAO.getUserDetails("Chintu@anu.com");
		System.out.println(result.size());
	}*/
	
	/*public void testCheckAnonymousUserLogin()
	{
		List<User> result = userDAO.checkAnonymousUserLogin("anuradha","anuradha");
		System.out.println(result.size());
		if(result != null && result.size() >0)
		{
		for(User userDetails : result)
		{
			System.out.println(userDetails.getUserName());
			System.out.println(userDetails.getPassword());
		}
		}
	}*/
	
	/*public void testGetPasswordNotUpdatdUsersList()
	{
		List<Object[]> list = userDAO.getPasswordNotUpdatdUsersList();
		System.out.println(list.size());
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	/*public void testGetAllUsersMobile()
	{
		List<Object[]> list = userDAO.getAllUsersMobile();
		if(list != null &&list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
				System.out.println(params[3]);
			}
			
		}
	}*/
	
	/*public void testGetAllMobilenosAsUnique()
	{
		Object list = userDAO.getAllMobilenosAsUnique();
		System.out.println(list);
	}*/
	
	/*public void testFindByUserRegistrationId()
	{
		List<User> result = userDAO.findByUserRegistrationId(1l);
		if(result != null && result.size() > 0)
		{
			for(User userDetails : result)
			{
				System.out.println(userDetails.getUserName());
				System.out.println(userDetails.getPassword());
			}
		}
	}*/
	
	/*public void testGetAllRegisteredUsers()
	{
		List<User> details = userDAO.getAllRegisteredUsers();
		Assert.assertEquals(33, details.size());
	}*/
	
	/*public void testGetSubusersByParentUserId()
	{
		List<Object[]> list = userDAO.getSubusersByParentUserId(1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(1l);
		List list = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(list.size());
	}*/
	
	/*public void testGetUserEmailByUserId()
	{
		List<Object[]> list = userDAO.getUserEmailByUserId(1l);
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
			}
		}
	}*/
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(1l);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Long> userId = new ArrayList<Long>(0);
		userId.add(1l);
		userId.add(2l);
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(userId);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	/*public void testgetAllUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(10l);
		List<Object> list = userDAO.getAllUsersInSelectedLocations(locationIds, "DISTRICT", 2l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}
	public void testgetNotConnectedUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(10l);
		List<Long> otherUsers = new ArrayList<Long>();
		otherUsers.add(10l);
		
		List<Object> list = userDAO.getNotConnectedUsersInSelectedLocations(817l, locationIds, "DISTRICT", otherUsers, 2l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}
	*/
	
	public void testGetUserNameAndPWDByUserId()
	{
		List<Object[]> list = userDAO.getUserNameAndPwdByUserId(1l);
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}
}
