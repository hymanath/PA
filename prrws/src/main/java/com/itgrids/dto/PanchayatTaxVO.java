package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PanchayatTaxVO {

	private Long id;
	private String name;
	
	private Long districtId;
	private String districtName;
	private Long parliamentId;
	private String parliamentName;
	private Long assemblyId;
	private String assemblyName;
	private Long mandalId;
	private String mandalName;
	private Long panchayatId;
	private String panchyatName;
	
	private Long taxTypeId;
	private String taxType;
	private String amount;
	private String dueYear;
	
	private List<PanchayatTaxVO> subList = new ArrayList<PanchayatTaxVO>();
	
	private Long usageTypeId;
	private String usageName;
	private String demand;
	private String collection;
	private String balance;
	private String demandAssmts;
	private String collectionAssmts;
	private String balanceAssmts;
	
	private String arrearDemand = "0";
	private String arrearCollection = "0";
	private String arrearBalance = "0";
	private String arrearDemandAssmts = "0";
	private String arrearCollectionAssmts = "0";
	private String arrearBalanceAssmts = "0";
	private String currentDemand = "0";
	private String currentCollection = "0";
	private String currentBalance = "0";
	private String currentDemandAssmts = "0";
	private String currentCollectionAssmts = "0";
	private String currentBalanceAssmts = "0";
	private String totalDemand = "0";
	private String totalCollection = "0";
	private String totalBalance = "0";
	private String totalDemandAssmts = "0";
	private String totalCollectionAssmts = "0";
	private String totalBalanceAssmts = "0";
	
	private String defaultersTax;
	private String defaultersAssmts;
	
	private String collectinAmntPerc;
	private String balanceAmntPerc;
	
	
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
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchyatName() {
		return panchyatName;
	}
	public void setPanchyatName(String panchyatName) {
		this.panchyatName = panchyatName;
	}
	public Long getTaxTypeId() {
		return taxTypeId;
	}
	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDueYear() {
		return dueYear;
	}
	public void setDueYear(String dueYear) {
		this.dueYear = dueYear;
	}
	public List<PanchayatTaxVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PanchayatTaxVO> subList) {
		this.subList = subList;
	}
	public Long getUsageTypeId() {
		return usageTypeId;
	}
	public void setUsageTypeId(Long usageTypeId) {
		this.usageTypeId = usageTypeId;
	}
	public String getUsageName() {
		return usageName;
	}
	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getDemandAssmts() {
		return demandAssmts;
	}
	public void setDemandAssmts(String demandAssmts) {
		this.demandAssmts = demandAssmts;
	}
	public String getCollectionAssmts() {
		return collectionAssmts;
	}
	public void setCollectionAssmts(String collectionAssmts) {
		this.collectionAssmts = collectionAssmts;
	}
	public String getBalanceAssmts() {
		return balanceAssmts;
	}
	public void setBalanceAssmts(String balanceAssmts) {
		this.balanceAssmts = balanceAssmts;
	}
	public String getArrearDemand() {
		return arrearDemand;
	}
	public void setArrearDemand(String arrearDemand) {
		this.arrearDemand = arrearDemand;
	}
	public String getArrearCollection() {
		return arrearCollection;
	}
	public void setArrearCollection(String arrearCollection) {
		this.arrearCollection = arrearCollection;
	}
	public String getArrearBalance() {
		return arrearBalance;
	}
	public void setArrearBalance(String arrearBalance) {
		this.arrearBalance = arrearBalance;
	}
	public String getArrearDemandAssmts() {
		return arrearDemandAssmts;
	}
	public void setArrearDemandAssmts(String arrearDemandAssmts) {
		this.arrearDemandAssmts = arrearDemandAssmts;
	}
	public String getArrearCollectionAssmts() {
		return arrearCollectionAssmts;
	}
	public void setArrearCollectionAssmts(String arrearCollectionAssmts) {
		this.arrearCollectionAssmts = arrearCollectionAssmts;
	}
	public String getArrearBalanceAssmts() {
		return arrearBalanceAssmts;
	}
	public void setArrearBalanceAssmts(String arrearBalanceAssmts) {
		this.arrearBalanceAssmts = arrearBalanceAssmts;
	}
	public String getCurrentDemand() {
		return currentDemand;
	}
	public void setCurrentDemand(String currentDemand) {
		this.currentDemand = currentDemand;
	}
	public String getCurrentCollection() {
		return currentCollection;
	}
	public void setCurrentCollection(String currentCollection) {
		this.currentCollection = currentCollection;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getCurrentDemandAssmts() {
		return currentDemandAssmts;
	}
	public void setCurrentDemandAssmts(String currentDemandAssmts) {
		this.currentDemandAssmts = currentDemandAssmts;
	}
	public String getCurrentCollectionAssmts() {
		return currentCollectionAssmts;
	}
	public void setCurrentCollectionAssmts(String currentCollectionAssmts) {
		this.currentCollectionAssmts = currentCollectionAssmts;
	}
	public String getCurrentBalanceAssmts() {
		return currentBalanceAssmts;
	}
	public void setCurrentBalanceAssmts(String currentBalanceAssmts) {
		this.currentBalanceAssmts = currentBalanceAssmts;
	}
	public String getTotalDemand() {
		return totalDemand;
	}
	public void setTotalDemand(String totalDemand) {
		this.totalDemand = totalDemand;
	}
	public String getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(String totalCollection) {
		this.totalCollection = totalCollection;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}
	public String getTotalDemandAssmts() {
		return totalDemandAssmts;
	}
	public void setTotalDemandAssmts(String totalDemandAssmts) {
		this.totalDemandAssmts = totalDemandAssmts;
	}
	public String getTotalCollectionAssmts() {
		return totalCollectionAssmts;
	}
	public void setTotalCollectionAssmts(String totalCollectionAssmts) {
		this.totalCollectionAssmts = totalCollectionAssmts;
	}
	public String getTotalBalanceAssmts() {
		return totalBalanceAssmts;
	}
	public void setTotalBalanceAssmts(String totalBalanceAssmts) {
		this.totalBalanceAssmts = totalBalanceAssmts;
	}
	public String getDefaultersTax() {
		return defaultersTax;
	}
	public void setDefaultersTax(String defaultersTax) {
		this.defaultersTax = defaultersTax;
	}
	public String getDefaultersAssmts() {
		return defaultersAssmts;
	}
	public void setDefaultersAssmts(String defaultersAssmts) {
		this.defaultersAssmts = defaultersAssmts;
	}
	public String getCollectinAmntPerc() {
		return collectinAmntPerc;
	}
	public void setCollectinAmntPerc(String collectinAmntPerc) {
		this.collectinAmntPerc = collectinAmntPerc;
	}
	public String getBalanceAmntPerc() {
		return balanceAmntPerc;
	}
	public void setBalanceAmntPerc(String balanceAmntPerc) {
		this.balanceAmntPerc = balanceAmntPerc;
	}
}
