package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertFeedbackStatusDAO;
import com.itgrids.partyanalyst.model.AlertFeedbackStatus;

public class AlertFeedbackStatusDAO extends GenericDaoHibernate<AlertFeedbackStatus, Long> implements IAlertFeedbackStatusDAO{

	public AlertFeedbackStatusDAO() {
		super(AlertFeedbackStatus.class);
	}

}
