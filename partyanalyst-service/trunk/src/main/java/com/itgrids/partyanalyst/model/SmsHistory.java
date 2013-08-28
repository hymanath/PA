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
@Table(name = "sms_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsHistory extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long historyId;
	private String sentDate;
	private String mobileNumber;
	private String smsContent;
	private SmsModule smsModule;
	private User user;
	private Long userId;
	
	private SmsType smsType;
	private Long smsTypeId;
	private Long smsResponseDetailsId;
	private VoiceSmsResponseDetails voiceSmsResponseDetails;
	
		
	// Constructors

	
	/** default constructor */
	public SmsHistory() {
	}

	/** minimal constructor */
	public SmsHistory(Long historyId) {
		this.historyId = historyId;
	}

	/** full constructor */
	public SmsHistory(Long historyId,String sentDate,String mobileNumber,String smsContent,
			String locationType,Long locationId,SmsModule smsModule,SmsTrack smsTrack) {
		this.historyId = historyId;
		this.sentDate = sentDate;
		this.mobileNumber = mobileNumber;
		this.smsContent = smsContent;
		this.smsModule = smsModule;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_history_id", unique = true, nullable = false)
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "sms_module_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SmsModule getSmsModule() {
		return smsModule;
	}

	public void setSmsModule(SmsModule smsModule) {
		this.smsModule = smsModule;
	}

	@Column(name = "sent_date", length = 250)
	public String getSentDate() {
		return sentDate;
	}

	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}

	@Column(name = "mobile_no", length = 250)
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "sms_content", length = 250)
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
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
	
   @Column(name="voice_sms_response_details_id")
	public Long getSmsResponseDetailsId() {
		return smsResponseDetailsId;
	}

	public void setSmsResponseDetailsId(Long smsResponseDetailsId) {
		this.smsResponseDetailsId = smsResponseDetailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voice_sms_response_details_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoiceSmsResponseDetails getVoiceSmsResponseDetails() {
		return voiceSmsResponseDetails;
	}

	public void setVoiceSmsResponseDetails(
			VoiceSmsResponseDetails voiceSmsResponseDetails) {
		this.voiceSmsResponseDetails = voiceSmsResponseDetails;
	}

}
