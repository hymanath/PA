package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICommitteeMemberDAO;

public class CommitteeMemberDAOHibernateTest  extends BaseDaoTestCase{
	private ICommitteeMemberDAO committeeMemberDAO;

	public ICommitteeMemberDAO getCommitteeMemberDAO() {
		return committeeMemberDAO;
	}

	public void setCommitteeMemberDAO(ICommitteeMemberDAO committeeMemberDAO) {
		this.committeeMemberDAO = committeeMemberDAO;
	}
	
	
}
