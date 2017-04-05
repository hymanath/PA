package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public class GovtDepartmentDesignationOfficerNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerNew, Long> implements IGovtDepartmentDesignationOfficerNewDAO {

	public GovtDepartmentDesignationOfficerNewDAO(){
		super(GovtDepartmentDesignationOfficerNew.class);
	}
}
