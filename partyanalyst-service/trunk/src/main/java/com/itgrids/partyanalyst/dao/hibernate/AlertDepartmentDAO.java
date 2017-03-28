package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentDAO;
import com.itgrids.partyanalyst.model.AlertDepartment;

public class AlertDepartmentDAO extends GenericDaoHibernate<AlertDepartment, Long> implements IAlertDepartmentDAO{

	public AlertDepartmentDAO() {
		super(AlertDepartment.class);
		
	}

}
