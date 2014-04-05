package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="user_sms_sent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSmsSent extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private Long userSmsSentId;
	 private User user;
	 private SmsType smsType;
	 private Date sentTime;
	 private String message;
	 
	 private Set<UserSmsReceiver> userSmsReceiver = new HashSet<UserSmsReceiver>(0);
	 
	 public UserSmsSent(){
		 
	 }
	 public UserSmsSent(Long userSmsSentId,User user,SmsType smsType,Date sentTime,String message) {
		 
		 this.userSmsSentId = userSmsSentId;
		 this.user = user;
		 this.smsType = smsType;
		 this.sentTime = sentTime;
		 this.message = message;
	}
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_sms_sent_id", unique = true, nullable = false)
	public Long getUserSmsSentId() {
		return userSmsSentId;
	}
	public void setUserSmsSentId(Long userSmsSentId) {
		this.userSmsSentId = userSmsSentId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "sms_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SmsType getSmsType() {
		return smsType;
	}
	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sent_time")
	public Date getSentTime() {
		return sentTime;
	}
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
	@Column(name="message" , length = 200)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userSmsSent")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserSmsReceiver> getUserSmsReceiver() {
		return userSmsReceiver;
	}
	public void setUserSmsReceiver(Set<UserSmsReceiver> userSmsReceiver) {
		this.userSmsReceiver = userSmsReceiver;
	}
	 
	 
}
