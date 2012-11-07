package com.itgrids.partyanalyst.social.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.social.dao.ISocialNetworkSiteDAO;
import com.itgrids.partyanalyst.social.model.SocialNetworkSite;

public class SocialNetworkSiteDAO extends GenericDaoHibernate<SocialNetworkSite, Long> implements ISocialNetworkSiteDAO{

	public SocialNetworkSiteDAO(){
		super(SocialNetworkSite.class);
	}
	
	@SuppressWarnings("unchecked")
	
	public Long getSocialNetworkIdByName(String name)
	{
		
	Query query = getSession().createQuery("select model.socialNetworkId from SocialNetworkSite model where model.name=?");
	query.setParameter(0, name);
	return (Long)query.uniqueResult();
	}
}
