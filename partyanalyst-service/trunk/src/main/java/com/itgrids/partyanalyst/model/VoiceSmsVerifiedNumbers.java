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
@Table(name="voice_sms_verified_numbers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoiceSmsVerifiedNumbers extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long voiceSmsVerifiedNumbersId;
	private Long senderMobileNumber;
	private User user;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voice_sms_verified_numbers_id", unique = true, nullable = false)
	public Long getVoiceSmsVerifiedNumbersId() {
		return voiceSmsVerifiedNumbersId;
	}
	public void setVoiceSmsVerifiedNumbersId(Long voiceSmsVerifiedNumbersId) {
		this.voiceSmsVerifiedNumbersId = voiceSmsVerifiedNumbersId;
	}
	
	@Column(name="sender_mobile_number" , length=15)
	public Long getSenderMobileNumber() {
		return senderMobileNumber;
	}
	public void setSenderMobileNumber(Long senderMobileNumber) {
		this.senderMobileNumber = senderMobileNumber;
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

}
