package com.itgrids.partyanalyst.model;

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

/*
 * @Author Srishailam Pittala
 * @Date 6th Dec,2014
 */

@Entity
@Table(name = "party_presidents")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyPresidents extends BaseModel implements java.io.Serializable{

	 private Long partyPresidentsId;
	 private District district;
	 private Long districtId;
	 
	 private Constituency constituency;
	 private Long constituencyId;
	 
	 private Tehsil tehsil;
	 private Long tehsilId;
	 
	 private Long wardId;
	 private LocalElectionBody localElectionBody;
	 private Long localElectionBodyId ;
	 
	 private String cadreName;
	 private String designation;
	 
	 private Long mobileNo;
	 private RegionScopes regionScopes;
	 private Long regionScopesId;
	 
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_president_id", unique = true, nullable = false)
	public Long getPartyPresidentsId() {
		return partyPresidentsId;
	}
	public void setPartyPresidentsId(Long partyPresidentsId) {
		this.partyPresidentsId = partyPresidentsId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="district_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name = "district_id", length = 50)
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@Column(name = "constituency_id", length = 50)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tehsil_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	
	@Column(name = "tehsil_id", length = 50)
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@Column(name = "ward_id", length = 50)
	public Long getWardId() {
		return wardId;
	}
	
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="local_election_body_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	
	@Column(name = "local_election_body_id", length = 50)
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@Column(name = "cadre_name", length = 50)
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	@Column(name = "designation", length = 50)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name = "mobile_no", length = 50)
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@Column(name = "region_scope_id", length = 50)
	public Long getRegionScopesId() {
		return regionScopesId;
	}
	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}
	 
}
