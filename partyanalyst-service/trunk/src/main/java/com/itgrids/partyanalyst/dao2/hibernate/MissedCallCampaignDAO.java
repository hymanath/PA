package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMissedCallCampaignDAO;
import com.itgrids.partyanalyst.model.MissedCallCampaign;



public class MissedCallCampaignDAO extends GenericDaoHibernate<MissedCallCampaign, Long> implements IMissedCallCampaignDAO{

	public MissedCallCampaignDAO() {
		super(MissedCallCampaign.class);
	}

}
