package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertStatus;

public class AlertStatusDAO extends GenericDaoHibernate<AlertStatus, Long>
		implements IAlertStatusDAO {
	public AlertStatusDAO() {
		super(AlertStatus.class);
	}

}
