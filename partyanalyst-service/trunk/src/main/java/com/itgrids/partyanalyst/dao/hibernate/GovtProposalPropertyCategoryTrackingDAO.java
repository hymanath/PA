package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryTrackingDAO;
import com.itgrids.partyanalyst.model.GovtProposalPropertyCategoryTracking;

public class GovtProposalPropertyCategoryTrackingDAO extends GenericDaoHibernate<GovtProposalPropertyCategoryTracking, Long> implements IGovtProposalPropertyCategoryTrackingDAO{

	public GovtProposalPropertyCategoryTrackingDAO() {
		super(GovtProposalPropertyCategoryTracking.class);
	}

	public String getProposalStatusFrAlert(Long alertId){
		Query query = getSession().createQuery("select distinct model.govtProposalStatus.status" +
				" from GovtProposalPropertyCategoryTracking model" +
				" where model.isDeleted = 'N' and model.alert.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return (String) query.uniqueResult();
	}
}
