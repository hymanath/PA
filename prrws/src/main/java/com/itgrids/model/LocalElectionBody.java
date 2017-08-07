package com.itgrids.model;

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

@Entity
@Table(name = "local_election_body")
public class LocalElectionBody implements Serializable {
	private Long localElectionBodyId;
	private String name;
	private Long electionTypeId;
	private Long tehsilId;
	private Long districtId;
	private Long noOfWards;
	private String nameLocal;
	
	
	private ElectionType electionType;
	private Tehsil tehsil;
	private District district;
	@Id
	@Column(name="local_election_body_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="election_type_id")
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	@Column(name="tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name="no_of_wards")
	public Long getNoOfWards() {
		return noOfWards;
	}
	public void setNoOfWards(Long noOfWards) {
		this.noOfWards = noOfWards;
	}
	@Column(name="name_local")
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_type_id", insertable = false, updatable = false)
	public ElectionType getElectionType() {
		return electionType;
	}
	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	
}
