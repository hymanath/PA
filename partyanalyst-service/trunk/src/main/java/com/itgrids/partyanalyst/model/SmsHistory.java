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
	private Registration registration;
	
		
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

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

}
