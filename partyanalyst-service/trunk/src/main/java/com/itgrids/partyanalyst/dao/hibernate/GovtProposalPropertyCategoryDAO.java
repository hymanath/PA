package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryDAO;
import com.itgrids.partyanalyst.model.GovtProposalPropertyCategory;

public class GovtProposalPropertyCategoryDAO extends GenericDaoHibernate<GovtProposalPropertyCategory, Long> implements IGovtProposalPropertyCategoryDAO{

	public GovtProposalPropertyCategoryDAO() {
		super(GovtProposalPropertyCategory.class);
	}
	
	public GovtProposalPropertyCategory getExistingStatusByAlertId(Long alertId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model" +
				" from GovtProposalPropertyCategory model" +
				" where model.isDeleted = 'N' ");
		if(alertId != null && alertId.longValue() >0l){
			sb.append(" and model.alert.alertId = :alertId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(alertId != null && alertId.longValue() >0l)
			query.setParameter("alertId", alertId);
		
		return (GovtProposalPropertyCategory) query.uniqueResult();
	}
	public List<Object[]> getStatusFrALert(Long alertId){
		Query query = getSession().createQuery("select distinct model.govtProposalStatus.govtProposalStatusId," +
				" model.govtProposalStatus.status" +
				" from GovtProposalPropertyCategory model" +
				" where model.isDeleted = 'N' and model.alert.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return query.list();
	}
  
}
