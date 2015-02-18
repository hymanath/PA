package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "missed_call_campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MissedCallCampaign {
	
	private Long missedCallCampaignId;	
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "missed_call_campaign_id", unique = true, nullable = false)
	public Long getMissedCallCampaignId() {
		return missedCallCampaignId;
	}
	public void setMissedCallCampaignId(Long missedCallCampaignId) {
		this.missedCallCampaignId = missedCallCampaignId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
