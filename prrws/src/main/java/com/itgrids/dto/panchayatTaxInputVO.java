package com.itgrids.dto;

public class panchayatTaxInputVO {

	private Long id;
	private String name;
	
	private Long taxTypeId;
	private Long feeTypeId;
	private Long indicatorsId;
	private String yearType;
	private String dataType;
	private String defaultersType;
	private String locationType;
	private Long locationValue;
	private String fromDateStr;
	private String toDateStr;
	
	
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTaxTypeId() {
		return taxTypeId;
	}
	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}
	public Long getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(Long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public Long getIndicatorsId() {
		return indicatorsId;
	}
	public void setIndicatorsId(Long indicatorsId) {
		this.indicatorsId = indicatorsId;
	}
	public String getYearType() {
		return yearType;
	}
	public void setYearType(String yearType) {
		this.yearType = yearType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDefaultersType() {
		return defaultersType;
	}
	public void setDefaultersType(String defaultersType) {
		this.defaultersType = defaultersType;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
}
