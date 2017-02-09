package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficer;

public class AlertAssignedOfficerDAO extends GenericDaoHibernate<AlertAssignedOfficer, Long> implements IAlertAssignedOfficerDAO{

	public AlertAssignedOfficerDAO() {
		super(AlertAssignedOfficer.class);
		
	}

}
