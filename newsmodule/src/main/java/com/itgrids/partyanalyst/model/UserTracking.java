package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import com.itgrids.partyanalyst.model.BaseModel;

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
@Table(name= "user_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTracking extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userTrackingId;
	private String urlName;
	private String ipAddress;
	private Date time;
	private String sessionId;
	private User user;
	private Long userId;
	private String userType;
	
	 public UserTracking()
	 {
		 
	 }
	 
	 public UserTracking(Long siteTrackingId)
	 {
		 this.userTrackingId = siteTrackingId;
	 }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_tracking_id", unique = true, nullable = false)
	public Long getUserTrackingId() {
		return userTrackingId;
	}

	
	public void setUserTrackingId(Long userTrackingId) {
		this.userTrackingId = userTrackingId;
	}
   
	@Column(name = "url_name", length = 500)
	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	
	@Column(name = "ip_address", length = 50)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name = "time", length = 50)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name = "session_id", length = 100)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_type", length = 25)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
