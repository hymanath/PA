package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_login_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLoginDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userLoginDetailsId;
	private User user;
	private String ipAddress;
	private Date loginTime;
	private Date logoutTime;
	private String sessionId;
	private Long userId;
	
	public UserLoginDetails(){
		
	}
	public UserLoginDetails(User user,String ipAddress,Date loginTime,Date logoutTime,String sessionId)
	{
		this.user = user;
		this.ipAddress = ipAddress;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.sessionId = sessionId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_login_details_id" , unique = true , nullable = false)
	public Long getUserLoginDetailsId() {
		return userLoginDetailsId;
	}
	public void setUserLoginDetailsId(Long userLoginDetailsId) {
		this.userLoginDetailsId = userLoginDetailsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "ip_address",length = 20)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "login_time")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@Column(name = "logout_time")
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	@Column(name = "session_id",length = 100)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
