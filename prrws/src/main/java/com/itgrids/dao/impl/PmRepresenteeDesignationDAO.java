package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.model.PmRepresenteeDesignation;

@Repository
public class PmRepresenteeDesignationDAO extends GenericDaoHibernate<PmRepresenteeDesignation, Long> implements IPmRepresenteeDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeDesignationDAO() {
		super(PmRepresenteeDesignation.class);
		
	}

	

}
