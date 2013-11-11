package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;

public class CandidatePartyKeywordDAO extends GenericDaoHibernate<CandidatePartyKeyword, Long> implements ICandidatePartyKeywordDAO{

	public CandidatePartyKeywordDAO() {
		super(CandidatePartyKeyword.class);
	}

	
}
