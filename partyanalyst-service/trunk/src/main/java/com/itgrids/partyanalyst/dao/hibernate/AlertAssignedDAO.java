package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.model.Activity;
import com.itgrids.partyanalyst.model.AlertAssigned;

public class AlertAssignedDAO extends GenericDaoHibernate<AlertAssigned, Long> implements IAlertAssignedDAO{

	public AlertAssignedDAO() {
		super(AlertAssigned.class);
		
	}
	
	
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId)
	{
		Query query = getSession().createQuery("select distinct model.tdpCadre.tdpCadreId from AlertAssigned model" +
				" where model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId in(:tdpCadreIds) and model.isDeleted ='N'");
		query.setParameter("alertId", alertId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	public List<Long> getDeleteAlertAssignedCandidates(Long alertId,Long tdpCadreId)
	{
		Query query = getSession().createQuery("select  model.alertAssignedId from AlertAssigned model" +
				" where model.isDeleted ='N' and  model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId =:tdpCadreId ");
		query.setParameter("alertId", alertId);
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidate(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId, model.tdpCadre.firstname"+
				" from AlertAssigned model where model.alert.isDeleted ='N' and model.isDeleted ='N' ");
		if(alertId != null && alertId > 0)
			str.append(" and  model.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertId != null && alertId > 0)
		query.setParameter("alertId", alertId);
		return query.list();
	}

}

