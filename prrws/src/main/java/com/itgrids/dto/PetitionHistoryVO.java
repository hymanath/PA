package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PetitionHistoryVO implements java.io.Serializable{

	private Long id;
	private Long workId;
	private String name;
	private String endorsmentNo;
	private String datestr;
	private String timeStr;
	private Long statusId;
	private String stautus;
	private String remarks;
	private List<KeyValueVO> filesList = new ArrayList<KeyValueVO>(0);
	private String userName;
	private String designation;
	private String officerName;
    private String path;
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	private List<PetitionHistoryVO> subList1 = new ArrayList<PetitionHistoryVO>();
	
	// create the history value
	private Long pmTrackingId;
	private Long petitionId;
	private Long pmSubWorkDetailsId;
	private Long trackingActionId;
	private String actionName;
	private Long documentId;
	private Long pmDeptDesgOfficerId;
	private Long pmDeptDesgId;
	private Long pmDeptId;
	private String pmDepartment;
	private Long pmOfficerId;
	private String pmOfficerName;
	private String mobileNo;
	private Long pmDeptDesginationId;
	private Long pmDepartmentId;
	private String pmDepartmentName;
	private String insertedDate;
	//private String pathStr;
	
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
	public String getEndorsmentNo() {
		return endorsmentNo;
	}
	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	public String getDatestr() {
		return datestr;
	}
	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStautus() {
		return stautus;
	}
	public void setStautus(String stautus) {
		this.stautus = stautus;
	}
	public List<KeyValueVO> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<KeyValueVO> filesList) {
		this.filesList = filesList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getPmTrackingId() {
		return pmTrackingId;
	}
	public void setPmTrackingId(Long pmTrackingId) {
		this.pmTrackingId = pmTrackingId;
	}
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	public Long getTrackingActionId() {
		return trackingActionId;
	}
	public void setTrackingActionId(Long trackingActionId) {
		this.trackingActionId = trackingActionId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getPmDeptDesgOfficerId() {
		return pmDeptDesgOfficerId;
	}
	public void setPmDeptDesgOfficerId(Long pmDeptDesgOfficerId) {
		this.pmDeptDesgOfficerId = pmDeptDesgOfficerId;
	}
	public Long getPmDeptDesgId() {
		return pmDeptDesgId;
	}
	public void setPmDeptDesgId(Long pmDeptDesgId) {
		this.pmDeptDesgId = pmDeptDesgId;
	}
	public Long getPmDeptId() {
		return pmDeptId;
	}
	public void setPmDeptId(Long pmDeptId) {
		this.pmDeptId = pmDeptId;
	}
	public String getPmDepartment() {
		return pmDepartment;
	}
	public void setPmDepartment(String pmDepartment) {
		this.pmDepartment = pmDepartment;
	}
	public Long getPmOfficerId() {
		return pmOfficerId;
	}
	public void setPmOfficerId(Long pmOfficerId) {
		this.pmOfficerId = pmOfficerId;
	}
	public String getPmOfficerName() {
		return pmOfficerName;
	}
	public void setPmOfficerName(String pmOfficerName) {
		this.pmOfficerName = pmOfficerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getPmDeptDesginationId() {
		return pmDeptDesginationId;
	}
	public void setPmDeptDesginationId(Long pmDeptDesginationId) {
		this.pmDeptDesginationId = pmDeptDesginationId;
	}
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	public String getPmDepartmentName() {
		return pmDepartmentName;
	}
	public void setPmDepartmentName(String pmDepartmentName) {
		this.pmDepartmentName = pmDepartmentName;
	}
	public String getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}
	public List<PetitionHistoryVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<PetitionHistoryVO> subList1) {
		this.subList1 = subList1;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	/*public String getPathStr() {
		return pathStr;
	}
	public void setPathStr(String pathStr) {
		this.pathStr = pathStr;
	}*/
}
