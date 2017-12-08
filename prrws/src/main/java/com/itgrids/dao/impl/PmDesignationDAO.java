package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDesignationDAO;
import com.itgrids.model.PmDesignation;

@Repository
public class PmDesignationDAO extends GenericDaoHibernate<PmDesignation, Long> implements IPmDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PmDesignationDAO() {
		super(PmDesignation.class);
		
	}

	

}
