package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICommitteeRoleDAO;

public class CommitteeRoleDAOHibernateTest  extends BaseDaoTestCase{
	
	private ICommitteeRoleDAO committeeRoleDAO;

	public ICommitteeRoleDAO getCommitteeRoleDAO() {
		return committeeRoleDAO;
	}

	public void setCommitteeRoleDAO(ICommitteeRoleDAO committeeRoleDAO) {
		this.committeeRoleDAO = committeeRoleDAO;
	}
	
	
}
