package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationLocationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationLocation;

public class GovtDepartmentDesignationLocationDAO extends GenericDaoHibernate<GovtDepartmentDesignationLocation, Long> implements IGovtDepartmentDesignationLocationDAO{

	public GovtDepartmentDesignationLocationDAO() {
		super(GovtDepartmentDesignationLocation.class);
		
	}

}
