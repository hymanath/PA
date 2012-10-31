package com.itgrids.partyanalyst.social.model;

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

import com.itgrids.partyanalyst.model.BaseModel;
import com.itgrids.partyanalyst.model.Party;

@Entity
@Table(name="party_social")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartySocial extends BaseModel implements Serializable {

	private static final long serialVersionUID = 2902389769539664823L;
	
	private Long partySocialId;
	private Party party;
	private SocialNetworkSite socialNetworkSite;
	private String profileId;
	
	public PartySocial()
	{}
	
	public PartySocial(Party party,SocialNetworkSite socialNetworkSite,String profileId)
	{
		this.party = party;
		this.socialNetworkSite = socialNetworkSite;
		this.profileId = profileId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_social_id", unique = true, nullable = false)
	public Long getPartySocialId() {
		return partySocialId;
	}
	public void setPartySocialId(Long partySocialId) {
		this.partySocialId = partySocialId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="social_network_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SocialNetworkSite getSocialNetworkSite() {
		return socialNetworkSite;
	}
	public void setSocialNetworkSite(SocialNetworkSite socialNetworkSite) {
		this.socialNetworkSite = socialNetworkSite;
	}
	
	@Column(name="profile_id", length = 100)
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
}

