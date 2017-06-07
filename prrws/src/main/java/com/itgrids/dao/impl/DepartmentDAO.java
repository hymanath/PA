package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.model.Department;

@Repository
public class DepartmentDAO extends GenericDaoHibernate<Department, Long> implements IDepartmentDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DepartmentDAO() {
		super(Department.class);

	}

}
