package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CommitteeResultVO implements Serializable{
	
	private boolean errorStatus;
	private Long errorCode;
	private String message;
    private Long tdpRolesId;
    private String role;
    private Long   totalCount;
	private Long  minCount;
    private Long  occupiedCount;
    private Long tdpCommitteeId;
    private List<CommitteeResultVO>  subList;
	

	public Long getMinCount() {
		return minCount;
	}

	public void setMinCount(Long minCount) {
		this.minCount = minCount;
	}

	public Long getOccupiedCount() {
		return occupiedCount;
	}

	public void setOccupiedCount(Long occupiedCount) {
		this.occupiedCount = occupiedCount;
	}

	public Long getTdpRolesId() {
		return tdpRolesId;
	}

	public void setTdpRolesId(Long tdpRolesId) {
		this.tdpRolesId = tdpRolesId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<CommitteeResultVO> getSubList() {
		return subList;
	}

	public void setSubList(List<CommitteeResultVO> subList) {
		this.subList = subList;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	

	public Long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}

	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
    	
}
