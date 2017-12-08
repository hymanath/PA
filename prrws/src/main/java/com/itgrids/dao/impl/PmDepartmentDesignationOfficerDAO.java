package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationOfficerDAO;
import com.itgrids.model.PmDepartmentDesignationOfficer;

@Repository
public class PmDepartmentDesignationOfficerDAO extends GenericDaoHibernate<PmDepartmentDesignationOfficer, Long>implements IPmDepartmentDesignationOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDesignationOfficerDAO() {
		super(PmDepartmentDesignationOfficer.class);
		// TODO Auto-generated constructor stub
	}

	

}
