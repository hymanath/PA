/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 17,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public class PartyVotesMarginInConstituency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long constituencyId;
	private String constituencyName;
	
	private List<PartyResultsInVotesMarginVO> partyResultsInVotesMarginVO;
	private List<PartyResultsInVotesMarginVO> partyResultsInMarginVotesVO;
	private MandalLevelResultsForParty partyResultsOverview;
	private List<BoothResultVO> boothResults;
	private ResultStatus resultStatus;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<PartyResultsInVotesMarginVO> getPartyResultsInVotesMarginVO() {
		return partyResultsInVotesMarginVO;
	}
	public void setPartyResultsInVotesMarginVO(
			List<PartyResultsInVotesMarginVO> partyResultsInVotesMarginVO) {
		this.partyResultsInVotesMarginVO = partyResultsInVotesMarginVO;
	}
	public MandalLevelResultsForParty getPartyResultsOverview() {
		return partyResultsOverview;
	}
	public void setPartyResultsOverview(
			MandalLevelResultsForParty partyResultsOverview) {
		this.partyResultsOverview = partyResultsOverview;
	}
	public List<BoothResultVO> getBoothResults() {
		return boothResults;
	}
	public void setBoothResults(List<BoothResultVO> boothResults) {
		this.boothResults = boothResults;
	}
	public List<PartyResultsInVotesMarginVO> getPartyResultsInMarginVotesVO() {
		return partyResultsInMarginVotesVO;
	}
	public void setPartyResultsInMarginVotesVO(
			List<PartyResultsInVotesMarginVO> partyResultsInMarginVotesVO) {
		this.partyResultsInMarginVotesVO = partyResultsInMarginVotesVO;
	}

}
