package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreDataAnalysisVO {

	private Long id;
	private String name;
	private Long districtId;
	private String districtName;
	private Long parliamentId;
	private String parliament;
	private Long totalBooths;
	private Long startedBooths;
	private Long belowCadres = 0l;
	private Long maleCnt = 0l;
	private Long feMaleCnt = 0l;
	private Long difference = 0l;
	private Long total = 0l;
	private String malePercentage;
	private String femalePercentage;
	private String percentage;
	private Long count = 0l;
	private Long mCount =0l;
	private Long fCount= 0l;
	private List<Long> below10BoothIds = new ArrayList<Long>();
	private List<Long> boothIds = new ArrayList<Long>();
	private List<CadreDataAnalysisVO> subList = new ArrayList<CadreDataAnalysisVO>();
	
	

	public List<Long> getBoothIds() {
		return boothIds;
	}
	public void setBoothIds(List<Long> boothIds) {
		this.boothIds = boothIds;
	}
	public List<Long> getBelow10BoothIds() {
		return below10BoothIds;
	}
	public void setBelow10BoothIds(List<Long> below10BoothIds) {
		this.below10BoothIds = below10BoothIds;
	}
	public Long getmCount() {
		return mCount;
	}
	public void setmCount(Long mCount) {
		this.mCount = mCount;
	}
	public Long getfCount() {
		return fCount;
	}
	public void setfCount(Long fCount) {
		this.fCount = fCount;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<CadreDataAnalysisVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreDataAnalysisVO> subList) {
		this.subList = subList;
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Long getStartedBooths() {
		return startedBooths;
	}
	public void setStartedBooths(Long startedBooths) {
		this.startedBooths = startedBooths;
	}
	public Long getBelowCadres() {
		return belowCadres;
	}
	public void setBelowCadres(Long belowCadres) {
		this.belowCadres = belowCadres;
	}
	public Long getMaleCnt() {
		return maleCnt;
	}
	public void setMaleCnt(Long maleCnt) {
		this.maleCnt = maleCnt;
	}
	public Long getFeMaleCnt() {
		return feMaleCnt;
	}
	public void setFeMaleCnt(Long feMaleCnt) {
		this.feMaleCnt = feMaleCnt;
	}
	public Long getDifference() {
		return difference;
	}
	public void setDifference(Long difference) {
		this.difference = difference;
	}
	public String getMalePercentage() {
		return malePercentage;
	}
	public void setMalePercentage(String malePercentage) {
		this.malePercentage = malePercentage;
	}
	public String getFemalePercentage() {
		return femalePercentage;
	}
	public void setFemalePercentage(String femalePercentage) {
		this.femalePercentage = femalePercentage;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
}
