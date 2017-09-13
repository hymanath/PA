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
@Table(name = "govt_scheme_benefits_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSchemeBenefitsInfo  extends BaseModel implements Serializable{
	
	private Long govtSchemeBenefitsIfoId;
	private Long locationScopeId;
	private Long locationValue;
	private Long  govtSchemeId;
	private Long grivenaceCount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_scheme_beneficiary_details_id", unique = true, nullable = false)
	public Long getGovtSchemeBenefitsIfoId() {
		return govtSchemeBenefitsIfoId;
	}
	public void setGovtSchemeBenefitsIfoId(Long govtSchemeBenefitsIfoId) {
		this.govtSchemeBenefitsIfoId = govtSchemeBenefitsIfoId;
	}
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="govt_scheme_id")
	public Long getGovtSchemeId() {
		return govtSchemeId;
	}
	public void setGovtSchemeId(Long govtSchemeId) {
		this.govtSchemeId = govtSchemeId;
	}
	@Column(name="grivenace_count")
	public Long getGrivenaceCount() {
		return grivenaceCount;
	}
	public void setGrivenaceCount(Long grivenaceCount) {
		this.grivenaceCount = grivenaceCount;
	}
	
	
}
