package com.rwss.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "aprwssuser.RWS_MIN_ASSET_VIEW")
public class RwsMinAssetView {

	private String assetCode;
	private String assetName;
	private String assetType;
	private String leadHab;
	private String location;
	private Date dateCreated;
	private String status;
	private BigDecimal assetCost;
	
	@Id
	@Column(name="ASSET_CODE")
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	@Column(name="ASSET_NAME")
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	@Column(name="ASSET_TYPE")
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	@Column(name="LEAD_HAB")
	public String getLeadHab() {
		return leadHab;
	}
	public void setLeadHab(String leadHab) {
		this.leadHab = leadHab;
	}
	@Column(name="LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="STATUS1")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="ASSET_COST")
	public BigDecimal getAssetCost() {
		return assetCost;
	}
	public void setAssetCost(BigDecimal assetCost) {
		this.assetCost = assetCost;
	}
}
