package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserReferralEmailsDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserReferralEmailsDAOHibernateTest extends BaseDaoTestCase{
	private IUserReferralEmailsDAO userReferralEmailsDAO;
	public IUserReferralEmailsDAO getUserReferralEmailsDAO() {
		return userReferralEmailsDAO;
	}

	public void setUserReferralEmailsDAO(
			IUserReferralEmailsDAO userReferralEmailsDAO) {
		this.userReferralEmailsDAO = userReferralEmailsDAO;
	}

	public void test()
	{
		userReferralEmailsDAO.getAll();
	}
	
	/*public void testGetConnectedUsersCount()
	{
		List connectedCount = ananymousUserDAO.getConnectedUsersCount(19L, IConstants.DISTRICT);
		
		System.out.println(connectedCount);
	}*/
	
	/*public void testGetUserReferencedEmails()
	{
		List<String> list = userReferralEmailsDAO.getUserReferencedEmails(1l);
		System.out.println(list.get(0));
	}*/

	
}
