package com.itgrids.model;

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
@Table(name = "constituency")
public class Constituency{
		
	private static final long serialVersionUID = -2853930539938433902L;

	private Long constituencyId;
	private String name;
	private Long electionScopeId;
	private Long stateId;
	private Long districtId;
	private String areaType;
	private String nameLocal;
	private Long prConstituencyId;
	private ElectionScope electionScope;
	private District district;
	private PrConstituency prConstituency;
	private Long rwsConstituencyId;
	private RwsConstituency rwsConstituency;
	private Long encConstituencyId;
	
	@Id
	@Column(name="constituency_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="election_scope_id")
	public Long getElectionScopeId() {
		return electionScopeId;
	}
	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name="area_type")
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	@Column(name="name_local")
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id", insertable = false, updatable = false)
	public ElectionScope getElectionScope() {
		return electionScope;
	}
	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	@Column(name="pr_constituency_id")
	public Long getPrConstituencyId() {
		return prConstituencyId;
	}
	public void setPrConstituencyId(Long prConstituencyId) {
		this.prConstituencyId = prConstituencyId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pr_constituency_id", insertable = false, updatable = false)
	public PrConstituency getPrConstituency() {
		return prConstituency;
	}
	public void setPrConstituency(PrConstituency prConstituency) {
		this.prConstituency = prConstituency;
	}
	@Column(name="rws_constituency_id")
	public Long getRwsConstituencyId() {
		return rwsConstituencyId;
	}
	public void setRwsConstituencyId(Long rwsConstituencyId) {
		this.rwsConstituencyId = rwsConstituencyId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_constituency_id", insertable = false, updatable = false)
	public RwsConstituency getRwsConstituency() {
		return rwsConstituency;
	}
	public void setRwsConstituency(RwsConstituency rwsConstituency) {
		this.rwsConstituency = rwsConstituency;
	}
	@Column(name ="enc_constituency_id")
	public Long getEncConstituencyId() {
		return encConstituencyId;
	}
	public void setEncConstituencyId(Long encConstituencyId) {
		this.encConstituencyId = encConstituencyId;
	}
		
	
}
