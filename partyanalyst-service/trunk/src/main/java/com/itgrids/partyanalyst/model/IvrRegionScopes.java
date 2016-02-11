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
@Table(name = "ivr_region_scopes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrRegionScopes extends BaseModel implements Serializable{
	
	private Long ivrRegionScopesId;
	private String regionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_region_scopes_id", unique = true, nullable = false)
	public Long getIvrRegionScopesId() {
		return ivrRegionScopesId;
	}
	public void setIvrRegionScopesId(Long ivrRegionScopesId) {
		this.ivrRegionScopesId = ivrRegionScopesId;
	}
	@Column(name="region_scopes")
	public String getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(String regionScopes) {
		this.regionScopes = regionScopes;
	}
}
