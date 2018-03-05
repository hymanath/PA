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
@Table(name = "light_pole_details")
public class LightPoleDetails {

	private Long lightPoleDetailsId;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	private Long totalPoles;
	private Long polesLed;
	private Long polesTubeLights;
	private Long polesCfl;
	private Long polesSvLamps;
	private Long polesMvLamps;
	private Long polesFilamentBulbs;
	private Long totalPolesNoLights;
	private String isDeleted;
	
	private LocationScope locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name = "light_pole_details_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getLightPoleDetailsId() {
		return lightPoleDetailsId;
	}
	public void setLightPoleDetailsId(Long lightPoleDetailsId) {
		this.lightPoleDetailsId = lightPoleDetailsId;
	}
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name = "total_poles")
	public Long getTotalPoles() {
		return totalPoles;
	}
	public void setTotalPoles(Long totalPoles) {
		this.totalPoles = totalPoles;
	}
	@Column(name = "poles_led")
	public Long getPolesLed() {
		return polesLed;
	}
	public void setPolesLed(Long polesLed) {
		this.polesLed = polesLed;
	}
	@Column(name = "poles_tube_lights")
	public Long getPolesTubeLights() {
		return polesTubeLights;
	}
	public void setPolesTubeLights(Long polesTubeLights) {
		this.polesTubeLights = polesTubeLights;
	}
	@Column(name = "poles_cfl")
	public Long getPolesCfl() {
		return polesCfl;
	}
	public void setPolesCfl(Long polesCfl) {
		this.polesCfl = polesCfl;
	}
	@Column(name = "poles_sv_lamps")
	public Long getPolesSvLamps() {
		return polesSvLamps;
	}
	public void setPolesSvLamps(Long polesSvLamps) {
		this.polesSvLamps = polesSvLamps;
	}
	@Column(name = "poles_mv_lamps")
	public Long getPolesMvLamps() {
		return polesMvLamps;
	}
	public void setPolesMvLamps(Long polesMvLamps) {
		this.polesMvLamps = polesMvLamps;
	}
	@Column(name = "poles_filament_bulbs")
	public Long getPolesFilamentBulbs() {
		return polesFilamentBulbs;
	}
	public void setPolesFilamentBulbs(Long polesFilamentBulbs) {
		this.polesFilamentBulbs = polesFilamentBulbs;
	}
	@Column(name = "total_poles_no_lights")
	public Long getTotalPolesNoLights() {
		return totalPolesNoLights;
	}
	public void setTotalPolesNoLights(Long totalPolesNoLights) {
		this.totalPolesNoLights = totalPolesNoLights;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
