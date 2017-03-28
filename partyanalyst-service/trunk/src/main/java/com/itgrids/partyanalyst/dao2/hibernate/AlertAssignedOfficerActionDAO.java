package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerActionDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerAction;

public class AlertAssignedOfficerActionDAO extends GenericDaoHibernate<AlertAssignedOfficerAction, Long> implements IAlertAssignedOfficerActionDAO{

	public AlertAssignedOfficerActionDAO() {
		super(AlertAssignedOfficerAction.class);
		
	}

}
