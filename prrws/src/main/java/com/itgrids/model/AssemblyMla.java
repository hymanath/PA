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
@Table(name = "assembly_mla")
public class AssemblyMla {
	private Long assemblyMlaId;
	private Long constituencyId;
	private String party;
	private String mlaName;
	private Long districtId;
	private Long parliamentConstituencyId;
	//private Long tdpCadreId
	
	private Constituency constituency;
	private District district;
	
	
	
	@Id
	@Column(name="assembly_mla_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getAssemblyMlaId() {
		return assemblyMlaId;
	}
	public void setAssemblyMlaId(Long assemblyMlaId) {
		this.assemblyMlaId = assemblyMlaId;
	}
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="party")
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	@Column(name="mla_name")
	public String getMlaName() {
		return mlaName;
	}
	public void setMlaName(String mlaName) {
		this.mlaName = mlaName;
	}
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name="parliament_constituency_id")
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false, updatable = false)
	
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
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
