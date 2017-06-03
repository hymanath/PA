package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location_scope")
public class LocationScope{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="location_scope_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long locationScopeId;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="description")
	private String description;

	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
