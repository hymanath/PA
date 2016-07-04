package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingActionDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertTrackingAction;

public class AlertTrackingActionDAO extends
		GenericDaoHibernate<AlertTrackingAction, Long> implements
		IAlertTrackingActionDAO {
	public AlertTrackingActionDAO() {
		super(AlertTrackingAction.class);
	}

}
