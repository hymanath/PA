package com.itgrids.eliteclub.model;

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
@Table(name = "location_scopes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocationScopes  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Long locationScopesId;
	String locationScope;
	
	
	public LocationScopes() {
		// TODO Auto-generated constructor stub
	}
	public LocationScopes(Long locationScopesId,String locationScope) {
		this.locationScopesId = locationScopesId;
		this.locationScope = locationScope;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="location_scopes_id",unique= true,nullable=false)
	public Long getLocationScopesId() {
		return locationScopesId;
	}
	public void setLocationScopesId(Long locationScopesId) {
		this.locationScopesId = locationScopesId;
	}
	
	@Column(name="location_scope")
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	
}
