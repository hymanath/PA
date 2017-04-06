package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLevelDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScopeLevel;

public class GovtDepartmentScopeLevelDAO extends GenericDaoHibernate<GovtDepartmentScopeLevel, Long> implements IGovtDepartmentScopeLevelDAO{

	public GovtDepartmentScopeLevelDAO(){
		super(GovtDepartmentScopeLevel.class);
	}
}
