package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeMemberDAO;
import com.itgrids.partyanalyst.model.CommitteeMember;


public class CommitteeMemberDAO extends GenericDaoHibernate<CommitteeMember, Long> implements ICommitteeMemberDAO{

	public CommitteeMemberDAO() {
		super(CommitteeMember.class);
		// TODO Auto-generated constructor stub
	}

}