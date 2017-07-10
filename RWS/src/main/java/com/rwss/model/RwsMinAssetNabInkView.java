package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_asset_hab_lnk_view")
public class RwsMinAssetNabInkView {

	private String assetCode;
	private String habCode;
	
	@Id
	@Column(name="ASSET_CODE")
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	@Column(name="HAB_CODE")
	public String getHabCode() {
		return habCode;
	}
	public void setHabCode(String habCode) {
		this.habCode = habCode;
	}
	
	
}
