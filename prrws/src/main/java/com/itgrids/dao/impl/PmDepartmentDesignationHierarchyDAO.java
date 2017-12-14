package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationHierarchyDAO;
import com.itgrids.model.PmDepartmentDesignationHierarchy;

@Repository
public class PmDepartmentDesignationHierarchyDAO extends GenericDaoHibernate<PmDepartmentDesignationHierarchy, Long> implements IPmDepartmentDesignationHierarchyDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PmDepartmentDesignationHierarchyDAO(){
		super(PmDepartmentDesignationHierarchy.class);
	}
}
