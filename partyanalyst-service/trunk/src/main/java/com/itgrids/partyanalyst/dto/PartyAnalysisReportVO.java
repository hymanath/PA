/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyAnalysisReportVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1342690810855886007L;
	
	private Long partyId;
	private Long electionId;
	private Long electionTypeId;
	private String stateName;
	private String partyName;
	private String electionType;
	private String electionYear;
	
	private List<SelectOptionVO> alliancPartys;
	private PartyAnalysisBasicVO partyBasicAnalysisVO;
	private List<PartyAnalysisBasicVO> alliancPartiesBasicAnalysisVO;
	private List<SelectOptionVO> partiesList;
	private List<SelectOptionVO> electionYearsList;
	private List<SelectOptionVO> electionYearsListForParty;
	
	private ResultStatus resultStatus;
	
	public void setElectionYearsListForParty(
			List<SelectOptionVO> electionYearsListForParty) {
		this.electionYearsListForParty = electionYearsListForParty;
	}
	public List<SelectOptionVO> getElectionYearsListForParty() {
		return electionYearsListForParty;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<SelectOptionVO> getAlliancPartys() {
		return alliancPartys;
	}
	public void setAlliancPartys(List<SelectOptionVO> alliancPartys) {
		this.alliancPartys = alliancPartys;
	}
	public PartyAnalysisBasicVO getPartyBasicAnalysisVO() {
		return partyBasicAnalysisVO;
	}
	public void setPartyBasicAnalysisVO(PartyAnalysisBasicVO partyBasicAnalysisVO) {
		this.partyBasicAnalysisVO = partyBasicAnalysisVO;
	}
	public List<PartyAnalysisBasicVO> getAlliancPartiesBasicAnalysisVO() {
		return alliancPartiesBasicAnalysisVO;
	}
	public void setAlliancPartiesBasicAnalysisVO(
			List<PartyAnalysisBasicVO> alliancPartiesBasicAnalysisVO) {
		this.alliancPartiesBasicAnalysisVO = alliancPartiesBasicAnalysisVO;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	public List<SelectOptionVO> getElectionYearsList() {
		return electionYearsList;
	}
	public void setElectionYearsList(List<SelectOptionVO> electionYearsList) {
		this.electionYearsList = electionYearsList;
	}
	
	
    
}
