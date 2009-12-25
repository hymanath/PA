package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemBeanVO {
	private String problem;
	private String description;
	private String location;
	private String reportedDate;
	private String existingFrom;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String probSource;
	
	public ProblemBeanVO() {
		
	}

	public ProblemBeanVO(String problem, String description, String location,
			String reportedDate, String existingFrom, String name,
			String email, String phone, String mobile, String address,
			String probSource) {
		
		this.problem = problem;
		this.description = description;
		this.location = location;
		this.reportedDate = reportedDate;
		this.existingFrom = existingFrom;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.probSource = probSource;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getExistingFrom() {
		return existingFrom;
	}

	public void setExistingFrom(String existingFrom) {
		this.existingFrom = existingFrom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProbSource() {
		return probSource;
	}

	public void setProbSource(String probSource) {
		this.probSource = probSource;
	}
	
	
	
	
}
