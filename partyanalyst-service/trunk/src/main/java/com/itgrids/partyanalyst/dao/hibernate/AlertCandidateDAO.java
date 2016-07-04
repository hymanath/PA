package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;

public class AlertCandidateDAO extends
		GenericDaoHibernate<AlertCandidate, Long> implements IAlertCandidateDAO {
	public AlertCandidateDAO() {
		super(AlertCandidate.class);
	}
	public List<Object[]> getAlertCandidateCount(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.candidateId),model.alert.alertId" +
				" from AlertCandidate model where model.alert.isDeleted ='N' ");
		if(alertIds != null && alertIds.size() > 0)
			str.append(" and model.alert.alertId in(:alertIds)");
		str.append(" group by model.alert.alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		return query.list();
	}
}
