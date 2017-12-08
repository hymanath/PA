package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.model.PmRefCandidate;

@Repository
public class PmRefCandidateDAO extends GenericDaoHibernate<PmRefCandidate, Long> implements IPmRefCandidateDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRefCandidateDAO() {
		super(PmRefCandidate.class);
		
	}

	

}
