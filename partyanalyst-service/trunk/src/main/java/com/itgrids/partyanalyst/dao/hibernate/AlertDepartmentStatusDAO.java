package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentStatus;

public class AlertDepartmentStatusDAO extends GenericDaoHibernate<AlertDepartmentStatus, Long> implements IAlertDepartmentStatusDAO{

	public AlertDepartmentStatusDAO() {
		super(AlertDepartmentStatus.class);
		
	}

}
