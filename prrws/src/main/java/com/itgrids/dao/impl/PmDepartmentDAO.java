package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDAO;
import com.itgrids.model.PmDepartment;
@Repository
public class PmDepartmentDAO extends GenericDaoHibernate<PmDepartment, Long> implements IPmDepartmentDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDAO() {
		super(PmDepartment.class);
		
	}

	
}
