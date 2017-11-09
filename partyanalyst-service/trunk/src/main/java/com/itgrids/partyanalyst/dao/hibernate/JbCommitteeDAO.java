package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.model.JbCommittee;

public class JbCommitteeDAO extends GenericDaoHibernate<JbCommittee, Long> implements IJbCommitteeDAO {

	public JbCommitteeDAO() {
		super(JbCommittee.class);
	}

	
}
