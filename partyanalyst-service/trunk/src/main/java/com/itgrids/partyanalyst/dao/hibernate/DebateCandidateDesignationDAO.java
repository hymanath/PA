package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateCandidateDesignationDAO;
import com.itgrids.partyanalyst.model.DebateCandidateDesignation;

public class DebateCandidateDesignationDAO extends GenericDaoHibernate<DebateCandidateDesignation, Long> implements IDebateCandidateDesignationDAO{

	public DebateCandidateDesignationDAO() {
		super(DebateCandidateDesignation.class);
		// TODO Auto-generated constructor stub
	}

	
	
}
