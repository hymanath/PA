package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.model.CandidateNewsResponse;

public class CandidateNewsResponseDAO extends GenericDaoHibernate<CandidateNewsResponse, Long> implements  ICandidateNewsResponseDAO{
	public CandidateNewsResponseDAO() {
		super(CandidateNewsResponse.class);
	}

}
