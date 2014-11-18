package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TdpCadreLocationWiseReportVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String locationType;
	private Long totalVoters;
	private Long targetedCadre;
	private Long tillNowTargetedCadre;
	private Long tillNowRegisteredCadre;
	private Double tillNowRegisteredCadrePerc;
	private Double overallPerc;
	
	private Long maleCount;
	private Long femaleCount;
	private Long registeredMaleCount;
	private Long registeredFemaleCount;
	
	private Long ageRangeId;
	private String ageRange;
	private Long votersInAge;
	private Long cadresInAge;
	private Double agePerc;
	
	List<TdpCadreLocationWiseReportVO>	 tdpCadreLocationWiseReportVOList = new ArrayList<TdpCadreLocationWiseReportVO>();
	List<TdpCadreLocationWiseReportVO>	 tehsilWiseList = new ArrayList<TdpCadreLocationWiseReportVO>();
	List<TdpCadreLocationWiseReportVO>	 panchayatWiseList = new ArrayList<TdpCadreLocationWiseReportVO>();
	
	
	public List<TdpCadreLocationWiseReportVO> getTehsilWiseList() {
		return tehsilWiseList;
	}

	public void setTehsilWiseList(List<TdpCadreLocationWiseReportVO> tehsilWiseList) {
		this.tehsilWiseList = tehsilWiseList;
	}

	public List<TdpCadreLocationWiseReportVO> getPanchayatWiseList() {
		return panchayatWiseList;
	}

	public void setPanchayatWiseList(
			List<TdpCadreLocationWiseReportVO> panchayatWiseList) {
		this.panchayatWiseList = panchayatWiseList;
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

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getTargetedCadre() {
		return targetedCadre;
	}

	public void setTargetedCadre(Long targetedCadre) {
		this.targetedCadre = targetedCadre;
	}

	public Long getTillNowTargetedCadre() {
		return tillNowTargetedCadre;
	}

	public void setTillNowTargetedCadre(Long tillNowTargetedCadre) {
		this.tillNowTargetedCadre = tillNowTargetedCadre;
	}

	public Long getTillNowRegisteredCadre() {
		return tillNowRegisteredCadre;
	}

	public void setTillNowRegisteredCadre(Long tillNowRegisteredCadre) {
		this.tillNowRegisteredCadre = tillNowRegisteredCadre;
	}

	public Double getTillNowRegisteredCadrePerc() {
		return tillNowRegisteredCadrePerc;
	}

	public void setTillNowRegisteredCadrePerc(Double tillNowRegisteredCadrePerc) {
		this.tillNowRegisteredCadrePerc = tillNowRegisteredCadrePerc;
	}

	public Double getOverallPerc() {
		return overallPerc;
	}

	public void setOverallPerc(Double overallPerc) {
		this.overallPerc = overallPerc;
	}

	public Long getMaleCount() {
		return maleCount;
	}

	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}

	public Long getFemaleCount() {
		return femaleCount;
	}

	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
	}

	public Long getRegisteredMaleCount() {
		return registeredMaleCount;
	}

	public void setRegisteredMaleCount(Long registeredMaleCount) {
		this.registeredMaleCount = registeredMaleCount;
	}

	public Long getRegisteredFemaleCount() {
		return registeredFemaleCount;
	}

	public void setRegisteredFemaleCount(Long registeredFemaleCount) {
		this.registeredFemaleCount = registeredFemaleCount;
	}

	public Long getAgeRangeId() {
		return ageRangeId;
	}

	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public Long getVotersInAge() {
		return votersInAge;
	}

	public void setVotersInAge(Long votersInAge) {
		this.votersInAge = votersInAge;
	}

	public Long getCadresInAge() {
		return cadresInAge;
	}

	public void setCadresInAge(Long cadresInAge) {
		this.cadresInAge = cadresInAge;
	}

	public Double getAgePerc() {
		return agePerc;
	}

	public void setAgePerc(Double agePerc) {
		this.agePerc = agePerc;
	}

	public List<TdpCadreLocationWiseReportVO> getTdpCadreLocationWiseReportVOList() {
		return tdpCadreLocationWiseReportVOList;
	}

	public void setTdpCadreLocationWiseReportVOList(
			List<TdpCadreLocationWiseReportVO> tdpCadreLocationWiseReportVOList) {
		this.tdpCadreLocationWiseReportVOList = tdpCadreLocationWiseReportVOList;
	}

	
}
