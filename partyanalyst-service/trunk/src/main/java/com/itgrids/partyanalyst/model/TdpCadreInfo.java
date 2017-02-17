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
@Table(name = "tdp_cadre_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreInfo {
	
	private Long tdpCadreInfoId;
	private String locationType;
	private Long locationId;
	private String type;
	private Long count;
	private Long tdpCommitteeEnrollMentYearId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_info_id", unique = true, nullable = false)
	public Long getTdpCadreInfoId() {
		return tdpCadreInfoId;
	}
	public void setTdpCadreInfoId(Long tdpCadreInfoId) {
		this.tdpCadreInfoId = tdpCadreInfoId;
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
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
		
	public void setCount(Long count) {
		this.count = count;
	}
	
	@Column(name="enrollment_year_id")
	public Long getTdpCommitteeEnrollMentYearId() {
		return tdpCommitteeEnrollMentYearId;
	}
	public void setTdpCommitteeEnrollMentYearId(Long tdpCommitteeEnrollMentYearId) {
		this.tdpCommitteeEnrollMentYearId = tdpCommitteeEnrollMentYearId;
	}
}
