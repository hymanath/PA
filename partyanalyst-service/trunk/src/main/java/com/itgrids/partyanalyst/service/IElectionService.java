package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;

public interface IElectionService {
	
	/*public List<SelectOptionVO> getAllElectionYearsBasedOnElectionType(String electionType);
	
	public List<SelectOptionVO> getElectionScopesByElectionType(Long electionTypeId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(Long electionScopeId);
		
	public List<SelectOptionVO> getAllElectionScopes();
	
	public List<SelectOptionVO> getAllElectionsInDistrict(Long districtId);

	public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId);

	public List<SelectOptionVO> getAllElectionTypes();
	
	public List<ElectionScope> getElectionScope(Long electionType);
	
	public List<SelectOptionVO> getElectionIdsAndYears(Long electionTypeId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,Long stateId);
	
	public List<String> getElectionYears(Long electionType);*/
	
	public List<CensusVO> getConstituencyCensusDetails(int selectIndex);
	
	public ElectionDataVO findAssemblyConstituenciesResultsByConstituencyIds(
			String electionYear, List<Long> constituencyIds);
	
}
