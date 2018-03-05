package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class MobileAppLoginVO {
	
	private Long userId;
	private String userName;
	private String password;
	private String fullName;
	private String mobileNum;
	private List<MobileAppUserLocationVO> lovcationVoList = new ArrayList<MobileAppUserLocationVO>(0);
	private List<SmallVO> voList = new ArrayList<SmallVO>(0);
	private String status;
	private Long userTypeId;
	private String userType;
	private List<GovtWorksVO> worksVOList = new ArrayList<GovtWorksVO>(0);
	private List<GovtMainWorkVO> mainWorksVOList = new ArrayList<GovtMainWorkVO>(0);
	public List<SmallVO> subUserTypes = new ArrayList<SmallVO>(0);
	
	public List<SmallVO> getVoList() {
		return voList;
	}
	public void setVoList(List<SmallVO> voList) {
		this.voList = voList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public List<MobileAppUserLocationVO> getLovcationVoList() {
		return lovcationVoList;
	}
	public void setLovcationVoList(List<MobileAppUserLocationVO> lovcationVoList) {
		this.lovcationVoList = lovcationVoList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<GovtWorksVO> getWorksVOList() {
		return worksVOList;
	}
	public void setWorksVOList(List<GovtWorksVO> worksVOList) {
		this.worksVOList = worksVOList;
	}
	public List<GovtMainWorkVO> getMainWorksVOList() {
		return mainWorksVOList;
	}
	public void setMainWorksVOList(List<GovtMainWorkVO> mainWorksVOList) {
		this.mainWorksVOList = mainWorksVOList;
	}
	public List<SmallVO> getSubUserTypes() {
		return subUserTypes;
	}
	public void setSubUserTypes(List<SmallVO> subUserTypes) {
		this.subUserTypes = subUserTypes;
	}
	
}
