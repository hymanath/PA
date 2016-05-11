package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrievanceSimpleVO implements Serializable{

	public Long id;
	public String name;
	public Date date;
	public String dateString;
	public String type;
	public String status;
	public String username;
	public String current;
	public String onlystatus;
	
	public String typeOfIssue;
	public String issueType;
	public Long count;
	public String approvedAmount;
	private Long otherBenifitCount = 0l;
	
	public List<GrievanceSimpleVO> simpleVOList1 = new ArrayList<GrievanceSimpleVO>(0);
	public List<GrievanceSimpleVO> simpleVOList2 = new ArrayList<GrievanceSimpleVO>(0);
	
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getOnlystatus() {
		return onlystatus;
	}
	public void setOnlystatus(String onlystatus) {
		this.onlystatus = onlystatus;
	}
	public List<GrievanceSimpleVO> getSimpleVOList1() {
		return simpleVOList1;
	}
	public void setSimpleVOList1(List<GrievanceSimpleVO> simpleVOList1) {
		this.simpleVOList1 = simpleVOList1;
	}
	public List<GrievanceSimpleVO> getSimpleVOList2() {
		return simpleVOList2;
	}
	public void setSimpleVOList2(List<GrievanceSimpleVO> simpleVOList2) {
		this.simpleVOList2 = simpleVOList2;
	}
	public String getTypeOfIssue() {
		return typeOfIssue;
	}
	public void setTypeOfIssue(String typeOfIssue) {
		this.typeOfIssue = typeOfIssue;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public Long getOtherBenifitCount() {
		return otherBenifitCount;
	}
	public void setOtherBenifitCount(Long otherBenifitCount) {
		this.otherBenifitCount = otherBenifitCount;
	}
}
