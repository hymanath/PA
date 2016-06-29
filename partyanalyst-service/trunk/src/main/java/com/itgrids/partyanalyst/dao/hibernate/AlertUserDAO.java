package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertUserDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertUser;

public class AlertUserDAO extends GenericDaoHibernate<AlertUser, Long>
		implements IAlertUserDAO {
	public AlertUserDAO() {
		super(AlertUser.class);
	}

}
