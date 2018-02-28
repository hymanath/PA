package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class TaxesVO {
	
	private Long id;
	private String name;
	private String demandArrearAmount;
	private String collectionArrearAmount;
	private String balanceArrearAmount;
	private String demandCurrentAmount;
	private String collectionCurrentAmount;
	private String balanceCurentAmount;
	private Long demandArrearUnits = 0L;
	private Long collectionArrearUnits = 0L;
	private Long balanceArrearUnits = 0L;
	private Long demandCurrentUnits = 0L;
	private Long collectionCurrentUnits = 0L;
	private Long balanceCurrentUnits = 0L;
	private String totalDemandAmount;
	private Long totalDemandUnits = 0L;
	private String totalCollectionAmount;
	private Long totalCollectionUnts = 0L;
	private String totalBalanceAmount;
	private Long totalBalanceUnits = 0L;
	private List<TaxesVO> taxesList = new ArrayList<TaxesVO>(0);
	private List<TaxesVO> feeList = new ArrayList<TaxesVO>(0);
	private String collectionAmuntPerc;
	private String collectionUnitsPerc;
	private String balAmuntPerc;
	private String balUnitsPerc;
	private String totalAmount;
	private Long totalUnits = 0L;
	private String percentage;
	
	
	public String getDemandArrearAmount() {
		return demandArrearAmount;
	}
	public void setDemandArrearAmount(String demandArrearAmount) {
		this.demandArrearAmount = demandArrearAmount;
	}
	public String getCollectionArrearAmount() {
		return collectionArrearAmount;
	}
	public void setCollectionArrearAmount(String collectionArrearAmount) {
		this.collectionArrearAmount = collectionArrearAmount;
	}
	public String getBalanceArrearAmount() {
		return balanceArrearAmount;
	}
	public void setBalanceArrearAmount(String balanceArrearAmount) {
		this.balanceArrearAmount = balanceArrearAmount;
	}
	public String getDemandCurrentAmount() {
		return demandCurrentAmount;
	}
	public void setDemandCurrentAmount(String demandCurrentAmount) {
		this.demandCurrentAmount = demandCurrentAmount;
	}
	public String getCollectionCurrentAmount() {
		return collectionCurrentAmount;
	}
	public void setCollectionCurrentAmount(String collectionCurrentAmount) {
		this.collectionCurrentAmount = collectionCurrentAmount;
	}
	public String getBalanceCurentAmount() {
		return balanceCurentAmount;
	}
	public void setBalanceCurentAmount(String balanceCurentAmount) {
		this.balanceCurentAmount = balanceCurentAmount;
	}
	public Long getDemandArrearUnits() {
		return demandArrearUnits;
	}
	public void setDemandArrearUnits(Long demandArrearUnits) {
		this.demandArrearUnits = demandArrearUnits;
	}
	public Long getCollectionArrearUnits() {
		return collectionArrearUnits;
	}
	public void setCollectionArrearUnits(Long collectionArrearUnits) {
		this.collectionArrearUnits = collectionArrearUnits;
	}
	public Long getBalanceArrearUnits() {
		return balanceArrearUnits;
	}
	public void setBalanceArrearUnits(Long balanceArrearUnits) {
		this.balanceArrearUnits = balanceArrearUnits;
	}
	public Long getDemandCurrentUnits() {
		return demandCurrentUnits;
	}
	public void setDemandCurrentUnits(Long demandCurrentUnits) {
		this.demandCurrentUnits = demandCurrentUnits;
	}
	public Long getCollectionCurrentUnits() {
		return collectionCurrentUnits;
	}
	public void setCollectionCurrentUnits(Long collectionCurrentUnits) {
		this.collectionCurrentUnits = collectionCurrentUnits;
	}
	public Long getBalanceCurrentUnits() {
		return balanceCurrentUnits;
	}
	public void setBalanceCurrentUnits(Long balanceCurrentUnits) {
		this.balanceCurrentUnits = balanceCurrentUnits;
	}
	public String getTotalDemandAmount() {
		return totalDemandAmount;
	}
	public void setTotalDemandAmount(String totalDemandAmount) {
		this.totalDemandAmount = totalDemandAmount;
	}
	public Long getTotalDemandUnits() {
		return totalDemandUnits;
	}
	public void setTotalDemandUnits(Long totalDemandUnits) {
		this.totalDemandUnits = totalDemandUnits;
	}
	public String getTotalCollectionAmount() {
		return totalCollectionAmount;
	}
	public void setTotalCollectionAmount(String totalCollectionAmount) {
		this.totalCollectionAmount = totalCollectionAmount;
	}
	public Long getTotalCollectionUnts() {
		return totalCollectionUnts;
	}
	public void setTotalCollectionUnts(Long totalCollectionUnts) {
		this.totalCollectionUnts = totalCollectionUnts;
	}
	public String getTotalBalanceAmount() {
		return totalBalanceAmount;
	}
	public void setTotalBalanceAmount(String totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}
	public Long getTotalBalanceUnits() {
		return totalBalanceUnits;
	}
	public void setTotalBalanceUnits(Long totalBalanceUnits) {
		this.totalBalanceUnits = totalBalanceUnits;
	}
	public List<TaxesVO> getTaxesList() {
		return taxesList;
	}
	public void setTaxesList(List<TaxesVO> taxesList) {
		this.taxesList = taxesList;
	}
	public List<TaxesVO> getFeeList() {
		return feeList;
	}
	public void setFeeList(List<TaxesVO> feeList) {
		this.feeList = feeList;
	}
	public String getCollectionAmuntPerc() {
		return collectionAmuntPerc;
	}
	public void setCollectionAmuntPerc(String collectionAmuntPerc) {
		this.collectionAmuntPerc = collectionAmuntPerc;
	}
	public String getCollectionUnitsPerc() {
		return collectionUnitsPerc;
	}
	public void setCollectionUnitsPerc(String collectionUnitsPerc) {
		this.collectionUnitsPerc = collectionUnitsPerc;
	}
	public String getBalAmuntPerc() {
		return balAmuntPerc;
	}
	public void setBalAmuntPerc(String balAmuntPerc) {
		this.balAmuntPerc = balAmuntPerc;
	}
	public String getBalUnitsPerc() {
		return balUnitsPerc;
	}
	public void setBalUnitsPerc(String balUnitsPerc) {
		this.balUnitsPerc = balUnitsPerc;
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(Long totalUnits) {
		this.totalUnits = totalUnits;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
