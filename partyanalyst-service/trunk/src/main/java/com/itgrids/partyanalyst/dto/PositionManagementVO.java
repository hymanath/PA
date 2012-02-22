package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PositionManagementVO implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long candidateId;
	  private String candidateName;
	  private Long electionScopeId;
	  private String electionType;
	  private Long electionTypeId;
	  private Long stateId;
	  private String year;
	  private Long partyId;
	  private String partyName;
	  private Long tehilId;
	  private Long districtId;
	  private Long positionScopeId;
	  private Long electionGovBodyPosId;
	  private String positionName;
	  private Date fromDate;
	  private Date toDate;
	  private String status;
	  private String type;
	  private String gender;
	  private Long constituencyId;
	  private String constituencyName;
	  private String votesPercengate;
	  private Long rank;
	  private BigDecimal votesEarned;
	  private Long localElecBodyId;
	  private String result;
	  private PositionManagementVO currentResult;
	  private PositionManagementVO previousResult;
	  private List<PositionManagementVO> positionManagementVOList;
	  private Long isPartial;
	  
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
	public PositionManagementVO getCurrentResult() {
		return currentResult;
	}
	public void setCurrentResult(PositionManagementVO currentResult) {
		this.currentResult = currentResult;
	}
	public PositionManagementVO getPreviousResult() {
		return previousResult;
	}
	public void setPreviousResult(PositionManagementVO previousResult) {
		this.previousResult = previousResult;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(Long isPartial) {
		this.isPartial = isPartial;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getVotesPercengate() {
		return votesPercengate;
	}
	public void setVotesPercengate(String votesPercengate) {
		this.votesPercengate = votesPercengate;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public BigDecimal getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(BigDecimal votesEarned) {
		this.votesEarned = votesEarned;
	}
	public List<PositionManagementVO> getPositionManagementVOList() {
		return positionManagementVOList;
	}
	public void setPositionManagementVOList(
			List<PositionManagementVO> positionManagementVOList) {
		this.positionManagementVOList = positionManagementVOList;
	}
	  
	  
}
