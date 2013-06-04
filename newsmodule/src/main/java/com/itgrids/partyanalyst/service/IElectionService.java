package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.CensusWisePartyResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IElectionService {/*
	
	spublic List<SelectOptionVO> getAllElectionYearsBasedOnElectionType(String electionType);
	
	public List<SelectOptionVO> getElectionScopesByElectionType(Long electionTypeId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(Long electionScopeId);
		
	public List<SelectOptionVO> getAllElectionScopes();
	
	public List<SelectOptionVO> getAllElectionsInDistrict(Long districtId);

	public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId);

	public List<SelectOptionVO> getAllElectionTypes();
	
	public List<ElectionScope> getElectionScope(Long electionType);
	
	public List<SelectOptionVO> getElectionIdsAndYears(Long electionTypeId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,Long stateId);
	
	public List<String> getElectionYears(Long electionType);
	
	public List<CensusVO> getConstituencyCensusDetails(Integer selectIndex,Long stateId,Long districtId,Long year,String level);
	
	public ElectionDataVO findAssemblyConstituenciesResultsByConstituencyIds(
			String electionYear, List<Long> constituencyIds, List<Long> partyIds, 
			List<Long> districtIds, Integer selected, Boolean isAll, Boolean includeConstiInfo);
	
	public ResultWithExceptionVO getPartywiseConstituenciesResultsForCensusInfo(Integer selectIndex,Long stateId,Long districtId,
			Long year,String level, Long partyId);
	
	public List<SelectOptionVO> getAllCensusParameters();
	
	public List<CensusWisePartyResultsVO> findAllPartiesInfoByCensusRanges(Integer selectIndex,Long stateId,Long districtId,
			Long year,String level);
	
	public List<SelectOptionVO> getLatestElectionYearForAStateBasedOnElectionType(Long stateId, String electionType, String subType);
	
*/}
