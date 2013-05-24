package com.itgrids.partyanalyst.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="custom_voter_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomVoterGroup extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long customVoterGroupId;
	private User user;	
	private String name;
	private AreaType areaType;
	private Long locationValue;
	
	private Constituency constituency;
	private Set<CustomVoter> customVoters = new HashSet<CustomVoter>(0);

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "custom_voter_group_id", unique = true, nullable = false)
	public Long getCustomVoterGroupId() {
		return customVoterGroupId;
	}
	public void setCustomVoterGroupId(Long customVoterGroupId) {
		this.customVoterGroupId = customVoterGroupId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	@Column(name="name" , length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "area_type_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public AreaType getAreaType() {
		return areaType;
	}
	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
	}
	
	@Column(name = "location_value", length = 15)
	public Long getLocationValue() {
		return locationValue;
	}
	
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customVoterGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

	public Set<CustomVoter> getCustomVoters() {
		return customVoters;
	}
	public void setCustomVoters(Set<CustomVoter> customVoters) {
		this.customVoters = customVoters;
	}
	
	

}
