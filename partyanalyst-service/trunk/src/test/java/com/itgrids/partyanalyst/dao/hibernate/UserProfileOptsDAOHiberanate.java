package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserProfileOptsDAO;

public class UserProfileOptsDAOHiberanate extends BaseDaoTestCase{

	private IUserProfileOptsDAO userProfileOptsDAO;
	
	public IUserProfileOptsDAO getUserProfileOptsDAO() {
		return userProfileOptsDAO;
	}

	public void setUserProfileOptsDAO(IUserProfileOptsDAO userProfileOptsDAO) {
		this.userProfileOptsDAO = userProfileOptsDAO;
	}
	
	/*public void  testGetAll(){
		userProfileOptsDAO.getAll();
	}*/
	
	public void testRemoveUserProfiles(){
		userProfileOptsDAO.removeOptsOfExistingUser(1l);
	}
	
}
