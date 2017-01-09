package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertClarificationCommentsDAO;
import com.itgrids.partyanalyst.model.AlertClarificationComments;

public class AlertClarificationCommentsDAO extends GenericDaoHibernate<AlertClarificationComments, Long> implements IAlertClarificationCommentsDAO{

	public AlertClarificationCommentsDAO() {
		super(AlertClarificationComments.class);
	}
	public List<Object[]> getClarificationComments(Long alertId){
		Query query = getSession().createQuery("select model.alertClarification.alertClarificationStatus.alertClarificationStatusId," +
				" model.alertClarification.alertClarificationStatus.status," +
				" model.comments," +
				" model.clarificationRequired" +
				" model.alertClarification.alert.alertId" +
				" from AlertClarificationComments model" +
				" where model.alertClarification.alert.alertId = :alertId" +
				" and model.isDeleted = 'N' and model.alertClarification.isDeleted ='N'");
		query.setParameter("alertId", alertId);
		return query.list();
	}

}
