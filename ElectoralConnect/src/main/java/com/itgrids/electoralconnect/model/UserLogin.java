package com.itgrids.electoralconnect.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="user_login")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLogin extends BaseModel implements Serializable{
	private Long userLoginId;
	private String userName;
	private String password;
	private Date lastLoginTime;
	private String isEmailVerified;
	private String isPwdChanged;
	
	public UserLogin(Long userLoginId,String userName,String password,Date lastLoginTime,
			String isEmailVerified,String isPwdChanged){
		this.userLoginId=userLoginId;
		this.userName=userName;
		this.password=password;
		this.lastLoginTime=lastLoginTime;
		this.isEmailVerified=isEmailVerified;
		this.isPwdChanged=isPwdChanged;
	}
	public UserLogin(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_login_id", unique=true, nullable=false)
	public Long getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Long userLoginId) {
		this.userLoginId = userLoginId;
	}
	
	@Column(name = "user_name", length = 70)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 70)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "last_login_time", length = 70)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "is_email_verified", length = 70)
	public String getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(String isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	@Column(name = "is_pwd_changed", length = 70)
	public String getIsPwdChanged() {
		return isPwdChanged;
	}

	public void setIsPwdChanged(String isPwdChanged) {
		this.isPwdChanged = isPwdChanged;
	}
	
	
}
