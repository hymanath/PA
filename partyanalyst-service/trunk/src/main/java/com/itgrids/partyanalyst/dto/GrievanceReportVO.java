package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GrievanceReportVO {
	private Long id;
	private String name;
	private String date;
	private String status;
	private String issueType;
	private List<ComplaintScanCopyVO> scanCopyList = new ArrayList<ComplaintScanCopyVO>();
	private Double efficencyPercentage;
	private Long grivanceCount;
	private String number;
	private String districtName;
	private String consitutencyName;
	private String mandalName;
	private String villageName;
	private String description;
	private String subject;
	private String updatedDate;
	private String referenceName;
	
	
	
	
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConsitutencyName() {
		return consitutencyName;
	}
	public void setConsitutencyName(String consitutencyName) {
		this.consitutencyName = consitutencyName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getGrivanceCount() {
		return grivanceCount;
	}
	public void setGrivanceCount(Long grivanceCount) {
		this.grivanceCount = grivanceCount;
	}
	public Double getEfficencyPercentage() {
		return efficencyPercentage;
	}
	public void setEfficencyPercentage(Double efficencyPercentage) {
		this.efficencyPercentage = efficencyPercentage;
	}
	public List<ComplaintScanCopyVO> getScanCopyList() {
		return scanCopyList;
	}
	public void setScanCopyList(List<ComplaintScanCopyVO> scanCopyList) {
		this.scanCopyList = scanCopyList;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
