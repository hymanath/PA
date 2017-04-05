package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationHierarchy;

public class GovtDepartmentDesignationHierarchyDAO extends GenericDaoHibernate<GovtDepartmentDesignationHierarchy, Long> implements IGovtDepartmentDesignationHierarchyDAO {

	public GovtDepartmentDesignationHierarchyDAO(){
		super(GovtDepartmentDesignationHierarchy.class);
	}
}
