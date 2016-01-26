package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MobileAppUserVO {
	private String uname;
	private String pwd;
	private String uniqueCode;
	private Long mobileAppUserId;
	private String mobileNum;
	private String mType;
	private String dr;
	private String advMessage;
	private String message;
	private String lastLogintime;
	private List<AccessLocationVO> accessLocations;
	
	
	public List<AccessLocationVO> getAccessLocations() {
		return accessLocations;
	}
	public void setAccessLocations(List<AccessLocationVO> accessLocations) {
		this.accessLocations = accessLocations;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public String getAdvMessage() {
		return advMessage;
	}
	public void setAdvMessage(String advMessage) {
		this.advMessage = advMessage;
	}
	public String getLastLogintime() {
		return lastLogintime;
	}
	public void setLastLogintime(String lastLogintime) {
		this.lastLogintime = lastLogintime;
	}
	

}
