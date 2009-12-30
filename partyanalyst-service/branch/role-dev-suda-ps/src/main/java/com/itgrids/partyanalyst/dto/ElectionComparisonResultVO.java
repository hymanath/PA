/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 12, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionComparisonResultVO {

	private Long districtId;
	private Long stateId;
	private String districtName;
	private int constituenciesCount;
	private List<PartyElectionResultsVO> partyElectionResultsVO;
	private List<CandidateOppositionVO> candidateOppositionVO;
	
	//getters and setters
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<PartyElectionResultsVO> getPartyElectionResultsVO() {
		return partyElectionResultsVO;
	}
	public void setPartyElectionResultsVO(
			List<PartyElectionResultsVO> partyElectionResultsVO) {
		this.partyElectionResultsVO = partyElectionResultsVO;
	}
	public List<CandidateOppositionVO> getCandidateOppositionVO() {
		return candidateOppositionVO;
	}
	public void setCandidateOppositionVO(
			List<CandidateOppositionVO> candidateOppositionVO) {
		this.candidateOppositionVO = candidateOppositionVO;
	}
	public int getConstituenciesCount() {
		return constituenciesCount;
	}
	public void setConstituenciesCount(int constituenciesCount) {
		this.constituenciesCount = constituenciesCount;
	}
}
