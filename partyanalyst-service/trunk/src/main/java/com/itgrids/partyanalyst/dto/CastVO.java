package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CastVO implements Serializable, Comparable<CastVO>{

	private String castName;
	private String castPercentage;
	private Long castCount; 
	
	private String canstnone;
	private Long castnoneCount;
	
	private String gender;
	
	private Long femalevoters;
	
	private Long malevoters;
	
	private Long castStateId;

	
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
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCastnoneCount() {
		return castnoneCount;
	}

	public void setCastnoneCount(Long castnoneCount) {
		this.castnoneCount = castnoneCount;
	}
	
	public Long getFemalevoters() {
		return femalevoters;
	}

	public void setFemalevoters(Long femalevoters) {
		this.femalevoters = femalevoters;
	}

	public Long getMalevoters() {
		return malevoters;
	}

	public void setMalevoters(Long malevoters) {
		this.malevoters = malevoters;
	}
	
	public int compareTo(CastVO arg0) {
		CastVO obj = null;
		if(obj instanceof CastVO){
			CastVO vo = (CastVO) obj;
			return castName.compareToIgnoreCase(vo.getCastName());
		}
		else
			return 0;
	}

	public Long getCastStateId() {
		return castStateId;
	}

	public void setCastStateId(Long castStateId) {
		this.castStateId = castStateId;
	}
	

}
