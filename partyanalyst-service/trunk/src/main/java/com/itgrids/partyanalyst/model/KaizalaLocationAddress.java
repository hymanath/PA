package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "kaizala_location_address")
public class KaizalaLocationAddress {

	private Long kaizalaLocationAddressId;
	private Long stateId;
	private Long districtId;
	private Long parliamentId;
	private Long assemblyId;
	private Long tehsilId;
	private Long localElectionBodyId;
	private Long panchayatId;
	private Long hamletId;
	private Long wardId;
	
	private State state;
	private District district;
	private Constituency assembly;
	private Constituency parliament;
	private Tehsil tehsil;
	private Panchayat panchayat;
	private Hamlet hamlet;
	private LocalElectionBody localElectionBody;
	private Constituency ward;
	
	@Id
	@Column(name="kaizala_location_address_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaLocationAddressId() {
		return kaizalaLocationAddressId;
	}
	public void setKaizalaLocationAddressId(Long kaizalaLocationAddressId) {
		this.kaizalaLocationAddressId = kaizalaLocationAddressId;
	}
	@Column(name="state_id", insertable = false, updatable = false)
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name="district_id", insertable = false, updatable = false)
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name="parliament_id", insertable = false, updatable = false)
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	@Column(name="constituency_id", insertable = false, updatable = false)
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	@Column(name="tehsil_id", insertable = false, updatable = false)
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	@Column(name="local_election_body_id", insertable = false, updatable = false)
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	@Column(name="panchayat_id", insertable = false, updatable = false)
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	@Column(name="hamlet_id", insertable = false, updatable = false)
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	public Constituency getAssembly() {
		return assembly;
	}
	public void setAssembly(Constituency assembly) {
		this.assembly = assembly;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parliament_id")
	public Constituency getParliament() {
		return parliament;
	}
	public void setParliament(Constituency parliament) {
		this.parliament = parliament;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id")
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id")
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	@Column(name = "ward", insertable = false, updatable = false)
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ward")
	public Constituency getWard() {
		return ward;
	}
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	
	
	
}
