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
	
	public ResultStatus assignScopeToPosition(Long electionGoverningBodyPositionId,Long electionTypeId,Long stateId,String type);
	 
    public List<SelectOptionVO> getAllElectionTypes();
	 
	public List<SelectOptionVO> getAllElectionYears(Long stateId, String electionType);
	 
	public ResultStatus assignCandidateToAPosition(PositionManagementVO positionManagementVO);
	
	public List<SelectOptionVO> getCandidates(PositionManagementVO positionManagementVO);
	 
	public List<SelectOptionVO> getElectionTypeDetails(Long electionGoverningBodyPositionId,String positionType);
	
	public List<SelectOptionVO> getPositionTypeDetails(Long electionGoverningBodyPositionId);
	
	public List<SelectOptionVO> getStateDetails(Long electionGoverningBodyPositionId,String positionType);
	
}
