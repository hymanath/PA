/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 6, 2009
 */

package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class ConstituencyElectionResults {

	private ConstituencyElectionResultVO electionResultForParty;
	private ConstituencyElectionResultVO electionResultForNewParty;
	private Map<SelectOptionVO,List<PartiesDetailsVO>> partiesDetailsVOMap;
	private ResultStatus resultStatus;	
	private Map<Long,SelectOptionVO> partyResults;
	private List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO;
	private List<SelectOptionVO> allPartiesData;
	private Long totalNumberOfConstituencies;
	
	//getters and setters

	public ConstituencyElectionResultVO getElectionResultForParty() {
		return electionResultForParty;
	}
	public Long getTotalNumberOfConstituencies() {
		return totalNumberOfConstituencies;
	}
	public void setTotalNumberOfConstituencies(Long totalNumberOfConstituencies) {
		this.totalNumberOfConstituencies = totalNumberOfConstituencies;
	}
	public List<SelectOptionVO> getAllPartiesData() {
		return allPartiesData;
	}
	public void setAllPartiesData(List<SelectOptionVO> allPartiesData) {
		this.allPartiesData = allPartiesData;
	}
	public List<PartiesStrengthsInfoVO> getPartiesStrengthsInfoVO() {
		return partiesStrengthsInfoVO;
	}
	public void setPartiesStrengthsInfoVO(
			List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO) {
		this.partiesStrengthsInfoVO = partiesStrengthsInfoVO;
	}
	public Map<Long, SelectOptionVO> getPartyResults() {
		return partyResults;
	}
	public void setPartyResults(Map<Long, SelectOptionVO> partyResults) {
		this.partyResults = partyResults;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Map<SelectOptionVO, List<PartiesDetailsVO>> getPartiesDetailsVOMap() {
		return partiesDetailsVOMap;
	}
	public void setPartiesDetailsVOMap(
			Map<SelectOptionVO, List<PartiesDetailsVO>> partiesDetailsVOMap) {
		this.partiesDetailsVOMap = partiesDetailsVOMap;
	}
	public void setElectionResultForParty(
			ConstituencyElectionResultVO electionResultForParty) {
		this.electionResultForParty = electionResultForParty;
	}
	public ConstituencyElectionResultVO getElectionResultForNewParty() {
		return electionResultForNewParty;
	}
	public void setElectionResultForNewParty(
			ConstituencyElectionResultVO electionResultForNewParty) {
		this.electionResultForNewParty = electionResultForNewParty;
	}
}
