package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocationNew;

public class GovtAlertDepartmentLocationNewDAO extends GenericDaoHibernate<GovtAlertDepartmentLocationNew, Long> {

	public GovtAlertDepartmentLocationNewDAO(){
		super(GovtAlertDepartmentLocationNew.class);
	}
}
