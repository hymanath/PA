package com.itgrids.partyanalyst.dto;

import java.util.List;

public class AlertVO {
	private Long alertTypeId;
	private String alertTypeName;
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
	
	private List<IdNameVO> idNamesList,assignList;
	
	private Long tdpCadreId;
	private Long id;
	private Long statusId;
	private String status;
	private Long count;
	private String category;
	private Long categoryId;
	private Long categoryCount = 0l;
	private Long locationId;
	private String locationName;
	
	private List<AlertVO> subList1;
	private List<AlertVO> subList2;
	private String title;
	
	
	public String getAlertTypeName() {
		return alertTypeName;
	}
	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<IdNameVO> getAssignList() {
		return assignList;
	}
	public void setAssignList(List<IdNameVO> assignList) {
		this.assignList = assignList;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<AlertVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<AlertVO> subList1) {
		this.subList1 = subList1;
	}
	public Long getCategoryCount() {
		return categoryCount;
	}
	public void setCategoryCount(Long categoryCount) {
		this.categoryCount = categoryCount;
	}
	
	public List<AlertVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<AlertVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
