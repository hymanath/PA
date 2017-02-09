package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationDAO;
import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocation;

public class GovtAlertDepartmentLocationDAO extends GenericDaoHibernate<GovtAlertDepartmentLocation, Long> implements IGovtAlertDepartmentLocationDAO{

	public GovtAlertDepartmentLocationDAO() {
		super(GovtAlertDepartmentLocation.class);
		
	}

}
