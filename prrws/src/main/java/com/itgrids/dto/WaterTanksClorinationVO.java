package com.itgrids.dto;

public class WaterTanksClorinationVO {
	
	private String sessionToken;
	private String sessionId;
	private Long id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String isSysAdmin;
	private String lastLoginDate;
	private String host;
	private Long checked = 0L;
	private Long clorinated = 0L;
	private Long notClorinated = 0L;
	
	private Long districtId;
	private String districtName;
	private Long areaId;
	private String areaName;
	private Long servicePointId;
	private String servicePointName;
	private Long vanId;
	private String vanNo;
	private Long noOfSPs = 0L;
	private String visitDate;
	private Long noOfDistricts = 0L;
	
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public Long getNoOfSPs() {
		return noOfSPs;
	}
	public void setNoOfSPs(Long noOfSPs) {
		this.noOfSPs = noOfSPs;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getServicePointId() {
		return servicePointId;
	}
	public void setServicePointId(Long servicePointId) {
		this.servicePointId = servicePointId;
	}
	public String getServicePointName() {
		return servicePointName;
	}
	public void setServicePointName(String servicePointName) {
		this.servicePointName = servicePointName;
	}
	public Long getVanId() {
		return vanId;
	}
	public void setVanId(Long vanId) {
		this.vanId = vanId;
	}
	public String getVanNo() {
		return vanNo;
	}
	public void setVanNo(String vanNo) {
		this.vanNo = vanNo;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsSysAdmin() {
		return isSysAdmin;
	}
	public void setIsSysAdmin(String isSysAdmin) {
		this.isSysAdmin = isSysAdmin;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Long getChecked() {
		return checked;
	}
	public void setChecked(Long checked) {
		this.checked = checked;
	}
	public Long getClorinated() {
		return clorinated;
	}
	public void setClorinated(Long clorinated) {
		this.clorinated = clorinated;
	}
	public Long getNotClorinated() {
		return notClorinated;
	}
	public void setNotClorinated(Long notClorinated) {
		this.notClorinated = notClorinated;
	}
	public Long getNoOfDistricts() {
		return noOfDistricts;
	}
	public void setNoOfDistricts(Long noOfDistricts) {
		this.noOfDistricts = noOfDistricts;
	}
}
