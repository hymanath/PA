package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrCampaignDAO;
import com.itgrids.partyanalyst.model.IvrCampaign;


public class IvrCampaignDAO extends GenericDaoHibernate<IvrCampaign, Long> implements IIvrCampaignDAO{

	public IvrCampaignDAO() {
		super(IvrCampaign.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCampaigns()
	{
		Query query = getSession().createQuery("select distinct model.ivrCampaignId , model.description from IvrCampaign model  " +
				"  where model.isDeleted = 'N' order by model.ivrCampaignId desc");
		
		return query.list();
	}
	
}
