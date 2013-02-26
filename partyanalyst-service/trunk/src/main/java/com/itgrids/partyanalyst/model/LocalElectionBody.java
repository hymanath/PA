package com.itgrids.partyanalyst.model;

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
@Table(name = "local_election_body")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalElectionBody extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long localElectionBodyId;
	private String name;
	private ElectionType electionType;
	private Tehsil tehsil;
	private District district;
	private Long noOfWards;
	private Set<Constituency> wards = new HashSet<Constituency>();
	private Set<UserAddress> userAddress = new HashSet<UserAddress>(0); 
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	private Set<Locality> Localities = new HashSet<Locality>(0);
	private Set<AssemblyLocalElectionBody> assemblyLocalElectionBody =new HashSet<AssemblyLocalElectionBody>();
	public LocalElectionBody(){
		
	}
	
	public LocalElectionBody(Long localElectionBodyId){
		this.localElectionBodyId = localElectionBodyId;
	}

	public LocalElectionBody(Long localElectionBodyId, String name,
			ElectionType electionType, Tehsil tehsil, District district,
			Long noOfWards,Set<LocalGroupRegion> localGroupRegion) {
		this.localElectionBodyId = localElectionBodyId;
		this.name = name;
		this.electionType = electionType;
		this.tehsil = tehsil;
		this.district = district;
		this.noOfWards = noOfWards;
		this.localGroupRegion = localGroupRegion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_election_body_id", unique = true, nullable = false)
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}

	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionType getElectionType() {
		return electionType;
	}

	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Column(name = "no_of_wards")
	public Long getNoOfWards() {
		return noOfWards;
	}

	public void setNoOfWards(Long noOfWards) {
		this.noOfWards = noOfWards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localElectionBody")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Constituency> getWards() {
		return wards;
	}

	public void setWards(Set<Constituency> wards) {
		this.wards = wards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localElectionBody")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserAddress> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Set<UserAddress> userAddress) {
		this.userAddress = userAddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localBody")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localElectionBody")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Locality> getLocalities() {
		return Localities;
	}

	public void setLocalities(Set<Locality> localities) {
		Localities = localities;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localElectionBody")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<AssemblyLocalElectionBody> getAssemblyLocalElectionBody() {
		return assemblyLocalElectionBody;
	}

	public void setAssemblyLocalElectionBody(
			Set<AssemblyLocalElectionBody> assemblyLocalElectionBody) {
		this.assemblyLocalElectionBody = assemblyLocalElectionBody;
	}
	
	
	
}
