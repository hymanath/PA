package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "login_details_by_tab")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LoginDetailsByTab extends BaseModel implements Serializable{

	
	/*login_details_by_tab_id
	user_name
	password
	login_date_time
	login_status*/
	
	private Long loginDetailsByTab;
	private String userName;
	private String passWord;
	private Date loginDateTime;
	private String loginStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_details_by_tab_id", unique = true, nullable = false)
	public Long getLoginDetailsByTab() {
		return loginDetailsByTab;
	}
	public void setLoginDetailsByTab(Long loginDetailsByTab) {
		this.loginDetailsByTab = loginDetailsByTab;
	}	
	
	@Column(name = "user_name", length = 300)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "login_date_time", length = 300)
	public Date getLoginDateTime() {
		return loginDateTime;
	}
	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}
	
	@Column(name = "password", length = 300)
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Column(name = "login_status", length = 300)
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	
	
}
