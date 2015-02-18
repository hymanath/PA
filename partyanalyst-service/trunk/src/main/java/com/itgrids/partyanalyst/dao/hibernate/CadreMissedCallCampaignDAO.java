package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreMissedCallCampaignDAO;
import com.itgrids.partyanalyst.model.CadreMissedCallCampaign;



public class CadreMissedCallCampaignDAO extends GenericDaoHibernate<CadreMissedCallCampaign, Long> implements ICadreMissedCallCampaignDAO{

	public CadreMissedCallCampaignDAO() {
		super(CadreMissedCallCampaign.class);
	}


}
