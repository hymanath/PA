package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.model.NominationPostCandidate;

public class NominationPostCandidateDAO extends GenericDaoHibernate<NominationPostCandidate, Long> implements INominationPostCandidateDAO{

	public NominationPostCandidateDAO() {
		super(NominationPostCandidate.class);
		// TODO Auto-generated constructor stub
	}

}
