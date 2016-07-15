package com.itgrids.partyanalyst.dto;

import java.util.List;

public class AlertVO {
	private Long alertTypeId;
	private Long severity;
	private Long locationLevelId;
	private Long locationValue;
	private String desc;
	private String candidateId;
	private Long alertImpactId;
	private Long alertSourceId;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long tehsilId;
	private Long localBodyId;
	private Long panchayatId;
	private Long wardId;
	
	private List<IdNameVO> idNamesList;
	
	private Long tdpCadreId;
	
	
	
	
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public List<IdNameVO> getIdNamesList() {
		return idNamesList;
	}
	public void setIdNamesList(List<IdNameVO> idNamesList) {
		this.idNamesList = idNamesList;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	public Long getAlertImpactId() {
		return alertImpactId;
	}
	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public Long getSeverity() {
		return severity;
	}
	public void setSeverity(Long severity) {
		this.severity = severity;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public Long getAlertSourceId() {
		return alertSourceId;
	}
	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}
	

}
