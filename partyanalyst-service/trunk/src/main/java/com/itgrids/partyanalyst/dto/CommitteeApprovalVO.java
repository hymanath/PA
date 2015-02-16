package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CommitteeApprovalVO implements Serializable{
	private String requestNo;
	private String location;
	private Long locationId;
	private String locationType;
	private Long locationTypeId;
	private Long committeeId;
	private String committeeName;
	
	private Long roleId;
	private String role;
	
	private String position;
	private String positionId;
	private Long currentPosCount;
	private Long requestdPosCount;
	private String status;
		
	private List<CommitteeApprovalVO> committeesList;
	private List<CommitteeApprovalVO> locationsList;
	private List<Long> locationIds;
	private String refNo;
	
	private Long tdpCommitteeRoleId;
	private Long cadreCommitteeIncreasedPosId;
	
	private Long pendingCount;
	private Long rejectedCount;
	private Long approvedCount;
	private Long totalCount;
	
	private String dateString;
	private String currentRole;
	private String newRole;
	private String memberShipNo;
	
	private Long tdpCommitteeMemberId;
	private Long currentRoleId;
	private Long newRoleId;
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	public Long getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}
	public Long getCadreCommitteeIncreasedPosId() {
		return cadreCommitteeIncreasedPosId;
	}
	public void setCadreCommitteeIncreasedPosId(Long cadreCommitteeIncreasedPosId) {
		this.cadreCommitteeIncreasedPosId = cadreCommitteeIncreasedPosId;
	}
	public Long getTdpCommitteeRoleId() {
		return tdpCommitteeRoleId;
	}
	public void setTdpCommitteeRoleId(Long tdpCommitteeRoleId) {
		this.tdpCommitteeRoleId = tdpCommitteeRoleId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public List<CommitteeApprovalVO> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<CommitteeApprovalVO> locationsList) {
		this.locationsList = locationsList;
	}
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public List<CommitteeApprovalVO> getCommitteesList() {
		return committeesList;
	}
	public void setCommitteesList(List<CommitteeApprovalVO> committeesList) {
		this.committeesList = committeesList;
	}
	
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
	public Long getCurrentPosCount() {
		return currentPosCount;
	}
	public void setCurrentPosCount(Long currentPosCount) {
		this.currentPosCount = currentPosCount;
	}
	public Long getRequestdPosCount() {
		return requestdPosCount;
	}
	public void setRequestdPosCount(Long requestdPosCount) {
		this.requestdPosCount = requestdPosCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}
	public String getNewRole() {
		return newRole;
	}
	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getTdpCommitteeMemberId() {
		return tdpCommitteeMemberId;
	}
	public void setTdpCommitteeMemberId(Long tdpCommitteeMemberId) {
		this.tdpCommitteeMemberId = tdpCommitteeMemberId;
	}
	public Long getCurrentRoleId() {
		return currentRoleId;
	}
	public void setCurrentRoleId(Long currentRoleId) {
		this.currentRoleId = currentRoleId;
	}
	public Long getNewRoleId() {
		return newRoleId;
	}
	public void setNewRoleId(Long newRoleId) {
		this.newRoleId = newRoleId;
	}
	
	
}
