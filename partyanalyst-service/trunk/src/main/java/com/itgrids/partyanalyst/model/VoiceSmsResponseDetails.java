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
@Table(name="sms_response_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoiceSmsResponseDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private Long voiceSmsResponseDetailsId;
	
	private Long noOfSmsSent;
	private String responseCode;
	private Date timeSent; 
	private User user;
	private String smsDescription;
	private Long smsTypeId;
	private SmsType smsType;
	
	private Set<SmsHistory> smsHistory = new HashSet<SmsHistory>(0);

	

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
	@Column(name="sent_time")
	public Date getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(Date sentDate) {
		this.timeSent = sentDate;
	}
	
	
	
	@Column(name="sms_description" , length=500)
	public String getSmsDescription() {
		return smsDescription;
	}
	public void setSmsDescription(String smsDescription) {
		this.smsDescription = smsDescription;
	}
	
	@Column(name="sms_type_id")
	public Long getSmsTypeId() {
		return smsTypeId;
	}
	public void setSmsTypeId(Long smsTypeId) {
		this.smsTypeId = smsTypeId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "sms_type_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SmsType getSmsType() {
		return smsType;
	}
	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voiceSmsResponseDetails")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SmsHistory> getSmsHistory() {
		return smsHistory;
	}
	public void setSmsHistory(Set<SmsHistory> smsHistory) {
		this.smsHistory = smsHistory;
	}
	
}
