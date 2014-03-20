
package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.List;

public class StrategicCensusVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String stateName;
	private String districtName;
	private Integer count;
	private Integer year;
	private Long totalPopulation;
	private String totalPopulationPercentage;
	private Long malePopulation;
	private Long femalePopulation;
	private String malePopulationPercentage;
	private String femalePopulationPercentage;
	private Long houseHolds;
	private String houseHoldsPercentage;
	private Long populationSC;
	private BigDecimal populationSCPercent;
	private BigDecimal populationSTPercent;
	private Long populationST;
	private Long workingPeople;
	private String workingPeoplePercentage;
	
	private Long workingMale;
	private Double totalWorkingMalePercentage;
	private Long workingFemale;
	private Double totalWorkingFemalePercentage;
	private BigDecimal nonWorkingPeoplePercent;
	private Long nonWorkingPeople;
	private Long populationUnderSix;
	private String populationUnderSixPercentage;
	
	private Long literates;
	private String literatesPercentage;
	private Long maleLiterates;
	private Double maleLiteraturePercentage;
	private Long femaleLiterates;
	private Double femaleLiteraturePercentage;
	private Long differencePopulation;
	private String differencePopulationPercent;
	
	private Long differenceMalePopulation;
	private String differenceMalePercent;
	private Long differenceFemalePopulation;
	private String differenceFemalePercent;
	private Long differenceHouseHolds;
	private String differenceHouseHoldsPercent;
	private Long differenceSC;
	private String differenceSCPercent;
	private Long differenceST;
	private String differenceSTPercent;
	private Long differenceWorkingPeople;
	private String differenceWorkingPeoplePercent;
	
	private Long differenceMaleWorkingPeople;
	private String differenceMaleWorkingPeoplePercent;
	private Long differenceFemaleWorkingPeople;
	private String differenceFemaleWorkingPeoplePercent;
	private Long differenceNonWorkingPeople;
	private String differenceNonWorkingPeoplePercent;
	private Long differenceLessthan6Population;
	private String differenceLessthan6Percent;
	private String differenceLiteratesPercent;
	private Long differenceLiterates;

	private Long differenceMaleLiterates;
	private String differenceMaleLiteratesPercent;
	private Long differenceFemaleLiterates;
	private String differenceFemaleLiteratesPercent;
	private StrategicCensusVO districtDetails;
	private StrategicCensusVO stateDetails;
	private String message;
	private String conclusion;
	private String Heading;
	private List<StrategicCensusVO> CensusDetailsList;
	
	
	public String getHeading() {
		return Heading;
	}
	public void setHeading(String heading) {
		Heading = heading;
	}
	public StrategicCensusVO getDistrictDetails() {
		return districtDetails;
	}
	public void setDistrictDetails(StrategicCensusVO districtDetails) {
		this.districtDetails = districtDetails;
	}
	public StrategicCensusVO getStateDetails() {
		return stateDetails;
	}
	public void setStateDetails(StrategicCensusVO stateDetails) {
		this.stateDetails = stateDetails;
	}
	public List<StrategicCensusVO> getCensusDetailsList() {
		return CensusDetailsList;
	}
	public void setCensusDetailsList(List<StrategicCensusVO> censusDetailsList) {
		CensusDetailsList = censusDetailsList;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getDifferenceMaleLiterates() {
		return differenceMaleLiterates;
	}
	public void setDifferenceMaleLiterates(Long differenceMaleLiterates) {
		this.differenceMaleLiterates = differenceMaleLiterates;
	}
	public String getDifferenceMaleLiteratesPercent() {
		return differenceMaleLiteratesPercent;
	}
	public void setDifferenceMaleLiteratesPercent(
			String differenceMaleLiteratesPercent) {
		this.differenceMaleLiteratesPercent = differenceMaleLiteratesPercent;
	}
	public Long getDifferenceFemaleLiterates() {
		return differenceFemaleLiterates;
	}
	public void setDifferenceFemaleLiterates(Long differenceFemaleLiterates) {
		this.differenceFemaleLiterates = differenceFemaleLiterates;
	}
	public String getDifferenceFemaleLiteratesPercent() {
		return differenceFemaleLiteratesPercent;
	}
	public void setDifferenceFemaleLiteratesPercent(
			String differenceFemaleLiteratesPercent) {
		this.differenceFemaleLiteratesPercent = differenceFemaleLiteratesPercent;
	}
	public Long getDifferenceMaleWorkingPeople() {
		return differenceMaleWorkingPeople;
	}
	public void setDifferenceMaleWorkingPeople(Long differenceMaleWorkingPeople) {
		this.differenceMaleWorkingPeople = differenceMaleWorkingPeople;
	}
	public String getDifferenceMaleWorkingPeoplePercent() {
		return differenceMaleWorkingPeoplePercent;
	}
	public void setDifferenceMaleWorkingPeoplePercent(
			String differenceMaleWorkingPeoplePercent) {
		this.differenceMaleWorkingPeoplePercent = differenceMaleWorkingPeoplePercent;
	}
	public Long getDifferenceFemaleWorkingPeople() {
		return differenceFemaleWorkingPeople;
	}
	public void setDifferenceFemaleWorkingPeople(Long differenceFemaleWorkingPeople) {
		this.differenceFemaleWorkingPeople = differenceFemaleWorkingPeople;
	}
	public String getDifferenceFemaleWorkingPeoplePercent() {
		return differenceFemaleWorkingPeoplePercent;
	}
	public void setDifferenceFemaleWorkingPeoplePercent(
			String differenceFemaleWorkingPeoplePercent) {
		this.differenceFemaleWorkingPeoplePercent = differenceFemaleWorkingPeoplePercent;
	}
	public Long getDifferenceNonWorkingPeople() {
		return differenceNonWorkingPeople;
	}
	public void setDifferenceNonWorkingPeople(Long differenceNonWorkingPeople) {
		this.differenceNonWorkingPeople = differenceNonWorkingPeople;
	}
	public String getDifferenceNonWorkingPeoplePercent() {
		return differenceNonWorkingPeoplePercent;
	}
	public void setDifferenceNonWorkingPeoplePercent(
			String differenceNonWorkingPeoplePercent) {
		this.differenceNonWorkingPeoplePercent = differenceNonWorkingPeoplePercent;
	}
	public Long getDifferenceLessthan6Population() {
		return differenceLessthan6Population;
	}
	public void setDifferenceLessthan6Population(Long differenceLessthan6Population) {
		this.differenceLessthan6Population = differenceLessthan6Population;
	}
	public String getDifferenceLessthan6Percent() {
		return differenceLessthan6Percent;
	}
	public void setDifferenceLessthan6Percent(String differenceLessthan6Percent) {
		this.differenceLessthan6Percent = differenceLessthan6Percent;
	}
	public String getDifferenceLiteratesPercent() {
		return differenceLiteratesPercent;
	}
	public void setDifferenceLiteratesPercent(String differenceLiteratesPercent) {
		this.differenceLiteratesPercent = differenceLiteratesPercent;
	}
	public Long getDifferenceLiterates() {
		return differenceLiterates;
	}
	public void setDifferenceLiterates(Long differenceLiterates) {
		this.differenceLiterates = differenceLiterates;
	}
	public Long getDifferenceMalePopulation() {
		return differenceMalePopulation;
	}
	public void setDifferenceMalePopulation(Long differenceMalePopulation) {
		this.differenceMalePopulation = differenceMalePopulation;
	}
	public String getDifferenceMalePercent() {
		return differenceMalePercent;
	}
	public void setDifferenceMalePercent(String differenceMalePercent) {
		this.differenceMalePercent = differenceMalePercent;
	}
	public Long getDifferenceFemalePopulation() {
		return differenceFemalePopulation;
	}
	public void setDifferenceFemalePopulation(Long differenceFemalePopulation) {
		this.differenceFemalePopulation = differenceFemalePopulation;
	}
	public String getDifferenceFemalePercent() {
		return differenceFemalePercent;
	}
	public void setDifferenceFemalePercent(String differenceFemalePercent) {
		this.differenceFemalePercent = differenceFemalePercent;
	}
	public Long getDifferenceHouseHolds() {
		return differenceHouseHolds;
	}
	public void setDifferenceHouseHolds(Long differenceHouseHolds) {
		this.differenceHouseHolds = differenceHouseHolds;
	}
	public String getDifferenceHouseHoldsPercent() {
		return differenceHouseHoldsPercent;
	}
	public void setDifferenceHouseHoldsPercent(String differenceHouseHoldsPercent) {
		this.differenceHouseHoldsPercent = differenceHouseHoldsPercent;
	}
	public Long getDifferenceSC() {
		return differenceSC;
	}
	public void setDifferenceSC(Long differenceSC) {
		this.differenceSC = differenceSC;
	}
	public String getDifferenceSCPercent() {
		return differenceSCPercent;
	}
	public void setDifferenceSCPercent(String differenceSCPercent) {
		this.differenceSCPercent = differenceSCPercent;
	}
	public Long getDifferenceST() {
		return differenceST;
	}
	public void setDifferenceST(Long differenceST) {
		this.differenceST = differenceST;
	}
	public String getDifferenceSTPercent() {
		return differenceSTPercent;
	}
	public void setDifferenceSTPercent(String differenceSTPercent) {
		this.differenceSTPercent = differenceSTPercent;
	}
	public Long getDifferenceWorkingPeople() {
		return differenceWorkingPeople;
	}
	public void setDifferenceWorkingPeople(Long differenceWorkingPeople) {
		this.differenceWorkingPeople = differenceWorkingPeople;
	}
	public String getDifferenceWorkingPeoplePercent() {
		return differenceWorkingPeoplePercent;
	}
	public void setDifferenceWorkingPeoplePercent(
			String differenceWorkingPeoplePercent) {
		this.differenceWorkingPeoplePercent = differenceWorkingPeoplePercent;
	}
	public Long getDifferencePopulation() {
		return differencePopulation;
	}
	public void setDifferencePopulation(Long differencePopulation) {
		this.differencePopulation = differencePopulation;
	}
	public String getDifferencePopulationPercent() {
		return differencePopulationPercent;
	}
	public void setDifferencePopulationPercent(String differencePopulationPercent) {
		this.differencePopulationPercent = differencePopulationPercent;
	}
	public Long getLiterates() {
		return literates;
	}
	public void setLiterates(Long literates) {
		this.literates = literates;
	}
	
	public String getPopulationUnderSixPercentage() {
		return populationUnderSixPercentage;
	}
	public void setPopulationUnderSixPercentage(String populationUnderSixPercentage) {
		this.populationUnderSixPercentage = populationUnderSixPercentage;
	}
	public String getLiteratesPercentage() {
		return literatesPercentage;
	}
	public void setLiteratesPercentage(String literatesPercentage) {
		this.literatesPercentage = literatesPercentage;
	}
	public Long getMaleLiterates() {
		return maleLiterates;
	}
	public void setMaleLiterates(Long maleLiterates) {
		this.maleLiterates = maleLiterates;
	}
	public Double getMaleLiteraturePercentage() {
		return maleLiteraturePercentage;
	}
	public void setMaleLiteraturePercentage(Double maleLiteraturePercentage) {
		this.maleLiteraturePercentage = maleLiteraturePercentage;
	}
	public Long getFemaleLiterates() {
		return femaleLiterates;
	}
	public void setFemaleLiterates(Long femaleLiterates) {
		this.femaleLiterates = femaleLiterates;
	}
	public Double getFemaleLiteraturePercentage() {
		return femaleLiteraturePercentage;
	}
	public void setFemaleLiteraturePercentage(Double femaleLiteraturePercentage) {
		this.femaleLiteraturePercentage = femaleLiteraturePercentage;
	}
	public Long getWorkingMale() {
		return workingMale;
	}
	public void setWorkingMale(Long workingMale) {
		this.workingMale = workingMale;
	}
	public Double getTotalWorkingMalePercentage() {
		return totalWorkingMalePercentage;
	}
	public void setTotalWorkingMalePercentage(Double totalWorkingMalePercentage) {
		this.totalWorkingMalePercentage = totalWorkingMalePercentage;
	}
	public Long getWorkingFemale() {
		return workingFemale;
	}
	public void setWorkingFemale(Long workingFemale) {
		this.workingFemale = workingFemale;
	}
	public Double getTotalWorkingFemalePercentage() {
		return totalWorkingFemalePercentage;
	}
	public void setTotalWorkingFemalePercentage(Double totalWorkingFemalePercentage) {
		this.totalWorkingFemalePercentage = totalWorkingFemalePercentage;
	}	
	public BigDecimal getNonWorkingPeoplePercent() {
		return nonWorkingPeoplePercent;
	}
	public void setNonWorkingPeoplePercent(BigDecimal nonWorkingPeoplePercent) {
		this.nonWorkingPeoplePercent = nonWorkingPeoplePercent;
	}
	public Long getNonWorkingPeople() {
		return nonWorkingPeople;
	}
	public void setNonWorkingPeople(Long nonWorkingPeople) {
		this.nonWorkingPeople = nonWorkingPeople;
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
	public String getHouseHoldsPercentage() {
		return houseHoldsPercentage;
	}
	public void setHouseHoldsPercentage(String houseHoldsPercentage) {
		this.houseHoldsPercentage = houseHoldsPercentage;
	}
	public Long getPopulationSC() {
		return populationSC;
	}
	public void setPopulationSC(Long populationSC) {
		this.populationSC = populationSC;
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
	public Long getPopulationST() {
		return populationST;
	}
	public void setPopulationST(Long populationST) {
		this.populationST = populationST;
	}
	public Long getWorkingPeople() {
		return workingPeople;
	}
	public void setWorkingPeople(Long workingPeople) {
		this.workingPeople = workingPeople;
	}
	public String getWorkingPeoplePercentage() {
		return workingPeoplePercentage;
	}
	public void setWorkingPeoplePercentage(String workingPeoplePercentage) {
		this.workingPeoplePercentage = workingPeoplePercentage;
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
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Long getTotalPopulation() {
		return totalPopulation;
	}
	public void setTotalPopulation(Long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	public String getTotalPopulationPercentage() {
		return totalPopulationPercentage;
	}
	public void setTotalPopulationPercentage(String totalPopulationPercentage) {
		this.totalPopulationPercentage = totalPopulationPercentage;
	}
	
	

}
