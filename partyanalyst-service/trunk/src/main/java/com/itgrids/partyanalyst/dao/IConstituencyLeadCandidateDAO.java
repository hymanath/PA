package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;

public interface IConstituencyLeadCandidateDAO  extends GenericDao<ConstituencyLeadCandidate, Long>{
	
	@SuppressWarnings("unchecked")
	public List getCountOfOldConstituenciesInAElection(Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getCountOfDelimitedConstituenciesInAElection(Long electionId);
	
	public List getLeadingConstituenciesCount(Long electionId);

	public List<Object[]> getPartyLeadingOrWinningConstituencies(Long electionId);
	
	
	public List<Object> getCandidateResultStatus(Long candidateId,Long constiElecId);
	
	public List<Object> checkCandidateResultExist(Long constiElecId);
	
	public List getElectionIds(Long electionId);
	
	public List<Object[]> getPartiesLeadingInfo(Long electionId);
	
	public List<Object[]> getPartiesinfoInSpecifiedConstituencies(Long electionId,List<Long> constituenciesList);
	
	public List<Object[]> getPartiesWonCountInSpecifiedConstituencies(Long electionId,List<Long> constituenciesList);
	
	public List<Object[]> getPartyWinConstituencies(Long partyId,Long electionId,List<Long> constituenciesList);
		
	public List<Object[]> getCandidateResultsForPartialElec(Long candidateId,Long electionId);
	
	public List<Object[]> getConstituencyWiseCandidatesStates(Long electionId);
	
	public Object getResultKnownConstituenciesCountInAElection(Long electionId);
	
	public List<Long> getTotalResultsKnown(Long electionId,List<Long> constituenciesList);
	
	public List<Object[]> getPartyWinConst(Long partyId,Long electionId,List<Long> constituenciesList);	
	
	public List<Object[]> getGenderAnalysisElectionresults(Long electionId) ;
	
	public List<Long> getOtherPartiesWinCount(List<Long> partyIds,Long electionId,List<Long> constituenciesList);
	
	public List<Long> getTotalKnownResultsOfADistrict(Long electionId,List<Long> constituenciesList);
	
	public Object getWonLeadConstituenciesCountInAElection(Long electionId,String type);
	
	public List<Object[]> getAllParties(Long electionId);
	
	public List<Object[]> getPartiesPartispatedCount(Long electionId);
	
	public List<Object[]> getResultsKnownConstituenciesInDistrict(Long electionId,Long districtId);
	
	public List<Object[]> getAllWonLeadCandidates(Long electionId);
	
}
