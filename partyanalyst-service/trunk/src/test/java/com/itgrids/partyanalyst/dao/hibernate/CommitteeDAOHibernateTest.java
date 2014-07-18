package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICommitteeDAO;

public class CommitteeDAOHibernateTest extends BaseDaoTestCase{
	private ICommitteeDAO committeeDAO;

	public void setCommitteeDAO(ICommitteeDAO committeeDAO) {
		this.committeeDAO = committeeDAO;
	}
	

}
