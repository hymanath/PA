/**
 * 
 */
package com.itgrids.dto;

/**
 * @author R Nagarjuna Gowd
 * date 07/06/2017
 *
 */
public class UserVO {
	
	private Long userId;
	private String email;
	private String userName;
	private String phoneNo;
	private Long responceCode;
	private String url;
	private String password;
	private String status;
	
	private String deptName;
	private String designation;
	private Long accessLvelId;
	private Long accessLevelValue;
	private String prDistrictId;
	private Long deptDesignationId;
	private Long deptDesignationOfficerId;
	private Long vendorId;
	private String vendorName;
	
	public Long getDeptDesignationId() {
		return deptDesignationId;
	}
	public void setDeptDesignationId(Long deptDesignationId) {
		this.deptDesignationId = deptDesignationId;
	}
	public Long getDeptDesignationOfficerId() {
		return deptDesignationOfficerId;
	}
	public void setDeptDesignationOfficerId(Long deptDesignationOfficerId) {
		this.deptDesignationOfficerId = deptDesignationOfficerId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Long getResponceCode() {
		return responceCode;
	}
	public void setResponceCode(Long responceCode) {
		this.responceCode = responceCode;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", email=" + email + ", userName=" + userName + ", phoneNo=" + phoneNo
				+ ", responceCode=" + responceCode + "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getAccessLvelId() {
		return accessLvelId;
	}
	public void setAccessLvelId(Long accessLvelId) {
		this.accessLvelId = accessLvelId;
	}
	public Long getAccessLevelValue() {
		return accessLevelValue;
	}
	public void setAccessLevelValue(Long accessLevelValue) {
		this.accessLevelValue = accessLevelValue;
	}
	public String getPrDistrictId() {
		return prDistrictId;
	}
	public void setPrDistrictId(String prDistrictId) {
		this.prDistrictId = prDistrictId;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
}
