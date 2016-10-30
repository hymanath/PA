package com.itgrids.partyanalyst.dto;

public class CadreDashboardVO {

	private Long id;
	private String name;
	
	private Long count2014;
	private Long count2016;
	private Long newCount;
	private Long renewalCount;
	private String perc2014;
	private String perc2016;
	private String newPerc;
	private String renewalPerc;
	private Long locationScopeId;
	private String type;
	private String percentage;
	
	
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
	public Long getCount2014() {
		return count2014;
	}
	public void setCount2014(Long count2014) {
		this.count2014 = count2014;
	}
	public Long getCount2016() {
		return count2016;
	}
	public void setCount2016(Long count2016) {
		this.count2016 = count2016;
	}
	public Long getNewCount() {
		return newCount;
	}
	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}
	public Long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}
	public String getPerc2014() {
		return perc2014;
	}
	public void setPerc2014(String perc2014) {
		this.perc2014 = perc2014;
	}
	public String getPerc2016() {
		return perc2016;
	}
	public void setPerc2016(String perc2016) {
		this.perc2016 = perc2016;
	}
	public String getNewPerc() {
		return newPerc;
	}
	public void setNewPerc(String newPerc) {
		this.newPerc = newPerc;
	}
	public String getRenewalPerc() {
		return renewalPerc;
	}
	public void setRenewalPerc(String renewalPerc) {
		this.renewalPerc = renewalPerc;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
