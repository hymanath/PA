package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;

public class ConstituencyLeadCandidateDAO  extends GenericDaoHibernate<ConstituencyLeadCandidate, Long> implements IConstituencyLeadCandidateDAO{
	public ConstituencyLeadCandidateDAO() {
		super(ConstituencyLeadCandidate.class);
	}
}
