package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignation;

public class GovtDepartmentDesignationDAO extends GenericDaoHibernate<GovtDepartmentDesignation, Long> implements IGovtDepartmentDesignationDAO{

	public GovtDepartmentDesignationDAO() {
		super(GovtDepartmentDesignation.class);
		
	}

}
