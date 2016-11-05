package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "tdp_cadre_achieved_sms")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreAchievedSms extends BaseModel implements Serializable {

	private Long tdpCadreAchievedSmsId;
	private Long tdpCadreSmsLeaderId;
	private Long mobileNo;
	private Long message;
	private Long sentTime;
	
	private TdpCadreSmsLeader tdpCadreSmsLeader;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_achieved_sms_id", unique = true, nullable = false)
	public Long getTdpCadreAchievedSmsId() {
		return tdpCadreAchievedSmsId;
	}
	public void setTdpCadreAchievedSmsId(Long tdpCadreAchievedSmsId) {
		this.tdpCadreAchievedSmsId = tdpCadreAchievedSmsId;
	}
	@Column(name = "tdp_cadre_sms_leader_id")
	public Long getTdpCadreSmsLeaderId() {
		return tdpCadreSmsLeaderId;
	}
	public void setTdpCadreSmsLeaderId(Long tdpCadreSmsLeaderId) {
		this.tdpCadreSmsLeaderId = tdpCadreSmsLeaderId;
	}
	@Column(name = "mobile_no")
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "message")
	public Long getMessage() {
		return message;
	}
	public void setMessage(Long message) {
		this.message = message;
	}
	@Column(name = "sent_time")
	public Long getSentTime() {
		return sentTime;
	}
	public void setSentTime(Long sentTime) {
		this.sentTime = sentTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_sms_leader_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreSmsLeader getTdpCadreSmsLeader() {
		return tdpCadreSmsLeader;
	}
	public void setTdpCadreSmsLeader(TdpCadreSmsLeader tdpCadreSmsLeader) {
		this.tdpCadreSmsLeader = tdpCadreSmsLeader;
	}
	
}
