package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class PositionManagementVO implements Serializable {

	  private Long candidateId;
	  private String candidateName;
	  private Long electionScopeId;
	  private String electionType;
	  private Long electionTypeId;
	  private Long stateId;
	  private String year;
	  private Long partyId;
	  private Long tehilId;
	  private Long districtId;
	  private Long positionScopeId;
	  private Long electionGovBodyPosId;
	  private Date fromDate;
	  private Date toDate;
	  private String status;
	  private String type;
	  private String gender;
	  private Long constituencyId;
	  private Long localElecBodyId;
	  private String result;
	  
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getElectionScopeId() {
		return electionScopeId;
	}
	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getTehilId() {
		return tehilId;
	}
	public void setTehilId(Long tehilId) {
		this.tehilId = tehilId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getPositionScopeId() {
		return positionScopeId;
	}
	public void setPositionScopeId(Long positionScopeId) {
		this.positionScopeId = positionScopeId;
	}
	public Long getElectionGovBodyPosId() {
		return electionGovBodyPosId;
	}
	public void setElectionGovBodyPosId(Long electionGovBodyPosId) {
		this.electionGovBodyPosId = electionGovBodyPosId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getLocalElecBodyId() {
		return localElecBodyId;
	}
	public void setLocalElecBodyId(Long localElecBodyId) {
		this.localElecBodyId = localElecBodyId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	  
	  
}
