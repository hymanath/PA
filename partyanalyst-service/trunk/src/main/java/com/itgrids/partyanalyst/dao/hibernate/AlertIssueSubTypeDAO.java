package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertIssueSubTypeDAO;
import com.itgrids.partyanalyst.model.AlertIssueSubType;

public class AlertIssueSubTypeDAO extends GenericDaoHibernate<AlertIssueSubType,Long> implements IAlertIssueSubTypeDAO{

	public AlertIssueSubTypeDAO()
	{
		super(AlertIssueSubType.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubTypesByAlertIssueType(Long alertIssueTypeId)
	{
		Query query = getSession().createQuery("SELECT model.alertIssueSubTypeId,model.issueType FROM AlertIssueSubType model WHERE " +
				" model.alertIssueType.alertIssueTypeId = :alertIssueTypeId order by model.orderNo");
		query.setParameter("alertIssueTypeId",alertIssueTypeId);
		return query.list();
	}
}
