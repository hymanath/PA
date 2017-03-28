package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertSeverity;

public class AlertSeverityDAO extends GenericDaoHibernate<AlertSeverity, Long>
		implements IAlertSeverityDAO {
	public AlertSeverityDAO() {
		super(AlertSeverity.class);
	}

}
