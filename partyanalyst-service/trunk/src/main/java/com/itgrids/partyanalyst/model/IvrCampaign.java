package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrCampaign {
	
	private Long ivrCampaignId;
	private String description;
	private Date ivrCampaignDate;
	private String isDeleted;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_campaign_id", unique = true, nullable = false)
	public Long getIvrCampaignId() {
		return ivrCampaignId;
	}
	public void setIvrCampaignId(Long ivrCampaignId) {
		this.ivrCampaignId = ivrCampaignId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="ivr_campaign_date")
	public Date getIvrCampaignDate() {
		return ivrCampaignDate;
	}
	public void setIvrCampaignDate(Date ivrCampaignDate) {
		this.ivrCampaignDate = ivrCampaignDate;
	}
	
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
