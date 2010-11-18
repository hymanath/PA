/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 5, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class LocalUserGroupDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long localUserGroupId;
	private Long groupCategoryId;
	private String localUserGroupName;
	private String groupCategoryType;
	private String groupDesc;
	private String createdDate;
	private Long groupLocationId;
	private String groupLocation;
	private Long groupMembersCount;
	private String registrationId;
	private String groupScopeId;
	private String groupScopeValueId;
	
	//group location details
	private String state;
	private String stateName;
	private String district;
	private String districtName;
	private String constituency;
	private String constituencyName;
	private String mandal;
	private String mandalName;
	private String villageOrWard;
	private String villageOrWardName;
	private String booth;
	private String boothName;
	
	private List<UserGroupMembersVO> groupMemberDetails;
	
	private ResultStatus resultStatus;
	
	public Long getLocalUserGroupId() {
		return localUserGroupId;
	}
	public void setLocalUserGroupId(Long localUserGroupId) {
		this.localUserGroupId = localUserGroupId;
	}
	public Long getGroupCategoryId() {
		return groupCategoryId;
	}
	public void setGroupCategoryId(Long groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}
	public String getLocalUserGroupName() {
		return localUserGroupName;
	}
	public void setLocalUserGroupName(String localUserGroupName) {
		this.localUserGroupName = localUserGroupName;
	}
	public String getGroupCategoryType() {
		return groupCategoryType;
	}
	public void setGroupCategoryType(String groupCategoryType) {
		this.groupCategoryType = groupCategoryType;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getGroupLocationId() {
		return groupLocationId;
	}
	public void setGroupLocationId(Long groupLocationId) {
		this.groupLocationId = groupLocationId;
	}
	public String getGroupLocation() {
		return groupLocation;
	}
	public void setGroupLocation(String groupLocation) {
		this.groupLocation = groupLocation;
	}
	public Long getGroupMembersCount() {
		return groupMembersCount;
	}
	public void setGroupMembersCount(Long groupMembersCount) {
		this.groupMembersCount = groupMembersCount;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<UserGroupMembersVO> getGroupMemberDetails() {
		return groupMemberDetails;
	}
	public void setGroupMemberDetails(List<UserGroupMembersVO> groupMemberDetails) {
		this.groupMemberDetails = groupMemberDetails;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getGroupScopeId() {
		return groupScopeId;
	}
	public void setGroupScopeId(String groupScopeId) {
		this.groupScopeId = groupScopeId;
	}
	public String getGroupScopeValueId() {
		return groupScopeValueId;
	}
	public void setGroupScopeValueId(String groupScopeValueId) {
		this.groupScopeValueId = groupScopeValueId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageOrWard() {
		return villageOrWard;
	}
	public void setVillageOrWard(String villageOrWard) {
		this.villageOrWard = villageOrWard;
	}
	public String getVillageOrWardName() {
		return villageOrWardName;
	}
	public void setVillageOrWardName(String villageOrWardName) {
		this.villageOrWardName = villageOrWardName;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	

}
