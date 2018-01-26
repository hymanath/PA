package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : IhhlProjectVO Vo Class
	 */
public class NregsOverviewVO implements Serializable{
	
	private Long id;
	private String name;
	private String averagePerDistrict;
	private String averagePerConstituency;
	private String averagePerMandal;
	private String totalBudget;
	private String totalAvgFarmsInDistrict;
	private String totalAvgFarmsInConstituency;
	private String totalAvgFarmsInMandal;
	private Long districtsInRed;
	private Long districtsInOrange;
	private Long districtsInGreen;
	private Long totalDistricts;
	private Long constituenciesInRed;
	private Long constituenciesInOrange;
	private Long constituenciesInGreen;
	private Long totalConstituencies;
	private Long mandalsInRed = 0L;
	private Long mandalsInOrange = 0L;
	private Long mandalsInGreen = 0L;
	private Long totalMandals;
	private Long villagesInRed;
	private Long villagesInOrange;
	private Long villagesInGreen;
	private Long totalVillages;
	private String totalBudget1;
	private Long districtsInGold;
	private Long constituenciesInGold;
	private Long mandalsInGold = 0L;
	private Long villagesInGold;
	private Long previousRedMandals = 0L;
	private Long previousOrangeMandals = 0L;
	private Long previousGreenMandals = 0L;
	private Long previousGoldMandals = 0L;
	private List<String> previousRedList = new ArrayList<String>();
	private List<String> previousOrangeList = new ArrayList<String>();
	private List<String> previousGreenList = new ArrayList<String>();
	private List<String> previousGoldList = new ArrayList<String>();
	private List<NregsOverviewVO> subList = new ArrayList<NregsOverviewVO>();
	private Long previousCount = 0L;
	private Long presentCount = 0L;
	private Long changedCount = 0L;
	
	
	public Long getChangedCount() {
		return changedCount;
	}
	public void setChangedCount(Long changedCount) {
		this.changedCount = changedCount;
	}
	public Long getPreviousCount() {
		return previousCount;
	}
	public void setPreviousCount(Long previousCount) {
		this.previousCount = previousCount;
	}
	public Long getPresentCount() {
		return presentCount;
	}
	public void setPresentCount(Long presentCount) {
		this.presentCount = presentCount;
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
	public List<NregsOverviewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NregsOverviewVO> subList) {
		this.subList = subList;
	}
	public List<String> getPreviousRedList() {
		return previousRedList;
	}
	public void setPreviousRedList(List<String> previousRedList) {
		this.previousRedList = previousRedList;
	}
	public List<String> getPreviousOrangeList() {
		return previousOrangeList;
	}
	public void setPreviousOrangeList(List<String> previousOrangeList) {
		this.previousOrangeList = previousOrangeList;
	}
	public List<String> getPreviousGreenList() {
		return previousGreenList;
	}
	public void setPreviousGreenList(List<String> previousGreenList) {
		this.previousGreenList = previousGreenList;
	}
	public List<String> getPreviousGoldList() {
		return previousGoldList;
	}
	public void setPreviousGoldList(List<String> previousGoldList) {
		this.previousGoldList = previousGoldList;
	}
	public Long getPreviousRedMandals() {
		return previousRedMandals;
	}
	public void setPreviousRedMandals(Long previousRedMandals) {
		this.previousRedMandals = previousRedMandals;
	}
	public Long getPreviousOrangeMandals() {
		return previousOrangeMandals;
	}
	public void setPreviousOrangeMandals(Long previousOrangeMandals) {
		this.previousOrangeMandals = previousOrangeMandals;
	}
	public Long getPreviousGreenMandals() {
		return previousGreenMandals;
	}
	public void setPreviousGreenMandals(Long previousGreenMandals) {
		this.previousGreenMandals = previousGreenMandals;
	}
	public Long getPreviousGoldMandals() {
		return previousGoldMandals;
	}
	public void setPreviousGoldMandals(Long previousGoldMandals) {
		this.previousGoldMandals = previousGoldMandals;
	}
	public Long getDistrictsInGold() {
		return districtsInGold;
	}
	public void setDistrictsInGold(Long districtsInGold) {
		this.districtsInGold = districtsInGold;
	}
	public Long getConstituenciesInGold() {
		return constituenciesInGold;
	}
	public void setConstituenciesInGold(Long constituenciesInGold) {
		this.constituenciesInGold = constituenciesInGold;
	}
	public Long getMandalsInGold() {
		return mandalsInGold;
	}
	public void setMandalsInGold(Long mandalsInGold) {
		this.mandalsInGold = mandalsInGold;
	}
	public Long getVillagesInGold() {
		return villagesInGold;
	}
	public void setVillagesInGold(Long villagesInGold) {
		this.villagesInGold = villagesInGold;
	}
	public String getTotalBudget1() {
		return totalBudget1;
	}
	public void setTotalBudget1(String totalBudget1) {
		this.totalBudget1 = totalBudget1;
	}
	public String getAveragePerDistrict() {
		return averagePerDistrict;
	}
	public void setAveragePerDistrict(String averagePerDistrict) {
		this.averagePerDistrict = averagePerDistrict;
	}
	public String getAveragePerConstituency() {
		return averagePerConstituency;
	}
	public void setAveragePerConstituency(String averagePerConstituency) {
		this.averagePerConstituency = averagePerConstituency;
	}
	public String getAveragePerMandal() {
		return averagePerMandal;
	}
	public void setAveragePerMandal(String averagePerMandal) {
		this.averagePerMandal = averagePerMandal;
	}
	public String getTotalAvgFarmsInDistrict() {
		return totalAvgFarmsInDistrict;
	}
	public void setTotalAvgFarmsInDistrict(String totalAvgFarmsInDistrict) {
		this.totalAvgFarmsInDistrict = totalAvgFarmsInDistrict;
	}
	public String getTotalAvgFarmsInConstituency() {
		return totalAvgFarmsInConstituency;
	}
	public void setTotalAvgFarmsInConstituency(String totalAvgFarmsInConstituency) {
		this.totalAvgFarmsInConstituency = totalAvgFarmsInConstituency;
	}
	public String getTotalAvgFarmsInMandal() {
		return totalAvgFarmsInMandal;
	}
	public void setTotalAvgFarmsInMandal(String totalAvgFarmsInMandal) {
		this.totalAvgFarmsInMandal = totalAvgFarmsInMandal;
	}
	public Long getDistrictsInRed() {
		return districtsInRed;
	}
	public void setDistrictsInRed(Long districtsInRed) {
		this.districtsInRed = districtsInRed;
	}
	public Long getDistrictsInGreen() {
		return districtsInGreen;
	}
	public void setDistrictsInGreen(Long districtsInGreen) {
		this.districtsInGreen = districtsInGreen;
	}
	public Long getTotalDistricts() {
		return totalDistricts;
	}
	public void setTotalDistricts(Long totalDistricts) {
		this.totalDistricts = totalDistricts;
	}
	public Long getConstituenciesInRed() {
		return constituenciesInRed;
	}
	public void setConstituenciesInRed(Long constituenciesInRed) {
		this.constituenciesInRed = constituenciesInRed;
	}
	public Long getConstituenciesInGreen() {
		return constituenciesInGreen;
	}
	public void setConstituenciesInGreen(Long constituenciesInGreen) {
		this.constituenciesInGreen = constituenciesInGreen;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getMandalsInRed() {
		return mandalsInRed;
	}
	public void setMandalsInRed(Long mandalsInRed) {
		this.mandalsInRed = mandalsInRed;
	}
	public Long getMandalsInGreen() {
		return mandalsInGreen;
	}
	public void setMandalsInGreen(Long mandalsInGreen) {
		this.mandalsInGreen = mandalsInGreen;
	}
	public Long getTotalMandals() {
		return totalMandals;
	}
	public void setTotalMandals(Long totalMandals) {
		this.totalMandals = totalMandals;
	}
	public Long getVillagesInRed() {
		return villagesInRed;
	}
	public void setVillagesInRed(Long villagesInRed) {
		this.villagesInRed = villagesInRed;
	}
	public Long getVillagesInGreen() {
		return villagesInGreen;
	}
	public void setVillagesInGreen(Long villagesInGreen) {
		this.villagesInGreen = villagesInGreen;
	}
	public Long getTotalVillages() {
		return totalVillages;
	}
	public void setTotalVillages(Long totalVillages) {
		this.totalVillages = totalVillages;
	}
	public Long getDistrictsInOrange() {
		return districtsInOrange;
	}
	public void setDistrictsInOrange(Long districtsInOrange) {
		this.districtsInOrange = districtsInOrange;
	}
	public Long getConstituenciesInOrange() {
		return constituenciesInOrange;
	}
	public void setConstituenciesInOrange(Long constituenciesInOrange) {
		this.constituenciesInOrange = constituenciesInOrange;
	}
	public Long getMandalsInOrange() {
		return mandalsInOrange;
	}
	public void setMandalsInOrange(Long mandalsInOrange) {
		this.mandalsInOrange = mandalsInOrange;
	}
	public Long getVillagesInOrange() {
		return villagesInOrange;
	}
	public void setVillagesInOrange(Long villagesInOrange) {
		this.villagesInOrange = villagesInOrange;
	}
	public String getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}
}
