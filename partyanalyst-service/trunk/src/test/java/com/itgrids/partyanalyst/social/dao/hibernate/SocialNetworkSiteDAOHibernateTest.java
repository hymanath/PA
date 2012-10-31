package com.itgrids.partyanalyst.social.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.social.dao.ISocialNetworkSiteDAO;

public class SocialNetworkSiteDAOHibernateTest extends BaseDaoTestCase{

	private ISocialNetworkSiteDAO socialNetworkSiteDAO;
	
	public void setSocialNetworkSiteDAO(ISocialNetworkSiteDAO socialNetworkSiteDAO) {
		this.socialNetworkSiteDAO = socialNetworkSiteDAO;
	}
	
	public void test()
	{
		socialNetworkSiteDAO.getAll();
	}
}
