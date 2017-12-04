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
	
}
