package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryTrackingDAO;
import com.itgrids.partyanalyst.model.GovtProposalPropertyCategoryTracking;

public class GovtProposalPropertyCategoryTrackingDAO extends GenericDaoHibernate<GovtProposalPropertyCategoryTracking, Long> implements IGovtProposalPropertyCategoryTrackingDAO{

	public GovtProposalPropertyCategoryTrackingDAO() {
		super(GovtProposalPropertyCategoryTracking.class);
	}

}
