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
import com.itgrids.partyanalyst.model.Candidate;

@Entity
@Table(name="candidate_social")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateSocial extends BaseModel implements Serializable {
             
	private static final long serialVersionUID = 1L;

	private Long candidateSocialId;
	private Candidate candidate;
	private SocialNetworkSite socialNetworkSite;
	private String profileId;
	
	public CandidateSocial()
	{}
	
	public CandidateSocial(Candidate candidate,SocialNetworkSite socialNetworkSite, String profileId)
	{
		this.candidate = candidate;
		this.socialNetworkSite = socialNetworkSite;
		this.profileId = profileId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_social_id", unique = true, nullable = false)
	public Long getCandidateSocialId() {
		return candidateSocialId;
	}
	public void setCandidateSocialId(Long candidateSocialId) {
		this.candidateSocialId = candidateSocialId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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
