package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationStatusDAO;
import com.itgrids.model.PmDepartmentDesignationStatus;

@Repository
public class PmDepartmentDesignationStatusDAO extends GenericDaoHibernate<PmDepartmentDesignationStatus, Long>
		implements IPmDepartmentDesignationStatusDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	PmDepartmentDesignationStatusDAO(){
		super(PmDepartmentDesignationStatus.class);
	}
}
