package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.model.Departments;

public class DepartmentsDAO extends GenericDaoHibernate<Departments, Long> implements IDepartmentsDAO{

	public DepartmentsDAO() {
		super(Departments.class);
		
	}

	public List<Object[]> getDepartments(){
		Query query = getSession().createQuery(" select model.departmentId, model.deptName from Departments model ");
		return query.list();
	}
}
