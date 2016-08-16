package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommitteeInputVO implements Serializable{
	
	private List<Long> tehsilIds;
	private List<Long> assemblyConstIds;
	private List<Long> districtIds;
	private List<Long> stateIds;
	
	private String     state;
	private Long  stateId;
	private List<Long> basicCommitteeIds;
	private List<Long> tdpCommitteeLevelIds;
	private Date startDate;
	private Date endDate;
	private String status;
	
	public List<Long> getTehsilIds() {
		return tehsilIds;
	}
	public void setTehsilIds(List<Long> tehsilIds) {
		this.tehsilIds = tehsilIds;
	}
	public List<Long> getAssemblyConstIds() {
		return assemblyConstIds;
	}
	public void setAssemblyConstIds(List<Long> assemblyConstIds) {
		this.assemblyConstIds = assemblyConstIds;
	}
	public List<Long> getDistrictIds() {
		return districtIds;
	}
	public void setDistrictIds(List<Long> districtIds) {
		this.districtIds = districtIds;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Long> getBasicCommitteeIds() {
		return basicCommitteeIds;
	}
	public void setBasicCommitteeIds(List<Long> basicCommitteeIds) {
		this.basicCommitteeIds = basicCommitteeIds;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Long> getStateIds() {
		return stateIds;
	}
	public void setStateIds(List<Long> stateIds) {
		this.stateIds = stateIds;
	}
	public List<Long> getTdpCommitteeLevelIds() {
		return tdpCommitteeLevelIds;
	}
	public void setTdpCommitteeLevelIds(List<Long> tdpCommitteeLevelIds) {
		this.tdpCommitteeLevelIds = tdpCommitteeLevelIds;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
}
