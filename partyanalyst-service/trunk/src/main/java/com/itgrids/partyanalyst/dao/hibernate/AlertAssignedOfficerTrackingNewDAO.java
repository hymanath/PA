package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;

public class AlertAssignedOfficerTrackingNewDAO extends GenericDaoHibernate<AlertAssignedOfficerTrackingNew, Long> implements
		IAlertAssignedOfficerTrackingNewDAO {
    public  AlertAssignedOfficerTrackingNewDAO(){
    	   super(AlertAssignedOfficerTrackingNew.class);
       }
}
