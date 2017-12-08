package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmOfficerDesignationDAO;
import com.itgrids.model.PmOfficerDesignation;

@Repository
public class PmOfficerDesignationDAO extends GenericDaoHibernate<PmOfficerDesignation, Long> implements IPmOfficerDesignationDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	PmOfficerDesignationDAO(){
		super(PmOfficerDesignation.class);
	}
}
