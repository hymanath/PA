package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreBasicVO {

	private Long surveyUserId;
	private String userName;
	private String longititude;
	private String latitude;
	private String surveyTime;
	private String fromDate;
	private String toDate;
	private String mobileNo;
	private String name;
	private String tdpCadreName;
	private String tdpCadreMbl;

	private List<CadreBasicVO> subList1;
	private List<CadreBasicVO> subList2;
	
	
	public String getTdpCadreName() {
		return tdpCadreName;
	}
	public void setTdpCadreName(String tdpCadreName) {
		this.tdpCadreName = tdpCadreName;
	}
	public String getTdpCadreMbl() {
		return tdpCadreMbl;
	}
	public void setTdpCadreMbl(String tdpCadreMbl) {
		this.tdpCadreMbl = tdpCadreMbl;
	}
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLongititude() {
		return longititude;
	}
	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}	public String getFromDate() {
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<CadreBasicVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<CadreBasicVO> subList1) {
		this.subList1 = subList1;
	}
	public List<CadreBasicVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<CadreBasicVO> subList2) {
		this.subList2 = subList2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
