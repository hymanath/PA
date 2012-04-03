package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CandidateMinistriesVO implements Serializable {

	private static final long serialVersionUID = -2426782207984592621L;
	
	private Long candidateId;
	private String candidateName;
	private Long partyId;
	private String partyName;
	private List<ElectionGoverningBodyVO> ministries;
	private List<String> ministryTypes;
	private Date fromDate;
	private Date toDate;
	private int noOfMinistries;
	private Boolean isChiefMinister;
	private Boolean isDeputyChiefMinister;
	private Boolean isPrimeMinister;
	private Boolean hasCabinetMinisters;
	private Boolean hasMSIC;
	private Boolean hasMS;
	
	public Boolean getIsDeputyChiefMinister() {
		return isDeputyChiefMinister;
	}
	public void setIsDeputyChiefMinister(Boolean isDeputyChiefMinister) {
		this.isDeputyChiefMinister = isDeputyChiefMinister;
	}
	public Boolean getIsPrimeMinister() {
		return isPrimeMinister;
	}
	public void setIsPrimeMinister(Boolean isPrimeMinister) {
		this.isPrimeMinister = isPrimeMinister;
	}
	public Boolean getHasCabinetMinisters() {
		return hasCabinetMinisters;
	}
	public void setHasCabinetMinisters(Boolean hasCabinetMinisters) {
		this.hasCabinetMinisters = hasCabinetMinisters;
	}
	public Boolean getHasMSIC() {
		return hasMSIC;
	}
	public void setHasMSIC(Boolean hasMSIC) {
		this.hasMSIC = hasMSIC;
	}
	public Boolean getHasMS() {
		return hasMS;
	}
	public void setHasMS(Boolean hasMS) {
		this.hasMS = hasMS;
	}
	public Boolean getIsChiefMinister() {
		return isChiefMinister;
	}
	public void setIsChiefMinister(Boolean isChiefMinister) {
		this.isChiefMinister = isChiefMinister;
	}
	public List<String> getMinistryTypes() {
		return ministryTypes;
	}
	public void setMinistryTypes(List<String> ministryTypes) {
		this.ministryTypes = ministryTypes;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getNoOfMinistries() {
		return noOfMinistries;
	}
	public void setNoOfMinistries(int noOfMinistries) {
		this.noOfMinistries = noOfMinistries;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Long getCandidateId() {
		return candidateId;
		
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
	public List<ElectionGoverningBodyVO> getMinistries() {
		return ministries;
	}
	public void setMinistries(List<ElectionGoverningBodyVO> ministries) {
		this.ministries = ministries;
	}
	
		
}
