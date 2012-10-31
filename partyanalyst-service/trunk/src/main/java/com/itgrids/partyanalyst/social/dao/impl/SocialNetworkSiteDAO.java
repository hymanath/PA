package com.itgrids.partyanalyst.social.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.social.dao.ISocialNetworkSiteDAO;
import com.itgrids.partyanalyst.social.model.SocialNetworkSite;

public class SocialNetworkSiteDAO extends GenericDaoHibernate<SocialNetworkSite, Long> implements ISocialNetworkSiteDAO{

	public SocialNetworkSiteDAO(){
		super(SocialNetworkSite.class);
	}
	
	
}
