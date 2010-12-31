/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 23, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CensusVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long stateId;
	private Long censusId;
	private int year;
	private Long districtId;
	private Long tehsilId;
	private Long locationId;
	private Long populationSC;
	private Long populationST;
	private String tru;
	private Long totalPopulation;
	private Long malePopulation;
	private Long femalePopulation;
	private Long literates;
	private Long illiterates;
	private Long workingPeople;
	private Long nonWorkingPeople;
	private String totalPopulationPercentage;
	private String malePopulationPercentage; 
	private String femalePopulationPercentage;
	private String populationSCPercentage;
	private String populationSTPercentage;
	private String literatesPercentage;
	private String illiteratesPercentage;
	private String workingPeoplePercentage;
	private String nonWorkingPeoplePercentage;
	private BigDecimal totPopPercent;
	private BigDecimal malePopPercent; 
	private BigDecimal femalePopPercent;
	private BigDecimal populationSCPercent;
	private BigDecimal populationSTPercent;
	private BigDecimal literatesPercent;
	private BigDecimal illiteratesPercent;
	private BigDecimal workingPeoplePercent;
	private BigDecimal nonWorkingPeoplePercent;
	private String locationName;
	private List<String> censusFields; 
	private Long censusSelectedIndex;
	
	
	public Long getCensusSelectedIndex() {
		return censusSelectedIndex;
	}
	public void setCensusSelectedIndex(Long censusSelectedIndex) {
		this.censusSelectedIndex = censusSelectedIndex;
	}
	public List<String> getCensusFields() {
		return censusFields;
	}
	public void setCensusFields(List<String> censusFields) {
		this.censusFields = censusFields;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getTotalPopulationPercentage() {
		return totalPopulationPercentage;
	}
	public void setTotalPopulationPercentage(String totalPopulationPercentage) {
		this.totalPopulationPercentage = totalPopulationPercentage;
	}
	public String getMalePopulationPercentage() {
		return malePopulationPercentage;
	}
	public void setMalePopulationPercentage(String malePopulationPercentage) {
		this.malePopulationPercentage = malePopulationPercentage;
	}
	public String getFemalePopulationPercentage() {
		return femalePopulationPercentage;
	}
	public void setFemalePopulationPercentage(String femalePopulationPercentage) {
		this.femalePopulationPercentage = femalePopulationPercentage;
	}
	public String getPopulationSCPercentage() {
		return populationSCPercentage;
	}
	public void setPopulationSCPercentage(String populationSCPercentage) {
		this.populationSCPercentage = populationSCPercentage;
	}
	public String getPopulationSTPercentage() {
		return populationSTPercentage;
	}
	public void setPopulationSTPercentage(String populationSTPercentage) {
		this.populationSTPercentage = populationSTPercentage;
	}
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getPopulationSC() {
		return populationSC;
	}
	public void setPopulationSC(Long populationSC) {
		this.populationSC = populationSC;
	}
	public Long getPopulationST() {
		return populationST;
	}
	public void setPopulationST(Long populationST) {
		this.populationST = populationST;
	}
	public BigDecimal getTotPopPercent() {
		return totPopPercent;
	}
	public void setTotPopPercent(BigDecimal totPopPercent) {
		this.totPopPercent = totPopPercent;
	}
	public BigDecimal getMalePopPercent() {
		return malePopPercent;
	}
	public void setMalePopPercent(BigDecimal malePopPercent) {
		this.malePopPercent = malePopPercent;
	}
	public BigDecimal getFemalePopPercent() {
		return femalePopPercent;
	}
	public void setFemalePopPercent(BigDecimal femalePopPercent) {
		this.femalePopPercent = femalePopPercent;
	}
	public BigDecimal getPopulationSCPercent() {
		return populationSCPercent;
	}
	public void setPopulationSCPercent(BigDecimal populationSCPercent) {
		this.populationSCPercent = populationSCPercent;
	}
	public BigDecimal getPopulationSTPercent() {
		return populationSTPercent;
	}
	public void setPopulationSTPercent(BigDecimal populationSTPercent) {
		this.populationSTPercent = populationSTPercent;
	}
	public Long getLiterates() {
		return literates;
	}
	public void setLiterates(Long literates) {
		this.literates = literates;
	}
	public Long getIlliterates() {
		return illiterates;
	}
	public void setIlliterates(Long illiterates) {
		this.illiterates = illiterates;
	}
	public Long getWorkingPeople() {
		return workingPeople;
	}
	public void setWorkingPeople(Long workingPeople) {
		this.workingPeople = workingPeople;
	}
	public Long getNonWorkingPeople() {
		return nonWorkingPeople;
	}
	public void setNonWorkingPeople(Long nonWorkingPeople) {
		this.nonWorkingPeople = nonWorkingPeople;
	}
	public String getLiteratesPercentage() {
		return literatesPercentage;
	}
	public void setLiteratesPercentage(String literatesPercentage) {
		this.literatesPercentage = literatesPercentage;
	}
	public String getIlliteratesPercentage() {
		return illiteratesPercentage;
	}
	public void setIlliteratesPercentage(String illiteratesPercentage) {
		this.illiteratesPercentage = illiteratesPercentage;
	}
	public String getWorkingPeoplePercentage() {
		return workingPeoplePercentage;
	}
	public void setWorkingPeoplePercentage(String workingPeoplePercentage) {
		this.workingPeoplePercentage = workingPeoplePercentage;
	}
	public String getNonWorkingPeoplePercentage() {
		return nonWorkingPeoplePercentage;
	}
	public void setNonWorkingPeoplePercentage(String nonWorkingPeoplePercentage) {
		this.nonWorkingPeoplePercentage = nonWorkingPeoplePercentage;
	}
	public BigDecimal getLiteratesPercent() {
		return literatesPercent;
	}
	public void setLiteratesPercent(BigDecimal literatesPercent) {
		this.literatesPercent = literatesPercent;
	}
	public BigDecimal getIlliteratesPercent() {
		return illiteratesPercent;
	}
	public void setIlliteratesPercent(BigDecimal illiteratesPercent) {
		this.illiteratesPercent = illiteratesPercent;
	}
	public BigDecimal getWorkingPeoplePercent() {
		return workingPeoplePercent;
	}
	public void setWorkingPeoplePercent(BigDecimal workingPeoplePercent) {
		this.workingPeoplePercent = workingPeoplePercent;
	}
	public BigDecimal getNonWorkingPeoplePercent() {
		return nonWorkingPeoplePercent;
	}
	public void setNonWorkingPeoplePercent(BigDecimal nonWorkingPeoplePercent) {
		this.nonWorkingPeoplePercent = nonWorkingPeoplePercent;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
}
