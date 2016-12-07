package com.itgrids.cardprint.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Constituency extends BaseModel implements java.io.Serializable {
	/*
	 * constituency_id,
	 * name,
	 * election_scope_id,
	 * start_date,
	 * deform_date,
	 * state_id,
	 * district_id,
	 * country_id,
	 * tehsil_id,
	 * local_election_body_id,
	 * area_type,
	 * constituency_image,
	 * name_local
	 */
	
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
	@Column(name = "area_type")
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	
	

}
