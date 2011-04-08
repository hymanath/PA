package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;

public class CandidateElectionVO extends CandidateVO{
	
	private String constituencyName;
	private String position;
	private String scope;
	
	private Long electionId;
	private String electionType;
	private String electionYear;
	
	private Long nominationId;
	private BigDecimal assets;
	private BigDecimal liabilities;
	
	private String assetsStr;
	private String liabilitiesStr;
	
	private String criminalCharges;
	
	
	public CandidateElectionVO(){
		
	}
	
	public CandidateElectionVO(String constituencyName, String position,
			String scope) {
		
		this.constituencyName = constituencyName;
		this.position = position;
		this.scope = scope;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
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

	public Long getNominationId() {
		return nominationId;
	}

	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}

	public BigDecimal getAssets() {
		return assets;
	}

	public void setAssets(BigDecimal assets) {
		this.assets = assets;
	}

	public BigDecimal getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(BigDecimal liabilities) {
		this.liabilities = liabilities;
	}

	public String getAssetsStr() {
		return assetsStr;
	}

	public void setAssetsStr(String assetsStr) {
		this.assetsStr = assetsStr;
	}

	public String getLiabilitiesStr() {
		return liabilitiesStr;
	}

	public void setLiabilitiesStr(String liabilitiesStr) {
		this.liabilitiesStr = liabilitiesStr;
	}

	public String getCriminalCharges() {
		return criminalCharges;
	}

	public void setCriminalCharges(String criminalCharges) {
		this.criminalCharges = criminalCharges;
	}
	
}
