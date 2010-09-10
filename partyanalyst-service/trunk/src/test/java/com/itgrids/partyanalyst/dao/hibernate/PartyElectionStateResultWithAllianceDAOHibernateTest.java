package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;

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
	
	public void testGet(){
		
		java.util.List validVotesWhenAllianceConsidered = partyElectionStateResultWithAllianceDAO.getPartyResultsByStateIdAndElectionId(1l,24l);
		for(int i=0;i<validVotesWhenAllianceConsidered.size();i++){
			Object[] parms = (Object[])validVotesWhenAllianceConsidered.get(i);
			ElectionInfoVO electionInfo = new ElectionInfoVO();
			Double votes = Double.parseDouble(parms[2].toString());
			
			electionInfo.setParticipatedConstituencies(new Long(parms[1].toString()));
			electionInfo.setTotalValidVotes(votes.longValue());
			electionInfo.setVotesPercentage(new Double(parms[3].toString()));
			electionInfo.setCompleteVotesPercentage(new Double(parms[4].toString()));
			System.out.println("State Id-->"+Long.parseLong(parms[0].toString())+"<----Votes-->"+parms[2].toString());
			
		}
	}
}
