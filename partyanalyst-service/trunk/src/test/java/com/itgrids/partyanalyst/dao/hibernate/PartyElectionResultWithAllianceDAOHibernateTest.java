package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionResultWithAllianceDAO;

public class PartyElectionResultWithAllianceDAOHibernateTest extends
		BaseDaoTestCase {

	private IPartyElectionResultWithAllianceDAO partyElectionResultWithAllianceDAO;

	public IPartyElectionResultWithAllianceDAO getPartyElectionResultWithAllianceDAO() {
		return partyElectionResultWithAllianceDAO;
	}

	public void setPartyElectionResultWithAllianceDAO(
			IPartyElectionResultWithAllianceDAO partyElectionResultWithAllianceDAO) {
		this.partyElectionResultWithAllianceDAO = partyElectionResultWithAllianceDAO;
	}
	public void testGetAllElectionResults(){
		partyElectionResultWithAllianceDAO.getAll();		
	}
}
