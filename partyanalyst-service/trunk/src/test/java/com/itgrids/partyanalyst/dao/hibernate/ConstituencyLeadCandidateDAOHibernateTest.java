package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;

public class ConstituencyLeadCandidateDAOHibernateTest  extends BaseDaoTestCase {
	private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;

	public void setConstituencyLeadCandidateDAO(
			IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO) {
		this.constituencyLeadCandidateDAO = constituencyLeadCandidateDAO;
	}
	public void test()
	{
		constituencyLeadCandidateDAO.getAll();
	}
}
