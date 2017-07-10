package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aprwssuser.rws_min_handpumps_view")
public class RwsMinHandpumpsView {

	private String hpCode;
	private String location;
	private String assetType;
	private String assetCode;
	private Long safeLpd;
	
	@Id
	@Column(name="HP_CODE")
	public String getHpCode() {
		return hpCode;
	}
	public void setHpCode(String hpCode) {
		this.hpCode = hpCode;
	}
	@Column(name="LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="ASSET_TYPE")
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	@Column(name="ASSET_CODE")
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	@Column(name="SAFE_LPD")
	public Long getSafeLpd() {
		return safeLpd;
	}
	public void setSafeLpd(Long safeLpd) {
		this.safeLpd = safeLpd;
	}
}
