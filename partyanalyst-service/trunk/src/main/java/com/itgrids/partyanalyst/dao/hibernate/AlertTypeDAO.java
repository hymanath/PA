package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertType;

public class AlertTypeDAO extends GenericDaoHibernate<AlertType, Long>
		implements IAlertTypeDAO {
	public AlertTypeDAO() {
		super(AlertType.class);
	}

}
