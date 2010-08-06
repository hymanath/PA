/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 04, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/*
 * @author Sai Krishna Basetti
 */
public class PartyElectionDetailsVO extends PartyBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Basic Election Information */
	private ElectionBasicInformationVO electionInfo;
    /** Total Number Of Seats Won By Party */
	private Long totalSeatsWonByParty;
	/** Total Number Of Seats Lost By Party */
	private Long totalSeatsLostByParty;
	/** Total Percentage Gained By Party */
	private Double totalPercentGainedByParty;
	/** Total Percentage Lost By Party*/
	private Double totalPercentLostByParty;
	/** Has Alliance In Election*/
	private Boolean hasAlliances;
	/** Alliance Parties List*/
	private List<SelectOptionVO> alliancePartiesList;
	
	/** Getters And Setters */
	public ElectionBasicInformationVO getElectionInfo() {
		return electionInfo;
	}
	public void setElectionInfo(ElectionBasicInformationVO electionInfo) {
		this.electionInfo = electionInfo;
	}
	public Long getTotalSeatsWonByParty() {
		return totalSeatsWonByParty;
	}
	public void setTotalSeatsWonByParty(Long totalSeatsWonByParty) {
		this.totalSeatsWonByParty = totalSeatsWonByParty;
	}
	public Long getTotalSeatsLostByParty() {
		return totalSeatsLostByParty;
	}
	public void setTotalSeatsLostByParty(Long totalSeatsLostByParty) {
		this.totalSeatsLostByParty = totalSeatsLostByParty;
	}
	public Double getTotalPercentGainedByParty() {
		return totalPercentGainedByParty;
	}
	public void setTotalPercentGainedByParty(Double totalPercentGainedByParty) {
		this.totalPercentGainedByParty = totalPercentGainedByParty;
	}
	public Double getTotalPercentLostByParty() {
		return totalPercentLostByParty;
	}
	public void setTotalPercentLostByParty(Double totalPercentLostByParty) {
		this.totalPercentLostByParty = totalPercentLostByParty;
	}
	public Boolean getHasAlliances() {
		return hasAlliances;
	}
	public void setHasAlliances(Boolean hasAlliances) {
		this.hasAlliances = hasAlliances;
	}
	public List<SelectOptionVO> getAlliancePartiesList() {
		return alliancePartiesList;
	}
	public void setAlliancePartiesList(List<SelectOptionVO> alliancePartiesList) {
		this.alliancePartiesList = alliancePartiesList;
	}

}
