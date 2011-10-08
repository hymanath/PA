package com.itgrids.partyanalyst.dao.hibernate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IUserProfileOptsDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.model.UserProfileOpts;
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
	public void checkForUserNameAvailabiityForEmail()
	{
		List result=ananymousUserDAO.checkForUserNameAvailabiity("gfjhgh@dghfth.in");
		System.out.println(result);
	}
	
}
