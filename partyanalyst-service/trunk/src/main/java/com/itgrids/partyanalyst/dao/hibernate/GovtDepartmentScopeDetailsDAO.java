package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDetailsDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScopeDetails;

public class GovtDepartmentScopeDetailsDAO extends GenericDaoHibernate<GovtDepartmentScopeDetails, Long> implements IGovtDepartmentScopeDetailsDAO{

	public GovtDepartmentScopeDetailsDAO() {
		super(GovtDepartmentScopeDetails.class);
		
	}

}
