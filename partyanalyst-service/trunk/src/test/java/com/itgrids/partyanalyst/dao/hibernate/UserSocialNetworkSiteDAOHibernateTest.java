package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserSocialNetworkSiteDAO;
import com.itgrids.partyanalyst.model.UserSocialNetworkSite;

public class UserSocialNetworkSiteDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserSocialNetworkSiteDAO userSocialNetworkSiteDAO;

	

	public void setUserSocialNetworkSiteDAO(
			IUserSocialNetworkSiteDAO userSocialNetworkSiteDAO) {
		this.userSocialNetworkSiteDAO = userSocialNetworkSiteDAO;
	}
	
	public void testGetTwitterIdByPartyName(){
		List list=userSocialNetworkSiteDAO.getTwitterIdByPartyName("bjp");
		System.out.println(list);
		
	}
	

}
