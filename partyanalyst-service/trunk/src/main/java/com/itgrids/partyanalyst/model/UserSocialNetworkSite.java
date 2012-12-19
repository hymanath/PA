package com.itgrids.partyanalyst.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_social_network_site")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSocialNetworkSite extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 1164868684649963786L;
	
	
	private  Long userSocialNetworksiteId;
	private String userName;
	private String userEmailId;
	private String partyName;
	private String candidateName;
	private String twitterProfileId;
	
	public UserSocialNetworkSite() {
	}
	
	public UserSocialNetworkSite(Long userSocialNetworksiteId) {
		this.userSocialNetworksiteId = userSocialNetworksiteId;
	}
	public UserSocialNetworkSite( Long userSocialNetworksiteId,String userName,String userEmailId,String partyName,String candidateName,String twitterProfileId) {
		this.userSocialNetworksiteId = userSocialNetworksiteId;
		this.userName=userName;
		this.userEmailId=userEmailId;
		this.partyName=partyName;
		this.candidateName=partyName;
		this.twitterProfileId=twitterProfileId;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_social_network_site_id", unique = true, nullable = false)
	public Long getUserSocialNetworksiteId() {
		return userSocialNetworksiteId;
	}
	
	
	public void setUserSocialNetworksiteId(Long userSocialNetworksiteId) {
		this.userSocialNetworksiteId = userSocialNetworksiteId;
	}
	
	@Column(name = "user_name", length = 45)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "user_email_id", length = 45)
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	
	@Column(name = "party_name", length = 45)
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	@Column(name = "candidate_name", length = 45)
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	@Column(name = "twitter_profile_id", length = 45)
	public String getTwitterProfileId() {
		return twitterProfileId;
	}
	public void setTwitterProfileId(String twitterProfileId) {
		this.twitterProfileId = twitterProfileId;
	}
	
	
	
	

}
