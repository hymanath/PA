package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficer;

public class GovtDepartmentDesignationOfficerDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficer, Long> implements IGovtDepartmentDesignationOfficerDAO{

	public GovtDepartmentDesignationOfficerDAO() {
		super(GovtDepartmentDesignationOfficer.class);
		
	}

}
