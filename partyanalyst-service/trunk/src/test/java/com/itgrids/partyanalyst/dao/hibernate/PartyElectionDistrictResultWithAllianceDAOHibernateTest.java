package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

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

	/*public void testGetAllElectionResultsInDistrict(){
		 partyElectionDistrictResultWithAllianceDAO.getAll();		
	}
	
	public void testGetPartiesByDistrict(){
		List list = partyElectionDistrictResultWithAllianceDAO.getPartyResultsForADistrictByPartyIdAndElectionId(3l,24l, 1l);
		System.out.println(list.size());
	}*/
	
	public void testFindDistrictWiseElectionResultsForStatePartyAndElection(){
		List list = partyElectionDistrictResultWithAllianceDAO.findDistrictWiseElectionResultsForStatePartyAndElection(361l, 1l, 3l);
		System.out.println(list.size());
	}
}
