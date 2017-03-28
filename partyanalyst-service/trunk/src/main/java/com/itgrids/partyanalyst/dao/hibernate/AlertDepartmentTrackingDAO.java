package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentTrackingDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentTracking;

public class AlertDepartmentTrackingDAO extends GenericDaoHibernate<AlertDepartmentTracking, Long> implements IAlertDepartmentTrackingDAO{

	public AlertDepartmentTrackingDAO() {
		super(AlertDepartmentTracking.class);
		
	}

}
