package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeshiAppGrievanceVO implements Serializable{
	
	private Long complaintId;
	private String title;
	private String description;
	private Long totalGrivanceCount = 0l;
	private String raisedDateTime;
	private String lastUpdatedDateTime;
	private String presentStatus;
	private String typeOfIssue;
	private String remarks;
	private String filePathUrl;
	private String fromDate;
	private String toDate;
	private String membershipId;
	private String cadreType;
	private String mobileNo;
	private List<PeshiAppGrievanceVO> subList;
	private List<String> filePthList = new ArrayList<String>();
	private String subject;
	private String name;
	private String image;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getFilePthList() {
		return filePthList;
	}
	public void setFilePthList(List<String> filePthList) {
		this.filePthList = filePthList;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTotalGrivanceCount() {
		return totalGrivanceCount;
	}
	public void setTotalGrivanceCount(Long totalGrivanceCount) {
		this.totalGrivanceCount = totalGrivanceCount;
	}
	public String getRaisedDateTime() {
		return raisedDateTime;
	}
	public void setRaisedDateTime(String raisedDateTime) {
		this.raisedDateTime = raisedDateTime;
	}
	public String getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}
	public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}
	public String getPresentStatus() {
		return presentStatus;
	}
	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}
	public String getTypeOfIssue() {
		return typeOfIssue;
	}
	public void setTypeOfIssue(String typeOfIssue) {
		this.typeOfIssue = typeOfIssue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFilePathUrl() {
		return filePathUrl;
	}
	public void setFilePathUrl(String filePathUrl) {
		this.filePathUrl = filePathUrl;
	}
	public List<PeshiAppGrievanceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PeshiAppGrievanceVO> subList) {
		this.subList = subList;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
