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
	private Registration registration;
	private AnanymousUser freeUser;
	private String userType;
	private String ipAddress;
	private Date time;
	private String status;
		
	
	public UserLoginDetails(){
		
	}
	public UserLoginDetails(Registration registration,AnanymousUser freeUser,String userType,
			String ipAddress,Date time,String status)
	{
		this.registration = registration;
		this.freeUser = freeUser;
		this.userType = userType;
		this.ipAddress = ipAddress;
		this.time = time;
		this.status = status;
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
	
	@Column(name = "user_type",length = 20)
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name = "ip_address",length = 20)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name = "time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name = "status",length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="free_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AnanymousUser getFreeUser() {
		return freeUser;
	}
	public void setFreeUser(AnanymousUser freeUser) {
		this.freeUser = freeUser;
	}


}
