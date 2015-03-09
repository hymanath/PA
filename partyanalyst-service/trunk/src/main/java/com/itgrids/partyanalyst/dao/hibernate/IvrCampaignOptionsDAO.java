package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrCampaignOptionsDAO;
import com.itgrids.partyanalyst.model.IvrCampaignOptions;


public class IvrCampaignOptionsDAO extends GenericDaoHibernate<IvrCampaignOptions, Long> implements IIvrCampaignOptionsDAO{

	public IvrCampaignOptionsDAO() {
		super(IvrCampaignOptions.class);
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllIVROptions(Long campaignId)
	{
		Query query = getSession().createQuery("select distinct model.ivrOptions.ivrOptionsId , model.ivrOptions.optionName from IvrCampaignOptions model  " +
				"  where model.ivrCampaign.ivrCampaignId = :campaignId ");
		query.setParameter("campaignId", campaignId);
		return query.list();
	}
}
