package com.itgrids.dto;

import java.io.Serializable;

	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : IhhlProjectVO Vo Class
	 */
public class NregsOverviewVO implements Serializable{

	  private String averagePerDistrict;
	  private String averagePerConstituency;
	  private String averagePerMandal;
	  private Long totalBudget;
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
	  private Long mandalsInRed;
	  private Long mandalsInOrange;
	  private Long mandalsInGreen;
	  private Long totalMandals;
	  private Long villagesInRed;
	  private Long villagesInOrange;
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
}
