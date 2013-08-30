package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class DemoRequestVO {

	private Long id;
	private String name;
	private String mobileNo;
	private String constituency;
	private String email;
	private String requestedTime;
	private String assignedTo;
	private String remarks;
	private Long userId;
	private String isDelete;
	private List<DemoRequestVO> DemoRequestVOList = new ArrayList<DemoRequestVO>(0);
	private String type;
	private String content;
	private String response;
	private int count;
	
	
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRequestedTime() {
		return requestedTime;
	}
	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public List<DemoRequestVO> getDemoRequestVOList() {
		return DemoRequestVOList;
	}
	public void setDemoRequestVOList(List<DemoRequestVO> demoRequestVOList) {
		DemoRequestVOList = demoRequestVOList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
