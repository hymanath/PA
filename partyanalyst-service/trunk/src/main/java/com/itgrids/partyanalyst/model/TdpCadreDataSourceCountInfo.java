package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "tdp_cadre_data_source_count_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreDataSourceCountInfo extends BaseModel implements Serializable {
	public Long tdpCadreDataSourceCountInfoId;
	public Long total;
	public Long renewal;
	public Long newCount;
	public String sourceType;
	public Long regionScopeId;
	public Long locationId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_data_source_count_info_id", unique = true, nullable = false)
	public Long getTdpCadreDataSourceCountInfoId() {
		return tdpCadreDataSourceCountInfoId;
	}
	public void setTdpCadreDataSourceCountInfoId(Long tdpCadreDataSourceCountInfoId) {
		this.tdpCadreDataSourceCountInfoId = tdpCadreDataSourceCountInfoId;
	}
	@Column(name="total")
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Column(name="renewal")
	public Long getRenewal() {
		return renewal;
	}
	public void setRenewal(Long renewal) {
		this.renewal = renewal;
	}
	@Column(name="new")
	public Long getNewCount() {
		return newCount;
	}
	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}
	@Column(name="source_type")
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	@Column(name="region_scope_id")
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	@Column(name="location_id") 
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
}
