package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aprwssuser.rws_min_scheme_sources_view")
public class RwsMinSchemeSourcesView {

	private String sourceCode; 
	private String sourceName;
	private String location;
	private String assetType;
	private String assetCode;
	private Long safeLpd;
	private String sourceTypeName;
	
	@Id
	@Column(name="SOURCE_CODE")
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	@Column(name="SOURCE_NAME")
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
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
	@Column(name="source_type_name")
	public String getSourceTypeName() {
		return sourceTypeName;
	}
	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}
	
}
