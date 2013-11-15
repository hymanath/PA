package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.model.CandidatePartyCategory;

public class CandidatePartyCategoryDAO extends GenericDaoHibernate<CandidatePartyCategory, Long> implements ICandidatePartyCategoryDAO{

	public CandidatePartyCategoryDAO() {
		super(CandidatePartyCategory.class);
		
	}

}
