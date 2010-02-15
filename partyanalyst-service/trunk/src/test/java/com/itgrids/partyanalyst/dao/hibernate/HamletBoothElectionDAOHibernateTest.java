package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAOHibernateTest extends BaseDaoTestCase{

	private IHamletBoothElectionDAO hamletBoothElectionDAO;

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	public void testGetAll(){
		List<HamletBoothElection> list = hamletBoothElectionDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}
	
}
