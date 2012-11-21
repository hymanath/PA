package com.itgrids.partyanalyst.dto;

public class CastVO {

	private String castName;
	private String castPercentage;
	private Long castCount; 
	
	private String canstnone;
	private Long castnoneCount;
	
	

	public CastVO(){
		
	}
	
	public CastVO(String castName, String castPercentage) {
		this.castName = castName;
		this.castPercentage = castPercentage;
	}

	public String getCastName() {
		return castName;
	}
	
	public void setCastName(String castName) {
		this.castName = castName;
	}
	
	public String getCastPercentage() {
		return castPercentage;
	}
	
	public void setCastPercentage(String castPercentage) {
		this.castPercentage = castPercentage;
	}

	public Long getCastCount() {
		return castCount;
	}

	public void setCastCount(Long castCount) {
		this.castCount = castCount;
	}
	
	public String getCanstnone() {
		return canstnone;
	}

	public void setCanstnone(String canstnone) {
		this.canstnone = canstnone;
	}

	public Long getCastnoneCount() {
		return castnoneCount;
	}

	public void setCastnoneCount(Long castnoneCount) {
		this.castnoneCount = castnoneCount;
	}
}
