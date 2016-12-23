package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PeshiAppLoginVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String userName;
	private String status;
	private String password;
	
	public PeshiAppLoginVO(){}
	public PeshiAppLoginVO(Long userId, String userName){
		this.userId = userId;
		this.userName=userName;
	}
	public PeshiAppLoginVO(Long userId, String userName,String status){
		this.userId = userId;
		this.userName=userName;
		this.status = status;
	}
	public PeshiAppLoginVO(String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
}
