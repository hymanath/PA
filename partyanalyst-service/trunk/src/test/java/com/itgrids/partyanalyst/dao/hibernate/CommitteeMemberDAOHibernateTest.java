package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	
	public void test()
	{
		List<Object[]> list = committeeMemberDAO.getCommitteeCadreInfo(1l);
		System.out.println(list.size());
		
		
	}
	
}
