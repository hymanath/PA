/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 23, 2009
 */
package com.itgrids.partyanalyst.dto;

public class CensusVO {

	private Long stateId;
	private Long censusId;
	private int year;
	private String tru;
	private Long totalPopulation;
	private Long malePopulation;
	private Long femalePopulation;
	
	//getters and setters
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCensusId() {
		return censusId;
	}
	public void setCensusId(Long censusId) {
		this.censusId = censusId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTru() {
		return tru;
	}
	public void setTru(String tru) {
		this.tru = tru;
	}
	public Long getTotalPopulation() {
		return totalPopulation;
	}
	public void setTotalPopulation(Long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	public Long getMalePopulation() {
		return malePopulation;
	}
	public void setMalePopulation(Long malePopulation) {
		this.malePopulation = malePopulation;
	}
	public Long getFemalePopulation() {
		return femalePopulation;
	}
	public void setFemalePopulation(Long femalePopulation) {
		this.femalePopulation = femalePopulation;
	}
}
