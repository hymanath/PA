package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeStatusDAO;
import com.itgrids.partyanalyst.model.JbCommitteeStatus;

public class JbCommitteeStatusDAO extends GenericDaoHibernate<JbCommitteeStatus, Long> implements IJbCommitteeStatusDAO{

	public JbCommitteeStatusDAO() {
		super(JbCommitteeStatus.class);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
