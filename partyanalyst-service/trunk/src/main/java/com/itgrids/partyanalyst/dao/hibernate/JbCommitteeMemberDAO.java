package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeMemberDAO;
import com.itgrids.partyanalyst.model.JbCommitteeMember;

public class JbCommitteeMemberDAO extends GenericDaoHibernate<JbCommitteeMember, Long> implements
		IJbCommitteeMemberDAO {

	public JbCommitteeMemberDAO() {
		super(JbCommitteeMember.class);
		
	}

	
}
