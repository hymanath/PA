package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationDAO;
import com.itgrids.model.PmDepartmentDesignation;

@Repository
public class PmDepartmentDesignationDAO extends GenericDaoHibernate<PmDepartmentDesignation, Long> implements IPmDepartmentDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PmDepartmentDesignationDAO(){
		super(PmDepartmentDesignation.class);
	}
}
