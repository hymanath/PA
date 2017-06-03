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

	@Id
	@Column(name="constituency_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long constituencyId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="election_scope_id")
	private Long electionScopeId;
	
	@Column(name="state_id")
	private Long stateId;
	
	@Column(name="district_id")
	private Long districtId;
	
	@Column(name="area_type")
	private String areaType;
	
	@Column(name="name_local")
	private String nameLocal;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id", insertable = false, updatable = false)
	private ElectionScope electionScope;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private District district;

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getNameLocal() {
		return nameLocal;
	}

	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}

	public ElectionScope getElectionScope() {
		return electionScope;
	}

	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	
}
