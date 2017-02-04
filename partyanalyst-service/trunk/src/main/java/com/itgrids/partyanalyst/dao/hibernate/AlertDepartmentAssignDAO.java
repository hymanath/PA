package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.AlertDepartmentAssign;

public class AlertDepartmentAssignDAO extends GenericDaoHibernate<AlertDepartmentAssign, Long>{

	public AlertDepartmentAssignDAO() {
		super(AlertDepartmentAssign.class);
		
	}

}
