package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertIssueCategoryDAO;
import com.itgrids.partyanalyst.model.AlertIssueCategory;

public class AlertIssueCategoryDAO extends GenericDaoHibernate<AlertIssueCategory, Long> implements IAlertIssueCategoryDAO{

	public AlertIssueCategoryDAO() {
		super(AlertIssueCategory.class);
	}

	public List<Object[]> getIssueCategoryDetailsOfAlertType(Long alertTypeId){
		
		Query query =getSession().createQuery(" select model.alertIssueCategoryId,model.issueCategory,model.categoryColor from AlertIssueCategory model " +
												" where model.alertTypeId =:alertTypeId " +
												" and model.isDeleted ='N' ");
		
		query.setParameter("alertTypeId", alertTypeId);
		
		return query.list();
	}
	
}
