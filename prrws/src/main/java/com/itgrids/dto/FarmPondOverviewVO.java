package com.itgrids.dto;

import java.io.Serializable;

public class FarmPondOverviewVO implements Serializable 
{
	private String averagePerDistrict;
    private String averagePerConstituency;
    private String averagePerMandal;
    private Long totalBudget;
    private String totalAvgFarmsInDistrict;
    private String totalAvgFarmsInConstituency;
    private String totalAvgFarmsInMandal;
    private Long districtsInRed;
    private Long districtsInOrgange;
    private Long districtsInGreen;
    private Long totalDistricts;
    private Long constituenciesInRed;
    private Long constituenciesInOrgange;
    private Long constituenciesInGreen;
    private Long totalConstituencies;
    private Long mandalsInRed;
    private Long mandalsInOrgange;
    private Long mandalsInGreen;
    private Long totalMandals;
    private Long villagesInRed;
    private Long villagesInOrgange;
    private Long villagesInGreen;
    private Long totalVillages;
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
	public Long getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(Long totalBudget) {
		this.totalBudget = totalBudget;
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
	public Long getDistrictsInOrgange() {
		return districtsInOrgange;
	}
	public void setDistrictsInOrgange(Long districtsInOrgange) {
		this.districtsInOrgange = districtsInOrgange;
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
	public Long getConstituenciesInOrgange() {
		return constituenciesInOrgange;
	}
	public void setConstituenciesInOrgange(Long constituenciesInOrgange) {
		this.constituenciesInOrgange = constituenciesInOrgange;
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
	public Long getMandalsInOrgange() {
		return mandalsInOrgange;
	}
	public void setMandalsInOrgange(Long mandalsInOrgange) {
		this.mandalsInOrgange = mandalsInOrgange;
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
	public Long getVillagesInOrgange() {
		return villagesInOrgange;
	}
	public void setVillagesInOrgange(Long villagesInOrgange) {
		this.villagesInOrgange = villagesInOrgange;
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
	@Override
	public String toString() {
		return "FarmPondOverviewVO [averagePerDistrict=" + averagePerDistrict + ", averagePerConstituency="
				+ averagePerConstituency + ", averagePerMandal=" + averagePerMandal + ", totalBudget=" + totalBudget
				+ ", totalAvgFarmsInDistrict=" + totalAvgFarmsInDistrict + ", totalAvgFarmsInConstituency="
				+ totalAvgFarmsInConstituency + ", totalAvgFarmsInMandal=" + totalAvgFarmsInMandal + ", districtsInRed="
				+ districtsInRed + ", districtsInOrgange=" + districtsInOrgange + ", districtsInGreen="
				+ districtsInGreen + ", totalDistricts=" + totalDistricts + ", constituenciesInRed="
				+ constituenciesInRed + ", constituenciesInOrgange=" + constituenciesInOrgange
				+ ", constituenciesInGreen=" + constituenciesInGreen + ", totalConstituencies=" + totalConstituencies
				+ ", mandalsInRed=" + mandalsInRed + ", mandalsInOrgange=" + mandalsInOrgange + ", mandalsInGreen="
				+ mandalsInGreen + ", totalMandals=" + totalMandals + ", villagesInRed=" + villagesInRed
				+ ", villagesInOrgange=" + villagesInOrgange + ", villagesInGreen=" + villagesInGreen
				+ ", totalVillages=" + totalVillages + "]";
	}

}
