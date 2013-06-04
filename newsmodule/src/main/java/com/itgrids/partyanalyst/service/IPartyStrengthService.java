package com.itgrids.partyanalyst.service;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ContenetTransferVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.PartiesStrenghInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPartyStrengthService extends Serializable {/*

	public PartiesStrenghInfoVO segregateAllConstituencies(Long selectedNoOfYears,String electionType,String electionSubType,Long stateId);
	
	public List<Long> getAllElectionYears(String electionType,Long stateId);
	
	public ElectionInfoVO getPartiesData(String electionType,Long stateId,Long electionYearsCount,Long partyId,String alliance,Long electionId,String partyName);
	
	public ConstituencyElectionResults getAllElectionData(List<Long> constituencyIds,List result,Long count,String type,String electionType,Long stateId);
	
	public ConstituencyElectionResults setElectionDataInToVo(List<Long> constituencyIds,List result);
	
	public PartiesDetailsVO setDataIntoPartiesDetailsVO(Object[] parms);
	
	public List<SelectOptionVO> getAllStatesHavinElectionData(String electionType);
	
	public List<SelectOptionVO> getCountOfElectionYears(Long stateId,String electionType);
	
	public List<SelectOptionVO> getAllPartiesData(Long stateId);
	
	public ConstituencyElectionResults getPartyStrengthsAndWeaknessDetails(String electionType,Long stateId,Long partieId,Long totalElectionYears,List result,List<Long> requiredConstituencies);
	
	public List<PartiesDetailsVO> getAllConstituenciesData(String electionType,Long stateId,String partyName,Long selectedNoOfYears,Long count,String excludeType);
	
	public ContenetTransferVO getIncludingAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,Long electionId,String partyName);
	
//	public PartiesDetailsVO getExcludingAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,String partyName);
	
	public PartiesDetailsVO getAllElectionAllianceYearsForAParty(Long partyId,Long stateId,String partyName);
	
	public List<PartiesDetailsVO> getAllCandidatesDetailsForAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,Long electionId,String partyName,Long columnId);
	
	public List<PartiesDetailsVO> getWeaknessConstituenceisDetails(String electionType,Long stateId,String type,Long partyId,Long colId,Long totalElectionYears,Long electionId,String partyName);
	
	
	public ElectionInfoVO getIncludingAllianceDetailsForAParty(String electionType,Long stateId,Long partyId,Long totalElectionYears,Long electionId,String partyName);
	
	public ElectionInfoVO getRequiredMatchingConstituencies(Long selectedNoOfYears,String electionType,Long stateId,String searchType,String searchText);
	
*/}
