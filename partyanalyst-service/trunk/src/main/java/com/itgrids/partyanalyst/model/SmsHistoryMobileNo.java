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
@Table(name = "sms_history_mobile_no")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsHistoryMobileNo extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long SMShistoryMobileId;
	private String mobileNumber;
	
	private Long historyId;
	private String insertedTime;

	
	
	// Constructors

	
	
	/** default constructor */
	public SmsHistoryMobileNo() {
	}

	/** minimal constructor */
	public SmsHistoryMobileNo(Long historyId) {
		this.historyId = historyId;
	}

	/** full constructor */
	public SmsHistoryMobileNo(Long SMShistoryMobileId,String insertedTime,String mobileNumber) {
		this.SMShistoryMobileId = SMShistoryMobileId;
		this.insertedTime = insertedTime;
		this.mobileNumber = mobileNumber;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_history_mobile_no_id", unique = true, nullable = false)
	public Long getSMShistoryMobileId() {
		return SMShistoryMobileId;
	}

	public void setSMShistoryMobileId(Long sMShistoryMobileId) {
		SMShistoryMobileId = sMShistoryMobileId;
	}

	@Column(name = "mobile_no", length = 250)
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	@Column(name = "inserted_time", length = 250)
	public String getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}

	
}
