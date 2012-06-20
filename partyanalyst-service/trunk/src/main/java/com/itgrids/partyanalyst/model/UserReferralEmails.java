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
@Table(name = "user_referral_emails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserReferralEmails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 3894099756722006346L;

	private Long userReferralEmailsId;
	private String email;
	private Date time;
	private User user;
	private Long userId;
	

	public UserReferralEmails() {

	}

	public UserReferralEmails(String email, Date time,
			Long userId) {
		this.email = email;
		this.time = time;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_referral_emails_id", unique = true, nullable = false)
	public Long getUserReferralEmailsId() {
		return userReferralEmailsId;
	}

	public void setUserReferralEmailsId(Long userReferralEmailsId) {
		this.userReferralEmailsId = userReferralEmailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Column(name = "email", length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "time", length = 10)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
