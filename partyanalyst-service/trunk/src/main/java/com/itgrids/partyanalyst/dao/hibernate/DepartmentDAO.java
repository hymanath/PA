package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;
import com.itgrids.partyanalyst.model.Department;

public class DepartmentDAO extends GenericDaoHibernate<Department, Long> implements IDepartmentDAO {
	public DepartmentDAO(){
		super(Department.class);
	}
}
