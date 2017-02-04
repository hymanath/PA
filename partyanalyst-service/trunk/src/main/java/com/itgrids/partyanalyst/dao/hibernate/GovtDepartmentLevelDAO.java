package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentLevel;

public class GovtDepartmentLevelDAO extends GenericDaoHibernate<GovtDepartmentLevel, Long> implements IGovtDepartmentLevelDAO{

	public GovtDepartmentLevelDAO() {
		super(GovtDepartmentLevel.class);
		
	}

}
