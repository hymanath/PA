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
	private Long constituencyId;
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
	private String range;
	private List<Long>locationIds;
    private Integer count;
    private Long seatsWon;
    private Long seatsParticipated;
    private BigDecimal avgPercent;
    private List<SelectOptionVO> mapeedConstituencies;
    private List<SelectOptionVO> unMapeedConstituencies;
    private List<SelectOptionVO> existedConstituencies;
	
	private String level;
	private Long houseHolds;
	private Long populationUnderSix;
	private Long maleUnderSix;
	private Long femaleUnderSix;
	private Long maleSC;
	private Long femaleSC;	
	private Long maleST;
	private Long femaleST;
	private Long populationLiterates;
	private Long maleLiterates;
	private Long femaleLiterates;
	private Long populationIlliterates;
	private Long maleIlliterates;
	private Long femaleIlliterates;
	private Long workingPopulation;
	private Long workingMale;
	private Long workingFemale;
	private Long mainWorkPopulation;
	private Long mainWorkMale;
	private Long mainWorkFemale;
	private Long mainCLPopulation;
	private Long mainCLMale;
	private Long mainCLFemale;
	private Long mainALPopulation;
	private Long mainALMale;
	private Long mainALFemale;
	private Long mainHHPopulation;
	private Long mainHHMale;
	private Long mainHHFemale;
	private Long mainOTPopulation;
	private Long mainOTMale;
	private Long mainOTFemale;
	private Long margWorkPopulation;
	private Long margWorkMale;
	private Long margWorkFemale;
	private Long margCLPopulation;
	private Long margCLMale;
	private Long margCLFemale;
	private Long margALPopulation;
	private Long margALMale;
	private Long margALFemale;
	private Long margHHPopulation;
	private Long margHHMale;
	private Long margHHFemale;
	private Long margOTPopulation;
	private Long margOTMale;
	private Long margOTFemale;
	private Long nonWorkingPopulation;
	private Long nonWorkingMale;
	private Long nonWorkingFemale;
	private Double sexRatio;
	private Double sexRatioSC;
	private Double sexRatioST;
	private Double houseHoldsSize;
	private Double percentageSC;
	private Double percentageST;
	private Double sexRatioUnderSix;
	private Double maleLiteratureRate;
	private Double femaleLiteratureRate;
	private Double genderGap;
	private Double popLiteraturePercentage;
	private Double maleLiteraturePercentage;
	private Double femaleLiteraturePercentage;
	private Double totalPopPercentage;
	private Double totalWorkingPopPercentage;
	private Double totalWorkingMalePercentage;
	private Double totalWorkingFemalePercentage;
	private Double totalMainPopPercentage;
	private Double totalMainMalePercentage;
	private Double totalMainFemalePercentage;
	private Double totalMargPopPercentage;
	private Double totalMargMalePercentage;
	private Double totalMargFemalePercentage;
	private Double nonWorkingPopPercentage;
	private Double nonWorkingMalePercentage;
	private Double nonWorkingFemalePercentage;
	private Double popCLPercentage;
	private Double maleCLPercentage;
	private Double femaleCLPercentage;
	private Double popALPercentage;
	private Double maleALPercentage;
	private Double femaleALPercentage;
	private Double popHHPercentage;
	private Double maleHHPercentage;
	private Double femaleHHPercentage;
	private Double popOWPercentage;
	private Double maleOWPercentage;
	private Double femaleOWPercentage;
	private Long mainMargCLPopulation;
	private Long mainMargCLMale;
	private Long mainMargCLFemale;
	private Long mainMargALPopulation;
	private Long mainMargALMale;
	private Long mainMargALFemale;
	private Long mainMargHHPopulation;
	private Long mainMargHHMale;
	private Long mainMargHHFemale;
	private Long mainMargOWPopulation;
	private Long mainMargOWMale;
	private Long mainMargOWFemale;
	private Double wpr;
	private Long totalConstituencies;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public Long getMainMargALPopulation() {
		return mainMargALPopulation;
	}
	public void setMainMargALPopulation(Long mainMargALPopulation) {
		this.mainMargALPopulation = mainMargALPopulation;
	}
	public Long getMainMargALMale() {
		return mainMargALMale;
	}
	public void setMainMargALMale(Long mainMargALMale) {
		this.mainMargALMale = mainMargALMale;
	}
	public Long getMainMargALFemale() {
		return mainMargALFemale;
	}
	public void setMainMargALFemale(Long mainMargALFemale) {
		this.mainMargALFemale = mainMargALFemale;
	}
	public Long getMainMargHHPopulation() {
		return mainMargHHPopulation;
	}
	public void setMainMargHHPopulation(Long mainMargHHPopulation) {
		this.mainMargHHPopulation = mainMargHHPopulation;
	}
	public Long getMainMargHHMale() {
		return mainMargHHMale;
	}
	public void setMainMargHHMale(Long mainMargHHMale) {
		this.mainMargHHMale = mainMargHHMale;
	}
	public Long getMainMargHHFemale() {
		return mainMargHHFemale;
	}
	public void setMainMargHHFemale(Long mainMargHHFemale) {
		this.mainMargHHFemale = mainMargHHFemale;
	}
	public Long getMainMargOWPopulation() {
		return mainMargOWPopulation;
	}
	public void setMainMargOWPopulation(Long mainMargOWPopulation) {
		this.mainMargOWPopulation = mainMargOWPopulation;
	}
	public Long getMainMargOWMale() {
		return mainMargOWMale;
	}
	public void setMainMargOWMale(Long mainMargOWMale) {
		this.mainMargOWMale = mainMargOWMale;
	}
	public Long getMainMargOWFemale() {
		return mainMargOWFemale;
	}
	public void setMainMargOWFemale(Long mainMargOWFemale) {
		this.mainMargOWFemale = mainMargOWFemale;
	}
	public Double getWpr() {
		return wpr;
	}
	public void setWpr(Double wpr) {
		this.wpr = wpr;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public Double getPopOWPercentage() {
		return popOWPercentage;
	}
	public void setPopOWPercentage(Double popOWPercentage) {
		this.popOWPercentage = popOWPercentage;
	}
	public Double getMaleOWPercentage() {
		return maleOWPercentage;
	}
	public void setMaleOWPercentage(Double maleOWPercentage) {
		this.maleOWPercentage = maleOWPercentage;
	}
	public Double getFemaleOWPercentage() {
		return femaleOWPercentage;
	}
	public void setFemaleOWPercentage(Double femaleOWPercentage) {
		this.femaleOWPercentage = femaleOWPercentage;
	}
	public Long getMainMargCLPopulation() {
		return mainMargCLPopulation;
	}
	public void setMainMargCLPopulation(Long mainMargCLPopulation) {
		this.mainMargCLPopulation = mainMargCLPopulation;
	}
	public Long getMainMargCLMale() {
		return mainMargCLMale;
	}
	public void setMainMargCLMale(Long mainMargCLMale) {
		this.mainMargCLMale = mainMargCLMale;
	}
	public Long getMainMargCLFemale() {
		return mainMargCLFemale;
	}
	public void setMainMargCLFemale(Long mainMargCLFemale) {
		this.mainMargCLFemale = mainMargCLFemale;
	}
	public Double getPopALPercentage() {
		return popALPercentage;
	}
	public void setPopALPercentage(Double popALPercentage) {
		this.popALPercentage = popALPercentage;
	}
	public Double getMaleALPercentage() {
		return maleALPercentage;
	}
	public void setMaleALPercentage(Double maleALPercentage) {
		this.maleALPercentage = maleALPercentage;
	}
	public Double getFemaleALPercentage() {
		return femaleALPercentage;
	}
	public void setFemaleALPercentage(Double femaleALPercentage) {
		this.femaleALPercentage = femaleALPercentage;
	}
	public Double getPopHHPercentage() {
		return popHHPercentage;
	}
	public void setPopHHPercentage(Double popHHPercentage) {
		this.popHHPercentage = popHHPercentage;
	}
	public Double getMaleHHPercentage() {
		return maleHHPercentage;
	}
	public void setMaleHHPercentage(Double maleHHPercentage) {
		this.maleHHPercentage = maleHHPercentage;
	}
	public Double getFemaleHHPercentage() {
		return femaleHHPercentage;
	}
	public void setFemaleHHPercentage(Double femaleHHPercentage) {
		this.femaleHHPercentage = femaleHHPercentage;
	}
	public Double getNonWorkingPopPercentage() {
		return nonWorkingPopPercentage;
	}
	public void setNonWorkingPopPercentage(Double nonWorkingPopPercentage) {
		this.nonWorkingPopPercentage = nonWorkingPopPercentage;
	}
	public Double getNonWorkingMalePercentage() {
		return nonWorkingMalePercentage;
	}
	public void setNonWorkingMalePercentage(Double nonWorkingMalePercentage) {
		this.nonWorkingMalePercentage = nonWorkingMalePercentage;
	}
	public Double getNonWorkingFemalePercentage() {
		return nonWorkingFemalePercentage;
	}
	public void setNonWorkingFemalePercentage(Double nonWorkingFemalePercentage) {
		this.nonWorkingFemalePercentage = nonWorkingFemalePercentage;
	}
	public Double getPopCLPercentage() {
		return popCLPercentage;
	}
	public void setPopCLPercentage(Double popCLPercentage) {
		this.popCLPercentage = popCLPercentage;
	}
	public Double getMaleCLPercentage() {
		return maleCLPercentage;
	}
	public void setMaleCLPercentage(Double maleCLPercentage) {
		this.maleCLPercentage = maleCLPercentage;
	}
	public Double getFemaleCLPercentage() {
		return femaleCLPercentage;
	}
	public void setFemaleCLPercentage(Double femaleCLPercentage) {
		this.femaleCLPercentage = femaleCLPercentage;
	}
	public Double getTotalMainPopPercentage() {
		return totalMainPopPercentage;
	}
	public void setTotalMainPopPercentage(Double totalMainPopPercentage) {
		this.totalMainPopPercentage = totalMainPopPercentage;
	}
	public Double getTotalMainMalePercentage() {
		return totalMainMalePercentage;
	}
	public void setTotalMainMalePercentage(Double totalMainMalePercentage) {
		this.totalMainMalePercentage = totalMainMalePercentage;
	}
	public Double getTotalMainFemalePercentage() {
		return totalMainFemalePercentage;
	}
	public void setTotalMainFemalePercentage(Double totalMainFemalePercentage) {
		this.totalMainFemalePercentage = totalMainFemalePercentage;
	}
	public Double getTotalMargPopPercentage() {
		return totalMargPopPercentage;
	}
	public void setTotalMargPopPercentage(Double totalMargPopPercentage) {
		this.totalMargPopPercentage = totalMargPopPercentage;
	}
	public Double getTotalMargMalePercentage() {
		return totalMargMalePercentage;
	}
	public void setTotalMargMalePercentage(Double totalMargMalePercentage) {
		this.totalMargMalePercentage = totalMargMalePercentage;
	}
	public Double getTotalMargFemalePercentage() {
		return totalMargFemalePercentage;
	}
	public void setTotalMargFemalePercentage(Double totalMargFemalePercentage) {
		this.totalMargFemalePercentage = totalMargFemalePercentage;
	}
	public Double getTotalPopPercentage() {
		return totalPopPercentage;
	}
	public void setTotalPopPercentage(Double totalPopPercentage) {
		this.totalPopPercentage = totalPopPercentage;
	}
	public Double getTotalWorkingPopPercentage() {
		return totalWorkingPopPercentage;
	}
	public void setTotalWorkingPopPercentage(Double totalWorkingPopPercentage) {
		this.totalWorkingPopPercentage = totalWorkingPopPercentage;
	}
	public Double getTotalWorkingMalePercentage() {
		return totalWorkingMalePercentage;
	}
	public void setTotalWorkingMalePercentage(Double totalWorkingMalePercentage) {
		this.totalWorkingMalePercentage = totalWorkingMalePercentage;
	}
	public Double getTotalWorkingFemalePercentage() {
		return totalWorkingFemalePercentage;
	}
	public void setTotalWorkingFemalePercentage(Double totalWorkingFemalePercentage) {
		this.totalWorkingFemalePercentage = totalWorkingFemalePercentage;
	}
	public Double getPopLiteraturePercentage() {
		return popLiteraturePercentage;
	}
	public void setPopLiteraturePercentage(Double popLiteraturePercentage) {
		this.popLiteraturePercentage = popLiteraturePercentage;
	}
	public Double getMaleLiteraturePercentage() {
		return maleLiteraturePercentage;
	}
	public void setMaleLiteraturePercentage(Double maleLiteraturePercentage) {
		this.maleLiteraturePercentage = maleLiteraturePercentage;
	}
	public Double getFemaleLiteraturePercentage() {
		return femaleLiteraturePercentage;
	}
	public void setFemaleLiteraturePercentage(Double femaleLiteraturePercentage) {
		this.femaleLiteraturePercentage = femaleLiteraturePercentage;
	}
	public Double getSexRatioUnderSix() {
		return sexRatioUnderSix;
	}
	public void setSexRatioUnderSix(Double sexRatioUnderSix) {
		this.sexRatioUnderSix = sexRatioUnderSix;
	}
	public Double getMaleLiteratureRate() {
		return maleLiteratureRate;
	}
	public void setMaleLiteratureRate(Double maleLiteratureRate) {
		this.maleLiteratureRate = maleLiteratureRate;
	}
	public Double getFemaleLiteratureRate() {
		return femaleLiteratureRate;
	}
	public void setFemaleLiteratureRate(Double femaleLiteratureRate) {
		this.femaleLiteratureRate = femaleLiteratureRate;
	}
	public Double getGenderGap() {
		return genderGap;
	}
	public void setGenderGap(Double genderGap) {
		this.genderGap = genderGap;
	}
	public Double getSexRatio() {
		return sexRatio;
	}
	public void setSexRatio(Double sexRatio) {
		this.sexRatio = sexRatio;
	}
	public Double getSexRatioSC() {
		return sexRatioSC;
	}
	public void setSexRatioSC(Double sexRatioSC) {
		this.sexRatioSC = sexRatioSC;
	}
	public Double getSexRatioST() {
		return sexRatioST;
	}
	public void setSexRatioST(Double sexRatioST) {
		this.sexRatioST = sexRatioST;
	}
	public Double getHouseHoldsSize() {
		return houseHoldsSize;
	}
	public void setHouseHoldsSize(Double houseHoldsSize) {
		this.houseHoldsSize = houseHoldsSize;
	}
	public Double getPercentageSC() {
		return percentageSC;
	}
	public void setPercentageSC(Double percentageSC) {
		this.percentageSC = percentageSC;
	}
	public Double getPercentageST() {
		return percentageST;
	}
	public void setPercentageST(Double percentageST) {
		this.percentageST = percentageST;
	}
	public Long getMargOTPopulation() {
		return margOTPopulation;
	}
	public void setMargOTPopulation(Long margOTPopulation) {
		this.margOTPopulation = margOTPopulation;
	}
	public Long getMargOTMale() {
		return margOTMale;
	}
	public void setMargOTMale(Long margOTMale) {
		this.margOTMale = margOTMale;
	}
	public Long getMargOTFemale() {
		return margOTFemale;
	}
	public void setMargOTFemale(Long margOTFemale) {
		this.margOTFemale = margOTFemale;
	}
	public Long getNonWorkingPopulation() {
		return nonWorkingPopulation;
	}
	public void setNonWorkingPopulation(Long nonWorkingPopulation) {
		this.nonWorkingPopulation = nonWorkingPopulation;
	}
	public Long getNonWorkingMale() {
		return nonWorkingMale;
	}
	public void setNonWorkingMale(Long nonWorkingMale) {
		this.nonWorkingMale = nonWorkingMale;
	}
	public Long getNonWorkingFemale() {
		return nonWorkingFemale;
	}
	public void setNonWorkingFemale(Long nonWorkingFemale) {
		this.nonWorkingFemale = nonWorkingFemale;
	}
	public Long getMargALPopulation() {
		return margALPopulation;
	}
	public void setMargALPopulation(Long margALPopulation) {
		this.margALPopulation = margALPopulation;
	}
	public Long getMargALMale() {
		return margALMale;
	}
	public void setMargALMale(Long margALMale) {
		this.margALMale = margALMale;
	}
	public Long getMargALFemale() {
		return margALFemale;
	}
	public void setMargALFemale(Long margALFemale) {
		this.margALFemale = margALFemale;
	}
	public Long getMargHHPopulation() {
		return margHHPopulation;
	}
	public void setMargHHPopulation(Long margHHPopulation) {
		this.margHHPopulation = margHHPopulation;
	}
	public Long getMargHHMale() {
		return margHHMale;
	}
	public void setMargHHMale(Long margHHMale) {
		this.margHHMale = margHHMale;
	}
	public Long getMargHHFemale() {
		return margHHFemale;
	}
	public void setMargHHFemale(Long margHHFemale) {
		this.margHHFemale = margHHFemale;
	}
	public Long getMainOTPopulation() {
		return mainOTPopulation;
	}
	public void setMainOTPopulation(Long mainOTPopulation) {
		this.mainOTPopulation = mainOTPopulation;
	}
	public Long getMainOTMale() {
		return mainOTMale;
	}
	public void setMainOTMale(Long mainOTMale) {
		this.mainOTMale = mainOTMale;
	}
	public Long getMainOTFemale() {
		return mainOTFemale;
	}
	public void setMainOTFemale(Long mainOTFemale) {
		this.mainOTFemale = mainOTFemale;
	}
	public Long getMargWorkPopulation() {
		return margWorkPopulation;
	}
	public void setMargWorkPopulation(Long margWorkPopulation) {
		this.margWorkPopulation = margWorkPopulation;
	}
	public Long getMargWorkMale() {
		return margWorkMale;
	}
	public void setMargWorkMale(Long margWorkMale) {
		this.margWorkMale = margWorkMale;
	}
	public Long getMargWorkFemale() {
		return margWorkFemale;
	}
	public void setMargWorkFemale(Long margWorkFemale) {
		this.margWorkFemale = margWorkFemale;
	}
	public Long getMargCLPopulation() {
		return margCLPopulation;
	}
	public void setMargCLPopulation(Long margCLPopulation) {
		this.margCLPopulation = margCLPopulation;
	}
	public Long getMargCLMale() {
		return margCLMale;
	}
	public void setMargCLMale(Long margCLMale) {
		this.margCLMale = margCLMale;
	}
	public Long getMargCLFemale() {
		return margCLFemale;
	}
	public void setMargCLFemale(Long margCLFemale) {
		this.margCLFemale = margCLFemale;
	}
	public Long getMainCLPopulation() {
		return mainCLPopulation;
	}
	public void setMainCLPopulation(Long mainCLPopulation) {
		this.mainCLPopulation = mainCLPopulation;
	}
	public Long getMainCLMale() {
		return mainCLMale;
	}
	public void setMainCLMale(Long mainCLMale) {
		this.mainCLMale = mainCLMale;
	}
	public Long getMainCLFemale() {
		return mainCLFemale;
	}
	public void setMainCLFemale(Long mainCLFemale) {
		this.mainCLFemale = mainCLFemale;
	}
	public Long getMainALPopulation() {
		return mainALPopulation;
	}
	public void setMainALPopulation(Long mainALPopulation) {
		this.mainALPopulation = mainALPopulation;
	}
	public Long getMainALMale() {
		return mainALMale;
	}
	public void setMainALMale(Long mainALMale) {
		this.mainALMale = mainALMale;
	}
	public Long getMainALFemale() {
		return mainALFemale;
	}
	public void setMainALFemale(Long mainALFemale) {
		this.mainALFemale = mainALFemale;
	}
	public Long getMainHHPopulation() {
		return mainHHPopulation;
	}
	public void setMainHHPopulation(Long mainHHPopulation) {
		this.mainHHPopulation = mainHHPopulation;
	}
	public Long getMainHHMale() {
		return mainHHMale;
	}
	public void setMainHHMale(Long mainHHMale) {
		this.mainHHMale = mainHHMale;
	}
	public Long getMainHHFemale() {
		return mainHHFemale;
	}
	public void setMainHHFemale(Long mainHHFemale) {
		this.mainHHFemale = mainHHFemale;
	}
	public Long getMainWorkPopulation() {
		return mainWorkPopulation;
	}
	public void setMainWorkPopulation(Long mainWorkPopulation) {
		this.mainWorkPopulation = mainWorkPopulation;
	}
	public Long getMainWorkMale() {
		return mainWorkMale;
	}
	public void setMainWorkMale(Long mainWorkMale) {
		this.mainWorkMale = mainWorkMale;
	}
	public Long getMainWorkFemale() {
		return mainWorkFemale;
	}
	public void setMainWorkFemale(Long mainWorkFemale) {
		this.mainWorkFemale = mainWorkFemale;
	}
	public Long getWorkingPopulation() {
		return workingPopulation;
	}
	public void setWorkingPopulation(Long workingPopulation) {
		this.workingPopulation = workingPopulation;
	}
	public Long getWorkingMale() {
		return workingMale;
	}
	public void setWorkingMale(Long workingMale) {
		this.workingMale = workingMale;
	}
	public Long getWorkingFemale() {
		return workingFemale;
	}
	public void setWorkingFemale(Long workingFemale) {
		this.workingFemale = workingFemale;
	}
	public Long getPopulationLiterates() {
		return populationLiterates;
	}
	public void setPopulationLiterates(Long populationLiterates) {
		this.populationLiterates = populationLiterates;
	}
	public Long getMaleLiterates() {
		return maleLiterates;
	}
	public void setMaleLiterates(Long maleLiterates) {
		this.maleLiterates = maleLiterates;
	}
	public Long getFemaleLiterates() {
		return femaleLiterates;
	}
	public void setFemaleLiterates(Long femaleLiterates) {
		this.femaleLiterates = femaleLiterates;
	}
	public Long getPopulationIlliterates() {
		return populationIlliterates;
	}
	public void setPopulationIlliterates(Long populationIlliterates) {
		this.populationIlliterates = populationIlliterates;
	}
	public Long getMaleIlliterates() {
		return maleIlliterates;
	}
	public void setMaleIlliterates(Long maleIlliterates) {
		this.maleIlliterates = maleIlliterates;
	}
	public Long getFemaleIlliterates() {
		return femaleIlliterates;
	}
	public void setFemaleIlliterates(Long femaleIlliterates) {
		this.femaleIlliterates = femaleIlliterates;
	}
	public Long getMaleST() {
		return maleST;
	}
	public void setMaleST(Long maleST) {
		this.maleST = maleST;
	}
	public Long getFemaleST() {
		return femaleST;
	}
	public void setFemaleST(Long femaleST) {
		this.femaleST = femaleST;
	}
	public Long getMaleSC() {
		return maleSC;
	}
	public void setMaleSC(Long maleSC) {
		this.maleSC = maleSC;
	}
	public Long getFemaleSC() {
		return femaleSC;
	}
	public void setFemaleSC(Long femaleSC) {
		this.femaleSC = femaleSC;
	}
	public Long getMaleUnderSix() {
		return maleUnderSix;
	}
	public void setMaleUnderSix(Long maleUnderSix) {
		this.maleUnderSix = maleUnderSix;
	}
	public Long getFemaleUnderSix() {
		return femaleUnderSix;
	}
	public void setFemaleUnderSix(Long femaleUnderSix) {
		this.femaleUnderSix = femaleUnderSix;
	}
	public Long getPopulationUnderSix() {
		return populationUnderSix;
	}
	public void setPopulationUnderSix(Long populationUnderSix) {
		this.populationUnderSix = populationUnderSix;
	}
	public Long getHouseHolds() {
		return houseHolds;
	}
	public void setHouseHolds(Long houseHolds) {
		this.houseHolds = houseHolds;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<SelectOptionVO> getMapeedConstituencies() {
		return mapeedConstituencies;
	}
	public void setMapeedConstituencies(List<SelectOptionVO> mapeedConstituencies) {
		this.mapeedConstituencies = mapeedConstituencies;
	}
	public List<SelectOptionVO> getUnMapeedConstituencies() {
		return unMapeedConstituencies;
	}
	public void setUnMapeedConstituencies(
			List<SelectOptionVO> unMapeedConstituencies) {
		this.unMapeedConstituencies = unMapeedConstituencies;
	}
	public List<SelectOptionVO> getExistedConstituencies() {
		return existedConstituencies;
	}
	public void setExistedConstituencies(List<SelectOptionVO> existedConstituencies) {
		this.existedConstituencies = existedConstituencies;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getSeatsWon() {
		return seatsWon;
	}
	public void setSeatsWon(Long seatsWon) {
		this.seatsWon = seatsWon;
	}
	public Long getSeatsParticipated() {
		return seatsParticipated;
	}
	public void setSeatsParticipated(Long seatsParticipated) {
		this.seatsParticipated = seatsParticipated;
	}
	public BigDecimal getAvgPercent() {
		return avgPercent;
	}
	public void setAvgPercent(BigDecimal avgPercent) {
		this.avgPercent = avgPercent;
	}

}
