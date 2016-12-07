package com.itgrids.cardprint.model;

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
@Table(name = "constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Constituency extends BaseModel implements java.io.Serializable {
		
	private Long constituencyId;
	private String name;
	private Long electionScopeId;
	private Date startDate;
	private Date deformDate;
	private Long stateId;
	private Long districtId;
	private Long countryId;
	private Long tehsilId;
	private Long localElectionBodyId;
	private String areaType;
	private String constituencyImage;
	private String nameLocal;
	
	private ElectionScope electionScope; 
	private State state;
	private Country country;
	private District district;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_id", unique = true, nullable = false)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "election_scope_id")
	public Long getElectionScopeId() {
		return electionScopeId;
	}
	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "deform_date")
	public Date getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name = "district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	@Column(name = "tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	@Column(name = "local_election_body_id")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	@Column(name = "area_type")
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	@Column(name = "constituency_image")
	public String getConstituencyImage() {
		return constituencyImage;
	}
	public void setConstituencyImage(String constituencyImage) {
		this.constituencyImage = constituencyImage;
	}
	@Column(name = "name_local")
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "election_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return electionScope;
	}
	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "state_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "country_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "district_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
}
