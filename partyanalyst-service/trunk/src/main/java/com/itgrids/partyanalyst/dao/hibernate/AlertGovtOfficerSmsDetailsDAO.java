package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertGovtOfficerSmsDetailsDAO;
import com.itgrids.partyanalyst.model.AlertGovtOfficerSmsDetails;

public class AlertGovtOfficerSmsDetailsDAO extends GenericDaoHibernate<AlertGovtOfficerSmsDetails, Long> implements IAlertGovtOfficerSmsDetailsDAO {

	public AlertGovtOfficerSmsDetailsDAO(){
		super(AlertGovtOfficerSmsDetails.class);
	}

}
