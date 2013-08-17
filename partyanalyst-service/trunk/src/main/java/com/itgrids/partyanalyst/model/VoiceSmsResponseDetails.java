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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="voice_sms_response_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoiceSmsResponseDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private Long voiceSmsResponseDetailsId;
	
	private Long noOfSmsSent;
	private String responseCode;
	private Date sentDate; 
	private User user;
	private String mobileNumbers;
	private String smsDescription;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voice_sms_response_details_id", unique = true, nullable = false)
	public Long getVoiceSmsResponseDetailsId() {
		return voiceSmsResponseDetailsId;
	}
	public void setVoiceSmsResponseDetailsId(Long voiceSmsResponseDetailsId) {
		this.voiceSmsResponseDetailsId = voiceSmsResponseDetailsId;
	}
	
	@Column(name="no_of_sms_sent" , length = 15)
	public Long getNoOfSmsSent() {
		return noOfSmsSent;
	}
	public void setNoOfSmsSent(Long noOfSmsSent) {
		this.noOfSmsSent = noOfSmsSent;
	}
	
	@Column(name="response_code" , length=20)
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_sent")
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@Column(name="mobile_numbers" , length = 500)
	public String getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(String mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	
	
	@Column(name="sms_description" , length=500)
	public String getSmsDescription() {
		return smsDescription;
	}
	public void setSmsDescription(String smsDescription) {
		this.smsDescription = smsDescription;
	}
	
}
