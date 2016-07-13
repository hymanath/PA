package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.model.Departments;

public class DepartmentsDAO extends GenericDaoHibernate<Departments, Long> implements IDepartmentsDAO{

	public DepartmentsDAO() {
		super(Departments.class);
		
	}

}
