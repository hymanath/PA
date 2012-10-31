package com.itgrids.partyanalyst.social.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.social.dao.IPartySocialDAO;
import com.itgrids.partyanalyst.social.model.PartySocial;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartySocialDAO extends GenericDaoHibernate<PartySocial, Long> implements IPartySocialDAO{

	public PartySocialDAO() {
		super(PartySocial.class);
	} 
	public List<PartySocial> getNames()
	{
		
		Query query = getSession().createQuery("select model.party.shortName from PartySocial model where model.socialNetworkSite.socialNetworkId=1");
		return query.list();
	}
	
}
