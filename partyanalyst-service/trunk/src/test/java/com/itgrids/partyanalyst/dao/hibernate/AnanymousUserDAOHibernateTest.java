package com.itgrids.partyanalyst.dao.hibernate;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.utils.IConstants;


public class AnanymousUserDAOHibernateTest extends BaseDaoTestCase {
	
	private IAnanymousUserDAO ananymousUserDAO;
	private ICustomMessageDAO customMessageDAO;
	
	public ICustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(ICustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}

	/*public void testAnonymousUserLogin(){		
		List<AnanymousUser> detailsList = ananymousUserDAO.checkAnonymousUserLogin("ravi","kiran");	
		assertEquals(detailsList.size(), 1);
	}*/

	/*public void testAvailabityOfUserNameForAnonymousUser(){		
		List<Long> details =  new ArrayList<Long>(0);
		details.add(2l);
		List<AnanymousUser> detailsList = ananymousUserDAO.getDetailsForUsers(details);	
		for(AnanymousUser result : detailsList){
			System.out.println(result.getLastName());
		}
		System.out.println(detailsList.size());
	}*/
	
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(2L);
		
		List locationDetails = ananymousUserDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(locationDetails.size());
		
	}*/
	
	/*public void testGetConnectedUsersCount()
	{
		List connectedCount = ananymousUserDAO.getConnectedUsersCount(19L, IConstants.DISTRICT);
		
		System.out.println(connectedCount);
	}*/
	
	/*public void testGetAllUsersCountInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(19L);
		String count = ananymousUserDAO.getAllUsersCountInSelectedLocations(locationIds, IConstants.DISTRICT);
		
		System.out.println(count);
	}*/
	
	/*public void testGetAllUsersInSelectedLocations()
	{
		List<Long> locationId = new ArrayList<Long>();
		locationId.add(232L);
		
		List count = ananymousUserDAO.getAllUsersInSelectedLocations(locationId, IConstants.CONSTITUENCY_LEVEL, 5L, 0L, "A");
		
		System.out.println(count.size());
	}*/
	
	/*public void testsaveUserProfileImageNameToDB()
	{
		int result = ananymousUserDAO.saveUserProfileImageNameToDB(21L, "kamalakar.jpg"); 
		System.out.println(result);
	}
	*/
	
	/*public void testgetUserProfileImageNameByUserId()
	{
		List result = ananymousUserDAO.getUserProfileImageNameByUserId(new Long(21));
		
		System.out.println(result.size());
	}*/
	/*public void checkForUserNameAvailabiityForEmail()
	{
		List result=ananymousUserDAO.checkForUserNameAvailabiity("gfjhgh@dghfth.in");
		System.out.println(result);
	}
	*/
	/*public void testgetUsername()
	{
	 List<AnanymousUser> params = ananymousUserDAO.getUserByUserName("kamalakar");
	 for(AnanymousUser list : params)
	 {
		 System.out.println(list.getPassword());
	 }
	 
}
	public void testchangeUserAsEmail()
	{
		List<AnanymousUser> params = ananymousUserDAO.changeUserNameAsEmail("nunna.nagamani123@gmail.com");
		for(AnanymousUser list : params)
		{
			System.out.println(list.getLastName());
		}
		
	}*/
	
	/*public void testGetUserEmail()
	{
	List<Object[]> list =ananymousUserDAO.getUserEmail(1l);
	System.out.println(list.size());
	if(list != null && list.size() > 0)
		for(Object[] params : list){
			
			System.out.println(params[0] +" " +params[1] +" " + params[2] + " " + params[3]);
		
		}
	}*/
	
	/*public void testGetPasswordNotUpdatdUsersList()
	{
		List<Object[]> list = ananymousUserDAO.getPasswordNotUpdatdUsersList();
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println((Date)params[2]);
			
		}
			
	}*/
	
	/*public void testGetAllUsersCountInSelectedLocationsInAFilterView()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(323l);
		System.out.println(ananymousUserDAO.getAllUsersCountInSelectedLocations(locationIds,IConstants.CONSTITUENCY_LEVEL,"kamal"));
	}*/
	
	/*public void testGetNotConnectedUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(3l);
		List<Long> otherUsers = new ArrayList<Long>(0);
		otherUsers.add(736l);
		
		List<Object> list = ananymousUserDAO.getNotConnectedUsersInSelectedLocations(411l, locationIds, IConstants.DISTRICT_LEVEL, otherUsers, 20l, 0l, "Athidhi");
		
		System.out.println(list.size());
		
		for(Object obj : list)
		{
			Object[] params = (Object[]) obj ;
			System.out.println();
			for(Object o : params)
				System.out.print("\t"+o.toString());
		}
	}*/
	
	/*public void testGetNotConnectedUsersCountForAUserInAFilterView(){
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(3l);
		List<Long> otherUsers = new ArrayList<Long>(0);
		otherUsers.add(736l);
		otherUsers.add(411l);
		otherUsers.add(583l);
		otherUsers.add(473l);
		Long notConnCnt=ananymousUserDAO.getNotConnectedUsersCountForAUserInAFilterView(411l, locationIds, IConstants.DISTRICT_LEVEL, "", otherUsers);
		System.out.println(notConnCnt);		
	}*/
	
	/*public void testgetAllUsersMobile()
	{
		List<Object[]> list = ananymousUserDAO.getAllUsersMobile();
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println(params[0].toString());
			System.out.println(params[1].toString());
			System.out.println(params[2].toString());
			System.out.println(params[3].toString());
			System.out.println(params[4].toString());
			
		}
		
		
	}*/
	public void testGelAllMobilenosAsUnique()
	{
		 List<Object> list = ananymousUserDAO.getAllMobilenosAsUnique();
		 System.out.println(list);
	}
}
