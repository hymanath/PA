package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;

public class PartyElectionStateResultWithAllianceDAOHibernateTest extends
		BaseDaoTestCase {

	private IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO;

	public IPartyElectionStateResultWithAllianceDAO getPartyElectionStateResultWithAllianceDAO() {
		return partyElectionStateResultWithAllianceDAO;
	}

	public void setPartyElectionStateResultWithAllianceDAO(
			IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO) {
		this.partyElectionStateResultWithAllianceDAO = partyElectionStateResultWithAllianceDAO;
	}

	public void testGetAllElectionResultsInState(){
		partyElectionStateResultWithAllianceDAO.getAll();		
	}
}
