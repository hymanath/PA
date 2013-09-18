package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.CrossVotingVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICrossVotingEstimationService {
	
	public List<SelectOptionVO> getConstituenciesForElectionYearAndScopeForBoothData(String electionYear, Long electionScopeId);
	
	public List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear);
	
	public List<SelectOptionVO> getPartiesForConstituencyAndElectionYearForBoothData(Long constituencyId, String electionYear);
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance);
	
	public List<SelectOptionVO> getConstituenciesForElectionYearAndTypeWithUserAccess(Long userId,Long electionYear,Long electionTypeId);
	
	public List<String> getElectionYearsForBoothResult();
	
	public List<SelectOptionVO> getAllOptions(String type,Long stateId,Long electionType,Long electionId);
	
	public List<SelectOptionVO> getPanchayatsForConstituencyList(List<Long> tehsilIds);
	
	public List<SelectOptionVO> getTehsilsForConstituencies(List<Long> constituenyIds);
	
	public List<SelectOptionVO> getBoothsForConstituencyList(List<Long> constituencyIds);
	
	public CrossVotingVO getElectionYearsForCrossVotingAnalysis(List<Long> assemblyIds);
	
	//public List<SelectOptionVO> getParliamentConstisByElectionYear(String year,List<Long> parliamentIds);
	
	//public List<SelectOptionVO> getAssemblyConstisByElectionYear(String year,Long parliamentId,Map<Long,List<SelectOptionVO>> parliamentMap);
	
	public List<SelectOptionVO> getAllElectionYearsForCrossVoting(List<Long> assemblyIds);
	
	public List<SelectOptionVO> getAllParliamentConstituenciesForCrossVoting(List<Long> assemblyIds,String electionYear);
	
	public List<SelectOptionVO> getAllAssemblyConstituenciesForCrossVoting(List<Long> assemblyIds,Long parliamentId,String electionYear);
	
	public List<SelectOptionVO> getUserAccessStateList(Long userId);
	
	public List<SelectOptionVO> getUserAccessElectionYears(Long electionTypeId,Long stateId,List<Long> constituencyIdsList);
	
	public List<SelectOptionVO> getConstituencyListByConstituenciesIds(Long electionId,List<Long> constituencyIdsList);
	
}
