package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.model.PmRefCandidateDesignation;
@Repository
public class PmRefCandidateDesignationDAO extends GenericDaoHibernate<PmRefCandidateDesignation, Long> implements IPmRefCandidateDesignationDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRefCandidateDesignationDAO() {
		super(PmRefCandidateDesignation.class);
		
	}

	
	
}
