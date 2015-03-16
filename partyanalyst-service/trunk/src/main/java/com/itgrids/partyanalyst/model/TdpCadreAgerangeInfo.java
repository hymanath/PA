package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_agerange_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreAgerangeInfo implements java.io.Serializable{

	private Long tdpCadreAgerangeInfoId;
	private String locationType;
	private Long locationId;
	private Long voterAgeRangeId;
	private Long count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_agerange_info_id", unique = true, nullable = false)
	public Long getTdpCadreAgerangeInfoId() {
		return tdpCadreAgerangeInfoId;
	}
	public void setTdpCadreAgerangeInfoId(Long tdpCadreAgerangeInfoId) {
		this.tdpCadreAgerangeInfoId = tdpCadreAgerangeInfoId;
	}
	
	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	@Column(name="location_id")
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	@Column(name="age_range_id")
	public Long getVoterAgeRangeId() {
		return voterAgeRangeId;
	}
	public void setVoterAgeRangeId(Long voterAgeRangeId) {
		this.voterAgeRangeId = voterAgeRangeId;
	}
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
}
