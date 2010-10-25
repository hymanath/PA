package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;

public class LocalElectionBodyWardDAOHibernateTest extends BaseDaoTestCase {

	public ILocalElectionBodyWardDAO localElectionBodyWardDAO;

	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}
	
	public void testGetAll(){
		localElectionBodyWardDAO.getAll();
	}
}
