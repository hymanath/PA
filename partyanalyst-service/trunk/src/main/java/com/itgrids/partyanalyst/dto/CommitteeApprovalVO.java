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
	
	
}
