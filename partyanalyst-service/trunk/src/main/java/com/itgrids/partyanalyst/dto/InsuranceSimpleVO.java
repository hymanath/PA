/**
 * @author SRAVANTH
 * MAR 28, 2017
 * InsuranceSimpleVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

/**
 * @author SRAVANTH
 * @date MAR 28, 2017
 */
public class InsuranceSimpleVO {

	public Long id;
	public String name;
	public Date date;
	public String dateString;
	public String type;
	public String status;
	public String username;
	public String current;
	public String onlystatus;
	
	public List<InsuranceSimpleVO> simpleVOList1;
	public List<InsuranceSimpleVO> simpleVOList2;
	private String registeredDate;
	private String submittedDateToInsuranceCompany;
	private String insuranceStartDate;
	private String insuranceEndDate;
	private Long insuranceCompanyId;
	
	
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
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getSubmittedDateToInsuranceCompany() {
		return submittedDateToInsuranceCompany;
	}
	public void setSubmittedDateToInsuranceCompany(
			String submittedDateToInsuranceCompany) {
		this.submittedDateToInsuranceCompany = submittedDateToInsuranceCompany;
	}
	public String getInsuranceStartDate() {
		return insuranceStartDate;
	}
	public void setInsuranceStartDate(String insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	public String getInsuranceEndDate() {
		return insuranceEndDate;
	}
	public void setInsuranceEndDate(String insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}
	public Long getInsuranceCompanyId() {
		return insuranceCompanyId;
	}
	public void setInsuranceCompanyId(Long insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}
	public List<InsuranceSimpleVO> getSimpleVOList1() {
		return simpleVOList1;
	}
	public void setSimpleVOList1(List<InsuranceSimpleVO> simpleVOList1) {
		this.simpleVOList1 = simpleVOList1;
	}
	public List<InsuranceSimpleVO> getSimpleVOList2() {
		return simpleVOList2;
	}
	public void setSimpleVOList2(List<InsuranceSimpleVO> simpleVOList2) {
		this.simpleVOList2 = simpleVOList2;
	}
}
