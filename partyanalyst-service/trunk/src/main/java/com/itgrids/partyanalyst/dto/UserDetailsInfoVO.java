/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class UserDetailsInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String userName;
	private String userType;
	private String userAccess;
	private Long userAccessRegionId;
	private String userAccessRegion;
	
	private String ipAddress;
	
	//Getters and Setters
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}
	public Long getUserAccessRegionId() {
		return userAccessRegionId;
	}
	public void setUserAccessRegionId(Long userAccessRegionId) {
		this.userAccessRegionId = userAccessRegionId;
	}
	public String getUserAccessRegion() {
		return userAccessRegion;
	}
	public void setUserAccessRegion(String userAccessRegion) {
		this.userAccessRegion = userAccessRegion;
	}

}
