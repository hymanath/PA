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
	private Long renewalSmsCount;
	private String renewalDate;
	private String description;
	private Registration registration;
	private String smsUsername;
	private String smsPassword;
	private String senderId;
	
		
	// Constructors

	/** default constructor */
	public SmsTrack() {
	}

	/** minimal constructor */
	public SmsTrack(Long smsTrackId) {
		this.smsTrackId = smsTrackId;
	}

	/** full constructor */
	public SmsTrack(Long smsTrackId,Registration registration,Long renewalSmsCount,String renewalDate,String description) {
		this.smsTrackId = smsTrackId;
		this.registration = registration;
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

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
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

}
