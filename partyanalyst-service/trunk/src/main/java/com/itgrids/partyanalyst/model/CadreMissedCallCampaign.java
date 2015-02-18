package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "cadre_missed_call_campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreMissedCallCampaign {
	
	private Long cadreMissedCallCampaignId;
	private String mobileNumber;
	private Date insertedTime;
	private MissedCallCampaign  missedCallCampaign;
	private Long missedCallCampaignId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_missed_call_campaign_id", unique = true, nullable = false)
	public Long getCadreMissedCallCampaignId() {
		return cadreMissedCallCampaignId;
	}
	public void setCadreMissedCallCampaignId(Long cadreMissedCallCampaignId) {
		this.cadreMissedCallCampaignId = cadreMissedCallCampaignId;
	}
	
	
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "missed_call_campaign_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MissedCallCampaign getMissedCallCampaign() {
		return missedCallCampaign;
	}
	public void setMissedCallCampaign(MissedCallCampaign missedCallCampaign) {
		this.missedCallCampaign = missedCallCampaign;
	}
	
	@Column(name="missed_call_campaign_id")
	public Long getMissedCallCampaignId() {
		return missedCallCampaignId;
	}
	public void setMissedCallCampaignId(Long missedCallCampaignId) {
		this.missedCallCampaignId = missedCallCampaignId;
	}  
	
	
	
	

}
