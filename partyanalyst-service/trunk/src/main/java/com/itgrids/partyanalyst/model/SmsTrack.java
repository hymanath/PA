package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "user_sms_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsTrack extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long smsTrackId;
	private User user;
	private Long renewalSmsCount;
	private String renewalDate;
	private String description;
	private String smsUsername;
	private String smsPassword;
	private String senderId;
	private Long userId;
	private SmsType smsType;
	private Long smsTypeId;
	
		
	// Constructors

	
	/** default constructor */
	public SmsTrack() {
	}

	/** minimal constructor */
	public SmsTrack(Long smsTrackId) {
		this.smsTrackId = smsTrackId;
	}

	/** full constructor */
	public SmsTrack(Long smsTrackId,Long renewalSmsCount,String renewalDate,String description) {
		this.smsTrackId = smsTrackId;
		this.renewalSmsCount = renewalSmsCount;
		this.renewalDate = renewalDate;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_sms_track_id", unique = true, nullable = false)
	public Long getSmsTrackId() {
		return smsTrackId;
	}

	public void setSmsTrackId(Long smsTrackId) {
		this.smsTrackId = smsTrackId;
	}

	@Column(name = "renewal_sms_count", length = 250)
	public Long getRenewalSmsCount() {
		return renewalSmsCount;
	}

	public void setRenewalSmsCount(Long renewalSmsCount) {
		this.renewalSmsCount = renewalSmsCount;
	}

	@Column(name = "renewal_date", length = 250)
	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "sms_username", length = 50)
	public String getSmsUsername() {
		return smsUsername;
	}

	public void setSmsUsername(String smsUsername) {
		this.smsUsername = smsUsername;
	}

	@Column(name = "sms_password", length = 50)
	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	@Column(name = "sender_id", length = 50)
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
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
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sms_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SmsType getSmsType() {
		return smsType;
	}

	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}
	
	@Column(name="sms_type_id")
	public Long getSmsTypeId() {
		return smsTypeId;
	}

	public void setSmsTypeId(Long smsTypeId) {
		this.smsTypeId = smsTypeId;
	}

}
