package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;

public interface ICandidateBoothResultDAO extends GenericDao<CandidateBoothResult, Long>{

	public List<CandidateBoothResult> findByProperty(Object value);
	
	public List<CandidateBoothResult> findByNominationAndBoothConstituencyElection(Long nominationId, Long boothConstituencyElectionId);
	
	public List<CandidateBoothResult> findByBoothElectionScopeAndParty(Long boothId, String electionYear, Long electionScopeId, Long partyId);
	
	public List<CandidateBoothResult> findByConstituencyElection(Long constituencyElectionId);
	
	public List<Party> findPartiesByConstituencyAndElectionYear(Long constituencyId, String electionYear);
	
	public List findPartyElectionResultForMandal(Long tehsilID,String electionTypeIDs, String electionYears);
	
	public List findPartyElectionResultForRevenueVillage(Long revenueVillageID,String electionTypeIDs, String electionYears);
	
	public List findBoothWisePartiesAllElectionResultsByBooth(Long boothId);
	
	public List getPartyGenderWiseBoothVotesForMandal(Long tehsilID);
	
	public List getElectionPartyResultsForTownship(Long electionID, Long townshipID);

	public List findMandalWisePartiesResultsForElection(Long tehsilId, Long electionId);
	
	public List findTownshipElectionResult(Long townshipId, Long electionId);
	
	public List findPartyResultsForBooths(String boothConstituencyElectionIds);
}
