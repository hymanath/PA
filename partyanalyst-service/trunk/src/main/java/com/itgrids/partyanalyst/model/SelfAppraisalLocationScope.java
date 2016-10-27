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
@Table(name="self_appraisal_location_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalLocationScope {

	private Long selfAppraisalLocationScopeId;
	private String locationScope;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_location_scope_id", unique=true, nullable=false)
	public Long getSelfAppraisalLocationScopeId() {
		return selfAppraisalLocationScopeId;
	}
	public void setSelfAppraisalLocationScopeId(Long selfAppraisalLocationScopeId) {
		this.selfAppraisalLocationScopeId = selfAppraisalLocationScopeId;
	}
	@Column(name="location_scope")
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	
	
}
