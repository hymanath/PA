package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("serial")
public class PMMinuteVO implements Serializable{


	private Long id;
	private String name;
	
	private String actionType;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long tehsilId;
	private Long panchayatId;
	private Long statusId;
	private Long userAddressId;
	private Long partyMeetingId;
	private Long localElectionBodyId;
	private Long wardId;
	private Long locationLevel;
	private Long divisionId;
	
	//Tour
	
	private Long tourTypeId;
	private Long tourCategoryId;
	private Long tdpCadreId;
	private Long locationScopeId;
	private Long locationValue;
	private String tourDate;
	private Long selfApraisalCandidateId;
	
	private List<KeyValueVO> statesList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> distList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> constList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> manTowDivList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> panWardList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> documentList = new ArrayList<KeyValueVO>(0);
	
	private List<IdNameVO> categoryList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> tourTypeList = new ArrayList<IdNameVO>(0);
	
	private Long count=0l;
	private Long isGovtParty=0l;
	
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	public List<KeyValueVO> getDistList() {
		return distList;
	}
	public void setDistList(List<KeyValueVO> distList) {
		this.distList = distList;
	}
	public List<KeyValueVO> getConstList() {
		return constList;
	}
	public void setConstList(List<KeyValueVO> constList) {
		this.constList = constList;
	}
	public List<KeyValueVO> getManTowDivList() {
		return manTowDivList;
	}
	public void setManTowDivList(List<KeyValueVO> manTowDivList) {
		this.manTowDivList = manTowDivList;
	}
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	public List<KeyValueVO> getPanWardList() {
		return panWardList;
	}
	public void setPanWardList(List<KeyValueVO> panWardList) {
		this.panWardList = panWardList;
	}
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	public Long getTourCategoryId() {
		return tourCategoryId;
	}
	public void setTourCategoryId(Long tourCategoryId) {
		this.tourCategoryId = tourCategoryId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public List<IdNameVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<IdNameVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<IdNameVO> getTourTypeList() {
		return tourTypeList;
	}
	public void setTourTypeList(List<IdNameVO> tourTypeList) {
		this.tourTypeList = tourTypeList;
	}
	public List<KeyValueVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<KeyValueVO> statesList) {
		this.statesList = statesList;
	}
	public Long getSelfApraisalCandidateId() {
		return selfApraisalCandidateId;
	}
	public void setSelfApraisalCandidateId(Long selfApraisalCandidateId) {
		this.selfApraisalCandidateId = selfApraisalCandidateId;
	}
	public List<KeyValueVO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<KeyValueVO> documentList) {
		this.documentList = documentList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getIsGovtParty() {
		return isGovtParty;
	}
	public void setIsGovtParty(Long isGovtParty) {
		this.isGovtParty = isGovtParty;
	}
	
}
