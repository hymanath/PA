package com.itgrids.partyanalyst.model;

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
@Table(name = "ivr_campaign_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrCampaignOptions {

	
	private Long ivrCampaignOptionsId;
	private Long ivrCampaignId;
	private IvrCampaign ivrCampaign;

	
	private Long ivrOptionsId;
	private IvrOptions ivrOptions;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_campaign_options_id", unique = true, nullable = false)
	public Long getIvrCampaignOptionsId() {
		return ivrCampaignOptionsId;
	}
	public void setIvrCampaignOptionsId(Long ivrCampaignOptionsId) {
		this.ivrCampaignOptionsId = ivrCampaignOptionsId;
	}
	
	
	@Column(name="ivr_campaign_id")
	public Long getIvrCampaignId() {
		return ivrCampaignId;
	}
	public void setIvrCampaignId(Long ivrCampaignId) {
		this.ivrCampaignId = ivrCampaignId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "ivr_campaign_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrCampaign getIvrCampaign() {
		return ivrCampaign;
	}
	public void setIvrCampaign(IvrCampaign ivrCampaign) {
		this.ivrCampaign = ivrCampaign;
	}
	
	@Column(name="ivr_options_id")
	public Long getIvrOptionsId() {
		return ivrOptionsId;
	}
	public void setIvrOptionsId(Long ivrOptionsId) {
		this.ivrOptionsId = ivrOptionsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "ivr_options_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrOptions getIvrOptions() {
		return ivrOptions;
	}
	public void setIvrOptions(IvrOptions ivrOptions) {
		this.ivrOptions = ivrOptions;
	}
	
	
	
	
}
