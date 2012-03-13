package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IElectionResultsUpdationService {

	public List<PartyElectionResultsVO> getCandidateResults(String electionType,Long constituencyId,Long stateId,String electionYear);
	
	public List<PartyElectionResultsVO> updateCandidateResults(Long candidateId,Long constiElecId,String status);
	
	public List<SelectOptionVO> getElectionYears(Long stateId, String electionType);
	
	public ResultStatus addNewPosition(String position);
	
	public List<SelectOptionVO> getAllPosition();
	
	public ResultStatus assignScopeToPosition(Long electionGoverningBodyPositionId,Long electionTypeId,Long stateId,Long ministerTypeId);
	 
    public List<SelectOptionVO> getAllElectionTypes();
	 
	public List<SelectOptionVO> getAllElectionYears(Long stateId, String electionType);
	 
	public ResultStatus assignCandidateToAPosition(PositionManagementVO positionManagementVO);
	
	public List<SelectOptionVO> getCandidates(PositionManagementVO positionManagementVO);
	 
	public List<SelectOptionVO> getElectionTypeDetails(Long electionGoverningBodyPositionId);
	
	public List<SelectOptionVO> getPositionTypeDetails(Long electionGoverningBodyPositionId,Long electionType);
	
	public List<SelectOptionVO> getStateDetails(Long electionGoverningBodyPositionId,Long electionType,Long ministerTypeId);
	
	public List<SelectOptionVO> getStatesForPartialElec();
	
	public List<SelectOptionVO> getConstituenciesForAnElec(Long electionId);
	
	public List<SelectOptionVO>  getMinistersType();
	
	public List<SelectOptionVO>  getMinistersTypeDetails(Long electionGoverningBodyPositionId,Long electionType);
	
	public List<SelectOptionVO> getAllStatesForParliamentMinisters();
	
	public List<SelectOptionVO> getAllYearsAndElecIdsForAssembly(Long stateId);
	
	public List<SelectOptionVO> getAllYearsAndElecIdsForParliament();
}
