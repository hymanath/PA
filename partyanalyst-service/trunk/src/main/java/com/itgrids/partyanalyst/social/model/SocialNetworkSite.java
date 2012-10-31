package com.itgrids.partyanalyst.social.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

import com.itgrids.partyanalyst.model.BaseModel;

@Entity
@Table(name="social_network_site")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SocialNetworkSite extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long socialNetworkId;
	private String name;
	private String siteAddress;
	private String description;
	
	private Set<PartySocial> partys = new HashSet<PartySocial>(0);
	private Set<CandidateSocial> candidates = new HashSet<CandidateSocial>(0);
	
	public SocialNetworkSite()
	{}
	
	public SocialNetworkSite(String name,String siteAddress,String description)
	{
		this.name = name;
		this.siteAddress = siteAddress;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="social_network_id", unique = true, nullable = false)
	public Long getSocialNetworkId() {
		return socialNetworkId;
	}
	public void setSocialNetworkId(Long socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}
	
	@Column(name="name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="site_address", length = 200)
	public String getSiteAddress() {
		return siteAddress;
	}
	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}
	
	@Column(name="description", length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialNetworkSite")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartySocial> getPartys() {
		return partys;
	}
	public void setPartys(Set<PartySocial> partys) {
		this.partys = partys;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialNetworkSite")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CandidateSocial> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<CandidateSocial> candidates) {
		this.candidates = candidates;
	}
	
}
