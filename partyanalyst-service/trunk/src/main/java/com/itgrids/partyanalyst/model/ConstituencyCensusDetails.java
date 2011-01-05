/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 31, 2010
 */

package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * constituency_census_details entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="constituency_census_details")
public class ConstituencyCensusDetails extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -3891389405500691334L;
	
	private Long constituencyCensusId;
	private Long stateId;
	private Long constituencyId;
	private String level;
	private Long year;
	private String tru;
	private Long houseHolds;
	private Long totalPopulation;
	private Long totalMalePopulation;
	private Long totalFemalePopulation;
	private Long populationUnderSix;
	private Long maleUnderSix;
	private Long femaleUnderSix;
	private Long populationSC;
	private Long maleSC;
	private Long femaleSC;	
	private Long populationST;
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
	private Double sexRatioUnderSix;
	private Double houseHoldsSize;
	private Double percentageSC;
	private Double percentageST;
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
	
	
	/** default constructor */
	public ConstituencyCensusDetails() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_census_id", unique = true, nullable = false)
	public Long getConstituencyCensusId() {
		return constituencyCensusId;
	}

	public void setConstituencyCensusId(Long constituencyCensusId) {
		this.constituencyCensusId = constituencyCensusId;
	}

	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	@Column(name = "constituency_id")	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name = "level", length = 20)
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name = "year")
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
	@Column(name = "TRU")
	public String getTru() {
		return tru;
	}
	public void setTru(String tru) {
		this.tru = tru;
	}
	
	@Column(name = "No_HH")
	public Long getHouseHolds() {
		return houseHolds;
	}
	public void setHouseHolds(Long houseHolds) {
		this.houseHolds = houseHolds;
	}
	
	@Column(name = "TOT_P")
	public Long getTotalPopulation() {
		return totalPopulation;
	}
	public void setTotalPopulation(Long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	
	@Column(name = "TOT_M")
	public Long getTotalMalePopulation() {
		return totalMalePopulation;
	}
	public void setTotalMalePopulation(Long totalMalePopulation) {
		this.totalMalePopulation = totalMalePopulation;
	}
	
	@Column(name = "TOT_F")
	public Long getTotalFemalePopulation() {
		return totalFemalePopulation;
	}
	public void setTotalFemalePopulation(Long totalFemalePopulation) {
		this.totalFemalePopulation = totalFemalePopulation;
	}
	
	@Column(name = "P_06")
	public Long getPopulationUnderSix() {
		return populationUnderSix;
	}
	public void setPopulationUnderSix(Long populationUnderSix) {
		this.populationUnderSix = populationUnderSix;
	}
	
	@Column(name = "M_06")
	public Long getMaleUnderSix() {
		return maleUnderSix;
	}
	public void setMaleUnderSix(Long maleUnderSix) {
		this.maleUnderSix = maleUnderSix;
	}
	
	@Column(name = "F_06")
	public Long getFemaleUnderSix() {
		return femaleUnderSix;
	}
	public void setFemaleUnderSix(Long femaleUnderSix) {
		this.femaleUnderSix = femaleUnderSix;
	}
	
	@Column(name = "P_SC")
	public Long getPopulationSC() {
		return populationSC;
	}
	public void setPopulationSC(Long populationSC) {
		this.populationSC = populationSC;
	}
	
	@Column(name = "M_SC")
	public Long getMaleSC() {
		return maleSC;
	}
	public void setMaleSC(Long maleSC) {
		this.maleSC = maleSC;
	}
	
	@Column(name = "F_SC")
	public Long getFemaleSC() {
		return femaleSC;
	}
	public void setFemaleSC(Long femaleSC) {
		this.femaleSC = femaleSC;
	}
	
	@Column(name = "P_ST")
	public Long getPopulationST() {
		return populationST;
	}
	public void setPopulationST(Long populationST) {
		this.populationST = populationST;
	}
	
	@Column(name = "M_ST")
	public Long getMaleST() {
		return maleST;
	}
	public void setMaleST(Long maleST) {
		this.maleST = maleST;
	}
	
	@Column(name = "F_ST")
	public Long getFemaleST() {
		return femaleST;
	}
	public void setFemaleST(Long femaleST) {
		this.femaleST = femaleST;
	}
	
	@Column(name = "P_LIT")
	public Long getPopulationLiterates() {
		return populationLiterates;
	}
	public void setPopulationLiterates(Long populationLiterates) {
		this.populationLiterates = populationLiterates;
	}
	
	@Column(name = "M_LIT")
	public Long getMaleLiterates() {
		return maleLiterates;
	}
	public void setMaleLiterates(Long maleLiterates) {
		this.maleLiterates = maleLiterates;
	}
	
	@Column(name = "F_LIT")
	public Long getFemaleLiterates() {
		return femaleLiterates;
	}
	public void setFemaleLiterates(Long femaleLiterates) {
		this.femaleLiterates = femaleLiterates;
	}
	
	@Column(name = "P_ILL")
	public Long getPopulationIlliterates() {
		return populationIlliterates;
	}
	public void setPopulationIlliterates(Long populationIlliterates) {
		this.populationIlliterates = populationIlliterates;
	}
	
	@Column(name = "M_ILL")
	public Long getMaleIlliterates() {
		return maleIlliterates;
	}
	public void setMaleIlliterates(Long maleIlliterates) {
		this.maleIlliterates = maleIlliterates;
	}
	
	@Column(name = "F_ILL")
	public Long getFemaleIlliterates() {
		return femaleIlliterates;
	}
	public void setFemaleIlliterates(Long femaleIlliterates) {
		this.femaleIlliterates = femaleIlliterates;
	}
	
	@Column(name = "TOT_WORK_P")
	public Long getWorkingPopulation() {
		return workingPopulation;
	}
	public void setWorkingPopulation(Long workingPopulation) {
		this.workingPopulation = workingPopulation;
	}
	
	@Column(name = "TOT_WORK_M")
	public Long getWorkingMale() {
		return workingMale;
	}
	public void setWorkingMale(Long workingMale) {
		this.workingMale = workingMale;
	}
	
	@Column(name = "TOT_WORK_F")
	public Long getWorkingFemale() {
		return workingFemale;
	}
	public void setWorkingFemale(Long workingFemale) {
		this.workingFemale = workingFemale;
	}
	
	@Column(name = "MAINWORK_P")
	public Long getMainWorkPopulation() {
		return mainWorkPopulation;
	}
	public void setMainWorkPopulation(Long mainWorkPopulation) {
		this.mainWorkPopulation = mainWorkPopulation;
	}
	
	@Column(name = "MAINWORK_M")
	public Long getMainWorkMale() {
		return mainWorkMale;
	}
	public void setMainWorkMale(Long mainWorkMale) {
		this.mainWorkMale = mainWorkMale;
	}
	
	@Column(name = "MAINWORK_F")
	public Long getMainWorkFemale() {
		return mainWorkFemale;
	}
	public void setMainWorkFemale(Long mainWorkFemale) {
		this.mainWorkFemale = mainWorkFemale;
	}
	
	@Column(name = "MAIN_CL_P")
	public Long getMainCLPopulation() {
		return mainCLPopulation;
	}
	public void setMainCLPopulation(Long mainCLPopulation) {
		this.mainCLPopulation = mainCLPopulation;
	}
	
	@Column(name = "MAIN_CL_M")
	public Long getMainCLMale() {
		return mainCLMale;
	}
	public void setMainCLMale(Long mainCLMale) {
		this.mainCLMale = mainCLMale;
	}
	
	@Column(name = "MAIN_CL_F")
	public Long getMainCLFemale() {
		return mainCLFemale;
	}
	public void setMainCLFemale(Long mainCLFemale) {
		this.mainCLFemale = mainCLFemale;
	}
	
	@Column(name = "MAIN_AL_P")
	public Long getMainALPopulation() {
		return mainALPopulation;
	}
	public void setMainALPopulation(Long mainALPopulation) {
		this.mainALPopulation = mainALPopulation;
	}
	
	@Column(name = "MAIN_AL_M")
	public Long getMainALMale() {
		return mainALMale;
	}
	public void setMainALMale(Long mainALMale) {
		this.mainALMale = mainALMale;
	}
	
	@Column(name = "MAIN_AL_F")
	public Long getMainALFemale() {
		return mainALFemale;
	}
	public void setMainALFemale(Long mainALFemale) {
		this.mainALFemale = mainALFemale;
	}
	
	@Column(name = "MAIN_HH_P")
	public Long getMainHHPopulation() {
		return mainHHPopulation;
	}
	public void setMainHHPopulation(Long mainHHPopulation) {
		this.mainHHPopulation = mainHHPopulation;
	}
	
	@Column(name = "MAIN_HH_M")
	public Long getMainHHMale() {
		return mainHHMale;
	}
	public void setMainHHMale(Long mainHHMale) {
		this.mainHHMale = mainHHMale;
	}
	
	@Column(name = "MAIN_HH_F")
	public Long getMainHHFemale() {
		return mainHHFemale;
	}
	public void setMainHHFemale(Long mainHHFemale) {
		this.mainHHFemale = mainHHFemale;
	}
	
	@Column(name = "MAIN_OT_P")
	public Long getMainOTPopulation() {
		return mainOTPopulation;
	}
	public void setMainOTPopulation(Long mainOTPopulation) {
		this.mainOTPopulation = mainOTPopulation;
	}
	
	@Column(name = "MAIN_OT_M")
	public Long getMainOTMale() {
		return mainOTMale;
	}
	public void setMainOTMale(Long mainOTMale) {
		this.mainOTMale = mainOTMale;
	}
	
	@Column(name = "MAIN_OT_F")
	public Long getMainOTFemale() {
		return mainOTFemale;
	}
	public void setMainOTFemale(Long mainOTFemale) {
		this.mainOTFemale = mainOTFemale;
	}
	
	@Column(name = "MARGWORK_P")
	public Long getMargWorkPopulation() {
		return margWorkPopulation;
	}
	public void setMargWorkPopulation(Long margWorkPopulation) {
		this.margWorkPopulation = margWorkPopulation;
	}
	
	@Column(name = "MARGWORK_M")
	public Long getMargWorkMale() {
		return margWorkMale;
	}
	public void setMargWorkMale(Long margWorkMale) {
		this.margWorkMale = margWorkMale;
	}
	
	@Column(name = "MARGWORK_F")
	public Long getMargWorkFemale() {
		return margWorkFemale;
	}
	public void setMargWorkFemale(Long margWorkFemale) {
		this.margWorkFemale = margWorkFemale;
	}
	
	@Column(name = "MARG_CL_P")
	public Long getMargCLPopulation() {
		return margCLPopulation;
	}
	public void setMargCLPopulation(Long margCLPopulation) {
		this.margCLPopulation = margCLPopulation;
	}
	
	@Column(name = "MARG_CL_M")
	public Long getMargCLMale() {
		return margCLMale;
	}
	public void setMargCLMale(Long margCLMale) {
		this.margCLMale = margCLMale;
	}
	
	@Column(name = "MARG_CL_F")
	public Long getMargCLFemale() {
		return margCLFemale;
	}
	public void setMargCLFemale(Long margCLFemale) {
		this.margCLFemale = margCLFemale;
	}
	
	@Column(name = "MARG_AL_P")
	public Long getMargALPopulation() {
		return margALPopulation;
	}
	public void setMargALPopulation(Long margALPopulation) {
		this.margALPopulation = margALPopulation;
	}
	
	@Column(name = "MARG_AL_M")
	public Long getMargALMale() {
		return margALMale;
	}
	public void setMargALMale(Long margALMale) {
		this.margALMale = margALMale;
	}
	
	@Column(name = "MARG_AL_F")
	public Long getMargALFemale() {
		return margALFemale;
	}
	public void setMargALFemale(Long margALFemale) {
		this.margALFemale = margALFemale;
	}
	
	@Column(name = "MARG_HH_P")
	public Long getMargHHPopulation() {
		return margHHPopulation;
	}
	public void setMargHHPopulation(Long margHHPopulation) {
		this.margHHPopulation = margHHPopulation;
	}
	
	@Column(name = "MARG_HH_M")
	public Long getMargHHMale() {
		return margHHMale;
	}
	public void setMargHHMale(Long margHHMale) {
		this.margHHMale = margHHMale;
	}
	
	@Column(name = "MARG_HH_F")
	public Long getMargHHFemale() {
		return margHHFemale;
	}
	public void setMargHHFemale(Long margHHFemale) {
		this.margHHFemale = margHHFemale;
	}
	
	@Column(name = "MARG_OT_P")
	public Long getMargOTPopulation() {
		return margOTPopulation;
	}
	public void setMargOTPopulation(Long margOTPopulation) {
		this.margOTPopulation = margOTPopulation;
	}
	
	@Column(name = "MARG_OT_M")
	public Long getMargOTMale() {
		return margOTMale;
	}
	public void setMargOTMale(Long margOTMale) {
		this.margOTMale = margOTMale;
	}
	
	@Column(name = "MARG_OT_F")
	public Long getMargOTFemale() {
		return margOTFemale;
	}
	public void setMargOTFemale(Long margOTFemale) {
		this.margOTFemale = margOTFemale;
	}
	
	@Column(name = "NON_WORK_P")
	public Long getNonWorkingPopulation() {
		return nonWorkingPopulation;
	}
	public void setNonWorkingPopulation(Long nonWorkingPopulation) {
		this.nonWorkingPopulation = nonWorkingPopulation;
	}
	
	@Column(name = "NON_WORK_M")
	public Long getNonWorkingMale() {
		return nonWorkingMale;
	}
	public void setNonWorkingMale(Long nonWorkingMale) {
		this.nonWorkingMale = nonWorkingMale;
	}
	
	@Column(name = "NON_WORK_F")
	public Long getNonWorkingFemale() {
		return nonWorkingFemale;
	}
	public void setNonWorkingFemale(Long nonWorkingFemale) {
		this.nonWorkingFemale = nonWorkingFemale;
	}
	
	@Column(name = "SEXRATIO")
	public Double getSexRatio() {
		return sexRatio;
	}
	public void setSexRatio(Double sexRatio) {
		this.sexRatio = sexRatio;
	}
	
	@Column(name = "SEXRATIO_SC")
	public Double getSexRatioSC() {
		return sexRatioSC;
	}
	public void setSexRatioSC(Double sexRatioSC) {
		this.sexRatioSC = sexRatioSC;
	}
	
	@Column(name = "SEXRATIO_ST")
	public Double getSexRatioST() {
		return sexRatioST;
	}
	public void setSexRatioST(Double sexRatioST) {
		this.sexRatioST = sexRatioST;
	}
	
	@Column(name = "SEXRATIO_06")
	public Double getSexRatioUnderSix() {
		return sexRatioUnderSix;
	}
	public void setSexRatioUnderSix(Double sexRatioUnderSix) {
		this.sexRatioUnderSix = sexRatioUnderSix;
	}
	
	@Column(name = "HH_SIZE")
	public Double getHouseHoldsSize() {
		return houseHoldsSize;
	}
	public void setHouseHoldsSize(Double houseHoldsSize) {
		this.houseHoldsSize = houseHoldsSize;
	}
	
	@Column(name = "SC_PER")
	public Double getPercentageSC() {
		return percentageSC;
	}
	public void setPercentageSC(Double percentageSC) {
		this.percentageSC = percentageSC;
	}
	
	@Column(name = "ST_PER")
	public Double getPercentageST() {
		return percentageST;
	}
	public void setPercentageST(Double percentageST) {
		this.percentageST = percentageST;
	}
	
	@Column(name = "M_LIT_RATE")
	public Double getMaleLiteratureRate() {
		return maleLiteratureRate;
	}
	public void setMaleLiteratureRate(Double maleLiteratureRate) {
		this.maleLiteratureRate = maleLiteratureRate;
	}
	
	@Column(name = "F_LIT_RATE")
	public Double getFemaleLiteratureRate() {
		return femaleLiteratureRate;
	}
	public void setFemaleLiteratureRate(Double femaleLiteratureRate) {
		this.femaleLiteratureRate = femaleLiteratureRate;
	}
	
	@Column(name = "GENDER_GAP")
	public Double getGenderGap() {
		return genderGap;
	}
	public void setGenderGap(Double genderGap) {
		this.genderGap = genderGap;
	}
	
	@Column(name = "P_LIT_PER")
	public Double getPopLiteraturePercentage() {
		return popLiteraturePercentage;
	}
	public void setPopLiteraturePercentage(Double popLiteraturePercentage) {
		this.popLiteraturePercentage = popLiteraturePercentage;
	}
	
	@Column(name = "M_LIT_PER")
	public Double getMaleLiteraturePercentage() {
		return maleLiteraturePercentage;
	}
	public void setMaleLiteraturePercentage(Double maleLiteraturePercentage) {
		this.maleLiteraturePercentage = maleLiteraturePercentage;
	}
	
	@Column(name = "F_LIT_PER")
	public Double getFemaleLiteraturePercentage() {
		return femaleLiteraturePercentage;
	}
	public void setFemaleLiteraturePercentage(Double femaleLiteraturePercentage) {
		this.femaleLiteraturePercentage = femaleLiteraturePercentage;
	}
	
	@Column(name = "TOT_P_PER")
	public Double getTotalPopPercentage() {
		return totalPopPercentage;
	}
	public void setTotalPopPercentage(Double totalPopPercentage) {
		this.totalPopPercentage = totalPopPercentage;
	}
	
	@Column(name = "TOT_WOR_PER_P")
	public Double getTotalWorkingPopPercentage() {
		return totalWorkingPopPercentage;
	}
	public void setTotalWorkingPopPercentage(Double totalWorkingPopPercentage) {
		this.totalWorkingPopPercentage = totalWorkingPopPercentage;
	}
	
	@Column(name = "TOT_WOR_PER_M")
	public Double getTotalWorkingMalePercentage() {
		return totalWorkingMalePercentage;
	}
	public void setTotalWorkingMalePercentage(Double totalWorkingMalePercentage) {
		this.totalWorkingMalePercentage = totalWorkingMalePercentage;
	}
	
	@Column(name = "TOT_WOR_PER_F")
	public Double getTotalWorkingFemalePercentage() {
		return totalWorkingFemalePercentage;
	}
	public void setTotalWorkingFemalePercentage(Double totalWorkingFemalePercentage) {
		this.totalWorkingFemalePercentage = totalWorkingFemalePercentage;
	}
	
	@Column(name = "TOT_MAIN_PER_P")
	public Double getTotalMainPopPercentage() {
		return totalMainPopPercentage;
	}
	public void setTotalMainPopPercentage(Double totalMainPopPercentage) {
		this.totalMainPopPercentage = totalMainPopPercentage;
	}
	
	@Column(name = "TOT_MAIN_PER_M")
	public Double getTotalMainMalePercentage() {
		return totalMainMalePercentage;
	}
	public void setTotalMainMalePercentage(Double totalMainMalePercentage) {
		this.totalMainMalePercentage = totalMainMalePercentage;
	}
	
	@Column(name = "TOT_MAIN_PER_F")
	public Double getTotalMainFemalePercentage() {
		return totalMainFemalePercentage;
	}
	public void setTotalMainFemalePercentage(Double totalMainFemalePercentage) {
		this.totalMainFemalePercentage = totalMainFemalePercentage;
	}
	
	@Column(name = "TOT_MARG_PER_P")
	public Double getTotalMargPopPercentage() {
		return totalMargPopPercentage;
	}
	public void setTotalMargPopPercentage(Double totalMargPopPercentage) {
		this.totalMargPopPercentage = totalMargPopPercentage;
	}
	
	@Column(name = "TOT_MARG_PER_M")
	public Double getTotalMargMalePercentage() {
		return totalMargMalePercentage;
	}
	public void setTotalMargMalePercentage(Double totalMargMalePercentage) {
		this.totalMargMalePercentage = totalMargMalePercentage;
	}
	
	@Column(name = "TOT_MARG_PER_F")
	public Double getTotalMargFemalePercentage() {
		return totalMargFemalePercentage;
	}
	public void setTotalMargFemalePercentage(Double totalMargFemalePercentage) {
		this.totalMargFemalePercentage = totalMargFemalePercentage;
	}
	
	@Column(name = "NON_WORK_PER_P")
	public Double getNonWorkingPopPercentage() {
		return nonWorkingPopPercentage;
	}
	public void setNonWorkingPopPercentage(Double nonWorkingPopPercentage) {
		this.nonWorkingPopPercentage = nonWorkingPopPercentage;
	}
	
	@Column(name = "NON_WORK_PER_M")
	public Double getNonWorkingMalePercentage() {
		return nonWorkingMalePercentage;
	}
	public void setNonWorkingMalePercentage(Double nonWorkingMalePercentage) {
		this.nonWorkingMalePercentage = nonWorkingMalePercentage;
	}
	
	@Column(name = "NON_WORK_PER_F")
	public Double getNonWorkingFemalePercentage() {
		return nonWorkingFemalePercentage;
	}
	public void setNonWorkingFemalePercentage(Double nonWorkingFemalePercentage) {
		this.nonWorkingFemalePercentage = nonWorkingFemalePercentage;
	}
	
	@Column(name = "CL_PER_P")
	public Double getPopCLPercentage() {
		return popCLPercentage;
	}
	public void setPopCLPercentage(Double popCLPercentage) {
		this.popCLPercentage = popCLPercentage;
	}
	
	@Column(name = "CL_PER_M")
	public Double getMaleCLPercentage() {
		return maleCLPercentage;
	}
	public void setMaleCLPercentage(Double maleCLPercentage) {
		this.maleCLPercentage = maleCLPercentage;
	}
	
	@Column(name = "CL_PER_F")
	public Double getFemaleCLPercentage() {
		return femaleCLPercentage;
	}
	public void setFemaleCLPercentage(Double femaleCLPercentage) {
		this.femaleCLPercentage = femaleCLPercentage;
	}
	
	@Column(name = "AL_PER_P")
	public Double getPopALPercentage() {
		return popALPercentage;
	}
	public void setPopALPercentage(Double popALPercentage) {
		this.popALPercentage = popALPercentage;
	}
	
	@Column(name = "AL_PER_M")
	public Double getMaleALPercentage() {
		return maleALPercentage;
	}
	public void setMaleALPercentage(Double maleALPercentage) {
		this.maleALPercentage = maleALPercentage;
	}
	
	@Column(name = "AL_PER_F")
	public Double getFemaleALPercentage() {
		return femaleALPercentage;
	}
	public void setFemaleALPercentage(Double femaleALPercentage) {
		this.femaleALPercentage = femaleALPercentage;
	}
	
	@Column(name = "HH_PER_P")
	public Double getPopHHPercentage() {
		return popHHPercentage;
	}
	public void setPopHHPercentage(Double popHHPercentage) {
		this.popHHPercentage = popHHPercentage;
	}
	
	@Column(name = "HH_PER_M")
	public Double getMaleHHPercentage() {
		return maleHHPercentage;
	}
	public void setMaleHHPercentage(Double maleHHPercentage) {
		this.maleHHPercentage = maleHHPercentage;
	}
	
	@Column(name = "HH_PER_F")
	public Double getFemaleHHPercentage() {
		return femaleHHPercentage;
	}
	public void setFemaleHHPercentage(Double femaleHHPercentage) {
		this.femaleHHPercentage = femaleHHPercentage;
	}
	
	@Column(name = "OW_PER_P")
	public Double getPopOWPercentage() {
		return popOWPercentage;
	}
	public void setPopOWPercentage(Double popOWPercentage) {
		this.popOWPercentage = popOWPercentage;
	}
	
	@Column(name = "OW_PER_M")
	public Double getMaleOWPercentage() {
		return maleOWPercentage;
	}
	public void setMaleOWPercentage(Double maleOWPercentage) {
		this.maleOWPercentage = maleOWPercentage;
	}
	
	@Column(name = "OW_PER_F")
	public Double getFemaleOWPercentage() {
		return femaleOWPercentage;
	}
	public void setFemaleOWPercentage(Double femaleOWPercentage) {
		this.femaleOWPercentage = femaleOWPercentage;
	}
	
	@Column(name = "CL_MAIN_MARG_P")
	public Long getMainMargCLPopulation() {
		return mainMargCLPopulation;
	}
	public void setMainMargCLPopulation(Long mainMargCLPopulation) {
		this.mainMargCLPopulation = mainMargCLPopulation;
	}
	
	@Column(name = "CL_MAIN_MARG_M")
	public Long getMainMargCLMale() {
		return mainMargCLMale;
	}
	public void setMainMargCLMale(Long mainMargCLMale) {
		this.mainMargCLMale = mainMargCLMale;
	}
	
	@Column(name = "CL_MAIN_MARG_F")
	public Long getMainMargCLFemale() {
		return mainMargCLFemale;
	}
	public void setMainMargCLFemale(Long mainMargCLFemale) {
		this.mainMargCLFemale = mainMargCLFemale;
	}
	
	@Column(name = "AL_MAIN_MARG_P")
	public Long getMainMargALPopulation() {
		return mainMargALPopulation;
	}
	public void setMainMargALPopulation(Long mainMargALPopulation) {
		this.mainMargALPopulation = mainMargALPopulation;
	}
	
	@Column(name = "AL_MAIN_MARG_M")
	public Long getMainMargALMale() {
		return mainMargALMale;
	}
	public void setMainMargALMale(Long mainMargALMale) {
		this.mainMargALMale = mainMargALMale;
	}
	
	@Column(name = "AL_MAIN_MARG_F")
	public Long getMainMargALFemale() {
		return mainMargALFemale;
	}
	public void setMainMargALFemale(Long mainMargALFemale) {
		this.mainMargALFemale = mainMargALFemale;
	}
	
	@Column(name = "HH_MAIN_MARG_P")
	public Long getMainMargHHPopulation() {
		return mainMargHHPopulation;
	}
	public void setMainMargHHPopulation(Long mainMargHHPopulation) {
		this.mainMargHHPopulation = mainMargHHPopulation;
	}
	
	@Column(name = "HH_MAIN_MARG_M")
	public Long getMainMargHHMale() {
		return mainMargHHMale;
	}
	public void setMainMargHHMale(Long mainMargHHMale) {
		this.mainMargHHMale = mainMargHHMale;
	}
	
	@Column(name = "HH_MAIN_MARG_F")
	public Long getMainMargHHFemale() {
		return mainMargHHFemale;
	}
	public void setMainMargHHFemale(Long mainMargHHFemale) {
		this.mainMargHHFemale = mainMargHHFemale;
	}
	
	@Column(name = "OW_MAIN_MARG_P")
	public Long getMainMargOWPopulation() {
		return mainMargOWPopulation;
	}
	public void setMainMargOWPopulation(Long mainMargOWPopulation) {
		this.mainMargOWPopulation = mainMargOWPopulation;
	}
	
	@Column(name = "OW_MAIN_MARG_M")
	public Long getMainMargOWMale() {
		return mainMargOWMale;
	}
	public void setMainMargOWMale(Long mainMargOWMale) {
		this.mainMargOWMale = mainMargOWMale;
	}
	
	@Column(name = "OW_MAIN_MARG_F")
	public Long getMainMargOWFemale() {
		return mainMargOWFemale;
	}
	public void setMainMargOWFemale(Long mainMargOWFemale) {
		this.mainMargOWFemale = mainMargOWFemale;
	}
	
	@Column(name = "WPR")
	public Double getWpr() {
		return wpr;
	}
	public void setWpr(Double wpr) {
		this.wpr = wpr;
	}

}
