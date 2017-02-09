package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;

public class AlertAssignedOfficerTrackingDAO extends GenericDaoHibernate<AlertAssignedOfficerTracking, Long> implements IAlertAssignedOfficerTrackingDAO{

	public AlertAssignedOfficerTrackingDAO() {
		super(AlertAssignedOfficerTracking.class);
		
	}

}
