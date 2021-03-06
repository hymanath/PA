/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
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

/**
 * Constituency entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Constituency extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 2770484105252236590L;
	
	// Fields
	
	private Long constituencyId;
	private District district;
	private Tehsil tehsil;
	private ElectionScope electionScope;
	private State state;
	private String name;
	private Date startDate;
	private Date deformDate;
	private Long countryId;
	private String areaType;
	private Set<ConstituencyElection> constituencyElections = new HashSet<ConstituencyElection>(0);
	private Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses = new HashSet<DelimitationConstituencyAssemblyDetails>(0);
	private Set<CommentCategoryConstituency> commentCategoryConstituency = new HashSet<CommentCategoryConstituency>(0);
	private LocalElectionBody localElectionBody;
	private Set<BoothConstituencyElection> boothConstituencyElections;
	private Set<UserAddress> userAddress = new HashSet<UserAddress>(0);
	private Set<UserAddress> ward = new HashSet<UserAddress>(0);
	private Set<UserAddress> parliamentConstituencies = new HashSet<UserAddress>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	private Set<LocalGroupRegion> localGroupWardRegion = new HashSet<LocalGroupRegion>(0);
	private Set<LocalGroupRegion> localGroupParliamentConstRegion = new HashSet<LocalGroupRegion>(0);
	private LocalElectionBodyWard localElectionBodyWard;
	
	private Set<UserConstituencyScope> userConstituencyScope = new HashSet<UserConstituencyScope>(0);
	private Set<MessageToParty> messageToParty = new HashSet<MessageToParty>(0);
	private Set<ConstituencyUrbanPercentage> constituencyUrbanPercentage = new HashSet<ConstituencyUrbanPercentage>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<CustomVoterGroup> customVoterGroups = new HashSet<CustomVoterGroup>(0);
	private Set<VoterDataAvailableConstituencies> voterDataAvailableConstituencies = new HashSet<VoterDataAvailableConstituencies>(0);
	private Set<CasteContainConstituency> casteContainConstituency = new HashSet<CasteContainConstituency>(0);
	//private Set<ConstiCasteGroupPerc> constiCasteGroupPerc = new HashSet<ConstiCasteGroupPerc>(0);
	// Constructors
	private Set<UserSurveyBooths> userSurveyBooths = new HashSet<UserSurveyBooths>(0);
	private String localName;
	

	/** default constructor */
	public Constituency() {
	}

	/** minimal constructor */
	public Constituency(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	/** full constructor */
	public Constituency(Long constituencyId, District district,
			ElectionScope electionScope, State state, String name,
			Date startDate, Date deformDate, Long countryId,
			Set<ConstituencyElection> constituencyElections,
			Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses, Tehsil tehsil,
			Set<CommentCategoryConstituency> commentCategoryConstituency) {
		this.constituencyId = constituencyId;
		this.district = district;
		this.electionScope = electionScope;
		this.state = state;
		this.name = name;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.countryId = countryId;
		this.constituencyElections = constituencyElections;
		this.delimitationConstituencyAssemblyDetailses = delimitationConstituencyAssemblyDetailses;
		this.tehsil = tehsil;
		this.commentCategoryConstituency = commentCategoryConstituency;
	}
	
	public Constituency(Long constituencyId, District district,
			ElectionScope electionScope, State state, String name,
			Date startDate, Date deformDate, Long countryId,
			Set<ConstituencyElection> constituencyElections,
			Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses,
			Set<CommentCategoryConstituency> commentCategoryConstituency,Set<LocalGroupRegion> localGroupRegion,
			Set<LocalGroupRegion> localGroupWardRegion) {
		this.constituencyId = constituencyId;
		this.district = district;
		this.electionScope = electionScope;
		this.state = state;
		this.name = name;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.countryId = countryId;
		this.constituencyElections = constituencyElections;
		this.delimitationConstituencyAssemblyDetailses = delimitationConstituencyAssemblyDetailses;
		this.commentCategoryConstituency = commentCategoryConstituency;
		this.localGroupRegion = localGroupRegion;
		this.localGroupWardRegion = localGroupWardRegion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_id", unique = true, nullable = false)
	public Long getConstituencyId() {
		return this.constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}


	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return this.electionScope;
	}

	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deform_date", length = 10)
	public Date getDeformDate() {
		return this.deformDate;
	}

	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}

	@Column(name = "country_id")
	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ConstituencyElection> getConstituencyElections() {
		return this.constituencyElections;
	}


	public void setConstituencyElections(
			Set<ConstituencyElection> constituencyElections) {
		this.constituencyElections = constituencyElections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationConstituencyAssemblyDetails> getDelimitationConstituencyAssemblyDetailses() {
		return delimitationConstituencyAssemblyDetailses;
	}

	public void setDelimitationConstituencyAssemblyDetailses(
			Set<DelimitationConstituencyAssemblyDetails> delimitationConstituencyAssemblyDetailses) {
		this.delimitationConstituencyAssemblyDetailses = delimitationConstituencyAssemblyDetailses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryConstituency> getCommentCategoryConstituency() {
		return commentCategoryConstituency;
	}

	public void setCommentCategoryConstituency(
			Set<CommentCategoryConstituency> commentCategoryConstituency) {
		this.commentCategoryConstituency = commentCategoryConstituency;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}

	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mappedConstituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<BoothConstituencyElection> getBoothConstituencyElections() {
		return boothConstituencyElections;
	}

	public void setBoothConstituencyElections(
			Set<BoothConstituencyElection> boothConstituencyElections) {
		this.boothConstituencyElections = boothConstituencyElections;
	}

	@Column(name = "area_type", length = 50)
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserAddress> getUserAddress() {
		return userAddress;
	}

	
	public void setUserAddress(Set<UserAddress> userAddress) {
		this.userAddress = userAddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserAddress> getWard() {
		return ward;
	}

	public void setWard(Set<UserAddress> ward) {
		this.ward = ward;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupWardRegion() {
		return localGroupWardRegion;
	}

	public void setLocalGroupWardRegion(Set<LocalGroupRegion> localGroupWardRegion) {
		this.localGroupWardRegion = localGroupWardRegion;
	}
	

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	public LocalElectionBodyWard getLocalElectionBodyWard() {
		return localElectionBodyWard;
	}

	public void setLocalElectionBodyWard(LocalElectionBodyWard localElectionBodyWard) {
		this.localElectionBodyWard = localElectionBodyWard;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parliamentConstituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserAddress> getParliamentConstituencies() {
		return parliamentConstituencies;
	}

	public void setParliamentConstituencies(
			Set<UserAddress> parliamentConstituencies) {
		this.parliamentConstituencies = parliamentConstituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parliamentConstituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Set<LocalGroupRegion> getLocalGroupParliamentConstRegion() {
		return localGroupParliamentConstRegion;
	}

	public void setLocalGroupParliamentConstRegion(
			Set<LocalGroupRegion> localGroupParliamentConstRegion) {
		this.localGroupParliamentConstRegion = localGroupParliamentConstRegion;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	public Set<UserConstituencyScope> getUserConstituencyScope() {
		return userConstituencyScope;
	}

	public void setUserConstituencyScope(
			Set<UserConstituencyScope> userConstituencyScope) {
		this.userConstituencyScope = userConstituencyScope;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MessageToParty> getMessageToParty() {
		return messageToParty;
	}

	public void setMessageToParty(Set<MessageToParty> messageToParty) {
		this.messageToParty = messageToParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ConstituencyUrbanPercentage> getConstituencyUrbanPercentage() {
		return constituencyUrbanPercentage;
	}

	public void setConstituencyUrbanPercentage(
			Set<ConstituencyUrbanPercentage> constituencyUrbanPercentage) {
		this.constituencyUrbanPercentage = constituencyUrbanPercentage;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomVoterGroup> getCustomVoterGroups() {
		return customVoterGroups;
	}

	public void setCustomVoterGroups(Set<CustomVoterGroup> customVoterGroups) {
		this.customVoterGroups = customVoterGroups;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<VoterDataAvailableConstituencies> getVoterDataAvailableConstituencies() {
		return voterDataAvailableConstituencies;
	}

	public void setVoterDataAvailableConstituencies(
			Set<VoterDataAvailableConstituencies> voterDataAvailableConstituencies) {
		this.voterDataAvailableConstituencies = voterDataAvailableConstituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CasteContainConstituency> getCasteContainConstituency() {
		return casteContainConstituency;
	}

	public void setCasteContainConstituency(
			Set<CasteContainConstituency> casteContainConstituency) {
		this.casteContainConstituency = casteContainConstituency;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserSurveyBooths> getUserSurveyBooths() {
		return userSurveyBooths;
	}

	public void setUserSurveyBooths(Set<UserSurveyBooths> userSurveyBooths) {
		this.userSurveyBooths = userSurveyBooths;
	}

	@Column(name="name_local")
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	
	
	
	
	
	
	
	
/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ConstiCasteGroupPerc> getConstiCasteGroupPerc() {
		return constiCasteGroupPerc;
	}

	public void setConstiCasteGroupPerc(
			Set<ConstiCasteGroupPerc> constiCasteGroupPerc) {
		this.constiCasteGroupPerc = constiCasteGroupPerc;
	}*/

	
}