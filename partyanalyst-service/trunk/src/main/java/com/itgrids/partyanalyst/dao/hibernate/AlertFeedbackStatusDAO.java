package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertFeedbackStatusDAO;
import com.itgrids.partyanalyst.model.AlertFeedbackStatus;

public class AlertFeedbackStatusDAO extends GenericDaoHibernate<AlertFeedbackStatus, Long> implements IAlertFeedbackStatusDAO{

	public AlertFeedbackStatusDAO() {
		super(AlertFeedbackStatus.class);
	}
	
	public List<Object[]> getFeedBackStatus(){
		Query query = getSession().createQuery("select " +
				" model.alertFeedbackStatusId," +
				" model.status " +
				" from AlertFeedbackStatus model " +
				" where model.isDeleted = 'N' order by model.orderNo ");
		return query.list();
	}
	public List<Object[]> getjalavaniFeedBackStatus(){
		Query query = getSession().createQuery("select " +
				" model.alertFeedbackStatusId," +
				" model.status " +
				" from AlertFeedbackStatus model " +
				" where model.isDeleted = 'N' order by model.jalavaniOrderNo ");
		return query.list();
	}

}
