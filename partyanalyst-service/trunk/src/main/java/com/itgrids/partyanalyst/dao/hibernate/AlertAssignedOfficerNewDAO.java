package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;

public class AlertAssignedOfficerNewDAO extends GenericDaoHibernate<AlertAssignedOfficerNew, Long> implements
		IAlertAssignedOfficerNewDAO {
	
        public AlertAssignedOfficerNewDAO(){
        	super(AlertAssignedOfficerNew.class);
        }
}
