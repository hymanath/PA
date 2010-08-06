/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 04, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/*
 * @author Sai Krishna Basetti
 */
public class ConstituencyElectionDetailsVO extends ConstituencyBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Basic Election Information */
	private ElectionBasicInformationVO electionInfo;
	/** Total Voters In Constituency */
	private Long totalVoters;
	/** Total Male Voters In Constituency */
	private Long maleVoters;
	/** Total Female Voters In Constituency */
	private Long femaleVoters;
    /** Total Male/Female Voters */
	private Long mfVoters;
	
	/** Total Voters Percentage */
	private Double totalVotersPercent;
	/** Total Male Voters Percent */
	private Double maleVotersPercent;
	/** Total Female Voters Percent */
	private Double femaleVotersPercent;
	/** Total Male/Female Voters Percent */
	private Double mfVotersPercent;
	
	/** Total Voters Percent In String */
	private String totalVotersPercentInString;
	/** Total Male Voters Percent In String */
	private String totalMaleVotersPercentInString;
	/** Total Female Voters Percent In String */
	private String totalFemaleVotersPercentInString;
	/** total Male/Female Voters Percent In String */
	private String totalMFVotersPercentInString;
	
	/** Total Polled Votes */
	private Long totalVotesPolled;
	/** Total Male Polled Votes */
	private Long totalMalePolledVotes;
	/** Total Female Polled Votes */
	private Long totalFemalePolledVotes;
	/** Total Male/Female Polled Votes */
	private Long totalMFPolledVotes;
	
	
	/** Getters And Setters */
	public ElectionBasicInformationVO getElectionInfo() {
		return electionInfo;
	}
	public void setElectionInfo(ElectionBasicInformationVO electionInfo) {
		this.electionInfo = electionInfo;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public Long getMfVoters() {
		return mfVoters;
	}
	public void setMfVoters(Long mfVoters) {
		this.mfVoters = mfVoters;
	}
	public Double getTotalVotersPercent() {
		return totalVotersPercent;
	}
	public void setTotalVotersPercent(Double totalVotersPercent) {
		this.totalVotersPercent = totalVotersPercent;
	}
	public Double getMaleVotersPercent() {
		return maleVotersPercent;
	}
	public void setMaleVotersPercent(Double maleVotersPercent) {
		this.maleVotersPercent = maleVotersPercent;
	}
	public Double getFemaleVotersPercent() {
		return femaleVotersPercent;
	}
	public void setFemaleVotersPercent(Double femaleVotersPercent) {
		this.femaleVotersPercent = femaleVotersPercent;
	}
	public Double getMfVotersPercent() {
		return mfVotersPercent;
	}
	public void setMfVotersPercent(Double mfVotersPercent) {
		this.mfVotersPercent = mfVotersPercent;
	}
	public String getTotalVotersPercentInString() {
		return totalVotersPercentInString;
	}
	public void setTotalVotersPercentInString(String totalVotersPercentInString) {
		this.totalVotersPercentInString = totalVotersPercentInString;
	}
	public String getTotalMaleVotersPercentInString() {
		return totalMaleVotersPercentInString;
	}
	public void setTotalMaleVotersPercentInString(
			String totalMaleVotersPercentInString) {
		this.totalMaleVotersPercentInString = totalMaleVotersPercentInString;
	}
	public String getTotalFemaleVotersPercentInString() {
		return totalFemaleVotersPercentInString;
	}
	public void setTotalFemaleVotersPercentInString(
			String totalFemaleVotersPercentInString) {
		this.totalFemaleVotersPercentInString = totalFemaleVotersPercentInString;
	}
	public String getTotalMFVotersPercentInString() {
		return totalMFVotersPercentInString;
	}
	public void setTotalMFVotersPercentInString(String totalMFVotersPercentInString) {
		this.totalMFVotersPercentInString = totalMFVotersPercentInString;
	}
	public Long getTotalVotesPolled() {
		return totalVotesPolled;
	}
	public void setTotalVotesPolled(Long totalVotesPolled) {
		this.totalVotesPolled = totalVotesPolled;
	}
	public Long getTotalMalePolledVotes() {
		return totalMalePolledVotes;
	}
	public void setTotalMalePolledVotes(Long totalMalePolledVotes) {
		this.totalMalePolledVotes = totalMalePolledVotes;
	}
	public Long getTotalFemalePolledVotes() {
		return totalFemalePolledVotes;
	}
	public void setTotalFemalePolledVotes(Long totalFemalePolledVotes) {
		this.totalFemalePolledVotes = totalFemalePolledVotes;
	}
	public Long getTotalMFPolledVotes() {
		return totalMFPolledVotes;
	}
	public void setTotalMFPolledVotes(Long totalMFPolledVotes) {
		this.totalMFPolledVotes = totalMFPolledVotes;
	}
	
}
