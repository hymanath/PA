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
	
	private List<KeyValueVO> distList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> constList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> manTowDivList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> panWardList = new ArrayList<KeyValueVO>(0);
	
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
	
	
}
