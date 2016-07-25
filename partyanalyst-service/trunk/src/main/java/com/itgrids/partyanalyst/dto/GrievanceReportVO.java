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
