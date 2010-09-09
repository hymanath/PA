package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;

public class PartyElectionDistrictResultWithAllianceDAOHibernateTest extends
		BaseDaoTestCase {

	private IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO;

	public IPartyElectionDistrictResultWithAllianceDAO getPartyElectionDistrictResultWithAllianceDAO() {
		return partyElectionDistrictResultWithAllianceDAO;
	}

	public void setPartyElectionDistrictResultWithAllianceDAO(
			IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO) {
		this.partyElectionDistrictResultWithAllianceDAO = partyElectionDistrictResultWithAllianceDAO;
	}

	public void testGetAllElectionResultsInDistrict(){
		 partyElectionDistrictResultWithAllianceDAO.getAll();		
	}
	
}
