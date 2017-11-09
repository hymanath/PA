package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeLocalBodyMemberDAO;
import com.itgrids.partyanalyst.model.JbCommitteeLocalBodyMember;

public class JbCommitteeLocalBodyMemberDAO extends GenericDaoHibernate<JbCommitteeLocalBodyMember, Long>
		implements IJbCommitteeLocalBodyMemberDAO {

	public JbCommitteeLocalBodyMemberDAO() {
		super(JbCommitteeLocalBodyMember.class);
		
	}

	
}
