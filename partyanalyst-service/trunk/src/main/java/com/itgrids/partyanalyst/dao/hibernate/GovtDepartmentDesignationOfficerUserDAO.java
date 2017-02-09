package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerUserDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerUser;

public class GovtDepartmentDesignationOfficerUserDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerUser, Long> implements IGovtDepartmentDesignationOfficerUserDAO{

	public GovtDepartmentDesignationOfficerUserDAO() {
		super(GovtDepartmentDesignationOfficerUser.class);
		
	}

}
