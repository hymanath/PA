package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScope;

public class GovtDepartmentScopeDAO extends GenericDaoHibernate<GovtDepartmentScope, Long> implements IGovtDepartmentScopeDAO{

	public GovtDepartmentScopeDAO() {
		super(GovtDepartmentScope.class);
		
	}
 }
