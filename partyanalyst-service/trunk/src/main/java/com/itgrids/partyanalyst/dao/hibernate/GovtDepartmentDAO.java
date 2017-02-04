package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.model.GovtDepartment;

public class GovtDepartmentDAO extends GenericDaoHibernate<GovtDepartment, Long> implements IGovtDepartmentDAO{

	public GovtDepartmentDAO() {
		super(GovtDepartment.class);
		
	}

}
