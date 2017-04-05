package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLocationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScopeLocation;

public class GovtDepartmentScopeLocationDAO extends GenericDaoHibernate<GovtDepartmentScopeLocation, Long> implements IGovtDepartmentScopeLocationDAO{

	public GovtDepartmentScopeLocationDAO() {
		super(GovtDepartmentScopeLocation.class);
		
	}

}
