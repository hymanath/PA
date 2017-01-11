package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IClarificationRequiredDAO;
import com.itgrids.partyanalyst.model.ClarificationRequired;

public class ClarificationRequiredDAO extends GenericDaoHibernate<ClarificationRequired, Long> implements IClarificationRequiredDAO{

	public ClarificationRequiredDAO() {
		super(ClarificationRequired.class);
		
	}
	
	public String getDetails(Long alertId){
		
		Query query = getSession().createQuery(" select model.isRequired " +
				" from ClarificationRequired model " +
				" where model.alertId = :alertId and model.isDeleted='N' and model.alert.isDeleted='N' ");
		
		query.setParameter("alertId", alertId);
		
		return (String)query.uniqueResult();
	}

}
