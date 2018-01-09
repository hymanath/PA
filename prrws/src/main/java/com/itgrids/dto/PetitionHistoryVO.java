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
	private String actionName;
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
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
}
